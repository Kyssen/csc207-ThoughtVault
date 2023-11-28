package use_case;

import InputData.EventInputData;
import OutputData.EventOutputData;
import data_access.EventsDataAccessObject;
import entity.LocalEvent;

import java.time.LocalTime;
import java.util.ArrayList;

public class LocalEventInteractor implements LocalEventInputBoundary{
    private LocalEventOutputBoundary localEventOutputBoundary;
    private EventsDataAccessObject eventsDataAccessObject;
    public LocalEventInteractor(EventsDataAccessObject eventsDataAccessObject, LocalEventOutputBoundary localEventOutputBoundary){
        this.eventsDataAccessObject = eventsDataAccessObject;
        this.localEventOutputBoundary = localEventOutputBoundary;
    }
    @Override
    public void createEvent(EventInputData eventInputData) {
        LocalEvent event = new LocalEvent(eventInputData.getID(), eventInputData.getTitle(),
                eventInputData.getUserID(), eventInputData.getDate(), eventInputData.getStartTime(),
                eventInputData.getEndTime(), eventInputData.getLocation(), eventInputData.getDescription(),
                eventInputData.getIsWork(), eventInputData.getPinned(), eventInputData.getSubEvents());

        eventsDataAccessObject.save(event);
        localEventOutputBoundary.CreateEventSuccessView(eventInputData.getTitle());
    }

    @Override
    public void editEvent(EventInputData eventInputData) {

    }

    @Override
    public void deleteEvent(EventInputData eventInputData) {

    }

    @Override
    public EventOutputData getEvent(String eventID, String userID) {
        return null;
    }

    @Override
    public ArrayList<EventOutputData> getAllEvents(String userID) {
        return null;
    }
}
