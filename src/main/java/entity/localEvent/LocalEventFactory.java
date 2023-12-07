package entity.localEvent;

import entity.NoteFactoryInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A factory that creates LocalEvent objects.
 */
public class LocalEventFactory implements NoteFactoryInterface {
    /**
     * Do nothing.
     * @param ID
     * @param title
     * @param userID
     * @param location
     * @param description
     * @param isWork
     * @param pinned
     * @param descendants
     * @return null
     */
    @Override
    public LocalEvent createNote(int ID, String title, int userID,
                                 String location, String description,
                                 boolean isWork, boolean pinned,
                                 ArrayList<Integer> descendants) {
        return null;
    }

    /**
     * Create a LocalEvent object.
     * @param ID the ID of the LocalEvent
     * @param title the title of the LocalEvent
     * @param userID the ID of the user who owns the LocalEvent
     * @param date the date of the LocalEvent
     * @param startTime the start time of the LocalEvent
     * @param endTime the end time of the LocalEvent
     * @param location the location of the LocalEvent
     * @param description the description of the LocalEvent
     * @param isWork whether the LocalEvent is a work event
     * @param pinned whether the LocalEvent is pinned
     * @param descendants the IDs of the descendants of the LocalEvent
     * @return a LocalEvent object
     */
    @Override
    public LocalEvent createNote(int ID, String title, int userID,
                                 LocalDate date, LocalTime startTime,
                                 LocalTime endTime, String location,
                                 String description, boolean isWork,
                                 boolean pinned, ArrayList<Integer>
                                         descendants) {
        return new LocalEvent(ID, title, userID, date, startTime, endTime,
                location, description, isWork, pinned, descendants);
    }
}
