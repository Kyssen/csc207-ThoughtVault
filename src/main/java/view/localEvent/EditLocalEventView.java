package view.localEvent;

import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.localEvent.LocalEventController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EditLocalEventView extends JPanel {
    public final String viewName = "edit event";
    private JTextField titleField;
    private JTextField dateField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField locationField;
    private JTextField descriptionField;
    private JCheckBox workCheckBox;
    private JCheckBox pinnedCheckBox;
    private LocalEventViewModel localEventViewModel;

    private LocalEventController localEventController;

    public EditLocalEventView(LocalEventViewModel localEventViewModel, LocalEventController localEventController) {
        this.localEventController = localEventController;
        this.localEventViewModel = localEventViewModel;
        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel(LocalEventViewModel.TITLE_LABEL);
        titleField = new JTextField(localEventViewModel.getState().getTitle());
        JLabel dateLabel = new JLabel(LocalEventViewModel.DATE_LABEL);
        dateField = new JTextField(localEventViewModel.getState().getDate().toString());
        JLabel startTimeLabel = new JLabel(LocalEventViewModel.START_TIME_LABEL);
        startTimeField = new JTextField(localEventViewModel.getState().getStartTime().toString());
        JLabel endTimeLabel = new JLabel(LocalEventViewModel.END_TIME_LABEL);
        endTimeField = new JTextField(localEventViewModel.getState().getEndTime().toString());
        JLabel locationLabel = new JLabel(LocalEventViewModel.LOCATION_LABEL);
        locationField = new JTextField(localEventViewModel.getState().getLocation());
        JLabel descriptionLabel = new JLabel(LocalEventViewModel.DESCRIPTION_LABEL);
        descriptionField = new JTextField(localEventViewModel.getState().getDescription());
        JLabel workLabel = new JLabel(LocalEventViewModel.IS_WORK_LABEL);
        workCheckBox = new JCheckBox();
        workCheckBox.setSelected(localEventViewModel.getState().getIsWork());
        JLabel pinnedLabel = new JLabel(LocalEventViewModel.PINNED_LABEL );
        pinnedCheckBox = new JCheckBox();
        pinnedCheckBox.setSelected(localEventViewModel.getState().getPinned());

        add(titleLabel);
        add(titleField);
        add(dateLabel);
        add(dateField);
        add(startTimeLabel);
        add(startTimeField);
        add(endTimeLabel);
        add(endTimeField);
        add(locationLabel);
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
                editEvent();
            }
        });

        add(saveButton);
    }

    private void editEvent() {
        String title = titleField.getText();
        LocalDate date = LocalDate.parse(dateField.getText());
        LocalTime startTime = LocalTime.parse(startTimeField.getText());
        LocalTime endTime = LocalTime.parse(endTimeField.getText());
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        // Call the editEvent method in your localEventController passing the updated information
        localEventController.editEvent(localEventViewModel.getState().getId(),
                title,localEventViewModel.getState().getUserId(),date,startTime,
                endTime,location,description,isWork,pinned,new ArrayList<Integer>());
    }

}