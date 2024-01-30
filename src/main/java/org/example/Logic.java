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
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "2":
                do {
                    library.returnAllBooksInfo();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "3":
                do {
                    library.returnSpecificBookInfoFromLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "4":
                do {
                    library.deleteBookFromLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "5": {
                do {
                    library.updateBookInfoInTheLibrary();
                    System.out.println("Do you want to go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            }
        }
    }

    public void userActionFlow(String decision,String user){
       Scanner scanner = new Scanner(System.in);
        if (decision.equalsIgnoreCase("1")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            System.out.println("Pass title");
            String decision1 = scanner.nextLine();
            library.rentABookByTitle(decision1, userFromList);
        }
        if (decision.equalsIgnoreCase("2")) {
            User userFromList = usersDataBase.returnObjectOfUserByName(user);
            userFromList.showBooks();
        }
    }

}
