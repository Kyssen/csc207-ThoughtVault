package data_access;

import entity.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import use_case.user.UserDataAccessInterface;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDataAccessObject implements UserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    private final File jsonFile;
    private int lastID = 0;

    @SuppressWarnings("unchecked")
    public UserDataAccessObject(String jsonPath) throws IOException, ParseException {
        jsonFile = new File(jsonPath);

        if (jsonFile.length() == 0) {
            // Create new file with jsonPath if it doesn't exist
            save();
        } else {
            // Read existing Events JSON file and map each event JSON object to a key-value pair in events
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(jsonFile)) {
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                usersList.forEach(usr -> parseUserObj((JSONObject) usr));
            }
        }
    }

    private void parseUserObj(JSONObject userJSON) {
        int userID = ((Long) userJSON.get("id")).intValue();
        String username = (String) userJSON.get("username");
        String hashedPw = (String) userJSON.get("password");
        String usrCredFileStr = (String) userJSON.get("gcal-credential");

        users.put(username, new User(userID, username, hashedPw, usrCredFileStr));
        lastID = userID;
    }

    @Override
    public User getUserByUsername(String username) {
        // returns null if key does not exist
        return users.get(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
        save();
    }

    public int getNewID() {
        return ++lastID;
    }

    @SuppressWarnings("unchecked")
    private void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every user in the users map to jsonFile
            JSONArray allUsersJSON = new JSONArray();
            for (String usrname : users.keySet()) {
                JSONObject userDetails = new JSONObject();
                userDetails.put("id", users.get(usrname).getUserid());
                userDetails.put("username", users.get(usrname).getUsername());
                userDetails.put("password", users.get(usrname).getHashedPassword());
                userDetails.put("gcal-credential", users.get(usrname).getCredential());

                allUsersJSON.add(userDetails);
            }

            writer.write(allUsersJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }
}
