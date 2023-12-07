package entity;

import entity.note.Note;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The interface for the note and local event factory.
 */
public interface NoteFactoryInterface {
    /**
     * Create a note with the given parameters.
     *
     * @param ID          the ID of the note
     * @param title       the title of the note
     * @param userID      the ID of the user who owns the note
     * @param location    the location of the note
     * @param description the description of the note
     * @param isWork      whether the note is a work note
     * @param pinned      whether the note is pinned
     * @param descendants the IDs of the notes that are descendants of this note
     * @return the note created
     */
    public Note createNote(int ID, String title, int userID, String location,
                           String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> descendants);

    /**
     * Create a note with the given parameters.
     *
     * @param ID          the ID of the note
     * @param title       the title of the note
     * @param userID      the ID of the user who owns the note
     * @param date        the date of the note
     * @param startTime   the start time of the note
     * @param endTime     the end time of the note
     * @param location    the location of the note
     * @param description the description of the note
     * @param isWork      whether the note is a work note
     * @param pinned      whether the note is pinned
     * @param descendants the IDs of the notes that are descendants of this note
     * @return the note created
     */
    public Note createNote(int ID, String title, int userID, LocalDate date,
                           LocalTime startTime, LocalTime endTime,
                           String location, String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> descendants);
}
