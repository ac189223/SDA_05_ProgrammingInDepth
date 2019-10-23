package exercise03;

/**
 * Controls the flow of the application
 */
public class Controller {
    private static final Controller con = new Controller();
    private final JSONOperator jsonOperator = new JSONOperator();
    private final String FILE_NAME = "src/main/java/exercise03/memberList.json";
    private MemberRegister memberRegister = new MemberRegister();

    /**
     * Getters for this class
     */
    public static Controller getCon() { return con; }
    public JSONOperator getJsonOperator() { return jsonOperator; }
    public String getFILE_NAME() { return FILE_NAME; }
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
        getCon().setMemberRegister(getCon().getJsonOperator().readFromJSON(getCon().getFILE_NAME()));
        if (!getCon().checkIds()) {
            throw new Exception("Provided Id values are not unique. File not accepted");
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
    public boolean checkIds() { return checkIds(getMemberRegister()); }

}
