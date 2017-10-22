import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    /** InMemory Store */
    private Map<Integer, User> userMap = new HashMap<>();

    @Override
    public void storeUser(User user) {
        System.out.println("repository: storeUser");
        this.userMap.put(user.getId(), user);
    }

    @Override
    public User findUser(int userId) {
        System.out.println("repository findUser");
        return this.userMap.get(userId);
    }

    @Override
    public void removeUser(int userId) {
        System.out.println("repository removeUser");
        this.userMap.remove(userId);
    }

}
