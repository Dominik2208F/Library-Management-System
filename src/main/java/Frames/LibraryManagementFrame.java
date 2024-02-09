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
    JComboBox<String> comboBox; // Zmiana na JComboBox
    LibraryDataBase libraryDataBase;
    Library flowLibrary;
    public LibraryManagementFrame(LibraryDataBase libraryDataBase){

        this.libraryDataBase=libraryDataBase;

       // DefaultListModel<String> libraryListPanel = new DefaultListModel<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(); // Model dla JComboBox

        for(Library Singlelibrary : libraryDataBase.getListOfLibrary()){
            comboBoxModel.addElement(Singlelibrary.getNameOfLibrary());
        }

        comboBox = new JComboBox<>(comboBoxModel); // Tworzenie JComboBox z modelem

        comboBox.setBounds(70,50, 100,40);
        //
        label = new JLabel("Select library");
        label.setBounds(79,10, 125,40);

        //
        selectedLibrary = new JLabel("Selected library");
        selectedLibrary.setBounds(120,220, 150,20);

        buttonConfirm= new JButton("Confirm");
        buttonConfirm.setBounds(70,100, 100,40);

        buttonConfirm.addActionListener(this);

        add(comboBox);
        add(label);
        add(buttonConfirm);
        //

        setSize(250,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setTitle("Select library");



        ImageIcon backgroundImage = new ImageIcon("src/background1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
        backgroundLabel.repaint();
        setLayout(null);
    }

    public void IfButtonClicked(){
        String selectedLibraryText = (String) comboBox.getSelectedItem();
        if(selectedLibraryText == null || selectedLibraryText.isEmpty()){
        JOptionPane.showMessageDialog(LibraryManagementFrame.this,"You have to choose at least 1 library","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
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


