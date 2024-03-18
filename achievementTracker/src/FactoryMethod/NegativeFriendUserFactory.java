package FactoryMethod;

import Observer.FriendUser;

public class NegativeFriendUserFactory extends FriendUserFactory {

	public FriendUser createFriendUser() {
		return null;
	}

}
