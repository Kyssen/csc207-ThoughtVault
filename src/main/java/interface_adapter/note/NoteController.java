package interface_adapter.note;

import use_case.note.NoteInputData;
import use_case.note.NoteInputBoundary;

import java.util.ArrayList;

/**
 * This class is a controller for the NoteView.
 * It contains the logic for the view.
 */
public class NoteController {
    private final NoteInputBoundary noteInteractor;

    /**
     * Constructor for the NoteController.
     * @param noteInteractor The interactor for the note use case.
     */
    public NoteController(NoteInputBoundary noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    /**
     * Creates a note.
     * @param id The id of the note.
     * @param title The title of the note.
     * @param userID The id of the user.
     * @param location The location of the note.
     * @param description The description of the note.
     * @param isWork Whether the note is for work.
     * @param pinned Whether the note is pinned.
     * @param subEvents The sub-events of the note.
     */
    public void createNote(int id, String title, int userID, String location,
                           String description, boolean isWork, boolean pinned,
                           ArrayList<Integer> subEvents) {
        NoteInputData noteInputData = new NoteInputData(id, title, userID,
                location, description, isWork, pinned, subEvents);

        noteInteractor.CreateNote(noteInputData);
    }

    /**
     * Edits a note.
     * @param id The id of the note.
     * @param title The title of the note.
     * @param userID The id of the user.
     * @param location The location of the note.
     * @param description The description of the note.
     * @param isWork Whether the note is for work.
     * @param pinned Whether the note is pinned.
     * @param subEvents The sub-events of the note.
     */
    public void editNote(int id, String title, int userID, String location,
                         String description, boolean isWork, boolean pinned,
                         ArrayList<Integer> subEvents) {
        NoteInputData noteInputData = new NoteInputData(id, title, userID,
                location, description, isWork, pinned, subEvents);

        noteInteractor.EditNote(noteInputData);
    }

    /**
     * Deletes a note.
     * @param NoteID The id of the note.
     */
    public void deleteNote(int NoteID) {
        noteInteractor.DeleteNote(NoteID);
    }

    public void displayNoteDetailedView(int noteId) {noteInteractor.DisplayNoteDetailedView(noteId);
    }
}
