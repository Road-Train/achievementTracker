package Memento;

import Observer.Context;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Achievement
{
	
	private static final Caretaker caretaker = new Caretaker();
	private String game;
	private String title;
	private String description;
	private int progress = 0;
	private int totalProgress;
	private LocalDateTime dateAchieved = null;
	
	private Achievement(Memento memento)
	{
		restore(memento);
	}
	
	public Achievement(String game, String title, String description, int totalProgress)
	{
		this.game = game;
		this.title = title;
		this.description = description;
		this.totalProgress = totalProgress;
	}
	
	public static Achievement importAchievement()
	{
		return new Achievement(Memento.deserialize());
	}
	
	public String save()
	{
		caretaker.addMemento(new Memento(this.game, this.title, this.description, this.totalProgress, this.progress, this.dateAchieved));
		return "Achievement saved at: " + LocalDateTime.now();
	}
	
	public int getHistory()
	{
		LinkedList<Memento> history = caretaker.fetchHistory();
		IntStream.range(0, history.size()).mapToObj(i -> i + 1 + ": " + history.get(i).getState()).forEach(System.out::println);
		return history.size();
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
		Memento memento = caretaker.getMementoAtIndex(index);
		restore(memento);
	}
	
	private void restore(Memento memento)
	{
		this.game = memento.game;
		this.title = memento.title;
		this.description = memento.description;
		this.progress = memento.progress;
		this.totalProgress = memento.totalProgress;
		this.dateAchieved = memento.dateAchieved;
	}
	
	public String getSimpleInfo()
	{
		return title + " - " + game;
	}
	
	public String getinfo()
	{
		return "Title: " + title + "\nGame: " + game + "\nDescription: " + description + "\nProgress: " + progress + "/" + totalProgress;
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
	
	public Context setProgress(int progress)
	{
		
		int newProgress = 0;
		double percentProgress = (double) 100 / totalProgress * this.progress;
		
		if (progress == 0)
		{
			newProgress = this.progress;
		}
		else if (progress > this.progress)
		{
			newProgress = progress;
		}
		
		double percentProgressNew = (double) 100 / totalProgress * newProgress;
		this.progress = progress;
		if (percentProgressNew > percentProgress && percentProgressNew < totalProgress)
		{
			return Context.PROGRESS;
		}
		else if (percentProgressNew > percentProgress && percentProgressNew == totalProgress)
		{
			this.setDateAchieved(LocalDateTime.now());
			return Context.COMPLETED;
		}
		else
		{
			return Context.EDIT;
		}
	}
	
	public int getTotalProgress()
	{
		return this.totalProgress;
	}
	
	public boolean setTotalProgress(int totalProgress)
	{
		
		this.totalProgress = totalProgress;
		if (totalProgress < progress)
		{
			progress = totalProgress;
			System.out.println("New total Progress is lower than achieved progress: Progress set to total Progress.");
			return true;
		}
		return false;
	}
	
	public LocalDateTime getDateAchieved()
	{
		return dateAchieved;
	}
	
	public void setDateAchieved(LocalDateTime dateAchieved)
	{
		this.dateAchieved = dateAchieved;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void serialize()
	{
		caretaker.getMementoAtIndex(caretaker.fetchHistory().size() - 1).serialize();
	}
	
	static class Memento implements Serializable
	{
		
		private final String game;
		private final String title;
		private final String description;
		private final int progress;
		private final int totalProgress;
		private final LocalDateTime dateAchieved;
		private final LocalDateTime dateCreated;
		
		private Memento(String game, String title, String description, int totalProgress, int progress, LocalDateTime dateAchieved)
		{
			this.game = game;
			this.title = title;
			this.description = description;
			this.progress = progress;
			this.totalProgress = totalProgress;
			this.dateCreated = LocalDateTime.now();
			this.dateAchieved = dateAchieved;
		}
		
		private static Memento deserialize()
		{
			return JsonReader.readMementoFromJson();
		}
		
		String getState()
		{
			String output = dateCreated + " - " + description + " - " + progress + "/" + totalProgress;
			if (dateAchieved != null)
			{
				output += " - " + dateAchieved;
			}
			return output;
		}
		
		String getFilePath()
		{
			return game + dateCreated.toString().replace(":", "-");
		}
		
		private void serialize()
		{
			JsonWriter.writeMementoToJson(this);
		}
	}
}
