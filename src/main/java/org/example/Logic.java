package org.example;

import java.util.Scanner;

public class Logic {

    Library library;
    UsersDataBase usersDataBase;
   public Logic(Library library,UsersDataBase usersDataBase){
       this.library= library;
       this.usersDataBase= usersDataBase;
   }

    public void bookShopFlow(String decision){
        String decisionInner = "";
        Scanner scanner = new Scanner(System.in);
        switch (decision) {
            case "1":
                do {
                    library.addBookToLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "2":
                do {
                    library.returnAllBooksInfo();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "3":
                do {
                    library.returnSpecificBookInfoFromLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "4":
                do {
                    library.deleteBookFromLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "5": {
                do {
                    library.updateBookInfoInTheLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            }
        }
    }

    public void userActionFlow(String decision,String user){
        if (decision.equalsIgnoreCase("1")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            library.rentABookByTitle(userFromList);
        }
        if (decision.equalsIgnoreCase("2")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            userFromList.showBooks();
        }
    }

    public void adminFlow(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Add a book\n" +
                    "2: Return info of all books\n" +
                    "3: Return specific info of a book\n" +
                    "4: Delete book\n" +
                    "5: Update book\n" +
                    "7: Change user\n" +
                    "8: Add user\n"+
                    "6: Log out");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("6")) {
                System.out.println("Thanks for visiting");
                break;
            }
            if (decision.equalsIgnoreCase("7")) {
                break;
            }
            if (decision.equalsIgnoreCase("8")) {
                usersDataBase.addUserToDatabase(library);
                System.out.println("Registration confirmed");
            }
            bookShopFlow(decision);
        }
    }
    public void userFlow(String userSelection){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Rent a book\n" +
                    "2: Show book assigned to User\n"+
                    "3: Change user\n" +
                    "4: Log out");

            String decision = scanner.nextLine();

            userActionFlow(decision,userSelection);

            if (decision.equalsIgnoreCase("3")) {
                System.out.println("Thanks for visiting");
                break;
            }
            if (decision.equalsIgnoreCase("4")) {
                break;
            }
        }
    }
}
