package Frames;

import Manager.AdminQueries;
import Manager.CommonFunctions;
import Manager.LengthRestrictedDocument;
import Manager.Queries;
import org.example.LibraryManager.Book;
import org.example.LibraryManager.Genre;
import org.example.LibraryManager.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.time.Year;
import java.util.regex.Pattern;

public class UpdateBookJFrame extends JFrame implements CommonFunctions {
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
    private UserChooseIFrame userChooseIFrame;

    public UpdateBookJFrame(JList<String> list, UserChooseIFrame userChooseIFrame) {

        this.list = list;
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

        JButton updateBook = new JButton("Update book");


        JButton button = new JButton("Select");


        button.setBounds(200, 240, 100, 40);
        add(button);

        JLabel imageAddBookLabel= new JLabel(setIcon("/phonebook.png"));
        imageAddBookLabel.setBounds(100, 10, 200, 65);
        add(imageAddBookLabel);

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


        String TitleToUpdate = extractTitle((String) list.getSelectedValue());


        ResultSet resultSet= Queries.getQucikViewInfo(userChooseIFrame.getLibraryManagementFrame().getSelectedLibrary(),TitleToUpdate);


        String dateOfBirth = null;
        String title = null;
        String authorFirstName = null;
        String authorLastName = null;
        String yearOfProduction = null;
        String genre = null;
        String pages = null;
        try {
            while (resultSet.next()) {

                dateOfBirth = resultSet.getString("date_of_birth");
                title = resultSet.getString("title");
                authorFirstName = resultSet.getString("first_name");
                authorLastName=resultSet.getString("last_name");
                yearOfProduction = resultSet.getString("yearofproduction");
                genre = resultSet.getString("name");
                pages = resultSet.getString("pages");




            }
        }catch (Exception e) {

        }


            titleField.setText(title);
            authorFirstNameField.setText(authorFirstName);
            authorLastNameField.setText(authorLastName);
            authorBirthDateField.setText(dateOfBirth);
            yearField.setText(String.valueOf(yearOfProduction));
            genreComboBox.setSelectedItem(genre);
            pagesField.setText(String.valueOf(pages));

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
        updateBook.setBounds(115, 440, 150, 40);
        add(updateBook);


        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String titleOfBookToUpdate = extractTitle(list.getSelectedValue());
                    updateBookByTitle(titleOfBookToUpdate);
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

        updateBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String titleOfBookToUpdate = extractTitle(list.getSelectedValue());

                updateBookByTitle(titleOfBookToUpdate);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar = new CalendarIFrame(button, UpdateBookJFrame.this);

            }
        });


        setSize(400, 550);
        setTitle("Update book");
        setLayout(null);
        setResizable(false);
        setVisible(true);


    }
    public void updateBookByTitle(String titleToUpdate) {



        String titleText = titleField.getText();
        String authorFirstNameText = authorFirstNameField.getText();
        String authorLastNameText = authorLastNameField.getText();
        String authorBirthDateText = authorBirthDateField.getText();

        String yearText = yearField.getText();
        String description = (String) genreComboBox.getSelectedItem();

        String pagesText = pagesField.getText();

        if (titleText.isEmpty() || authorFirstNameText.isEmpty() || authorLastNameText.isEmpty() ||
                authorBirthDateText.isEmpty() || yearText.isEmpty() || description.isEmpty() || pagesText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String regex = "^[a-zA-Z][a-zA-Z ]*$";
            String regexSurname = "^[a-zA-Z]+$";
            if (!Pattern.matches(regex, authorFirstNameText)) {
                JOptionPane.showMessageDialog(null, "Invalid format. Name cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Pattern.matches(regexSurname, authorLastNameText)) {
                JOptionPane.showMessageDialog(null, "Invalid format. Surname cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
            }

            int year = 0;
            int pagesStart = 0;
            try {
                year = Integer.parseInt(yearField.getText());
                if (year <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Invalid year format. Year cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(year> Year.now().getValue()){
                    JOptionPane.showMessageDialog(null, "Invalid year format. Year cannot be from future", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid year format. Type a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                pagesStart = Integer.parseInt(pagesField.getText());
                if (pagesStart <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid pages format. Pages cannot have such value", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid pages format. Type a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }


            if (pagesStart != 0 && year != 0 && year <= Year.now().getValue() && Pattern.matches(regex, authorFirstNameText) && Pattern.matches(regexSurname, authorLastNameText)) {

                AdminQueries.updateBook(titleText,authorFirstNameText,authorLastNameText,authorBirthDateText,yearText,pagesText,description,titleToUpdate);
                JOptionPane.showMessageDialog(null, "Book updated successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                DefaultListModel<String> updatedModel = new DefaultListModel<>();
                Queries.getAllAvailableBook(userChooseIFrame.getLibraryManagementFrame().getSelectedLibrary()).stream()
                        .map(String::toString)
                        .forEach(updatedModel::addElement);;
                list.setModel(updatedModel);
                dispose();
            }
        }
    }

}
