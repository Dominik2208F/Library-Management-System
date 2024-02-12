package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

public class AddUserFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel,addUser;
    private UserChooseIFrame userChooseIFrame;
    private Library flowLibrary;

    public AddUserFrame(UserChooseIFrame userChooseIFrame, Library library, boolean b) {
        this.userChooseIFrame = userChooseIFrame;
        this.flowLibrary = library;


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
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JToggleButton showHideButton = new JToggleButton("Show/hide");
        saveButton = new JButton("Save");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameLabel.setBounds(30, 80, 150, 30);
        passwordLabel.setBounds(30,115 , 100, 30);
        usernameField.setBounds(100, 85, 150, 30);
        passwordField.setBounds(100, 120, 150, 30);
        saveButton.setBounds(180, 170, 100, 30);
        showHideButton.setBounds(50, 170, 120, 30);

        ImageIcon iconAddUser = new ImageIcon("src/following.png");
        addUser = new JLabel(iconAddUser);
        addUser.setBounds(70,10,200,70);



        ImageIcon save =new ImageIcon("src/save-file.png");
        saveButton.setIcon(save);
        ImageIcon qucikView = new ImageIcon("src/view.png");
        showHideButton.setIcon(qucikView);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    saveUser();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        usernameField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(saveButton);
        add(addUser);
        add(showHideButton);

        showHideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (showHideButton.isSelected()) {
                    passwordField.setEchoChar((char)0);
                } else {

                    passwordField.setEchoChar('\u2022');
                }
            }
        });

        setSize(350, 300);
        setTitle("Library menagement system v2");
        setLayout(null);
        setResizable(false);
        setVisible(true);

    }

    public void saveUser() {
        String username = usernameField.getText();
        char[] passwordchar = passwordField.getPassword();
        String regex = "(?=.*[a-ząćęłńóśźż])(?=.*[A-ZĄĆĘŁŃÓŚŹŻ])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-ząćęłńóśźżĄĆĘŁŃÓŚŹŻ\\d@$!%*?&]{8,}$";


        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwordchar.length == 0) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String password = new String(passwordchar);
            if(!Pattern.matches(regex,password)){
                JOptionPane.showMessageDialog(null, "Password is not enough strong.The password must have:\n" +
                        "* One capital letter.\n" +
                        "* One lowercase letter.\n" +
                        "* One digit.\n" +
                        "* One special character.\n" +
                        "* Does not contain personal data.\n" +
                        "* Uses eight characters.\n"
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                User newUser =new User(username, password);
                if( flowLibrary.getLibraryUserDataBase().getListOfUser().contains(newUser)){
                    JOptionPane.showMessageDialog(null, "User with identical data exist in database. You cannot create the user", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    flowLibrary.getLibraryUserDataBase().getListOfUser().add(new User(username, password));
                    userChooseIFrame.updateListOfUsers();
                    setVisible(false);
                }
            }
        }
    }

}