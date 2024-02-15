package Frames;

import Manager.CommonFunctions;
import Manager.Queries;
import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LibraryManagementFrame extends JFrame implements ActionListener, CommonFunctions {

    private JLabel welcome,assign;
    private JButton buttonConfirm;
    private JComboBox<String> comboBox;

    public LibraryDataBase getLibraryDataBase() {
        return libraryDataBase;
    }

    private LibraryDataBase libraryDataBase;
    private Library flowLibrary;

    public String getSelectedLibrary() {
        return selectedLibrary;
    }

    private String selectedLibrary;



    public LibraryManagementFrame(LibraryDataBase libraryDataBase) {

        this.libraryDataBase = libraryDataBase;

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Zwraca listę bibliotek
        try {
            ResultSet set= Queries.readAllLibraries();
            while (set.next()) {
                comboBoxModel.addElement(set.getString("library_name"));
            }
        }
        catch (Exception e){
            System.out.println("No libraries in Data Base");
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


        comboBox = new JComboBox<>(comboBoxModel);
        comboBox.setBounds(100, 280, 200, 40);

        welcome = new JLabel("Welcome!");
        welcome.setBounds(120, 40, 200, 60);
        welcome.setFont(new Font("Forte", Font.ITALIC, 35));
        welcome.setForeground(Color.WHITE);

        assign = new JLabel("© Dominik Jakubaszek");
        assign.setBounds(220, 420, 200, 40);
        assign.setFont(new Font("Forte", Font.ITALIC, 12));
        assign.setForeground(Color.white);

        buttonConfirm = new JButton("Confirm");
        buttonConfirm.setBounds(100, 330, 200, 40);
        buttonConfirm.addActionListener(this);

        ImageIcon confirm =setIcon("/approved.png");
        buttonConfirm.setIcon(confirm);

        URL imageUrl = getClass().getResource("/pngwing.com (2).png");
        JLabel label = null;
        if (imageUrl != null) {

            try (InputStream inputStream = imageUrl.openStream()) {

                Image originalImage = ImageIO.read(inputStream);
                int newWidth = 150;
                int newHeight = 150;
                Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                label = new JLabel(scaledIcon);
                label.setBounds(120, 120, newWidth, newHeight);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Nie udało się znaleźć zasobu.");
        }

        getContentPane().setLayout(null);
        getContentPane().add(label);




        add(comboBox);
        add(buttonConfirm);
        add(welcome);
        add(assign);

        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setTitle("Library Management System");

    }

    public void IfButtonClicked() {
         selectedLibrary = (String) comboBox.getSelectedItem();
        if (selectedLibrary == null || selectedLibrary.isEmpty()) {
            JOptionPane.showMessageDialog(LibraryManagementFrame.this, "You have to choose at least 1 library", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Library librarySpec : libraryDataBase.getListOfLibrary()) {
                if (librarySpec.getNameOfLibrary().equalsIgnoreCase(selectedLibrary)) {
                    flowLibrary = librarySpec;
                }
            }
            UserChooseIFrame userChooseIFrame = new UserChooseIFrame(flowLibrary, LibraryManagementFrame.this);
            dispose();
            userChooseIFrame.setVisible(true);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonConfirm) {
            IfButtonClicked();
        }
    }

}


