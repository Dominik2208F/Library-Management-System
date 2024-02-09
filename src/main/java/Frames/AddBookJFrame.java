package Frames;

import org.example.LibraryManager.Author;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Genre;
import org.example.LibraryManager.Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;


public class AddBookJFrame extends JFrame {

    Library flowLibrary;
    public JList fromPanelAbove;
    CalendarIFrame calendar;
    JTextField titleField = new JTextField();
    JTextField authorFirstNameField = new JTextField();
    JTextField authorLastNameField = new JTextField();
    public JTextField authorBirthDateField = new JTextField();
    JTextField yearField = new JTextField();
    JTextField pagesField = new JTextField();
    JComboBox<String> genreComboBox = new JComboBox<>(new String[]{"Przygodowa", "Akcji", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"});

    public AddBookJFrame(Library flowLibrary, JList<String> list) {

        JButton addBook = new JButton("Add book");
        JButton button = new JButton("Select");


        button.setBounds(180, 130, 90, 25);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar = new CalendarIFrame(button, AddBookJFrame.this);

            }
        });

        this.flowLibrary = flowLibrary;
        this.fromPanelAbove = list;

        titleField.setBounds(80, 15, 200, 25);
        authorFirstNameField.setBounds(80, 55, 200, 25);
        authorLastNameField.setBounds(80, 90, 200, 25);
        authorBirthDateField.setBounds(80, 130, 90, 25);
        authorBirthDateField.setEditable(false);
        yearField.setBounds(80, 170, 200, 25);
        genreComboBox.setBounds(80, 210, 200, 25);
        pagesField.setBounds(80, 250, 200, 25);

        add(new JLabel("Title:")).setBounds(0, 5, 100, 35);
        add(titleField);
        add(new JLabel("First Name:")).setBounds(0, 45, 100, 35);
        add(authorFirstNameField);
        add(new JLabel("Last Name:")).setBounds(0, 80, 100, 35);
        add(authorLastNameField);
        add(new JLabel("Birth Date:")).setBounds(0, 120, 100, 35);
        add(authorBirthDateField);
        add(new JLabel("Year:")).setBounds(0, 160, 100, 35);
        add(yearField);
        add(new JLabel("Genre:")).setBounds(0, 200, 100, 35);
        add(genreComboBox);
        add(new JLabel("Pages:")).setBounds(0, 240, 100, 35);
        add(pagesField);
        addBook.setBounds(90, 300, 100, 35);
        add(addBook);
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AddBook(fromPanelAbove);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        titleField.addKeyListener(keyListener);
        authorFirstNameField.addKeyListener(keyListener);
        authorLastNameField.addKeyListener(keyListener);
        authorBirthDateField.addKeyListener(keyListener);
        yearField.addKeyListener(keyListener);
        genreComboBox.addKeyListener(keyListener);
        pagesField.addKeyListener(keyListener);

        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook(fromPanelAbove);
            }
        });

        setSize(300, 400);
        setTitle("Add new book");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    public void AddBook(JList list) {
        String title = titleField.getText();
        String authorFirstName = authorFirstNameField.getText();
        String authorLastName = authorLastNameField.getText();


        String authorBirthDate = authorBirthDateField.getText();
        String yearText = yearField.getText();
        String description = (String) genreComboBox.getSelectedItem();

        String pagesText = pagesField.getText();

        if (title.isEmpty() || authorFirstName.isEmpty() || authorLastName.isEmpty() || authorBirthDate.isEmpty() ||
                yearText.isEmpty() || description.isEmpty() || pagesText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String regex = "^[a-zA-Z]+$";
            if (!Pattern.matches(regex, authorFirstName)) {
                JOptionPane.showMessageDialog(null, "Invalid format. Name cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Pattern.matches(regex, authorLastName)) {
                JOptionPane.showMessageDialog(null, "Invalid format. Surname cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
            }

            int year = 0;
            int pages = 0;
            try {
                year = Integer.parseInt(yearField.getText());
                if (year <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid year format. Year cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid year format. Type a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                pages = Integer.parseInt(pagesField.getText());
                if (pages <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid pages format. Pages cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid pages format. Type a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (pages != 0 && year != 0 && Pattern.matches(regex, authorFirstName) && Pattern.matches(regex, authorLastName)) {

                Author author = new Author(authorFirstName, authorLastName, authorBirthDate);
                Genre genre = new Genre(description);
                Book newBook = new Book(title, author, year, genre, pages);
                flowLibrary.getListOfBooks().add(newBook);

                DefaultListModel<String> updatedModel = new DefaultListModel<>();
                for (Book book : flowLibrary.getListOfBooks()) {
                    updatedModel.addElement(book.toString());
                }
                list.setModel(updatedModel);
                dispose();
            }
        }
    }
}
