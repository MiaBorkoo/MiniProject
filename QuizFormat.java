package EPICC;

import java.util.List;

//interface that is implemented in all the quiz format options
public interface QuizFormat {
	void startQuiz(User selectedUser, List<Question> questions);
}
