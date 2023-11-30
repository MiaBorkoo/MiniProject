package EPICC;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//random draw quiz format
public class RandomDrawQuizFormat implements QuizFormat {
    @Override
    public void startQuiz(User selectedUser, List<Question> questions) {
        // Implementation for starting a quiz with random draw format
        List<Question> allQuestions = new ArrayList<>(questions);
        List<Question> selectedQuestions = new ArrayList<>();

        Collections.shuffle(allQuestions);

        // Select 6 questions (2 from each category)
        int questionsPerCategory = 2;
        for (String category : new String[] { "COMP SCIENCE", "COMP ORG", "DISCRETE MATHS" }) {
            List<Question> categoryQuestions = new ArrayList<>(allQuestions);
            categoryQuestions.removeIf(question -> !question.getCategory().equals(category));
            Collections.shuffle(categoryQuestions);
            selectedQuestions.addAll(categoryQuestions.subList(0, questionsPerCategory));
        }

        // Ask selected questions
        for (Question question : selectedQuestions) {
            QuestionAsk.askQuestion(selectedUser, question);
    }
}
}