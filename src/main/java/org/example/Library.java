package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    private final List<Book> listOfBooks = new ArrayList<>();
    private UsersDataBase libraryUserDataBase;
    private String NameOfLibrary;

    public Library(UsersDataBase usersDataBase,String nameOfLibrary){
        this.libraryUserDataBase=usersDataBase;
        this.NameOfLibrary=nameOfLibrary;
    }
    public UsersDataBase getLibraryUserDataBase() {
        return libraryUserDataBase;
    }
    public String getNameOfLibrary() {
        return NameOfLibrary;
    }
    public void addBookToLibrary() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj tytuł książki:");
        String title = scanner.nextLine();
        System.out.println("Podaj imię autora:");
        String authorName = scanner.nextLine();
        System.out.println("Podaj nazwisko autora:");
        String authorSurname = scanner.nextLine();
        System.out.println("Podaj date urodzenia autora:");
        String dateOfBirthday = scanner.nextLine();
        System.out.println("Podaj gatunek książki:");
        String genreName = scanner.next();
        System.out.println("Podaj ilość stron:");
        int amountOfPage = scanner.nextInt();

        Author author = new Author(authorName, authorSurname, dateOfBirthday);
        Genre genre = new Genre(genreName);
        Book book = new Book(title, author, dateOfBirthday, genre, amountOfPage);

        listOfBooks.add(book);
        System.out.println("Book with title " + book.getTitle() + " has been added");
        returnSizeOfLibrary();
    }
    public void addDefaultBooksToLibrary(){
        listOfBooks.add(new Book("Alicja w Krainie czarów",
                new Author("Leonidas","Staff","12-12-1960")
                ,"1998",new Genre("Przygodowa"),250));

        listOfBooks.add(new Book("Miś koralgol",
                new Author("Mirosław","Wielkopolski","11-12-1960")
                ,"1998",new Genre("Przygodowa"),154));
    }
    public void addDefaultBooksToOsiedlowaLibrary(){
        listOfBooks.add(new Book("Kot w butach",
                new Author("Wincent","Staff","12-12-1960")
                ,"1943",new Genre("Akcji"),12));

        listOfBooks.add(new Book("Mała Wróżka",
                new Author("Angel","Hrabio","11-12-1960")
                ,"1965",new Genre("Dramat"),122));
    }
    public void returnAllBooksInfo() {
        int index = 0;
        if (listOfBooks.isEmpty()) {
            System.out.println(getNameOfLibrary() +" Library is empty.Add some book");
        }
        for (Book books : listOfBooks) {
            System.out.println("Available books in:" + getNameOfLibrary());
            System.out.println("Index " + index + " " + books.toString());
            index++;
        }
    }
    public void returnSizeOfLibrary() {
        System.out.println("Size of the" + getNameOfLibrary()+ "library is " + listOfBooks.size());
    }
    public void returnSpecificBookInfoFromLibrary() {
        if (!listOfBooks.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type info you want to access:\n" +
                    "title\n" +
                    "author\n" +
                    "date of production\n" +
                    "genre\n" +
                    "Amount of Page");

            String input = scanner.nextLine();
            System.out.println("Type index of books you want to access");

            int indexOfBookInList = scanner.nextInt();
            if (listOfBooks.size() < indexOfBookInList + 1) {
                System.out.println("No book with that index");
            } else {
                switch (input) {
                    case "title":
                        System.out.println(listOfBooks.get(indexOfBookInList).getTitle());
                        break;
                    case "author":
                        System.out.println("Author: " + listOfBooks.get(indexOfBookInList).getAuthor().toString());
                        break;
                    case "date of production":
                        System.out.println("Date of Production: " + listOfBooks.get(indexOfBookInList).getDateOfProduction());
                        break;
                    case "genre":
                        System.out.println("Genre: " + listOfBooks.get(indexOfBookInList).getGenre().toString());
                        break;
                    case "Amount of Page":
                        System.out.println("Amount of Pages: " + listOfBooks.get(indexOfBookInList).getAmountOfPage());
                        break;
                    default:
                        System.out.println("Invalid field name");

                }
            }
        } else {
            System.out.println(getNameOfLibrary() +" Library is empty, add some book");
        }
    }
    public void deleteBooksByTitleFromLibrary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type title of book to remove");
        String input = scanner.nextLine();
        for (int i = 0; i < listOfBooks.size(); i++) {
            if (listOfBooks.get(i).getTitle().equals(input)) {
                listOfBooks.remove(i);
            }
        }
        System.out.println("Books has been deleted sucessfully from " +getNameOfLibrary() + " library");
    }
    public void deleteBookFromLibrary() {
        if (!listOfBooks.isEmpty()) {
            System.out.println("Available books in:" + getNameOfLibrary());
            for (Book books : listOfBooks) {
                System.out.println(books.toString());
            }
            deleteBooksByTitleFromLibrary();

        } else {
            System.out.println(getNameOfLibrary()+ " Library is empty. You cannot delete book");
        }
    }
    public void updateBookInfoInTheLibrary() {
        if (!listOfBooks.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            int index = 0;
            System.out.println("Type index of book to update");
            while (true) {
                index = scanner.nextInt();
                if (index + 1 > listOfBooks.size()) {
                    System.out.println("Wrong index. Max index in library is " + listOfBooks.size());
                } else {
                    break;
                }
            }
            scanner.nextLine();
            System.out.println("Type info you want to access:\n" +
                    "title\n" +
                    "author\n" +
                    "date of production\n" +
                    "genre\n" +
                    "Amount of Page");
            String input = scanner.nextLine();
            switch (input) {
                case "title":
                    System.out.println("Type new title: ");
                    String title = scanner.nextLine();
                    listOfBooks.get(index).setTitle(title);
                    break;
                case "author":
                    System.out.println("Type new First Name:");
                    String name = scanner.nextLine();
                    System.out.println("Type new Second Name:");
                    String secondname = scanner.nextLine();
                    listOfBooks.get(index).getAuthor().setFirstName(name);
                    listOfBooks.get(index).getAuthor().setLastName(secondname);
                    break;
                case "date of production":
                    System.out.println("Set date of production");
                    String datOfProduction = scanner.nextLine();
                    listOfBooks.get(index).setDateOfProduction(datOfProduction);
                    break;
                case "genre":
                    System.out.println("Genre: " + listOfBooks.get(index).getGenre().toString());
                    break;
                case "Amount of Page":
                    System.out.println("Type new amount:");
                    int amount = scanner.nextInt();
                    listOfBooks.get(index).setAmountOfPage(amount);
                    break;
                default:
                    System.out.println("Invalid field name");

            }
            System.out.println("Books has been updated sucessfully");
        } else {
            System.out.println("Library is empty. Add some books");
        }
    }
    public void rentABookByTitle(User user) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        if(listOfBooks.isEmpty()){
            System.out.println("List of books is empty");
        }else{
            for (Book books : listOfBooks) {
                System.out.println("Available books in: " +getNameOfLibrary());
                System.out.println("Index " + index + " " + books.toString());
                index++;
            }
            System.out.println("Pass title");
            String title = scanner.nextLine();
            boolean booksFound=false;
            for(Book book: listOfBooks){
                if(book.getTitle().equals(title)) {
                    user.assignBookToUser(book);
                    System.out.println("Book rented " + book.getTitle());
                    listOfBooks.remove(book);
                    booksFound=true;
                    break;
                }
            }
            if(!booksFound){
                System.out.println("No books on the list with that title");
            }
        }
    }
    public void returnBookByTitle(User user) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        if(user.books.isEmpty()){
            System.out.println("You have no books to return");
        }else{
            for (Book books : user.books) {
                System.out.println("Available books to return:");
                System.out.println("Index " + index + " " + books.toString());
                index++;
            }
            System.out.println("Pass title");
            String title = scanner.nextLine();
            boolean bookFound=false;
            for(Book book: user.books){
                if(book.getTitle().equals(title)) {
                    user.UnassignBookFromUser(book);
                    System.out.println("Book returned " + book.getTitle());
                    listOfBooks.add(book);
                    bookFound=true;
                    break;
                }
            }
            if (!bookFound) {
                System.out.println("No books on the list with that title");
            }
        }
    }

}
