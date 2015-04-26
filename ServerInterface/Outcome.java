import java.io.Serializable;
import java.util.ArrayList;

public class Outcome implements Serializable 
{
	/**
	 * Class Outcome - sets up an Outcome object.
	 * 
	 * @author Daryl Smith, MSc IT
	 * @version 1
	 */

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Player> winner;
	private int winningScore;
	private int quizID;
	
	//constructor
	public Outcome(ArrayList<Player> winner, int winningScore, int quizID) 
	{
		this.winner = winner;
		this.winningScore = winningScore;
		this.quizID = quizID;
	}

	//getters
	public ArrayList<Player> getWinner() 
	{
		return this.winner;
	}

	public int getWinningScore() 
	{
		return this.winningScore;
	}

	public int getQuizID() 
	{
		return this.quizID;	
	}
}