package Frames;

import Manager.CommonFunctions;
import Manager.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Pattern;


public class ChangePasswordJFrame extends JFrame implements CommonFunctions {

    private JTextField userId = new JTextField();
    private JTextField Username = new JTextField();
    private JPasswordField OldPassword = new JPasswordField();
    private JPasswordField newPassword = new JPasswordField();


    public ChangePasswordJFrame(String libraryNameField, String username) {


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


        JButton button = new JButton("Confirm");
        button.setBounds(110, 300, 150, 35);
        add(button);
        button.setVisible(true);

        JButton button2 = new JButton("Previous screen");
        button2.setBounds(110, 345, 150, 35);
        add(button2);
        button2.setVisible(true);

        ImageIcon goBackButton = setIcon("/logout.png");
        button2.setIcon(goBackButton);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ImageIcon okIcon = setIcon("/check.png");
        button.setIcon(okIcon);

        JLabel imageAddBookLabel = new JLabel(setIcon("/padlock.png"));
        imageAddBookLabel.setBounds(70, 10, 200, 65);
        add(imageAddBookLabel);

        JToggleButton showHideButtonOLD = new JToggleButton();
        JToggleButton showHideButtonNEW = new JToggleButton();

        showHideButtonOLD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (showHideButtonOLD.isSelected()) {
                    OldPassword.setEchoChar((char) 0);
                } else {

                    OldPassword.setEchoChar('\u2022');
                }
            }
        });

        showHideButtonNEW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (showHideButtonNEW.isSelected()) {
                    newPassword.setEchoChar((char) 0);
                } else {

                    newPassword.setEchoChar('\u2022');
                }
            }
        });


        ImageIcon qucikView = setIcon("/view.png");
        showHideButtonOLD.setIcon(qucikView);
        showHideButtonNEW.setIcon(qucikView);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser(libraryNameField, username);
            }
        });


        showHideButtonOLD.setBounds(255, 190, 60, 40);
        showHideButtonNEW.setBounds(255, 240, 60, 40);


        userId.setBounds(100, 90, 150, 40);
        Username.setBounds(100, 140, 150, 40);
        OldPassword.setBounds(100, 190, 150, 40);
        newPassword.setBounds(100, 240, 150, 40);


        add(showHideButtonNEW);
        add(showHideButtonOLD);

        add(new JLabel("User id:")).setBounds(15, 90, 100, 35);
        add(userId);
        add(new JLabel("Username:")).setBounds(15, 140, 100, 35);
        add(Username);
        add(new JLabel("Old password:")).setBounds(15, 190, 100, 35);
        add(OldPassword);
        add(new JLabel("New password:")).setBounds(15, 240, 100, 35);
        add(newPassword);


        ResultSet resultSet = Queries.userInfo(libraryNameField, username);


        String user_id = null;
        String userName = null;


        try {
            while (resultSet.next()) {

                user_id = resultSet.getString("user_id");
                userName = resultSet.getString("username");


            }
        } catch (Exception e) {

        }
        userId.setText(user_id);
        Username.setText(userName);

        userId.setEditable(false);
        Username.setEditable(false);

        setSize(380, 450);
        setTitle("Change password");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }


    public void saveUser(String libraryNameField, String username) {

        char[] passwordcharOLD = OldPassword.getPassword();
        char[] passwordchar = newPassword.getPassword();
        String passwordNew = new String(passwordchar);
        String passwordOLD = new String(passwordcharOLD);
        String regex = "(?=.*[a-ząćęłńóśźż])(?=.*[A-ZĄĆĘŁŃÓŚŹŻ])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-ząćęłńóśźżĄĆĘŁŃÓŚŹŻ\\d@$!%*?&#]{8,}$";
        String passwordFromDataBase = Queries.userPassword(libraryNameField, username);
        if (!passwordFromDataBase.equals(passwordOLD)) {
            JOptionPane.showMessageDialog(null, "Wrong current password.Try again"
                    , "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwordFromDataBase.equals(passwordNew)) {

            JOptionPane.showMessageDialog(null, "New password cannot be the same as current"
                    , "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwordchar.length == 0) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            if (!Pattern.matches(regex, passwordNew) || passwordNew.toLowerCase().contains(username.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Password is not strong enough.The password must meet the conditions :\n" +
                                "* One capital letter.\n" +
                                "* One lowercase letter.\n" +
                                "* One digit.\n" +
                                "* One special character.\n" +
                                "* Does not contain personal data from user name.\n" +
                                "* Uses eight characters.\n"
                        , "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                Queries.updatePassword(passwordNew, libraryNameField, username);
                JOptionPane.showMessageDialog(null, "New Password has been set successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        }
    }


}
