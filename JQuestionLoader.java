package EPICC;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//JQuestionLoader stands for JSON question loader, loading question from the JSON file
public class JQuestionLoader implements QuestionLoader {
    @Override
    public List<Question> loadQuestions() {
        List<Question> loadedQuestions = new ArrayList<>();

        //implementing try/catch for error handling
        try {
            String jsonFilePath = "./src/EPICC/questionsNew.json";
            FileReader fileReader = new FileReader(jsonFilePath);
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(fileReader, JsonArray.class);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject questionObject = jsonArray.get(i).getAsJsonObject();
                Question question = new Question(
                        questionObject.get("category").getAsString(),
                        questionObject.get("difficulty").getAsString(),
                        questionObject.get("question").getAsString(),
                        parseOptions(questionObject.getAsJsonArray("options")),
                        questionObject.get("correctOption").getAsString()
                );
                loadedQuestions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loadedQuestions;
    }

    private String[] parseOptions(JsonArray optionsArray) {
        String[] options = new String[optionsArray.size()];
        for (int i = 0; i < optionsArray.size(); i++) {
            options[i] = optionsArray.get(i).getAsString();
        }
        return options;
    }
}
