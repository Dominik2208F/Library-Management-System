package org.example;


import Frames.LibraryManagementFrame;
import org.example.LibraryManager.LibraryDataBase;

public class Main {

    public static void main(String[] args)  {


        LibraryDataBase libraryDataBase = new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);
        SetUp.createDefaultLibrariesSetUp("Pruszkowska");
        SetUp.createDefaultLibrariesSetUp("Warszawska");

        new Flow(libraryDataBase);
        new LibraryManagementFrame(libraryDataBase);

    }
}
