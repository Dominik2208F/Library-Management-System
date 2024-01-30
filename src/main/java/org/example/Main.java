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

            if (userSelection.equalsIgnoreCase("Admin")) {
                logic.adminFlow();
            } else if(usersDataBase.listOfUser.contains(userSelection)) {
                logic.userFlow(userSelection);
            }
            else{
                break;
            }
        }
    }
}
