package FactoryMethod;

import Observer.FriendUser;
import Observer.NegativeFriend;

public class NegativeFriendUserFactory extends FriendUserFactory
{
	
	public FriendUser createFriendUser(String name)
	{
		return new NegativeFriend(name);
	}
	
}
