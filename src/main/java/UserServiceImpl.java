public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User registerUser(User user) {
        this.userRepository.storeUser(user);
        return this.userRepository.findUser(user.getId());
    }

}
