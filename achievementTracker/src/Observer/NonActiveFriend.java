package Observer;

import java.util.EventListener;

public class NonActiveFriend extends FriendUser implements EventListener
{
    public NonActiveFriend(String name)
    {
        super(name);
    }

    public String update(String context)
    {
        return getName() + " received an update for: " + context;
    }

}
