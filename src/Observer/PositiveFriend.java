package Observer;

public class PositiveFriend extends FriendUser
{
	public PositiveFriend(String name)
	{
		super(name);
	}
	
	public void update(String context)
	{
		switch (context)
		{
			case "New" -> System.out.println(getName() + " said: Nice, you finally started that achievement!");
			case "Edit" -> System.out.println(getName() + " said: Good luck");
			case "Progress" -> System.out.println(getName() + " said: Nice job! Have fun with this game!");
			case "Completed" -> System.out.println(getName() + " said: Super fast! Proud of you!");
		}
	}
	
}
