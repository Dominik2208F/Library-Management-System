package Frames;

import Manager.CommonFunctions;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;


public class AdminActionFrame extends JFrame implements CommonFunctions {

    private JMenuBar menubar;
    private JMenu Options, Program;
    private JMenuItem changeUser, changeLibrary, programInfo;
    private JButton AddBook, ReturnInfoAllBooks, ReturnInfoOfBook, DeleteBook, UpdateBook, AddUser, DeleteUser, ReturnBookOfAGivenUser, BorrowedBooksOfUser, ConfirmChoice, QuickView, ConfirmChoiceOfGivenUser, returnAll;
    private JList<String> list;
    private Library flowLibrary;
    private UserChooseIFrame userChooseIFrame;
    private JLabel booksLabel;

    boolean deleteBookClicked = false;
    boolean updateBookClicked = false;

    private boolean informationUpdate = true;
    private boolean informationDelete = true;
    private boolean returnBookOfAGivenUser = false;
    private JComboBox<String> categoryComboBox, SubCategoryComboBox, UserSelectionComboBox, UserSelectionComboBoxToReturnABook;
    private Map<String, List<String>> subcategoriesMap = new HashMap<>();

    public AdminActionFrame(UserChooseIFrame userChooseIFrame, Library library) {

        this.flowLibrary = library;
        this.userChooseIFrame = userChooseIFrame;

        DefaultListModel<String> listOfAction = new DefaultListModel<>();
        list = new JList<>(listOfAction);
        list.setBounds(150, 20, 700, 150);
        add(list);

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);


                int width = getWidth();
                int height = getHeight();


                GradientPaint gradient = new GradientPaint(0, 0, new Color(240, 248, 255), width, height, new Color(0, 191, 255));

