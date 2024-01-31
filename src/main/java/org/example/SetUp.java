package org.example;

import java.util.Scanner;

public class SetUp {

    private LibraryDataBase libraryDataBase;
    public SetUp(LibraryDataBase libraryDataBase){
        this.libraryDataBase=libraryDataBase;
    }


    public void createDefaultSetUpOsiedlowa(){

        UsersDataBase usersDataBaseOsiedlowa = new UsersDataBase();
        Library libraryOsiedlowa = new Library(usersDataBaseOsiedlowa, "Osiedlowa");
        libraryDataBase.addLibrary(libraryOsiedlowa);
        libraryOsiedlowa.addDefaultBooksToOsiedlowaLibrary();
        usersDataBaseOsiedlowa.createDefaultAdminUser();
    }
    public void createDefaultSetUpHudini(){
        UsersDataBase usersDataBaseHudini= new UsersDataBase();
        Library libraryHudini = new Library(usersDataBaseHudini, "Hudini");
        libraryDataBase.addLibrary(libraryHudini);
        libraryHudini.addDefaultBooksToLibrary();
        usersDataBaseHudini.createDefaultAdminUser();
    }

    public Library selectLibraryInstanceToVisit(){
        System.out.println("Select a library to visit");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            for (Library librarySpec : libraryDataBase.listOfLibrary) {
                System.out.println(librarySpec.getNameOfLibrary());
            }
            String librarydecision = scanner.nextLine();
            //LIBRARY SELECTION
            for (Library librarySpec : libraryDataBase.listOfLibrary) {
                if (librarySpec.getNameOfLibrary().contains(librarydecision)) {
                    return librarySpec;

                }
            }
            System.out.println("Wrong library name. Try once again");
        }
    }
}
