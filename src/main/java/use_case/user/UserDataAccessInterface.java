package use_case.user;

import entity.user.User;

public interface UserDataAccessInterface {
    User getUserByUsername(String username);
    void save(User user);
    int getNewID();
}
