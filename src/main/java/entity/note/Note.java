package entity.note;

import entity.LabelCat;

import java.util.ArrayList;

/**
 * A Note is a NoteInterface that has a title, a location, a description,
 * a label, and a list of descendant IDs.
 */
public class Note implements NoteInterface {
    private final int ID;
    private String title;
    private final int userID;
    private String location;
    private String description;
    private LabelCat label;
    private boolean pinned;
    private ArrayList<Integer> descendantsID;           // make sure the descendant IDs are valid when instantiating a Note

    /**
     * Constructs a new Note with the given ID, title, userID, location, description, label, and descendants.
     *
     * @param ID          the ID of this Note
     * @param title       the title of this Note
     * @param userID      the userID of this Note
     * @param location    the location of this Note
     * @param description the description of this Note
     * @param isWork      the label of this Note
     * @param pinned      the pinned status of this Note
     * @param descendants the descendants of this Note
     */
    public Note(int ID, String title, int userID, String location, String description, boolean isWork, boolean pinned,
                ArrayList<Integer> descendants) {
        this.ID = ID;
        this.userID = userID;
        amendAllAttributes(title, location, description, isWork, pinned, descendants);
    }

    /**
     * Edits the Note to have the given title, location, description, label,
     * pinned and descendants.
     *
     * @param title       the title of this Note
     * @param location    the location of this Note
     * @param description the description of this Note
     * @param isWork      the label of this Note
     * @param pinned      the pinned status of this Note
     * @param descendants the descendants of this Note
     */
    public void amendAllAttributes(String title, String location, String description, boolean isWork, boolean pinned,
                                   ArrayList<Integer> descendants) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.pinned = pinned;
        label = isWork ? LabelCat.WORK : LabelCat.PERSONAL;
        this.descendantsID = new ArrayList<>(descendants);
    }

    /**
     * Returns the ID of this Note.
     * @return the ID of this Note
     */
    @Override
    public int getID() {
        return ID;
    }

    /**
     * Returns the title of this Note.
     * @return the title of this Note
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns the userID of this Note.
     * @return the userID of this Note
     */
    @Override
    public int getUserID() {
        return userID;
    }

    /**
     * Returns the location of this Note.
     * @return the location of this Note
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * Returns the description of this Note.
     * @return the description of this Note
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the label of this Note.
     * @return the label of this Note
     */
    @Override
    public boolean isWork() {
        return label == LabelCat.WORK;
    }

    /**
     * Returns the pinned status of this Note.
     * @return the pinned status of this Note
     */
    @Override
    public boolean getPinned() {
        return pinned;
    }

    /**
     * Returns the descendants of this Note.
     * @return the descendants of this Note
     */
    @Override
    public ArrayList<Integer> getDescendants() {
        return descendantsID;
    }

    /**
     * Removes the descendant with the given ID from this Note.
     * @param descendantID the ID of the descendant to be removed
     */
    @Override
    public void removeDescendants(int descendantID) {
        descendantsID.remove(descendantID);
    }
}
