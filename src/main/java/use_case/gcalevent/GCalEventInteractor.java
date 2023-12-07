package use_case.gcalevent;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import entity.localEvent.LocalEvent;
import data_access.EntriesDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.Date;

public class GCalEventInteractor implements GCalEventInputBoundary {
    final GCalEventDataAccessInterface userDataAccessObject;
    final GCalEventOutputBoundary gCalEventPresenter;
    final EntriesDataAccessInterface entriesDataAccessObject;

    public GCalEventInteractor(GCalEventDataAccessInterface userDataAccessObject,
                               GCalEventOutputBoundary gCalEventPresenter,
                               EntriesDataAccessInterface entriesDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.gCalEventPresenter = gCalEventPresenter;
        this.entriesDataAccessObject = entriesDataAccessObject;
    }

    @Override
    public boolean importEvent(String eventId) throws IOException {
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();

        if (!userDataAccessObject.eventExists(eventId)) {
            gCalEventPresenter.prepareFailView(eventId + " :Event does not exist in your calendar.");
            return false;
        } else {
            Event event = calendar.events().get(calendarId, eventId).execute();
            System.out.println(event.getSummary());

            GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(eventId, calendar, calendarId);
            gCalEventPresenter.prepareSuccessView(gCalEventOutputData);

            return true;
        }
    }

    @Override
    public boolean exportEvent(int localEventId) throws IOException {
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        LocalEvent localEvent = (LocalEvent) entriesDataAccessObject.getByID(localEventId);

        Event exportedEvent = new Event();
        exportedEvent.setICalUID(calendarId);

        LocalDateTime startDateTime = LocalDateTime.of(localEvent.getDate(), localEvent.getStartEndTIme()[0]);
        Date startDate = Date.from(startDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
        exportedEvent.setStart(new EventDateTime().setDateTime(start));

        LocalDateTime endDateTime = LocalDateTime.of(localEvent.getDate(), localEvent.getStartEndTIme()[1]);
        Date endDate = Date.from(endDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        exportedEvent.setEnd(new EventDateTime().setDateTime(end));

        exportedEvent.setDescription(localEvent.getDescription());
        exportedEvent.setSummary(localEvent.getTitle());
        exportedEvent.setLocation(localEvent.getLocation());

        // Event importedEvent = calendar.events().calendarImport(calendarId, exportedEvent).execute();
        exportedEvent = calendar.events().insert(calendarId, exportedEvent).execute();
        System.out.println(exportedEvent.getId());

        GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(exportedEvent.getId(), calendar, calendarId);
        gCalEventPresenter.prepareSuccessView(gCalEventOutputData);

        return true;
    }

    public ArrayList<GCalEventInputData> getAllEvents() throws IOException {
        ArrayList<GCalEventInputData> listOfEvents = new ArrayList<>();
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        String pageToken = null;

        do {
            Events events = calendar.events().list(calendarId).setMaxResults(10).setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                GCalEventInputData inputData = new GCalEventInputData(event.getId(), calendar);
                listOfEvents.add(inputData);
                System.out.println(event.getSummary());
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        return listOfEvents;
    }

    public boolean switchToHome(){
        gCalEventPresenter.switchToHome();
        return true;
    }


}
