package Observer;

public abstract class FriendUser
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
	
	public abstract void update(String context);
}
