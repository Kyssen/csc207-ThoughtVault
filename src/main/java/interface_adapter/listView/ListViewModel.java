package interface_adapter.listView;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

/**
 * This class is the ViewModel for the ListView.
 * It contains the state of the ListView.
 * It is used by the ListViewPresenter to update the ListView.
 */
public class ListViewModel extends ViewModel {
    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String IMPORT_EVENTS_BUTTON_LABEL = "Import Events";
    public static final String EXPORT_EVENTS_BUTTON_LABEL = "Export Events";
    private ListViewState state;

    /**
     * Constructor for the ListViewModel.
     */
    public ListViewModel() {
        super("List view");
    }

    /**
     * Sets the state of the ListView.
     * @param state The new state of the ListView.
     */
    public void setState(ListViewState state) {
        this.state = state;
    }

    /**
     * Gets the state of the ListView.
     * @return The state of the ListView.
     */
    public ListViewState getState() {
        return state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(
            this);

    /**
     * Fires a property change event.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener.
     * @param listener The listener to add.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
