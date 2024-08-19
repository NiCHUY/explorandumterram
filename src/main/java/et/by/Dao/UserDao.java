package et.by.Dao;

import et.by.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private static int PEOPLE_COUNT;
    private List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(++PEOPLE_COUNT, "Tom", "1"));
        users.add(new User(++PEOPLE_COUNT, "Sam", "1"));
        users.add(new User(++PEOPLE_COUNT, "Juan", "1"));
        users.add(new User(++PEOPLE_COUNT, "Selery", "1"));
    }

    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getUserID() == id).findAny().orElse(null);
    }
}
