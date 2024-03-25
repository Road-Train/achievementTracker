package Observer;

import java.util.EventListener;

public class PositiveFriend extends FriendUser implements EventListener {
    public PositiveFriend(String name)
    {
        super(name);
    }

    public String update(String context)
    {
        return getName() + " received an update for: " + context;
    }

}
