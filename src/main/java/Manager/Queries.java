package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    static Connection connection;

    public Queries(Connection connection) {
        this.connection = connection;
    }


    public static ResultSet readAllLibraries() {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT library_name FROM public.library");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }

        return resultSet;
    }

    public static ResultSet readUsersAssignedToLibraryByName(String Name) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT username FROM public.users LEFT JOIN library on users.library_Id=library.library_id WHERE library.library_name='%s' ORDER BY username ASC", Name);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public static String getPasswordFromUserByName(String Name) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT password FROM public.users WHERE username='%s'", Name);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getIdOfLibraryByName(String name) {

        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT library_id FROM public.library where library_Name='%s'", name);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return resultSet.getString("library_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void updateDataBaseWithNewUser(String userName, String password, String idOfLibrary) {

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = today.format(formatter);

        Statement statement;
        try {
            String query = String.format("INSERT INTO public.users (username, library_id, dateofcreation, permissionlevel, password) VALUES ('%s', %s, '%s', 'User', '%s')", userName, idOfLibrary, formattedDate, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getPasswordOfUser(String username, String libraryName) {

        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT password FROM public.users LEFT JOIN library on users.library_id=library.library_id where library.library_name='%s' and users.username='%s'", libraryName, username);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static List<String> getCurrentStateOfBooks(String libraryName, String permission) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {

            String query = String.format("SELECT status,users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and book.is_deleted = false ORDER BY title", libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String status = resultSet.getString("status");
                String assignedTo = resultSet.getString("username");
                if (assignedTo == null) {
                    assignedTo = "None";
                }
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genre = resultSet.getString("name");
                String bookInfo;
                if (permission.equals("Admin")) {
                    bookInfo = "Status: " + status + "," + "Assigned to: " + assignedTo + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                } else {
                    bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                }
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allBooks;
    }

    public static List<String> getAllAvailableBook(String libraryName) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and  book.status='AVAILABLE' and book.user_id is NULL and book.is_deleted = false ORDER BY title", libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String assignedTo = resultSet.getString("username");
                if (assignedTo == null) {
                    assignedTo = "None";
                }
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genre = resultSet.getString("name");

                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allBooks;
    }

    public static List<String> getAllAvailableBookSorting(String libraryName, String sortingDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and  book.status='AVAILABLE' and book.user_id is NULL and book.is_deleted = false ORDER BY title %s", libraryName, sortingDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String assignedTo = resultSet.getString("username");
                if (assignedTo == null) {
                    assignedTo = "None";
                }
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genre = resultSet.getString("name");

                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allBooks;
    }

    public static boolean checkIfAnyBookIsAvailable(String libraryName) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and  book.status='AVAILABLE' and book.user_id is NULL and book.is_deleted = false", libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                if (status.equals("AVAILABLE")) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean checkIfAnyBookIsInStatusBorrowed(String libraryName, String userName) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and  book.status='BORROWED' and users.username='%s' and book.is_deleted = false", libraryName, userName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                if (status.equals("BORROWED")) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void updateBookStatusByTitle(String library, String status, String value, String assignedTo) {
        Statement statement;


        try {
            statement = connection.createStatement();
            String takeUserId = String.format("SELECT user_id FROM public.users LEFT JOIN library on library.library_id=users.library_id where username='%s' and library_name='%s'", assignedTo, library);
            ResultSet set = statement.executeQuery(takeUserId);
            String userID = null;
            while (set.next()) {
                userID = set.getString("user_id");
            }

            String query = String.format("UPDATE book\n" +
                    "SET status = '%s', user_id =" + userID + "\n" +
                    "WHERE title = '%s' AND library_id IN (SELECT library_id FROM library WHERE library_name = '%s') and book.is_deleted = false;", status, value, library);

            statement.executeUpdate(query);
            System.out.println("Data updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAllBooksBorrowed(String library, String assignedTo, String statusOld, String statusNew) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String takeUserId = String.format("SELECT user_id FROM public.users LEFT JOIN library on library.library_id=users.library_id where username='%s' and library_name='%s'", assignedTo, library);
            ResultSet set = statement.executeQuery(takeUserId);
            String userID = null;
            while (set.next()) {
                userID = set.getString("user_id");
            }


            String query = String.format("UPDATE book\n" +
                    "SET status = '%s', user_id =" + userID + "\n" +
                    "WHERE status='%s' AND library_id IN (SELECT library_id FROM library WHERE library_name = '%s' ) and book.is_deleted = false", statusNew, statusOld, library);
            statement.executeUpdate(query);
            System.out.println("All book set as borrowed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setAllBooksAvailable(String library, String assignTO, String statusOld, String statusNew, String currentAssigned) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String takeUserId = String.format("SELECT user_id FROM public.users LEFT JOIN library on library.library_id=users.library_id where username='%s' and library_name='%s'", currentAssigned, library);
            ResultSet set = statement.executeQuery(takeUserId);
            String userID = null;
            while (set.next()) {
                userID = set.getString("user_id");
            }


            String query = String.format(String.format("UPDATE book \n" +
                    " SET status = '%s', user_id = (SELECT user_id FROM users WHERE username = '%s') \n" +
                    " WHERE status='%s' AND library_id IN (SELECT library_id FROM library WHERE library_name = '%s') AND user_id=" + userID + " and book.is_deleted = false", statusNew, assignTO, statusOld, library, currentAssigned));

            statement.executeUpdate(query);
            System.out.println("Data updated: " + "Library " + library + " has been assigned to " + assignTO + " Old status " + statusOld + " New Status " + statusNew + " was assgined to" + currentAssigned);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> getAllBorrowedBooksByUser(String libraryName, String userName, String permission) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and  book.status='BORROWED' and users.username='%s' and book.is_deleted = false ORDER BY title", libraryName, userName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String assignedTo = resultSet.getString("username");
                if (assignedTo == null) {
                    assignedTo = "None";
                }
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genre = resultSet.getString("name");
                String bookInfo;
                if (permission.equals("Admin")) {
                    bookInfo = "Status: " + status + "," + "Assigned to: " + assignedTo + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                } else {
                    bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                }
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allBooks;
    }

    public static List<String> getAllAuthorsInLibrary(String libraryName) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT author.last_name, author.first_name,library.library_id FROM public.author\n" +
                    "LEFT JOIN book on book.author_id = author.author_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id where library.library_name='%s' and book.is_deleted = false ORDER BY last_name", libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String last_name = resultSet.getString("last_name");
                String first_name = resultSet.getString("first_name");
                String userInfo = last_name + " " + first_name;
                allBooks.add(userInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByGenre(String libraryName, String genre, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "    LEFT JOIN author on book.author_id=author.author_id\n" +
                    "    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "    LEFT JOIN library on book.library_id= library.library_id\n" +
                    "    LEFT JOIN users on book.user_id=users.user_id\n" +
                    "    WHERE library.library_name='%s' and  genre.name='%s' and book.is_deleted = false ORDER BY title  %s ", libraryName, genre, sortDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByGenreOnlyAvailable(String libraryName, String genre, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "    LEFT JOIN author on book.author_id=author.author_id\n" +
                    "    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "    LEFT JOIN library on book.library_id= library.library_id\n" +
                    "    LEFT JOIN users on book.user_id=users.user_id\n" +
                    "    WHERE library.library_name='%s' and  genre.name='%s' and book.status='AVAILABLE' and book.is_deleted = false ORDER BY title %s", libraryName, genre, sortDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByAuthorNameAndSurname(String libraryName, String name, String Surname, String sortinDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "    LEFT JOIN author on book.author_id=author.author_id\n" +
                    "    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "    LEFT JOIN library on book.library_id= library.library_id\n" +
                    "    LEFT JOIN users on book.user_id=users.user_id\n" +
                    "    WHERE library.library_name='%s' and  author.first_name='%s' and author.last_name='%s' and book.is_deleted = false ORDER BY title %s", libraryName, name, Surname, sortinDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByAuthorNameAndSurnameOnlyAvailable(String libraryName, String name, String Surname, String sortingDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "    LEFT JOIN author on book.author_id=author.author_id\n" +
                    "    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "    LEFT JOIN library on book.library_id= library.library_id\n" +
                    "    LEFT JOIN users on book.user_id=users.user_id\n" +
                    "    WHERE library.library_name='%s' and  author.first_name='%s' and author.last_name='%s' and status='AVAILABLE' and book.is_deleted = false ORDER BY title %s", libraryName, name, Surname, sortingDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByStatus(String libraryName, String statuss, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' and  status='%s' and book.is_deleted = false ORDER BY title %s", libraryName, statuss, sortDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByProductionDate(String libraryName, Integer min, Integer max, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' AND  yearofproduction BETWEEN '%s' AND '%s' and book.is_deleted = false ORDER BY yearofproduction %s", libraryName, min, max, sortDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> filterBookByPages(String libraryName, Integer min, Integer max, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username,pages, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "LEFT JOIN author on book.author_id=author.author_id\n" +
                    "LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "LEFT JOIN library on book.library_id= library.library_id\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "WHERE library.library_name='%s' AND  pages BETWEEN '%s' AND '%s' and book.is_deleted = false ORDER BY pages %s", libraryName, min, max, sortDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String pages = resultSet.getString("pages");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree + " Pages: " + pages;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> searchForBookByTitle(String libraryName, String text, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = " SELECT status, users.username, pages, title, author.first_name, author.last_name, yearofproduction, genre.name FROM public.book\n" +
                    "LEFT JOIN author ON book.author_id = author.author_id\n" +
                    "LEFT JOIN genre ON book.genre_id = genre.genre_id\n" +
                    "LEFT JOIN library ON book.library_id = library.library_id\n" +
                    "LEFT JOIN users ON book.user_id = users.user_id\n" +
                    "WHERE library.library_name = '" + libraryName + "'\n" +
                    "AND book.is_deleted = false\n" +
                    "AND (\n" +
                    "    LOWER(title) LIKE LOWER('%" + text + "%')\n" +
                    "    OR LOWER(author.first_name) LIKE LOWER('%" + text + "%')\n" +
                    "    OR LOWER(author.last_name) LIKE LOWER('%" + text + "%')\n" +
                    "    OR LOWER(genre.name) LIKE LOWER('%" + text + "%')\n" +
                    ")\n" +
                    "ORDER BY title  " + sortDirection;

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> getAllTransactionByUser(String libraryName, String username) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT DATE_TRUNC('second', dateoftransaction) AS dateoftransaction,direction,users.username,title,author.first_name,author.last_name,yearofproduction,genre.name  FROM public.borrowedbooks\n" +
                    "LEFT JOIN library ON borrowedbooks.library_id = library.library_id\n" +
                    "LEFT JOIN book ON borrowedbooks.book_id = book.book_id\n" +
                    "LEFT JOIN users ON borrowedbooks.user_id = users.user_id\n" +
                    "LEFT JOIN genre ON book.genre_id = genre.genre_id\n" +
                    "LEFT JOIN author ON book.author_id = author.author_id\n" +
                    "WHERE library.library_name='%s' and users.username='%s' ORDER BY dateoftransaction DESC", libraryName, username);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String direction = resultSet.getString("direction");
                String dateOFtransaction = resultSet.getString("dateoftransaction");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String bookInfo = "Time: " + dateOFtransaction + "," + "Direction: " + direction + "," + " Title: " + title + "," + " Author: " + author;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> getAllTransactionByUserAndDate(String libraryName, String username, String left, String right) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        if (right.equals("")) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter);
            right = formattedDate;
        }
        try {
            String query = String.format(" SELECT DATE_TRUNC('second', dateoftransaction) AS dateoftransaction,direction,users.username,title,author.first_name,author.last_name,yearofproduction,genre.name  FROM public.borrowedbooks\n" +
                    "LEFT JOIN library ON borrowedbooks.library_id = library.library_id\n" +
                    "LEFT JOIN book ON borrowedbooks.book_id = book.book_id\n" +
                    "LEFT JOIN users ON borrowedbooks.user_id = users.user_id\n" +
                    "LEFT JOIN genre ON book.genre_id = genre.genre_id\n" +
                    "LEFT JOIN author ON book.author_id = author.author_id\n" +
                    "WHERE library.library_name='%s' and users.username='%s'and DATE(dateoftransaction) BETWEEN '%s' AND '%s' ORDER BY dateoftransaction DESC", libraryName, username, left, right);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String direction = resultSet.getString("direction");
                String dateOFtransaction = resultSet.getString("dateoftransaction");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String bookInfo = "Time: " + dateOFtransaction + "," + "Direction: " + direction + "," + " Title: " + title + "," + " Author: " + author;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> getAllTransactionByUserStatus(String libraryName, String username, String status) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT DATE_TRUNC('second', dateoftransaction) AS dateoftransaction,direction,users.username,title,author.first_name,author.last_name,yearofproduction,genre.name  FROM public.borrowedbooks\n" +
                    "LEFT JOIN library ON borrowedbooks.library_id = library.library_id\n" +
                    "LEFT JOIN book ON borrowedbooks.book_id = book.book_id\n" +
                    "LEFT JOIN users ON borrowedbooks.user_id = users.user_id\n" +
                    "LEFT JOIN genre ON book.genre_id = genre.genre_id\n" +
                    "LEFT JOIN author ON book.author_id = author.author_id\n" +
                    "WHERE library.library_name='%s' and users.username='%s' and direction='%s' ORDER BY dateoftransaction DESC", libraryName, username, status);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String direction = resultSet.getString("direction");
                String dateOFtransaction = resultSet.getString("dateoftransaction");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String bookInfo = "Time: " + dateOFtransaction + "," + "Direction: " + direction + "," + " Title: " + title + "," + " Author: " + author;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static List<String> getAllTransactionByUserStatusANDDate(String libraryName, String username, String status, String from, String to) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        if (to.equals("")) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter);
            to = formattedDate;
        }
        try {
            String query = String.format(" SELECT DATE_TRUNC('second', dateoftransaction) AS dateoftransaction,direction,users.username,title,author.first_name,author.last_name,yearofproduction,genre.name  FROM public.borrowedbooks\n" +
                    "LEFT JOIN library ON borrowedbooks.library_id = library.library_id\n" +
                    "LEFT JOIN book ON borrowedbooks.book_id = book.book_id\n" +
                    "LEFT JOIN users ON borrowedbooks.user_id = users.user_id\n" +
                    "LEFT JOIN genre ON book.genre_id = genre.genre_id\n" +
                    "LEFT JOIN author ON book.author_id = author.author_id\n" +
                    "WHERE library.library_name='%s' and users.username='%s' and direction='%s' and DATE(dateoftransaction) BETWEEN '%s' AND '%s' ORDER BY dateoftransaction DESC", libraryName, username, status, from, to);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String direction = resultSet.getString("direction");
                String dateOFtransaction = resultSet.getString("dateoftransaction");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String bookInfo = "Time: " + dateOFtransaction + "," + "Direction: " + direction + "," + " Title: " + title + "," + " Author: " + author;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }

    public static void insertBorrowedBookToHistory(String user, String title, String library, String direction) {


        Statement statement;
        try {
            statement = connection.createStatement();
            String takeUserId = String.format("SELECT user_id FROM public.users LEFT JOIN library on library.library_id=users.library_id where username='%s' and library_name='%s'", user, library);
            ResultSet set = statement.executeQuery(takeUserId);
            String userID = null;
            while (set.next()) {
                userID = set.getString("user_id");
            }

            String query = String.format("INSERT INTO BorrowedBooks (user_id, book_id, dateoftransaction, library_id, direction)\n" +
                    "VALUES (\n" +
                    "    " + userID + ",\n" +
                    "    (SELECT book_id FROM Book WHERE title = '%s'),\n" +
                    "    now(),\n" +
                    "    (SELECT library_id FROM Library WHERE library_name = '%s'),'%s')", title, library, direction);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Book has been saved in history user: " + user + " title: " + title + " direction " + direction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getQucikViewInfo(String libraryName, String titlearg) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT status,users.username,users.user_id, title, author.first_name, author.last_name,author.date_of_birth,yearofproduction,genre.name,pages  FROM public.book\n" +
                    "                    LEFT JOIN author on book.author_id=author.author_id\n" +
                    "                    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "                    LEFT JOIN library on book.library_id= library.library_id\n" +
                    "                    LEFT JOIN users on book.user_id=users.user_id\n" +
                    "                    WHERE library.library_name='%s' and title='%s' and book.is_deleted = false", libraryName, titlearg);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public static ResultSet userInfo(String libraryName, String username) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format("SELECT user_id,username,dateofcreation,permissionlevel,library_name FROM public.users\n" +
                    "LEFT JOIN library ON library.library_id=users.library_id\n" +
                    "where username='%s'and library_name='%s'", username, libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public static String userPassword(String libraryName, String username) {
        ResultSet resultSet = null;
        Statement statement;
        String password = null;
        try {
            String query = String.format("SELECT password FROM public.users\n" +
                    "LEFT JOIN library on library.library_id=users.library_id\n" +
                    "where username='%s' and library_name='%s'", username, libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return password;
    }

    public static void updatePassword(String passwordNew, String library, String username) {
        Statement statement;
        try {
            String query = String.format("Update Users set password='%s'\n" +
                    "where username='%s' and library_id IN (SELECT library_id FROM library WHERE library_name = '%s')", passwordNew, username, library);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Password updated");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> sortBookByParameter(String libraryName, String parameter, String sortDirection) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            String query = String.format(" SELECT status, users.username, title,pages, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book\n" +
                    "                    LEFT JOIN author on book.author_id=author.author_id\n" +
                    "                    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                    "                    LEFT JOIN library on book.library_id= library.library_id\n" +
                    "                    LEFT JOIN users on book.user_id=users.user_id\n" +
                    "                    WHERE library.library_name='%s' ORDER BY %s %s", libraryName, parameter, sortDirection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                String title = resultSet.getString("title");
                String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String yearOfProduction = resultSet.getString("yearofproduction");
                String genree = resultSet.getString("name");
                String bookInfo = "Status: " + status + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genree;
                allBooks.add(bookInfo);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return allBooks;
    }
}
