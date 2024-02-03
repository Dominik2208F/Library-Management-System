package org.example;


import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;
import org.example.UserManager.UsersDataBase;

public class SetUp {

    private LibraryDataBase libraryDataBase;

    public SetUp(LibraryDataBase libraryDataBase) {
        this.libraryDataBase = libraryDataBase;
    }


    public void createDefaultLibrariesSetUp(String name) {
        UsersDataBase usersDataBase = new UsersDataBase(); // baza danych to instancji biblioteki
        Library library = new Library(usersDataBase, name); // nowa instancja biblioteki
        libraryDataBase.addLibrary(library); // add each library to DataBaseLibrary
        if (name.equals("Osiedlowa")) {
            library.addDefaultBooksToOsiedlowaLibrary();
            usersDataBase.createDefaultAdminUser();
        } else {
            library.addDefaultBooksToLibrary();
            usersDataBase.createDefaultAdminUser();
        }
    }
}
