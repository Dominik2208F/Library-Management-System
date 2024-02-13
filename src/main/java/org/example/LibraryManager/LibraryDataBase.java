package org.example.LibraryManager;


import java.util.ArrayList;
import java.util.List;

public class LibraryDataBase {

    private List<Library> listOfLibrary;
    public LibraryDataBase() {
        listOfLibrary = new ArrayList<>();
    }
    public List<Library> getListOfLibrary() {
        return listOfLibrary;
    }
    public void addLibrary(Library library) {
        listOfLibrary.add(library);
        System.out.println("Library " + library.getNameOfLibrary() + " has been added");
    }
}
