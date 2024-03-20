package FactoryMethod;

import Observer.*;

public class PositiveFriendUserFactory extends FriendUserFactory
{

	public FriendUser createFriendUser()
	{
		return new PositiveFriend();
	}

}
