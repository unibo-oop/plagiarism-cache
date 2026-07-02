package memento;

import java.util.ArrayList;
import java.util.List;

public class MementoPanelChange {
	private Originator originator= new Originator();
	private CareTaker careTaker = new CareTaker();
	public void savePanelName(String name) {
		originator.setState(name);
		careTaker.add(originator.saveStateToMemento());
	}
	public String getPreviousPanelName() {
		String ret = null;
		if(!careTaker.mementoList.isEmpty()) {
			ret = new String(careTaker.mementoList.get(0).getState());
			careTaker.mementoList.remove(0);
		}
		return ret;
	}
	private class Memento {
		   private String state;

		   public Memento(String state){
		      this.state = state;
		   }

		   public String getState(){
		      return state;
		   }	
		}
	private class Originator {
		   private String state;

		   public void setState(String state){
		      this.state = state;
		   }

		   public String getState(){
		      return state;
		   }

		   public Memento saveStateToMemento(){
		      return new Memento(state);
		   }

		   public void getStateFromMemento(Memento memento){
		      state = memento.getState();
		   }
		}
	

	private class CareTaker {
	   private List<Memento> mementoList = new ArrayList<Memento>();

	   public void add(Memento state){
	      mementoList.add(0,state);
	   }

	   public Memento get(int index){
	      return mementoList.get(index);
	   }
	}
}

