package Observer;

import java.io.File;
import java.util.EventListener;

public class NegativeFriend extends FriendUser implements EventListener
{

    private String email;
    public NegativeFriend(String name)
    {
        super(name);
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void update(String context, int number)
    {
        if (context.equals("New"))
        {
            System.out.println(getName() + " said: Completed this achievement last year lol");
        }
        else if (context.equals("Edit"))
        {
            System.out.println(getName() + " said: Don't care");
        }
        else if (context.equals("Progress"))
        {
            System.out.println(getName() + " said: You can do better than this right?");
        }
        else if (context.equals("Completed"))
        {
            System.out.println(getName() + " said: I completed this way faster than you...");
        }
    }
}
