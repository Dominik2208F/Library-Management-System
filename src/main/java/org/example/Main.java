package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LibraryDataBase libraryDataBase= new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);
        Library currentLibrary=null;


        SetUp.createDefaultSetUpOsiedlowa();
        SetUp.createDefaultSetUpHudini();


        while (true) {
            currentLibrary= SetUp.selectLibraryInstanceToVisit();
            //ASIGN USER TO LIBRARY
            currentLibrary.getLibraryUserDataBase().addUserToLibrary();


         boolean forceSwitchLibrary=false;
            //USER ACTION
            while(true) {
                if(forceSwitchLibrary){
                    break;
                }
                currentLibrary.getLibraryUserDataBase().getAllAvailableUserSwitch();
                String userSelection = scanner.nextLine();
                if (userSelection.equals("switch")) {
                    break;
                }
                if (currentLibrary.getLibraryUserDataBase().checkIfUserExist(userSelection)) {
                    switch (userSelection) {
                        case "Admin":
                            if (currentLibrary.getLibraryUserDataBase().validateAdminLogin()) {
                                forceSwitchLibrary =currentLibrary.adminFlow();
                            } else {
                                System.out.println("Wrong password.");
                            }
                            break;
                        default:
                            if (currentLibrary.getLibraryUserDataBase().validateUserLogin(userSelection)) {
                                currentLibrary.userFlow(userSelection);
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
    }
}
