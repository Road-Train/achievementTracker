package Observer;

public class NegativeFriend extends FriendUser
{
	
	private String email;
	
	public NegativeFriend(String name)
	{
		super(name);
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void update(String context)
	{
		switch (context)
		{
			case "New" -> System.out.println(getName() + " said: Completed this achievement last year lol");
			case "Edit" -> System.out.println(getName() + " said: Don't care");
			case "Progress" -> System.out.println(getName() + " said: You can do better than this right?");
			case "Completed" -> System.out.println(getName() + " said: I completed this way faster than you...");
		}
	}
}
