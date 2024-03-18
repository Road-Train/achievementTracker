package other;

import Observer.FriendUser;

public class Achievement {
	private String game;
	private String description;
	private int progress;
	private int totalProgress;

	public Achievement(String game, String description, int progress, int totalProgress)
	{
		this.game = game;
		this.description = description;
		this.progress = progress;
		this.totalProgress = totalProgress;
	}

	public String getGame()
	{
		return this.game;
	}

	public void setGame(String game)
	{
		this.game = game;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getProgress()
	{
		return this.progress;
	}

	public void setProgress(int progress)
	{
		this.progress = progress;
	}

	public int getTotalProgress()
	{
		return this.totalProgress;
	}

	public void setTotalProgress(int totalProgress)
	{
		this.totalProgress = totalProgress;
	}
}
