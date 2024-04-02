package Observer;

public class PositiveFriend extends FriendUser
{
	public PositiveFriend(String name)
	{
		super(name);
	}
	
	public String update(String context)
	{
		return switch (context)
		{
			case "New" -> "Nice, you finally started that achievement!";
			case "Edit" -> "Good luck";
			case "Progress" -> "Nice job! Have fun with this game!";
			case "Completed" -> "Super fast! Proud of you!";
			default -> null;
		};
	}
	
}
