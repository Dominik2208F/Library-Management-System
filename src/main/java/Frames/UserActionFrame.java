package Frames;

import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;


public class UserActionFrame extends JFrame {

    JMenuBar menubar;
    JMenu Options, Program;
    JMenuItem changeUser, changeLibrary, programInfo;
    JButton Sort,BorrowAbook,ReturnAbook,ConfirmChoice,showborrowedBook,returnAll,borrowALL, filter;
    JList<String> list;
    Library flowLibrary;
    UserChooseIFrame userChooseIFrame;
    JCheckBox ascendingCheckBox;
    JCheckBox descendingCheckBox;
    JLabel booksLabel;

    private JComboBox<String> sortComboBox, categoryComboBox, SubCategoryComboBox;

    private boolean borrowButtonClicked = false;
    private boolean returnButtonClicked = false;
    private boolean informationBorrow = true;
    private boolean informationReturn = true;

    public UserActionFrame(UserChooseIFrame userChooseIFrame, Library library) {

        this.flowLibrary = library;
        this.userChooseIFrame = userChooseIFrame;

        DefaultListModel<String> listOfAction = new DefaultListModel<>();
        list = new JList<>(listOfAction);
        list.setBounds(180, 20, 500, 150);
        add(list);

        Map<String, List<String>> subcategoriesMap = new HashMap<>();

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);


                int width = getWidth();
                int height = getHeight();


                GradientPaint gradient = new GradientPaint(100, 300, new Color(250, 235, 215), width, height, new Color(127, 255, 212));

