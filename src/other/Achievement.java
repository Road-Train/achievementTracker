package other;

import Memento.Memento;

import java.time.LocalDateTime;

public class Achievement
{
	
	private String game;
	private String description;
	private int progress;
	private int totalProgress;
	private LocalDateTime dateAchieved;
	
	public Achievement(String game, String description, int progress, int totalProgress)
	{
		this.game = game;
		this.description = description;
		this.progress = progress;
		this.totalProgress = totalProgress;
		this.dateAchieved = LocalDateTime.now();
	}
	
	public Memento save()
	{
		return new Memento(this.game, this.description, this.totalProgress, this.progress, this.dateAchieved);
	}
	
	public String getGame()
	{
		return this.game;
	}
	
	public void setGame(String game)
	{
		this.game = game;
	}
	
	public void restore(Memento memento)
	{
		this.game = memento.getGame();
		this.description = memento.getDescription();
		this.progress = memento.getProgress();
		this.totalProgress = memento.getTotalProgress();
		this.dateAchieved = memento.getDateTime();
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
	
	public LocalDateTime getDateAchieved()
	{
		return dateAchieved;
	}
	
	public void setDateAchieved(LocalDateTime dateAchieved)
	{
		this.dateAchieved = dateAchieved;
	}
}
