package EPICC;
//interface that implements another interface, UserManager
public interface BasicUser {
	User selectUser();
    void createUser();
    boolean isUsernameTaken(String username, String password);
}
