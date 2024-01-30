package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hudini's library. Register user first");

        Library library = new Library();
        UsersDataBase usersDataBase = new UsersDataBase();
        Logic logic = new Logic(library,usersDataBase);
        library.addDefaultBooksToLibrary();
        usersDataBase.createDefaultAdminUser(library);
        usersDataBase.createNewUser(library);

        while (true) {
            usersDataBase.getAllAvailableUser();
            String userSelection = scanner.nextLine();
            if(usersDataBase.checkifUserExist(userSelection)) {
                switch (userSelection) {
                    case "Admin":
                        if(usersDataBase.validateAdminLogin()) {
                            logic.adminFlow();
                        }
                        else{
                            System.out.println("Wrong password.");
                        }
                       break;
                    default:
                        if(usersDataBase.validateUserLogin(userSelection)) {
                            logic.userFlow(userSelection);
                        }
                        else{
                            System.out.println("Wrong password");
                        }
                        break;
                }
            }
            else{
                System.out.println("User does not exist");
            }
        }
    }
}
