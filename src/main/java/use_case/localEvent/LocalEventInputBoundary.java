package use_case.localEvent;

/**
 * This interface is used to create, edit, delete, and display events.
 * It is used by the LocalEventController.
 */
public interface LocalEventInputBoundary {
    /**
     * Displays the event creation view.
     */
    void DisplayEventCreationView();

    /**
     * Displays the event edit view.
     * @param eventID The ID of the event to be edited.
     */
    void DisplayEventEditView(int eventID);

    /**
     * Creates an event.
     * @param eventInputData The data of the event to be created.
     */
    void CreateEvent(LocalEventInputData eventInputData);

    /**
     * Edits an event.
     * @param eventInputData The data of the event to be edited.
     */
    void EditEvent(LocalEventInputData eventInputData);

    /**
     * Deletes an event.
     * @param eventID The ID of the event to be deleted.
     */
    void DeleteEvent(int eventID);

    /**
     * Displays the event detailed view.
     * @param eventID The ID of the event to be displayed.
     */
    void DisplayEventDetailedView(int eventID);

    /**
     * Gets all events of a user.
     * @param userID The ID of the user whose events are to be displayed.
     */
    void GetAllEvents(int userID);
}
