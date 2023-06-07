package jp.kobe_u.cs27.Poker.application.Service;

import jp.kobe_u.cs27.Poker.application.bean.User;
import jp.kobe_u.cs27.Poker.application.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUsername(String id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("User not found: " + id)
        );
        return user;
    }

    public void saveNewUser(User user) throws Exception {
        if(userRepository.existsById(user.getId())) {
            throw new Exception("User ID already exists");
        }
        userRepository.save(user);
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }
}