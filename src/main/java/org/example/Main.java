package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hudini's library\n" +
                "Register user first");
        UsersDataBase usersDataBase = new UsersDataBase();
        Library library = new Library(usersDataBase);  //set dataBase of Users in library

        Logic logic = new Logic(library); // logic stores workflow logic

        library.addDefaultBooksToLibrary(); // add default books to library
        usersDataBase.createDefaultAdminUser(); // add admin to library
        usersDataBase.createNewUser();  // create new Users object

        while (true) {
            usersDataBase.getAllAvailableUser();
            String userSelection = scanner.nextLine();
            if(userSelection.equals("0")){
                break;
            }
            if(usersDataBase.checkIfUserExist(userSelection)) {
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
