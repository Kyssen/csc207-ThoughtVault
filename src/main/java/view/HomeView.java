package view;

import com.google.api.services.calendar.model.Event;
import entity.note.Note;
import interface_adapter.ViewManagerModel;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.importevents.ImportEventsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    public final String viewName = "home page";
    private final HomeViewModel homeViewModel;
    private final ImportEventsViewModel importEventsViewModel;
    private final ExportEventsViewModel exportEventsViewModel;
    private final ViewManagerModel viewManagerModel;
    final DefaultListModel<String> eventsListModel;
    final JList<String> eventsList;
    final DefaultListModel<String> notesListModel;
    final JList<String> notesList;
    final JButton importEvents;
    final JButton exportEvents;

    public HomeView(HomeViewModel homeViewModel,
                    ImportEventsViewModel importEventsViewModel,
                    ExportEventsViewModel exportEventsViewModel,
                    ViewManagerModel viewManagerModel){
        this.homeViewModel = homeViewModel;
        this.importEventsViewModel = importEventsViewModel;
        this.exportEventsViewModel = exportEventsViewModel;
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel("Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        eventsListModel = new DefaultListModel<>();
        HomeState currentState = homeViewModel.getState();
        for (Event event : currentState.getListOfEvents()) {
            eventsListModel.addElement(event.getSummary());
        }
        eventsList = new JList<>(eventsListModel);

        notesListModel = new DefaultListModel<>();
        for (Note note : currentState.getListOfNotes()) {
            notesListModel.addElement(note.getTitle());
            notesListModel.addElement(note.getDescription());
        }
        notesList = new JList<>(notesListModel);

        JPanel buttons = new JPanel();
        importEvents = new JButton(homeViewModel.IMPORT_EVENTS_BUTTON_LABEL);
        buttons.add(importEvents);
        importEvents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(importEvents)){
                            homeViewModel.setState(new HomeState());
                            homeViewModel.firePropertyChanged();
                            HomeView.this.viewManagerModel.setActiveView(HomeView.this.importEventsViewModel.getViewName());
                            HomeView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        exportEvents = new JButton(homeViewModel.EXPORT_EVENTS_BUTTON_LABEL);
        buttons.add(exportEvents);
        exportEvents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exportEvents)){
                            homeViewModel.setState(new HomeState());
                            homeViewModel.firePropertyChanged();
                            HomeView.this.viewManagerModel.setActiveView(HomeView.this.exportEventsViewModel.getViewName());
                            HomeView.this.viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(eventsList);
        this.add(notesList);
        this.add(buttons);
    }
}
