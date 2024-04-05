package FactoryMethod;

import Observer.FriendUser;
import Observer.PositiveFriend;

public class PositiveFriendUserFactory extends FriendUserFactory
{
	
	public FriendUser createFriendUser(String name)
	{
		return new PositiveFriend(name);
	}
	
}
