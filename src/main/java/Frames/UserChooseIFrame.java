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
    String ChoosenUserName;
    Library flowLibrary;
    public UserChooseIFrame(Library library){
    this.flowLibrary=library;

        DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();


        for(User user : flowLibrary.getLibraryUserDataBase().getListOfUser()){
            listOfUsersFromLibrary.addElement(user.getName());
        }

        list = new JList<>(listOfUsersFromLibrary);

        list.setBounds(70,50, 100,60);
        add(list);
        //
        availableUser = new JLabel("Available Users");
        availableUser.setBounds(70,10, 150,20);
        add(availableUser);
        //
        selectedUser = new JLabel("Selected User");
        selectedUser.setBounds(50,120, 150,20);

        confirmUser= new JButton("Confirm");
        confirmUser.setBounds(70,120, 100,40);
        add(confirmUser);
        //
        addUser= new JButton("Add User");
        addUser.setBounds(70,170,100,30);
        add(addUser);
        confirmUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedValue().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Choose at least 1 user","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    ChoosenUserName = list.getSelectedValue();

                    new LoginUserFrame(UserChooseIFrame.this, library);

                }
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

        setSize(250,350);
        setTitle("Choose user");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
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


