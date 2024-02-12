package org.example.LibraryManager;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Author implements Comparable<Author> {

    private String firstName;
    private String lastName;
    private String dateOfBirth;


    public Author(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int compareTo(Author o) {
        return lastName.compareTo(o.lastName);
    }
}
