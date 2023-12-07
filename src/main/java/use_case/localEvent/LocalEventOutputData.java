package use_case.localEvent;

import use_case.note.NoteOutputData;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The output data for local events
 */
public class LocalEventOutputData extends NoteOutputData {
    private final LocalDate date;
    // default startTime when user specifies whole-day event

    private LocalTime startTime = LocalTime.MIDNIGHT;

    private LocalTime endTime = LocalTime.of(23, 59);

    /**
     * Creates a new LocalEventOutputData object
     * @param ID The ID of the event
     * @param title The title of the event
     * @param userID The ID of the user who created the event
     * @param date The date of the event
     * @param startTime The start time of the event
     * @param endTime The end time of the event
     * @param location The location of the event
     * @param description The description of the event
     * @param isWork Whether the event is a work event
     * @param pinned Whether the event is pinned
     * @param subEvents The IDs of the sub-events of the event
     */
    public LocalEventOutputData(int ID, String title, int userID, LocalDate date,
                                LocalTime startTime, LocalTime endTime,
                                String location, String description, boolean isWork,
                                boolean pinned, ArrayList<Integer> subEvents) {
        super(ID, title, userID, location, description, isWork, pinned,
                subEvents);

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;
    }

    /**
     * Returns a dictionary of the event data
     * @return The dictionary of the event data
     */
    public HashMap<String, Object> getEventData() {
        HashMap<String, Object> data = super.getNoteData();
        data.put("date", getDate());
        data.put("startTime", getStartTime());
        data.put("endTime", getEndTime());
        return data;
    }

    /**
     * Returns the date of the event
     * @return The date of the event
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the start time of the event
     * @return The start time of the event
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event
     * @return The end time of the event
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