                ((Graphics2D) g).setPaint(gradient);
                g.fillRect(0, 0, width, height);

            }
        });

        menubar = new JMenuBar();
        Options = new JMenu("Options");
        Program = new JMenu("Info");
        changeUser = new JMenuItem("Change user");
        changeLibrary = new JMenuItem("Change library");
        programInfo = new JMenuItem("About");
        Options.add(changeUser);
        Options.add(changeLibrary);
        Program.add(programInfo);

        setJMenuBar(menubar);
        menubar.add(Options);
        menubar.add(Program);

        returnAll = new JButton("Return all");
        returnAll.setBounds(500, 240, 160, 30);
        add(returnAll);
        returnAll.setVisible(false);

        borrowALL = new JButton("Borrow all");
        borrowALL.setBounds(500, 240, 160, 30);
        add(borrowALL);
        borrowALL.setVisible(false);

        sortComboBox = new JComboBox<>(new String[]{"Title", "Author", "Genre"});
        sortComboBox.setBounds(500, 240, 160, 30);
        add(sortComboBox);

        categoryComboBox = new JComboBox<>(new String[]{"Author", "Genre", "Select"});
        categoryComboBox.setBounds(500, 200, 160, 30);
        add(categoryComboBox);
        categoryComboBox.setSelectedItem("Select");

        SubCategoryComboBox = new JComboBox<>();
        SubCategoryComboBox.setBounds(500, 240, 160, 30);
        add(SubCategoryComboBox);

        booksLabel = new JLabel(flowLibrary.getListOfBooks().size() + " books in " + flowLibrary.getNameOfLibrary() + " library");
        booksLabel.setBounds(350, -5, 200, 30);
        add(booksLabel);

        ascendingCheckBox = new JCheckBox("Sort Ascending");
        ascendingCheckBox.setBounds(250, 180, 150, 30);

        descendingCheckBox = new JCheckBox("Sort Descending");
        descendingCheckBox.setBounds(400, 180, 150, 30);
        add(ascendingCheckBox);
        add(descendingCheckBox);

        BorrowAbook = new JButton("Borrow a book");
        BorrowAbook.setBounds(40, 20, 130, 35);
        add(BorrowAbook);


        ReturnAbook = new JButton("Return a book");
        ReturnAbook.setBounds(40, 60, 130, 35);
        add(ReturnAbook);

        filter = new JButton("Filter books in library");
        filter.setBounds(40, 200, 200, 35);
        add(filter);

        showborrowedBook = new JButton("Show borrowed books");
        showborrowedBook.setBounds(40, 240, 200, 35);
        add(showborrowedBook);


        ConfirmChoice = new JButton("Confirm Choice");
        ConfirmChoice.setBounds(500, 280, 160, 40);
        add(ConfirmChoice);
        ConfirmChoice.setBackground(Color.PINK);
        ConfirmChoice.setVisible(false);

        Sort = new JButton("Sort books in Library");
        Sort.setBounds(40, 280, 200, 35);
        add(Sort);
        showborrowedBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                returnAll.setVisible(false);
                borrowALL.setVisible(false);
                ConfirmChoice.setVisible(false);
                list.setEnabled(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                User userFromList = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());

                if (!userFromList.getUserbooks().isEmpty()) {

                    for (Book book : userFromList.getUserbooks()) {
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                    borrowButtonClicked = false;
                } else {
                    for (Book book : userFromList.getUserbooks()) {
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "No borrowed books", "Warning", JOptionPane.WARNING_MESSAGE);
                    borrowButtonClicked = false;
                }

            }
        });
        ReturnAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmChoice.setEnabled(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                returnAll.setVisible(true);
                returnAll.setEnabled(true);
                borrowALL.setVisible(false);
                ConfirmChoice.setVisible(true);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());


                for (Book Book : flowLibrary.getListOfBooks()) {
                    if(Book.getStatus()==Status.BORROWED && user.getName().equals(userChooseIFrame.getChoosenUserName())) {
                        modifiedModel.addElement(Book.toString());
                    }
                }

                if (checkIfAllBooksReturned(library.getListOfBooks())) {

                    ConfirmChoice.setEnabled(false);
                    returnAll.setEnabled(false);

                    list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null, "No books to return", "Warning", JOptionPane.WARNING_MESSAGE);
                    borrowButtonClicked = false;
                } else {
                    ConfirmChoice.setEnabled(true);
                    list.setModel(modifiedModel);
                    if (informationReturn) {
                        JOptionPane.showMessageDialog(null, "Choose at least one book to return and confirm", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                    informationReturn = false;
                    borrowButtonClicked = false;
                    returnButtonClicked = true;
                }
            }
        });
        Sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(true);
                descendingCheckBox.setVisible(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                sortComboBox.setVisible(true);
                returnAll.setVisible(false);
                borrowALL.setVisible(false);
                ConfirmChoice.setVisible(false);
                list.setEnabled(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
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
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                ConfirmChoice.setEnabled(true);
                if (borrowButtonClicked) {

                    if (list.getSelectedIndex() != -1) {

                        DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                        String titleOfBookToUnassingFromUser = extractTitle(list.getSelectedValue());
                        Book bookInstance=null;
                        for(Book book : flowLibrary.getListOfBooks()){
                            if(book.getTitle().equals(titleOfBookToUnassingFromUser)){
                                bookInstance=book;
                                break;
                            }
                        }


                            int odp = JOptionPane.showConfirmDialog(null, "Do you want to borrow a book: " +   bookInstance.getTitle() + " ?");
                            if (odp == JOptionPane.YES_OPTION) {
                                bookInstance.setStatus(Status.BORROWED);
                                User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());
                                bookInstance.setAssignedUserToBook(user);


                                DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                                for (Book Book : flowLibrary.getListOfBooks()) {
                                    if (Book.getStatus() == Status.AVAILABLE && user.getName().equals(userChooseIFrame.getChoosenUserName())) {
                                        modifiedModel1.addElement(Book.toString());
                                    }
                                }
                                list.setModel(modifiedModel1);
                                JOptionPane.showMessageDialog(null, "Book " + bookInstance.getTitle() + " has been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                                if (checkIfAllBooksBorrowed(flowLibrary.getListOfBooks())) {
                                    borrowALL.setEnabled(false);
                                    ConfirmChoice.setEnabled(false);
                                }

                                }
                             }
                    else {
                        JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (returnButtonClicked) {

                    if (list.getSelectedIndex() != -1) {
                        DefaultListModel<String> modifiedModel = new DefaultListModel<>();


                        User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());

                        String titleOfBookToUnassingFromUser = extractTitle(list.getSelectedValue());
                        Book bookInstance=null;

                        for(Book book : flowLibrary.getListOfBooks()){
                            if(book.getTitle().equals(titleOfBookToUnassingFromUser)){
                                bookInstance=book;
                                break;
                            }
                        }


                        int odp = JOptionPane.showConfirmDialog(null, "Do you want return a book: " + bookInstance.getTitle() + " ?");
                        if (odp == JOptionPane.YES_OPTION) {


                            bookInstance.setStatus(Status.AVAILABLE);
                            bookInstance.setAssignedUserToBook(new User("None","none"));


                            for (Book Book : flowLibrary.getListOfBooks()) {
                                if(Book.getStatus()==Status.BORROWED && user.getName().equals(userChooseIFrame.getChoosenUserName())) {
                                    modifiedModel.addElement(Book.toString());
                                }
                            }
                            list.setModel(modifiedModel);


                            if (checkIfAllBooksReturned(library.getListOfBooks())) {
                                returnAll.setEnabled(false);
                                ConfirmChoice.setEnabled(false);
                            }
                            JOptionPane.showMessageDialog(null, "Book " + bookInstance.getTitle() + " has been returned successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                            booksLabel.setText(flowLibrary.getListOfBooks().size() + " books in " + flowLibrary.getNameOfLibrary() + " library");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

        });
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                sortComboBox.setVisible(false);
                new UserChooseIFrame(userChooseIFrame.getFlowLibrary(), userChooseIFrame.getLibraryManagementFrame());
                setVisible(false);
            }
        });
        changeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                sortComboBox.setVisible(false);
                new LibraryManagementFrame(userChooseIFrame.getLibraryManagementFrame().getLibraryDataBase());
                setVisible(false);
            }
        });
        BorrowAbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                borrowButtonClicked = true;
                ConfirmChoice.setEnabled(true);
                ConfirmChoice.setVisible(true);
                borrowALL.setVisible(true);
                borrowALL.setEnabled(true);
                returnAll.setVisible(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString(
                        ));
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    borrowALL.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books to borrow", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    if (informationBorrow) {
                        JOptionPane.showMessageDialog(null, "Choose at least one book to borrow and confirm", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                    informationBorrow = false;
                    ConfirmChoice.setEnabled(true);
                    borrowButtonClicked = true;
                }
            }

        });
        programInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Program written by Dominik Jakubaszek. \n Version 1.0.1", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        descendingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        ascendingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> listOfAuthorSurnames = new ArrayList<>();

                for (Book book : library.getListOfBooks()) {
                    if (!listOfAuthorSurnames.contains(book.getAuthor().getLastName())) {
                        listOfAuthorSurnames.add(book.getAuthor().getLastName());
                    }
                }

                subcategoriesMap.put("Select", Arrays.asList(" "));
                subcategoriesMap.put("Author", listOfAuthorSurnames);
                subcategoriesMap.put("Genre", Arrays.asList("Przygodowa", "Akcji", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"));

                String selectedCategory = (String) categoryComboBox.getSelectedItem();

                List<String> subcategories = subcategoriesMap.get(selectedCategory);
                String subcategoriesArray[] = subcategories.toArray(new String[subcategories.size()]);


                if (subcategories != null && !selectedCategory.equals("Select")) {
                    SubCategoryComboBox.setEnabled(true);
                    SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
                    SubCategoryComboBox.setVisible(true);
                } else {
                    SubCategoryComboBox.setModel(new DefaultComboBoxModel<>(subcategoriesArray));
                    SubCategoryComboBox.setEnabled(false);
                }
            }

        });
        SubCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                String selectedGenre = (String) SubCategoryComboBox.getSelectedItem();
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if (selectedCategory.equals("Genre")) {
                    for (Book book : flowLibrary.getListOfBooks()) {
                        if (book.getGenre().getName().equals(selectedGenre)) {
                            modifiedModel.addElement(book.toString());
                        }
                    }
                    if (modifiedModel.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "No book meets the criteria Category " + selectedCategory + " and " + selectedGenre, "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        list.setModel(modifiedModel);
                    }
                } else if (selectedCategory.equals("Author")) {

                    for (Book book : flowLibrary.getListOfBooks()) {
                        if (book.getAuthor().getLastName().equals(selectedGenre)) {
                            modifiedModel.addElement(book.toString());
                        }
                    }
                    if (modifiedModel.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "No book meets the criteria Category " + selectedCategory + " and " + selectedGenre, "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        list.setModel(modifiedModel);
                    }
                }
            }
        });
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                List<Book> temporaryList = new ArrayList<>();


                temporaryList.addAll(flowLibrary.getListOfBooks());
                Comparator<Book> comparator = null;
                String selectedSortOption = (String) sortComboBox.getSelectedItem();


                if ("Title".equals(selectedSortOption)) {
                    comparator = Comparator.comparing(Book::getTitle);
                } else if ("Author".equals(selectedSortOption)) {
                    comparator = Comparator.comparing(Book::getAuthor);
                } else if ("Genre".equals(selectedSortOption)) {
                    comparator = Comparator.comparing(Book::getGenre);
                }
                //
                if (!ascendingCheckBox.isSelected() && !descendingCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Choose ascending or descending type and try again", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (comparator != null) {
                        if (ascendingCheckBox.isSelected()) {
                            Collections.sort(temporaryList, comparator);
                        }


                        if (descendingCheckBox.isSelected()) {
                            Collections.sort(temporaryList, comparator.reversed());
                        }

                    }

                    for (Book book : temporaryList) {
                        modifiedModel.addElement(book.toString());
                    }
                    list.setModel(modifiedModel);
                }
            }
        });
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingCheckBox.setVisible(false);
                descendingCheckBox.setVisible(false);
                sortComboBox.setVisible(false);
                categoryComboBox.setVisible(true);
                SubCategoryComboBox.setVisible(true);
                borrowALL.setVisible(false);
                returnAll.setVisible(false);
                list.setEnabled(false);
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();

                if (flowLibrary.getListOfBooks().isEmpty()) {
                    ConfirmChoice.setEnabled(false);
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    ConfirmChoice.setEnabled(false);
                    SubCategoryComboBox.setEnabled(false);
                    categoryComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books to filter in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    ConfirmChoice.setEnabled(false);
                    SubCategoryComboBox.setEnabled(true);
                    categoryComboBox.setEnabled(true);
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                }
            }

        });
        borrowALL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());


                    int odp = JOptionPane.showConfirmDialog(null, "Do you want to borrow all books?");
                    if (odp == JOptionPane.YES_OPTION) {
                        for (Book book : flowLibrary.getListOfBooks()) {
                            if(book.getStatus()!=Status.BORROWED && book.getAssignedUserToBook().getName().equals("None")) {
                                book.setStatus(Status.BORROWED);
                                book.setAssignedUserToBook(user);
                            }
                         //   modifiedModel.addElement(book.toString());
                        }
                       // list.setModel(modifiedModel);
                        JOptionPane.showMessageDialog(null, "All books have been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                        DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                        for (Book Book : flowLibrary.getListOfBooks()) {
                            if (Book.getStatus() == Status.AVAILABLE && user.getName().equals(userChooseIFrame.getChoosenUserName())) {
                                modifiedModel1.addElement(Book.toString());
                            }
                        }
                        list.setModel(modifiedModel1);

                        if (checkIfAllBooksBorrowed(flowLibrary.getListOfBooks())) {
                            borrowALL.setEnabled(false);
                            ConfirmChoice.setEnabled(false);
                        }
                    }
            }
        });
        returnAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel<String> modifiedModel = new DefaultListModel<>();


                User user = flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChooseIFrame.getChoosenUserName());

                int odp = JOptionPane.showConfirmDialog(null, "Do you want to return all books?");
                if (odp == JOptionPane.YES_OPTION) {
                    for (Book book : flowLibrary.getListOfBooks()) {
                        if (book.getStatus() != Status.AVAILABLE && book.getAssignedUserToBook().getName().equals(userChooseIFrame.getChoosenUserName())) {
                            book.setStatus(Status.AVAILABLE);
                            book.setAssignedUserToBook(new User("None", "none"));
                        }
                     //   modifiedModel.addElement(book.toString());
                    }
                  //  list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null, "All books have been borrowed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                    DefaultListModel<String> modifiedModel1 = new DefaultListModel<>();
                    for (Book Book : flowLibrary.getListOfBooks()) {
                        if (Book.getStatus() == Status.BORROWED && user.getName().equals(userChooseIFrame.getChoosenUserName())) {
                            modifiedModel1.addElement(Book.toString());
                        }
                    }
                    list.setModel(modifiedModel1);

                    if (checkIfAllBooksReturned(flowLibrary.getListOfBooks())) {
                        ConfirmChoice.setEnabled(false);
                        returnAll.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(180, 20, 500, 150);
        add(scrollPane);


        setSize(700, 400);
        setTitle("User Action Panel logged as " + userChooseIFrame.getChoosenUserName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        ascendingCheckBox.setVisible(false);
        descendingCheckBox.setVisible(false);
        sortComboBox.setVisible(false);
        categoryComboBox.setVisible(false);
        SubCategoryComboBox.setVisible(false);

    }


    public boolean checkIfAllBooksBorrowed( List<Book> books){

        for(Book book : books){
            if(book.getStatus()==Status.AVAILABLE){
                return false;
            }
        }
        return true;
    }

    public boolean checkIfAllBooksReturned( List<Book> books){

        for(Book book : books){
            if(book.getStatus()==Status.BORROWED){
                return false;
            }
        }
        return true;
    }

    public  String extractTitle(String inputString) {

        String[] parts = inputString.split(", ");
        for (String part : parts) {
            if (part.startsWith("Title:")) {
                String[] titleParts = part.split(": ");
                return titleParts[1];
            }
        }
        return null;
    }
}
