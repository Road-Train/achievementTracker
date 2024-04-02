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
			case "New" -> STR."\{getName()} said: Nice, you finally started that achievement!";
			case "Edit" -> getName() + " said: Good luck";
			case "Progress" -> getName() + " said: Nice job! Have fun with this game!";
			case "Completed" -> getName() + " said: Super fast! Proud of you!";
			default -> null;
		};
	}
	
}
