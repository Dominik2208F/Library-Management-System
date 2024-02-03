package org.example;


import org.example.LibraryManager.LibraryDataBase;

public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System v1 by Dominik Jakubaszek\n");
        System.out.println("Loading a default libraries set up.....Pass");

        LibraryDataBase libraryDataBase = new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);
        SetUp.createDefaultLibrariesSetUp("Pruszkowska");
        System.out.println("Loading.....Pass");
        SetUp.createDefaultLibrariesSetUp("Warszawska");
        System.out.println("Loading.....Pass\n");
        Flow flow = new Flow(libraryDataBase);

        while (true) {
            flow.mainFlow();
        }
    }
}
