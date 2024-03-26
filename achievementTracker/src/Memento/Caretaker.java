package Memento;

import other.Achievement;

import java.util.ArrayList;

public class Caretaker {

	private Achievement achievement;

	private ArrayList<Memento> history;

	public void undo(Memento memento)
	{
		achievement.restore(memento);
	}

	public Caretaker(Achievement achievement)
	{
		this.achievement = achievement;
	}

	public void addMemento(Memento memento )
	{
		history.add(memento);
	}

	public void removeMemento(Memento memento )
	{
		history.remove(memento);
	}
}
