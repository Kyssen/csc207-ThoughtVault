package entity.localEvent;

import entity.note.NoteInterface;

import java.time.LocalDate;

import java.time.LocalTime;

/**
 * Interface for LocalEvent
 * @see LocalEvent
 */
public interface LocalEventInterface extends NoteInterface {
    /**
     * @return the date of the event
     */
    LocalDate getDate();

    /**
     * @return the start time of the event
     */
    LocalTime getStartTime();

    /**
     * @return the end time of the event
     */
    LocalTime getEndTime();
}
