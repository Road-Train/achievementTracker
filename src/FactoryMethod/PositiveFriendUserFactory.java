package FactoryMethod;

import Observer.*;

public class PositiveFriendUserFactory extends FriendUserFactory
{
	
	public FriendUser createFriendUser(String name)
	{
		return new PositiveFriend(name);
	}
	
}
