package view.localEvent;

import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CreateLocalEventView extends JPanel{
    public final String viewName = "create event";
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

    public CreateLocalEventView(LocalEventViewModel localEventViewModel, LocalEventController localEventController) {
        this.localEventController = localEventController;
        this.localEventViewModel = localEventViewModel;
        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateField = new JTextField();
        JLabel startTimeLabel = new JLabel("Start Time (HH:MM):");
        startTimeField = new JTextField();
        JLabel endTimeLabel = new JLabel("End Time (HH:MM):");
        endTimeField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        locationField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel workLabel = new JLabel("Is Work Event:");
        workCheckBox = new JCheckBox();
        JLabel pinnedLabel = new JLabel("Pinned:");
        pinnedCheckBox = new JCheckBox();

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

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEvent();
            }
        });

        add(createButton);
    }
    private void createEvent() {
        String title = titleField.getText();
        LocalDate date = LocalDate.parse(dateField.getText());
        LocalTime startTime = LocalTime.parse(startTimeField.getText());
        LocalTime endTime = LocalTime.parse(endTimeField.getText());
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        localEventController.createEvent(0, title, localEventViewModel.getState().getUserId(),
                date, startTime, endTime, location, description, isWork,pinned, new ArrayList<Integer>());

    }
}
