import java.io.Serializable;

public class Player implements Serializable
{
	/**
	 * Class Player - sets up the Player object.
	 * 
	 * @author Daryl Smith, MSc IT
	 * @version 10
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	private String userName = "";
	
	public Player(String name, String userName) 
	{
		this.name = name;
		this.userName = userName;
	}

	String getName() 
	{
		return this.name;
	}

	String getUserName() 
	{
		return this.userName;
	}
}
