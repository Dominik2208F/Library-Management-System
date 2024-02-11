package org.example.LibraryManager;

import Frames.Status;
import org.example.UserManager.User;

public class Book implements Comparable<Book> {

    private String title;
    private Author author;
    private int dateOfProduction;
    private Genre genre;
    private int amountOfPage;

    public void setAssignedUserToBook(User assignedUserToBook) {
        AssignedUserToBook = assignedUserToBook;
    }

    private User AssignedUserToBook =new User("None","none"); //default user to book

    public User getAssignedUserToBook() {
        return AssignedUserToBook;
    }

    private Status status;

    public Book(String title, Author author, int yearOfProduction, Genre genre, int amountOfPage, Status status) {
        this.title = title;
        this.author = author;
        this.dateOfProduction = yearOfProduction;
        this.genre = genre;
        this.amountOfPage = amountOfPage;
        this.status= status;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getDateOfProduction() {
        return dateOfProduction;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getAmountOfPage() {
        return amountOfPage;
    }

    //user view
    @Override
    public String toString() {
        return  "Status:" +status +
                 ", Title: " + title +
                ", Author: " + author +
                ", Date of Production: " + dateOfProduction +
                ", Genre: " + genre +
                ", Amount of Pages: " + amountOfPage;
    }
// admin view
    public String toString(boolean flag) {
        if(flag) {
            return "Status: " + status + "Assigned: " + AssignedUserToBook.getName() +
                    ", Title: " + title +
                    ", Author: " + author +
                    ", Date of Production: " + dateOfProduction +
                    ", Genre: " + genre +
                    ", Amount of Pages: " + amountOfPage;
        }
        return null;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setDateOfProduction(int dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAmountOfPage(int amountOfPage) {
        this.amountOfPage = amountOfPage;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.getTitle());
    }
}
