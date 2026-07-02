package model.model;

import model.data.Data;
import model.movements.Movements;
import model.movements.MovementsImpl;
import model.pawns.Pawns;

public class ModelImpl implements Model{

	private Movements move;
	
	@Override
	public synchronized int movePawn(Pawns p) {
		
		this.move.changePosition(p);
		p.setState(!p.getState());
		return p.getPosition();
	}

	@Override
	public void startGame(Data data) {
		
		this.move=new MovementsImpl(data);
	}

	@Override
	public synchronized boolean checkWin(Pawns p) {
		
		return this.move.checkWin(p.getPosition());
	}

}

