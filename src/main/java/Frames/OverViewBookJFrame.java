package Frames;

import Manager.CommonFunctions;
import Manager.Queries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class OverViewBookJFrame extends JFrame implements CommonFunctions {
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
    private JTextField statusField = new JTextField();

    public OverViewBookJFrame(String libraryName, JList list) {

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

        JButton select = new JButton("Select");

        select.setBounds(200, 240, 100, 40);
        add(select);

        select.setEnabled(false);

        JButton button = new JButton("OK");
        button.setBounds(140, 490, 100, 40);
        add(button);
        button.setVisible(true);

        ImageIcon okIcon = setIcon("/check.png");
        button.setIcon(okIcon);

        JLabel imageAddBookLabel= new JLabel(setIcon("/research.png"));
        imageAddBookLabel.setBounds(100, 10, 200, 65);
        add(imageAddBookLabel);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        statusField.setBounds(100,440,200,40);


       String TitleToUpdate = extractTitle((String) list.getSelectedValue());


        ResultSet resultSet=Queries.getQucikViewInfo(libraryName,TitleToUpdate);


        String status = null;
        String dateOfBirth = null;
        String title = null;
        String authorFirstName = null;
        String authorLastName = null;
        String yearOfProduction = null;
        String genre = null;
        String pages = null;
        try {
            while (resultSet.next()) {

                 status = resultSet.getString("status");
                 dateOfBirth = resultSet.getString("date_of_birth");
                 title = resultSet.getString("title");
                 authorFirstName = resultSet.getString("first_name");
                 authorLastName=resultSet.getString("last_name");
                 yearOfProduction = resultSet.getString("yearofproduction");
                 genre = resultSet.getString("name");
                 pages = resultSet.getString("pages");




            }
        }catch (Exception e){

        }

        statusField.setText(status);
        titleField.setText(title);
        authorFirstNameField.setText(authorFirstName);
        authorLastNameField.setText(authorLastName);
        authorBirthDateField.setText(dateOfBirth);
        yearField.setText(yearOfProduction);
        genreComboBox.setSelectedItem(genre);
        pagesField.setText(pages);

        add(new JLabel("Title:")).setBounds(15, 90, 100, 35);
        add(titleField);
        add(new JLabel("First Name:")).setBounds(15, 140, 100, 35);
        add(authorFirstNameField);
        add(new JLabel("Last Name:")).setBounds(15, 190, 100, 35);
        add(authorLastNameField);
        add(new JLabel("Birth Date:")).setBounds(15, 240, 100, 35);
        add(authorBirthDateField);
        add(new JLabel("Year:")).setBounds(15, 290, 100, 35);
        add(yearField);
        add(new JLabel("Genre:")).setBounds(15, 340, 100, 35);
        add(genreComboBox);
        add(new JLabel("Pages:")).setBounds(15, 390, 100, 35);
        add(pagesField);
        add(new JLabel("Status:")).setBounds(15, 435, 100, 35);
        add(statusField);

        statusField.setEditable(false);
        titleField.setEditable(false);
        authorFirstNameField.setEditable(false);
        authorLastNameField.setEditable(false);
        authorBirthDateField.setEditable(false);
        yearField.setEditable(false);
        genreComboBox.setEnabled(false);
        pagesField.setEditable(false);


        setSize(400, 580);
        setTitle("Qucik overview");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

}
