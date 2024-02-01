package org.example;


import javax.sound.midi.Soundbank;

public class Main {

    public static void main(String[] args) {
        System.out.println("Library menagemet system v1.Loading a default library...");
        LibraryDataBase libraryDataBase= new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);

        SetUp.createDefaultLibrariesSetUp("Osiedlowa");
        SetUp.createDefaultLibrariesSetUp("Wojewódzka");
        System.out.println("-----------------");
        Flow flow = new Flow(libraryDataBase);

        while (true) {
            flow.mainFlow();
        }
    }
}
