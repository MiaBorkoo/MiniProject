package EPICC;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
//class that handles single user data
public class User implements UserManager {
    private String username;
    private List<Integer> quizScores;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.quizScores = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void addQuizScore(int points) {
        quizScores.add(points);
    }
    

    public List<Integer> getQuizScores() {
        return quizScores;
    }
    
    public String getPassword() {
        return password;
    }

    // Method to verify the password
    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    @Override
    public void addQuizScore(User user, int score) {
        user.addQuizScore(score);
    }
    


	@Override
	public boolean isUsernameTaken(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User selectUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameTaken(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createUser(String username, String password) {
		// TODO Auto-generated method stub
		
	}
}
