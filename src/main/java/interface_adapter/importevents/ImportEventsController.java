package interface_adapter.importevents;

import use_case.gcalevent.GCalEventInputBoundary;
import use_case.gcalevent.GCalEventInputData;

import java.io.IOException;
import java.util.ArrayList;

public class ImportEventsController {
    final GCalEventInputBoundary gCalEventUseCaseInteractor;
    public ImportEventsController(GCalEventInputBoundary gCalEventUseCaseInteractor){
        this.gCalEventUseCaseInteractor = gCalEventUseCaseInteractor;
    }
    public void execute(String eventId) throws IOException {
        gCalEventUseCaseInteractor.importEvent(eventId);
    }

    public ArrayList<GCalEventInputData> getAllEvents() throws IOException {
        return gCalEventUseCaseInteractor.getAllEvents();
    }

    public void switchToHome(){
        gCalEventUseCaseInteractor.switchToHome();
    }
//    public List<String> getAllEventsTitles() throws IOException {
//        ArrayList<GCalEventInputData> listOfEvents = gCalEventUseCaseInteractor.getAllEvents();
//        ArrayList<String> eventsTitles = new ArrayList<>();
//        for (GCalEventInputData event : listOfEvents) {
//            eventsTitles.add(event.getTitle());
//        }
//        return eventsTitles;
//    }
}
