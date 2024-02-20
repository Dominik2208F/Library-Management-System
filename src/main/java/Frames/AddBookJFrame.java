package Frames;

import Manager.AdminQueries;
import Manager.LengthRestrictedDocument;
import Manager.Queries;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Library;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.regex.Pattern;


public class AddBookJFrame extends JFrame {

    private Library flowLibrary;
    public JList fromPanelAbove;
    private CalendarIFrame calendar;
    private JTextField titleField = new JTextField();
    private JTextField authorFirstNameField = new JTextField();
    private JTextField authorLastNameField = new JTextField();

    public JTextField getAuthorBirthDateField() {
        return authorBirthDateField;
    }

    private JTextField authorBirthDateField = new JTextField();
    private JTextField yearField = new JTextField();
    private JTextField pagesField = new JTextField();
    private JComboBox<String> genreComboBox = new JComboBox<>(new String[]{"Przygodowa", "Akcji", "ScienceFiction", "Romans", "Historyczne", "Akademickie", "Finansowe", "Dramat"});
    private  JLabel BooksLebel;
    private UserChooseIFrame userChooseIFrame;

    public AddBookJFrame(Library flowLibrary, JList<String> list, JLabel book, UserChooseIFrame userChooseIFrame) {

    this.BooksLebel=book;
    this.userChooseIFrame=userChooseIFrame;
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


        JLabel imageAddBookLabel= new JLabel(setIcon("/notebook (1).png"));
        imageAddBookLabel.setBounds(100, 10, 200, 65);
        add(imageAddBookLabel);
        JButton addBook = new JButton("Add book");
        JButton select = new JButton("Select");


        select.setBounds(200, 240, 100, 40);
        add(select);

        this.flowLibrary = flowLibrary;
        this.fromPanelAbove = list;

        titleField.setBounds(100, 90, 200, 40);
        authorFirstNameField.setBounds(100, 140, 200, 40);
        authorLastNameField.setBounds(100, 190, 200, 40);
        authorBirthDateField.setBounds(100, 240,  90, 40);
        authorBirthDateField.setEditable(false);
        yearField.setBounds(100, 290, 200, 40);
        genreComboBox.setBounds(100, 340, 200, 40);
        pagesField.setBounds(100, 390, 200, 40);
        titleField.setDocument(new LengthRestrictedDocument(40));
        authorFirstNameField.setDocument(new LengthRestrictedDocument(40));
        authorLastNameField.setDocument(new LengthRestrictedDocument(40));
        authorBirthDateField.setDocument(new LengthRestrictedDocument(40));
        yearField.setDocument(new LengthRestrictedDocument(4));
        pagesField.setDocument(new LengthRestrictedDocument(6));

        add(new JLabel("Title:")).setBounds(15, 85, 100, 35);
        add(titleField);
        add(new JLabel("First Name:")).setBounds(15, 135, 100, 35);
        add(authorFirstNameField);
        add(new JLabel("Last Name:")).setBounds(15, 185, 100, 35);
        add(authorLastNameField);
        add(new JLabel("Birth Date:")).setBounds(15, 235, 100, 35);
        add(authorBirthDateField);
        add(new JLabel("Year:")).setBounds(15, 285, 100, 35);
        add(yearField);
        add(new JLabel("Genre:")).setBounds(15, 335, 100, 35);
        add(genreComboBox);
        add(new JLabel("Pages:")).setBounds(15, 385, 100, 35);
        add(pagesField);
        addBook.setBounds(115, 440, 150, 40);
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

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar = new CalendarIFrame(select, AddBookJFrame.this);

            }
        });
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook(fromPanelAbove);
            }
        });
        setSize(400, 550);
        setTitle("Add book");
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

        LocalDate currentDate = LocalDate.now();
        int yearCurrent = currentDate.getYear();

        if (title.isEmpty() || authorFirstName.isEmpty() || authorLastName.isEmpty() || authorBirthDate.isEmpty() ||
                yearText.isEmpty() || description.isEmpty() || pagesText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String regex ="^[a-zA-Z][a-zA-Z ]*$";
            String regexSurname= "^[a-zA-Z]+$";
            if (!Pattern.matches(regex, authorFirstName)) {
                JOptionPane.showMessageDialog(null, "Invalid format. Name cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Pattern.matches(regexSurname, authorLastName)) {
                JOptionPane.showMessageDialog(null, "Invalid format. Surname cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
            }


            int year = 0;
            int pages = 0;
            try {
                year = Integer.parseInt(yearField.getText());
                if (year <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid year format. Year cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(year > yearCurrent){
                    JOptionPane.showMessageDialog(null, "Invalid year format. Year cannot be more than " +yearCurrent, "Error", JOptionPane.ERROR_MESSAGE);
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

            if (pages != 0 && year != 0 && Pattern.matches(regex, authorFirstName) && Pattern.matches(regexSurname, authorLastName)) {


                AdminQueries.addBook(title,authorFirstName,authorLastName,authorBirthDate,yearText,pagesText,"AVAILABLE","null",userChooseIFrame.getLibraryManagementFrame().getSelectedLibrary(),description,false);
                JOptionPane.showMessageDialog(null, "Book added successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                DefaultListModel<String> updatedModel = new DefaultListModel<>();
                Queries.getAllAvailableBook(userChooseIFrame.getLibraryManagementFrame().getSelectedLibrary()).stream()
                        .map(String::toString)
                        .forEach(updatedModel::addElement);

                list.setModel(updatedModel);
                dispose();
            }
        }
    }
    public ImageIcon setIcon(String source){
        URL imageUrl = getClass().getResource(source);

        ImageIcon icon=null;
        if (imageUrl != null) {
            try (InputStream inputStream = imageUrl.openStream()) {

                Image originalImage = ImageIO.read(inputStream);

                icon = new ImageIcon(originalImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Nie udało się znaleźć zasobu.");
        }
        return icon;
    }

}
