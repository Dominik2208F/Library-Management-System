package org.example.UserManager;

import org.example.LibraryManager.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private List<Book> Userbooks;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        Userbooks = new ArrayList<>();
    }

    public List<Book> getUserbooks() {
        return Userbooks;
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

    public void assignBookToUser(Book book) {
        Userbooks.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public void UnassignBookFromUser(Book book) {
        Userbooks.remove(book);
    }

    public void showBooks() {
        if (!Userbooks.isEmpty()) {
            for (Book book : Userbooks) {
                System.out.println(book.toString());
            }
        } else {
            System.out.println("User does not havy any books borrowed");
        }
    }

}
