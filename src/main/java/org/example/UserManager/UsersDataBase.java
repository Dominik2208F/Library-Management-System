package org.example.UserManager;

import java.util.ArrayList;
import java.util.List;


public class UsersDataBase {

    private List<User> listOfUser = new ArrayList<>();

    public UsersDataBase() {

    }
    public List<User> getListOfUser() {
        return listOfUser;
    }



    public void addAdminDatabase() {
        User user = new User("Admin", "admin");
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

    public void createDefaultUsers() {
        addAdminDatabase();
    }

}
