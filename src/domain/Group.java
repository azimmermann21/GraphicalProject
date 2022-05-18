package src.domain;

import java.util.ArrayList;
import java.util.HashMap;

import src.domain.Interface.Searchable;

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
     * Get the name of the group
     * @return the name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the group
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the country of the group
     * @return the country of the group
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country of the group
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the creator of the group
     * @return the creator of the group
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Set the creator of the group
     * @param creator the creator to set
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Get the hobbies of the group
     * @return the hobbies of the group
     */
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * Set the hobbies of the group
     * @param hobbies the hobbies to set
     */
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * Get the members of the group
     * @return the members of the group
     */
    public HashMap<String, User> getMembers() {
        return members;
    }

    /**
     * Set the members of the group
     * @param members the members to set
     */
    public void setMembers(HashMap<String, User> members) {
        this.members = members;
    }

    /**
     * Get the contents of the group
     * @return the contents of the group
     */
    public HashMap<String, Content> getContents() {
        return contents;
    }

    /**
     * Set the contents of the group
     * @param contents the contents to set
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

    /**
     * Add the content to the contents of the group
     * @param content the content to add
     */
    public void addContent(Content content) {
        contents.put(content.getTitle(), content);
    }
}
