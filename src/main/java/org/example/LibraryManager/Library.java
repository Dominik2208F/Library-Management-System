package org.example.LibraryManager;


import Frames.Status;
import org.example.UserManager.UsersDataBase;

import java.util.ArrayList;
import java.util.List;

public class Library {


    private final List<Book> listOfBooks = new ArrayList<>();
    private UsersDataBase libraryUserDataBase;
    private String NameOfLibrary;

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public Library(UsersDataBase usersDataBase, String nameOfLibrary) {
        this.libraryUserDataBase = usersDataBase;
        this.NameOfLibrary = nameOfLibrary;
    }

    public UsersDataBase getLibraryUserDataBase() {
        return libraryUserDataBase;
    }

    public String getNameOfLibrary() {
        return NameOfLibrary;
    }

    public void addDefaultBookswarszawskaToLibrary() {
        listOfBooks.add(new Book("Alicja w Krainie Mugoli",
                new Author("Leonidas", "Staff", "1960-12-12")
                , 1998, new Genre("Przygodowa"), 250, Status.AVAILABLE));

        listOfBooks.add(new Book("Misiek koralgol",
                new Author("Adam", "Wielkopolski", "1966-11-13")
                , 1998, new Genre("Akcji"), 154, Status.AVAILABLE));

        listOfBooks.add(new Book("Przygody Van Goka",
                new Author("Piotr", "Adamczyk", "1967-5-31")
                , 1998, new Genre("ScienceFiction"), 154, Status.AVAILABLE));

        listOfBooks.add(new Book("Krolewna z zamku",
                new Author("Beata", "Bodnar", "1978-01-04")
                , 1998, new Genre("Romans"), 154, Status.AVAILABLE));

        listOfBooks.add(new Book("Władca pierścieni: Drużyna Pierścienia",
                new Author("J.R.R.", "Tolkien", "1964-5-24")
                , 1954, new Genre("Akcji"), 455, Status.AVAILABLE));

        listOfBooks.add(new Book("Harry Potter i Kamień Filozoficzny",
                new Author("J.K.K", "Rowling", "1989-03-18")
                , 1997, new Genre("Akcji"), 320, Status.AVAILABLE));

        listOfBooks.add(new Book("Zbrodnia i Kara",
                new Author("Fiodor", "Dostojewski", "2001-04-03")
                , 1866, new Genre("Klasyczna Literatura Rosyjska"), 672, Status.AVAILABLE));

        listOfBooks.add(new Book("W pustyni i w puszczy",
                new Author("Henryk", "Sienkiewicz", "2002-06-05")
                , 1911, new Genre("Przygodowa"), 420, Status.AVAILABLE));

    }

    public void addDefaultBooksToPruszkowskaLibrary() {
        listOfBooks.add(new Book("Kot w butach",
                new Author("Wincent", "Staff", "2004-11-03")
                , 1943, new Genre("Akcji"), 12, Status.AVAILABLE));

        listOfBooks.add(new Book("Przygody Kundla",
                new Author("Angel", "Hrabio", "2006-12-04")
                , 1965, new Genre("Dramat"), 122, Status.AVAILABLE));

        listOfBooks.add(new Book("Historia Sfinksa",
                new Author("Angel", "Hrabio", "2002-04-25")
                , 1965, new Genre("Historyczne"), 122, Status.AVAILABLE));

        listOfBooks.add(new Book("Finansowy spokoj",
                new Author("Anna", "Danienko", "2004-02-14")
                , 1998, new Genre("Finansowe"), 154, Status.AVAILABLE));
        listOfBooks.add(new Book("Duchowy spokoj",

                new Author("Anna", "Danienko", "2007-03-25")
                , 1998, new Genre("Akcji"), 154, Status.AVAILABLE));

        listOfBooks.add(new Book("Relaks Akademicki",
                new Author("Ewa", "Cylian", "2008-03-25")
                , 1998, new Genre("Akademickie"), 154, Status.AVAILABLE));

    }
}
