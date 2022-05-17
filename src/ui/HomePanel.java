package src.ui;

import src.domain.User;
import src.domain.Group;
import src.domain.Content;
import src.domain.Searchable;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class HomePanel extends JPanel {
    MainScreen screen;
    JButton logoutButton = new JButton("Logout");
    JButton profileButton = new JButton("Profile");
    JButton createPostButton = new JButton("Create post");
    JButton searchButton = new JButton("Search");
    JTextField searchBar = new JTextField();

    /**
     * Create the panel
     */
    public HomePanel(MainScreen screen) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    /**
     * Set all the components properties and their bounds
     */
    private void setAllComponents() {
        logoutButton.setBounds(3, 3,100,25);
        profileButton.setBounds(681, 3,100,25);
        createPostButton.setBounds(3, 531, 150, 25);
        searchButton.setBounds(540, 3, 100, 25);
        searchBar.setBounds(140, 3, 400, 25);
    }

    /**
     * Add all the components to the panel
     */
    private void addAllComponents() {
        add(logoutButton);
        add(profileButton);
        add(createPostButton);
        add(searchButton);
        add(searchBar);
    }

    /**
     * Listen to the buttons and perform the corresponding actions
     */
    private void listenButtons() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setMe(null);
                screen.switchPanel("login", null);
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("profile", "me");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Searchable result;   

                try {
                    result = search();

                    if (result instanceof User) {
                        User user = (User) result;
                        screen.switchPanel("profile", user.getUsername());
                    } else if (result instanceof Group) {
                        Group group = (Group) result;
                        screen.switchPanel("group", group.getName());
                    } else if (result instanceof Content) {
                        Content content = (Content) result;
                        screen.switchPanel("post", content.getTitle());
                    }
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("createPost", null);
            }
        });
    }

    /**
     * Search for a user, group, content by name or title
     */
    private Searchable search() {
        ArrayList<User> users = screen.getUsers();
        ArrayList<Group> groups = screen.getGroups();

        for (User user : users)
            if (user.getUsername().equals(searchBar.getText()))
                return user;

        for (Group group : groups)
            if (group.getName().equals(searchBar.getText()))
                return group;

        for (User user : screen.getMe().getFollowed().values())
            for (Content content : user.getContents().values())
                if (content.getTitle().equals(searchBar.getText()))
                    return content;
        
        for (Group group : groups)
            if (group.getMembers().containsKey(screen.getMe().getUsername()))
                for (Content content : group.getContents().values())
                    if (content.getTitle().equals(searchBar.getText()))
                        return content;

        throw new IllegalArgumentException("No User, Group or Content named " + searchBar.getText() + " has been found");
    }
}
