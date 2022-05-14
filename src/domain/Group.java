package src.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Group {
    private String name;
    private String country;
    private ArrayList <String> hobbies = new ArrayList<String>();
    private ArrayList <User> members = new ArrayList<User>();
    private HashMap <String, Content> contents = new HashMap<String, Content>();

    public Group(String name, String country,ArrayList <String> hobbies) {
        this.name = name;
        this.country = country;
        this.hobbies = hobbies;
    }

    /**
     * @return the name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * Set the name of the group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the country of the group
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     * Set the country of the group
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the hobbies of the group
     */
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     * Set the hobbies of the group
     */
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * @return the members of the group
     */
    public ArrayList<User> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     * Set the members of the group
     */
    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    /**
     * @return the contents of the group
     */
    public HashMap<String, Content> getContents() {
        return contents;
    }

    /**
     * @param contents the contents to set
     * Set the contents of the group
     */
    public void setContents(HashMap<String, Content> contents) {
        this.contents = contents;
    }

    
}
