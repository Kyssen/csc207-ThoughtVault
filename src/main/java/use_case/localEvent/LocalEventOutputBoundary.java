package use_case.localEvent;

import java.util.ArrayList;

/**
 * The output boundary for local events
 */
public interface LocalEventOutputBoundary {
    /**
     * Updates the events list
     * @param localEventOutputData The event to be added or updated in the list
     */
    void UpdateEventsList(LocalEventOutputData localEventOutputData);

    /**
     * Displays the view for a successful event deletion
     */
    void DeleteEventSuccessView(int ID);

    /**
     * Displays the view for a failed event operation
     * @param errorMessage The error message to be displayed
     */
    void EventFailView(String errorMessage);

    /**
     * Displays a detailed view of the event
     * @param eventOutputData The event to be displayed
     */
    void DisplayEventDetailedView(LocalEventOutputData eventOutputData);

    /**
     * Displays the event creation view
     */
    void DisplayEventCreationView();

    /**
     * Displays the event edit view
     * @param eventOutputData The event to be edited
     */
    void DisplayEventEditView(LocalEventOutputData eventOutputData);

    /**
     * Displays all events
     * @param eventOutputDataArrayList The list of events to be displayed
     */
    void DisplayAllEvents(ArrayList<LocalEventOutputData> eventOutputDataArrayList);
}
