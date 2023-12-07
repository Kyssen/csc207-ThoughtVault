package interface_adapter.home;

import com.google.api.services.calendar.model.Event;
import entity.note.Note;

import java.util.List;

public class HomeState {
    private List<Event> listOfEvents;
    private List<Note> listOfNotes;

    public HomeState(HomeState copy){
        listOfEvents = copy.listOfEvents;
        listOfNotes = copy.listOfNotes;
    }

    public HomeState(){}

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }

    public List<Note> getListOfNotes() {
        return listOfNotes;
    }

    public void setListOfEvents(List<Event> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

    public void setListOfNotes(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
    }
}
