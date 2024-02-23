package Manager;


import Frames.LibraryManagementFrame;
import com.formdev.flatlaf.FlatLightLaf;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;



public class
Main {

    public static void main(String[] args) {


        DbConnection db  = new DbConnection();
        Connection connection= db.connectionToDB("Library","postgres","Dominik1");
        new Queries(connection);
        new AdminQueries(connection);

        FlatLightLaf.setup();
        UIManager.put( "Button.arc", 20 );
        UIManager.put( "Component.arc", 20 );
        UIManager.put( "CheckBox.arc", 20 );
        UIManager.put( "ProgressBar.arc", 20 );
        UIManager.put( "TextComponent.arc" , 20 );
        UIManager.put( "ScrollBar.thumbArc", 999 );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        UIManager.put("List.selectionBackground", new Color(0, 191, 255));

        new LibraryManagementFrame();
    }
}
