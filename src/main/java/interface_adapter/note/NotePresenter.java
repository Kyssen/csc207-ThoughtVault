package interface_adapter.note;

import use_case.note.NoteOutputData;
import interface_adapter.listView.ListViewModel;
import interface_adapter.listView.ListViewState;
import interface_adapter.ViewManagerModel;
import use_case.note.NoteOutputBoundary;

import java.util.ArrayList;

import java.util.HashMap;

/**
 * This class is a Presenter for the NoteView.
 */
public class NotePresenter implements NoteOutputBoundary {
    private final NoteViewModel detailedNoteViewModel;

    private final NoteViewModel noteCreationViewModel;

    private final NoteViewModel noteEditViewModel;

    private final ListViewModel listViewModel;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for NotePresenter.
     *
     * @param viewManagerModel         The view manager model.
     * @param detailedNoteViewModel    The detailed note view model.
     * @param noteCreationViewModel    The note creation view model.
     * @param noteEditViewModel        The note edit view model.
     * @param listViewModel            The list view model.
     */
    public NotePresenter(ViewManagerModel viewManagerModel,
                         NoteViewModel detailedNoteViewModel,
                         NoteViewModel noteCreationViewModel,
                         NoteViewModel noteEditViewModel,
                         ListViewModel listViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.detailedNoteViewModel = detailedNoteViewModel;
        this.noteCreationViewModel = noteCreationViewModel;
        this.noteEditViewModel = noteEditViewModel;
        this.listViewModel = listViewModel;
    }

    /**
     * Creates a note state.
     *
     * @param noteOutputData  The note output data.
     * @return                The note state.
     */
    public NoteState CreateState(NoteOutputData noteOutputData) {
        return new NoteState(noteOutputData.getID(),
                noteOutputData.getTitle(),
                noteOutputData.getLocation(),
                noteOutputData.getDescription(),
                noteOutputData.getIsWork(),
                noteOutputData.getPinned(),
                noteOutputData.getSubEvents(),
                noteOutputData.getUserId());
    }

    /**
     * Updates the list view's notes list with the given note output data and
     * switches to a detailed view of the note.
     * @param noteOutputData The note output data.
     */
    @Override
    public void UpdateNotesList(NoteOutputData
                                         noteOutputData) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> listViewNotes =
                listViewState.getNotes();
        listViewNotes.put(noteOutputData.getID(),
                noteOutputData.getNoteData());
        listViewState.setNotes(listViewNotes);
        listViewModel.firePropertyChanged();
        DisplayNoteDetailedView(noteOutputData);
    }

    /**
     * Delete the note with the given ID from the list view's notes list and
     * switch to the list view.
     * @param ID The ID of the note to be deleted.
     */
    @Override
    public void DeleteNoteSuccessView(int ID) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> noteViewModelArrayList =
                listViewState.getNotes();
        noteViewModelArrayList.remove(ID);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays an error message.
     * @param errorMessage The error message.
     */
    @Override
    public void NoteFailView(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a detailed view of the note with the given note output data.
     * @param noteOutputData The note output data.
     */
    @Override
    public void DisplayNoteDetailedView(NoteOutputData noteOutputData) {
        NoteState noteState = new NoteState(
                noteOutputData.getID(),
                noteOutputData.getTitle(),
                noteOutputData.getLocation(),
                noteOutputData.getDescription(),
                noteOutputData.getIsWork(),
                noteOutputData.getPinned(),
                noteOutputData.getSubEvents(),
                noteOutputData.getUserId());
        detailedNoteViewModel.setState(noteState);
        detailedNoteViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                detailedNoteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays the note creation view.
     */
    @Override
    public void DisplayNoteCreationView() {
        NoteState noteState = new NoteState();
        noteCreationViewModel.setState(noteState);
        noteCreationViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                noteCreationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays the note edit view with the given note output data.
     * @param noteOutputData The note output data.
     */
    @Override
    public void DisplayNoteEditView(NoteOutputData noteOutputData) {
        NoteState noteState = CreateState(noteOutputData);
        noteEditViewModel.setState(noteState);
        noteEditViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(noteEditViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Displays all notes in the array in list view.
     * @param noteOutputDataArrayList The note output data array list.
     */
    @Override
    public void DisplayAllNotes(ArrayList<NoteOutputData>
                                         noteOutputDataArrayList) {
        HashMap<Integer, HashMap<String, ?>> listViewNotes = new HashMap<>();
        for (NoteOutputData noteOutputData :
                noteOutputDataArrayList) {
            listViewNotes.put(noteOutputData.getID(),
                    noteOutputData.getNoteData());
        }
        ListViewState listViewState = listViewModel.getState();
        listViewState.setNotes(listViewNotes);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

