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


public class AdminActionFrame extends JFrame {

    JMenuBar menubar;
    JMenu Options,Program;
    JMenuItem changeUser, changeLibrary,programInfo;
    JButton AddBook,ReturnInfoAllBooks,ReturnInfoOfBook,DeleteBook,UpdateBook,AddUser,DeleteUser,ReturnBookOfAGivenUser,BorrowedBooksOfUser;
    JList<String> list;
    Library flowLibrary;
    UserChooseIFrame userChooseIFrame;
    JCheckBox ascendingCheckBox;
    JCheckBox descendingCheckBox;


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


        AddBook = new JButton("Add a book");
        AddBook.setBounds(40, 20, 130, 35);
        add(AddBook);


        ReturnInfoAllBooks = new JButton("Get all Books");
        ReturnInfoAllBooks.setBounds(40, 60, 130, 35);
        add(ReturnInfoAllBooks);

        ReturnInfoAllBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> modifiedModel = new DefaultListModel<>();
                if (flowLibrary.getListOfBooks().isEmpty()) {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                    JOptionPane.showMessageDialog(null, "No books in library", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (Book books : flowLibrary.getListOfBooks()) {
                        modifiedModel.addElement(books.toString());
                    }
                    list.setModel(modifiedModel);
                }
            }
        });

        ReturnInfoOfBook = new JButton("Get book info");
        ReturnInfoOfBook.setBounds(40, 100, 130, 35);
        add(ReturnInfoOfBook);

        DeleteBook = new JButton("Delete book");
        DeleteBook.setBounds(40, 140, 130, 35);
        add(DeleteBook);

        UpdateBook = new JButton("Update book");
        UpdateBook.setBounds(40, 180, 130, 35);
        add(UpdateBook);

        AddUser = new JButton("Add user");
        AddUser.setBounds(40, 220, 130, 35);
        add(AddUser);

        DeleteUser = new JButton("Delete user");
        DeleteUser.setBounds(40, 260, 130, 35);
        add(DeleteUser);

        ReturnBookOfAGivenUser = new JButton("Return book of a given user");
        ReturnBookOfAGivenUser.setBounds(40, 300, 200, 35);
        add(ReturnBookOfAGivenUser);

        BorrowedBooksOfUser = new JButton("User borrowed books");
        BorrowedBooksOfUser.setBounds(40, 340, 200, 35);
        add(BorrowedBooksOfUser);


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




        JScrollPane scrollPane = new JScrollPane(list); // Wrap the JList in a JScrollPane
        scrollPane.setBounds(180, 20, 500, 130);
        add(scrollPane);

        setSize(700, 600);
        setTitle("Admin Panel logged as " + userChooseIFrame.ChoosenUserName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        ImageIcon backgroundImage = new ImageIcon("src/admin.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
        setLayout(null);

    }

}
