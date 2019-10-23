package exercise03;

/**
 * Representing member
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Member {
    private String name;
    private String id;

    /**
     * Constructor for data from JSON
     *
     * @param name  name to be assigned to the member
     * @param id    id to be assigned
     */
    public Member(String name, String id) {
        this.setName(name);
        this.setId(id);
    }

    /**
     * Getters for this class
     */
    public String getName() { return name; }
    public String getId() { return id; }

    /**
     * Setters for this class
     */
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
}
