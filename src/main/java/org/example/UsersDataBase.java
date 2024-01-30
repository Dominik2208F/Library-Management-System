package org.example;

import java.util.ArrayList;
import java.util.List;

public class UsersDataBase {

    List<User> listOfUser= new ArrayList<>();
    public UsersDataBase() {

    }

    public void addUser(User user){
        listOfUser.add(user);
    }

    public User returnObjectOfUserByName(String name) {
        for (User user : listOfUser) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
}
