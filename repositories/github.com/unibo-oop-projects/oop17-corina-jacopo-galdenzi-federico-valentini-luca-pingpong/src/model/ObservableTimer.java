package model;

public interface ObservableTimer{
	public void addObserver(TimerObserver observer);
	public void notifyObservers();
}
