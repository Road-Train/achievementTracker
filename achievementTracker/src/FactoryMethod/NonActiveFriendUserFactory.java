package FactoryMethod;

import Observer.FriendUser;

public class NonActiveFriendUserFactory extends FriendUserFactory {


	public FriendUser createFriendUser() {
		return null;
	}

}
