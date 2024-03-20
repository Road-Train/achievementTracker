package FactoryMethod;

import Observer.*;

public class NegativeFriendUserFactory extends FriendUserFactory
{

	public FriendUser createFriendUser()
	{
		return new NegativeFriend();
	}

}
