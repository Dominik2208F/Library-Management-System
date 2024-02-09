package Frames;

import org.example.LibraryManager.Author;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Genre;
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


public class AdminActionFrame extends JFrame {

    JMenuBar menubar;
    JMenu Options,Program;
    JMenuItem changeUser, changeLibrary,programInfo;
    JButton AddBook,ReturnInfoAllBooks,ReturnInfoOfBook,DeleteBook,UpdateBook,AddUser,DeleteUser,ReturnBookOfAGivenUser,BorrowedBooksOfUser,ConfirmChoice;
    JList<String> list;
    Library flowLibrary;
    UserChooseIFrame userChooseIFrame;
    JLabel booksLabel;

    boolean deleteBookClicked=false;
    boolean updateBookClicked=false;

    private boolean informationUpdate= true;
    private boolean informationDelete=true;
    private JComboBox<String> categoryComboBox,SubCategoryComboBox,UserSelectionComboBox;

    public AdminActionFrame(UserChooseIFrame userChooseIFrame, Library library) {

        this.flowLibrary = library;
        this.userChooseIFrame = userChooseIFrame;

        DefaultListModel<String> listOfAction = new DefaultListModel<>();
        list = new JList<>(listOfAction);
        list.setBounds(180, 20, 500, 130);
        add(list);

        Map<String, List<String>> subcategoriesMap = new HashMap<>();

        menubar= new JMenuBar();
        Options= new JMenu("Options");
        Program= new JMenu("Info");
        changeUser= new JMenuItem("Change user");
        changeLibrary =new JMenuItem("Change library");
        programInfo =new JMenuItem("About");
        Options.add(changeUser);
        Options.add(changeLibrary);
        Program.add(programInfo);

        setJMenuBar(menubar);
        menubar.add(Options);
        menubar.add(Program);


        booksLabel= new JLabel(flowLibrary.getListOfBooks().size() +" books in "  + flowLibrary.getNameOfLibrary() + " library");
        booksLabel.setBounds(350, -5, 200, 30);
        add(booksLabel);

        AddBook = new JButton("Add a book");
        AddBook.setBounds(40, 20, 130, 35);
        add(AddBook);

        ReturnInfoAllBooks = new JButton("Show all books");
        ReturnInfoAllBooks.setBounds(40, 60, 130, 35);
        add(ReturnInfoAllBooks);

        DeleteBook = new JButton("Delete book");
        DeleteBook.setBounds(40, 100, 130, 35);
        add(DeleteBook);

        ConfirmChoice= new JButton("Confirm Choice");
        ConfirmChoice.setBounds(500, 180, 130, 35);
        add(ConfirmChoice);
        ConfirmChoice.setVisible(false);
        ConfirmChoice.setBackground(Color.PINK);

        UpdateBook = new JButton("Update book");
        UpdateBook.setBounds(40, 140, 130, 35);
        add(UpdateBook);

        AddUser = new JButton("Add user");
        AddUser.setBounds(40, 180, 130, 35);
        add(AddUser);

        DeleteUser = new JButton("Delete user");
        DeleteUser.setBounds(40, 220, 130, 35);
        add(DeleteUser);


        ReturnBookOfAGivenUser = new JButton("Return book of a given user");
        ReturnBookOfAGivenUser.setBounds(40, 260, 200, 35);
        add(ReturnBookOfAGivenUser);

        BorrowedBooksOfUser = new JButton("User borrowed books");
        BorrowedBooksOfUser.setBounds(40, 300, 200, 35);
        add(BorrowedBooksOfUser);

        categoryComboBox = new JComboBox<>(new String[]{"Author", "Genre","Select"});
        categoryComboBox.setBounds(500,200,160,30);
        add(categoryComboBox);
        categoryComboBox.setSelectedItem("Select");

        SubCategoryComboBox = new JComboBox<>();
        SubCategoryComboBox.setBounds(500,240,160,30);
        add(SubCategoryComboBox);


        UserSelectionComboBox =new JComboBox<>();
        UserSelectionComboBox.setBounds(500,180,160,30);
        add(UserSelectionComboBox);

        categoryComboBox.setVisible(false);
        SubCategoryComboBox.setVisible(false);
        UserSelectionComboBox.setVisible(false);

        ReturnBookOfAGivenUser.setEnabled(false);
        BorrowedBooksOfUser.setEnabled(false);


        DeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                UserSelectionComboBox.setVisible(true);
                ConfirmChoice.setVisible(false);
                if (flowLibrary.getLibraryUserDataBase().getListOfUser().size()==1) {
                    UserSelectionComboBox.setEnabled(false);
                    UserSelectionComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"No users"}));
                    JOptionPane.showMessageDialog(null,
                            "No additional users assigned to library", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    List<String> listOfUserName = new ArrayList<>();
                    listOfUserName.add("Select");
                    for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
                        if(!user.getName().equals("Admin")) {
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

                List<String> listOfAuthorSurnames= new ArrayList<>();

                for(Book book : library.getListOfBooks()){
                    if(!listOfAuthorSurnames.contains(book.getAuthor().getLastName())) {
                        listOfAuthorSurnames.add(book.getAuthor().getLastName());
                    }
                }

                subcategoriesMap.put("Select", Arrays.asList(" "));
                subcategoriesMap.put("Author", listOfAuthorSurnames);
                subcategoriesMap.put("Genre", Arrays.asList("Przygodowa", "Akcji", "ScienceFiction","Romans","Historyczne","Akademickie","Finansowe","Dramat"));

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
                String selectedGenre =  (String)  SubCategoryComboBox.getSelectedItem();
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if(selectedCategory.equals("Genre")){
                    for(Book book : flowLibrary.getListOfBooks()){
                        if(book.getGenre().getName().equals(selectedGenre)){
                            modifiedModel.addElement(book.toString());
                        }
                    }
                    if(modifiedModel.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "No book meets the criteria Category "+selectedCategory + " and " +selectedGenre, "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        list.setModel(modifiedModel);
                    }
                }
                else if(selectedCategory.equals("Author")){

                    for(Book book : flowLibrary.getListOfBooks()){
                        if(book.getAuthor().getLastName().equals(selectedGenre)){
                            modifiedModel.addElement(book.toString());
                        }
                    }
                    if(modifiedModel.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "No book meets the criteria Category "+selectedCategory + " and " +selectedGenre, "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        list.setModel(modifiedModel);
                    }
                }
            }
        });
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UserChooseIFrame(userChooseIFrame.flowLibrary, userChooseIFrame.libraryManagementFrame);
                setVisible(false);
            }
        });
        changeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LibraryManagementFrame(userChooseIFrame.libraryManagementFrame.libraryDataBase);
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
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    SubCategoryComboBox.setEnabled(false);
                    categoryComboBox.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "No books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    SubCategoryComboBox.setEnabled(true);
                    categoryComboBox.setEnabled(true);
                    list.setModel(modifiedModel);
                    list.setEnabled(false);
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
                        int odp = JOptionPane.showConfirmDialog(null,"Do you want to remove a book: " + flowLibrary.getListOfBooks().get(selectedBookIndexToRemove).getTitle()+ " ?");

                        if(odp==JOptionPane.YES_OPTION){

                        //Remove
                        flowLibrary.getListOfBooks().remove(selectedBookIndexToRemove);
                        for(Book book : flowLibrary.getListOfBooks()){
                            listOfBooksToIterateThrough.add(book.toString());
                        }

                            String BooksAfterRemove[] = listOfBooksToIterateThrough.toArray(new String[listOfBooksToIterateThrough.size()]);
                            list.setModel(new DefaultComboBoxModel<>(BooksAfterRemove));
                            booksLabel.setText(flowLibrary.getListOfBooks().size() + " books in "  + flowLibrary.getNameOfLibrary() + " library");
                        }
                        if(flowLibrary.getListOfBooks().isEmpty()){
                            ConfirmChoice.setEnabled(false);
                            JOptionPane.showMessageDialog(null,
                                    "No book to delete in " + flowLibrary.getNameOfLibrary(), "Message", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                     }

                    if (updateBookClicked) {
                        if(list.getSelectedIndex()!=-1) {
                            UpdateBookJFrame updateBookJFrame = new UpdateBookJFrame(library, list);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Choose at least one book", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
            }
        });
        DeleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookClicked=false;
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                UserSelectionComboBox.setVisible(false);
                ConfirmChoice.setVisible(true);
                list.setEnabled(true);

                if (flowLibrary.getListOfBooks().isEmpty()) {
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "No book to delete in " + flowLibrary.getNameOfLibrary(), "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(informationDelete) {
                        JOptionPane.showMessageDialog(null,
                                "Choose at least one book from list and click confirm", "Message", JOptionPane.INFORMATION_MESSAGE);
                        informationDelete = false;
                    }
                    ConfirmChoice.setEnabled(true);
                    deleteBookClicked=true;
                    DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
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

                    List<User> arrayCopyToIterate =  new ArrayList<>(flowLibrary.getLibraryUserDataBase().getListOfUser());

                    for (User user : arrayCopyToIterate) {
                        if (user.getName().equals(selectedUserToDelete)) {
                            int odp=  JOptionPane.showConfirmDialog(null, "Do you want to remove " + user.getName() + " ?" , "Message", JOptionPane.YES_NO_OPTION);
                            if(odp==JOptionPane.YES_OPTION) {
                                flowLibrary.getLibraryUserDataBase().getListOfUser().remove(user);
                                List<String> UpdatedListOfUsers =new ArrayList<>();
                                for (User user1 : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
                                    if (!user1.getName().equals("Admin")) {
                                        UpdatedListOfUsers.add(user1.getName());
                                    }
                                }
                                if(UpdatedListOfUsers.isEmpty()){
                                    UserSelectionComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"No users"}));
                                    UserSelectionComboBox.setEnabled(false);
                                }
                                else {
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
                AddUserFrame addUserFrame= new AddUserFrame(userChooseIFrame,library,true);
                addUserFrame.setVisible(true);
            }
        });
        UpdateBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBookClicked=false;
                list.setEnabled(true);
                categoryComboBox.setVisible(false);
                SubCategoryComboBox.setVisible(false);
                UserSelectionComboBox.setVisible(false);
                ConfirmChoice.setVisible(true);

                if (flowLibrary.getListOfBooks().isEmpty()) {
                    ConfirmChoice.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "No book to update " + flowLibrary.getNameOfLibrary(), "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    updateBookClicked=true;
                  if(informationUpdate) {
                      JOptionPane.showMessageDialog(null,
                              "Choose at least one book from list and click confirm", "Message", JOptionPane.INFORMATION_MESSAGE);
                      informationUpdate = false;
                  }
                    DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                        list.setModel(modifiedModel);
                    }
                }
            }
        });
        AddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.setEnabled(false);
                DefaultListModel<String> updatedModel = new DefaultListModel<>();
                for (Book books : flowLibrary.getListOfBooks()) {
                    updatedModel.addElement(books.toString());
                }
                SubCategoryComboBox.setEnabled(true);
                categoryComboBox.setEnabled(true);
                list.setModel(updatedModel);
                AddBookJFrame addBookJFrame = new AddBookJFrame(flowLibrary,list);
            }
        });
        programInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Program written by Dominik Jakubaszek. \n Version 1.0.1", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JScrollPane scrollPane = new JScrollPane(list); // Wrap the JList in a JScrollPane
        scrollPane.setBounds(180, 20, 500, 130);
        add(scrollPane);

        setSize(700, 500);
        setTitle("Admin Panel logged as " + userChooseIFrame.ChoosenUserName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        ImageIcon backgroundImage = new ImageIcon("src/programmer_v_02.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
        setLayout(null);

    }

}
