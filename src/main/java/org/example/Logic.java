package org.example;

import java.util.Scanner;

public class Logic {

    private Library library;
    private UsersDataBase usersDataBase;
   public Logic(Library library,UsersDataBase usersDataBase){
       this.library= library;
       this.usersDataBase= usersDataBase;
   }

    public void adminActions(String decision){
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
    public void userActions(String decision,String user){
        if (decision.equalsIgnoreCase("1")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            library.rentABookByTitle(userFromList);
        }
        if (decision.equalsIgnoreCase("2")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            library.returnBookByTitle(userFromList);
        }
        if (decision.equalsIgnoreCase("3")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            userFromList.showBooks();
        }
    }
    public void userFlow(String userSelection){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Rent a book\n" +
                    "2: Return a book\n"+
                    "3: Show book assigned to User\n"+
                    "4: Change user");

            String decision = scanner.nextLine();

            userActions(decision,userSelection);

            if (decision.equalsIgnoreCase("4")) {
                System.out.println("Thanks for visiting");
                break;
            }
        }
    }
    public void adminFlow(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Add a book\n" +
                    "2: Return info of all available books\n" +
                    "3: Return specific info of a book\n" +
                    "4: Delete book\n" +
                    "5: Update book\n" +
                    "7: Change user\n" +
                    "8: Add user");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("7")) {
                System.out.println("Thanks for visiting");
                break;
            }
            if (decision.equalsIgnoreCase("8")) {
                usersDataBase.addUserToDatabase();
                System.out.println("Registration confirmed");
            }
            adminActions(decision);
        }
    }
}
