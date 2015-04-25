import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Class to manage a Client Quiz Interface
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public interface QuizInterface extends Remote 
{
	/** 
	  * Gets a list of available quizzes  
	  * @return an list of available quizzes
	  * @throws RemoteException if any checked exception occurs as part of a remote procedire call
	  * @throws IOException if any IO exception occurs
	*/ 
	ArrayList<QuizSummary> getQuizList() throws RemoteException, IOException;

	/** 
	  * Selects a quiz to play
	  * @param quizID - the ID of the quiz to play
	  * @return the requested quiz
	  * @throws RemoteException if any checked exception occurs as part of a remote procedire call
	  * @throws IOException if any IO exception occurs
	*/ 	
	Quiz chooseQuiz(int quizID) throws RemoteException, IOException;

	/** 
	  * Submits a quiz to have marked with the score and player details saved
	  * @param Quiz - the quiz, with answers, to be scored
	  * @param Player - the details of the user playing the quiz
	  * @return the requested quiz
	  * @throws RemoteException if any checked exception occurs as part of a remote procedire call
	  * @throws IOException if any IO exception occurs
	*/ 	
	String submitQuiz(Quiz quiz, Player player) throws RemoteException, FileNotFoundException, IOException;		
}