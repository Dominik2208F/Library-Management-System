package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUserFrame extends JFrame {


    private JPasswordField passwordField;
    private JButton LoginButton;
    private JLabel passwordLabel;
    private JLabel Userlebel;
    private UserChooseIFrame userChoose;
    private Library flowLibrary;

    public LoginUserFrame(UserChooseIFrame userChooseIFrame, Library library) {
        this.userChoose=userChooseIFrame;
        this.flowLibrary=library;

        passwordField = new JPasswordField();
        LoginButton = new JButton("Log in");
        passwordLabel = new JLabel("Password:");
        Userlebel = new JLabel("Your account is " + userChooseIFrame.ChoosenUserName);
        passwordLabel.setBounds(30,40,100,30);
        passwordField.setBounds(100,50,150,20);
        LoginButton.setBounds(130,90,80,30);
        Userlebel.setBounds(30,10,200,30);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char[] passwordchar = passwordField.getPassword();
                String password = new String(passwordchar);

                if (flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChoose.ChoosenUserName).getPassword().equalsIgnoreCase(password)) {
                    int odp = JOptionPane.showConfirmDialog(null, "Confirm your choice");

                    if (odp == JOptionPane.YES_OPTION) {
                        if (userChoose.ChoosenUserName.equals("Admin")) {

                            setVisible(false);
                            userChoose.setVisible(false);
                            new AdminActionFrame(userChoose, library);
                        } else {

                            setVisible(false);
                            userChoose.setVisible(false);
                            new UserActionFrame(userChoose, library);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Wrong password try again","Error",JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                }
            }
        });

        add(passwordLabel);
        add(passwordField);
        add(LoginButton);
        add(Userlebel);
        setSize(300,200);
        setTitle("Log in into User");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        ImageIcon backgroundImage = new ImageIcon("src/background1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
        setLayout(null);
    }

}