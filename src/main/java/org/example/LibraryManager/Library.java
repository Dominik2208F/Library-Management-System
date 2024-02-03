package org.example.LibraryManager;


import org.example.UserManager.User;
import org.example.UserManager.UsersDataBase;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Library {

    private final List<Book> listOfBooks = new ArrayList<>();
    private UsersDataBase libraryUserDataBase;
    private String NameOfLibrary;

    public Library(UsersDataBase usersDataBase, String nameOfLibrary) {
        this.libraryUserDataBase = usersDataBase;
        this.NameOfLibrary = nameOfLibrary;
    }

    public UsersDataBase getLibraryUserDataBase() {
        return libraryUserDataBase;
    }

    public void getInfoAboutBorrowedBooksAmongsUser() {

        if (!(libraryUserDataBase.getListOfUser().size() == 1)) {
            for (User user : libraryUserDataBase.getListOfUser()) {
                if (user.getName().equals("Admin")) {
                    continue;
                }
                System.out.println("User " + user.getName() + " has borrowed a book " + user.getUserbooks().toString());
            }
        } else {
            System.out.println("Add some user to library");
        }
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
        int amountOfPage;

        while (true) {
            try {
                amountOfPage = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number");
                scanner.nextLine();
            }
        }
        Author author = new Author(authorName, authorSurname, dateOfBirthday);
        Genre genre = new Genre(genreName);
        Book book = new Book(title, author, dateOfBirthday, genre, amountOfPage);

        listOfBooks.add(book);
        System.out.println("Book with title " + book.getTitle() + " has been added");
        returnSizeOfLibrary();
    }

    public void addDefaultBooksToLibrary() {
        listOfBooks.add(new Book("Alicja w Krainie Mugoli",
                new Author("Leonidas", "Staff", "12-12-1960")
                , "1998", new Genre("Przygodowa"), 250));

        listOfBooks.add(new Book("Misiek koralgol",
                new Author("Adam", "Wielkopolski", "11-12-1960")
                , "1998", new Genre("Przygodowa"), 154));
    }

    public void addDefaultBooksToOsiedlowaLibrary() {
        listOfBooks.add(new Book("Kot w butach",
                new Author("Wincent", "Staff", "12-12-1960")
                , "1943", new Genre("Akcji"), 12));

        listOfBooks.add(new Book("Przygody Kundla",
                new Author("Angel", "Hrabio", "11-12-1960")
                , "1965", new Genre("Dramat"), 122));
    }

    public void returnAllBooksInfo() {
        int index = 0;
        if (listOfBooks.isEmpty()) {
            System.out.println(getNameOfLibrary() + " Library is empty.Add some book");
        } else {
            System.out.println("Available books in:" + getNameOfLibrary());
            for (Book books : listOfBooks) {
                System.out.println("Index " + index + " " + books.toString());
                index++;
            }
        }
    }

    public void returnSizeOfLibrary() {
        System.out.println("Size of the " + getNameOfLibrary() + " library is " + listOfBooks.size());
    }

    public void returnSpecificBookInfoFromLibrary() {
        if (!listOfBooks.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type index of books you want to access");

            int index = 0;
            System.out.println("Available books in:" + getNameOfLibrary());
            for (Book books : listOfBooks) {
                System.out.println("Index " + index + " " + books.getTitle());
                index++;
            }
            int indexOfBookInList;
            while (true) {
                try {
                    indexOfBookInList = scanner.nextInt();
                    if (listOfBooks.size() < indexOfBookInList + 1) {
                        System.out.println("No book with that index.Try once again");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number");
                    // Clear the buffer
                    scanner.nextLine();
                }
            }

            scanner.nextLine();
            while (true) {
                boolean operationDone = true;
                System.out.println("Type info you want to access:\n" +
                        "Title\n" +
                        "Author\n" +
                        "Production date\n" +
                        "Genre\n" +
                        "Pages");
                String input = scanner.nextLine();
                switch (input) {
                    case "Title":
                        System.out.println(listOfBooks.get(indexOfBookInList).getTitle());
                        break;
                    case "Author":
                        System.out.println("Author: " + listOfBooks.get(indexOfBookInList).getAuthor().toString());
                        break;
                    case "Production date":
                        System.out.println("Date of Production: " + listOfBooks.get(indexOfBookInList).getDateOfProduction());
                        break;
                    case "Genre":
                        System.out.println("Genre: " + listOfBooks.get(indexOfBookInList).getGenre().toString());
                        break;
                    case "Pages":
                        System.out.println("Amount of Pages: " + listOfBooks.get(indexOfBookInList).getAmountOfPage());
                        break;
                    default:
                        operationDone = false;
                }
                if (operationDone) {
                    break;
                } else {
                    System.out.println("Invalid field name.Try once again");
                }
            }

        } else {
            System.out.println(getNameOfLibrary() + " Library is empty, add some book");
        }
    }

    public void deleteBooksByTitleFromLibrary() {
        Scanner scanner = new Scanner(System.in);
        boolean bookFound = false;
        while (true) {
            System.out.println("Type title of book to remove");
            String input = scanner.nextLine();
            for (int i = 0; i < listOfBooks.size(); i++) {
                if (listOfBooks.get(i).getTitle().equals(input)) {
                    listOfBooks.remove(i);
                    System.out.println("Books has been deleted sucessfully from " + getNameOfLibrary() + " library");
                    bookFound = true;
                    break;
                }
            }
            if (bookFound) {
                break;
            } else {
                System.out.println("Wrong book title. Try once again");
            }
        }
    }

    public void deleteBookFromLibrary() {
        if (!listOfBooks.isEmpty()) {
            System.out.println("Available books in:" + getNameOfLibrary());
            for (Book books : listOfBooks) {
                System.out.println(books.toString());
            }
            deleteBooksByTitleFromLibrary();

        } else {
            System.out.println(getNameOfLibrary() + " Library is empty. You cannot delete book");
        }
    }

    public void updateBookInfoInTheLibrary() {
        if (!listOfBooks.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            int counter = 0;
            System.out.println("Type index of book to update");
            for (Book books : listOfBooks) {
                System.out.println("Index " + counter + " " + books.toString());
                counter++;
            }

            int index = 0;
            while (true) {
                try {
                    index = scanner.nextInt();
                    if (index + 1 > listOfBooks.size()) {
                        System.out.println("Wrong index.Try once again");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
            while (true) {
                boolean operationDone = true;
                System.out.println("Type info you want to access:\n" +
                        "Title\n" +
                        "Author\n" +
                        "Production date\n" +
                        "Genre\n" +
                        "Pages");
                String input = scanner.nextLine();
                switch (input) {
                    case "Title":
                        System.out.println("Type new title: ");
                        String title = scanner.nextLine();
                        listOfBooks.get(index).setTitle(title);
                        break;
                    case "Author":
                        System.out.println("Type new First Name:");
                        String name = scanner.nextLine();
                        System.out.println("Type new Second Name:");
                        String secondname = scanner.nextLine();
                        listOfBooks.get(index).getAuthor().setFirstName(name);
                        listOfBooks.get(index).getAuthor().setLastName(secondname);
                        break;
                    case "Production date":
                        System.out.println("Set date of production");
                        String datOfProduction = scanner.nextLine();
                        listOfBooks.get(index).setDateOfProduction(datOfProduction);
                        break;
                    case "Genre":
                        System.out.println("Genre: " + listOfBooks.get(index).getGenre().toString());
                        break;
                    case "Pages":
                        while (true) {
                            System.out.println("Type new amount:");
                            try {
                                int amount = scanner.nextInt();
                                listOfBooks.get(index).setAmountOfPage(amount);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number");
                                scanner.nextLine();
                            }
                        }
                        break;
                    default:
                        operationDone = false;

                }
                if (operationDone) {
                    System.out.println("Books has been updated sucessfully");
                    break;
                } else {
                    System.out.println("Invalid field name.Try once again");
                }
            }
        } else {
            System.out.println("Library is empty. Add some books");
        }
    }

    public void rentABookByTitle(User user) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        boolean booksFound = false;
        if (listOfBooks.isEmpty()) {
            System.out.println("List of books is empty");
        } else {
            System.out.println("Available books in: " + getNameOfLibrary());
            for (Book books : listOfBooks) {
                System.out.println("Index " + index + " " + books.toString());
                index++;
            }

            while (true) {
                System.out.println("Pass title");
                String title = scanner.nextLine();
                for (Book book : listOfBooks) {
                    if (book.getTitle().equals(title)) {
                        user.assignBookToUser(book);
                        System.out.println("Book " + book.getTitle() + " has been borrowed");
                        listOfBooks.remove(book);
                        booksFound = true;
                        break;
                    }
                }
                if (booksFound) {
                    break;
                } else {
                    System.out.println("Wrong book title.Please try once again");
                }
            }
        }
    }

    public void returnBookByTitle(User user) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        boolean bookFound = false;
        if (user.getUserbooks().isEmpty()) {
            System.out.println("You have no books to return");
        } else {
            for (Book books : user.getUserbooks()) {
                System.out.println("Available books to return:");
                System.out.println("Index " + index + " " + books.toString());
                index++;
            }

            while (true) {
                System.out.println("Pass title of book to return");
                String title = scanner.nextLine();
                for (Book book : user.getUserbooks()) {
                    if (book.getTitle().equals(title)) {
                        user.UnassignBookFromUser(book);
                        System.out.println("Book returned " + book.getTitle());
                        listOfBooks.add(book);
                        bookFound = true;
                        break;
                    }
                }
                if (bookFound) {
                    break;
                } else {
                    System.out.println("Wrong book title.Please try once again");
                }
            }
        }
    }

}
