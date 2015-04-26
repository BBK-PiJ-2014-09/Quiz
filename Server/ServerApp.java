import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class to run the Server application of the client/server quiz system
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class ServerApp 
{
	public static void main(String args[]) throws RemoteException, AlreadyBoundException 
	{
		ServerImpl serverImpl = new ServerImpl();

		//access Java/computer registry responsible for attaching programs to ports
		Registry registry = LocateRegistry.createRegistry(1489);

		//bind a specific Java object to a specific Port
		registry.bind("Test server", serverImpl);
		System.out.println("The server has started");
	}
}
