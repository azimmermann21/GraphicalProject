package src.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    MainScreen screen;
    JLabel profileLabel = new JLabel();
    JButton logoutButton = new JButton("Logout");

    public HomePanel(MainScreen screen) {
        this.screen = screen;
        setSize(800, 600);
        setBackground(new java.awt.Color(0, 150, 255));
        setVisible(true);

        setAllComponents();

        addAllComponents();

        listenButtons();
    }

    private void setAllComponents() {
        profileLabel.setText("Homepage");
        profileLabel.setSize(200, 50);
    }

    private void addAllComponents() {
        add(profileLabel);
        add(logoutButton);
    }

    private void listenButtons() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setMe(null);
                screen.switchPanel("login");
            }
        });
    }
}