                ((Graphics2D) g).setPaint(gradient);
                g.fillRect(0, 0, width, height);

            }
        });
        menubar = new JMenuBar();
        Options = new JMenu("Options");
        Program = new JMenu("Info");
        changeUser = new JMenuItem("Log out");
        changeLibrary = new JMenuItem("Change library");
        programInfo = new JMenuItem("About");
        Options.add(changeUser);
        Options.add(changeLibrary);
        Program.add(programInfo);

        setJMenuBar(menubar);
        menubar.add(Options);
        menubar.add(Program);

        QuickView = new JButton("Quick view");
        QuickView.setBounds(855, 20, 120, 40);
        add(QuickView);

        ImageIcon qucikView = setIcon("/view.png");
        QuickView.setIcon(qucikView);

        booksLabel = new JLabel(flowLibrary.getListOfBooks().size() + " books in library");
        booksLabel.setBounds(400, -5, 180, 30);
        add(booksLabel);

        AddBook = new JButton("Add a book");
        AddBook.setBounds(10, 20, 130, 40);
        add(AddBook);

        ImageIcon add = setIcon("/add.png");
        AddBook.setIcon(add);

        ReturnInfoAllBooks = new JButton("Show all ");
        ReturnInfoAllBooks.setBounds(10, 70, 130, 40);
        add(ReturnInfoAllBooks);

        ImageIcon showboook = setIcon("/all_5334695.png");
        ReturnInfoAllBooks.setIcon(showboook);

        DeleteBook = new JButton("Delete book");
        DeleteBook.setBounds(10, 120, 130, 40);
        add(DeleteBook);

        ImageIcon deleteBook = setIcon("/dislike_9250694.png");
        DeleteBook.setIcon(deleteBook);

        ConfirmChoice = new JButton("Confirm Choice");
        ConfirmChoice.setBounds(680, 180, 160, 40);
        add(ConfirmChoice);
        ConfirmChoice.setVisible(false);
        ConfirmChoice.setBackground(Color.PINK);

        ImageIcon confirm = setIcon("/approved.png");
        ConfirmChoice.setIcon(confirm);

        UpdateBook = new JButton("Update book");
        UpdateBook.setBounds(10, 170, 130, 40);
        add(UpdateBook);

        ImageIcon updateBook = setIcon("/recycle_9250683.png");
        UpdateBook.setIcon(updateBook);

        AddUser = new JButton("Add user");
        AddUser.setBounds(10, 220, 130, 40);
        add(AddUser);

        ImageIcon addUserIcon = setIcon("/add-user_8924229 (1).png");
        AddUser.setIcon(addUserIcon);

        DeleteUser = new JButton("Delete user");
        DeleteUser.setBounds(10, 270, 130, 40);
        add(DeleteUser);

        ImageIcon deleteUser = setIcon("/delete-user.png");
        DeleteUser.setIcon(deleteUser);

        ReturnBookOfAGivenUser = new JButton("Return book of a given user");
        ReturnBookOfAGivenUser.setBounds(10, 320, 240, 40);
        add(ReturnBookOfAGivenUser);

        ImageIcon returnBook = setIcon("/return.png");
        ReturnBookOfAGivenUser.setIcon(returnBook);

        returnAll = new JButton("Return all by user");
        returnAll.setBounds(680, 280, 200, 40);
        add(returnAll);
        returnAll.setVisible(false);

        ImageIcon returnAllIcon = setIcon("/all.png");
        returnAll.setIcon(returnAllIcon);

        BorrowedBooksOfUser = new JButton("User borrowed books");
        BorrowedBooksOfUser.setBounds(10, 370, 200, 40);
        //  add(BorrowedBooksOfUser);

        categoryComboBox = new JComboBox<>(new String[]{"Author", "Genre", "Select", "Status", "Assigned to"});
        categoryComboBox.setBounds(680, 200, 160, 40);
        add(categoryComboBox);
        categoryComboBox.setSelectedItem("Select");

        SubCategoryComboBox = new JComboBox<>();
        SubCategoryComboBox.setBounds(680, 250, 160, 40);
        add(SubCategoryComboBox);


        UserSelectionComboBox = new JComboBox<>();
        UserSelectionComboBox.setBounds(680, 180, 160, 40);
        add(UserSelectionComboBox);

        UserSelectionComboBoxToReturnABook = new JComboBox<>();
        UserSelectionComboBoxToReturnABook.setBounds(680, 180, 160, 40);
        add(UserSelectionComboBoxToReturnABook);
        UserSelectionComboBoxToReturnABook.setVisible(false);


        ConfirmChoiceOfGivenUser = new JButton("Confirm choice by user");
        ConfirmChoiceOfGivenUser.setBounds(680, 230, 200, 40);
        add(ConfirmChoiceOfGivenUser);
        ConfirmChoiceOfGivenUser.setVisible(false);
        ConfirmChoiceOfGivenUser.setBackground(Color.PINK);

        ImageIcon confirmChoiceByUser = setIcon("/approved.png");
        ConfirmChoiceOfGivenUser.setIcon(confirmChoiceByUser);

        categoryComboBox.setVisible(false);
        SubCategoryComboBox.setVisible(false);
        UserSelectionComboBox.setVisible(false);
        UserSelectionComboBoxToReturnABook.setVisible(false);
        BorrowedBooksOfUser.setEnabled(false);
        ConfirmChoiceOfGivenUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (returnBookOfAGivenUser) {
                    if (list.getSelectedIndex() != -1) {
                        DefaultListModel<String> modifiedModel = new DefaultListModel<>();


                        String titleOfBookToUnassingFromUser = extractTitle(list.getSelectedValue());
                        String UserWithBorrowedBook = (String) UserSelectionComboBoxToReturnABook.getSelectedItem();
                        Book bookInstance = null;

                        for (Book book : ListOfBorrowedBook(library)) {
                            if (book.getTitle().equals(titleOfBookToUnassingFromUser)) {
                                bookInstance = book;
                                break;
                            }
                        }


                        int odp = JOptionPane.showConfirmDialog(null, "Do you want return a book: " + bookInstance.getTitle() + " ?");

                        if (odp == JOptionPane.YES_OPTION) {


                            bookInstance.setStatus(Status.AVAILABLE);
                            bookInstance.setAssignedUserToBook(new User("None", "none"));


                            for (Book Book : ListOfBorrowedBook(library)) {
                                if (Book.getStatus() == Status.BORROWED && Book.getAssignedUserToBook().getName().equals(UserWithBorrowedBook)) {
                                    modifiedModel.addElement(Book.toString(true));
                                }
                            }


                            list.setModel(modifiedModel);
                            if (modifiedModel.isEmpty()) {
                                JOptionPane.showMessageDialog(null, UserWithBorrowedBook + " does not have any book to borrow", "Message", JOptionPane.INFORMATION_MESSAGE);
                                ConfirmChoiceOfGivenUser.setEnabled(false);
                                returnAll.setEnabled(false);
                            }

                            if (checkIfAllBooksReturned(library.getListOfBooks())) {
                                ConfirmChoiceOfGivenUser.setEnabled(false);
                                returnAll.setEnabled(false);
                                UserSelectionComboBoxToReturnABook.setEnabled(false);
                                UserSelectionComboBoxToReturnABook.setModel(new DefaultComboBoxModel<>(new String[]{"No users"}));

                            }

                            JOptionPane.showMessageDialog(null, "Book " + bookInstance.getTitle() + " has been returned successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Choose one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        UserSelectionComboBoxToReturnABook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                String UserWithBorrowedBook = (String) UserSelectionComboBoxToReturnABook.getSelectedItem();

                if (!UserWithBorrowedBook.equals("Select")) {

                    ConfirmChoiceOfGivenUser.setVisible(true);
                    returnAll.setVisible(true);
                    returnAll.setEnabled(true);
                    for (Book Book : ListOfBorrowedBook(library)) {
                        if (Book.getAssignedUserToBook().getName().equals(UserWithBorrowedBook)) {
                            modifiedModel.addElement(Book.toString(true));
                        }
                    }
                    list.setModel(modifiedModel);
                    list.setEnabled(true);
                    QuickView.setEnabled(true);
                    returnBookOfAGivenUser = true;
                }

                if (checkIfAllBooksReturned(library.getListOfBooks())) {

                    ConfirmChoiceOfGivenUser.setEnabled(false);
                    returnAll.setEnabled(false);

                    list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null, "No books to return", "Warning", JOptionPane.WARNING_MESSAGE);
                    //  borrowButtonClicked = false;
                } else {
                    ConfirmChoiceOfGivenUser.setEnabled(true);
                    list.setModel(modifiedModel);
                    // /    if (informationReturn) {
                    //        JOptionPane.showMessageDialog(null, "Choose one book to return and confirm", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    //    }
                    //  informationReturn = false;
                    //  borrowButtonClicked = false;
                    // returnButtonClicked = true;
                }


            }
        });
        returnAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                String UserWithBorrowedBook = (String) UserSelectionComboBoxToReturnABook.getSelectedItem();
                int odp = JOptionPane.showConfirmDialog(null, "Do you want to return all books?");
                if (odp == JOptionPane.YES_OPTION) {
                    for (Book book : flowLibrary.getListOfBooks()) {
                        if (book.getStatus() == Status.BORROWED && book.getAssignedUserToBook().getName().equals(UserWithBorrowedBook)) {
                            book.setStatus(Status.AVAILABLE);
                            book.setAssignedUserToBook(new User("None", "none"));
                        }
                    }
                    JOptionPane.showMessageDialog(null, "All books have been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                    DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                    for (Book Book : flowLibrary.getListOfBooks()) {
                        if (Book.getStatus() == Status.BORROWED && Book.getAssignedUserToBook().getName().equals(userChooseIFrame.getChoosenUserName())) {
                            modifiedModel1.addElement(Book.toString());
                        }
                    }
                    list.setModel(modifiedModel1);
                    if (modifiedModel.isEmpty()) {
                        ConfirmChoiceOfGivenUser.setEnabled(false);
                        returnAll.setEnabled(false);
                    }
                    if (checkIfAllBooksReturned(flowLibrary.getListOfBooks())) {
                        ConfirmChoiceOfGivenUser.setEnabled(false);
                        returnAll.setEnabled(false);
                        UserSelectionComboBoxToReturnABook.setModel(new DefaultComboBoxModel<>(new String[]{"No users"}));

                    }
                }
            }
        });
        ReturnBookOfAGivenUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryComboBox.setVisible(true);
                SubCategoryComboBox.setVisible(true);
                UserSelectionComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                ConfirmChoice.setVisible(false);

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                if (ListOfBorrowedBook(library).isEmpty()) {
                    list.setModel(modifiedModel);
                    ConfirmChoiceOfGivenUser.setEnabled(false);
                    returnAll.setEnabled(false);
                    UserSelectionComboBoxToReturnABook.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No borrowed books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {


                    for (Book books : ListOfBorrowedBook(library)) {
                        modifiedModel.addElement(books.toString(true));
                    }
                    list.setModel(modifiedModel);
                    list.setEnabled(false);
                    QuickView.setEnabled(false);
                    UserSelectionComboBoxToReturnABook.setVisible(true);

                    List<String> listOfAllUserName = new ArrayList<>();
                    for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
                        if (!user.getName().equals("Admin")) {
                            listOfAllUserName.add(user.getName());
                        }
                    }
                    List<String> listOfBorrowedUserName = new ArrayList<>();
                    listOfBorrowedUserName.add("Select");
                    for (String userName : listOfAllUserName) {
                        for (Book book : ListOfBorrowedBook(library)) {
                            if (book.getAssignedUserToBook().getName().equals(userName)) {
                                listOfBorrowedUserName.add(userName);
                                break;
                            }
                        }
                    }

                    String UserNameArray[] = listOfBorrowedUserName.toArray(new String[listOfBorrowedUserName.size()]);
                    UserSelectionComboBoxToReturnABook.setModel(new DefaultComboBoxModel<>(UserNameArray));
                }
            }
        });

        QuickView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (list.getSelectedIndex() != -1) {
                    new OverViewBookJFrame(library, list);
                } else {
                    JOptionPane.showMessageDialog(null, "Choose one book from list", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        DeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                UserSelectionComboBox.setVisible(true);
                UserSelectionComboBox.setEnabled(true);
                ConfirmChoiceOfGivenUser.setVisible(false);
                returnAll.setVisible(false);
                UserSelectionComboBoxToReturnABook.setVisible(false);
                ConfirmChoice.setVisible(false);
                if (flowLibrary.getLibraryUserDataBase().getListOfUser().size() == 1) {
                    UserSelectionComboBox.setEnabled(false);
                    UserSelectionComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"No users"}));
                    JOptionPane.showMessageDialog(null,
                            "No additional users assigned to library", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    List<String> listOfUserName = new ArrayList<>();
                    listOfUserName.add("Select");
                    for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
                        if (!user.getName().equals("Admin")) {
                            listOfUserName.add(user.getName());
                        }
                    }
                    String UserNameArray[] = listOfUserName.toArray(new String[listOfUserName.size()]);
                    UserSelectionComboBox.setModel(new DefaultComboBoxModel<>(UserNameArray));
                }
            }
        });
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                categoryFiltering();
            }

        });
        SubCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                subCategoryFiltering();
            }
        });
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UserChooseIFrame(userChooseIFrame.getFlowLibrary(), userChooseIFrame.getLibraryManagementFrame());
                setVisible(false);
            }
        });
        changeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LibraryManagementFrame(userChooseIFrame.getLibraryManagementFrame().getLibraryDataBase());
                setVisible(false);
            }
        });
        ReturnInfoAllBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryComboBox.setVisible(true);
                SubCategoryComboBox.setVisible(true);
                UserSelectionComboBox.setVisible(false);
                ConfirmChoice.setVisible(false);
                ConfirmChoiceOfGivenUser.setVisible(false);
                returnAll.setVisible(false);
                UserSelectionComboBoxToReturnABook.setVisible(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString(true));
                    }
                    list.setModel(modifiedModel);
                    SubCategoryComboBox.setEnabled(false);
                    categoryComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString(true));
                    }
                    SubCategoryComboBox.setEnabled(true);
                    categoryComboBox.setEnabled(true);
                    list.setModel(modifiedModel);
                    list.setEnabled(true);
                }
            }
        });
        ConfirmChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (deleteBookClicked) {
                    if (list.getSelectedIndex() != -1) {

                        List<String> listOfBooksToIterateThrough = new ArrayList<>();
                        int selectedBookIndexToRemove = list.getSelectedIndex();
                        int odp = JOptionPane.showConfirmDialog(null, "Do you want to remove a book: " + flowLibrary.getListOfBooks().get(selectedBookIndexToRemove).getTitle() + " ?");

                        if (odp == JOptionPane.YES_OPTION) {

                            //Remove
                            flowLibrary.getListOfBooks().remove(selectedBookIndexToRemove);
                            for (Book book : flowLibrary.getListOfBooks()) {
                                listOfBooksToIterateThrough.add(book.toString(true));
                            }

                            String BooksAfterRemove[] = listOfBooksToIterateThrough.toArray(new String[listOfBooksToIterateThrough.size()]);
                            list.setModel(new DefaultComboBoxModel<>(BooksAfterRemove));
                            booksLabel.setText(flowLibrary.getListOfBooks().size() + " books in library");
                        }
                        if (flowLibrary.getListOfBooks().isEmpty()) {
                            ConfirmChoice.setEnabled(false);
                            JOptionPane.showMessageDialog(null,
                                    "No book to delete in " + flowLibrary.getNameOfLibrary(), "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                        deleteBookClicked = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Choose one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                if (updateBookClicked) {
                    if (list.getSelectedIndex() != -1) {
                        UpdateBookJFrame updateBookJFrame = new UpdateBookJFrame(library, list);
                        updateBookClicked = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Choose one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        DeleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookClicked = false;
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                UserSelectionComboBox.setVisible(false);
                ConfirmChoice.setVisible(true);
                list.setEnabled(true);
                ConfirmChoiceOfGivenUser.setVisible(false);
                returnAll.setVisible(false);
                UserSelectionComboBoxToReturnABook.setVisible(false);
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "No book to delete in " + flowLibrary.getNameOfLibrary(), "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (informationDelete) {
                        JOptionPane.showMessageDialog(null,
                                "Choose one book from list and click confirm", "Message", JOptionPane.INFORMATION_MESSAGE);
                        informationDelete = false;
                    }
                    ConfirmChoice.setEnabled(true);
                    deleteBookClicked = true;
                    DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString(true));
                    }
                    SubCategoryComboBox.setEnabled(true);
                    categoryComboBox.setEnabled(true);
                    list.setModel(modifiedModel);

                }
            }
        });
        UserSelectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedUserToDelete = (String) UserSelectionComboBox.getSelectedItem();
                if (!selectedUserToDelete.equals("Select")) {

                    List<User> arrayCopyToIterate = new ArrayList<>(flowLibrary.getLibraryUserDataBase().getListOfUser());

                    for (User user : arrayCopyToIterate) {
                        if (user.getName().equals(selectedUserToDelete)) {
                            int odp = JOptionPane.showConfirmDialog(null, "Do you want to remove " + user.getName() + " ?", "Message", JOptionPane.YES_NO_OPTION);
                            if (odp == JOptionPane.YES_OPTION) {


                                flowLibrary.getLibraryUserDataBase().getListOfUser().remove(user);

                                for (Book book : flowLibrary.getListOfBooks()) {
                                    if (book.getAssignedUserToBook().getName().equals(selectedUserToDelete)) {
                                        book.setStatus(Status.AVAILABLE);
                                        book.setAssignedUserToBook(new User("None", "none"));
                                    }
                                }


                                List<String> UpdatedListOfUsers = new ArrayList<>();
                                for (User user1 : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
                                    if (!user1.getName().equals("Admin")) {
                                        UpdatedListOfUsers.add(user1.getName());
                                    }
                                }

                                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                                for (Book books : flowLibrary.getListOfBooks()) {
                                    modifiedModel.addElement(books.toString(true));
                                }
                                list.setModel(modifiedModel);


                                if (UpdatedListOfUsers.isEmpty()) {
                                    UserSelectionComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"No users"}));
                                    UserSelectionComboBox.setEnabled(false);
                                } else {
                                    String modifiedUserArray[] = UpdatedListOfUsers.toArray(new String[UpdatedListOfUsers.size()]);
                                    UserSelectionComboBox.setModel(new DefaultComboBoxModel<>(modifiedUserArray));
                                }
                            }
                        }
                    }

                }
            }
        });
        AddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.setEnabled(false);
                UserSelectionComboBox.setVisible(false);
                ConfirmChoice.setVisible(false);
                AddUserFrame addUserFrame = new AddUserFrame(userChooseIFrame, library, true);
                addUserFrame.setVisible(true);
            }
        });
        UpdateBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBookClicked = false;
                list.setEnabled(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                UserSelectionComboBox.setVisible(false);
                ConfirmChoice.setVisible(true);
                ConfirmChoiceOfGivenUser.setVisible(false);
                returnAll.setVisible(false);
                UserSelectionComboBoxToReturnABook.setVisible(false);
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "No book to update " + flowLibrary.getNameOfLibrary(), "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    updateBookClicked = true;
                    if (informationUpdate) {
                        JOptionPane.showMessageDialog(null,
                                "Choose one book from list and click confirm", "Message", JOptionPane.INFORMATION_MESSAGE);
                        informationUpdate = false;
                    }
                    DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString(true));
                        list.setModel(modifiedModel);
                    }
                }
            }
        });
        AddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmChoiceOfGivenUser.setVisible(false);
                returnAll.setVisible(false);
                UserSelectionComboBoxToReturnABook.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                DefaultListModel<String> updatedModel = new DefaultListModel<>();
                for (Book books : flowLibrary.getListOfBooks()) {
                    updatedModel.addElement(books.toString(true));
                }
                list.setModel(updatedModel);
                AddBookJFrame addBookJFrame = new AddBookJFrame(flowLibrary, list, booksLabel);

            }
        });
        programInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Program written by Dominik Jakubaszek. \n Version 2.0.0", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(150, 20, 700, 150);
        add(scrollPane);

        setSize(1000, 460);
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

    }

    public void subCategoryFiltering() {

        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        String selectedOption = (String) SubCategoryComboBox.getSelectedItem();
        DefaultListModel<String> modifiedModel = new DefaultListModel<>();
        if (selectedCategory.equals("Genre")) {
            for (Book book : flowLibrary.getListOfBooks()) {
                if (book.getGenre().getName().equals(selectedOption)) {
                    modifiedModel.addElement(book.toString(true));
                    list.setModel(modifiedModel);
                }
            }
            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedOption, "Message", JOptionPane.INFORMATION_MESSAGE);

            }
        } else if (selectedCategory.equals("Author")) {

            for (Book book : flowLibrary.getListOfBooks()) {
                if (book.getAuthor().getLastName().equals(selectedOption)) {
                    modifiedModel.addElement(book.toString(true));
                }
                list.setModel(modifiedModel);
            }
            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedOption, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (selectedCategory.equals("Status")) {

            for (Book book : flowLibrary.getListOfBooks()) {
                if (book.getStatus().toString().equals(selectedOption)) {
                    modifiedModel.addElement(book.toString(true));
                    list.setModel(modifiedModel);
                }
            }
            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedOption, "Message", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (selectedCategory.equals("Assigned to")) {

            for (Book book : flowLibrary.getListOfBooks()) {
                if (book.getAssignedUserToBook().getName().equals(selectedOption)) {
                    modifiedModel.addElement(book.toString(true));
                    list.setModel(modifiedModel);
                }
            }
            if (modifiedModel.isEmpty()) {
                list.setModel(modifiedModel);
                JOptionPane.showMessageDialog(null,
                        "No book meets the criteria Category " + selectedCategory + " and " + selectedOption, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void categoryFiltering() {

        List<String> listOfAuthorSurnames = new ArrayList<>();
        List<String> listOfUsers = new ArrayList<>();

        for (Book book : flowLibrary.getListOfBooks()) {
            if (!listOfAuthorSurnames.contains(book.getAuthor().getLastName())) {
                listOfAuthorSurnames.add(book.getAuthor().getLastName());
            }
        }

        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            if (!user.getName().equals("Admin")) {
                listOfUsers.add(user.getName());
            }
        }
        listOfUsers.add(new User("None", "none").getName());

        subcategoriesMap.put("Select", Arrays.asList(" "));
        subcategoriesMap.put("Status", Arrays.asList(Status.AVAILABLE.toString(), Status.BORROWED.toString()));
        subcategoriesMap.put("Author", listOfAuthorSurnames);
        subcategoriesMap.put("Genre", Arrays.asList("Przygodowa", "Akcji", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"));
        subcategoriesMap.put("Assigned to", listOfUsers);

        String selectedCategory = (String) categoryComboBox.getSelectedItem();

        List<String> subcategories = subcategoriesMap.get(selectedCategory);
        String subcategoriesArray[] = subcategories.toArray(new String[subcategories.size()]);


        if (subcategories != null && !selectedCategory.equals("Select")) {
            SubCategoryComboBox.setEnabled(true);
            SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
            SubCategoryComboBox.setVisible(true);
            subCategoryFiltering();
        } else {
            SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
            SubCategoryComboBox.setEnabled(false);
        }
    }

}
