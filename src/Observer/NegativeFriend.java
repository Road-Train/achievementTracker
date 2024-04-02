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
			case "New" -> "Completed this achievement last year lol";
			case "Edit" -> "Don't care";
			case "Progress" -> "You can do better than this right?";
			case "Completed" -> "I completed this way faster than you...";
			default -> null;
		};
	}
}
