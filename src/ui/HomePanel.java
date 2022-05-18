package src.ui;

import src.domain.Interface.Page;
import src.domain.Interface.Searchable;
import src.domain.User;
import src.domain.Group;
import src.domain.Content;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import java.text.DateFormat;

public class HomePanel extends JPanel implements Page {
    MainScreen screen;
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("en", "US"));
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
        logoutButton.setBounds(3, 3,100,25);
        profileButton.setBounds(681, 3,100,25);
        createPostButton.setBounds(3, 531, 150, 25);
        searchButton.setBounds(540, 3, 100, 25);
        searchBar.setBounds(140, 3, 400, 25);
    }

    /**
     * Add all the components to the panel
     */
    @Override
    public void addAllComponents() {
        add(logoutButton);
        add(profileButton);
        add(createPostButton);
        add(searchButton);
        add(searchBar);
    }

    /**
     * Listen to the buttons and perform the corresponding actions
     */
    @Override
    public void listenButtons() {
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

    /**
     * Display the 5 last posts that were created by the user I follow or created in the group I am in
     */
    private void postList() {
        int index = 0;

        ArrayList<Content> contents = getContentToDisplay();
        Collections.sort(contents);
        Collections.reverse(contents);

        for (Content c : contents) {
            if (index < 5) {
                JLabel titleLabel = new JLabel(c.getTitle());
                JLabel authorAndDateLabel = new JLabel(c.getAuthor() + " -  " + dateFormat.format(c.getDate()));
                JLabel contentLabel = new JLabel(c.getContent());
                JLabel pictureLabel = new JLabel(new ImageIcon(c.getPicture()));

                authorAndDateLabel.setBounds(190, 40 + index * 100, 400, 20);
                titleLabel.setBounds(190, 60 + (index * 100), 400, 20);
                contentLabel.setBounds(190, 85 + (index * 100), 400, 20);
                pictureLabel.setBounds(550, 40 + (index * 100), 64, 64);
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
     * Get the post of the user I follow and created in the group I am in
     */
    private ArrayList<Content> getContentToDisplay() {
        ArrayList<Content> contents = new ArrayList<Content>();

        for (User user : screen.getMe().getFollowed().values())
            for (Content content : user.getContents().values())
                contents.add(content);

        for (Group group : screen.getGroups())
            if (group.getMembers().containsKey(screen.getMe().getUsername()))
                for (Content content : group.getContents().values())
                    contents.add(content);

        return contents;
    }
}
