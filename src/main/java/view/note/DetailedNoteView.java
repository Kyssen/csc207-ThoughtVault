package view.note;

import interface_adapter.note.NoteViewModel;
import interface_adapter.note.NoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedNoteView extends JPanel {
    private JLabel titleLabel;
    private JLabel locationLabel;
    private JLabel descriptionLabel;
    private JLabel isWorkLabel;
    private JLabel pinnedLabel;
    private JLabel subEventsLabel;
    private JButton deleteButton;
    private NoteController noteController;
    public String viewName = "";
    public DetailedNoteView (NoteViewModel noteViewModel, NoteController noteController){
        this.viewName = noteViewModel.getViewName();

        setLayout(new GridLayout(6, 1)); // Adjust the layout as per your preference
        titleLabel = new JLabel(NoteViewModel.TITLE_LABEL + noteViewModel.getState().getTitle());
        locationLabel = new JLabel(NoteViewModel.LOCATION_LABEL + noteViewModel.getState().getLocation());
        descriptionLabel = new JLabel(NoteViewModel.DESCRIPTION_LABEL + noteViewModel.getState().getDescription());
        isWorkLabel = new JLabel(NoteViewModel.IS_WORK_LABEL + noteViewModel.getState().getIsWork());
        pinnedLabel = new JLabel(NoteViewModel.PINNED_LABEL + noteViewModel.getState().getPinned());
        subEventsLabel = new JLabel(NoteViewModel.SUB_EVENTS_LABEL + noteViewModel.getState().getSubEvents().toString());

        deleteButton = new JButton("Delete Event");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when delete button is clicked
                // For now, let's remove the panel itself
                noteController.deleteNote(noteViewModel.getState().getId());
            }
        });
        add(titleLabel);
        add(locationLabel);
        add(descriptionLabel);
        add(isWorkLabel);
        add(pinnedLabel);
        add(subEventsLabel);

        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for clarity
    }
}
