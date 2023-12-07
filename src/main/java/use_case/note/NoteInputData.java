package use_case.note;

import java.util.ArrayList;

/**
 * The input data for notes
 */
public class NoteInputData {
    private final int ID;

    private final String title;

    private final int userID;

    private final String location;

    private final String description;

    private final boolean isWork;

    private final boolean pinned;

    private final ArrayList<Integer> subEvents;

    /**
     * Creates a new NoteInputData object
     * @param ID The ID of the note
     * @param title The title of the note
     * @param userID The ID of the user who created the note
     * @param location The location of the note
     * @param description The description of the note
     * @param isWork Whether the note is a work note
     * @param pinned Whether the note is pinned
     * @param subEvents The IDs of the sub-events of the note
     */
    public NoteInputData (int ID, String title, int userID, String location,
                          String description, boolean isWork, boolean pinned,
                          ArrayList<Integer> subEvents) {
        this.ID = ID;

        this.title = title;

        this.userID = userID;

        this.location = location;

        this.description = description;

        this.pinned = pinned;

        this.isWork = isWork;

        this.subEvents = subEvents;
    }

    /**
     * Returns the ID of the note
     * @return The ID of the note
     */
    public int getID() {
        return ID;
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
    public int getUserID() {
        return userID;
    }

    /**
     * Returns the location of the note
     * @return The location of the note
     */
    public String getLocation() {
        return location;
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
}
