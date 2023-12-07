package interface_adapter.gcalevent;

import use_case.gcalevent.GCalEventInputBoundary;

import java.io.IOException;

public class GCalEventController {
    final GCalEventInputBoundary gcalEventUseCaseInteractor;
    public GCalEventController(GCalEventInputBoundary gcalEventUseCaseInteractor) {
        this.gcalEventUseCaseInteractor = gcalEventUseCaseInteractor;
    }

    public void importEvent(String eventId) throws IOException {
        gcalEventUseCaseInteractor.importEvent(eventId);
    }

    public void exportEvent(int localEventId) throws IOException {
        gcalEventUseCaseInteractor.exportEvent(localEventId);
    }
}
