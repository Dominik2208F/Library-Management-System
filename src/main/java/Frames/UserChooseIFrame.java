package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserChooseIFrame extends JFrame {

    JLabel availableUser;
    JLabel selectedUser;
    JButton confirmUser;
    JButton addUser;
    JList<String> list;

    Library flowLibrary;
    public UserChooseIFrame(Library library){
    this.flowLibrary=library;

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
                AddUserFrame addUserFrame= new AddUserFrame(UserChooseIFrame.this,library);
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


