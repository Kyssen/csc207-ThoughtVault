package use_case.note;

/**
 * The input boundary for notes
 */
public interface NoteInputBoundary {
    /**
     * Displays the note creation view
     */
    void DisplayNoteCreationView();

    /**
     * Displays the note edit view
     * @param noteID The ID of the note to be edited
     */
    void DisplayNoteEditView(int noteID);

    /**
     * Displays the note detailed view
     * @param noteID The ID of the note to be displayed
     */
    void DisplayNoteDetailedView(int noteID);

    /**
     * Creates a note and saves it to the database
     * @param noteInputData The input data for the note to be created
     */
    void CreateNote(NoteInputData noteInputData);

    /**
     * Edits a note and saves it to the database
     * @param noteInputData The input data for the note to be edited
     */
    void EditNote(NoteInputData noteInputData);

    /**
     * Deletes a note from the database
     * @param noteID The ID of the note to be deleted
     */
    void DeleteNote(int noteID);

    /**
     * Gets all notes from the database
     * @param userID The ID of the user whose notes are to be retrieved
     */
    void GetAllNotes(int userID);
}
