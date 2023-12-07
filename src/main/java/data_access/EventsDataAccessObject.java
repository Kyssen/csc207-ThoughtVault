package data_access;

import entity.localEvent.LocalEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class EventsDataAccessObject extends EntriesDataAccessObject<LocalEvent> {

    public EventsDataAccessObject(String jsonPath) throws IOException, ParseException {
        super(jsonPath);
    }

    void parseEntryObj(JSONObject entryJSON) {
        LocalDate date = LocalDate.parse((String) entryJSON.get("date"));
        LocalTime startTime = LocalTime.parse((String) entryJSON.get("starttime"));
        LocalTime endTime = LocalTime.parse((String) entryJSON.get("endtime"));

        JSONArray eventsJSONArr = (JSONArray) entryJSON.get("sub-events");
        ArrayList<Integer> eventsList = parseSubEntries(eventsJSONArr);

        int eventID = parseEntryID(entryJSON);
        entries.put(eventID, new LocalEvent(eventID, parseTitle(entryJSON), parseUserID(entryJSON), date, startTime, endTime,
                parseLocation(entryJSON), parseDescription(entryJSON), parseLabel(entryJSON), parsePinned(entryJSON), eventsList));
        lastID = eventID;
    }

    @SuppressWarnings("unchecked")
    void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every event in the events map to jsonFile
            JSONArray allEventsJSON = new JSONArray();
            for (int id : entries.keySet()) {
                JSONObject eventDetails = putEntryDetails(id);

                eventDetails.put("date", entries.get(id).getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                LocalTime startTime = entries.get(id).getStartTime();
                LocalTime endTime = entries.get(id).getEndTime();
                eventDetails.put("starttime", startTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
                eventDetails.put("endtime", endTime.format(DateTimeFormatter.ISO_LOCAL_TIME));

                JSONArray subEventsJSON = new JSONArray();
                subEventsJSON.addAll(entries.get(id).getDescendants());
                eventDetails.put("sub-events", subEventsJSON);

                allEventsJSON.add(eventDetails);
            }
            writer.write(allEventsJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }

    // for testing only
    public static void main(String[] args) throws IOException, ParseException {
        EventsDataAccessObject dao = new EventsDataAccessObject("./testEvent.json");

        LocalEvent firstEvent = new LocalEvent(dao.getNewID(), "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);
        dao.save(new LocalEvent(dao.getNewID(), "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstEvent.getID(), 1000))));
        dao.save(new LocalEvent(dao.getNewID(), "third", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>(Arrays.asList(0, firstEvent.getID()))));

        LocalEvent otherUserEvent = new LocalEvent(dao.getNewID(), "another user", 1, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>());
        dao.save(otherUserEvent);

        otherUserEvent.amendAllAttributes("another user editted", LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :(((", "EDITED DESCRIPTION T_T", true, true, new ArrayList<>());
        dao.save(otherUserEvent);

        ArrayList<LocalEvent> user0Events = dao.getAllUserEntries(0);
        dao.delete(firstEvent.getID());
    }
}