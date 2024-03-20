package FactoryMethod;

import Observer.*;

public class NonActiveFriendUserFactory extends FriendUserFactory
{

	public FriendUser createFriendUser()
	{
		return new NonActiveFriend();
	}

}
