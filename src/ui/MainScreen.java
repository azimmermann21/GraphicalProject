package src.ui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import src.domain.User;

public class MainScreen extends JFrame {
    private HashMap <String, JPanel> panels = new HashMap<String, JPanel>();
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

        panels.put("login", new LoginPanel(this));
        panels.put("profile", new HomePanel(this));
        panels.put("register", new RegisterPanel(this));
        setContentPane(panels.get("login"));
        createDummyUsers();
    }

    public void switchPanel(String panelName) {
        System.out.println(me);
        setContentPane(panels.get(panelName));
        revalidate();
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
