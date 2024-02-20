package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminQueries {

    static Connection connection;
    public AdminQueries(Connection connection) {
        this.connection=connection;
    }



    public static ResultSet getAllAssignedToUsers(String library){
        ResultSet resultSet=null;
        Statement statement;
        try{
            String query = String.format("SELECT DISTINCT users.user_id, users.username FROM public.book\n" +
                    "LEFT JOIN users on book.user_id=users.user_id\n" +
                    "LEFT JOIN library on library.library_id=book.library_id\n" +
                    "where users.user_id IS NOT NULL and library.library_name='%s'",library);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            System.out.println(e);
        }

        return resultSet;
    }
    public static List<String> getAllBookFilteredByAssignedTo(String libraryName, String userName,String direction) {
        List<String> allBooks = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;


        if (userName.equals("None")) {
            try {
                String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book \n" +
                        "                    LEFT JOIN author on book.author_id=author.author_id \n" +
                        "                    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                        "                    LEFT JOIN library on book.library_id= library.library_id\n" +
                        "                    LEFT JOIN users on book.user_id=users.user_id\n" +
                        "                    WHERE library.library_name='%s' and users.username is null and book.is_deleted = false ORDER BY title %s ", libraryName,direction);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String status = resultSet.getString("status");
                    String assignedTo = resultSet.getString("username");
                    if (assignedTo == null) { //zakrycie do kogo przypisane dla uzytkownika. Do zmiany dla Admina w bookInfo
                        assignedTo = "None";
                    }
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                    String yearOfProduction = resultSet.getString("yearofproduction");
                    String genre = resultSet.getString("name");

                    String bookInfo = "Status: " + status + "," + "Assigned to: " + assignedTo + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                    allBooks.add(bookInfo);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return allBooks;
        }
        else {
            try {
                String query = String.format("SELECT status, users.username,users.user_id, title, author.first_name, author.last_name,yearofproduction,genre.name  FROM public.book \n" +
                        "                    LEFT JOIN author on book.author_id=author.author_id \n" +
                        "                    LEFT JOIN genre on book.genre_id=genre.genre_id\n" +
                        "                    LEFT JOIN library on book.library_id= library.library_id\n" +
                        "                    LEFT JOIN users on book.user_id=users.user_id\n" +
                        "                    WHERE library.library_name='%s' and users.username='%s' and book.is_deleted = false ORDER BY title %s", libraryName, userName,direction);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String status = resultSet.getString("status");
                    String assignedTo = resultSet.getString("username");
                    if (assignedTo == null) { //zakrycie do kogo przypisane dla uzytkownika. Do zmiany dla Admina w bookInfo
                        assignedTo = "None";
                    }
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                    String yearOfProduction = resultSet.getString("yearofproduction");
                    String genre = resultSet.getString("name");

                    String bookInfo = "Status: " + status + "," + "Assigned to: " + assignedTo + "," + " Title: " + title + "," + " Author: " + author + "," + " Production date: " + yearOfProduction + "," + " Genre: " + genre;
                    allBooks.add(bookInfo);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return allBooks;
        }
    }

    public static void deleteBookByTitle(String title, String library){
        Statement statement;
        try{
            String query=String.format("UPDATE Book\n" +
                    "SET is_deleted = true\n" +
                    "WHERE title = '%s'\n" +
                    "AND library_id = (SELECT library_id FROM Library WHERE library.library_name = '%s') and book.is_deleted= false;",title,library);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");

        }catch (Exception e){
            System.out.println(e);
        }
    }



}
