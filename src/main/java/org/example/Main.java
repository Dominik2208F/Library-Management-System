package org.example;


import Frames.LibraryManagementFrame;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.LibraryManager.LibraryDataBase;


import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args)  {


        FlatLightLaf.setup();
        UIManager.put( "Button.arc", 20 );
        UIManager.put( "Component.arc", 20 );
        UIManager.put( "CheckBox.arc", 20 );
        UIManager.put( "ProgressBar.arc", 20 );
        UIManager.put( "TextComponent.arc" , 20 );
        UIManager.put( "ScrollBar.thumbArc", 999 );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        UIManager.put("List.selectionBackground", new Color(0, 191, 255));

        LibraryDataBase libraryDataBase = new LibraryDataBase();
        SetUp SetUp = new SetUp(libraryDataBase);
        SetUp.createDefaultLibrariesSetUp("Pruszkowska");
        SetUp.createDefaultLibrariesSetUp("Warszawska");

        new Flow(libraryDataBase);
        new LibraryManagementFrame(libraryDataBase);


    }
}
