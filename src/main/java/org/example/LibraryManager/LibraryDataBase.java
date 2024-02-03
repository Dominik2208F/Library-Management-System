package org.example.LibraryManager;

import org.example.LibraryManager.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryDataBase {

    List<Library> listOfLibrary;

    public LibraryDataBase() {
        listOfLibrary = new ArrayList<>();
    }

    public List<Library> getListOfLibrary() {
        return listOfLibrary;
    }

    public void setListOfLibrary(List<Library> listOfLibrary) {
        this.listOfLibrary = listOfLibrary;
    }

    public void addLibrary(Library library) {
        listOfLibrary.add(library);
        System.out.println("Library " + library.getNameOfLibrary() + " has been added");
    }

    public void removeLibrary() {

        if (!(listOfLibrary.size() == 0)) {
            Scanner scanner = new Scanner(System.in);
            boolean libraryFound = false;
            while (true) {
                System.out.println("Are you sure? Pass name of library to delete");
                String name = scanner.nextLine();
                for (Library library : listOfLibrary) {
                    if (library.getNameOfLibrary().equals(name)) {
                        listOfLibrary.remove(library);
                        libraryFound = true;
                        System.out.println("Library named " + name + " has been deleted");
                        break;
                    }
                }
                if (libraryFound) {
                    break;
                } else {
                    System.out.println("Library not found. Try once again");
                }
            }
        } else {
            System.out.println("Library list to delete is empty");
        }
    }
}
