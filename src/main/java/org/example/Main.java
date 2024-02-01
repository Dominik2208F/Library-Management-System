package org.example;


public class Main {

    public static void main(String[] args) {

        LibraryDataBase libraryDataBase= new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);

        SetUp.createDefaultLibrariesSetUp("Osiedlowa");
        SetUp.createDefaultLibrariesSetUp("Wojewódzka");

        Flow flow = new Flow(libraryDataBase);

        while (true) {
            flow.mainFlow();
        }
    }
}
