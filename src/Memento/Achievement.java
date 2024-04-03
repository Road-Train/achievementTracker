package Memento;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Achievement
{
	
	private String game;
	private String title;
	private String description;
	private int progress = 0;
	private int totalProgress;
	private LocalDateTime dateAchieved;
	private Caretaker caretaker;
	
	public Achievement(Memento memento)
	{
		restore(memento);
	}
	
	public Achievement(String game, String title, String description, int totalProgress)
	{
		this.game = game;
		this.title = title;
		this.description = description;
		this.totalProgress = totalProgress;
		this.dateAchieved = LocalDateTime.now();
	}
	
	public void save()
	{
		caretaker.addMemento(new Memento(this.game, this.title, this.description, this.totalProgress, this.progress, this.dateAchieved));
	}
	
	public String getGame()
	{
		return this.game;
	}
	
	public void setGame(String game)
	{
		this.game = game;
	}
	
	public void restore(int index)
	{
		Memento memento = caretaker.undo(index);
	}
	private void restore(Memento memento)
	{
		this.game = memento.game;
		this.title = memento.title;
		this.description = memento.description;
		this.progress = memento.progress;
		this.totalProgress = memento.totalProgress;
		this.dateAchieved = memento.dateTime;
	}
	
	public String getSimpleInfo()
	{
		return STR."\{title} (\{game})";
	}
	
	public String getinfo()
	{
		return STR."Title: \{title}\nGame: \{game}Description: \{description}Progress: \{progress}/\{totalProgress}";
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
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	class Memento implements Serializable
	{
		
		private final String game;
		private final String title;
		private final String description;
		private final int progress;
		private final int totalProgress;
		private final LocalDateTime dateAchieved;
		private final LocalDateTime dateTime;
		
		private Memento(String game, String title, String description, int totalProgress, int progress, LocalDateTime dateAchieved)
		{
			this.game = game;
			this.title = title;
			this.description = description;
			this.progress = progress;
			this.totalProgress = totalProgress;
			this.dateTime = LocalDateTime.now();
			this.dateAchieved = dateAchieved;
		}
		
		public String getFilePath()
		{
			return STR."\{game}\{dateTime.toString().replace(":", "-")}";
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
}
