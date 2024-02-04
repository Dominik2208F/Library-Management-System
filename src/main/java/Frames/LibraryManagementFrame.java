package Frames;

import Frames.UserChooseIFrame;
import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementFrame extends JFrame implements ActionListener {

    JLabel label;
    JLabel selectedLibrary;
    JButton buttonConfirm;
    JList<String> list;
    LibraryDataBase libraryDataBase;
    Library flowLibrary;
    public LibraryManagementFrame(LibraryDataBase libraryDataBase){

        this.libraryDataBase=libraryDataBase;

        DefaultListModel<String> libraryListPanel = new DefaultListModel<>();

        for(Library Singlelibrary : libraryDataBase.getListOfLibrary()){
            libraryListPanel.addElement(Singlelibrary.getNameOfLibrary());
        }

        list = new JList<>(libraryListPanel);
        list.setBounds(30,50, 100,50);
        add(list);
        //
        label = new JLabel("Select library to visit");
        label.setBounds(50,10, 150,20);
        add(label);
        //
        selectedLibrary = new JLabel("Selected library");
        selectedLibrary.setBounds(50,120, 150,20);
        add(selectedLibrary);
        //
        buttonConfirm= new JButton("Confirm");
        buttonConfirm.setBounds(150,50, 100,40);
        add(buttonConfirm);
        buttonConfirm.addActionListener(this);
        //
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public void IfButtonClicked(){
        String selectedLibraryText = list.getSelectedValue();
        selectedLibrary.setText("Selected library is " + selectedLibraryText);

        for (Library librarySpec : libraryDataBase.getListOfLibrary()) {
            if (librarySpec.getNameOfLibrary().equalsIgnoreCase(selectedLibraryText)) {
                flowLibrary=librarySpec;

            }
        }

        UserChooseIFrame userChooseIFrame = new UserChooseIFrame(flowLibrary);
        dispose();
        userChooseIFrame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
        if(source==buttonConfirm) {
            IfButtonClicked();
        }
    }
    }


