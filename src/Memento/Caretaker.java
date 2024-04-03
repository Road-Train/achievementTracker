package Memento;

import Memento.Achievement;

import java.util.ArrayList;

public class Caretaker
{
	
	private final Achievement achievement;
	
	private ArrayList<Achievement.Memento> history;
	
	public void undo(Achievement.Memento memento)
	{
		achievement.restore(memento);
	}
	
	public Caretaker(Achievement achievement)
	{
		this.achievement = achievement;
	}
	
	public void addMemento(Achievement.Memento memento)
	{
		history.add(memento);
	}
	
	public void removeMemento(Achievement.Memento memento)
	{
		history.remove(memento);
	}
}
