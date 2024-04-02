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
	
	public void update(String context)
	{
		Random random = new Random();
		int max = 10;
		int min = 1;
		int randomNumber = random.nextInt(max - min + 1) + min;
		
		if (randomNumber == responseNumber)
		{
			switch (context)
			{
				case "New" -> System.out.println(getName() + " said: Nice, you finally started that achievement!");
				case "Edit" -> System.out.println(getName() + " said: Good luck");
				case "Progress" -> System.out.println(getName() + " said: Nice job!");
				case "Completed" -> System.out.println(getName() + " said: Super fast!");
			}
		}
		else
		{
			System.out.println("Friend is nonactive!");
		}
	}
	
}
