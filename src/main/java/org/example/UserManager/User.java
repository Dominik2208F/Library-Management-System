package org.example.UserManager;

import java.util.Date;
import java.util.Objects;

public class User {

    private String name;
    private String password;
    private Date dateOfCreation;
    private String permissionLevel;

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", creation date=" + dateOfCreation + '\''+
                ", permission level=" +permissionLevel +
                '}';
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        dateOfCreation=new Date();
        permissionLevel="User";
    }

    public User(String name, String password,String permissionLevel) {
        this.name = name;
        this.password = password;
        dateOfCreation=new Date();
        this.permissionLevel=permissionLevel;
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
