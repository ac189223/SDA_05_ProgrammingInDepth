package exercise03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
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
    public MemberRegister readMembersFromJSON(String fileName) {
        Gson gson = new Gson();
        MemberRegister memberRegister;

        try (Reader reader = new FileReader(fileName)) {
            // Convert JSON file to Java object
            memberRegister = gson.fromJson(reader, MemberRegister.class);
        } catch (IOException e) {
            memberRegister = new MemberRegister();
        }
    return memberRegister;
    }

    /**
     * Writing members into JSON file
     *
     * @param fileName  name of the file with members
     */
    public void writeMembersToJSON(String fileName, MemberRegister memberRegister) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Java object to file
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(memberRegister, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rearing data from JSON file containing list of attendances
     *
     * @param fileName  name of the file with attendance data
     * @return          read data in AttendanceRegister format
     */
    public AttendanceRegister readAttendanceFromJSON(String fileName) {
        Gson gson = new Gson();
        AttendanceRegister attendanceRegister;

        try (Reader reader = new FileReader(fileName)) {
            // Convert JSON file to Java object
            attendanceRegister = gson.fromJson(reader, AttendanceRegister.class);
        } catch (IOException e) {
            attendanceRegister = new AttendanceRegister();
        }
        return attendanceRegister;
    }

    /**
     * Writing attendance register into JSON file
     *
     * @param fileName  name of the file with attendance data
     */
    public void writeAttendanceToJSON(String fileName, AttendanceRegister attendanceRegister) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Java object to file
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(attendanceRegister, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
