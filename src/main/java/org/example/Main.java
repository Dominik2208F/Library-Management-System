package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hudini's library. Register user first");
        Library library = new Library();
        UsersDataBase usersDataBase = new UsersDataBase();
        Logic logic;
        usersDataBase.createNewUser(library);

        while (true) {
            logic = new Logic(library,usersDataBase);
            usersDataBase.getAllAvailableUser();
            String userSelection = scanner.nextLine();
            if(usersDataBase.checkifUserExist(userSelection)) {
                switch (userSelection) {
                    case "Admin":
                        logic.adminFlow();
                        break;
                    default:
                        logic.userFlow(userSelection);
                        break;
                }
            }
            else{
                System.out.println("User does not exist");
            }
        }
    }
}
