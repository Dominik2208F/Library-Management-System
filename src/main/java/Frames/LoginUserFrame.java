package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginUserFrame extends JFrame {


    private JPasswordField passwordField;
    private JButton LoginButton;
    private JLabel passwordLabel;
    private JLabel Userlebel;
    private UserChooseIFrame userChoose;
    private Library flowLibrary;

    public LoginUserFrame(UserChooseIFrame userChooseIFrame, Library library) {
        this.userChoose = userChooseIFrame;
        this.flowLibrary = library;

        passwordField = new JPasswordField();
        LoginButton = new JButton("Log in");
        passwordLabel = new JLabel("Password:");
        Userlebel = new JLabel("Your account is " + userChooseIFrame.getChoosenUserName());
        passwordLabel.setBounds(30, 40, 100, 30);
        passwordField.setBounds(100, 50, 150, 20);
        LoginButton.setBounds(130, 90, 80, 30);
        Userlebel.setBounds(30, 10, 200, 30);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInto(library);
            }
        });
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
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logInto(library);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        passwordField.addKeyListener(keyListener);
        add(passwordLabel);
        add(passwordField);
        add(LoginButton);
        add(Userlebel);
        setSize(300, 200);
        setTitle("Log in into User");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

    }

    public void logInto(Library library) {
        char[] passwordchar = passwordField.getPassword();
        String password = new String(passwordchar);

        if (flowLibrary.getLibraryUserDataBase().returnObjectOfUserByName(userChoose.getChoosenUserName()).getPassword().equalsIgnoreCase(password)) {
            int odp = JOptionPane.showConfirmDialog(null, "Confirm your choice");

            if (odp == JOptionPane.YES_OPTION) {
                if (userChoose.getChoosenUserName().equals("Admin")) {

                    setVisible(false);
                    userChoose.setVisible(false);
                    new AdminActionFrame(userChoose, library);
                } else {

                    setVisible(false);
                    userChoose.setVisible(false);
                    new UserActionFrame(userChoose, library);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong password try again", "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
}