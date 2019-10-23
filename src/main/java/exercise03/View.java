package exercise03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * Printing on the screen
 */
public class View {
    private final String TITLE = "=-_-=";
    private final String TITLE_DATE = String.valueOf(LocalDate.now());
    private final Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Getters for this class
     */
    public String getTITLE() { return TITLE; }
    public String getTITLE_DATE() { return TITLE_DATE; }
    public Dimension getDIM() { return DIM; }

    public void idNotUniqueMessage() {
        JOptionPane.showMessageDialog(new JFrame("      " + getTITLE()),
                "Provided Id values are not unique.\n" + "File not accepted",
                TITLE,
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Checking the attendance of members
     *
     * @param con    controller containing list of members
     */
    public void checkAttendance(Controller con) {

        JFrame frame = new JFrame("      " + getTITLE_DATE());
        frame.getContentPane().setBackground(new Color(30, 144, 255));
        frame.setBackground(new Color(135, 206, 235));
        frame.setLocation(getDIM().width / 3, getDIM().height / 3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(135, 206, 235), 0, true));
        panel.setBackground(new Color(135, 206, 235));
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        frame.add(panel);

        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        con.getMemberRegister().getMembers().forEach(member -> {
            JCheckBox checkBox = new JCheckBox(member.getName());
            checkBoxes.add(checkBox);
            panel.add(checkBox);
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                con.addAttendance(saveAttendance());
                frame.dispose();
            }

            /**
             * Private method to create list of attendance to the register
             *
             * @return  date and list of attenders
             */
            private ArrayList<String> saveAttendance() {
                ArrayList<String> attendance = new ArrayList<>();
                attendance.add(TITLE_DATE);
                attendance.addAll(checkBoxes.stream().filter(AbstractButton::isSelected)
                        .map(AbstractButton::getText).collect(Collectors.toList()));
                return attendance;
            }
        });
        panel.add(saveButton);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
