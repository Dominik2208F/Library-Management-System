package org.example.LibraryManager;


import Frames.Status;
import org.example.UserManager.UsersDataBase;

import java.util.ArrayList;
import java.util.List;

public class Library {


    private final List<Book> listOfBooks = new ArrayList<>();
    private UsersDataBase libraryUserDataBase;
    private String NameOfLibrary;

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public Library(UsersDataBase usersDataBase, String nameOfLibrary) {
        this.libraryUserDataBase = usersDataBase;
        this.NameOfLibrary = nameOfLibrary;
    }

    public UsersDataBase getLibraryUserDataBase() {
        return libraryUserDataBase;
    }

    public String getNameOfLibrary() {
        return NameOfLibrary;
    }

}
