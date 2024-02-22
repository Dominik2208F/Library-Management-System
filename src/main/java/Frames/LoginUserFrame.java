package Frames;

import Manager.CommonFunctions;
import Manager.Queries;
import org.example.LibraryManager.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUserFrame extends JFrame implements CommonFunctions {

    private JPasswordField passwordField;
    private JButton LoginButton,GoBackButton;
    private JLabel passwordLabel;
    private JLabel UserImageLebel;
    private UserChooseIFrame userChoose;
    private Library flowLibrary;

    public LoginUserFrame(UserChooseIFrame userChooseIFrame, Library library) {
        this.userChoose = userChooseIFrame;
        this.flowLibrary = library;

        passwordField = new JPasswordField();
        LoginButton = new JButton("Log in");
        passwordLabel = new JLabel("Password:");
        JToggleButton showHideButton = new JToggleButton();
        passwordLabel.setBounds(30, 75, 100, 30);
        passwordField.setBounds(100, 80, 150, 30);
        LoginButton.setBounds(100, 120, 150, 30);
        showHideButton.setBounds(255, 80, 60, 30);
        GoBackButton =new JButton("Previous screen");
        GoBackButton.setBounds(100, 160, 150, 30);
        ImageIcon goBackButton = setIcon("/logout.png");
        GoBackButton.setIcon(goBackButton);
        GoBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                userChoose.getComboBoxUser().setEnabled(true);
                userChoose.getAddUser().setEnabled(true);
                userChoose.getConfirmUser().setEnabled(true);
            }
        });
        if(userChooseIFrame.getChoosenUserName().equals("Admin")) {
            ImageIcon iconAdmin = setIcon("/user (4).png");
            UserImageLebel = new JLabel(iconAdmin);
        } else {
            ImageIcon iconUser = setIcon("/user (5).png");
            UserImageLebel = new JLabel(iconUser);
        }
        UserImageLebel.setBounds(70, 10, 200, 70);

        LoginButton.setIcon(setIcon("/log-in (1).png"));

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInto(library);
            }
        });

        ImageIcon qucikView = setIcon("/view.png");
        showHideButton.setIcon(qucikView);


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
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logInto(library);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        passwordField.addKeyListener(keyListener);
        add(passwordLabel);
        add(passwordField);
        add(LoginButton);
        add(UserImageLebel);
        add(GoBackButton);
        add(showHideButton);
        setSize(350, 250);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        showHideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (showHideButton.isSelected()) {
                    passwordField.setEchoChar((char)0);
                } else {

                    passwordField.setEchoChar('\u2022');
                }
            }
        });



        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("opened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                userChoose.setVisible(true);
                userChoose.getComboBoxUser().setEnabled(true);
                userChoose.getAddUser().setEnabled(true);
                userChoose.getConfirmUser().setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    public void logInto(Library library) {
        char[] passwordchar = passwordField.getPassword();
        String password = new String(passwordchar);
        String UserName=userChoose.getChoosenUserName();

        String passwordFromDB= Queries.getPasswordOfUser(UserName,userChoose.getLibraryManagementFrame().getSelectedLibrary());


        if (passwordFromDB.equals(password)) {
            int odp = JOptionPane.showConfirmDialog(null, "Confirm your choice");

            if (odp == JOptionPane.YES_OPTION) {
                if (userChoose.getChoosenUserName().equals("Admin")) {
                    setVisible(false);
                    userChoose.setVisible(false);
                    new AdminActionFrame(userChoose, library);
                } else {
                    setVisible(false);
                    userChoose.setVisible(false);
                    userChoose.getComboBoxUser().setEnabled(true);
                    new UserActionFrame(userChoose);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong password try again", "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
}