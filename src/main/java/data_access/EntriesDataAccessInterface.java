package data_access;

import entity.note.Note;

import java.util.ArrayList;

public interface EntriesDataAccessInterface<T extends Note> {
    T getByID(int entryID);
    ArrayList<T> getAllUserEntries(int userID);
    void save(T entry);
    void delete(int entryID);
    int getNewID();
}