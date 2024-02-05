package Frames;

import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserActionFrame extends JFrame  {


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

       DefaultListModel<String> listOfAction= new DefaultListModel<>();

        listOfAction.addElement("Select option to retrieve data");
        list = new JList<>(listOfAction);

        list.setBounds(180, 30, 500, 80);
        add(list);


        ShowBookToBorrow= new JButton("Show borrowed books");
        ShowBookToBorrow.setBounds(40, 220, 160, 30);
        add(ShowBookToBorrow);
        //
        BorrowAbook = new JButton("Borrow a book");
        BorrowAbook.setBounds(40, 60, 130, 30);
        add(BorrowAbook);
        BorrowAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                for(Book books: flowLibrary.getListOfBooks()){
                    modifiedModel.addElement(books.toString());
                }
                list.setModel(modifiedModel);
            }
        });

        ReturnAbook = new JButton("Return a book");
        ReturnAbook.setBounds(40, 100, 130, 30);
        add(ReturnAbook);

        ChangeUser = new JButton("Change User");
        ChangeUser.setBounds(40, 140, 130, 30);
        add(ChangeUser);
        ChangeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserChooseIFrame(userChooseIFrame.flowLibrary,userChooseIFrame.libraryManagementFrame);
                setVisible(false);
            }
        });

        ChangeLibrary = new JButton("Change library");
        ChangeLibrary.setBounds(40, 180, 130, 30);
        add(ChangeLibrary);

        ChangeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new LibraryManagementFrame(userChooseIFrame.libraryManagementFrame.libraryDataBase);
                setVisible(false);
            }
        });


        showborrowedBook = new JButton("Change library");
        showborrowedBook.setBounds(40, 20, 130, 30);
        add(showborrowedBook);

        JScrollPane scrollPane = new JScrollPane(list); // Wrap the JList in a JScrollPane
        scrollPane.setBounds(180, 30, 500, 80);
        add(scrollPane);



        setSize(700, 300);
        setTitle("User Action Panel logged as " + userChooseIFrame.ChoosenUserName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
    }

