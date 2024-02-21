package org.example.LibraryManager;


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


    @Override
    public int compareTo(Author o) {
        return lastName.compareTo(o.lastName);
    }
}
