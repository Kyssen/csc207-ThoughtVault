package interface_adapter.localEvent;

import use_case.localEvent.LocalEventInputData;

import use_case.localEvent.LocalEventInputBoundary;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

/**
 * This class is the controller for the local event use case.
 */
public class LocalEventController {
    private final LocalEventInputBoundary localEventInteractor;

    /**
     * This constructor creates a new LocalEventController object.
     *
     * @param localEventInteractor The interactor for the local event use case.
     */
    public LocalEventController(LocalEventInputBoundary localEventInteractor) {
        this.localEventInteractor = localEventInteractor;
    }

    /**
     * This method creates a new local event.
     *
     * @param id          The ID of the event.
     * @param title       The title of the event.
     * @param userID      The ID of the user who owns the event.
     * @param date        The date of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     * @param location    The location of the event.
     * @param description The description of the event.
     * @param isWork      Whether the event is a work event.
     * @param pinned      Whether the event is pinned.
     * @param subEvents   The IDs of the sub-events of the event.
     */
    public void createEvent(int id, String title, int userID, LocalDate date,
                            LocalTime startTime, LocalTime endTime,
                            String location, String description, boolean isWork,
                            boolean pinned, ArrayList<Integer> subEvents) {
        LocalEventInputData eventInputData = new LocalEventInputData(id, title, userID,
                date, startTime, endTime, location, description, isWork, pinned,
                subEvents);

        localEventInteractor.CreateEvent(eventInputData);
    }

    /**
     * This method edits an existing local event.
     *
     * @param id          The ID of the event.
     * @param title       The title of the event.
     * @param userID      The ID of the user who owns the event.
     * @param date        The date of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     * @param location    The location of the event.
     * @param description The description of the event.
     * @param isWork      Whether the event is a work event.
     * @param pinned      Whether the event is pinned.
     * @param subEvents   The IDs of the sub-events of the event.
     */
    public void editEvent(int id, String title, int userID, LocalDate date,
                          LocalTime startTime, LocalTime endTime,
                          String location, String description, boolean isWork,
                          boolean pinned, ArrayList<Integer> subEvents) {
        LocalEventInputData eventInputData = new LocalEventInputData(id, title, userID,
                date, startTime, endTime, location, description, isWork, pinned,
                subEvents);

        localEventInteractor.EditEvent(eventInputData);
    }

    public void displayEventDetailedView(int eventID){localEventInteractor.DisplayEventDetailedView(eventID);}


    /**
     * This method deletes an existing local event.
     *
     * @param eventID The ID of the event.
     */
    public void deleteEvent(int eventID) {
        localEventInteractor.DeleteEvent(eventID);
    }
}
