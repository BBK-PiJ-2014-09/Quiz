import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class ClientTest - manages the client quiz playing experience.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class ClientTest 
{
	public static void main(String args[]) throws NotBoundException, IOException
	{
		Registry registry = LocateRegistry.getRegistry("localhost", 1489);
		Remote remote = registry.lookup("Test server");
		
		QuizInterface interfacetester = (QuizInterface)remote;
		
		 ArrayList<QuizSummary> quizSummary = interfacetester.getQuizList();
		 for (int i = 0; i < quizSummary.size(); i++) 
		 {
			 System.out.println(quizSummary.get(i));
		 }
		 System.out.println("Please select the quiz (id) you would like to play");
		 
		 Scanner in = new Scanner(System.in);
		 int quizID = in.nextInt();
		 Quiz currentQuiz = interfacetester.chooseQuiz(quizID);
		 ArrayList<Question> questions = currentQuiz.getQuestionList();
		 for (int i = 0; i < questions.size(); i++) 
		 {
			 questions.get(i).ask(in);		 	
		 }
		 
		 System.out.println("Please specify your first name");
		 String playerName = in.next();
		 
		 System.out.println("Please specify your username");
		 String playerUserName = in.next();
		 
		 Player player = new Player(playerName, playerUserName);
		 System.out.println(interfacetester.submitQuiz(currentQuiz, player));		 
	}
}