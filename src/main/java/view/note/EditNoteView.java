package view.note;

import interface_adapter.note.NoteViewModel;
import interface_adapter.note.NoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditNoteView extends JPanel {
    public final String viewName = "edit event";
    private JTextField titleField;
    private JTextField locationField;
    private JTextField descriptionField;
    private JCheckBox workCheckBox;
    private JCheckBox pinnedCheckBox;

    private NoteViewModel noteViewModel;

    private NoteController noteController;
    public EditNoteView (NoteViewModel noteViewModel, NoteController noteController){
        this.noteController = noteController;
        this.noteViewModel = noteViewModel;
        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel(noteViewModel.TITLE_LABEL);
        titleField = new JTextField(noteViewModel.getState().getTitle());
        locationField = new JTextField(noteViewModel.getState().getLocation());
        JLabel descriptionLabel = new JLabel(noteViewModel.DESCRIPTION_LABEL);
        descriptionField = new JTextField(noteViewModel.getState().getDescription());
        JLabel workLabel = new JLabel(noteViewModel.IS_WORK_LABEL);
        workCheckBox = new JCheckBox();
        workCheckBox.setSelected(noteViewModel.getState().getIsWork());
        JLabel pinnedLabel = new JLabel(noteViewModel.PINNED_LABEL );
        pinnedCheckBox = new JCheckBox();
        pinnedCheckBox.setSelected(noteViewModel.getState().getPinned());

        add(titleLabel);
        add(titleField);
        add(locationField);
        add(descriptionLabel);
        add(descriptionField);
        add(workLabel);
        add(workCheckBox);
        add(pinnedLabel);
        add(pinnedCheckBox);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNote();
            }
        });

        add(saveButton);
    }
    private void editNote() {
        String title = titleField.getText();
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        // Call the editEvent method in your localEventController passing the updated information
        noteController.editNote(noteViewModel.getState().getId(),
                title,noteViewModel.getState().getUserId(),
                location,description,isWork,pinned,new ArrayList<Integer>());
    }
}
