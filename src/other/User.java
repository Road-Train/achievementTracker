package other;

import Memento.Achievement;
import Memento.JsonReader;
import Observer.FriendUser;

import java.util.LinkedList;

import FactoryMethod.*;

public class User
{
	private final LinkedList<Achievement> achievementList;
	private final LinkedList<FriendUser> friendList;
	private String name;
	
	public User(String name)
	{
		this.name = name;
		this.achievementList = new LinkedList<>();
		this.friendList = new LinkedList<>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public LinkedList<Achievement> getAchievementList()
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
	
	public LinkedList<FriendUser> getFriendList()
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
					System.out.println(STR."\{friendUser.getName()} said: \{friendUser.update(context)}");
				}
			}
		}
	}
	
	public void editAchievement(Achievement achievement, int progress)
	{
		int progressAchievement = achievement.getProgress();
		int totalProgress = achievement.getTotalProgress();
		int newProgress = 0;
		double percentProgress = (double) 100 / totalProgress * progressAchievement;
		
		if (progress == 0)
		{
			newProgress = progressAchievement;
		}
		else if (progress > progressAchievement)
		{
			newProgress = progress;
		}
		
		double percentProgressNew = (double) 100 / totalProgress * newProgress;
		
		if (percentProgressNew > percentProgress && percentProgressNew < totalProgress)
		{
			notifyFriends("Progress");
		}
		else if (percentProgressNew > percentProgress && percentProgressNew == totalProgress)
		{
			notifyFriends("Completed");
		}
		else
		{
			notifyFriends("Edit");
		}
	}
	
	public void saveMemento(Achievement achievementToSave)
	{
		achievementToSave.save();
	}
	
	public void restoreMemento(Achievement achievement, int index)
	{
		achievement.restore(index);
	}
	
	public void importAchievement()
	{
		JsonReader jsonReader = new JsonReader();
		Achievement achievement = new Achievement(jsonReader.readMementoFromJson());
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
