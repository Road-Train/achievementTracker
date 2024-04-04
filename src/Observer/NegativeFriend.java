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
	
	public String update(Context context)
	{
		return switch (context)
		{
			case NEW -> "Completed this achievement last year lol";
			case EDIT -> "Don't care";
			case PROGRESS -> "You can do better than this right?";
			case COMPLETED -> "I completed this way faster than you...";
		};
	}
}
