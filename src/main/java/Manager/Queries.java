package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Queries {

    static Connection connection;
    public Queries(Connection connection) {
        this.connection=connection;
    }

    public void createTable(Connection connection, String tableName){

        Statement statement;
        try{
            String query="create table "+tableName+"(empid SERIAL,name varchar(200),address varchar(200),primary key(empid));";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void insert_row(String tableName,String name,String address){

        Statement statement;
        try{
            String query=String.format("insert into %s(name,address) values('%s','%s');",tableName,name,address);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static ResultSet readAllLibraries(){
        ResultSet resultSet=null;
        Statement statement;
        try{
            String query = String.format("SELECT library_name FROM public.library");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            System.out.println(e);
        }

        return resultSet;
    }


    public static ResultSet readUsersAssignedToLibraryByName(String Name){
        ResultSet resultSet=null;
        Statement statement;
        try{
            String query=String.format("SELECT username FROM public.users LEFT JOIN library on users.library_Id=library.library_id WHERE library.library_name='%s'",Name);
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
        }catch (Exception e){
            System.out.println(e);
        }
        return resultSet;
    }

    public static String getPasswordFromUserByName(String Name){
        ResultSet resultSet=null;
        Statement statement;
        try{
            String query=String.format("SELECT password FROM public.users WHERE username='%s'",Name);
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);

            while(resultSet.next()){
                return resultSet.getString("password");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static String getIdOfLibraryByName(String name){

        ResultSet resultSet=null;
        Statement statement;
        try{
            String query=String.format("SELECT library_id FROM public.library where library_Name='%s'",name);
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);

            while(resultSet.next()){
                return resultSet.getString("library_id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void updateDataBaseWithNewUser(String userName,String password,String idOfLibrary){

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = today.format(formatter);

        Statement statement;
        try {
            String query = String.format("INSERT INTO public.users (username, library_id, dateofcreation, permissionlevel, password) VALUES ('%s', %s, '%s', 'User', '%s')", userName, idOfLibrary, formattedDate, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void update_name(String tableName,String oldValue,String newValue){
        Statement statement;
        try{
            String query=String.format("update %s set name='%s' where name='%s'",tableName,newValue,oldValue);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void search_by_name(String tableName,String name){
        Statement statement;
        ResultSet rs;
        try{
            String query=String.format("select * from %s where name='%s'",tableName,name);
            statement=connection.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid"));
                System.out.print(rs.getString("name"));
                System.out.println(rs.getString("address"));
            }
            System.out.println("Data fetched");

        }catch (Exception e){
            System.out.println(e);
        }
    }

}
