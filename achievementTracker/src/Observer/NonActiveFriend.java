package Observer;

public class NonActiveFriend extends FriendUser {

    public NonActiveFriend(String name)
    {
        super(name);
    }

    public String update(String context) {
        return null;
    }

}
