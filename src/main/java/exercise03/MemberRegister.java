package exercise03;

import java.util.ArrayList;

/**
 * Representing register of members
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class MemberRegister {
    private ArrayList<Member> members;

    /**
     * Constructor for ready to work register with empty list
     *
     */
    public MemberRegister() {
        this.setMembers(new ArrayList<>());
    }

    /**
     * Getter for this class
     */
    public ArrayList<Member> getMembers() { return members; }

    /**
     * Setter for this class
     */
    public void setMembers(ArrayList<Member> members) { this.members = members; }
}
