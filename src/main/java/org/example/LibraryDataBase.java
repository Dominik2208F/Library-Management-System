package org.example;

import java.util.ArrayList;
import java.util.List;

public class LibraryDataBase {

    List<Library> listOfLibrary;
    public LibraryDataBase(){
        listOfLibrary= new ArrayList<>();
    }

    public List<Library> getListOfLibrary() {
        return listOfLibrary;
    }
    public void setListOfLibrary(List<Library> listOfLibrary) {
        this.listOfLibrary = listOfLibrary;
    }
    public void addLibrary(Library library){
        listOfLibrary.add(library);
        System.out.println("Library " +library.getNameOfLibrary() + " has been added");
    }
}
