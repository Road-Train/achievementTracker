package Observer;

public abstract class FriendUser {

	private String name;

	public abstract String update(String context);

	FriendUser(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

}
