package org.example;


import javax.sound.midi.Soundbank;

public class Main {

    public static void main(String[] args) {

        System.out.println("Library menagement system v1 by Dominik Jakubaszek");
        System.out.println("Loading a default libraries set up");

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
