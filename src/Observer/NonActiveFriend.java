package Observer;

import java.util.Random;

public class NonActiveFriend extends FriendUser
{
	private final int responseNumber;
	
	public NonActiveFriend(String name)
	{
		super(name);
		Random random = new Random();
		int max = 10;
		int min = 1;
		this.responseNumber = random.nextInt(max - min + 1) + min;
	}
	
	public String update(String context)
	{
		Random random = new Random();
		int max = 10;
		int min = 1;
		int randomNumber = random.nextInt(max - min + 1) + min;
		
		if (randomNumber == responseNumber)
		{
			return switch (context)
			{
				case "New" -> STR."\{getName()} said: Nice, you finally started that achievement!";
				case "Edit" ->  STR."\{getName()} said: Good luck";
				case "Progress" -> STR."\{getName()} said: Nice job!";
				case "Completed" ->  STR."\{getName()} said: Super fast!";
				default -> null;
			};
		}
		return null;
	}
	
}
