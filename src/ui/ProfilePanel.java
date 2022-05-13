package src.ui;

import javax.swing.JPanel;

import src.domain.User;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfilePanel extends JPanel {
    MainScreen screen;
    User user;
    JButton homeButton = new JButton("Home");
    JLabel usernameLabel = new JLabel();
    JButton followButton = new JButton("Follow");
    JLabel followersLabel = new JLabel();
    JLabel followedLabel = new JLabel();
    JLabel followerLabel = new JLabel();

    public ProfilePanel(MainScreen screen, String username) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        setUser(username);

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    private void setAllComponents() {
        if (screen.getMe().isFollowing(user))
            followButton.setText("Unfollow");
        homeButton.setBounds(3, 3,100,25);
        usernameLabel.setBounds(300, 300, 400, 25);
        followButton.setBounds(300, 350, 100, 25);
        followersLabel.setText("Followers: (" + user.getFollowers().size() + ")");
        followersLabel.setBounds(300, 400, 400, 25);
        followedLabel.setText("Followed: (" + user.getFollowed().size() + ")");
        followedLabel.setBounds(300, 450, 400, 25);
    }

    private void addAllComponents() {
        if (user.getUsername() !=  screen.getMe().getUsername())
            add(followButton);
        add(homeButton);
        add(usernameLabel);
        add(followersLabel);
        add(followedLabel);
    }

    private void listenButtons() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("homepage", null);
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
                updateFollowers();
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

    public void updateFollowers() {
        followersLabel.setText("Followers: (" + user.getFollowers().size() + ")");
    }

    // Display the followers and followed of the user
}
