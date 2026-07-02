package model.pawns;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PawnsImpl extends Observable implements Pawns{

	private static final int START=0; 
	private int positions;
	private boolean state;

	public PawnsImpl() {
		
		this.positions=START;
		this.state=false;
	}
	
	@Override
    public void setState(boolean state) {
      
		this.state = state;
        setChanged();
        notifyObservers();
    }
 
    @Override
    public boolean getState() {
       
    	return this.state;
    }
	
	@Override
	public int getPosition() {
		
		return this.positions;
	}

	@Override
	public void setPosition(int pos) {   // observer
 		
		this.positions=pos;
	}

	@Override
	public void addObserverList(List<Observer> obsList) {
		
		if (!obsList.isEmpty()){
			obsList.forEach(o->this.addObserver(o));
		}
	}

	
}
