import java.io.Serializable;

public class QuizSummary implements Serializable 
{
	/**
	 * Class to manage the presentation of the summary details of a quiz
	 * 
	 * @author Daryl Smith, MSc IT 
	 * @version 1
	 */

	private static final long serialVersionUID = 1L;
	private long quizID;
	private String quizName;
	private String quizDesc;
	
	//constructor
	public QuizSummary(long quizID, String quizName, String quizDesc) 
	{
		this.quizID = quizID;
		this.quizName = quizName;
		this.quizDesc = quizDesc;
	}

	//getters
	public long getQuizID() 
	{
		return quizID;
	}

	public String getQuizName() 
	{
		return quizName;
	}	

	public String getQuizDesc() 
	{
		return quizDesc;
	}

	//toString
	public String toString() 
	{
		return "Quiz ID: " + quizID + "\n Quiz Name: " + quizName + "\n Description: " +  quizDesc;
	}
}