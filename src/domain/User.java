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
    private ArrayList<String> hobbies = new ArrayList<String>();
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

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     * Set the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     * Set the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     * Set the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     * Set the firstName of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     * Set the lastName of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age of the user
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     * Set the age of the user
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return if the user is premium
     */
    public boolean getPremium() {
        return premium;
    }

    /**
     * @param premium the premium to set
     * Set if the user is premium
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * @return the profile picture of the user
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture the profile picture to set
     * Set the profile picture of the user
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return the hobbies of the user
     */
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     * Set the hobbies of the user
     */
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * @return the followed users of the user
     */
    public HashMap<String, User> getFollowed() {
        return followed;
    }

    /**
     * @param followed the followed users to set
     * Set the followed users of the user
     */
    public void setFollowed(HashMap<String, User> followed) {
        this.followed = followed;
    }

    /**
     * @param user the user to follow
     * Add the user to the followed users
     */
    public void follow(User user) {
        followed.put(user.getUsername(), user);
    }

    /**
     * @param user the user to unfollow
     * Remove the user from the followed users
     */
    public void unfollow(User user) {
        followed.remove(user.getUsername());
    }

    /**
     * @param user the user to check if the user is followed
     * @return if the user is followed
     * Check if the user is followed
     */
    public boolean isFollowing(User user) {
        return followed.containsKey(user.getUsername());
    }
}
