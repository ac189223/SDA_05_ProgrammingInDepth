package exercise03;

import java.util.ArrayList;

/**
 * Represents daily attendance of members
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class AttendanceRegister {
    private ArrayList<ArrayList<String>> attendance;

    /**
     * Constructors for this class
     */
    public AttendanceRegister() {
        this.setAttendance(new ArrayList<>());
    }

    public AttendanceRegister(ArrayList<ArrayList<String>> attendance) {
        this.setAttendance(attendance);
    }

    /**
     * Getter for this class
     */
    public ArrayList<ArrayList<String>> getAttendance() { return attendance; }

    /**
     * Setter for this class
     */
    public void setAttendance(ArrayList<ArrayList<String>> attendance) { this.attendance = attendance; }
}
