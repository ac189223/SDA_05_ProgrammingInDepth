package exercise03;

/**
 * Controls the flow of the application
 */
public class Controller {
    private static final Controller con = new Controller();
    private final JSONOperator jsonOperator = new JSONOperator();
    private final String FILE_NAME = "src/main/java/exercise03/memberList.json";

    /**
     * Getters for this class
     */
    public static Controller getCon() { return con; }
    public JSONOperator getJsonOperator() { return jsonOperator; }
    public String getFILE_NAME() { return FILE_NAME; }

    /**
     * Main method of the application
     *
     * @param args  not used
     */
    public static void main(String[] args) {
        getCon().getJsonOperator().readFromJSON(getCon().getFILE_NAME());

    }

}
