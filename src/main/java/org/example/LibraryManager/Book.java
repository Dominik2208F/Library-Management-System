package org.example.LibraryManager;

public class Book implements Comparable<Book> {

    private String title;
    private Author author;
    private String dateOfProduction;
    private Genre genre;
    private int amountOfPage;

    public Book(String title, Author author, String yearOfProduction, Genre genre, int amountOfPage) {
        this.title = title;
        this.author = author;
        this.dateOfProduction = yearOfProduction;
        this.genre = genre;
        this.amountOfPage = amountOfPage;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getDateOfProduction() {
        return dateOfProduction;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getAmountOfPage() {
        return amountOfPage;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                ", Author: " + author +
                ", Date of Production: " + dateOfProduction +
                ", Genre: " + genre +
                ", Amount of Pages: " + amountOfPage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setDateOfProduction(String dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAmountOfPage(int amountOfPage) {
        this.amountOfPage = amountOfPage;
    }

    @Override
    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.getTitle());
    }
}
