package src.ui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import src.domain.User;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePanel extends JPanel {
    MainScreen screen;
    JButton logoutButton = new JButton("Logout");
    JButton profileButton = new JButton("Profile");
    JTextField searchBar = new JTextField();
    JButton searchButton = new JButton("Search");

    public HomePanel(MainScreen screen) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    private void setAllComponents() {
        logoutButton.setBounds(3, 3,100,25);
        profileButton.setBounds(681, 3,100,25);
        searchBar.setBounds(140, 3, 400, 25);
        searchButton.setBounds(540, 3, 100, 25);
    }

    private void addAllComponents() {
        add(logoutButton);
        add(profileButton);
        add(searchBar);
        add(searchButton);
    }

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
                User user;

                try {
                    user = searchUser();
                    if (user != null) {
                        screen.switchPanel("profile", user.getUsername());
                        return;
                    }
                    //Group search
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
    }

    private User searchUser() {
        ArrayList<User> users = screen.getUsers();

        for (User user : users) {
            if (user.getUsername().equals(searchBar.getText())) {
                return user;
            }
        }
        throw new IllegalArgumentException("User " + searchBar.getText() + " not found");
    }
}
