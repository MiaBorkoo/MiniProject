package EPICC;

import java.util.List;
//interface that interacts with classes User and UsersAll
public interface UserManager extends BasicUser {
    void addUser(User user);
    List<User> getAllUsers();
    User findUser(String username);
    void createUser(String username, String password);
    boolean isUsernameTaken(String username);
    User selectUser();
    void addQuizScore(User user, int score);
}
