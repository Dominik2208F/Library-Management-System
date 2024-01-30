package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {

    Library  library;
    List<Book> books;
    private String name;
    private String password;
    public User(String name) {
        this.name = name;
        books= new ArrayList<>();
    }
    public User(String name,String password) {
        this.name = name;
        this.password=password;
        books= new ArrayList<>();
    }
    public String getPassword() {
        return password;
    }
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
        books.add(book);
    }
    public void showBooks(){
        if(!books.isEmpty()) {
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
        else{
            System.out.println("User does not havy any books");
        }
    }

}
