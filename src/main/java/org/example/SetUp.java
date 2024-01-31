package org.example;

import java.util.Scanner;

public class SetUp {

    private LibraryDataBase libraryDataBase;
    public SetUp(LibraryDataBase libraryDataBase){
        this.libraryDataBase=libraryDataBase;
    }


    public void createDefaultLibrariesSetUp(String name){
        UsersDataBase usersDataBase =new UsersDataBase();
        Library library = new Library(usersDataBase, name);
        libraryDataBase.addLibrary(library);
        if(name.equals("Osiedlowa")) {
            library.addDefaultBooksToOsiedlowaLibrary();
            usersDataBase.createDefaultAdminUser();
        }
        else{
            library.addDefaultBooksToLibrary();
            usersDataBase.createDefaultAdminUser();
        }
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
