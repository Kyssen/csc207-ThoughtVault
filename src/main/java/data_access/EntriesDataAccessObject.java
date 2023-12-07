package data_access;

import entity.note.Note;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
abstract public class EntriesDataAccessObject<T extends Note> implements EntriesDataAccessInterface<T> {
    final Map<Integer, T> entries = new HashMap<>();
    final File jsonFile;
    int lastID = 0;

    public EntriesDataAccessObject(String jsonPath) throws IOException, ParseException {
        jsonFile = new File(jsonPath);

        if (jsonFile.length() == 0) {
            // Create new file with jsonPath if it doesn't exist
            save();
        } else {
            // Read existing notes/Notes JSON file and map each event/note JSON object to a key-value pair in entries
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(jsonFile)) {
                Object obj = jsonParser.parse(reader);
                JSONArray entriesList = (JSONArray) obj;
                entriesList.forEach(ent -> parseEntryObj((JSONObject) ent));
            }
        }
    }

    abstract void parseEntryObj(JSONObject entryJSON);

    int parseEntryID(JSONObject entryJSON) {
        return ((Long) entryJSON.get("id")).intValue();
    }

    String parseTitle(JSONObject entryJSON) {
        return (String) entryJSON.get("title");
    }

    int parseUserID(JSONObject entryJSON) {
        return ((Long) entryJSON.get("userid")).intValue();
    }

    String parseLocation(JSONObject entryJSON) {
        return (String) entryJSON.get("location");
    }

    String parseDescription(JSONObject entryJSON) {
        return (String) entryJSON.get("description");
    }

    boolean parseLabel(JSONObject entryJSON) {
        return (entryJSON.get("label")).equals("WORK");
    }

    boolean parsePinned(JSONObject entryJSON) {
        return (Boolean) entryJSON.get("pinned");
    }

    ArrayList<Integer> parseSubEntries(JSONArray subEntriesJSON) {
        ArrayList<Integer> entriesList = new ArrayList<>();
        for (Long eventLongID : (ArrayList<Long>) subEntriesJSON)
            entriesList.add(eventLongID.intValue()); 
        return entriesList;
    }

    @Override
    public T getByID(int entryID) {
        return entries.get(entryID);
    }

    @Override
    public ArrayList<T> getAllUserEntries(int userID) {
        ArrayList<T> userEntriesID = new ArrayList<>();
        for (int id : entries.keySet())
            if (entries.get(id).getUserID() == userID)
                userEntriesID.add(entries.get(id));
        return userEntriesID;
    }

    @Override
    public void save(T entry) {
        entries.put(entry.getID(), entry);
        save();
    }


    @Override
    public void delete(int entryID) {
        T rmEntry = entries.remove(entryID);
        if (rmEntry == null)
            return;
        // If this entry is a child of any other entries, remove this entry's id in its parent's sub-events
        for (int id : entries.keySet()) {
            int count = 0;
            for (int subEntryID : entries.get(id).getDescendants()) {
                if (subEntryID == entryID) {
                    entries.get(id).removeDescendants(count);
                    break;
                }
                count++;
            }
        }
        save();
    }

    @Override
    public int getNewID() {
        return ++lastID;
    }

    abstract void save();

    JSONObject putEntryDetails(int entryID) {
        JSONObject entryDetails = new JSONObject();
        entryDetails.put("id", entries.get(entryID).getID());
        entryDetails.put("title", entries.get(entryID).getTitle());
        entryDetails.put("userid", entries.get(entryID).getUserID());
        entryDetails.put("location", entries.get(entryID).getLocation());
        entryDetails.put("description", entries.get(entryID).getDescription());
        entryDetails.put("label", entries.get(entryID).isWork() ? "WORK" : "PERSONAL");
        entryDetails.put("pinned", entries.get(entryID).getPinned());
        return entryDetails;
    }
}
