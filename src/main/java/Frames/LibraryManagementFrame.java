package Frames;

import org.example.LibraryManager.Library;
import org.example.LibraryManager.LibraryDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class LibraryManagementFrame extends JFrame implements ActionListener {

    private JLabel welcome,assign,selectedLibrary;
    private JButton buttonConfirm;
    private JComboBox<String> comboBox;

    public LibraryDataBase getLibraryDataBase() {
        return libraryDataBase;
    }

    private LibraryDataBase libraryDataBase;
    private Library flowLibrary;


    public LibraryManagementFrame(LibraryDataBase libraryDataBase) {

        this.libraryDataBase = libraryDataBase;

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (Library Singlelibrary : libraryDataBase.getListOfLibrary()) {
            comboBoxModel.addElement(Singlelibrary.getNameOfLibrary());
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

        ImageIcon confirm = new ImageIcon("src/approved.png");
        buttonConfirm.setIcon(confirm);

        ImageIcon icon = new ImageIcon("src/pngwing.com (2).png");
        Image originalImage = icon.getImage();
        int newWidth = 150;
        int newHeight = 150;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(120, 120, newWidth, newHeight);

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
        setTitle("Library Menagement System");

    }

    public void IfButtonClicked() {
        String selectedLibraryText = (String) comboBox.getSelectedItem();
        if (selectedLibraryText == null || selectedLibraryText.isEmpty()) {
            JOptionPane.showMessageDialog(LibraryManagementFrame.this, "You have to choose at least 1 library", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Library librarySpec : libraryDataBase.getListOfLibrary()) {
                if (librarySpec.getNameOfLibrary().equalsIgnoreCase(selectedLibraryText)) {
                    flowLibrary = librarySpec;
                }
            }
            UserChooseIFrame userChooseIFrame = new UserChooseIFrame(flowLibrary, LibraryManagementFrame.this);  // przekazujesz klase IFrame z zainicjowanym obiektem UserDataBase
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


