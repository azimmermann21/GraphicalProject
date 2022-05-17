/* Pledge of Honor *************************************************************************************
I hereby certify that I have completed this programming project on my own without any help from
anyone else. The effort in the project thus belongs completely to me. I did not search for a
solution, or I did not consult to any program written by others or did not copy any program from
other sources. I read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Antoine Zimmermann, 78291>
************************************************************************************************************/

package src.ui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import src.domain.User;
import src.domain.Group;
import src.domain.Content;

public class MainScreen extends JFrame {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Group> groups = new ArrayList<Group>();
    private User me;

    public static void main(String[] args) {
        new MainScreen();
    }

    /**
     * Create the frame.
     */
    public MainScreen() {
        setTitle("Social Network");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        setContentPane(new LoginPanel(this));
        createDummyDatas();
    }

    /**
     * Switches the content pane of the frame to the given panel
     * @param panel the panel to set as the content pane
     * @param arg an argument that can be passed to a panel
     */
    public void switchPanel(String panelName, String arg) {
        if (panelName == "login")
            setContentPane(new LoginPanel(this));
        else if (panelName == "register")
            setContentPane(new RegisterPanel(this));
        else if (panelName == "homepage")
            setContentPane(new HomePanel(this));
        else if (panelName == "profile")
            setContentPane(new ProfilePanel(this, arg));
        else if (panelName == "group")
            setContentPane(new GroupPanel(this, arg));
        else if (panelName == "createGroup")
            setContentPane(new CreateGroupPanel(this));
        else if (panelName == "createPost")
            setContentPane(new CreatePostPanel(this, arg));
        else if (panelName == "post")
            setContentPane(new PostPanel(this, arg));
        repaint();
    }

    /**
     * Creates dummy datas for testing purposes
     */
    public void createDummyDatas() {
        User johndoe = new User("johndoe", "John123", "john@doe.com", "John", "Doe", 21, "United States", false);
        User janedoe = new User("janedoe", "Jane123", "jane@doe.com", "Jane", "Doe", 21, "United States", false);
        User billylesinge = new User("billylesinge", "Louis123", "billy@lesinge.com", "Billy", "Lesinge", 15, "France", true);
        User ouitest = new User("ouitest", "Louis123", "billy@lesinge.com", "Billy", "Lesinge", 15, "France", true);
        User louisleboss = new User("louisleboss", "Louis123", "louis@leboss.com", "Louis", "Christner", 21, "France", true);
        User hgvleboss = new User("hgvleboss", "Louis123", "hgv@leboss.com", "Hoang-Giang", "Vo", 21, "United States", true);
        User iboleboss = new User("iboleboss", "Louis123", "ibo@leboss.com", "Ibrahim", "Akgul", 21, "Turkey", true);
        User matleboss = new User("matleboss", "Louis123", "mat@leboss.com", "Matthieu", "Houlle", 21, "Turkey", true);
        
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("football");
        hobbies.add("coding");

        ArrayList<String> hobbies2 = new ArrayList<String>();
        hobbies2.add("reading");
        hobbies2.add("gaming");

        ArrayList<String> hobbies3 = new ArrayList<String>();
        hobbies3.add("gaming");
        hobbies3.add("coding");

        ArrayList<String> hobbies4 = new ArrayList<String>();
        hobbies4.add("football");
        hobbies4.add("coding");
        hobbies4.add("reading");

        ArrayList<String> hobbies5 = new ArrayList<String>();
        hobbies5.add("basketball");
        hobbies5.add("gaming");
        hobbies5.add("cooking");

        johndoe.setHobbies(hobbies);
        janedoe.setHobbies(hobbies2);
        billylesinge.setHobbies(hobbies3);
        ouitest.setHobbies(hobbies3);
        louisleboss.setHobbies(hobbies);
        hgvleboss.setHobbies(hobbies2);
        iboleboss.setHobbies(hobbies3);
        matleboss.setHobbies(hobbies);

        users.add(johndoe);
        users.add(janedoe);
        users.add(billylesinge);
        users.add(ouitest);
        users.add(louisleboss);
        users.add(hgvleboss);
        users.add(iboleboss);
        users.add(matleboss);

        Group group1 = new Group("group1", "France", johndoe, hobbies);
        Group group2 = new Group("group2", "France", janedoe, hobbies2);
        Group group3 = new Group("group3", "USA", billylesinge, hobbies3);
        Group group4 = new Group("group4", "China", louisleboss, hobbies4);
        Group group5 = new Group("group5", "USA", hgvleboss, hobbies5);
        Group group6 = new Group("group6", "China", iboleboss, hobbies);
        Group group7 = new Group("group7", "Turkey", matleboss, hobbies2);
        Group group8 = new Group("group8", "Turkey", billylesinge, hobbies3);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);
        groups.add(group5);
        groups.add(group6);
        groups.add(group7);
        groups.add(group8);
    }

    /**
     * Get all users in the system
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Set all users in the system
     * @param users the users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Get all groups in the system
     * @return the groups
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Set all groups in the system
     * @param groups the groups to set
     */
    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    /**
     * Get the current user
     * @return the me
     */
    public User getMe() {
        return me;
    }

    /**
     * Set the current user
     * @param me the me to set
     */
    public void setMe(User me) {
        this.me = me;
    }

    /**
     * Get all posts in the system
     * @return all posts in the system
     */
    public HashMap<String, Content> getAllContent() {
        HashMap<String, Content> allContent = new HashMap<String, Content>();

        for (User user : users)
            for (Content content : user.getContents().values())
                allContent.put(content.getTitle(), content);
        for (Group group : groups)
            for (Content content : group.getContents().values())
                allContent.put(content.getTitle(), content);
        return allContent;
    }
}
