package Frames;

import org.example.LibraryManager.Book;
import org.example.LibraryManager.Genre;
import org.example.LibraryManager.Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

public class OverViewBookJFrame extends JFrame {
    private Library library;
    private JList list;
    private CalendarIFrame calendar;

    public JTextField getAuthorBirthDateField() {
        return authorBirthDateField;
    }

    private JTextField authorBirthDateField = new JTextField();
    private JTextField titleField = new JTextField();
    private JTextField authorFirstNameField = new JTextField();
    private JTextField authorLastNameField = new JTextField();
    private JTextField yearField = new JTextField();
    private JComboBox<String> genreComboBox = new JComboBox<>(new String[]{"Przygodowa", "Akcji", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"});
    private JTextField pagesField = new JTextField();

    public OverViewBookJFrame(Library library, JList list) {

        this.library = library;
        this.list = list;


        JButton button = new JButton("Select");

        button.setBounds(180, 130, 90, 25);
        add(button);
        button.setEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar = new CalendarIFrame(button, OverViewBookJFrame.this);

            }
        });


        titleField.setBounds(80, 15, 200, 25);
        authorFirstNameField.setBounds(80, 55, 200, 25);
        authorLastNameField.setBounds(80, 90, 200, 25);
        authorBirthDateField.setBounds(80, 130, 90, 25);
        authorBirthDateField.setEditable(false);
        yearField.setBounds(80, 170, 200, 25);
        genreComboBox.setBounds(80, 210, 200, 25);
        pagesField.setBounds(80, 250, 200, 25);

        int indexToUpdate = list.getSelectedIndex();
        int counter = 0;
        for (Book book : library.getListOfBooks()) {
            if (counter == indexToUpdate) {
                titleField.setText(book.getTitle());
                authorFirstNameField.setText(book.getAuthor().getFirstName());
                authorLastNameField.setText(book.getAuthor().getLastName());
                authorBirthDateField.setText(book.getAuthor().getDateOfBirth());
                yearField.setText(String.valueOf(book.getDateOfProduction()));
                genreComboBox.setSelectedItem(book.getGenre().getName());
                pagesField.setText(String.valueOf(book.getAmountOfPage()));
            }
            counter++;
        }

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


        titleField.setEditable(false);
        authorFirstNameField.setEditable(false);
        authorLastNameField.setEditable(false);
        authorBirthDateField.setEditable(false);
        yearField.setEditable(false);
        genreComboBox.setEnabled(false);
        pagesField.setEditable(false);


        setSize(300, 400);
        setTitle("Qucik overview");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

}
