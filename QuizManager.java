package EPICC;
import java.sql.Connection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.IntSummaryStatistics;

import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class QuizManager {
	//initializing all quiz formats
	private RandomDrawQuizFormat randomDrawQuizFormat;
    private IncreasingDifficultyQuizFormat increasingDifficultyQuizFormat;
    private ManualSelectionQuizFormat manualSelectionQuizFormat; 
    
	private List<Question> questions = new ArrayList<>();
    private User selectedUser;

    // Access the static UsersAll instance from QuizManager
    public static UsersAll usersAll = new UsersAll();
    
    public User getSelectedUser() {
        return selectedUser;
    }
    //making sure the magic numbers are not used
    private static final int RANDOM_DRAW = 0;
    private static final int INCREASING_DIFFICULTY = 1;
    private static final int MANUAL_SELECTION = 2;

    private StatisticsManager statisticsManager;

    
        
        


    public QuizManager() {
    	this.statisticsManager = new StatisticsManager(this);
    	this.randomDrawQuizFormat = new RandomDrawQuizFormat();
        this.increasingDifficultyQuizFormat = new IncreasingDifficultyQuizFormat();
        this.manualSelectionQuizFormat = new ManualSelectionQuizFormat();
        try {
        	
        	//reading through the JSON file using file reader
            String jsonFilePath = "./src/EPICC/questionsNew.json";  
            FileReader fileReader = new FileReader(jsonFilePath);
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(fileReader, JsonArray.class);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject questionObject = jsonArray.get(i).getAsJsonObject();
                // Create Question objects from questionObject and add them to the questions list
                Question question = new Question(
                	    questionObject.get("category").getAsString(),
                	    questionObject.get("difficulty").getAsString(),
                	    questionObject.get("question").getAsString(),
                	    parseOptions(questionObject.getAsJsonArray("options")),
                	    questionObject.get("correctOption").getAsString()
                	);
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private String[] parseOptions(JsonArray optionsArray) {
        String[] options = new String[optionsArray.size()];
        for (int i = 0; i < optionsArray.size(); i++) {
            options[i] = optionsArray.get(i).getAsString();
        }
        return options;
    }
    
    
    //calling methods that are used in this class
    public void handleUserStatistics() {
        statisticsManager.handleUserStatistics();
    }
    
    public void displayUserStatistics(User user) {
        statisticsManager.displayUserStatistics(user);
    }

    public void displayAllUserStatistics() {
        statisticsManager.displayAllUserStatistics();
    }

    private void startRandomDrawQuiz() {
        randomDrawQuizFormat.startQuiz(selectedUser, questions);
    }

    private void startIncreasingDifficultyQuiz() {
        increasingDifficultyQuizFormat.startQuiz(selectedUser, questions);
    }
    
    private void selectCategory() {
    	manualSelectionQuizFormat.startQuiz(selectedUser, questions);
    }
   
    //displaying quiz formats
    public void startQuiz(User user) {
        selectedUser = user;
         
        usersAll.addUser(user);

        String[] options = { "Random Draw", "Increasing Difficulty", "Select categories manually" };
        
        int choice = showCustom(
                "Select the quiz format",
                "Quiz Format",
                options,
                "Choose an Option"
        );

        switch (choice) {
        case RANDOM_DRAW:
            // Start the quiz using the random draw format
            startRandomDrawQuiz();
            break;
        case INCREASING_DIFFICULTY:
            // Start the quiz using the increasing difficulty format
            startIncreasingDifficultyQuiz();
            break;
        case MANUAL_SELECTION:
            // Start the quiz using the select category format
            selectCategory();
            break;
        default:
            // Handle default case if necessary
            break;
    }
    }
    
    private int showCustom(String message, String title, String[] buttonLabels, String buttonPrompt) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Design
        int buttonWidth = 200;
        int buttonHeight = 90;
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        UIManager.put("OptionPane.minimumSize", new Dimension(50, 200));
    	UIManager.put("OptionPane.buttonOrientation", SwingConstants.RIGHT);

        // Add buttons with equal spacing between them (using for loop)
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalStrut(30)); 
            panel.add(button);
        }
        
        

        int choice = JOptionPane.showOptionDialog(
                null, message, title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttonLabels, buttonLabels[0]);

            return choice;
        
    }

    
}