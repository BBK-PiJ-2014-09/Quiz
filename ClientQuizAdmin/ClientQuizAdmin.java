import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class ClientQuizAdmin - implements the ClientQuizAdmin Interface for admin functionality.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class ClientQuizAdmin 
{
	public static void main(String[] args) throws NotBoundException, IOException
	{		
		Registry registry = LocateRegistry.getRegistry("localhost", 1489);
		Remote remote = registry.lookup("Test server");
				
		QuizAdminInterface adminInterface = (QuizAdminInterface) remote;
		QuizInterface interfacetester = (QuizInterface) remote;
		
		// First ask whether to create or close a quiz:
		Scanner scanner = new Scanner(System.in);
		System.out.println("What would you like to do?\n" + "1. Create a new quiz\n2. Close a quiz");
		String answer = scanner.nextLine();
		
		if (answer.equals("1")) 
		{
			Quiz quiz = createQuiz(scanner);
			long ans = adminInterface.createQuiz(quiz);
			System.out.println("The server successfully added a quiz with id " + ans);
			
		} 
		else if (answer.equals("2")) 
		{
			System.out.println("Please enter the ID of the quiz you'd like to close. As a reminder, the Quiz IDs are:");
			ArrayList<QuizSummary> quizSummary = interfacetester.getQuizList();
			for (int i = 0; i < quizSummary.size(); i++) 
			{
				System.out.println(quizSummary.get(i));
			}

			int idToClose = closeQuiz(scanner);
			Outcome outcome = adminInterface.closeQuiz(idToClose);
			
			if (outcome == null) 
			{
				System.out.println("This quiz was not played by anyone");
				return;
			}
			if (outcome.getWinningScore() == -1) 
			{
				System.out.println("You have supplied an invalid QuizID");
				return;
			}
			
			System.out.println("Top score was: " + outcome.getWinningScore());
			System.out.println("The following players had this score:");
			for (int i = 0; i < outcome.getWinner().size(); i++) 
			{
				System.out.println(" * " + outcome.getWinner().get(i).getName() + 
						" (username: " + outcome.getWinner().get(i).getUserName() + ")");
			}
		}
	}

	/**
	  * Returns a newly created  quiz.
	  * @param takes input from the user 
	*/ 	
	public static Quiz createQuiz(Scanner scanner) 
	{
		System.out.println("Please provide quiz name:");
		String name = scanner.nextLine();
		System.out.println("Please provide quiz description:");
		String desc = scanner.nextLine();
		
		ArrayList<Question> questions = new ArrayList<Question>();
		boolean keepAdding = true;
		while (keepAdding) {
			System.out.println("Enter question:");
			String question = scanner.nextLine();
			
			boolean keepAddingAnswers = true;
			ArrayList<String> possibleAnswers = new ArrayList<String>();
			while (keepAddingAnswers) {
				System.out.println("Enter possible answer:");
				String ans = scanner.nextLine();
				possibleAnswers.add(ans);
				System.out.println("Add another? Y/N");
				String keepAddingAnswersString = scanner.nextLine();
				if (!keepAddingAnswersString.equalsIgnoreCase("y")) 
				{
					keepAddingAnswers = false;
				}
			}

			System.out.println("Enter the index of the correct answer. As a reminder, the indices and answers are:");
			for (int i = 0; i < possibleAnswers.size(); i++) 
			{
				System.out.println(" " + i + ": " + possibleAnswers.get(i));
			}
			int correctIndex = Integer.parseInt(scanner.nextLine());
			
			Question q = new Question(question,possibleAnswers,correctIndex);
			
			questions.add(q);
			
			System.out.println("\n Would you like to add another question? Y/N");
			String cont = scanner.nextLine();
			if (!cont.equalsIgnoreCase("Y")) 
			{
				keepAdding = false;
			}
		}		

		QuizSummary quizSummary = new QuizSummary(0, name, desc);
		Quiz quiz = new Quiz(questions, quizSummary);
		
		return quiz;		
	}

	/**
	  * Returns the ID of a quiz to be deleted.
	  * @param takes input from the user . 
	*/ 	
	public static int closeQuiz(Scanner scanner) 
	{		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the ID of the quiz you'd like to close:");
		
		int id = in.nextInt();
		
		return id;
	}
}