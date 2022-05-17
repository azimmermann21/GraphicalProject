package src.ui;

import src.domain.Content;
import src.domain.Group;
import src.domain.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CreatePostPanel extends JPanel {
    MainScreen screen;
    Group group;
    String picture;
    JButton homeButton = new JButton("Home");
    JButton createPostButton = new JButton("Create Post");
    JButton addPictureButton = new JButton("Add Picture");
    JLabel createAPostLabel = new JLabel("Create a Post");
    JLabel postTitleLabel = new JLabel("Title :");
    JTextField postTitleText = new JTextField();
    JLabel postContentLabel = new JLabel("Content:");
    JTextField PostContentText = new JTextField();
    JLabel postPicture = new JLabel();

    /**
     * Create the panel
     */
    public CreatePostPanel(MainScreen screen, String groupName) {
        this.screen = screen;
        setGroup(groupName);

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
        if (group != null) {
            createAPostLabel.setText("Create a post for group " + group.getName());
            createAPostLabel.setBounds(270, 50, 400, 30);
        } else
            createAPostLabel.setBounds(340, 50, 400, 30);

        homeButton.setBounds(681, 3,100,25);
        createPostButton.setBounds(300, 390, 200, 30);
        addPictureButton.setBounds(300, 350, 200, 30);
        createAPostLabel.setFont(new Font("Arial", Font.BOLD, 20));
        postTitleLabel.setBounds(70, 200, 100, 30);
        postTitleText.setBounds(140, 200, 200, 30);
        postContentLabel.setBounds(70, 250, 100, 30);
        PostContentText.setBounds(140, 250, 400, 30);
        postPicture.setBounds(570, 170, 128, 128);
    }

    /**
     * Add all the components to the panel
     */
    private void addAllComponents() {
        add(homeButton);
        add(createPostButton);
        add(addPictureButton);
        add(createAPostLabel);
        add(postTitleLabel);
        add(postTitleText);
        add(postContentLabel);
        add(PostContentText);
        add(postPicture);
    }

    /**
     * Listen to the buttons and perform the corresponding action
     */
    private void listenButtons() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("homepage", null);
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createPost();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });

        addPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosePicture();
            }
        });
    }

    /**
     * Create a post
     */
    private void createPost() throws Exception {
        if (postTitleText.getText().equals(""))
            throw new Exception("Post title cannot be empty");
        if (postTitleText.getText().length() < 1 || postTitleText.getText().length() > 22)
            throw new Exception("Post title must be between 1 and 22 characters");
        if (!checkTitle(postTitleText.getText()))
            throw new Exception("Post title already exists");
        if (PostContentText.getText().equals(""))
            throw new Exception("Post content cannot be empty");
        if (PostContentText.getText().length() < 1 || PostContentText.getText().length() > 50)
            throw new Exception("Post content must be between 1 and 50 characters");
        
        Content content = new Content(postTitleText.getText(), PostContentText.getText(), screen.getMe().getUsername(), picture);

        if (group != null)
            group.addContent(content);
        else
            screen.getMe().addContent(content);

        screen.switchPanel("homepage", content.getTitle());
    }

    /**
     * Checks if the title is already taken
     * @param title the title to check
     * @return true if the title is not taken, false otherwise
     */
    private boolean checkTitle(String title) {
        ArrayList<User> users = screen.getUsers();
        ArrayList<Group> groups = screen.getGroups();
        HashMap<String, Content> content = screen.getAllContent();

        for(User user : users)
            if(user.getUsername().equals(title))
                return false;
        for(Group group : groups)
            if(group.getName().equals(title))
                return false;
        if (content.containsKey(title))
            return false;

        return true;
    }

    /**
     * Set the group to create a post in
     * @param name the name of the group
     */
    private void setGroup(String name) {
        ArrayList<Group> groups = screen.getGroups();

        if (name != null)
            for (Group g : groups)
                if (g.getName().equals(name))
                    group = g;
    }

    /**
     * Choose a picture and set it to the post picture
     */
    private void choosePicture() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            postPicture.setIcon(new ImageIcon(path));
            picture = path;
            revalidate();
            repaint();
        }
    }
}
