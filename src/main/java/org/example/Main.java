package org.example;


import org.example.LibraryManager.LibraryDataBase;

public class Main {

    public static void main(String[] args)  {


        LibraryDataBase libraryDataBase = new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);
        SetUp.createDefaultLibrariesSetUp("Pruszkowska");
        SetUp.createDefaultLibrariesSetUp("Warszawska");

        Flow flow = new Flow(libraryDataBase);

        while (true) {
            flow.mainFlow();
        }
    }
}
