import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface QuizAdminInterface extends Remote 
{
	long createQuiz(Quiz quiz) throws RemoteException, IOException;
	Outcome closeQuiz(int QuizID) throws RemoteException, FileNotFoundException, IOException;
}
