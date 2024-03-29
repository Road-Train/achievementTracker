package Observer;

import java.util.EventListener;

public class PositiveFriend extends FriendUser implements EventListener {
    public PositiveFriend(String name)
    {
        super(name);
    }

    public void update(String context, int number)
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
            System.out.println(getName() + " said: Nice job! Have fun with this game!");
        }
        else if (context.equals("Completed"))
        {
            System.out.println(getName() + " said: Super fast! Proud of you!");
        }
    }

}
