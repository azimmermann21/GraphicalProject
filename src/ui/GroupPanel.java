package src.ui;

import src.domain.Group;
import src.domain.User;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupPanel extends JPanel {
    MainScreen screen;
    Group group;
    JButton homeButton = new JButton("Home");
    JButton joinButton = new JButton("Join");
    JButton removeMemberButton = new JButton("Remove Member");
    JButton deleteGroupButton = new JButton("Delete Group");
    JButton createPostButton = new JButton("Create Post");
    JButton modifyPostButton = new JButton("Modify post");
    JLabel groupLabel = new JLabel();
    JLabel countryLabel = new JLabel("Country:");
    JLabel countryNameLabel = new JLabel();
    JLabel hobbiesLabel = new JLabel("Hobbies:");
    JLabel membersLabel = new JLabel();
    JLabel creatorLabel = new JLabel();
    JTextField removeMemberText = new JTextField();
    JTextField postToModifyText = new JTextField();

    /**
     * Create the panel
     */
    public GroupPanel(MainScreen screen, String groupName) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);
        
        setGroup(groupName);

        if (group.getMembers().containsKey(screen.getMe().getUsername()))
            memberList();

        hobbieList();

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    /**
     * Set all the components properties and their bounds
     */
    private void setAllComponents() {
        if (group.getMembers().containsKey(screen.getMe().getUsername()))
            joinButton.setText("Leave");
        homeButton.setBounds(681, 3,100,25);
        joinButton.setBounds(3, 33,100,25);
        removeMemberButton.setBounds(631, 533,150,25);
        deleteGroupButton.setBounds(315, 533,150,25);
        createPostButton.setBounds(3, 515,150,25);
        modifyPostButton.setBounds(3, 478, 150, 25);
        groupLabel.setText(group.getName());
        groupLabel.setBounds(3,3,150,25);
        groupLabel.setFont(new Font("Serif", Font.BOLD, 20));
        countryLabel.setBounds(3,60,150,25);
        countryNameLabel.setText(group.getCountry());
        countryNameLabel.setBounds(5, 75,150,25);
        hobbiesLabel.setBounds(3,100,150,25);
        membersLabel.setText(group.getMembers().size() + " Members :");
        membersLabel.setBounds(681, 30, 100, 25);
        creatorLabel.setText("Group by : " + group.getCreator().getUsername());
        creatorLabel.setBounds(3, 540, 150, 25);
        removeMemberText.setBounds(631, 507, 150, 25);
        postToModifyText.setBounds(3, 450, 150, 25);
    }

    /**
     * Add all the components to the panel
     */
    private void addAllComponents() {
        add(homeButton);
        add(groupLabel);
        add(countryLabel);
        add(countryNameLabel);
        add(hobbiesLabel);

        if (!group.getCreator().getUsername().equals(screen.getMe().getUsername()))
            add(joinButton);
        else {
            add(deleteGroupButton);
            add(removeMemberButton);
            add(removeMemberText);
        }

        if (group.getMembers().containsKey(screen.getMe().getUsername())) {
            add(membersLabel);
            add(creatorLabel);
            add(createPostButton);
            add(modifyPostButton);
            add(postToModifyText);
        }
    }

    /**
     * Listen to the buttons and perform the corresponding actions
     */
    private void listenButtons() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("homepage", null);
            }
        });
        
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (joinButton.getText().equals("Join")) {
                    group.addMember(screen.getMe());
                    joinButton.setText("Leave");
                } else {
                    group.removeMember(screen.getMe());
                    joinButton.setText("Join");
                }
                updatePage();
            }
        });

        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeMember(removeMemberText.getText());
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });

        deleteGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.getGroups().remove(group);
                screen.switchPanel("homepage", null);
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("createPost", group.getName());
            }
        });

        modifyPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modifyPost();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
    }

    /**
     * Set the group to display on the panel
     * @param groupName the name of the group to display
     */
    private void setGroup(String groupName) {
        ArrayList<Group> groups = screen.getGroups();

        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                this.group = group;
                break;
            }
        }
    }

    /**
     * Display the list of members in the group
     */
    private void memberList() {
        HashMap<String, User> members = group.getMembers();
        int xGap = 0;

        for (User member : members.values()) {
            JLabel membersListLabel = new JLabel();
            membersListLabel.setText(member.getUsername());
            membersListLabel.setBounds(681, 55 + xGap, 400, 20);
            xGap += 20;
            add(membersListLabel);
        }
    }

    /**
     * Display the list of hobbies in the group
     */
    private void hobbieList() {
        ArrayList<String> hobbie = group.getHobbies();
        int xGap = 0;

        for (String hobbieName : hobbie) {
            JLabel hobbieListLabel = new JLabel();
            hobbieListLabel.setText(hobbieName);
            hobbieListLabel.setBounds(5, 120 + xGap, 400, 20);
            xGap += 20;
            add(hobbieListLabel);
        }
    }

    /**
     * Update the page
     */
    private void updatePage() {
        screen.switchPanel("group", group.getName());
    }

    /**
     * Remove a member from the group
     * @param username the username of the member to remove
     * @throws Exception if the username is empty
     * @throws Exception if the username is the creator of the group
     * @throws Exception if the username is not in the group
     */
    private void removeMember(String username) throws Exception {
        HashMap<String, User> members = group.getMembers();

        if (username.equals(""))
            throw new Exception("Please enter a username");
        if (group.getCreator().getUsername().equals(username))
            throw new Exception("You can't remove the creator of the group");
        else if (members.containsKey(username)) {
            group.removeMember(members.get(username));
            updatePage();
        } else
            throw new Exception("User " + username + " not found");
    }

    /**
     * Modify a post
     * @throws Exception if the post to modify is empty
     * @throws Exception if the post to modify is not in the group
     * @throws Exception if the post to modify is don't belong to the user
     */
    private void modifyPost() throws Exception {
        if (postToModifyText.getText().isEmpty())
            throw new Exception("The post can't be empty");
        if (group.getContents().get(postToModifyText.getText()) == null)
            throw new Exception("Post \"" + postToModifyText.getText() + "\" does not exist");
        else if (group.getContents().get(postToModifyText.getText()).getAuthor().equals(screen.getMe().getUsername()))
            screen.switchPanel("post", postToModifyText.getText());
        else
            throw new Exception("Post \"" + postToModifyText.getText() + "\" does not belong to you");
    }
}