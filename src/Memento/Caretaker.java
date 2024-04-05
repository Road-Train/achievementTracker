package Memento;

import java.util.LinkedList;

public class Caretaker
{
	
	private final LinkedList<Achievement.Memento> history;
	
	
	public Caretaker()
	{
		this.history = new LinkedList<>();
	}
	
	Achievement.Memento getMementoAtIndex(int index)
	{
		return history.get(index);
	}
	
	void addMemento(Achievement.Memento memento)
	{
		history.add(memento);
	}
	
	LinkedList<Achievement.Memento> fetchHistory()
	{
		return history;
	}
	
	void removeMemento(Achievement.Memento memento)
	{
		history.remove(memento);
	}
}
