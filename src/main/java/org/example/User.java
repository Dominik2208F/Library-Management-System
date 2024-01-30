package org.example;

import javax.xml.crypto.Data;
import java.time.LocalDate;

public class User {

    Library  library;
    Book book;
    public User(String name) {
        this.name = name;

    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void assignBookToUser(Book book){
        this.book=book;
    }
    public void showBook(){
        System.out.println(book.toString());
    }
}
