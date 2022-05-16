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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PostPanel extends JPanel {
    MainScreen screen;
    Content post;
    String picture;
    JButton homeButton = new JButton("Home");
    JButton editPostButton = new JButton("Edit Post");
    JButton changePictureButton = new JButton("Change Picture");
    JLabel postTitleLabel = new JLabel();
    JTextField postTitleText = new JTextField();
    JLabel postContentLabel = new JLabel();
    JTextField PostContentText = new JTextField();
    JLabel postPicture = new JLabel();

    //TODO: add author and date

    /**
     * Create the panel
     */
    public PostPanel(MainScreen screen, String contentName) {
        this.screen = screen;
        setContent(contentName);

        setSize(800, 600);
        setVisible(true);
        setLayout(null);

        picture = post.getPicture();

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    /**
     * Set all the components properties and their bounds
     */
    private void setAllComponents() {
        homeButton.setBounds(681, 3,100,25);
        editPostButton.setBounds(300, 390, 200, 30);
        postPicture.setBounds(570, 170, 128, 128);
        postPicture.setIcon(new ImageIcon(picture));
        postTitleText.setText(post.getTitle());
        postTitleText.setBounds(140, 200, 200, 30);
        PostContentText.setText(post.getContent());
        PostContentText.setBounds(140, 250, 400, 30);
        changePictureButton.setBounds(300, 350, 200, 30);
        postTitleLabel.setText(post.getTitle());
        postTitleLabel.setBounds(140, 200, 100, 30);
        postContentLabel.setText(post.getContent());
        postContentLabel.setBounds(140, 250, 400, 30);
    }

    /**
     * Add all the components to the panel
     */
    private void addAllComponents() {
        add(homeButton);
        add(postPicture);

        if (post.getAuthor().equals(screen.getMe().getUsername())) {
            add(postTitleText);
            add(PostContentText);
            add(changePictureButton);
            add(editPostButton);
        } else {
            add(postTitleLabel);
            add(postContentLabel);
        }
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

        editPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editPost();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });

        changePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choosePicture();
            }
        });
    }

    /**
     * Edit a post
     */
    private void editPost() throws Exception {
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
        
        ArrayList<Group> groups = screen.getGroups();
        HashMap<String, Content> contents;

        // Update the post if it is stored in user
        contents = screen.getMe().getContents();
        for (Content content : contents.values()) {
            if (content.getTitle().equals(post.getTitle())) {
                if (!content.getAuthor().equals(screen.getMe().getUsername()))
                    throw new Exception("You cannot edit this post");
                content.setTitle(postTitleText.getText());
                content.setContent(PostContentText.getText());
                content.setPicture(picture);
            }
        }

        // Update the post if it is stored in group
        for (Group group : groups) {
            contents = group.getContents();
            for (Content content : contents.values()) {
                if (content.getTitle().equals(post.getTitle())) {
                    if (!content.getAuthor().equals(screen.getMe().getUsername()))
                        throw new Exception("You cannot edit this post");
                    content.setTitle(postTitleText.getText());
                    content.setContent(PostContentText.getText());
                    content.setPicture(picture);
                }
            }
        }

        screen.switchPanel("homepage", null);
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

        //TODO: Test this for content

        if (title.equals(post.getTitle()))
            return true;
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
     * Set the content to create a post in
     * @param title the title of the content
     */
    private void setContent(String title) {
        HashMap<String, Content> contents = screen.getAllContent();

        post = contents.get(title);
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
