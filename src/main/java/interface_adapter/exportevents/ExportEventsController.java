package interface_adapter.exportevents;

import use_case.gcalevent.GCalEventInputBoundary;

import java.io.IOException;

public class ExportEventsController {
    final GCalEventInputBoundary gCalEventUseCaseInteractor;
    public ExportEventsController(GCalEventInputBoundary gCalEventUseCaseInteractor){
        this.gCalEventUseCaseInteractor = gCalEventUseCaseInteractor;
    }
    public void execute(int localEventId) throws IOException {
        gCalEventUseCaseInteractor.exportEvent(localEventId);
    }

    public void switchToHome(){
        gCalEventUseCaseInteractor.switchToHome();
    }
}
