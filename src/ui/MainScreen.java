package src.ui;

import java.util.ArrayList;

import javax.swing.*;

import src.domain.User;

public class MainScreen extends JFrame {
    private ArrayList<User> users;
    private User me;

    public static void main(String[] args) {
        new MainScreen();
    }

    public MainScreen() {
        setTitle("Social Network");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        setContentPane(new LoginPanel(this));
        createDummyUsers();
    }

    public void switchPanel(String panelName, String arg) {
        if (panelName == "login")
            setContentPane(new LoginPanel(this));
        else if (panelName == "register")
            setContentPane(new RegisterPanel(this));
        else if (panelName == "homepage")
            setContentPane(new HomePanel(this));
        else if (panelName == "profile")
            setContentPane(new ProfilePanel(this, arg));
        repaint();
    }

    public void createDummyUsers() {
        users = new ArrayList<User>();
        users.add(new User("johndoe", "John123", "john@doe.com", "John", "Doe", 21, false));
        users.add(new User("billylesinge", "Louis123", "billy@lesinge.com", "Billy", "Lesinge", 8, false));
        users.add(new User("louisleboss", "Louis123", "louis@leboss.com", "Louis", "Christner", 21, true));
        users.add(new User("hgvleboss", "Louis123", "hgv@leboss.com", "Hoang-Giang", "Vo", 21, true));
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }
}
