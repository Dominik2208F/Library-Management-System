package Frames;

import Manager.CommonFunctions;
import Manager.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MyAccountOverViewJFrame extends JFrame implements CommonFunctions {

    private JTextField userId = new JTextField();
    private JTextField Username = new JTextField();
    private JTextField libraryName = new JTextField();
    private JTextField dateOfCreation = new JTextField();
    private JTextField permissionLevel = new JTextField();

    public MyAccountOverViewJFrame(String libraryNameField,String username) {



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


        JButton button = new JButton("OK");
        button.setBounds(125, 350, 100, 35);
        add(button);
        button.setVisible(true);

        ImageIcon okIcon = setIcon("/check.png");
        button.setIcon(okIcon);

        JLabel imageAddBookLabel= new JLabel(setIcon("/user-avatar.png"));
        imageAddBookLabel.setBounds(70, 10, 200, 65);
        add(imageAddBookLabel);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



        userId.setBounds(100, 90, 150, 40);
        Username.setBounds(100, 140, 150, 40);
        libraryName.setBounds(100, 190, 150, 40);
        dateOfCreation.setBounds(100, 240,  150, 40);
        permissionLevel.setBounds(100,290,150,40);

       ResultSet resultSet = Queries.userInfo(libraryNameField,username);


        String user_id = null;
        String userName = null;
        String libraryNamee = null;
        String dateofCreation = null;
        String  permlevel= null;

        try {
            while (resultSet.next()) {

                user_id = resultSet.getString("user_id");
                userName = resultSet.getString("username");
                libraryNamee = resultSet.getString("library_name");
                dateofCreation = resultSet.getString("dateofcreation");
                permlevel=resultSet.getString("permissionlevel");


            }
        }catch (Exception e){

        }

        userId.setText(user_id);
        Username.setText(userName);
        libraryName.setText(libraryNamee);
        dateOfCreation.setText(dateofCreation);
        permissionLevel.setText(permlevel);


        add(new JLabel("User id:")).setBounds(15, 90, 100, 35);
        add(userId);
        add(new JLabel("Username:")).setBounds(15, 140, 100, 35);
        add(Username);
        add(new JLabel("Library:")).setBounds(15, 190, 100, 35);
        add(libraryName);
        add(new JLabel("Creation date:")).setBounds(15, 240, 100, 35);
        add(dateOfCreation);
        add(new JLabel("Permission :")).setBounds(15, 290, 100, 35);
        add(permissionLevel);

        userId.setEditable(false);
        Username.setEditable(false);
        libraryName.setEditable(false);
        dateOfCreation.setEditable(false);
        permissionLevel.setEditable(false);

        setSize(330, 450);
        setTitle("User details");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

}
