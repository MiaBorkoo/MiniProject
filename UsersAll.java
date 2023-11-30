package EPICC;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//class that manages all the users and their data
public class UsersAll implements UserManager {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void createUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);
    }

  //checking if the username is taken

    @Override
    public boolean isUsernameTaken(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    //method for selecting a user from all the users that have been registered
    @Override
    public User selectUser() {
        String[] usernames = users.stream().map(User::getUsername).toArray(String[]::new);

        String selectedUsername = (String) JOptionPane.showInputDialog(
                null,
                "Select a user:",
                "Select User",
                JOptionPane.QUESTION_MESSAGE,
                null,
                usernames,
                usernames[0]);

        return users.stream()
                .filter(user -> user.getUsername().equals(selectedUsername))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addQuizScore(User user, int score) {
        user.addQuizScore(score);
    }
    
  
//creating a new user
    public void createUser() {
        String username = JOptionPane.showInputDialog(null, "Enter username:");
        String password = JOptionPane.showInputDialog(null, "Create a password (you will need it later to access statistics): ");

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different username.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            User user = new User(username, password);
            addUser(user);
            JOptionPane.showMessageDialog(null, "User created successfully.", "User Created", JOptionPane.INFORMATION_MESSAGE);
        }
    }

	@Override
	public boolean isUsernameTaken(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

 
    }


    
