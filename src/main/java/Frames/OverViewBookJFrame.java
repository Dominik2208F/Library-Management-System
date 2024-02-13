package Frames;

import org.example.LibraryManager.Book;
import org.example.LibraryManager.Genre;
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

        JButton button = new JButton("Select");
        button.setBounds(200, 240, 100, 40);
        add(button);
        button.setEnabled(false);
        JLabel imageAddBookLabel= new JLabel(setIcon("/research.png"));
        imageAddBookLabel.setBounds(100, 10, 200, 65);
        add(imageAddBookLabel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar = new CalendarIFrame(button, OverViewBookJFrame.this);

            }
        });


        titleField.setBounds(100, 90, 200, 40);
        authorFirstNameField.setBounds(100, 140, 200, 40);
        authorLastNameField.setBounds(100, 190, 200, 40);
        authorBirthDateField.setBounds(100, 240,  90, 40);
        authorBirthDateField.setEditable(false);
        yearField.setBounds(100, 290, 200, 40);
        genreComboBox.setBounds(100, 340, 200, 40);
        pagesField.setBounds(100, 390, 200, 40);

       String TitleToUpdate = extractTitle((String) list.getSelectedValue());



        for (Book book : library.getListOfBooks()) {
            if (book.getTitle().equals(TitleToUpdate)) {
                titleField.setText(book.getTitle());
                authorFirstNameField.setText(book.getAuthor().getFirstName());
                authorLastNameField.setText(book.getAuthor().getLastName());
                authorBirthDateField.setText(book.getAuthor().getDateOfBirth());
                yearField.setText(String.valueOf(book.getDateOfProduction()));
                genreComboBox.setSelectedItem(book.getGenre().getName());
                pagesField.setText(String.valueOf(book.getAmountOfPage()));
            }

        }


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


        titleField.setEditable(false);
        authorFirstNameField.setEditable(false);
        authorLastNameField.setEditable(false);
        authorBirthDateField.setEditable(false);
        yearField.setEditable(false);
        genreComboBox.setEnabled(false);
        pagesField.setEditable(false);


        setSize(400, 550);
        setTitle("Qucik overview");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
    public String extractTitle(String inputString) {
        String[] parts = inputString.split(", ");
        for (String part : parts) {
            if (part.startsWith("Title:")) {
                String[] titleParts = part.split(": ");
                return titleParts[1];
            }
        }
        return null;
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
