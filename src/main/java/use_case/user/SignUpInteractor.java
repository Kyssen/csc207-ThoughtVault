package use_case.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import entity.user.User;

public class SignUpInteractor implements SignUpInputBoundary{
    final UserDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary signUpPresenter;

    public SignUpInteractor(UserDataAccessInterface userDataAccessObject, SignUpOutputBoundary signUpPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.signUpPresenter = signUpPresenter;
    }

    @Override
    public void createUser(SignUpInputData inputData) {
        if (userDataAccessObject.getUserByUsername(inputData.getUsername()) != null) {
            signUpPresenter.prepareFailView("This username is already taken.", SignUpOutputBoundary.ErrorType.USERNAME);
        } else if (!inputData.getPassword().equals(inputData.getRepeatPassword())) {
            signUpPresenter.prepareFailView("The password you entered does not match your repeated password.", SignUpOutputBoundary.ErrorType.PASSWORD);
        } // checks if credentials json string is valid
        String hashedPw = hashPassword(inputData.getPassword());
        User newUser = new User(userDataAccessObject.getNewID(), inputData.getUsername(), hashedPw, inputData.getAPIKEy());
        userDataAccessObject.save(newUser);
        signUpPresenter.prepareSuccessView(newUser.getUsername());
    }

    @Override
    public void switchtoLogin() {
        signUpPresenter.switchToLogin();
    }

    public static String hashPassword(String password) {
        return BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, password.toCharArray());
    }
}
