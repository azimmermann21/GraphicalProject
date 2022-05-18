package src.ui;

import src.domain.Interface.Page;
import src.domain.Content;
import src.domain.Group;
import src.domain.User;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGroupPanel extends JPanel implements Page {
    MainScreen screen;
    User user;
    String[] countries = {"France", "Germany", "Spain", "Italy", "United Kingdom", "United States", "Canada", "Australia", "New Zealand", "Turkey", "Russia", "China", "Japan", "India", "Brazil", "Argentina", "Mexico", "Portugal", "Poland", "Sweden", "Denmark", "Norway"};
    JLabel createAgroupLabel = new JLabel("Create a group");
    JLabel groupNameLabel = new JLabel("Name:");
    JTextField groupNameText = new JTextField();
    JLabel countryLabel = new JLabel("Country:");
    JComboBox<String> countryComboBox = new JComboBox<String>(countries);
    JLabel hobbiesLabel = new JLabel("Hobbies:");
    JTextField hobbiesText = new JTextField();
    JButton createGroupButton = new JButton("Create Group");
    JButton homeButton = new JButton("Home");

    /**
     * Create the panel
     */
    public CreateGroupPanel(MainScreen screen) {
        this.screen = screen;
        this.user = screen.getMe();

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
    @Override
    public void setAllComponents() {
        createAgroupLabel.setBounds(300, 50, 200, 30);
        createAgroupLabel.setFont(new Font("Arial", Font.BOLD, 20));
        groupNameLabel.setBounds(300, 100, 100, 30);
        groupNameText.setBounds(400, 100, 200, 30);
        countryLabel.setBounds(300, 150, 100, 30);
        countryComboBox.setBounds(400, 150, 200, 30);
        hobbiesLabel.setBounds(300, 200, 100, 30);
        hobbiesText.setBounds(400, 200, 200, 30);
        createGroupButton.setBounds(400, 250, 200, 30);
        homeButton.setBounds(681, 3,100,25);
    }

    /**
     * Add all the components to the panel
     */
    @Override
    public void addAllComponents() {
        add(createAgroupLabel);
        add(groupNameLabel);
        add(groupNameText);
        add(countryLabel);
        add(countryComboBox);
        add(hobbiesLabel);
        add(hobbiesText);
        add(createGroupButton);
        add(homeButton);
    }

    /**
     * Listen to the buttons and perform the corresponding action
     */
    @Override
    public void listenButtons() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("homepage", null);
            }
        });

        createGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createGroup();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
    }

    /**
     * Create a group
     */
    private void createGroup() throws Exception {
        ArrayList<String> hobbies = new ArrayList<String>();

        if (groupNameText.getText().equals(""))
            throw new Exception("Group name cannot be empty");
        if (groupNameText.getText().length() < 1 || groupNameText.getText().length() > 22)
            throw new Exception("Group name must be between 1 and 22 characters");
        if (!checkName(groupNameText.getText()))
            throw new Exception("Group name already exists");
        if (hobbiesText.getText().length() > 0) {
            String hobbiesString = hobbiesText.getText().toLowerCase();
            String[] hobbiesArray = hobbiesString.split(" ");
            for (String hobby : hobbiesArray)
                hobbies.add(hobby);
        }

        Group group = new Group(groupNameText.getText(), countryComboBox.getSelectedItem().toString(), user, hobbies);
        screen.getGroups().add(group);
        screen.switchPanel("group", groupNameText.getText());
    }

    /**
     * Checks if the name is already taken
     * @param name the name to check
     * @return true if the name is not taken, false otherwise
     */
    private boolean checkName(String name) {
        ArrayList<User> users = screen.getUsers();
        ArrayList<Group> groups = screen.getGroups();
        HashMap<String, Content> content = screen.getAllContent();

        for(User user : users)
            if(user.getUsername().equals(name))
                return false;
        for(Group group : groups)
            if(group.getName().equals(name))
                return false;
        if (content.containsKey(name))
            return false;

        return true;
    }
}
