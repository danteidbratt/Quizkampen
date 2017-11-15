package quizkampen;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> array = new ArrayList<>();

    public void addUser(String userName) {
        array.add(new User(userName));
    }

    public void removeUser(String userName) { }

    public boolean userExist(String userName) {
        for(User u : array) {
            return u.getUserName().equalsIgnoreCase(userName);
        }
        return false;
    }
}
