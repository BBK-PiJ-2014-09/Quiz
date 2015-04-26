import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Question implements Serializable 
{
	/**
	 * Class to manage the presentation and submission of answers for a quiz question 
	 * 
	 * @author Daryl Smith, MSc IT 
	 * @version 1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String question;
	private ArrayList<String> answers;
	private int correctAnswer;
	private boolean clientAnswerCorrect = false;
	
	//constructor
	public Question(String question, ArrayList<String> answers, int correctAnswer) {
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	//getters
	public String getQuestion() 
	{
		return question;
	}
	public ArrayList<String> getAnswers() 
	{
		return answers;
	}
	public int getCorrectAnswer() 
	{
		return correctAnswer;
	}

	/** 
	  * Checks whether a quiz question has been answered correctly
	  * @return true or false depending on whether the question has been correctly answered 
	*/ 
	public boolean hasCorrectAnswer() 
	{
		return clientAnswerCorrect;
	}
	
	/** 
	  * Prompts the player to answer a series of quiz questions
	*/ 
	public void ask(Scanner in) 
	{
		System.out.println(this.toString());
		System.out.println("Please select your answer from one of the options");
		String clientAnswer = in.next();
		int intAnswer = -1;
		try 
		{
			intAnswer = Integer.parseInt(clientAnswer);
		} 	
		catch (Exception e) 
		{ 
		}

		//check if the answer supplied is correct
		if (clientAnswer.equals(answers.get(correctAnswer)) || intAnswer-1 == correctAnswer) 
		{
			clientAnswerCorrect = true;
		}
		System.out.println(clientAnswerCorrect);
	}
	
	//toString
	public String toString() 
	{
		String answersString = "";
		for (int i = 0; i < answers.size(); i++) 
		{
			answersString = answersString + (i + 1) + ": " + answers.get(i) + "\n";
		}
		return question + "\n" + answersString;
	}
}