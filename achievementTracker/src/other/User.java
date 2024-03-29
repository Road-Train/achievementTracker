package other;

import Memento.Memento;
import Observer.FriendUser;

import java.util.ArrayList;
import java.util.Random;

import FactoryMethod.*;
import Observer.NegativeFriend;
import Observer.NonActiveFriend;

import javax.imageio.plugins.tiff.TIFFDirectory;

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


	// FactoryMethod
	public void addAchievement(Achievement achievement)
	{
		this.achievementList.add(achievement);
		notifyFriends("New");
	}

	// FactoryMethod
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

	// Observer
	public void notifyFriends(String context)
	{
		Random random = new Random();
		int max = 10;
		int min = 1;
		int number = random.nextInt(max - min + 1) + min;

		if (this.friendList != null && !this.friendList.isEmpty())
		{
			for (FriendUser friendUser : this.friendList)
			{
				if (friendUser instanceof NonActiveFriend)
				{
					friendUser.update(context, number);
				}
				else
				{
					friendUser.update(context, 0);
				}
			}
		}
		else
		{
			System.out.println("No friends in your friendslist to notify!");
		}
	}

	public void editAchievement(Achievement achievement, int progress)
	{
		int progressAchievement = achievement.getProgress();
		int totalProgress = achievement.getTotalProgress();
		int newProgress = 0;
		double procentProgress = (double) 100 / totalProgress * progressAchievement;

		if (progress == 0)
		{
			newProgress = progressAchievement;
		}
		else if (progress != 0 && progress > progressAchievement)
		{
			newProgress = progress;
		}

		double procentProgressNew = (double) 100 / totalProgress * newProgress;

		if (procentProgressNew > procentProgress && procentProgressNew < totalProgress)
		{
			notifyFriends("Progress");
		}
		else if (procentProgressNew > procentProgress && procentProgressNew == totalProgress)
		{
			notifyFriends("Completed");
		}
		else
		{
			notifyFriends("Edit");
		}
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
