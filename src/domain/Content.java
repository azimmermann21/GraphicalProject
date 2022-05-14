package src.domain;

public class Content {
    private String title;
    private String content;
    private String author;
    private String picture;

    public Content(String title, String content, String author, String picture) {
        this.title = title;
        this.content = content;
        this.author = author;
        if (picture != null)
            this.picture = picture;
    }

    /**
     * @return the title of the content
     * Get the title of the content
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     * Set the title of the content
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content of the content
     * Get the content of the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     * Set the content of the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the author of the content
     * Get the author of the content
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     * Set the author of the content
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the picture of the content
     * Get the picture of the content
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     * Set the picture of the content
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}