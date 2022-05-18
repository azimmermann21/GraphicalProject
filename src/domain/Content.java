package src.domain;

import java.util.Date;

import src.domain.Interface.Searchable;

public class Content implements Searchable, Comparable<Content> {
    private String title;
    private String content;
    private String author;
    private String picture;
    private Date date;

    public Content(String title, String content, String author, String picture) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = new Date();
        if (picture != null)
            this.picture = picture;
    }

    /**
     * Get the title of the content
     * @return the title of the content
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the content
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the content of the content
     * @return the content of the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content of the content
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the author of the content
     * @return the author of the content
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author of the content
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get the picture of the content
     * @return the picture of the content
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Set the picture of the content
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Get the date of the content
     * @return the date of the content
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date of the content
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Compare two contents by their date
     * @param content the content to compare
     */
    @Override
    public int compareTo(Content content) {
        return getDate().compareTo(content.getDate());
    }

}
