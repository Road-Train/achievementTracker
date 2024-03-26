package FactoryMethod;

import Observer.*;

public class NegativeFriendUserFactory extends FriendUserFactory
{

	public FriendUser createFriendUser(String name)
	{
		return new NegativeFriend(name);
	}

}
