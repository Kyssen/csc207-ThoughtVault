package interface_adapter.localEvent;

import interface_adapter.note.NoteState;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

/**
 * State for local events.
 */
public class LocalEventState extends NoteState {
    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    public LocalEventState() {
    }

    /**
     * Constructor for LocalEventState.
     *
     * @param id            The event's ID
     * @param title         The title.
     * @param location      The location.
     * @param description   The description.
     * @param isWork        Whether the local event is work.
     * @param pinned        Whether the local event is pinned.
     * @param subEvents     The sub events.
     * @param date          The date.
     * @param startTime     The start time.
     * @param endTime       The end time.
     * @param userId        The event creator's ID
     */
    public LocalEventState(int id, String title, String location, String description,
                           boolean isWork, boolean pinned,
                           ArrayList<Integer> subEvents, LocalDate date,
                           LocalTime startTime, LocalTime endTime, int userId) {
        super(id, title, location, description, isWork, pinned, subEvents, userId);

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;
    }

    /**
     * Gets the date.
     *
     * @return  The date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the start time.
     *
     * @return  The start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time.
     *
     * @return  The end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
