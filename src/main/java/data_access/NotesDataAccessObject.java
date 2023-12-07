package data_access;

import entity.note.Note;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NotesDataAccessObject extends EntriesDataAccessObject<Note> {
    public NotesDataAccessObject(String jsonFile) throws IOException, ParseException {
        super(jsonFile);
    }

    void parseEntryObj(JSONObject entryJSON) {
        JSONArray notesJSONArr = (JSONArray) entryJSON.get("sub-notes");
        ArrayList<Integer> notesList = parseSubEntries(notesJSONArr);

        int noteID = parseEntryID(entryJSON);
        entries.put(noteID, new Note(noteID, parseTitle(entryJSON), parseUserID(entryJSON), parseLocation(entryJSON),
                parseDescription(entryJSON), parseLabel(entryJSON), parsePinned(entryJSON), notesList));
        lastID = noteID;
    }

    @SuppressWarnings("unchecked")
    void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every entry in the entries map to jsonFile
            JSONArray allEntriesJSON = new JSONArray();
            for (int id : entries.keySet()) {
                JSONObject entryDetails = putEntryDetails(id);

                JSONArray subNotesJSON = new JSONArray();
                subNotesJSON.addAll(entries.get(id).getDescendants());
                entryDetails.put("sub-notes", subNotesJSON);

                allEntriesJSON.add(entryDetails);
            }
            writer.write(allEntriesJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        NotesDataAccessObject dao = new NotesDataAccessObject("./testNotes.json");

        Note firstNote = new Note(dao.getNewID(), "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstNote);
        dao.save(new Note(dao.getNewID(), "second", 0,
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstNote.getID(), 1000))));
        dao.save(new Note(dao.getNewID(), "third", 0,
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>(Arrays.asList(0, firstNote.getID()))));

        Note otherUserEvent = new Note(dao.getNewID(), "another user", 1,
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>());
        dao.save(otherUserEvent);

        otherUserEvent.amendAllAttributes("another user edited",
                "Garbage chute :(((", "EDITED DESCRIPTION T_T", true, true, new ArrayList<>());
        dao.save(otherUserEvent);

        ArrayList<Note> user0Events = dao.getAllUserEntries(0);
        dao.delete(firstNote.getID());
    }
}