package entity.note;

import entity.NoteFactoryInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A factory class that creates a Note object.
 */
public class NoteFactory implements NoteFactoryInterface {
    /**
     * Creates a Note object with the given parameters.
     *
     * @param ID          the ID of the Note
     * @param title       the title of the Note
     * @param userID      the ID of the User who owns the Note
     * @param location    the location of the Note
     * @param description the description of the Note
     * @param isWork      whether the Note is a work-related Note
     * @param pinned      whether the Note is pinned
     * @param descendants the IDs of the descendants of the Note
     * @return a Note object with the given parameters
     */
    @Override
    public Note createNote(int ID, String title, int userID, String location,
                           String description, boolean isWork, boolean pinned,
                           ArrayList<Integer> descendants) {
        return new Note(ID, title, userID, location, description, isWork,
                pinned, descendants);
    }

    /**
     * Do nothing.
     *
     * @param ID          the ID of the Note
     * @param title       the title of the Note
     * @param userID      the ID of the User who owns the Note
     * @param date        the date of the Note
     * @param startTime   the start time of the Note
     * @param endTime     the end time of the Note
     * @param location    the location of the Note
     * @param description the description of the Note
     * @param isWork      whether the Note is a work-related Note
     * @param pinned      whether the Note is pinned
     * @param descendants the IDs of the descendants of the Note
     * @return null
     */
    @Override
    public Note createNote(int ID, String title, int userID, LocalDate date,
                           LocalTime startTime, LocalTime endTime,
                           String location, String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> descendants) {
        return null;
    }
}
