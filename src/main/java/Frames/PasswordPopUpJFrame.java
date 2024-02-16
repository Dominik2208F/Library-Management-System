package Frames;

import Manager.Queries;


import javax.swing.*;
import java.awt.*;

public class PasswordPopUpJFrame extends JFrame {


    private UserChooseIFrame userChooseIFrame;
    private JLabel passwordHint,passwordHint2;
    public PasswordPopUpJFrame(UserChooseIFrame userChooseIFrame) {
        this.userChooseIFrame=userChooseIFrame;

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

        passwordHint = new JLabel("Text");
        passwordHint.setBounds(120, 40, 200, 30);
        add(passwordHint);

        passwordHint2 = new JLabel("Password hint :");
        passwordHint2.setBounds(30, 40, 150, 30);
        add(passwordHint2);

        setSize(300, 200);
        setTitle("Password reminder");
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    public void setHint(){

        String name= userChooseIFrame.getSelectedUserValue();

            char[] letters =Queries.getPasswordFromUserByName(name).toCharArray();

            StringBuilder buildHint= new StringBuilder();
            int counter=0;
            for(char letter: letters){
                if(counter%2==0 && counter!=0){
                    buildHint.append("*");
                }
                else {
                    buildHint.append(letter);
                }
                counter++;
                }
            passwordHint.setText(buildHint.toString());

        }

}
