package src.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.domain.Group;
import src.domain.User;
import src.domain.Content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Font;

public class RegisterPanel extends JPanel {
    MainScreen screen;
    JLabel registerLabel = new JLabel();
    JLabel usernameLabel = new JLabel();
    JTextField usernameText = new JTextField();
    JLabel passwordLabel = new JLabel();
    JPasswordField passwordText = new JPasswordField();
    JLabel emailLabel = new JLabel();
    JTextField emailText = new JTextField();
    JLabel firstNameLabel = new JLabel();
    JTextField firstNameText = new JTextField();
    JLabel lastNameLabel = new JLabel();
    JTextField lastNameText = new JTextField();
    JLabel ageLabel = new JLabel();
    JTextField ageText = new JTextField();
    JButton registerButton = new JButton("Register");
    JButton loginButton = new JButton("Already have an account ? Login");

    /**
     * Create the panel
     */
    public RegisterPanel(MainScreen screen) {
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
        registerLabel.setText("Register");
        registerLabel.setBounds(340,20,160,40);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        usernameLabel.setText("Username");
        usernameLabel.setBounds(370,80,80,40);
        usernameText.setBounds(325,115,150,18);
        passwordLabel.setText("Password");
        passwordLabel.setBounds(370,135,80,40);
        passwordText.setBounds(325,170,150,18);
        emailLabel.setText("Email");
        emailLabel.setBounds(370,190,80,40);
        emailText.setBounds(325,225,150,18);
        firstNameLabel.setText("First name");
        firstNameLabel.setBounds(370,245,80,40);
        firstNameText.setBounds(325,280,150,18);
        lastNameLabel.setText("Last name");
        lastNameLabel.setBounds(370,300,80,40);
        lastNameText.setBounds(325,335,150,18);
        ageLabel.setText("Age");
        ageLabel.setBounds(370,355,80,40);
        ageText.setBounds(325,390,150,18);
        registerButton.setBounds(275,430,250,40);
        loginButton.setBounds(275,480,250,40);
    }
    
    /**
     * Adds all components to the panel
     */
    private void addAllComponents() {
        add(registerLabel);
        add(usernameLabel);
        add(usernameText);
        add(passwordLabel);
        add(passwordText);
        add(emailLabel);
        add(emailText);
        add(firstNameLabel);
        add(firstNameText);
        add(lastNameLabel);
        add(lastNameText);
        add(ageLabel);
        add(ageText);
        add(registerButton);
        add(loginButton);
    }

    /**
     * Listen for button presses and do the appropriate action.
     */
    private void listenButtons() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user;
                try {
                    user = register();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                    return;
                }
                ArrayList<User> users = screen.getUsers();
                users.add(user);
                screen.setUsers(users);
                screen.setMe(user);
                screen.switchPanel("homepage", null);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("login", null);
            }
        });
    }

    /**
     * Register a new user
     * @return the new user
     * @throws Exception if one of the fields is empty or not valid
     */
    private User register() throws Exception {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@.+\\..+$");
        Matcher matcher = pattern.matcher(emailText.getText());

        if (ageText.getText().isEmpty())
            throw new Exception("Age is empty");
        int age = Integer.parseInt(ageText.getText());

        if (usernameText.getText().length() < 1 || usernameText.getText().length() > 22)
            throw new Exception("Username must be between 1 and 22 characters");
        if (!checkUsername(usernameText.getText()))
            throw new Exception("Username already exists");
        if (passwordText.getText().length() < 5)
            throw new Exception("Password must be at least 5 characters");
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

        return new User(usernameText.getText(), passwordText.getText(), emailText.getText(), firstNameText.getText(), lastNameText.getText(), age, false);
    }

    /**
     * Checks if the username is already taken
     * @param username the username to check
     * @return true if the username is not taken, false otherwise
     */
    private boolean checkUsername(String username) {
        ArrayList<User> users = screen.getUsers();
        ArrayList<Group> groups = screen.getGroups();
        HashMap<String, Content> content = screen.getAllContent();
        
        //TODO: Test this for content

        for(User user : users)
            if(user.getUsername().equals(username))
                return false;
        for(Group group : groups)
            if(group.getName().equals(username))
                return false;
        if (content.containsKey(username))
            return false;

        return true;
    }
}