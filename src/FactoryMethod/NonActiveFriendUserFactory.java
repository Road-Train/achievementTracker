package FactoryMethod;

import Observer.*;

public class NonActiveFriendUserFactory extends FriendUserFactory
{

	public FriendUser createFriendUser(String name)
	{
		return new NonActiveFriend(name);
	}

}
