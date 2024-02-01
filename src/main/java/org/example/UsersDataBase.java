package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersDataBase {

    List<User> listOfUser = new ArrayList<>();

    public UsersDataBase() {

    }

    public boolean checkIfUserExist(String name) {
        for (User user : listOfUser) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void getAllAvailableUserSwitch() {
        System.out.println("Type 'Name' of User to log into");
        System.out.println("Type 'Switch' to change library");
        int index=0;
        for (User users : listOfUser) {
            System.out.println("Index: " +index + " Name: " + users.getName());
            index++;
        }
    }
    public void getAllAvailableUser() {
        System.out.println("Available users");
        int index=0;
        for (User users : listOfUser) {
            System.out.println("Index: " + index + " Name: " + users.getName());
            index++;
        }
    }
    public void addUserToLibrary(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            getAllAvailableUser();
            System.out.println("Add new user? 'Y/N'");
            String addUser = scanner.nextLine();
            if (addUser.equals("Y")) {
                createNewUser();
                break;
            }
            else if(addUser.equals("N")){
                break;
            }
            else{
                System.out.println("Type 'Y' or 'N'");
            }
        }
    }
    public void addUserToDatabase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set name of user");
        String name = scanner.nextLine();
        System.out.println("Set password of user");
        String password = scanner.nextLine();
        User user = new User(name, password);
        listOfUser.add(user);
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

    public void createNewUser() {
        while (true) {

            addUserToDatabase();
            System.out.println("User added sucessfully");
            System.out.println("Available users");
            for (User users : listOfUser) {
                System.out.println("Name: " + users.getName());
            }
            System.out.println("Add next user? Type: Y/N");
            Scanner scanner = new Scanner(System.in);
            String decisionInner = scanner.nextLine();
            if (decisionInner.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    public void createDefaultAdminUser() {
        addAdminDatabase();
    }

    public boolean validateAdminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type password to Admin account");
        String password = scanner.nextLine();
        return returnObjectOfUserByName("Admin").getPassword().equalsIgnoreCase(password);
    }

    public boolean validateUserLogin(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type password to " + name + " account");
        String password = scanner.nextLine();
        return returnObjectOfUserByName(name).getPassword().equalsIgnoreCase(password);
    }
}
