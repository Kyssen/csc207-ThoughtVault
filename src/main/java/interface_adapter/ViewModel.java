package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * This class is an abstract ViewModel for the ViewManager.
 */
public abstract class ViewModel {
    private final String viewName;

    /**
     * Constructor for the ViewModel.
     *
     * @param viewName The name of the view.
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Getter for the name of the view.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Fires a property change.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds a property change listener.
     *
     * @param listener The listener to be added.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener
                                                           listener);
}
