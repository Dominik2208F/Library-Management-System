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

}
