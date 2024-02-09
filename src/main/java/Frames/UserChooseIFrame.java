package Frames;

import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserChooseIFrame extends JFrame {

    JLabel availableUser;
    JLabel selectedUser;
    JButton confirmUser;
    JButton addUser;
    JList<String> list;
    String ChoosenUserName;
    Library flowLibrary;
    LibraryManagementFrame libraryManagementFrame;
    public UserChooseIFrame(Library library, LibraryManagementFrame libraryManagementFrame){
    this.flowLibrary=library;
    this.libraryManagementFrame=libraryManagementFrame;

        DefaultListModel<String> listOfUsersFromLibrary = new DefaultListModel<>();


        for(User user : flowLibrary.getLibraryUserDataBase().getListOfUser()){
            listOfUsersFromLibrary.addElement(user.getName());
        }
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Pobranie wymiarów panelu
                int width = getWidth();
                int height = getHeight();

                // Tworzenie gradientu od górnego lewego do dolnego prawego kąta
                GradientPaint gradient = new GradientPaint(0, 0, new Color(240,248,255), width, height, new Color(0,191, 255));

                ((Graphics2D) g).setPaint(gradient);
                g.fillRect(0, 0, width, height);

            }
        });

        list = new JList<>(listOfUsersFromLibrary);

        list.setBounds(70,50, 100,60);
        add(list);
        //
        availableUser = new JLabel("Available users");
        availableUser.setBounds(50,13, 150,20);
        availableUser.setFont(new Font("Forte",Font.ITALIC,20));
        add(availableUser);
        //
        selectedUser = new JLabel("Selected user");
        selectedUser.setBounds(50,120, 150,20);

        confirmUser= new JButton("Confirm");
        confirmUser.setBounds(70,120, 100,40);
        add(confirmUser);
        //
        addUser= new JButton("Add user");
        addUser.setBounds(70,170,100,30);
        add(addUser);

        JScrollPane scrollPane = new JScrollPane(list); // Wrap the JList in a JScrollPane
        scrollPane.setBounds(70, 50, 100, 60);
        add(scrollPane);
        confirmUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null,"Choose at least 1 user","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    ChoosenUserName = list.getSelectedValue();
                    new LoginUserFrame(UserChooseIFrame.this, library);  // tutaj tak samo klasa z zainicjowanym obiektem libraryManagementFrame;

                }
            }
        });

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame= new AddUserFrame(UserChooseIFrame.this,library, false);
                addUserFrame.setVisible(true);
            }
        });


        setSize(250,350);
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


