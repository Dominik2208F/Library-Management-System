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


    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
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

}
