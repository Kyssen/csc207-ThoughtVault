package interface_adapter.localEvent;

import use_case.localEvent.LocalEventOutputData;
import interface_adapter.localEvent.LocalEventState;
import interface_adapter.listView.ListViewModel;
import interface_adapter.listView.ListViewState;
import interface_adapter.ViewManagerModel;
import use_case.localEvent.LocalEventOutputBoundary;

import java.util.ArrayList;

import java.util.HashMap;

/**
 * Presenter for local events.
 */
public class LocalEventPresenter implements LocalEventOutputBoundary {
    private final LocalEventViewModel detailedLocalEventViewModel;

    private final LocalEventViewModel localEventCreationViewModel;

    private final LocalEventViewModel localEventEditViewModel;

    private final ListViewModel listViewModel;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for LocalEventPresenter.
     *
     * @param viewManagerModel             The view manager model.
     * @param detailedLocalEventViewModel  The detailed local event view model.
     * @param localEventCreationViewModel  The local event creation view model.
     * @param localEventEditViewModel      The local event edit view model.
     * @param listViewModel                The list view model.
     */
    public LocalEventPresenter(ViewManagerModel viewManagerModel,
                               LocalEventViewModel detailedLocalEventViewModel,
                               LocalEventViewModel localEventCreationViewModel,
                               LocalEventViewModel localEventEditViewModel,
                               ListViewModel listViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.detailedLocalEventViewModel = detailedLocalEventViewModel;
        this.localEventCreationViewModel = localEventCreationViewModel;
        this.localEventEditViewModel = localEventEditViewModel;
        this.listViewModel = listViewModel;
    }

    /**
     * Creates a local event state.
     *
     * @param localEventOutputData  The local event output data.
     * @return                      The local event state.
     */
    public LocalEventState CreateState(LocalEventOutputData localEventOutputData) {
        return new LocalEventState(
                localEventOutputData.getID(),
                localEventOutputData.getTitle(),
                localEventOutputData.getLocation(),
                localEventOutputData.getDescription(),
                localEventOutputData.getIsWork(),
                localEventOutputData.getPinned(),
                localEventOutputData.getSubEvents(),
                localEventOutputData.getDate(),
                localEventOutputData.getStartTime(),
                localEventOutputData.getEndTime(),
                localEventOutputData.getUserId());
    }

    /**
     * Updates the list of events in the list view model and displays the
     * event's detailed view.
     * @param localEventOutputData The local event output data.
     */
    @Override
    public void UpdateEventsList(LocalEventOutputData
                                                   localEventOutputData) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> listViewEvents =
                listViewState.getEvents();
        listViewEvents.put(localEventOutputData.getID(),
                localEventOutputData.getEventData());
        listViewState.setEvents(listViewEvents);
        listViewModel.firePropertyChanged();
        DisplayEventDetailedView(localEventOutputData);
    }

    /**
     * Updates the list of events in the list view model after deleting an
     * event and switches to the list view.
     * @param ID The ID of the event to be deleted.
     */
    @Override
    public void DeleteEventSuccessView(int ID) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> localEventViewModelArrayList =
                listViewState.getEvents();
        localEventViewModelArrayList.remove(ID);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays an error message.
     * @param errorMessage The error message.
     */
    @Override
    public void EventFailView(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays the detailed view of an event.
     * @param localEventOutputData The local event output data.
     */
    @Override
    public void DisplayEventDetailedView(LocalEventOutputData localEventOutputData) {
        LocalEventState localEventState = CreateState(localEventOutputData);
        detailedLocalEventViewModel.setState(localEventState);
        detailedLocalEventViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                detailedLocalEventViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays the view for creating a new event.
     */
    @Override
    public void DisplayEventCreationView() {
        LocalEventState localEventState = new LocalEventState();
        localEventCreationViewModel.setState(localEventState);
        localEventCreationViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                localEventCreationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays the view for editing an event.
     * @param eventOutputData The local event output data.
     */
    @Override
    public void DisplayEventEditView(LocalEventOutputData eventOutputData) {
        LocalEventState localEventState = CreateState(eventOutputData);
        localEventEditViewModel.setState(localEventState);
        localEventEditViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(localEventEditViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays the list view of events.
     * @param localEventOutputDataArrayList The list of local event output data.
     */
    @Override
    public void DisplayAllEvents(ArrayList<LocalEventOutputData>
                                             localEventOutputDataArrayList) {
        HashMap<Integer, HashMap<String, ?>> listViewEvents = new HashMap<>();
        for (LocalEventOutputData localEventOutputData :
                localEventOutputDataArrayList) {
            listViewEvents.put(localEventOutputData.getID(),
                    localEventOutputData.getEventData());
        }
        ListViewState listViewState = listViewModel.getState();
        listViewState.setEvents(listViewEvents);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
