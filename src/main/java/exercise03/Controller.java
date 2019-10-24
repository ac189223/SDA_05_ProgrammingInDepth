package exercise03;

import java.util.ArrayList;

/**
 * Controls the flow of the application
 */
public class Controller {
    private static final Controller CON = new Controller();
    private final JSONOperator JSON_OPERATOR = new JSONOperator();
    private final String FILE_NAME_MEMBERS = "src/main/java/exercise03/memberList.json";
    private final String FILE_NAME_ATTENDANCE = "src/main/java/exercise03/attendance.json";
    private AttendanceRegister attendanceRegister = new AttendanceRegister();
    private MemberRegister memberRegister = new MemberRegister();

    /**
     * Getters for this class
     */
    public static Controller getCON() { return CON; }
    public JSONOperator getJSON_OPERATOR() { return JSON_OPERATOR; }
    public String getFILE_NAME_MEMBERS() { return FILE_NAME_MEMBERS; }
    public String getFILE_NAME_ATTENDANCE() { return FILE_NAME_ATTENDANCE; }
    public AttendanceRegister getAttendanceRegister() { return attendanceRegister; }
    public MemberRegister getMemberRegister() { return memberRegister; }

    /**
     * Setter for this class
     */
    public void setMemberRegister(MemberRegister memberRegister) { this.memberRegister = memberRegister; }
    public void setAttendanceRegister(AttendanceRegister attendanceRegister) { this.attendanceRegister = attendanceRegister; }

    /**
     * Main method of the application
     *
     * @param args  not used
     */
    public static void main(String[] args) throws Exception {
        View view = new View();
        getCON().setMemberRegister(getCON().getJSON_OPERATOR().readMembersFromJSON(getCON().getFILE_NAME_MEMBERS()));
        getCON().setAttendanceRegister(getCON().getJSON_OPERATOR().readAttendanceFromJSON(getCON().getFILE_NAME_ATTENDANCE()));
        if (!getCON().checkIds()) {
            view.idNotUniqueMessage();
            System.exit(0);
        }
        getCON().chooseActivity();
    }

    /**
     * Choose of activity to do
     */
    public void chooseActivity() {
        View view = new View();
        int option;
        while (true) {
            option = view.chooseOption();
            switch (option) {
                case 6:     // Exit program
                    getCON().saveAndClose();
                case 5:     // Check attendance for today
                    view.checkAttendance(getCON());
                    break;
                case 4:     // Edit past attendance
                    view.editPastAttendance(getCON());
                    break;
                case 3:     // Add member
                    view.addMember(getCON());
                    break;
                case 2:     // Edit member
                    view.editMember(getCON());
                    break;
                case 1:     // Remove member
                    view.deleteMember(getCON());
                    break;
                case 0:     // Statistics
                    view.stats(getCON());
                    break;
            }
        }
    }

    /**
     * Check if Id values in membersRegister are unique
     *
     * @param memberRegister    (optional) memberRegister to be checked
     * @return                  true if Ids are unique, false if not
     */
    public boolean checkIds(MemberRegister memberRegister) {
        boolean check = true;
        int amountOfMembers = memberRegister.getMembers().size();
        int amountOfUniqueIds = (int) memberRegister.getMembers().stream().map(Member::getId).distinct().count();
        if (amountOfMembers != amountOfUniqueIds)
            check = false;
        return check;
    }
    public boolean checkIds() {
        return checkIds(getMemberRegister());
    }

    /**
     * Adding list of attendance to the register, after removing possibly existing old record with same date
     *
     * @param listOfAttendance  list to be saved
     */
    public void addAttendance(ArrayList<String> listOfAttendance) {
        for (ArrayList<String> attendance: getAttendanceRegister().getAttendance())
            if (attendance.get(0).equals(listOfAttendance.get(0))) {
                getAttendanceRegister().getAttendance().remove(attendance);
                break;
            }
        getAttendanceRegister().getAttendance().add(listOfAttendance);
        saveAndClose();
    }

    /**
     * Save data to JSON files and quit
     */
    public void saveAndClose() {
        getCON().getJSON_OPERATOR().writeMembersToJSON(getCON().getFILE_NAME_MEMBERS(), getCON().getMemberRegister());
        getCON().getJSON_OPERATOR().writeAttendanceToJSON(getCON().getFILE_NAME_ATTENDANCE(), getCON().getAttendanceRegister());
        System.exit(0);
    }
}
