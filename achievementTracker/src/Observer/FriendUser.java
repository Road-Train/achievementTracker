package Observer;

public abstract class FriendUser {

	private String name;

	public abstract String update(String context);

	public String getName()
	{
		return name;
	}

}
