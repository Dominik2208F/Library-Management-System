package org.example;



public class SetUp {

    private LibraryDataBase libraryDataBase;
    public SetUp(LibraryDataBase libraryDataBase){
        this.libraryDataBase=libraryDataBase;
    }


    public void createDefaultLibrariesSetUp(String name){
        UsersDataBase usersDataBase =new UsersDataBase();
        Library library = new Library(usersDataBase, name);
        libraryDataBase.addLibrary(library); // add each library to DataBase
        if(name.equals("Osiedlowa")) {
            library.addDefaultBooksToOsiedlowaLibrary();
            usersDataBase.createDefaultAdminUser();
        }
        else{
            library.addDefaultBooksToLibrary();
            usersDataBase.createDefaultAdminUser();
        }
    }
}
