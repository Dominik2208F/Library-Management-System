package Manager;


import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;
import org.example.UserManager.UsersDataBase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SetUp {

    private LibraryDataBase libraryDataBase;

    public SetUp(LibraryDataBase libraryDataBase) {
        this.libraryDataBase = libraryDataBase;
    }


    public void createDefaultLibrariesSetUp(String name) {
        UsersDataBase usersDataBase = new UsersDataBase(); // baza danych to instancji biblioteki
        Library library = new Library(usersDataBase, name); // nowa instancja biblioteki
        libraryDataBase.addLibrary(library); // add each library to DataBaseLibrary
        if (name.equals("Pruszkowska")) {
            library.addDefaultBooksToPruszkowskaLibrary();
            usersDataBase.createDefaultUsers();
        } else {
            library.addDefaultBookswarszawskaToLibrary();
            usersDataBase.createDefaultUsers();
        }
    }

}
