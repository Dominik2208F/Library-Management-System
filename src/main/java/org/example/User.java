package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {

    Library  library;
    List<Book> books;
    public User(String name) {
        this.name = name;
        books= new ArrayList<>();
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
        books.add(book);
    }
    public void showBooks(){
        for(Book book : books){
            System.out.println(book.toString());
        }
    }

    public User addNewUserToDataBase(String name,UsersDataBase usersDataBase){

        User user= new User(name);
        usersDataBase.listOfUser.add(user);
        return user;
    }
}
