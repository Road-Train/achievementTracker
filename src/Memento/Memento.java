package Memento;

import other.JsonWriter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Memento implements Serializable
{
	
	private final String game;
	private final String title;
	private final String description;
	private final int progress;
	private final int totalProgress;
	private final LocalDateTime dateAchieved;
	private final LocalDateTime dateTime;
	
	public Memento(String game, String title, String description, int totalProgress, int progress, LocalDateTime dateAchieved)
	{
		this.game = game;
		this.title = title;
		this.description = description;
		this.progress = progress;
		this.totalProgress = totalProgress;
		this.dateTime = LocalDateTime.now();
		this.dateAchieved = dateAchieved;
	}
	
	public String getGame()
	{
		return game;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getProgress()
	{
		return progress;
	}
	
	public int getTotalProgress()
	{
		return totalProgress;
	}
	
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
	
	public LocalDateTime getDateAchieved()
	{
		return dateAchieved;
	}
	
	public void getJson()
	{
		JsonWriter jsonWriter = new JsonWriter();
		jsonWriter.writeMementoToJson(this);
	}
	
	public void setJson()
	{
	
	}
}
