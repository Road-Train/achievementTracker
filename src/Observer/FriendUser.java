package Observer;

import java.io.File;
import java.util.EventListener;

public abstract class FriendUser implements EventListener
{

	private String name;

	public FriendUser(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public abstract void update(String context, int number);
}
