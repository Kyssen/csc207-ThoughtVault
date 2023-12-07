package entity.note;

import java.util.ArrayList;

/**
 * Interface for Note class
 * @see Note
 */
public interface NoteInterface {
    /**
     * @return ID of the note
     */
    int getID();

    /**
     * @return title of the note
     */
    String getTitle();

    /**
     * @return ID of the user who created the note
     */
    int getUserID();

    /**
     * @return ID of the parent note
     */
    String getLocation();

    /**
     * @return description of the note
     */
    String getDescription();

    /**
     * @return date of creation of the note
     */
    boolean isWork();

    /**
     * @return date of creation of the note
     */
    boolean getPinned();

    /**
     * @return date of creation of the note
     */
    ArrayList<Integer> getDescendants();

    /**
     * @param descendantID ID of the note
     */
    void removeDescendants(int descendantID);
}
