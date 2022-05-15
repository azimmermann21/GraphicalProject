package src.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Group implements Searchable {
    private String name;
    private String country;
    private User creator;
    private ArrayList<String> hobbies = new ArrayList<String>();
    private HashMap<String, User> members = new HashMap<String, User>();
    private HashMap<String, Content> contents = new HashMap<String, Content>();

    public Group(String name, String country, User creator, ArrayList <String> hobbies) {
        this.name = name;
        this.country = country;
        this.creator = creator;
        members.put(creator.getUsername(), creator);
        this.hobbies = hobbies;
    }

    /**
     * @return the name of the group
     * Get the name of the group
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
     * Get the country of the group
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
     * @return the creator of the group
     * Get the creator of the group
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     * Set the creator of the group
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * @return the hobbies of the group
     * Get the hobbies of the group
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
     * Get the members of the group
     */
    public HashMap<String, User> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     * Set the members of the group
     */
    public void setMembers(HashMap<String, User> members) {
        this.members = members;
    }

    /**
     * @return the contents of the group
     * Get the contents of the group
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

    /**
     * Add a member to the group
     * @param member the member to add
     */
    public void addMember(User member) {
        members.put(member.getUsername(), member);
    }

    /**
     * Remove a member from the group
     * @param member the member to remove
     */
    public void removeMember(User member) {
        members.remove(member.getUsername());
    }
}
