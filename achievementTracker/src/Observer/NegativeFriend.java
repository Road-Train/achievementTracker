package Observer;

import java.io.File;
import java.util.EventListener;

public class NegativeFriend extends FriendUser implements EventListener
{

    private String email;
    public NegativeFriend(String name)
    {
        super(name);
//        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String update(String context)
    {
        return getName() + " received an update for: " + context;
    }

    public void update(String eventType, File file)
    {
        // eventType = achievementNew, achievementUpdate, achievementCustomized
        // Someone has created a new achievement.
        // een melding in beeld, hoe groot?
        System.out.println("Email to " + this.email + "text" + eventType + "text" + file);
    }

}
