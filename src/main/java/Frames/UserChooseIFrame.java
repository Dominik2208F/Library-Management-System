package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserChooseIFrame extends JFrame {


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
    JComboBox comboBoxUser= new JComboBox<>();

    public UserChooseIFrame(Library library, LibraryManagementFrame libraryManagementFrame) {
        this.flowLibrary = library;
        this.libraryManagementFrame = libraryManagementFrame;


        List<String> listOfCurrentUsers = new ArrayList<String>();


        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            listOfCurrentUsers.add(user.getName());
            String[] usersArray = listOfCurrentUsers.toArray(new String[listOfCurrentUsers.size()]);
             comboBoxUser.setModel(new DefaultComboBoxModel<>(usersArray));
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




        comboBoxUser.setBounds(55, 150, 130, 30);
        add(comboBoxUser);

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


        confirmUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLibraryText = (String) comboBoxUser.getSelectedItem();
                if (comboBoxUser==null || selectedLibraryText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Choose at least 1 user", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ChoosenUserName = selectedLibraryText;
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

        ImageIcon addUserIcon = new ImageIcon("src/add-user_8924229 (1).png");




        ImageIcon icon2 = new ImageIcon("src/checklist_11707966 (2).png");

        getContentPane().setLayout(null);
        getContentPane().add(label);
        addUser.setIcon(addUserIcon);
        confirmUser.setIcon(icon2);


        setSize(250, 350);
        setTitle("Choose user");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

    }

    public void updateListOfUsers() {
        List<String> listOfCurrentUsers = new ArrayList<String>();
        for (User user : flowLibrary.getLibraryUserDataBase().getListOfUser()) {
            listOfCurrentUsers.add(user.getName());
            String[] usersArray = listOfCurrentUsers.toArray(new String[listOfCurrentUsers.size()]);
            comboBoxUser.setModel(new DefaultComboBoxModel<>(usersArray));
        }
    }
}


