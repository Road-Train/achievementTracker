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
	
	public String update(String context)
	{
		return switch (context)
		{
			case "New" -> STR."\{getName()} said: Completed this achievement last year lol";
			case "Edit" -> STR."\{getName()} said: Don't care";
			case "Progress" -> STR."\{getName()} said: You can do better than this right?";
			case "Completed" -> STR."\{getName()} said: I completed this way faster than you...";
			default -> null;
		};
	}
}
