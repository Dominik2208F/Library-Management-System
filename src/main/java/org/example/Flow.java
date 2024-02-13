package org.example;

import Frames.LibraryManagementFrame;
import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;
import org.example.UserManager.User;

import java.util.Scanner;

public class Flow {

    Library flowLibrary;
    LibraryDataBase libraryDataBase;

    LibraryManagementFrame libraryManagementFrame;
    public Flow(LibraryDataBase libraryDataBase) {
        this.libraryDataBase=libraryDataBase;
    }

    public void mainFlow(){

        libraryManagementFrame =new LibraryManagementFrame(libraryDataBase);

    }
}
