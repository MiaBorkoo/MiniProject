package EPICC;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//manual selection of categories quiz format
public class ManualSelectionQuizFormat implements QuizFormat {
    @Override
    public void startQuiz(User selectedUser, List<Question> questions) {
    	String[] categoryOptions = {"Novice", "Intermediate", "Expert", "All questions"};
        String choice = (String) JOptionPane.showInputDialog(
                null, "Select a category:", "Category Selection",
                JOptionPane.QUESTION_MESSAGE, null, categoryOptions, categoryOptions[0]);

        if (choice != null) {
            List<Question> allQuestions = new ArrayList<>(questions);
            List<Question> selectedQuestions = new ArrayList<>();

            if ("All questions".equals(choice)) {
                // If "All questions" is selected, add all the questions
                selectedQuestions.addAll(allQuestions);
            } else {
                // Filter questions based on the selected category (difficulty)
                for (Question question : allQuestions) {
                    if (question.getDifficulty().equals(choice)) {
                        selectedQuestions.add(question);
                    }
                }
            }

            // Ask questions from the selected category
            for (Question question : selectedQuestions) {
                QuestionAsk.askQuestion(selectedUser, question);
        }
    }
    }
}
