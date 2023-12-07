package interface_adapter.listView;

import java.util.HashMap;

/**
 * A class that stores the state of the list view.
 */
public class ListViewState {
    private int userId;
    private HashMap<Integer, HashMap<String, ?>> events;
    private HashMap<Integer, HashMap<String, ?>> notes;

    /**
     * Constructs a new ListViewState object.
     */
    public ListViewState() {
        events = new HashMap<Integer, HashMap<String, ?>>();

        notes = new HashMap<Integer, HashMap<String, ?>>();
    }

    /**
     * Returns the events.
     *
     * @return the events.
     */
    public HashMap<Integer, HashMap<String, ?>> getEvents() {
        return events;
    }

    /**
     * Returns the notes.
     *
     * @return the notes.
     */
    public HashMap<Integer, HashMap<String, ?>> getNotes() {
        return notes;
    }

    /**
     * Sets the events.
     *
     * @param events the events.
     */
    public void setEvents(HashMap<Integer, HashMap<String, ?>> events) {
        this.events = events;
    }

    /**
     * Sets the notes.
     *
     * @param notes the notes.
     */
    public void setNotes(HashMap<Integer, HashMap<String, ?>> notes) {
        this.notes = notes;
    }
    public int getUserId(){return this.userId;}
    public void setUserId(int userId){this.userId = userId;}
}
