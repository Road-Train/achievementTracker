package Memento;

import java.time.LocalDateTime;

public class Memento {

	private String game;

	private String description;

	private int progress;

	private int totalProgress;

	private LocalDateTime dateTime;

	private Memento(String game, String description, int totalProgress)
	{
		this.game = game;
		this.description = description;
		this.progress = 0;
		this.totalProgress = totalProgress;
		this.dateTime = LocalDateTime.now();
	}

	public String getGame()
	{
		return game;
	}

	public void setGame(String game)
	{
		this.game = game;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getProgress()
	{
		return progress;
	}

	public void setProgress(int progress)
	{
		this.progress = progress;
	}

	public int getTotalProgress()
	{
		return totalProgress;
	}

	public void setTotalProgress(int totalProgress)
	{
		this.totalProgress = totalProgress;
	}

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public void getJson() {

	}

	public void setJson() {

	}

	public void updateProgress(int current)
	{
		progress = current;
	}

}
