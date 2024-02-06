package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private UserChooseIFrame userChooseIFrame;
    private Library flowLibrary;

    public AddUserFrame(UserChooseIFrame userChooseIFrame, Library library) {
        this.userChooseIFrame=userChooseIFrame;
        this.flowLibrary=library;

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        saveButton = new JButton("Save");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameLabel.setBounds(30,10, 100,30);
        passwordLabel.setBounds(30,40,100,30);

        usernameField.setBounds(100,20,150,20);
        passwordField.setBounds(100,50,150,20);

        ;
        saveButton.setBounds(130,90,80,30);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordchar= passwordField.getPassword();

                if(username.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Username cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(passwordchar.length==0){
                    JOptionPane.showMessageDialog(null,"Password cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String password= new String(passwordchar);
                    flowLibrary.getLibraryUserDataBase().getListOfUser().add(new User(username,password));

                    userChooseIFrame.updateListOfUsers();
                    setVisible(false);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(saveButton);

        setSize(300,200);
        setTitle("Add new User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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