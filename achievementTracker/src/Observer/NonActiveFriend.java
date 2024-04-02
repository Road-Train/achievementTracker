package Observer;

import java.util.EventListener;
import java.util.Random;

public class NonActiveFriend extends FriendUser implements EventListener
{
    public NonActiveFriend(String name)
    {
        super(name);
    }

    public void update(String context, int number)
    {
        Random random = new Random();
        int max = 10;
        int min = 1;
        int randomNumber = random.nextInt(max - min + 1) + min;

        if (randomNumber == number)
        {
            if (context.equals("New"))
            {
                System.out.println(getName() + " said: Nice, you finally started that achievement!");
            }
            else if (context.equals("Edit"))
            {
                System.out.println(getName() + " said: Good luck");
            }
            else if (context.equals("Progress"))
            {
                System.out.println(getName() + " said: Nice job!");
            }
            else if (context.equals("Completed"))
            {
                System.out.println(getName() + " said: Super fast!");
            }
        }
        else
        {
            System.out.println("Friend is nonactive!");
        }
    }

}
