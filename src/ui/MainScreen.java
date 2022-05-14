package src.ui;

import java.util.ArrayList;

import javax.swing.*;

import src.domain.User;

public class MainScreen extends JFrame {
    private ArrayList<User> users = new ArrayList<User>();
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
        User johndoe = new User("johndoe", "John123", "john@doe.com", "John", "Doe", 21, false);
        User janedoe = new User("janedoe", "Jane123", "jane@doe.com", "Jane", "Doe", 21, false);
        User billylesinge = new User("billylesinge", "Louis123", "billy@lesinge.com", "Billy", "Lesinge", 15, false);
        User louisleboss = new User("louisleboss", "Louis123", "louis@leboss.com", "Louis", "Christner", 21, true);
        User hgvleboss = new User("hgvleboss", "Louis123", "hgv@leboss.com", "Hoang-Giang", "Vo", 21, true);
        User iboleboss = new User("iboleboss", "Louis123", "ibo@leboss.com", "Ibrahim", "Akgul", 21, true);
        User matleboss = new User("matleboss", "Louis123", "mat@leboss.com", "Matthieu", "Houlle", 21, true);
        
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("Football");
        hobbies.add("Coding");
        hobbies.add("Reading");
        louisleboss.setHobbies(hobbies);

        users.add(johndoe);
        users.add(janedoe);
        users.add(billylesinge);
        users.add(louisleboss);
        users.add(hgvleboss);
        users.add(iboleboss);
        users.add(matleboss);
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
