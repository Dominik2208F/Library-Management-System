package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;


public class UserActionFrame extends JFrame {


    JButton  ShowBookToBorrow;
    JButton  BorrowAbook;
    JButton  ReturnAbook;
    JButton  ChangeUser;
    JButton  ChangeLibrary;
    JButton showborrowedBook;

    JList<String> list;
    Library flowLibrary;

    UserChooseIFrame userChooseIFrame;

    public UserActionFrame(UserChooseIFrame userChooseIFrame,Library library) {

        this.flowLibrary = library;
        this.userChooseIFrame=userChooseIFrame;
        DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();


        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            listOfUsersFromLibrary.addElement(user.getName());
        }

        list = new JList<>(listOfUsersFromLibrary);

        list.setBounds(180, 30, 400, 60);
        add(list);


        ShowBookToBorrow= new JButton("Show borrowed books");
        ShowBookToBorrow.setBounds(40, 220, 160, 30);
        add(ShowBookToBorrow);
        //
        BorrowAbook = new JButton("Borrow a book");
        BorrowAbook.setBounds(40, 60, 130, 30);
        add(BorrowAbook);

        ReturnAbook = new JButton("Return a book");
        ReturnAbook.setBounds(40, 100, 130, 30);
        add(ReturnAbook);

        ChangeUser = new JButton("Change User");
        ChangeUser.setBounds(40, 140, 130, 30);
        add(ChangeUser);

        ChangeLibrary = new JButton("Change library");
        ChangeLibrary.setBounds(40, 180, 130, 30);
        add(ChangeLibrary);

        showborrowedBook = new JButton("Change library");
        showborrowedBook.setBounds(40, 20, 130, 30);
        add(showborrowedBook);

        JScrollPane scrollPane = new JScrollPane(list); // Wrap the JList in a JScrollPane
        scrollPane.setBounds(180, 30, 400, 60);
        add(scrollPane);



        setSize(600, 300);
        setTitle("User Action Panel logged as " + userChooseIFrame.ChoosenUserName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
    }

