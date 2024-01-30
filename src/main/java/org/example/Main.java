package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hudini's library. Register user first");
        Library library = new Library();
        UsersDataBase usersDataBase = new UsersDataBase();
        Logic logic=null;
        while(true) {
            System.out.println("Pass name of user");
            String name = scanner.nextLine();
            usersDataBase.addUserToDatabase(name,library);
            System.out.println("User added sucessfully");
            System.out.println("Users availabe " +usersDataBase.listOfUser.size());
            System.out.println("Do you want to add next user Y/N");
            String decisionInner = scanner.nextLine();
            if(decisionInner.equalsIgnoreCase("N")){
                break;
            }
        }

        while (true) {

            System.out.println("Select user to log in");
            for(User users : usersDataBase.listOfUser){
                System.out.println("Name: "+ users.getName());
            }
            String userSelection = scanner.nextLine();


            if (userSelection.equalsIgnoreCase("Admin")) {
                while (true) {
                    System.out.println("Decide what you want to do:\n" +
                            "1: Add a book\n" +
                            "2: Return info of all books\n" +
                            "3: Return specific info of a book\n" +
                            "4: Delete book\n" +
                            "5: Update book\n" +
                            "7: Change user\n" +
                            "8: Add user\n"+
                            "6: Exit library");
                    String decision = scanner.nextLine();
                    if (decision.equalsIgnoreCase("6")) {
                        System.out.println("Thanks for visiting");
                        break;
                    }
                    if (decision.equalsIgnoreCase("7")) {
                        break;
                    }
                    if (decision.equalsIgnoreCase("8")) {
                        System.out.println("Pass name of user");
                        String name = scanner.nextLine();
                        usersDataBase.addUserToDatabase(name,library);
                        System.out.println("Registration confirmed");
                    }
                    logic = new Logic(library,usersDataBase);
                    logic.bookShopFlow(decision);
                }
            } else {
                while (true) {
                    System.out.println("Decide what you want to do:\n" +
                            "1: Rent a book\n" +
                            "2: Show book assigned to User\n"+
                            "3: Change user\n" +
                            "4: Exit User");

                    String decision = scanner.nextLine();
                    logic.userActionFlow(decision,userSelection);
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
    }
}
