package exercise03;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * Printing on the screen
 */
public class View {
    private final String TITLE = "    =-_-=    ";

    /**
     * Getters for this class
     */
    public String getTITLE() { return TITLE; }

    public void idNotUniqueMessage() {
        JOptionPane.showMessageDialog(new JFrame(getTITLE()),
                "Provided Id values are not unique.\n" + "File not accepted",
                TITLE,
                JOptionPane.PLAIN_MESSAGE);
    }

    public void checkAttendance(MemberRegister memberRegister) {

        JFrame frame = new JFrame(getTITLE());
        frame.getContentPane().setBackground(new Color(30, 144, 255));
        frame.setBackground(new Color(135, 206, 235));
        //frame.setBounds(100, 100, 480, 610);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(135, 206, 235), 0, true));
        panel.setBackground(new Color(135, 206, 235));
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        frame.add(panel);


        memberRegister.getMembers().forEach(member -> {
            JCheckBox checkBox = new JCheckBox(member.getName());
            panel.add(checkBox);
        });

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
