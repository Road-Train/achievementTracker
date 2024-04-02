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
	private void notifyFriends(String context)
	{
		if (!this.friendList.isEmpty())
		{
			for (FriendUser friendUser : this.friendList)
			{
				if(friendUser.update(context)!=null)
				{
					System.out.println(friendUser.update(context));
				}
			}
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
		else if (progress > progressAchievement)
		{
			newProgress = progress;
		}
		
		double percentProgressNew = (double) 100 / totalProgress * newProgress;
		
		if (percentProgressNew > procentProgress && percentProgressNew < totalProgress)
		{
			notifyFriends("Progress");
		}
		else if (percentProgressNew > procentProgress && percentProgressNew == totalProgress)
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
		Achievement achievement = new Achievement(memento);
		achievementList.add(achievement);
	}
	
	public void restoreMementoFromJson()
	{
		JsonReader jsonReader = new JsonReader();
		Memento memento = jsonReader.readMementoFromJson();
		
		Achievement achievement = new Achievement(memento);
		
		achievementList.add(achievement);
	}
	
	public FriendUser createFriend(String type, String name)
	{
		return switch (type)
		{
			case "positive" -> new PositiveFriendUserFactory().createFriendUser(name);
			case "negative" -> new NegativeFriendUserFactory().createFriendUser(name);
			default -> new NonActiveFriendUserFactory().createFriendUser(name);
		};
	}
}
