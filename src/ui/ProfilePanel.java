package src.ui;

import src.domain.User;
import src.domain.Group;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;

public class ProfilePanel extends JPanel {
    MainScreen screen;
    User user;
    JButton homeButton = new JButton("Home");
    JButton logoutButton = new JButton("Logout");
    JButton deleteButton = new JButton("Delete account");
    JButton followButton = new JButton("Follow");
    JButton editButton = new JButton("Save changes");
    JButton pictureButton = new JButton("Change profile pic");
    JButton userSuggestionButton = new JButton("Suggest users");
    JButton groupSuggestionButton = new JButton("Suggest groups");
    JButton createPostButton = new JButton("Create post");
    JButton createGroupButton = new JButton("Create group");
    JLabel followedLabel = new JLabel();
    JLabel groupsLabel = new JLabel();
    JLabel profilePicture = new JLabel();
    JLabel usernameLabel = new JLabel();
    JLabel firstNameLabel = new JLabel();
    JLabel lastNameLabel = new JLabel();
    JLabel accountTypeLabel = new JLabel();
    JLabel emailLabel = new JLabel();
    JLabel ageLabel = new JLabel();
    JLabel hobbiesLabel = new JLabel();
    JLabel hobbiesList = new JLabel();
    JTextField firstNameText = new JTextField();
    JTextField lastNameText = new JTextField();
    JTextField emailText = new JTextField();
    JTextField ageText = new JTextField();
    JTextField hobbiesText = new JTextField();

    public ProfilePanel(MainScreen screen, String username) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        setUser(username);

        followList();

        groupList();

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    private void setAllComponents() {
        if (screen.getMe().isFollowing(user))
            followButton.setText("Unfollow");
        homeButton.setBounds(681, 3,100,25);
        logoutButton.setBounds(3, 3,100,25);
        followButton.setBounds(3, 125, 100, 25);
        editButton.setBounds(3, 330, 150, 25);
        deleteButton.setBounds(3, 360,150,25);
        pictureButton.setBounds(3, 390, 150, 25);
        userSuggestionButton.setBounds(655, 501, 125, 25);
        groupSuggestionButton.setBounds(655, 531, 125, 25);
        createGroupButton.setBounds(3, 501, 150, 25);
        createPostButton.setBounds(3, 531, 150, 25);
        followedLabel.setText(user.getFollowed().size() + " Follow :");
        followedLabel.setBounds(681, 30, 100, 25);
        groupsLabel.setText(getGroupNumber() + " Groups :");
        groupsLabel.setBounds(681, 200, 100, 25);
        accountTypeLabel.setText(user.getPremium() ? "Premium" : "Standard");
        accountTypeLabel.setBounds(20, 30, 100, 25);
        profilePicture.setIcon(getProfilePicture());
        profilePicture.setBounds(16, 55, 64, 64);
        usernameLabel.setBounds(12, 150, 100, 25);
        firstNameLabel.setText(user.getFirstName());
        firstNameLabel.setBounds(12, 170, 100, 25);
        lastNameLabel.setText(user.getLastName());
        lastNameLabel.setBounds(12, 190, 100, 25);
        emailLabel.setText(user.getEmail());
        emailLabel.setBounds(12, 210, 150, 25);
        ageLabel.setText(user.getAge() + "");
        ageLabel.setBounds(12, 230, 100, 25);
        hobbiesLabel.setText("Hobbies :");
        hobbiesLabel.setBounds(110, 3, 70, 25);
        hobbiesList.setText(getHobbies());
        hobbiesList.setBounds(170, 3, 200, 25);
        firstNameText.setText(user.getFirstName());
        firstNameText.setBounds(12, 175, 100, 25);
        lastNameText.setText(user.getLastName());
        lastNameText.setBounds(12, 205, 100, 25);
        emailText.setText(user.getEmail());
        emailText.setBounds(12, 235, 150, 25);
        ageText.setText(user.getAge() + "");
        ageText.setBounds(12, 265, 100, 25);
        hobbiesText.setText(getHobbies());
        hobbiesText.setBounds(12, 295, 200, 25);
    }

    private void addAllComponents() {
        add(homeButton);
        add(logoutButton);
        add(followedLabel);
        add(groupsLabel);
        add(profilePicture);
        add(usernameLabel);
        add(accountTypeLabel);

        if (user.getUsername() !=  screen.getMe().getUsername()) {
            add(followButton);
            add(firstNameLabel);
            add(lastNameLabel);
            if (screen.getMe().isFollowing(user)) {
                add(emailLabel);
                add(ageLabel);
                add(hobbiesLabel);
                add(hobbiesList);
            }
        } else {
            add(firstNameText);
            add(lastNameText);
            add(emailText);
            add(ageText);
            add(hobbiesText);
            add(editButton);
            add(deleteButton);
            add(pictureButton);
            add(userSuggestionButton);
            add(groupSuggestionButton);
            add(createPostButton);
            if (user.getPremium())
                add(createGroupButton);
        }
    }

