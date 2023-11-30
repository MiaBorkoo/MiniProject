package EPICC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Increase difficulty format
public class IncreasingDifficultyQuizFormat implements QuizFormat {
    @Override
    public void startQuiz(User selectedUser, List<Question> questions) {
    	List<Question> allQuestions = new ArrayList<>(questions);
        List<Question> selectedQuestions = new ArrayList<>();

        // Sort all questions by difficulty (NOVICE, INTERMEDIATE, EXPERT)
        Collections.sort(allQuestions, (q1, q2) -> q1.getDifficulty().compareTo(q2.getDifficulty()));

        // Select 6 questions (2 from each category, increasing difficulty)
        int questionsPerCategory = 2;
        for (String category : new String[] { "COMP SCIENCE", "COMP ORG", "DISCRETE MATHS" }) {
            List<Question> categoryQuestions = new ArrayList<>(allQuestions);
            categoryQuestions.removeIf(question -> !question.getCategory().equals(category));
            selectedQuestions.addAll(categoryQuestions.subList(0, questionsPerCategory));
        }

        // Ask selected questions
        for (Question question : selectedQuestions) {
            QuestionAsk.askQuestion(selectedUser, question);
    }
}
}
