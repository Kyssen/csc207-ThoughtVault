package entity.user;

public class User implements UserInterface {
    private final int userid;
    private final String username;
    private final String password;
    private final String credential;

    public User(int userid, String username, String password, String credential) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.credential = credential;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return password;
    }

    public String getCredential() {
        return credential;
    }
}
