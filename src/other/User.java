package other;

import Memento.Achievement;
import Observer.Context;
import Observer.FriendUser;

import java.util.LinkedList;

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
	public void addAchievement(String game, String title, String description, int progress)
	{
		this.achievementList.add(new Achievement(game,title,description,progress));
		notifyFriends(Context.NEW);
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
	private void notifyFriends(Context context)
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
	public void editAchievement(Achievement achievement,String newGame, String newTitle, String newDescription, int newTotalProgress)
	{
		achievement.setGame(newGame);
		achievement.setTitle(newTitle);
		achievement.setDescription(newDescription);
		notifyFriends(Context.EDIT);
		if(achievement.setTotalProgress(newTotalProgress))
		{
			notifyFriends(Context.COMPLETED);
		}
	}
	public void updateProgress(Achievement achievement, int progress)
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
			notifyFriends(Context.PROGRESS);
		}
		else if (percentProgressNew > percentProgress && percentProgressNew == totalProgress)
		{
			notifyFriends(Context.COMPLETED);
		}
		else
		{
			notifyFriends(Context.EDIT);
		}
	}
	
	public void saveAchievement(Achievement achievementToSave)
	{
		System.out.println(achievementToSave.save());
	}
	
	public void restoreMemento(Achievement achievement, int index)
	{
		achievement.restore(index);
	}
	
	public void importAchievement()
	{
		Achievement achievement = Achievement.importAchievement();
		achievementList.add(achievement);
	}
}
