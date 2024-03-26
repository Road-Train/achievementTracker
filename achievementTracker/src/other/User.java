package other;

import Memento.Memento;
import Observer.FriendUser;

import java.util.ArrayList;

import FactoryMethod.*;

public class User
{
	private String name;
	private ArrayList<Achievement> achievementList;
	private ArrayList<FriendUser> friendList;

	public User(String name)
	{
		this.name = name;
		this.achievementList = new ArrayList<>();
		this.friendList = new ArrayList<>();
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Achievement> getAchievementList()
	{
		return this.achievementList;
	}

	public void addAchievement(Achievement achievement)
	{
		this.achievementList.add(achievement);
	}

	public void removeAchievement(Achievement achievement)
	{
		this.achievementList.remove(achievement);
	}

	public ArrayList<FriendUser> getFriendList()
	{
		return this.friendList;
	}

	public void addFriend(FriendUser friend)
	{
		this.friendList.add(friend);
	}

	public void removeFriend(FriendUser friend)
	{
		this.friendList.remove(friend);
	}


	public void notifyFriends()
	{

	}

	public void editAchievement(Achievement achievement)
	{

	}

	public Memento saveMemento(Achievement achievementToSave)
	{
		return null;
	}

	public void restoreMemento(Memento memento)
	{

	}

	public FriendUser createFriend(String type, String name)
	{
		switch (type) {
			case "positive":
				return new PositiveFriendUserFactory().createFriendUser(name);
			case "negative":
				return new NegativeFriendUserFactory().createFriendUser(name);
			default:
				return new NonActiveFriendUserFactory().createFriendUser(name);
		}
	}
}
