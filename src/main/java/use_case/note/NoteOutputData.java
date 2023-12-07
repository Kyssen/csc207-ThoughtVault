package use_case.note;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The output data for notes
 */
public class NoteOutputData {
    private final int ID;

    private final String title;

    private final String location;

    private final String description;

    private final boolean isWork;

    private final boolean pinned;

    private final ArrayList<Integer> subEvents;

    /**
     * Creates a new NoteOutputData object
     * @param id The ID of the note
     * @param title The title of the note
     * @param userID The ID of the user who created the note
     * @param location The location of the note
     * @param description The description of the note
     * @param isWork Whether the note is a work note
     * @param pinned Whether the note is pinned
     * @param subEvents The IDs of the sub-events of the note
     */
    public NoteOutputData(int id, String title, int userID, String location,
                          String description, boolean isWork, boolean pinned,
                          ArrayList<Integer> subEvents) {
        this.ID = id;

        this.title = title;

        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subEvents = subEvents;
    }

    /**
     * Returns a dictionary of the note data
     * @return The dictionary of the note data
     */
    public HashMap<String, Object> getNoteData() {
        HashMap<String, Object> data = new HashMap<String, Object>();

        data.put("title", getTitle());

        data.put("isWork", getIsWork());

        data.put("pinned", getPinned());

        return data;
    }

    /**
     * Returns the pinned status of the note
     * @return The pinned status of the note
     */
    public boolean getPinned() {
        return pinned;
    }

    /**
     * Returns the work status of the note
     * @return The work status of the note
     */
    public boolean getIsWork() {
        return isWork;
    }

    /**
     * Returns the ID of the note
     * @return The ID of the note
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the title of the note
     * @return The title of the note
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the IDs of the sub-events of the note
     * @return The IDs of the sub-events of the note
     */
    public ArrayList<Integer> getSubEvents() {
        return subEvents;
    }

    /**
     * Returns the description of the note
     * @return The description of the note
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the ID of the user who created the note
     * @return The ID of the user who created the note
     */
    public String getLocation() {
        return location;
    }

    public int getUserId() {
        return this.userID;
    }
}
