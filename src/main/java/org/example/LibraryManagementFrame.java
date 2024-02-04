package org.example;

import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;
import org.example.UserManager.User;

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

        UserChooseIFrame userChooseIFrame = new UserChooseIFrame();
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


    public class UserChooseIFrame extends  JFrame{

        JLabel availableUser;
        JLabel selectedUser;
        JButton confirmUser;
        JButton addUser;
        UserChooseIFrame(){

            DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();


            for(User user : flowLibrary.getLibraryUserDataBase().getListOfUser()){
                listOfUsersFromLibrary.addElement(user.getName());
            }

            list = new JList<>(listOfUsersFromLibrary);

            list.setBounds(30,50, 100,50);
            add(list);
            //
            availableUser = new JLabel("Available User");
            availableUser.setBounds(50,10, 150,20);
            add(availableUser);
            //
            selectedUser = new JLabel("Selected User");
            selectedUser.setBounds(50,120, 150,20);
            add(selectedUser);
            //
            confirmUser= new JButton("Confirm");
            confirmUser.setBounds(150,50, 100,40);
            add(confirmUser);
            //
            addUser= new JButton("Add User");
            addUser.setBounds(150,90,100,30);
            add(addUser);
            confirmUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String selectedUserText= list.getSelectedValue();
                    selectedUser.setText("Selected User is " + selectedUserText);
                }
            });

            addUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame= new AddUserFrame(UserChooseIFrame.this);
                addUserFrame.setVisible(true);
                }
            });
            //
            setSize(300,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setVisible(true);
        }

        public void updateListOfUsers() {
            DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();

            for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
                listOfUsersFromLibrary.addElement(user.getName());
            }

            list.setModel(listOfUsersFromLibrary);
        }
        }

    public class AddUserFrame extends JFrame {

        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton saveButton;
        private JLabel usernameLabel;
        private JLabel passwordLabel;
        private UserChooseIFrame userChooseIFrame;

        public AddUserFrame(UserChooseIFrame userChooseIFrame) {
            this.userChooseIFrame=userChooseIFrame;

            usernameField = new JTextField();
            passwordField = new JPasswordField();
            saveButton = new JButton("Save");
            usernameLabel = new JLabel("Username:");
            passwordLabel = new JLabel("Password:");

            usernameLabel.setBounds(30,10, 100,30);
            passwordLabel.setBounds(30,40,100,30);

            usernameField.setBounds(100,20,150,20);
            passwordField.setBounds(100,50,150,20);

           ;
            saveButton.setBounds(130,90,80,30);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    char[] passwordchar= passwordField.getPassword();
                    String password= new String(passwordchar);
                    flowLibrary.getLibraryUserDataBase().getListOfUser().add(new User(username,password));
                    userChooseIFrame.updateListOfUsers();
                    setVisible(false);
                }
            });

            add(usernameLabel);
            add(usernameField);
            add(passwordLabel);
            add(passwordField);
            add(saveButton);

            setSize(300,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setVisible(true);
        }

     }
    }


