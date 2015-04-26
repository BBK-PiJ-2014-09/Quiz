import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable 
{
	/**
	 * Class to manage a Quiz 
	 * 
	 * @author Daryl Smith, MSc IT 
	 * @version 1
	 */

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Question> questionList;
	private int correctAnswerScore = 0;
	private QuizSummary quizSummary = null;
	
	//constructor
	public Quiz(ArrayList<Question> questionList, QuizSummary quizSummary) 
	{
		this.questionList = questionList;
		this.quizSummary = quizSummary;
	}

	//getters
	public ArrayList<Question> getQuestionList() 
	{
		return questionList;
	}	

	public QuizSummary getQuizSummary() 
	{
		return quizSummary;
	}	
	
	/** 
	  * Gets the correct score total for the currently played quiz. 
	  * @return the the correct score total for the quiz
	*/ 
	public int getCorrectAnswerScore() 
	{
		this.correctAnswerScore = 0;
		for (int i = 0; i < questionList.size(); i++) 
		{
			if (questionList.get(i).hasCorrectAnswer()) 
			{
				correctAnswerScore++;
			}	
		}
		return correctAnswerScore;
	}
}