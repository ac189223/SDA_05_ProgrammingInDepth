package exercise03;

/**
 * Controls the flow of the application
 */
public class Controller {
    private static final Controller CON = new Controller();
    private final JSONOperator JSON_OPERATOR = new JSONOperator();
    private final String FILE_NAME = "src/main/java/exercise03/memberList.json";
    private final View VIEW = new View();
    private MemberRegister memberRegister = new MemberRegister();

    /**
     * Getters for this class
     */
    public static Controller getCON() { return CON; }
    public JSONOperator getJSON_OPERATOR() { return JSON_OPERATOR; }
    public String getFILE_NAME() { return FILE_NAME; }
    public View getVIEW() { return VIEW; }
    public MemberRegister getMemberRegister() { return memberRegister; }

    /**
     * Setter for this class
     */
    public void setMemberRegister(MemberRegister memberRegister) { this.memberRegister = memberRegister; }

    /**
     * Main method of the application
     *
     * @param args  not used
     */
    public static void main(String[] args) throws Exception {
        getCON().setMemberRegister(getCON().getJSON_OPERATOR().readFromJSON(getCON().getFILE_NAME()));
        if (!getCON().checkIds()) {
            getCON().getVIEW().idNotUniqueMessage();
            System.exit(0);
        }
        getCON().getVIEW().checkAttendance(getCON().getMemberRegister());

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

}
