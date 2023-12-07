package use_case.note;

import data_access.NotesDataAccessObject;

import entity.NoteFactoryInterface;
import entity.note.Note;
import entity.note.NoteFactory;

import java.util.ArrayList;

/**
 * The interactor for the Note entity
 */
public class NoteInteractor implements NoteInputBoundary {
    private final NoteOutputBoundary noteOutputBoundary;

    private final NotesDataAccessObject notesDataAccessObject;

    private final NoteFactoryInterface noteFactory;

    /**
     * Constructor for NoteInteractor
     * @param noteOutputBoundary The presenter for the NoteInteractor
     * @param notesDataAccessObject The data access object for the NoteInteractor
     */
    public NoteInteractor(NoteOutputBoundary noteOutputBoundary,
                          NotesDataAccessObject notesDataAccessObject){
        this.noteOutputBoundary = noteOutputBoundary;

        this.notesDataAccessObject = notesDataAccessObject;

        this.noteFactory = new NoteFactory();
    }

    /**
     * Displays the note creation view
     */
    @Override
    public void DisplayNoteCreationView() {
        noteOutputBoundary.DisplayNoteCreationView();
    }

    /**
     * Displays the note edit view
     * @param noteID The ID of the note to be edited
     */
    @Override
    public void DisplayNoteEditView(int noteID) {
        Note note = notesDataAccessObject.getByID(noteID);

        noteOutputBoundary.DisplayNoteEditView(new
                NoteOutputData(note.getID(), note.getTitle(), note.getUserID(),
                note.getLocation(), note.getDescription(), note.isWork(),
                note.getPinned(), note.getDescendants()));
    }

    /**
     * Displays the note detailed view
     * @param noteID The ID of the note to be displayed
     */
    @Override
    public void DisplayNoteDetailedView(int noteID) {
        Note note = notesDataAccessObject.getByID(noteID);

        noteOutputBoundary.DisplayNoteDetailedView(new
                NoteOutputData(note.getID(), note.getTitle(), note.getUserID(),
                note.getLocation(), note.getDescription(), note.isWork(),
                note.getPinned(), note.getDescendants()));
    }

    /**
     * Creates a note and saves it to the database
     * @param noteInputData The input data for the note to be created
     */
    @Override
    public void CreateNote(NoteInputData noteInputData) {
        try {
            Note note = noteFactory.createNote(notesDataAccessObject.getNewID(),
                    noteInputData.getTitle(), noteInputData.getUserID(),
                    noteInputData.getLocation(), noteInputData.getDescription(),
                    noteInputData.getIsWork(), noteInputData.getPinned(),
                    noteInputData.getSubEvents());

            notesDataAccessObject.save(note);

            noteOutputBoundary.UpdateNotesList(new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants()));
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note creation failed.");
        }
    }

    /**
     * Edits a note and saves it to the database
     * @param noteInputData The input data for the note to be edited
     */
    @Override
    public void EditNote(NoteInputData noteInputData) {
        try {
            Note note = noteFactory.createNote(noteInputData.getID(),
                    noteInputData.getTitle(), noteInputData.getUserID(),
                    noteInputData.getLocation(), noteInputData.getDescription(),
                    noteInputData.getIsWork(), noteInputData.getPinned(),
                    noteInputData.getSubEvents());

            notesDataAccessObject.delete(note.getID());

            notesDataAccessObject.save(note);

            noteOutputBoundary.UpdateNotesList(new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants()));
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note editing failed.");
        }
    }

    /**
     * Deletes a note from the database
     * @param noteID The ID of the note to be deleted
     */
    @Override
    public void DeleteNote(int noteID) {
        try {
            notesDataAccessObject.delete(noteID);

            noteOutputBoundary.DeleteNoteSuccessView(noteID);
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note deletion failed.");
        }
    }

    /**
     * Displays all notes in the database
     * @param userID The ID of the user whose notes are to be displayed
     */
    @Override
    public void GetAllNotes(int userID) {
        ArrayList<NoteOutputData> noteOutputDataArrayList = new ArrayList<>();

        for (Note note : notesDataAccessObject.getAllUserEntries(userID)) {
            noteOutputDataArrayList.add(new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants()));
        }

        noteOutputBoundary.DisplayAllNotes(noteOutputDataArrayList);
    }
}
