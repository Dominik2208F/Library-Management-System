package org.example;

import java.util.Scanner;

public class Flow {

    Library flowLibrary;
    SetUp setup;
    public Flow(SetUp setUp) {
        this.setup=setUp;
    }

    public void mainFlow(){
        Scanner scanner = new Scanner(System.in);

        flowLibrary= setup.selectLibraryInstanceToVisit();  // assign library instance to flowLibrary variable to work on

        flowLibrary.getLibraryUserDataBase().addUserToLibrary(); // add user to that library through database of User

        boolean forceSwitchLibrary=false;

        //USER ACTION
        while(true) {
            if(forceSwitchLibrary){
                break;
            }
            flowLibrary.getLibraryUserDataBase().getAllAvailableUserSwitch(); // Users assigned to that library instance
            String userSelection = scanner.nextLine();
            if (userSelection.equals("switch")) {
                break;
            }
            if (flowLibrary.getLibraryUserDataBase().checkIfUserExist(userSelection)) {
                switch (userSelection) {
                    case "Admin":
                        if (flowLibrary.getLibraryUserDataBase().validateAdminLogin()) {
                            forceSwitchLibrary =adminFlow();
                        } else {
                            System.out.println("Wrong password.");
                        }
                        break;
                    default:
                        if (flowLibrary.getLibraryUserDataBase().validateUserLogin(userSelection)) {
                            userFlow(userSelection);
                        } else {
                            System.out.println("Wrong password");
                        }
                        break;
                }
            } else {
                System.out.println("User does not exist");
            }
        }
    }
    public boolean adminFlow(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Decide what you want to do:\n" +
                    "1: Add a book\n" +
                    "2: Return info of all available books\n" +
                    "3: Return specific info of a book\n" +
                    "4: Delete book\n" +
                    "5: Update book\n" +
                    "7: Change user\n" +
                    "8: Add user\n"+
                    "9: Change library");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("7")) {
                System.out.println("Thanks for visiting.You are going to user selection view");
                break;
            }
            if (decision.equalsIgnoreCase("8")) {
                flowLibrary.getLibraryUserDataBase().addUserToDatabase();
                System.out.println("User registration confirmed");
            }
            if (decision.equalsIgnoreCase("9")) {
                System.out.println("Thanks for visiting. You are going to library selection view");
                return true;
            }
            adminActions(decision);
        }
        return false;
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
    public void adminActions(String decision){
        String decisionInner = "";
        Scanner scanner = new Scanner(System.in);
        switch (decision) {
            case "1":
                do {
                    flowLibrary.addBookToLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "2":
                do {
                    flowLibrary.returnAllBooksInfo();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "3":
                do {
                    flowLibrary.returnSpecificBookInfoFromLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "4":
                do {
                    flowLibrary.deleteBookFromLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            case "5": {
                do {
                    flowLibrary.updateBookInfoInTheLibrary();
                    System.out.println("Go back to main menu. Y/N");
                    decisionInner = scanner.nextLine();
                } while (!decisionInner.equalsIgnoreCase("Y"));
                break;
            }
        }
    }
    public void userActions(String decision,String user){
        if (decision.equalsIgnoreCase("1")) {
            User userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(user);
            flowLibrary.rentABookByTitle(userFromList);
        }
        if (decision.equalsIgnoreCase("2")) {
            User userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(user);
            flowLibrary.returnBookByTitle(userFromList);
        }
        if (decision.equalsIgnoreCase("3")) {
            User userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(user);
            userFromList.showBooks();
        }
    }
}
