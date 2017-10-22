public interface UserRepository {
    void storeUser(User user);
    User findUser(int userId);
}
