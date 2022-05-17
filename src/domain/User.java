package src.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class User implements Searchable {
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
    private HashMap<String, Content> contents = new HashMap<String, Content>();

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
     * Get the username of the user
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the email of the user
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the first name of the user
     * @return the firstName of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstName of the user
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the user
     * @return the lastName of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastName of the user
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the age of the user
     * @return the age of the user
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of the user
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the premium status of the user
     * @return if the user is premium
     */
    public boolean getPremium() {
        return premium;
    }

    /**
     * Set the premium status of the user
     * @param premium the premium to set
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * Get the profile picture of the user
     * @return the profile picture of the user
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Set the profile picture of the user
     * @param profilePicture the profile picture to set
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Get the hobbies of the user
     * @return the hobbies of the user
     */
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * Set the hobbies of the user
     * @param hobbies the hobbies to set
     */
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * Get the followed users of the user
     * @return the followed users of the user
     */
    public HashMap<String, User> getFollowed() {
        return followed;
    }

    /**
     * Set the followed users of the user
     * @param followed the followed users to set
     */
    public void setFollowed(HashMap<String, User> followed) {
        this.followed = followed;
    }

    /**
     * Get the contents the user created
     * @return the contents of the user
     */
    public HashMap<String, Content> getContents() {
        return contents;
    }

    /**
     * Set the contents the user created
     * @param contents the contents to set
     */
    public void setContents(HashMap<String, Content> contents) {
        this.contents = contents;
    }

    /**
     * Add the user to the followed users
     * @param user the user to follow
     */
    public void follow(User user) {
        followed.put(user.getUsername(), user);
    }

    /**
     * Remove the user from the followed users
     * @param user the user to unfollow
     */
    public void unfollow(User user) {
        followed.remove(user.getUsername());
    }

    /**
     * Check if the user is followed
     * @param user the user to check if the user is followed
     * @return if the user is followed
     */
    public boolean isFollowing(User user) {
        return followed.containsKey(user.getUsername());
    }

    /**
     * Add the content to the contents of the user
     * @param content the content to add
     */
    public void addContent(Content content) {
        contents.put(content.getTitle(), content);
    }
}
