package src.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private boolean premium;
    private String profilePicture;
    private ArrayList<String> hobbies;
    private HashMap<String, User> followers = new HashMap<String, User>();
    private HashMap<String, User> followed = new HashMap<String, User>();

    public User(String username, String password, String email, String firstName, String lastName, int age, boolean premium) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.premium = premium;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public boolean getPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public HashMap<String, User> getFollowers() {
        return followers;
    }

    public void setFollowers(HashMap<String, User> followers) {
        this.followers = followers;
    }

    public HashMap<String, User> getFollowed() {
        return followed;
    }

    public void setFollowed(HashMap<String, User> followed) {
        this.followed = followed;
    }

    public boolean isFollowing(User user) {
        return followed.containsKey(user.getUsername());
    }

    public void follow(User user) {
        followed.put(user.getUsername(), user);
        user.addFollower(this);
    }

    public void unfollow(User user) {
        followed.remove(user.getUsername());
        user.removeFollower(this);
    }

    public void addFollower(User user) {
        followers.put(user.getUsername(), user);
    }

    public void removeFollower(User user) {
        followers.remove(user.getUsername());
    }
}
