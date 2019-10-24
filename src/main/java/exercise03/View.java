package exercise03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

        JDialog frame = new JDialog();
        frame.setTitle("      " + getTITLE_DATE());
        frame.setModal(true);
        frame.getContentPane().setBackground(new Color(30, 144, 255));
        frame.setBackground(new Color(135, 206, 235));
        frame.setLocation(getDIM().width / 3, getDIM().height / 3);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
                frame.setVisible(false);
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

    /**
     * Print available activities and ask to choose
     *
     * @return  chosen option
     */
    public int chooseOption() {
        int chosenOption = -1;
        Object[] options = {6, 5, 4, 3, 2, 1, 0};
        while (chosenOption < 0 || chosenOption > 6)
            chosenOption = JOptionPane.showOptionDialog(new JFrame("      " + getTITLE()),
                    "Please choose what to do" +
                            "\n0: Exit the program" +
                            "\n1: Check attendance for today" +
                            "\n2: Edit past attendance" +
                            "\n3: Add member" +
                            "\n4: Edit member" +
                            "\n5: Remove member" +
                            "\n6: Statistics",
                    "    =-_-=",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[6]);
        return chosenOption;
    }

    /**
     * Choose of list to edit
     *
     * @param con   controller with data
     */
    public void editPastAttendance(Controller con) {
        String chosenAttendance = "";
        if (con.getAttendanceRegister().getAttendance().size() != 0) {
            do {
                Object[] choices = con.getAttendanceRegister().getAttendance().stream().map(record -> record.get(0)).toArray();
                chosenAttendance = (String) JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Choose date",
                        getTITLE(), JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
            } while (!con.getAttendanceRegister().getAttendance().stream().map(record -> record.get(0)).collect(Collectors.toList()).contains(chosenAttendance));
            editAttendance(con, chosenAttendance);
        } else {
            JOptionPane.showMessageDialog(new JFrame("      " + getTITLE()),
                    "No attendance lists stored", getTITLE(), JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Editing the attendance of members
     *
     * @param chosenDate    list chosen for edition
     */
    public void editAttendance(Controller con, String chosenDate) {

        JDialog frame = new JDialog();
        frame.setTitle("      " + chosenDate);
        frame.setModal(true);
        frame.getContentPane().setBackground(new Color(30, 144, 255));
        frame.setBackground(new Color(135, 206, 235));
        frame.setLocation(getDIM().width / 3, getDIM().height / 3);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(135, 206, 235), 0, true));
        panel.setBackground(new Color(135, 206, 235));
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        frame.add(panel);

        ArrayList<String> chosenList = con.getAttendanceRegister().getAttendance().stream()
                .filter(record -> record.get(0).equals(chosenDate)).collect(Collectors.toList()).get(0);
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        con.getMemberRegister().getMembers().forEach(member -> {
            JCheckBox checkBox;
            if (chosenList.contains(member.getName()))
                checkBox = new JCheckBox(member.getName(), true);
            else
                checkBox = new JCheckBox(member.getName());
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

    /**
     * Adding members to register
     *
     * @param con   controller with data
     */
    public void addMember(Controller con) {
        String name = JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Enter name of a new member",
                getTITLE(), JOptionPane.PLAIN_MESSAGE);
        String id = "";
        do {
            id = JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Enter id of a new member (four characters)",
                    getTITLE(), JOptionPane.PLAIN_MESSAGE);
        } while (id.length() != 4 ||
                con.getMemberRegister().getMembers().stream().map(Member::getId).collect(Collectors.toList()).contains(id));
        con.getMemberRegister().getMembers().add(new Member(name, id));
    }

    /**
     * Choose member to edit
     *
     * @param con   controller with data
     */
    public void editMember(Controller con) {
        String chosenMember = "";
        if (con.getMemberRegister().getMembers().size() != 0) {
            do {
                Object[] choices = con.getMemberRegister().getMembers().stream().map(Member::getName).toArray();
                chosenMember = (String) JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Choose member to edit",
                        getTITLE(), JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
            } while (!con.getMemberRegister().getMembers().stream().map(Member::getName).collect(Collectors.toList()).contains(chosenMember));
            editMemberData(con, chosenMember);
        } else {
            JOptionPane.showMessageDialog(new JFrame("      " + getTITLE()),
                    "No members stored", getTITLE(), JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Editing fields of member
     *
     * @param con           controller with data
     * @param chosenMember  chosen member
     */
    private void editMemberData(Controller con, String chosenMember) {
        Member memberToEdit = con.getMemberRegister().getMembers().stream()
                .filter(member -> member.getName().equals(chosenMember)).collect(Collectors.toList()).get(0);
        String name = JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Enter name of a member to replace \"" +
                memberToEdit.getName() + "\"", getTITLE(), JOptionPane.PLAIN_MESSAGE);
        memberToEdit.setName(name);
        String id = "";
        do {
            id = JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Enter id of a member (four characters) " +
                            " to replace \"" + memberToEdit.getId() + "\"", getTITLE(), JOptionPane.PLAIN_MESSAGE);
        } while (id.length() != 4 ||
                con.getMemberRegister().getMembers().stream().map(Member::getId).collect(Collectors.toList()).contains(id));
        memberToEdit.setId(id);
    }

    /**
     * Delete a member
     *
     * @param con   controller with data
     */
    public void deleteMember(Controller con) {
        String chosenMember = "";
        if (con.getMemberRegister().getMembers().size() != 0) {
            do {
                Object[] choices = con.getMemberRegister().getMembers().stream().map(Member::getName).toArray();
                chosenMember = (String) JOptionPane.showInputDialog(new JFrame("      " + getTITLE()), "Choose member to delete",
                        getTITLE(), JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
            } while (!con.getMemberRegister().getMembers().stream().map(Member::getName).collect(Collectors.toList()).contains(chosenMember));
            String finalChosenMember = chosenMember;
            Member memberToDelete = con.getMemberRegister().getMembers().stream()
                    .filter(member -> member.getName().equals(finalChosenMember)).collect(Collectors.toList()).get(0);
            con.getMemberRegister().getMembers().remove(memberToDelete);
        } else {
            JOptionPane.showMessageDialog(new JFrame("      " + getTITLE()),
                    "No members stored", getTITLE(), JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Printing stats of attendance (percentage)
     *
     * @param con   controller with data
     */
    public void stats(Controller con) {

        JDialog frame = new JDialog();
        frame.setTitle("      " + getTITLE());
        frame.setModal(true);
        frame.getContentPane().setBackground(new Color(30, 144, 255));
        frame.setBackground(new Color(135, 206, 235));
        frame.setLocation(getDIM().width / 3, getDIM().height / 3);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(135, 206, 235), 0, true));
        panel.setBackground(new Color(135, 206, 235));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        int amountOfLists = con.getAttendanceRegister().getAttendance().size();

        con.getMemberRegister().getMembers().stream().sorted(Comparator
                .comparing(member -> con.getAttendanceRegister().getAttendance().stream()
                        .filter(list -> list.contains(member.getName())).count()))
                .forEach(member -> {
            JLabel label = new JLabel("   " + con.getAttendanceRegister().getAttendance().stream()
                    .filter(list -> list.contains(member.getName())).count() *100 / amountOfLists + "%   " + member.getName() + "   ");
            panel.add(label);
        });

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}