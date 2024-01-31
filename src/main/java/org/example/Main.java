package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         LibraryDataBase libraryDataBase= new LibraryDataBase();
        Library library=null; // z pętli
        //DEFAULT LIBRARY
        UsersDataBase usersDataBaseOsiedlowa = new UsersDataBase();
        Library libraryOsiedlowa = new Library(usersDataBaseOsiedlowa, "Osiedlowa");
        libraryDataBase.addLibrary(libraryOsiedlowa);
        libraryOsiedlowa.addDefaultBooksToOsiedlowaLibrary();
        usersDataBaseOsiedlowa.createDefaultAdminUser();

        UsersDataBase usersDataBaseHudini= new UsersDataBase();
        Library libraryHudini = new Library(usersDataBaseHudini, "Hudini");
        libraryDataBase.addLibrary(libraryHudini);
        libraryHudini.addDefaultBooksToLibrary();
        usersDataBaseHudini.createDefaultAdminUser();



        while (true) {
            System.out.println("Select a library to visit");
            for(Library librarySpec : libraryDataBase.listOfLibrary) {
                System.out.println(librarySpec.getNameOfLibrary());
                }
            String librarydecision = scanner.nextLine();
            //LIBRARY SELECTION
                    for(Library librarySpec : libraryDataBase.listOfLibrary) {
                        if (librarySpec.getNameOfLibrary().contains(librarydecision)) {
                            library = librarySpec;
                            break;
                        }
                    }
            while(true) {
                library.getLibraryUserDataBase().getAllAvailableUser();
                System.out.println("Add new user? 'Y/N'");
                String addUser = scanner.nextLine();
                if (addUser.equals("Y")) {
                    library.getLibraryUserDataBase().createNewUser();
                    break;
                }
                else{
                    break;
                }
            }
            //USER ACTION
            while(true) {
                library.getLibraryUserDataBase().getAllAvailableUserSwitch();
                String userSelection = scanner.nextLine();
                if (userSelection.equals("switch")) {
                    break;
                }
                if (library.getLibraryUserDataBase().checkIfUserExist(userSelection)) {
                    switch (userSelection) {
                        case "Admin":
                            if (library.getLibraryUserDataBase().validateAdminLogin()) {
                                library.adminFlow();
                            } else {
                                System.out.println("Wrong password.");
                            }
                            break;
                        default:
                            if (library.getLibraryUserDataBase().validateUserLogin(userSelection)) {
                                library.userFlow(userSelection);
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
