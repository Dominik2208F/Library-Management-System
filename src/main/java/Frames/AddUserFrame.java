package Frames;

import Manager.CommonFunctions;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.util.List;
import java.util.stream.Collectors;


public class AddUserFrame extends JFrame implements CommonFunctions {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel,addUser;
    private UserChooseIFrame userChooseIFrame;
    private Library flowLibrary;

    public void setAdminActionFrame(AdminActionFrame adminActionFrame) {
        this.adminActionFrame = adminActionFrame;
    }

    private AdminActionFrame adminActionFrame;

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

        ImageIcon iconAddUser = setIcon("/following.png");
        addUser = new JLabel(iconAddUser);
        addUser.setBounds(70,10,200,70);



        ImageIcon save =setIcon("/save-file.png");
        saveButton.setIcon(save);
        ImageIcon qucikView = setIcon("/view.png");
        showHideButton.setIcon(qucikView);


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

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

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
                userChooseIFrame.setVisible(true);
                userChooseIFrame.getComboBoxUser().setEnabled(true);
                userChooseIFrame.getAddUser().setEnabled(true);
                userChooseIFrame.getConfirmUser().setEnabled(true);
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



        setSize(350, 300);
        setTitle("Add user");
        setLayout(null);
        setResizable(false);
        setVisible(true);

    }

    public void saveUser() {
        String username = usernameField.getText();
        char[] passwordchar = passwordField.getPassword();
        String regex = "(?=.*[a-ząćęłńóśźż])(?=.*[A-ZĄĆĘŁŃÓŚŹŻ])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-ząćęłńóśźżĄĆĘŁŃÓŚŹŻ\\d@$!%*?&]{8,}$";

        List<User> users = flowLibrary.getLibraryUserDataBase().getListOfUser();
        List<String> lowerCaseUserNames = users.stream()
                .map(user -> user.getName().toLowerCase())
                .collect(Collectors.toList());


        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lowerCaseUserNames.contains(username.toLowerCase())){
            JOptionPane.showMessageDialog(null, "Such login is no more available. Please create unique login", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (passwordchar.length == 0) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String password = new String(passwordchar);
            if(!Pattern.matches(regex,password) || password.toLowerCase().contains(username.toLowerCase())){
                JOptionPane.showMessageDialog(null, "Password is not strong enough.The password must meet the conditions :\n" +
                        "* One capital letter.\n" +
                        "* One lowercase letter.\n" +
                        "* One digit.\n" +
                        "* One special character.\n" +
                        "* Does not contain personal data from user name.\n" +
                        "* Uses eight characters.\n"
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }else{
            //    User newUser =new User(username, password);
            //    if(newUser.getName().equals("Admin") || newUser.getName().equals("admin")){
            //        JOptionPane.showMessageDialog(null, "You cannot create second admin account", "Error", JOptionPane.ERROR_MESSAGE);
            //    }
            //    else if( flowLibrary.getLibraryUserDataBase().getListOfUser().contains(newUser)) {
            //        JOptionPane.showMessageDialog(null, "User with identical data exist in database. You cannot create the user", "Error", JOptionPane.ERROR_MESSAGE);

                    flowLibrary.getLibraryUserDataBase().getListOfUser().add(new User(username, password));
                    userChooseIFrame.updateListOfUsers();
                    JOptionPane.showMessageDialog(null, "User " + username +" has been added successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                userChooseIFrame.getComboBoxUser().setEnabled(true);
                userChooseIFrame.getAddUser().setEnabled(true);
                userChooseIFrame.getConfirmUser().setEnabled(true);
                    setVisible(false);
                }
            }
        }

}