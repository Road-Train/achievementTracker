package other;

import Memento.Memento;
import Observer.FriendUser;

import java.util.ArrayList;

import FactoryMethod.*;

public class User
{
	private final ArrayList<Achievement> achievementList;
	private final ArrayList<FriendUser> friendList;
	private String name;
	
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
		if (this.friendList != null && !this.friendList.isEmpty())
		{
			for (FriendUser friendUser : this.friendList)
			{
				friendUser.update(context);
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
		return achievementToSave.save();
	}
	
	public void restoreMemento(Memento memento)
	{
		Achievement achievement = new Achievement(null, null, 0, 0);
		achievement.restore(memento);
		
		achievementList.add(achievement);
	}
	
	public void restoreMementoFromJson()
	{
		JsonReader jsonReader = new JsonReader();
		Memento memento = jsonReader.readMementoFromJson();
		
		Achievement achievement = new Achievement(null, null, 0, 0);
		achievement.restore(memento);
		
		achievementList.add(achievement);
	}
	
	public FriendUser createFriend(String type, String name)
	{
		switch (type)
		{
			case "positive":
				return new PositiveFriendUserFactory().createFriendUser(name);
			case "negative":
				return new NegativeFriendUserFactory().createFriendUser(name);
			default:
				return new NonActiveFriendUserFactory().createFriendUser(name);
		}
	}
}
