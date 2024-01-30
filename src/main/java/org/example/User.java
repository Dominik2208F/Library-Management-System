package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {

    List<Book> books;
    private String name;
    private String password;
    private static int index = 0;
    private int currentIndex;
    public User(String name,String password) {
        this.name = name;
        this.password=password;
        this.currentIndex=++index;
        books= new ArrayList<>();
    }
    public String getPassword() {
        return password;
    }
    public int getCurrentIndex() {
        return currentIndex;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void assignBookToUser(Book book){
        books.add(book);
    }
    public void UnassignBookFromUser(Book book){
        books.remove(book);
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
