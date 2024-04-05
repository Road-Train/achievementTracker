package FactoryMethod;

import Observer.FriendUser;
import Observer.NonActiveFriend;

public class NonActiveFriendUserFactory extends FriendUserFactory
{
	
	public FriendUser createFriendUser(String name)
	{
		return new NonActiveFriend(name);
	}
	
}
