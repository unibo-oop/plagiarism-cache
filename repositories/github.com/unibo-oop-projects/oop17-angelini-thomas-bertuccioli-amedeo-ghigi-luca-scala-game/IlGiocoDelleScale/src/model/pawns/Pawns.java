package model.pawns;

import java.util.List;
import java.util.Observer;

public interface Pawns{

	public int getPosition();
	
	public void setPosition(int pos);
	
	public void setState(boolean state);
	
	public boolean getState();

	public void addObserverList(List<Observer> obsList);
}
