import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Class to implement both the Quiz Interface and Quiz Administration Interfaces
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class ServerImpl extends UnicastRemoteObject implements QuizInterface, QuizAdminInterface 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ServerImpl() throws RemoteException 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized ArrayList<QuizSummary> getQuizList() throws IOException 
	{
		Reader reader = new FileReader("Quizes.json");		
		Object obj = JSONValue.parse(reader);
		
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray jsonQuizArray = (JSONArray) jsonObj.get("Quizes");
		int NumOfQuizes = jsonQuizArray.size();

		ArrayList<QuizSummary> quizSummaryList = new ArrayList<QuizSummary>(); 
		for (int i = 0; i < NumOfQuizes; i++) {
			JSONObject jsonQuizSummaryObj = (JSONObject) jsonQuizArray.get(i);			
			long quizID = (long)jsonQuizSummaryObj.get("ID");
			String quizName = (String) jsonQuizSummaryObj.get("Name");
			String quizDesc = (String) jsonQuizSummaryObj.get("Description");
			QuizSummary quizSummary = new QuizSummary(quizID, quizName, quizDesc);
			quizSummaryList.add(quizSummary);
		}
		
		reader.close();
		return quizSummaryList;
	}

	@Override
	public synchronized Quiz chooseQuiz(int quizID) throws IOException 
	{
		Reader reader = new FileReader("Quizes.json");		
		Object obj = JSONValue.parse(reader);
		
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray jsonQuizArray = (JSONArray) jsonObj.get("Quizes");
		int NumOfQuizes = jsonQuizArray.size();

		JSONObject quizObject = null;
		//ArrayList<QuizSummary> quizSummaryList = new ArrayList<QuizSummary>(); 
		for (int i = 0; i < NumOfQuizes; i++) 
		{
			JSONObject jsonQuizObj = (JSONObject) jsonQuizArray.get(i);			
			long inputQuizID = (long)jsonQuizObj.get("ID");
			//String quizName = (String) jsonQuizSummaryObj.get("Name");
			//String quizDesc = (String) jsonQuizSummaryObj.get("Description");
			if (inputQuizID ==  quizID) 
			{
				quizObject = jsonQuizObj;
			}
		}
		if (quizObject == null) 
		{
			return null;
		}
		
		ArrayList<Question> myQuestions = new ArrayList<Question>();
		
		JSONArray myJSONQuestions = (JSONArray) quizObject.get("Questions");
		
		int numOfQuestions = myJSONQuestions.size();
		for (int i = 0; i < numOfQuestions; i++) 
		{
			JSONObject myJSONQuestionObj = (JSONObject) myJSONQuestions.get(i);
						
			String myQuestion = (String) myJSONQuestionObj.get("Question");
			JSONArray myJSONAnswers = (JSONArray) myJSONQuestionObj.get("Answers");
			ArrayList<String> myAnswers = new ArrayList<String>();
			int numOfAnswers = myJSONAnswers.size();
			for (int j = 0; j < numOfAnswers; j++) 
			{
				myAnswers.add((String) myJSONAnswers.get(j));
			}
			
			int correctAnswer = ((Long) myJSONQuestionObj.get("CorrectAnswer")).intValue();
			//boolean clientAnswerCorrect = false;

			Question thisQuestion = new Question(myQuestion, myAnswers, correctAnswer);

			myQuestions.add(thisQuestion);
		}
		
		long inputQuizID = (long)quizObject.get("ID");
		String quizName = (String) quizObject.get("Name");
		String quizDesc = (String) quizObject.get("Description");
		QuizSummary summary = new QuizSummary(inputQuizID,quizName,quizDesc);
		Quiz myQuiz = new Quiz(myQuestions,summary);

		reader.close();
		return myQuiz;
	}

	@Override
	public synchronized String submitQuiz(Quiz quiz, Player player) throws IOException 
	{
		Reader reader = new FileReader("HighScoreList.json");		
		Object obj = JSONValue.parse(reader);
		reader.close();
		
		JSONObject jsonObj = (JSONObject) obj;
		
		Object quizArray = jsonObj.get(String.valueOf(quiz.getQuizSummary().getQuizID()));
		JSONArray jsonQuizArray = null;

		if (quizArray == null) 
		{
			jsonQuizArray = new JSONArray();
		} 
		else 
		{
			jsonQuizArray = (JSONArray) quizArray;
		}
		
		Map highScoreMap = new HashMap();
		highScoreMap.put("Name", player.getName());
		highScoreMap.put("UserName", player.getUserName());
		highScoreMap.put("Score", quiz.getCorrectAnswerScore());		
		
		jsonQuizArray.add(highScoreMap);
		jsonObj.put(String.valueOf(quiz.getQuizSummary().getQuizID()), jsonQuizArray);
		
		FileWriter myWriter = null;

		myWriter = new FileWriter("HighScoreList.json");
		myWriter.append(jsonObj.toJSONString());
		myWriter.flush();
		myWriter.close();
		
		return "Your score for this quiz is: " + quiz.getCorrectAnswerScore() + " out of " + quiz.getQuestionList().size();		
	}

	@Override
	public synchronized long createQuiz(Quiz quiz) throws IOException 
	{		
		Reader reader = new FileReader("Quizes.json");		
		Object obj = JSONValue.parse(reader);
		reader.close();
		
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray jsonQuizArray = (JSONArray) jsonObj.get("Quizes");
		long jsonNewQuizID = (long) jsonObj.get("NewQuizID");
		
		Map myQuizMap = new HashMap();
		myQuizMap.put("ID", jsonNewQuizID);
		myQuizMap.put("Name", quiz.getQuizSummary().getQuizName());
		myQuizMap.put("Description", quiz.getQuizSummary().getQuizDesc());
		ArrayList myQuestions = new ArrayList();
		for (int i = 0; i < quiz.getQuestionList().size(); i++) 
		{
			Map myQuestionMap = new HashMap();
			myQuestionMap.put("CorrectAnswer", quiz.getQuestionList().get(i).getCorrectAnswer());
			myQuestionMap.put("Question", quiz.getQuestionList().get(i).getQuestion());
			ArrayList myAnswers = new ArrayList();
			for (int j = 0; j < quiz.getQuestionList().get(i).getAnswers().size(); j++) 
			{
				myAnswers.add(quiz.getQuestionList().get(i).getAnswers().get(j));
			}
			myQuestionMap.put("Answers", myAnswers);
			myQuestions.add(myQuestionMap);
		}
		myQuizMap.put("Questions", myQuestions);
		
		jsonQuizArray.add(myQuizMap);
		jsonObj.put("Quizes", jsonQuizArray);
		jsonNewQuizID++;
		jsonObj.put("NewQuizID", jsonNewQuizID);

		FileWriter myWriter = null;

		myWriter = new FileWriter("Quizes.json");
		myWriter.append(jsonObj.toJSONString());
		myWriter.flush();
		myWriter.close();
		
		return jsonNewQuizID - 1;
	}

	@Override
	public synchronized Outcome closeQuiz(int QuizID) throws IOException 
	{
		Reader reader = new FileReader("Quizes.json");		
		Object obj = JSONValue.parse(reader);
		reader.close();
		
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray jsonQuizArray = (JSONArray) jsonObj.get("Quizes");
		
		int quizToDelete = -1;		
		for (int i = 0; i < jsonQuizArray.size(); i++) 
		{
			JSONObject jsonQuizObj = (JSONObject) jsonQuizArray.get(i);
			if ((String.valueOf(jsonQuizObj.get("ID"))).equals(String.valueOf(QuizID))) 
			{
				quizToDelete = i;
				break;
			}
		}

		jsonQuizArray.remove(quizToDelete);
		jsonObj.put("Quizes", jsonQuizArray);
		
		FileWriter myWriter = null;

		myWriter = new FileWriter("Quizes.json");
		myWriter.append(jsonObj.toJSONString());
		myWriter.flush();
		myWriter.close();
				
		Reader reader2 = new FileReader("HighScoreList.json");		
		Object obj2 = JSONValue.parse(reader2);
		reader2.close();
		
		JSONObject jsonObj2 = (JSONObject) obj2;
		Object jsonAttemptArrayObject = jsonObj2.get(String.valueOf(QuizID));
		
		if (jsonAttemptArrayObject == null) 
		{
			return null;
		}
		
		JSONArray jsonAttemptArray = (JSONArray) jsonAttemptArrayObject;

		long highScore = -1;
		for (int i = 0; i < jsonAttemptArray.size(); i++) 
		{
			JSONObject attempt = (JSONObject) jsonAttemptArray.get(i);
			if ((long) attempt.get("Score") > highScore) 
			{
				highScore = (long) attempt.get("Score");
			}
		}
		ArrayList<Player> highScorePlayers = new ArrayList<Player>();
		for (int j = 0; j < jsonAttemptArray.size(); j++) {
			JSONObject attempt = (JSONObject) jsonAttemptArray.get(j);
			if ((long) attempt.get("Score") == highScore) 
			{
				Player highScorePlayer = new Player((String) attempt.get("Name"), (String) attempt.get("UserName"));				
				highScorePlayers.add(highScorePlayer);
			}
		}
		
		Outcome quizOutcome = new Outcome(highScorePlayers, (int) highScore, QuizID);
		
		return quizOutcome;
	}
}