package Frames;

import org.example.LibraryManager.Author;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Genre;
import org.example.LibraryManager.Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddBookJFrame extends JFrame {

    Library flowLibrary;
    JList fromPanelAbove;
    public AddBookJFrame(Library flowLibrary, JList<String> list) {
        JTextField titleField = new JTextField();
        JTextField authorFirstNameField = new JTextField();
        JTextField authorLastNameField = new JTextField();
        JTextField authorBirthDateField = new JTextField();
        JTextField yearField = new JTextField();
        JComboBox<String> genreComboBox = new JComboBox<>(new String[]{"Przygodowa", "Akcji", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"});
        JTextField pagesField = new JTextField();
        JButton addBook  = new JButton("Add book");

        this.flowLibrary= flowLibrary;
        this.fromPanelAbove=list;

        titleField.setBounds(80,15,200,25);
        authorFirstNameField.setBounds(80,55,200,25);
        authorLastNameField.setBounds(80,90,200,25);
        authorBirthDateField.setBounds(80,130,200,25);
        yearField.setBounds(80,170,200,25);
        genreComboBox.setBounds(80,210,200,25);
        pagesField.setBounds(80,250,200,25);

        add(new JLabel("Title:")).setBounds(0,5,100,35);
        add(titleField);
        add(new JLabel("First Name:")).setBounds(0,45,100,35);
        add(authorFirstNameField);
        add(new JLabel("Last Name:")).setBounds(0,80,100,35);
        add(authorLastNameField);
        add(new JLabel("Birth Date:")).setBounds(0,120,100,35);
        add(authorBirthDateField);
        add(new JLabel("Year:")).setBounds(0,160,100,35);
        add(yearField);
        add(new JLabel("Genre:")).setBounds(0,200,100,35);
        add(genreComboBox);
        add(new JLabel("Pages:")).setBounds(0,240,100,35);
        add(pagesField);
        addBook.setBounds(90,300,100,35);
        add(addBook);

        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String authorFirstName = authorFirstNameField.getText();
                String authorLastName = authorLastNameField.getText();
                String authorBirthDate = authorBirthDateField.getText();

                String yearText = yearField.getText();
                String description = (String) genreComboBox.getSelectedItem();

                String pagesText = pagesField.getText();

                    if (title.isEmpty() || authorFirstName.isEmpty() || authorLastName.isEmpty() ||
                            authorBirthDate.isEmpty() || yearText.isEmpty() || description.isEmpty() || pagesText.isEmpty()) {
                     JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                        int year =0;
                        int pages=0;
                        try {
                            year = Integer.parseInt(yearField.getText());
                        }catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Invalid year format. Type a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            pages = Integer.parseInt(pagesField.getText());
                        }catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Invalid pages format. Type a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if(pages!=0 && year!=0) {
                           // Tworzenie nowego obiektu książki
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
        });
        setSize(300,400);
        setTitle("Add new book");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
}
