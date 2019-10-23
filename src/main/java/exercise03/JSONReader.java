package exercise03;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JSONReader {
    private final String FILE_NAME = "src/main/java/exercise03/memberList.json";

    public MemberRegister readFromJSON(String fileName) {
        Gson gson = new Gson();
        MemberRegister memberRegister = new MemberRegister();

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
