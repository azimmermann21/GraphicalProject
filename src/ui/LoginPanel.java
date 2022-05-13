package src.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.domain.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

public class LoginPanel extends JPanel {
    MainScreen screen;
    JLabel loginLabel = new JLabel();
    JLabel usernameLabel = new JLabel();
    JTextField usernameText = new JTextField("billylesinge");
    JLabel passwordLabel = new JLabel();
    JTextField passwordText = new JTextField("Louis123");
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register yourself !");


    public LoginPanel(MainScreen screen) {
        this.screen = screen;
        setSize(800, 600);
        setVisible(true);
        setLayout(null);
        
        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    private void setAllComponents() {
        loginLabel.setText("Login");
        loginLabel.setBounds(360,120,80,40);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        usernameLabel.setText("Username");
        usernameLabel.setBounds(370,180,80,40);
        usernameText.setBounds(325,215,150,18);
        passwordLabel.setText("Password");
        passwordLabel.setBounds(370,235,80,40);
        passwordText.setBounds(325,270,150,18);
        loginButton.setBounds(275,310,250,40);
        registerButton.setBounds(275,360,250,40);
    }

    private void addAllComponents() {
        add(loginLabel);
        add(usernameLabel);
        add(usernameText);
        add(passwordLabel);
        add(passwordText);
        add(loginButton);
        add(registerButton);
    }

    private void listenButtons() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = null;
                try {
                    user = login();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                    return;
                }
                screen.setMe(user);
                screen.switchPanel("homepage", null);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.switchPanel("register", null);
            }
        });
    }

    private User login() throws Exception {
        ArrayList<User> users = screen.getUsers();

        for (User user : users) {
            if (user.getUsername().equals(usernameText.getText())) {
                if (user.getPassword().equals(passwordText.getText()))
                    return user;
                else
                    throw new Exception("Password is incorrect");
            }
        }
        throw new Exception("Username is incorrect");
    }
}