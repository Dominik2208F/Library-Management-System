package Frames;

import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class UserActionFrame extends JFrame {


    JButton ShowBookToBorrow;
    JButton BorrowAbook;
    JButton ReturnAbook;
    JButton ChangeUser;
    JButton ChangeLibrary;
    JButton ConfirmChoice;
    JButton showborrowedBook;
    JList<String> list;
    Library flowLibrary;
    UserChooseIFrame userChooseIFrame;
    JCheckBox ascendingCheckBox;
    JCheckBox descendingCheckBox;

    private boolean borrowButtonClicked = false;
    private boolean returnButtonClicked = false;

    public UserActionFrame(UserChooseIFrame userChooseIFrame, Library library) {

        this.flowLibrary = library;
        this.userChooseIFrame = userChooseIFrame;

        DefaultListModel<String> listOfAction = new DefaultListModel<>();
        list = new JList<>(listOfAction);
        list.setBounds(180, 30, 500, 80);
        add(list);


        //
        ascendingCheckBox = new JCheckBox("Sort Ascending");
        ascendingCheckBox.setBounds(250, 120, 150, 30);

        descendingCheckBox = new JCheckBox("Sort Descending");
        descendingCheckBox.setBounds(400, 120, 150, 30);
        add(ascendingCheckBox);

        ascendingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ascendingCheckBox.isSelected()) {
                    DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                    List<Book> temporaryList = new ArrayList<>();
                    temporaryList.addAll(flowLibrary.getListOfBooks());

                    Collections.sort(temporaryList);

                    for (Book book : temporaryList) {
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                }
            }
        });

        ascendingCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ascendingCheckBox.isSelected()) {
                    descendingCheckBox.setSelected(false);
                }
            }
        });

        add(descendingCheckBox);
        descendingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                    if (descendingCheckBox.isSelected()) {
                    List<Book> temporaryList = new ArrayList<>();
                    temporaryList.addAll(flowLibrary.getListOfBooks());

                    Collections.sort(temporaryList,Comparator.reverseOrder());

                    for(Book book : temporaryList){
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                }
            }
        });

        descendingCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendingCheckBox.isSelected()) {
                    ascendingCheckBox.setSelected(false);
                }
            }
        });

        ChangeLibrary = new JButton("Change library");
        ChangeLibrary.setBounds(40, 20, 130, 30);
        add(ChangeLibrary);


        ReturnAbook = new JButton("Return a book");
        ReturnAbook.setBounds(40, 100, 130, 30);
        add(ReturnAbook);


        ReturnAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmChoice.setEnabled(true);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                //assign User instance

                User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.ChoosenUserName);

                if (user.getUserbooks().isEmpty()) {

                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books to return", "Warning", JOptionPane.WARNING_MESSAGE);
                    borrowButtonClicked=false;
                } else {
                    ConfirmChoice.setEnabled(true);
                    for (Book Book : user.getUserbooks()) {
                        modifiedModel.addElement(Book.toString());
                    }
                    list.setModel(modifiedModel);
                    borrowButtonClicked=false;
                    returnButtonClicked=true;
                }

            }
        });


        ChangeUser = new JButton("Change User");
        ChangeUser.setBounds(40, 140, 130, 30);
        add(ChangeUser);

        BorrowAbook = new JButton("Borrow a book");
        BorrowAbook.setBounds(40, 60, 130, 30);
        add(BorrowAbook);

        showborrowedBook = new JButton("Show borrowed books");
        showborrowedBook.setBounds(40, 180, 180, 30);
        add(showborrowedBook);

        showborrowedBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                User userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.ChoosenUserName);

                if (!userFromList.getUserbooks().isEmpty()) {

                    for (Book book : userFromList.getUserbooks()) {
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                    borrowButtonClicked=false;
                } else {
                    for (Book book : userFromList.getUserbooks()) {
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "No borrowed books", "Warning", JOptionPane.WARNING_MESSAGE);
                    borrowButtonClicked=false;
                }

            }
        });


        ConfirmChoice = new JButton("Confirm Choice");
        ConfirmChoice.setBounds(500, 200, 160, 30);
        add(ConfirmChoice);

        ShowBookToBorrow= new JButton("Show all Books in Library");
        ShowBookToBorrow.setBounds(40,220,200,30);
        add(ShowBookToBorrow);

        ShowBookToBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(true);
                descendingCheckBox.setVisible(true);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                }
            }
        });


        ConfirmChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                ConfirmChoice.setEnabled(true);
                if (borrowButtonClicked) {
                    if (list.getSelectedIndex() != -1) {

                        DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                        int selectedBookIndex = list.getSelectedIndex();

                        //assign User instance
                        User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.ChoosenUserName);

                        //Assign book to user;


                        String titleOfBookToAssignToUser = flowLibrary.getListOfBooks().get(selectedBookIndex).getTitle();

                        //Assign book to user;
                        for (Book book : flowLibrary.getListOfBooks()) {
                            if (book.getTitle().equals(titleOfBookToAssignToUser)) {
                                user.assignBookToUser(book);
                            }
                        }

                        //Remove book from list
                        flowLibrary.getListOfBooks().remove(selectedBookIndex);
                        //

                        //refresh list of JList
                        for (Book books : flowLibrary.getListOfBooks()) {
                            modifiedModel.addElement(books.toString());
                        }

                        list.setModel(modifiedModel);
                    } else {
                        JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(returnButtonClicked){

                    if(list.getSelectedIndex() != -1){

                        DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                        int selectedBookIndexUserList = list.getSelectedIndex();

                        //assign User instance
                        User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.ChoosenUserName);

                        //Assign book to user;


                        String titleOfBookToUnassingFromUser = user.getUserbooks().get(selectedBookIndexUserList).getTitle();

                        List<Book> userBooksCopy = new ArrayList<>(user.getUserbooks());

                        for (Book book : userBooksCopy) {
                            if (book.getTitle().equals(titleOfBookToUnassingFromUser)) {
                                user.UnassignBookFromUser(book);
                                flowLibrary.getListOfBooks().add(book);
                            }
                        }



                        for (Book Book : user.getUserbooks()) {
                            modifiedModel.addElement(Book.toString());
                        }
                        list.setModel(modifiedModel);

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }



                }


            }

        });


        ChangeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                new UserChooseIFrame(userChooseIFrame.flowLibrary, userChooseIFrame.libraryManagementFrame);
                setVisible(false);
            }
        });


        ChangeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                new LibraryManagementFrame(userChooseIFrame.libraryManagementFrame.libraryDataBase);
                setVisible(false);
            }
        });

        BorrowAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                borrowButtonClicked = true;
                ConfirmChoice.setEnabled(true);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books to borrow", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(true);
                    borrowButtonClicked=true;
                }
            }

        });


        JScrollPane scrollPane = new JScrollPane(list); // Wrap the JList in a JScrollPane
        scrollPane.setBounds(180, 30, 500, 80);
        add(scrollPane);


        setSize(700, 300);
        setTitle("User Action Panel logged as " + userChooseIFrame.ChoosenUserName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        ascendingCheckBox.setVisible(false);
        descendingCheckBox.setVisible(false);

    }


}
