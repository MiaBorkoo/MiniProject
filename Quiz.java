package EPICC;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//class where the main method is
public class Quiz {
    private static final int CREATE_NEW_USER = 0;
    private static final int DISPLAY_USER_STATISTICS = 1;
    private static final int START_QUIZ = 2;
    private static final int EXIT = 3;

    private static QuizManager quizManager = new QuizManager();
    private static UsersAll usersAll = new UsersAll();

    public static List<User> getUsers() {
        return usersAll.getAllUsers();
    }

    public static User getSelectedUser() {
        return quizManager.getSelectedUser();
    }

    //main method, displaying option to the user what to do in the quiz
    public static void main(String[] args) {
        while (true) {
            setLookAndFeel();

            String[] options = {"Create New User", "Display User Statistics", "Start Quiz", "Exit"};
            int choice = showCustomOptionsDialog("Quiz Application", "Choose an option:", options);

            switch (choice) {
                case CREATE_NEW_USER:
                    usersAll.createUser();
                    break;
                case DISPLAY_USER_STATISTICS:
                    List<User> allUsers = usersAll.getAllUsers();
                    if (!allUsers.isEmpty()) {
                        quizManager.handleUserStatistics();
                    } else {
                        showErrorDialog("Error: There are no users logged into the system");
                    }
                    break;
                case START_QUIZ:
                    List<User> allUsersForQuiz = usersAll.getAllUsers();
                    if (!allUsersForQuiz.isEmpty()) {
                        User selectedUser = usersAll.selectUser();
                        if (selectedUser != null) {
                            quizManager.startQuiz(selectedUser);
                        }
                    } else {
                        showErrorDialog("Error: There are no users logged into the system");
                    }
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int showCustomOptionsDialog(String title, String message, String[] options) {
        UIManager.put("OptionPane.minimumSize", new Dimension(350, 150));

        return JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    private static void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
