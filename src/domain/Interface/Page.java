package src.domain.Interface;

/**
 * Interface for classes that are pages
 */
public interface Page {
    /**
     * Set all the components properties and their bounds
     */
    public abstract void setAllComponents();

    /**
     * Add all the components to the panel
     */
    public void addAllComponents();

    /**
     * Listen to the buttons and perform the corresponding actions
     */
    public void listenButtons();
}
