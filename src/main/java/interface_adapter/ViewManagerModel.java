package interface_adapter;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

/**
 * This class is a Model for the ViewManager.
 */
public class ViewManagerModel {
    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(
            this);

    /**
     * Getter for the active view.
     * @return The active view.
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Setter for the active view.
     * @param activeView The active view.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Fires a property change.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null,
                this.activeViewName);
    }

    /**
     * Adds a property change listener.
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
