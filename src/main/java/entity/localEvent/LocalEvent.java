package entity.localEvent;

import entity.note.Note;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

public class LocalEvent extends Note implements LocalEventInterface {
    private LocalDate date;

    private LocalTime startTime = LocalTime.MIDNIGHT;                 // default starttime when user specifies whole-day

    private LocalTime endTime = LocalTime.of(23, 59);    // default starttime when user specifies whole-day


    /**
     * Constructor for LocalEvent
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
     * @param subEvents the IDs of the subEvents of the LocalEvent
     */
    public LocalEvent(int ID, String title, int userID, LocalDate date,
                      LocalTime startTime, LocalTime endTime,String location,
                      String description, boolean isWork, boolean pinned,
                      ArrayList<Integer> subEvents) {
        super(ID, title, userID, location, description, isWork, pinned, subEvents);

        this.startTime = startTime;

        this.endTime = endTime;

        this.date = date;
    }

    /**
     * Amend all attributes of the LocalEvent
     * @param title the title of the LocalEvent
     * @param date the date of the LocalEvent
     * @param startTime the start time of the LocalEvent
     * @param endTime the end time of the LocalEvent
     * @param location the location of the LocalEvent
     * @param description the description of the LocalEvent
     * @param isWork whether the LocalEvent is a work event
     * @param pinned whether the LocalEvent is pinned
     * @param subEvents the IDs of the subEvents of the LocalEvent
     */
    public void amendAllAttributes(String title, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   String location, String description, boolean isWork, boolean pinned, ArrayList<Integer> subEvents) {
        super.amendAllAttributes(title, location, description, isWork, pinned, subEvents);

        this.startTime = startTime;

        this.endTime = endTime;

        this.date = date;
    }

    /**
     * Get the date of the LocalEvent
     * @return the date of the LocalEvent
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the start time of the LocalEvent
     * @return the start time of the LocalEvent
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Get the end time of the LocalEvent
     * @return the end time of the LocalEvent
     */
    public LocalTime getEndTime() {
        return endTime;
    }
}
