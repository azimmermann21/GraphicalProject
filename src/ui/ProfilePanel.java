package src.ui;

import src.domain.Interface.Page;
import src.domain.User;
import src.domain.Content;
import src.domain.Group;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;

import java.text.DateFormat;

public class ProfilePanel extends JPanel implements Page {
    MainScreen screen;
    User user;
    String[] countries = {"France", "Germany", "Spain", "Italy", "United Kingdom", "United States", "Canada", "Australia", "New Zealand", "Turkey", "Russia", "China", "Japan", "India", "Brazil", "Argentina", "Mexico", "Portugal", "Poland", "Sweden", "Denmark", "Norway"};
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("en", "US"));
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
    JButton modifyPostButton = new JButton("Modify post");
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
    JLabel countryLabel = new JLabel();
    JLabel hobbiesList = new JLabel();
    JTextField firstNameText = new JTextField();
    JTextField lastNameText = new JTextField();
    JTextField emailText = new JTextField();
    JTextField ageText = new JTextField();
    JTextField hobbiesText = new JTextField();
    JComboBox<String> countryText = new JComboBox<String>(countries);
    JTextField postToModifyText = new JTextField();

    /**
     * Create the panel
     */
    public ProfilePanel(MainScreen screen, String username) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        setUser(username);

        followList();

        groupList();

        if (user.getUsername().equals(screen.getMe().getUsername()) || screen.getMe().isFollowing(user))
            postList();

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    /**
     * Set all the components properties and their bounds
     */
    @Override
    public void setAllComponents() {
        if (screen.getMe().isFollowing(user))
            followButton.setText("Unfollow");
        homeButton.setBounds(681, 3,100,25);
        logoutButton.setBounds(3, 3,100,25);
        followButton.setBounds(3, 125, 100, 25);
        editButton.setBounds(3, 360, 150, 25);
        deleteButton.setBounds(325, 531,150,25);
        pictureButton.setBounds(3, 390, 150, 25);
        userSuggestionButton.setBounds(655, 501, 125, 25);
        groupSuggestionButton.setBounds(655, 531, 125, 25);
        createGroupButton.setBounds(3, 501, 150, 25);
        createPostButton.setBounds(3, 531, 150, 25);
        modifyPostButton.setBounds(3, 460, 150, 25);
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
        countryLabel.setText(user.getCountry());
        countryLabel.setBounds(12, 250, 100, 25);
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
        countryText.setSelectedIndex(getCountryIndex());
        countryText.setBounds(12, 325, 100, 25);
        postToModifyText.setBounds(3, 432, 150, 25);
    }

    /**
     * Add all the components to the panel
     */
    @Override
    public void addAllComponents() {
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
                add(countryLabel);
                add(hobbiesLabel);
                add(hobbiesList);
            }
        } else {
            add(firstNameText);
            add(lastNameText);
            add(emailText);
            add(ageText);
            add(countryText);
            add(hobbiesText);
            add(postToModifyText);
            add(editButton);
            add(deleteButton);
            add(pictureButton);
            add(userSuggestionButton);
            add(groupSuggestionButton);
            add(createPostButton);
            add(modifyPostButton);
            if (user.getPremium())
                add(createGroupButton);
        }
    }

    /**
     * Listen to the buttons and perform the corresponding actions
     */
    @Override
    public void listenButtons() {
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
                suggestUsers();
            }
        });

        groupSuggestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suggestGroups();
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("createPost", null);
            }
        });

        createGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("createGroup", null);
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
     * Set the user of the page
     * @param username the username of the user
     */
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

    /**
     * Updates the page by refreshing it
     */
    public void updatePage() {
        screen.switchPanel("profile", user.getUsername());
    }

    /**
     * Display the users followed by the user 
     */
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

    /**
     * Get the number of groups the user is in
     * @return int number of groups
     */
    private int getGroupNumber() {
        int groupNumber = 0;

        for (Group group : screen.getGroups()) {
            if (group.getMembers().get(user.getUsername()) != null)
                groupNumber++;
        }
        return groupNumber;
    }

    /**
     * Display the groups that the user is in
     */
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

    /**
     * Edits the user's information
     * @throws Exception if the age, email, first name, last name are not valid
     */
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
            String hobbiesString = hobbiesText.getText().toLowerCase();
            String[] hobbiesArray = hobbiesString.split(" ");
            for (String hobby : hobbiesArray)
                hobbies.add(hobby);
        }

        user.setFirstName(firstNameText.getText());
        user.setLastName(lastNameText.getText());
        user.setEmail(emailText.getText());
        user.setAge(Integer.parseInt(ageText.getText()));
        user.setCountry(countryText.getSelectedItem().toString());
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
        // Search and delete contents that have the user as author in all groups
        for (Group g : groups) {
            ArrayList<Content> contentsToDelete = new ArrayList<>();    
            for (Content c : g.getContents().values())
                if (c.getAuthor().equals(user.getUsername()))
                    contentsToDelete.add(c);
            // Delete contents that have the user as author
            for (Content c : contentsToDelete)
                g.getContents().remove(c.getTitle());
        }

        screen.switchPanel("login", null);
    }

    /**
     * Get a string of the user's hobbies, separated by spaces
     * @return String of the user's hobbies
     */
    private String getHobbies() {
        String hobbies = "";
        for (String s : user.getHobbies()) {
            hobbies += s + " ";
        }
        return hobbies;
    }
    
    /**
     * Get an imageIcon of the user's profile picture if it exists and is valid, otherwise return null
     * @return ImageIcon of the user's profile picture
     */
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

    /**
     * Modify a post
     * @throws Exception if the post to modify is empty
     * @throws Exception if the post to modify is not in the user
     */
    private void modifyPost() throws Exception {
        if (postToModifyText.getText().isEmpty())
            throw new Exception("The post can't be empty");
        if (user.getContents().get(postToModifyText.getText()) == null)
            throw new Exception("Post \"" + postToModifyText.getText() + "\" does not exist");
        else
            screen.switchPanel("post", postToModifyText.getText());
    }

    /**
     * Display the 5 last posts that the user has created
     */
    private void postList() {
        int index = 0;

        ArrayList<Content> contents = new ArrayList<Content>(user.getContents().values());
        Collections.sort(contents);
        Collections.reverse(contents);

        for (Content c : contents) {
            if (index < 5) {
                JLabel titleLabel = new JLabel(c.getTitle());
                JLabel authorAndDateLabel = new JLabel(c.getAuthor() + " -  " + dateFormat.format(c.getDate()));
                JLabel contentLabel = new JLabel(c.getContent());
                JLabel pictureLabel = new JLabel(new ImageIcon(c.getPicture()));

                authorAndDateLabel.setBounds(220, 50 + index * 100, 400, 20);
                titleLabel.setBounds(220, 70 + (index * 100), 400, 20);
                contentLabel.setBounds(220, 95 + (index * 100), 400, 20);
                pictureLabel.setBounds(580, 50 + (index * 100), 64, 64);
                index++;
                add(authorAndDateLabel);
                add(titleLabel);
                add(contentLabel);
                add(pictureLabel);
            } else
                break;
        }
    }

    /**
     * Get the index of the country of the user in the country list
     * @return index of the country of the user in the country list
     */
    private int getCountryIndex() {
        for (int i = 0; i < countries.length; i++)
            if (countries[i].equals(user.getCountry()))
                return i;
        return 0;
    }

    /**
     * Display the suggested users to the user
     */
    private void suggestUsers() {
        ArrayList<User> users = screen.getUsers();
        ArrayList<User> alreadyFollowed = new ArrayList<User>(user.getFollowed().values());
        TreeMap<Integer, ArrayList<String>> suggestions = new TreeMap<Integer, ArrayList<String>>();
        String suggestedString = "";
        int i = 0;

        users.remove(user);
        users.removeAll(alreadyFollowed);

        // Set the scores of each user
        for (User u : users) {
            int score = 0;
            if (u.getCountry().equals(user.getCountry()))
                score++;
            if (user.getAge() - 5 <= u.getAge() && u.getAge() <= user.getAge() + 5)
                score++;
            for (String s : u.getHobbies())
                if (user.getHobbies().contains(s))
                    score++;
            ArrayList<String> suggestionsList = suggestions.get(score);
            if (suggestionsList == null)
                suggestionsList = new ArrayList<String>();
            suggestionsList.add(u.getUsername());
            suggestions.put(score, suggestionsList);
        }

        // Get the list of suggestions with the highest score
        ArrayList<String> suggestionsList = new ArrayList<String>();
        for (ArrayList<String> userList : suggestions.values())
            for (String s : userList)
                suggestionsList.add(s);
        Collections.reverse(suggestionsList);

        // Display the suggestions
        for (String s : suggestionsList) {
            if (i < 5)
                suggestedString += s + "\n";
            else
                break;
            i++;
        }
        JOptionPane.showMessageDialog(this, suggestedString, "Suggested Users", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display the suggested groups to the user
     */
    private void suggestGroups() {
        ArrayList<Group> groups = screen.getGroups();
        ArrayList<Group> notMyGroups = new ArrayList<Group>();
        TreeMap<Integer, ArrayList<String>> suggestions = new TreeMap<Integer, ArrayList<String>>();
        String suggestedString = "";
        int i = 0;

        // Get the list of groups that the user is not in
        for (Group g : groups)
            if (!g.getMembers().containsKey(user.getUsername()))
                notMyGroups.add(g);

        // Set the scores of each group
        for (Group g : notMyGroups) {
            int score = 0;
            if (g.getCountry().equals(user.getCountry()))
                score++;
            for (String s : g.getHobbies())
                if (user.getHobbies().contains(s))
                    score++;
            ArrayList<String> suggestionsList = suggestions.get(score);
            if (suggestionsList == null)
                suggestionsList = new ArrayList<String>();
            suggestionsList.add(g.getName());
            suggestions.put(score, suggestionsList);
        }

        // Get the list of suggestions with the highest score
        ArrayList<String> suggestionsList = new ArrayList<String>();
        for (ArrayList<String> groupList : suggestions.values())
            for (String s : groupList)
                suggestionsList.add(s);
        Collections.reverse(suggestionsList);

        // Display the suggestions
        for (String s : suggestionsList) {
            if (i < 2)
                suggestedString += s + "\n";
            else
                break;
            i++;
        }
        JOptionPane.showMessageDialog(this, suggestedString, "Suggested Groups", JOptionPane.INFORMATION_MESSAGE);
    }
}
