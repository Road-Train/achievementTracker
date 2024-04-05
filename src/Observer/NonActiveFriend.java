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
	
	public String update(Context context)
	{
		Random random = new Random();
		int max = 10;
		int min = 1;
		int randomNumber = random.nextInt(max - min + 1) + min;
		
		if (randomNumber == responseNumber)
		{
			return switch (context)
			{
				case NEW -> "Nice, you finally started that achievement!";
				case EDIT -> "Good luck";
				case PROGRESS -> "Nice job!";
				case COMPLETED -> "Super fast!";
			};
		}
		else
		{
			return null;
		}
	}
	
}
