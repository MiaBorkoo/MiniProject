package EPICC;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuestionAsk {
	public static void askQuestion(User selectedUser, Question question) {
        // Create an array of options with labels A, B, and C
        String[] optionLabels = {"A", "B", "C"};

        // Create an array of radio buttons for options
        JRadioButton[] radioButtons = new JRadioButton[3];

        // Create a button group to ensure only one option can be selected
        ButtonGroup buttonGroup = new ButtonGroup();

        // Create a panel to hold the radio buttons and options
        JPanel panel = new JPanel(new GridLayout(0, 1));

        // Create a label to display the question
        JLabel questionLabel = new JLabel(question.getQuestionText());
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set a custom font
        panel.add(questionLabel);

        // Add radio buttons and options to the panel
        for (int i = 0; i < 3; i++) {
            radioButtons[i] = new JRadioButton(optionLabels[i] + ": " + question.getOptions()[i]);
            panel.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }

        int option = JOptionPane.showConfirmDialog(
                null, panel, "Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            for (int i = 0; i < 3; i++) {
                if (radioButtons[i].isSelected()) {
                    if (optionLabels[i].equalsIgnoreCase(question.getCorrectAnswer())) {
                        // Correct answer
                        int points = getPoints(question.getDifficulty());
                        selectedUser.addQuizScore(points);
                        question.setAnsweredCorrectly(true);
                        JOptionPane.showMessageDialog(null, "Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Incorrect answer
                        JOptionPane.showMessageDialog(
                                null,
                                "Incorrect. The correct answer is: " + question.getCorrectAnswer(),
                                "Result",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        }
    }

    private static int getPoints(String difficulty) {
        // Assign points based on the difficulty level
        switch (difficulty) {
            case "Novice":
                return 1;
            case "Intermediate":
                return 2;
            case "Expert":
                return 3;
            default:
                return 0;
        }
    }
}


