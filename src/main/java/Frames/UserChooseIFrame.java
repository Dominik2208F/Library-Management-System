package Frames;

import Manager.CommonFunctions;
import Manager.Queries;
import org.example.LibraryManager.Library;
import org.example.UserManager.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserChooseIFrame extends JFrame implements CommonFunctions {


    public JButton getConfirmUser() {
        return confirmUser;
    }

    private JButton confirmUser,showPassword;

    public JButton getAddUser() {
        return addUser;
    }

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

    private String selectedUserValue;
    private Library flowLibrary;
    private LibraryManagementFrame libraryManagementFrame;

    public JComboBox getComboBoxUser() {
        return comboBoxUser;
    }

    JComboBox comboBoxUser= new JComboBox<>();

    private JMenuBar menubar;
    private JMenu Options;
    private JMenuItem changeLibrary;

    public String getSelectedUserValue() {
        return selectedUserValue;
    }

    public UserChooseIFrame(Library library, LibraryManagementFrame libraryManagementFrame) {
        this.flowLibrary = library;
        this.libraryManagementFrame = libraryManagementFrame;


        List<String> listOfCurrentUsers = new ArrayList<String>();

            //zwrócenie użytkowników z bazy danych do listy
            ResultSet set= Queries.readUsersAssignedToLibraryByName(libraryManagementFrame.getSelectedLibrary());
            try {
                while (set.next()) {

                    listOfCurrentUsers.add(set.getString("username"));
                }
            }catch (Exception e){

            }

             String[] usersArray = listOfCurrentUsers.toArray(new String[listOfCurrentUsers.size()]);
             comboBoxUser.setModel(new DefaultComboBoxModel<>(usersArray));


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




        comboBoxUser.setBounds(100, 240, 200, 40);
        add(comboBoxUser);


        confirmUser = new JButton("Log in");
        confirmUser.setBounds(100, 290, 200, 40);
        add(confirmUser);

        addUser = new JButton("Add user");
        addUser.setBounds(100, 340, 200, 40);
        add(addUser);

        showPassword= new JButton(" Don't remember password?");
        showPassword.setBounds(100,410,200,40);
        add(showPassword);


        menubar = new JMenuBar();
        Options = new JMenu("Options");

        changeLibrary = new JMenuItem("Change library");
        Options.add(changeLibrary);


        setJMenuBar(menubar);
        menubar.add(Options);

        changeLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LibraryManagementFrame(getLibraryManagementFrame().getLibraryDataBase());
                setVisible(false);
            }
        });

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedUserValue = (String) comboBoxUser.getSelectedItem();
                if(selectedUserValue.equals("Admin")){
                    JOptionPane.showMessageDialog(null, "You cannot remind password to  admin", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new PasswordPopUpJFrame(UserChooseIFrame.this).setHint();
                }
            }
        });
        confirmUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedUserValue = (String) comboBoxUser.getSelectedItem();
                if (comboBoxUser==null || selectedUserValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Choose at least 1 user", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ChoosenUserName = selectedUserValue;
                    new LoginUserFrame(UserChooseIFrame.this, library);
                    comboBoxUser.setEnabled(false);
                    addUser.setEnabled(false);
                    confirmUser.setEnabled(false);
                }
            }
        });

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame = new AddUserFrame(UserChooseIFrame.this, library, false);
                addUserFrame.setVisible(true);
                comboBoxUser.setEnabled(false);
                addUser.setEnabled(false);
                confirmUser.setEnabled(false);
            }
        });


        URL imageUrl = getClass().getResource("/profile_3135715.png");

        JLabel label = null;
        if (imageUrl != null) {
          
            try (InputStream inputStream = imageUrl.openStream()) {
                Image originalImage = ImageIO.read(inputStream);
                int newWidth = 180;
                int newHeight = 180;
                Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                label = new JLabel(scaledIcon);
                label.setBounds(110, 20, newWidth, newHeight);
                
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        } else {
            System.err.println("Nie udało się znaleźć zasobu."); // 
        }



        ImageIcon addUserIcon = setIcon("/add-user_8924229 (1).png");
        ImageIcon icon2 = setIcon("/checklist_11707966 (2).png");

        getContentPane().setLayout(null);
        getContentPane().add(label);
        addUser.setIcon(addUserIcon);
        confirmUser.setIcon(icon2);


        setSize(400, 500);
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


