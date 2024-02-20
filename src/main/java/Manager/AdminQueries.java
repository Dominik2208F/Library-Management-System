package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
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

    public static String searchAuthorByDetails(String authorFirstName,String lastName){

        Statement statement;
        String AuthorId = null;
        try {
            statement = connection.createStatement();
            String readAuthorId = String.format("SELECT author_id FROM public.author\n" +
                    "where first_name='%s' and last_name='%s'", authorFirstName, lastName);

            ResultSet resultSet = statement.executeQuery(readAuthorId);
            while (resultSet.next()) {
                 AuthorId = resultSet.getString("author_id");
            }
        }catch (Exception e){

        }
        return AuthorId;
    }


    public static void addBook(String title,String authorFirstName,String lastName,String dateofbirth,String yearOfProduction, String pages, String status, String user_id,String library_name,String genre_name,boolean is_deleted){
        Statement statement;
        try{
            statement=connection.createStatement();

            String updateAuthor=String.format("INSERT INTO author (first_name, last_name, date_of_birth)\n" +
                    "VALUES ('%s', '%s', '%s')\n"+
                    "ON CONFLICT (first_name, last_name, date_of_birth) DO NOTHING",authorFirstName,lastName,dateofbirth);

            statement.executeUpdate(updateAuthor);
            System.out.println("Author created");

            String readAuthorId=String.format("SELECT author_id FROM public.author\n" +
                    "where first_name='%s' and last_name='%s'",authorFirstName,lastName);

            ResultSet resultSet = statement.executeQuery(readAuthorId);
            String newAuthorCreatedId="";
            while(resultSet.next()){
                newAuthorCreatedId=resultSet.getString("author_id");
            }

            System.out.println("Author id fetched");

            String readLibraryId=String.format("SELECT library_id FROM public.library\n" +
                    "            where library_name='%s'",library_name);

            ResultSet resultSet1 = statement.executeQuery(readLibraryId);
            String libraryId="";
            while(resultSet1.next()){
                libraryId=resultSet1.getString("library_id");
            }
            System.out.println("Library id fetched");


            String genreId;

            switch(genre_name) {
                case "Akcji":
                    genreId= "1";
                    break;
                case "Przygodowa":
                    genreId= "5";
                    break;
                case "ScienceFiction":
                    genreId= "2";
                    break;
                case "Romans":
                    genreId= "3";
                    break;
                case "Historyczne":
                    genreId= "4";
                    break;
                case "Akademickie":
                    genreId= "8";
                    break;
                case "Finansowe":
                    genreId= "9";
                    break;
                case "Dramat":
                    genreId= "6";
                    break;
                default:
                    genreId ="0";
                    break;
            }

            String addBook=String.format("INSERT INTO Book (title, author_id, yearofproduction,pages, status, user_id,library_id, genre_id,is_deleted)\n" +
                    "VALUES ('%s', %s, %s, %s, '%s',%s, %s, %s,%s);\n",title,newAuthorCreatedId,yearOfProduction,pages,status,user_id,libraryId,genreId,is_deleted);

            statement.executeUpdate(addBook);
            System.out.println("Book created");

        }catch (Exception e){
            System.out.println(e);
        }


    }


    public static void updateBook(String title, String authorName, String authorLastName, String dateOfBirth, String yearOfProduction, String pages, String genre_name, String titleToUpdate){
        Statement statement;
        try {
            statement=connection.createStatement();
            String author = String.format("INSERT INTO author (first_name, last_name, date_of_birth)\n" +
                    "VALUES ('%s', '%s', '%s')\n"+
                    "ON CONFLICT (first_name, last_name, date_of_birth) DO NOTHING", authorName, authorLastName, dateOfBirth);

            statement.executeUpdate(author);
            String authorID = searchAuthorByDetails(authorName, authorLastName);
            System.out.println("Author id fetched " +authorID);

            String genreId;

            switch(genre_name) {
                case "Akcji":
                    genreId= "1";
                    break;
                case "Przygodowa":
                    genreId= "5";
                    break;
                case "ScienceFiction":
                    genreId= "2";
                    break;
                case "Romans":
                    genreId= "3";
                    break;
                case "Historyczne":
                    genreId= "4";
                    break;
                case "Akademickie":
                    genreId= "8";
                    break;
                case "Finansowe":
                    genreId= "9";
                    break;
                case "Dramat":
                    genreId= "6";
                    break;
                default:
                    genreId ="0";
                    break;
            }
            String updateBook = String.format("UPDATE Book\n" +
                    "SET title = '%s', \n" +
                    "   author_id = %s, \n"+
                    "    yearofproduction = %s, \n" +
                    "    pages = %s,\n" +
                    "    genre_id = %s \n" +
                    "WHERE title='%s'  ", title,authorID, yearOfProduction, pages, genreId,titleToUpdate);
            statement.executeUpdate(updateBook);
            System.out.println("Book updated");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static List<String> getAllUsersInfo(String libraryName) {
        List<String> users = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;


        try {
            String query = String.format("SELECT * FROM public.users\n" +
                    "LEFT JOIN library on library.library_id=users.library_id\n" +
                    "where library.library_name='%s' ORDER BY user_id ASC", libraryName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String user_id = resultSet.getString("user_id");
                String username = resultSet.getString("username");
                String dateofcreation = resultSet.getString("dateofcreation");
                String permissionlevel = resultSet.getString("permissionlevel");
                String password = resultSet.getString("password");
                String libraryname = resultSet.getString("library_name");

                String userinfo = "ID: " + user_id + "," + " Username: " + username + "," + "Creation date: " + dateofcreation + "," + " Permission level: " + permissionlevel + "," + " Password: " + password + "," + " Library: " + libraryname;
                users.add(userinfo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }
}
