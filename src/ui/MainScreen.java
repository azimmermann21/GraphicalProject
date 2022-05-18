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
        User user1 = new User("user1", "password1", "user1@example.com", "user1Name", "user1Surname", 21, "United States", false);
        User user2 = new User("user2", "password2", "user2@example.com", "user2Name", "user2Surname", 21, "United States", false);
        User user3 = new User("user3", "password3", "user3@example.com", "user3Name", "user3Surname", 21, "United States", false);
        User user4 = new User("user4", "password4", "user4@example.com", "user4Name", "user4Surname", 21, "United States", false);
        User user5 = new User("user5", "password5", "user5@example.com", "user5Name", "user5Surname", 21, "United States", false);
        User user6 = new User("user6", "password6", "user6@example.com", "user6Name", "user6Surname", 21, "United States", false);
        User user7 = new User("user7", "password7", "user7@example.com", "user7Name", "user7Surname", 21, "United States", false);
        User user8 = new User("user8", "password8", "user8@example.com", "user8Name", "user8Surname", 21, "United States", false);
        User user9 = new User("user9", "password9", "user9@example.com", "user9Name", "user9Surname", 21, "United States", false);
        User user10 = new User("user10", "password10", "user10@example.com", "user10Name", "user10Surname", 21, "United States", false);
        User user11 = new User("user11", "password11", "user11@example.com", "user11Name", "user11Surname", 21, "United States", false);
        User user12 = new User("user12", "password12", "user12@example.com", "user12Name", "user12Surname", 21, "United States", false);
        
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

        user1.setHobbies(hobbies);
        user2.setHobbies(hobbies2);
        user3.setHobbies(hobbies3);
        user4.setHobbies(hobbies4);
        user5.setHobbies(hobbies5);
        user6.setHobbies(hobbies);
        user7.setHobbies(hobbies2);
        user8.setHobbies(hobbies3);
        user9.setHobbies(hobbies4);
        user10.setHobbies(hobbies5);
        user11.setHobbies(hobbies);
        user12.setHobbies(hobbies2);

        user1.follow(user2);
        user1.follow(user3);
        user1.follow(user4);
        user2.follow(user3);
        user2.follow(user4);
        user2.follow(user5);
        user3.follow(user4);
        user3.follow(user5);
        user3.follow(user6);
        user4.follow(user5);
        user4.follow(user6);
        user4.follow(user7);
        user5.follow(user6);
        user5.follow(user7);
        user5.follow(user8);
        user6.follow(user7);
        user6.follow(user8);
        user6.follow(user9);
        user7.follow(user8);
        user7.follow(user9);
        user7.follow(user10);
        user8.follow(user9);
        user8.follow(user10);
        user8.follow(user11);
        user9.follow(user10);
        user9.follow(user11);
        user9.follow(user12);
        user10.follow(user11);
        user10.follow(user12);
        user10.follow(user1);
        user11.follow(user12);
        user11.follow(user1);
        user11.follow(user2);
        user12.follow(user1);
        user12.follow(user2);
        user12.follow(user3);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);

        Group group1 = new Group("Group1", "France", user1, hobbies);
        Group group2 = new Group("Group2", "Turkey", user2, hobbies2);
        Group group3 = new Group("Group3", "USA", user3, hobbies3);
        Group group4 = new Group("Group4", "China", user4, hobbies4);

        group1.addMember(user5);
        group1.addMember(user6);
        group2.addMember(user7);
        group2.addMember(user8);
        group3.addMember(user9);
        group3.addMember(user10);
        group4.addMember(user11);
        group4.addMember(user12);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);

        Content content1 = new Content("Content1", "Content1Description", user1.getUsername(), "assets/default.png");
        Content content2 = new Content("Content2", "Content2Description", user1.getUsername(), null);
        Content content3 = new Content("Content3", "Content3Description", user1.getUsername(), null);
        Content content4 = new Content("Content4", "Content4Description", user1.getUsername(), null);
        Content content5 = new Content("Content5", "Content5Description", user2.getUsername(), "assets/default.png");
        Content content6 = new Content("Content6", "Content6Description", user2.getUsername(), null);
        Content content7 = new Content("Content7", "Content7Description", user2.getUsername(), null);
        Content content8 = new Content("Content8", "Content8Description", user2.getUsername(), null);
        Content content9 = new Content("Content9", "Content9Description", user3.getUsername(), "assets/default.png");
        Content content10 = new Content("Content10", "Content10Description", user3.getUsername(), null);
        Content content11 = new Content("Content11", "Content11Description", user3.getUsername(), null);
        Content content12 = new Content("Content12", "Content12Description", user3.getUsername(), null);
        Content content13 = new Content("Content13", "Content13Description", user4.getUsername(), "assets/default.png");
        Content content14 = new Content("Content14", "Content14Description", user4.getUsername(), null);
        Content content15 = new Content("Content15", "Content15Description", user4.getUsername(), null);
        Content content16 = new Content("Content16", "Content16Description", user4.getUsername(), null);
        Content content17 = new Content("Content17", "Content17Description", user5.getUsername(), "assets/default.png");
        Content content18 = new Content("Content18", "Content18Description", user5.getUsername(), null);
        Content content19 = new Content("Content19", "Content19Description", user5.getUsername(), null);
        Content content20 = new Content("Content20", "Content20Description", user5.getUsername(), null);
        Content content21 = new Content("Content21", "Content21Description", user6.getUsername(), "assets/default.png");
        Content content22 = new Content("Content22", "Content22Description", user6.getUsername(), null);
        Content content23 = new Content("Content23", "Content23Description", user6.getUsername(), null);
        Content content24 = new Content("Content24", "Content24Description", user6.getUsername(), null);
        Content content25 = new Content("Content25", "Content25Description", user7.getUsername(), "assets/default.png");
        Content content26 = new Content("Content26", "Content26Description", user7.getUsername(), null);
        Content content27 = new Content("Content27", "Content27Description", user7.getUsername(), null);
        Content content28 = new Content("Content28", "Content28Description", user7.getUsername(), null);
        Content content29 = new Content("Content29", "Content29Description", user8.getUsername(), "assets/default.png");
        Content content30 = new Content("Content30", "Content30Description", user8.getUsername(), null);
        Content content31 = new Content("Content31", "Content31Description", user8.getUsername(), null);
        Content content32 = new Content("Content32", "Content32Description", user8.getUsername(), null);
        Content content33 = new Content("Content33", "Content33Description", user9.getUsername(), "assets/default.png");
        Content content34 = new Content("Content34", "Content34Description", user9.getUsername(), null);
        Content content35 = new Content("Content35", "Content35Description", user9.getUsername(), null);
        Content content36 = new Content("Content36", "Content36Description", user9.getUsername(), null);
        Content content37 = new Content("Content37", "Content37Description", user10.getUsername(), "assets/default.png");
        Content content38 = new Content("Content38", "Content38Description", user10.getUsername(), null);
        Content content39 = new Content("Content39", "Content39Description", user10.getUsername(), null);
        Content content40 = new Content("Content40", "Content40Description", user10.getUsername(), null);
        Content content41 = new Content("Content41", "Content41Description", user11.getUsername(), "assets/default.png");
        Content content42 = new Content("Content42", "Content42Description", user11.getUsername(), null);
        Content content43 = new Content("Content43", "Content43Description", user11.getUsername(), null);
        Content content44 = new Content("Content44", "Content44Description", user11.getUsername(), null);
        Content content45 = new Content("Content45", "Content45Description", user12.getUsername(), "assets/default.png");
        Content content46 = new Content("Content46", "Content46Description", user12.getUsername(), null);
        Content content47 = new Content("Content47", "Content47Description", user12.getUsername(), null);
        Content content48 = new Content("Content48", "Content48Description", user12.getUsername(), null);

        group1.addContent(content1);
        user1.addContent(content2);
        user1.addContent(content3);
        user1.addContent(content4);

        group2.addContent(content5);
        user2.addContent(content6);
        user2.addContent(content7);
        user2.addContent(content8);

        user3.addContent(content9);
        user3.addContent(content10);
        user3.addContent(content11);
        group3.addContent(content12);

        user4.addContent(content13);
        user4.addContent(content14);
        user4.addContent(content15);
        group4.addContent(content16);

        user5.addContent(content17);
        user5.addContent(content18);
        user5.addContent(content19);
        group1.addContent(content20);

        user6.addContent(content21);
        user6.addContent(content22);
        user6.addContent(content23);
        group1.addContent(content24);

        user7.addContent(content25);
        user7.addContent(content26);
        user7.addContent(content27);
        group2.addContent(content28);

        user8.addContent(content29);
        user8.addContent(content30);
        user8.addContent(content31);
        group2.addContent(content32);

        group3.addContent(content33);
        user9.addContent(content34);
        user9.addContent(content35);
        user9.addContent(content36);

        user10.addContent(content37);
        user10.addContent(content38);
        user10.addContent(content39);
        group3.addContent(content40);

        group4.addContent(content41);
        user11.addContent(content42);
        user11.addContent(content43);
        user11.addContent(content44);

        user12.addContent(content45);
        user12.addContent(content46);
        user12.addContent(content47);
        group4.addContent(content48);
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
