package Frames;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarIFrame extends JFrame {

    Date returnDateOfBirth;

    AddBookJFrame addBookJFrameF;

    CalendarIFrame(JButton button, AddBookJFrame addBookJFrame) {
        this.addBookJFrameF=addBookJFrame;
        JDialog dialog = new JDialog((Frame) null, "Select Date", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        calendar.setTodayButtonVisible(true);
        calendar.setWeekOfYearVisible(true);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = calendar.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                addBookJFrameF.authorBirthDateField.setText(dateFormat.format(selectedDate));

                JOptionPane.showMessageDialog(null, "Selected Date: " + dateFormat.format(selectedDate));
                dialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);

        dialog.add(calendar);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(button);
        dialog.setVisible(true);
    }


}
