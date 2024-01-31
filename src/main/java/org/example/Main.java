package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LibraryDataBase libraryDataBase= new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);

        SetUp.createDefaultLibrariesSetUp("Osiedlowa");
        SetUp.createDefaultLibrariesSetUp("Hudini");

        Flow flow = new Flow(SetUp);

        while (true) {
            flow.mainFlow();
        }
    }
}
