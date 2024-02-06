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
        list.setBounds(70,50, 100,40);
        //
        label = new JLabel("Select library");
        label.setBounds(79,10, 125,40);

        //
        selectedLibrary = new JLabel("Selected library");
        selectedLibrary.setBounds(120,220, 150,20);

        buttonConfirm= new JButton("Confirm");
        buttonConfirm.setBounds(70,100, 100,40);

        buttonConfirm.addActionListener(this);

        add(list);
        add(label);
        add(buttonConfirm);
        //

        setSize(250,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);


        ImageIcon backgroundImage = new ImageIcon("src/background1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
        backgroundLabel.repaint();
        setLayout(null);
    }

    public void IfButtonClicked(){
        if(list.isSelectionEmpty()){
        JOptionPane.showMessageDialog(LibraryManagementFrame.this,"You have to choose at least 1 library","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            String selectedLibraryText = list.getSelectedValue();
            selectedLibrary.setText("Selected library is " + selectedLibraryText);

            for (Library librarySpec : libraryDataBase.getListOfLibrary()) {
                if (librarySpec.getNameOfLibrary().equalsIgnoreCase(selectedLibraryText)) {
                    flowLibrary = librarySpec;

                }
            }
            UserChooseIFrame userChooseIFrame = new UserChooseIFrame(flowLibrary,LibraryManagementFrame.this);  // przekazujesz klase IFrame z zainicjowanym obiektem UserDataBase
            dispose();
            userChooseIFrame.setVisible(true);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
        if(source==buttonConfirm) {
            IfButtonClicked();
        }
    }
    }


