package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersDataBase {

    List<User> listOfUser= new ArrayList<>();
    public UsersDataBase() {

    }

    public void getAllAvailableUser(){
        System.out.println("Select user to log in");
        for(User users : listOfUser){
            System.out.println("Name: "+ users.getName());
        }
    }
    public void addUserToDatabase(Library library){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass name of user");
        String name = scanner.nextLine();
        User user = new User(name);
        user.setLibrary(library);
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

    public void createNewUser(Library library){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            addUserToDatabase(library);
            System.out.println("User added sucessfully");
            System.out.println("Users availabe " +listOfUser.size());
            System.out.println("Add next user? Type: Y/N");
            String decisionInner = scanner.nextLine();
            if(decisionInner.equalsIgnoreCase("N")){
                break;
            }
        }
    }
}