    private void listenButtons() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("homepage", null);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setMe(null);
                screen.switchPanel("login", null);
            }
        });

        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (followButton.getText() == "Follow") {
                    screen.getMe().follow(user);
                    followButton.setText("Unfollow");
                } else {
                    screen.getMe().unfollow(user);
                    followButton.setText("Follow");
                }
                updatePage();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editUser();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                    return;
                }
                updatePage();
            }
        });

        pictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    user.setProfilePicture(path);
                    updatePage();
                }
            }
        });

        userSuggestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
                System.out.println("Suggestion user");
            }
        });

        groupSuggestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
                System.out.println("Suggestion group");
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
                System.out.println("create post");
            }
        });

        createGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:
                System.out.println("create group");
            }
        });
    }

    public void setUser(String username) {
        if (username.equals("me"))
            user = screen.getMe();
        else {
            ArrayList<User> users = screen.getUsers();

            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    user = u;
                    break;
                }
            }
        }
        usernameLabel.setText(user.getUsername());
    }

    public void updatePage() {
        screen.switchPanel("profile", user.getUsername());
    }

    private void followList() {
        HashMap<String, User> followed = user.getFollowed();
        int xGap = 0;

        for (User follow : followed.values()) {
            JLabel followedListLabel = new JLabel();
            followedListLabel.setText(follow.getUsername());
            followedListLabel.setBounds(681, 55 + xGap, 400, 20);
            xGap += 20;
            add(followedListLabel);
        }
    }

    private int getGroupNumber() {
        int groupNumber = 0;

        for (Group group : screen.getGroups()) {
            if (group.getMembers().get(user.getUsername()) != null)
                groupNumber++;
        }
        return groupNumber;
    }

    private void groupList() {
        int xGap = 0;

        for (Group group : screen.getGroups()) {
            if (group.getMembers().get(user.getUsername()) != null) {
                JLabel groupListLabel = new JLabel();
                groupListLabel.setText(group.getName());
                groupListLabel.setBounds(681, 220 + xGap, 400, 20);
                xGap += 20;
                add(groupListLabel);
            }
        }
    }

    private void editUser() throws Exception {

        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@.+\\..+$");
        Matcher matcher = pattern.matcher(emailText.getText());
        ArrayList<String> hobbies = new ArrayList<>();

        if (ageText.getText().isEmpty())
            throw new Exception("Age is empty");
        int age = Integer.parseInt(ageText.getText());

        if (!matcher.matches())
            throw new Exception("Email is not valid");
        if (emailText.getText().length() > 40)
            throw new Exception("Email must be less than 40 characters");
        if (firstNameText.getText().length() < 1 || firstNameText.getText().length() > 22)
            throw new Exception("First Name must be between 1 and 22 characters");
        if (lastNameText.getText().length() < 1 || lastNameText.getText().length() > 22)
            throw new Exception("Last Name must be between 1 and 22 characters");
        if (age < 15 || age > 100)
            throw new Exception("Age must be between 15 and 100");
        if (hobbiesText.getText().length() > 0) {
            String[] hobbiesArray = hobbiesText.getText().split(" ");
            for (String hobby : hobbiesArray)
                hobbies.add(hobby);
        }

        user.setFirstName(firstNameText.getText());
        user.setLastName(lastNameText.getText());
        user.setEmail(emailText.getText());
        user.setAge(Integer.parseInt(ageText.getText()));
        user.setHobbies(hobbies);
    }

    /**
     * Deletes the account of the user from the application, and all of the data associated with it
     */
    private void deleteAccount() {
        ArrayList<User> users = screen.getUsers();
        ArrayList<Group> groups = screen.getGroups();
        ArrayList<Group> groupsToDelete = new ArrayList<>();

        screen.setMe(null);
        users.remove(user);

        // Search for groups that have the user as creator
        for (Group g : groups)
            if (g.getCreator().getUsername().equals(user.getUsername()))
                groupsToDelete.add(g);
        // Delete groups that have the user as creator
        for (Group g : groupsToDelete)
            groups.remove(g);
        // Delete user from groups
        for (Group g : groups) {
            HashMap<String, User> members = g.getMembers();
            members.remove(user.getUsername());
        }
        // Delete user from all users' followed list
        for (User u : users) {
            HashMap<String, User> followed = u.getFollowed();
            followed.remove(user.getUsername());
        }

        //TODO: remove posts
        screen.switchPanel("login", null);
    }

    private String getHobbies() {
        String hobbies = "";
        for (String s : user.getHobbies()) {
            hobbies += s + " ";
        }
        return hobbies;
    }
    
    private ImageIcon getProfilePicture() {
        Matcher matcherpng;
        Matcher matcherjpg;
        Matcher matcherjpeg;
        ImageIcon defaultPicture = new ImageIcon("assets/user.png");
        String picture = user.getProfilePicture();

        if (picture != null) {
            matcherpng = Pattern.compile(".png").matcher(picture);
            matcherjpg = Pattern.compile(".jpg").matcher(picture);
            matcherjpeg = Pattern.compile(".jpeg").matcher(picture);
            if (matcherpng.find() || matcherjpg.find() || matcherjpeg.find())
                return new ImageIcon(user.getProfilePicture());
        }
        return defaultPicture;
    }
}
