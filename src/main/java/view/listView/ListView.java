package view;

import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.importevents.ImportEventsViewModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.listView.ListViewState;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.note.NoteViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.note.NoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ListView extends JPanel{
    public final String viewName = "home page";
    private final ListViewModel listViewModel;
    private final LocalEventViewModel localEventViewModel;
    private final NoteViewModel noteViewModel;
    private final ImportEventsViewModel importEventsViewModel;
    private final ExportEventsViewModel exportEventsViewModel;
    private final ViewManagerModel viewManagerModel;
    private HashMap notes;
    private HashMap events;

    private final LocalEventController localEventController;
    private final NoteController noteController;
    //final JList<String> eventsList;
    //final DefaultListModel<String> notesListModel;
    //final JList<String> notesList;
    final JButton importEvents;
    final JButton exportEvents;
    final JButton createEvent;
    final JButton createNote;
    public ListView(ListViewModel listViewModel,
                    ImportEventsViewModel importEventsViewModel,
                    ExportEventsViewModel exportEventsViewModel,
                    ViewManagerModel viewManagerModel,
                    LocalEventViewModel localEventViewModel,
                    NoteViewModel noteViewModel ,
                    LocalEventController localEventController, NoteController noteController){
        this.localEventViewModel = localEventViewModel;
        this.noteViewModel = noteViewModel;
        this.listViewModel = listViewModel;
        this.importEventsViewModel = importEventsViewModel;
        this.exportEventsViewModel = exportEventsViewModel;
        this.viewManagerModel = viewManagerModel;
        ListViewState listViewState = listViewModel.getState();
        this.notes = listViewState.getNotes();
        this.events = listViewState.getEvents();
        this.localEventController = localEventController;
        this.noteController = noteController;

        JLabel title = new JLabel("Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel eventPanel = new JPanel();
        // Create buttons for each event
        for (Object eventId : events.keySet()) {
            HashMap<String, ?> eventDetails = (HashMap<String, ?>) events.get(eventId);

            JButton button = createEventButton((Integer)eventId, eventDetails);
            eventPanel.add(button);
        }
        JPanel notePanel = new JPanel();
        for (Object noteId : notes.keySet()) {
            HashMap<String, ?> noteDetails = (HashMap<String, ?>) notes.get(noteId);

            JButton button = createEventButton((Integer)noteId, noteDetails);
            eventPanel.add(button);
        }
        JPanel buttons = new JPanel();
        importEvents = new JButton(ListViewModel.IMPORT_EVENTS_BUTTON_LABEL);
        buttons.add(importEvents);
        importEvents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(importEvents)){
                            //listViewModel.setState(new ListViewState());
                            //listViewModel.firePropertyChanged();
                            ListView.this.viewManagerModel.setActiveView(ListView.this.importEventsViewModel.getViewName());
                            ListView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        createEvent = new JButton("Create Event");
        buttons.add(createEvent);
        createEvent.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createEvent)){
                            localEventViewModel.setUserId(listViewModel.getState().getUserId());
                            ListView.this.viewManagerModel.setActiveView("create event");
                            ListView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        createNote = new JButton("Create Note");
        buttons.add(createNote);
        createEvent.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createNote)){
                            noteViewModel.setUserId(listViewModel.getState().getUserId());
                            ListView.this.viewManagerModel.setActiveView("create note");
                            ListView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        exportEvents = new JButton(ListViewModel.EXPORT_EVENTS_BUTTON_LABEL);
        buttons.add(exportEvents);
        exportEvents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exportEvents)){
                            //listViewModel.setState(new ListViewState());
                            //listViewModel.firePropertyChanged();
                            ListView.this.viewManagerModel.setActiveView(ListView.this.exportEventsViewModel.getViewName());
                            ListView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(eventPanel);
        this.add(notePanel);
    }


    // Method to create an event button with ActionListener
    private JButton createEventButton(Integer eventId, HashMap<String, ?> eventDetails) {
        JButton button = new JButton("Event: " + eventDetails.get("title") + "Start: "
                + eventDetails.get("startTime") + "End: " + eventDetails.get("endTime"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when button is clicked (For demo, just print event details)
                ListView.this.localEventController.displayEventDetailedView(eventId);
            }
        });
        return button;
    }

    private JButton createNoteButton(Integer noteId, HashMap<String, ?> eventDetails) {
        JButton button = new JButton("Note: " + eventDetails.get("title"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when button is clicked (For demo, just print event details)
                ListView.this.noteController.displayNoteDetailedView(noteId);
            }
        });
        return button;
    }
    // Create a sample event map with details
}
