package Memento;

import other.JsonWriter;

import java.time.LocalDateTime;

public class Memento {

	private String game;

	private String description;

	private int progress;

	private int totalProgress;

	private LocalDateTime dateAchieved;
	private LocalDateTime dateTime;

	public Memento(String game, String description, int totalProgress, int progress, LocalDateTime dateAchieved)
	{
		this.game = game;
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

	public LocalDateTime getDateAchieved() {
		return dateAchieved;
	}

	public void setDateAchieved(LocalDateTime dateAchieved) {
		this.dateAchieved = dateAchieved;
	}

	public void getJson()
	{
		JsonWriter jsonWriter = new JsonWriter();
		jsonWriter.writeMementoToJson(this);
	}

	public void setJson() {

	}

	public void updateProgress(int current)
	{
		progress = current;
	}

}
