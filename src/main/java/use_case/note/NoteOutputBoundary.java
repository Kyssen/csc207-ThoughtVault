package use_case.note;

import java.util.ArrayList;

/**
 * The output boundary for notes
 */
public interface NoteOutputBoundary {
    /**
     * Updates the notes list
     * @param noteOutputData The note to be added or updated in the list
     */
    void UpdateNotesList(NoteOutputData noteOutputData);

    /**
     * Displays the view for a successful note deletion
     */
    void DeleteNoteSuccessView(int ID);

    /**
     * Displays the view for a failed note operation
     * @param errorMessage The error message to be displayed
     */
    void NoteFailView(String errorMessage);

    /**
     * Displays a detailed view of the note
     * @param noteOutputData The note to be displayed
     */
    void DisplayNoteDetailedView(NoteOutputData noteOutputData);

    /**
     * Displays the note creation view
     */
    void DisplayNoteCreationView();

    /**
     * Displays the note edit view
     * @param noteOutputData The note to be edited
     */
    void DisplayNoteEditView(NoteOutputData noteOutputData);

    /**
     * Displays all notes
     * @param noteOutputDataArrayList The list of notes to be displayed
     */
    void DisplayAllNotes(ArrayList<NoteOutputData> noteOutputDataArrayList);
}
