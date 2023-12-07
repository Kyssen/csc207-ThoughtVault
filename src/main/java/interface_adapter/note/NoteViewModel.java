package interface_adapter.note;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

/**
 * This class is a ViewModel for the NoteView.
 * It contains the state of the view.
 * It also contains the labels for the view.
 */
public class NoteViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Note view";

    public static final String TITLE_LABEL = "Title: ";

    public static final String LOCATION_LABEL = "Location: ";

    public static final String DESCRIPTION_LABEL = "Description: ";

    public static final String IS_WORK_LABEL = "Is Work: ";

    public static final String PINNED_LABEL = "Pinned: ";

    public static final String SUB_EVENTS_LABEL = "Sub-events: ";

    private NoteState state = new NoteState();

    /**
     * Constructor for the NoteViewModel.
     * @param viewName The name of the view.
     */
    public NoteViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Setter for the state of the view.
     */
    public void setState(NoteState state) {
        this.state = state;
    }

    /**
     * Getter for the state of the view.
     * @return The state of the view.
     */
    public NoteState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(
            this);

    /**
     * Fires a property change.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null,
                this.state);
    }

    /**
     * Adds a property change listener.
     * @param listener The property change listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setUserId(int userId) {
        this.state.setUserId(userId);
    }
}
