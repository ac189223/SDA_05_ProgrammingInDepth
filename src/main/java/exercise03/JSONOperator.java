package exercise03;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Represents operations possible to provide for an JSON file
 */
public class JSONOperator {

    /**
     * Rearing data from JSON file containing list of members
     *
     * @param fileName  name of the file with members data
     * @return          read data in MemberRegister format
     */
    public MemberRegister readFromJSON(String fileName) {
        Gson gson = new Gson();
        MemberRegister memberRegister;

        try (Reader reader = new FileReader(fileName)) {
            // Convert JSON file to Java object
            memberRegister = gson.fromJson(reader, MemberRegister.class);
            // Print MemberRegister object
            // memberRegister.getMembers().forEach(member -> System.out.println(member.getId() + " - " + member.getName()));
        } catch (IOException e) {
            memberRegister = new MemberRegister();
        }
    return memberRegister;
    }
}
