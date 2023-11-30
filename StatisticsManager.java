package EPICC;

import javax.swing.*;
import java.util.List;
//managing all statistics data
public class StatisticsManager {
	private QuizManager quizManager;

    public StatisticsManager(QuizManager quizManager) {
        this.setQuizManager(quizManager);
    }

    //data for a single user
    void displayUserStatistics(User user) {
    	List<User> users = quizManager.usersAll.getAllUsers();
        if (user != null) {
            String username = user.getUsername();
            List<Integer> quizScores = user.getQuizScores();
            double mean = calculateMean(quizScores);
            double median = calculateMedian(quizScores);
            double standardDeviation = calculateStandardDeviation(quizScores);

            StringBuilder message = new StringBuilder("Statistics for " + username + ":\n");
            message.append("Quiz Scores: ").append(quizScores).append("\n");
            message.append("Mean: ").append(mean).append("\n");
            message.append("Median: ").append(median).append("\n");
            message.append("Standard Deviation: ").append(standardDeviation).append("\n");

            JOptionPane.showMessageDialog(null, message.toString(), "User Statistics", JOptionPane.INFORMATION_MESSAGE);
            //QuizManager.nicePopUp(null, message.toString(), "User Statistics", JOptionPane.INFORMATION_MESSAGE, 300);
        } else {
            JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //data for all the users
    void displayAllUserStatistics() {
    	List<User> users = quizManager.usersAll.getAllUsers();
    	
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No users have taken the quiz.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder message = new StringBuilder("Statistics for all users:\n");

            for (User user : users) {
                message.append(user.getUsername()).append(":\n");
                message.append("Quiz Scores: ").append(user.getQuizScores()).append("\n");
                message.append("Mean: ").append(calculateMean(user.getQuizScores())).append("\n");
                message.append("Median: ").append(calculateMedian(user.getQuizScores())).append("\n");
                message.append("Standard Deviation: ").append(calculateStandardDeviation(user.getQuizScores())).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, message.toString(), "All Users Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
//methods for calculating statistics
    private double calculateMean(List<Integer> quizScores) {
        if (quizScores.isEmpty()) return 0;

        int sum = quizScores.stream().mapToInt(Integer::intValue).sum();
        return (double) sum / quizScores.size();
    }

    private double calculateMedian(List<Integer> quizScores) {
        if (quizScores.isEmpty()) return 0;

        int n = quizScores.size();
        quizScores.sort(Integer::compareTo);

        if (n % 2 == 0) {
            return (double) (quizScores.get(n / 2 - 1) + quizScores.get(n / 2)) / 2;
        } else {
            return quizScores.get(n / 2);
        }
    }

    private double calculateStandardDeviation(List<Integer> quizScores) {
        if (quizScores.isEmpty()) return 0;

        double mean = calculateMean(quizScores);
        double sumOfSquares = quizScores.stream().mapToDouble(score -> Math.pow(score - mean, 2)).sum();

        return Math.sqrt(sumOfSquares / quizScores.size());
    }

    //giving user the option to select what to see in the statistics
    void handleUserStatistics() {
        List<User> users = quizManager.usersAll.getAllUsers();

        String[] options = {"View User Statistics", "View All User Statistics"};
        int choice = showCustom("Select an option", "Statistics Options", options, "Choose an Option");

        if (choice == 0) {
            if (!users.isEmpty()) {
                String[] usernames = users.stream().map(User::getUsername).toArray(String[]::new);
                String selectedUsername = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a user to view their statistics:",
                        "Select User",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        usernames,
                        usernames[0]
                );

                if (selectedUsername != null) {
                    User selectedUser = quizManager.usersAll.findUser(selectedUsername);

                    if (selectedUser != null) {
                        // Ask for the password
                        String inputPassword = JOptionPane.showInputDialog(null, "Enter your password:");

                        if (selectedUser.verifyPassword(inputPassword)) {
                            // Password is correct, display statistics
                            displayUserStatistics(selectedUser);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: There are no users logged into the system", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: There are no users logged into the system", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (choice == 1) {
            displayAllUserStatistics();
        }
    }

    private int showCustom(String message, String title, String[] options, String initialOption) {
        return JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                initialOption
        );
    }
    


	public QuizManager getQuizManager() {
		return quizManager;
	}

	public void setQuizManager(QuizManager quizManager) {
		this.quizManager = quizManager;
	}
}
