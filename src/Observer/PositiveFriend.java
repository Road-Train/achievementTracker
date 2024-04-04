package Observer;

public class PositiveFriend extends FriendUser
{
	public PositiveFriend(String name)
	{
		super(name);
	}
	
	public String update(Context context)
	{
		return switch (context)
		{
			case NEW -> "Nice, you finally started that achievement!";
			case EDIT -> "Good luck";
			case PROGRESS -> "Nice job! Have fun with this game!";
			case COMPLETED -> "Super fast! Proud of you!";
		};
	}
	
}
