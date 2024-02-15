package Manager;

import java.sql.*;

public class DbConnection {

    public Connection connectionToDB(String dbname,String user,String pass){
        Connection connection = null;


        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(connection!=null){
                System.out.println("Connection established");
            }
            else{
                System.out.println("Connection failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }


}
