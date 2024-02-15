package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
