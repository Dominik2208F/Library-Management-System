package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserChooseIFrame extends JFrame {

    private JLabel availableUser;
    private JLabel selectedUser;
    private JButton confirmUser,showPassword;
    private JButton addUser;


    private JList<String> list;

    public String getChoosenUserName() {
        return ChoosenUserName;
    }

    public Library getFlowLibrary() {
        return flowLibrary;
    }

    public LibraryManagementFrame getLibraryManagementFrame() {
        return libraryManagementFrame;
    }

    public JList<String> getList() {
        return list;
    }
    private String ChoosenUserName;
    private Library flowLibrary;
    private LibraryManagementFrame libraryManagementFrame;

    public UserChooseIFrame(Library library, LibraryManagementFrame libraryManagementFrame) {
        this.flowLibrary = library;
        this.libraryManagementFrame = libraryManagementFrame;

        DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();


        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            listOfUsersFromLibrary.addElement(user.getName());
        }
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

        list = new JList<>(listOfUsersFromLibrary);

        list.setBounds(70, 110, 100, 60);
        add(list);
        //

        showPassword= new JButton(" Don't remember password?");
        showPassword.setBounds(20,280,200,20);
        add(showPassword);
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Choose at least 1 user", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(list.getSelectedValue().equals("Admin")){
                    JOptionPane.showMessageDialog(null, "You cannot choose admin", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new PasswordPopUpJFrame(UserChooseIFrame.this).setHint();
                }
            }
        });


        selectedUser = new JLabel("Selected user");
        selectedUser.setBounds(50, 120, 150, 20);

        confirmUser = new JButton("Confirm");
        confirmUser.setBounds(55, 190, 130, 40);
        add(confirmUser);

        addUser = new JButton("Add user");
        addUser.setBounds(55, 240, 130, 30);
        add(addUser);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(70, 110, 100, 60);
        add(scrollPane);
        confirmUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Choose at least 1 user", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ChoosenUserName = list.getSelectedValue();
                    new LoginUserFrame(UserChooseIFrame.this, library);

                }
            }
        });

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame = new AddUserFrame(UserChooseIFrame.this, library, false);
                addUserFrame.setVisible(true);
            }
        });


        ImageIcon icon = new ImageIcon("src/profile_3135715.png");
        Image originalImage = icon.getImage();
        int newWidth = 80;
        int newHeight = 80;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(80, 20, newWidth, newHeight);

        ImageIcon icon1 = new ImageIcon("src/add-user_747554.png");
        Image originalImage1 = icon1.getImage();
        int newWidth1 = 20;
        int newHeight1 = 20;
        Image scaledImage1 = originalImage1.getScaledInstance(newWidth1, newHeight1, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        JLabel label1 = new JLabel(scaledIcon1);




        ImageIcon icon2 = new ImageIcon("src/checklist_11707966 (2).png");

        getContentPane().setLayout(null);
        getContentPane().add(label);
        getContentPane().add(label1);
        addUser.setIcon(scaledIcon1);
        confirmUser.setIcon(icon2);


        setSize(250, 350);
        setTitle("Choose user");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

    }

    public void updateListOfUsers() {
        DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();

        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            listOfUsersFromLibrary.addElement(user.getName());
        }

        list.setModel(listOfUsersFromLibrary);
    }
}


