package model;

import java.io.Serializable;

public class CellImpl implements Cell, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int position; //is the number of the cell.
	private Standing type; //is the type of the cell, it could be : AT_HOME, PLAYING, ARRIVED.
	
	public CellImpl(){ //Default costructor.
		this.position = 0;
		this.type = Standing.AT_HOME;
	}
	
	public CellImpl(int position, Standing type){
		this.position = position;
		this.type = type;
	}

	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position, int nPlayers){
		this.position = position;
		if(nPlayers == 4){
			if(this.position > 40 && this.getType()!= Standing.ARRIVED){ 	//The gameboard 
				this.position -= 40; 
			}
		}
		else{
			if(this.position > 48 && this.getType()!= Standing.ARRIVED){ 	//The gameboard 
				this.position -= 48; 
			}
		}
	}

	public Standing getType() {
		return type;
	}
	
	public void setType(Standing type){
		this.type = type;
	}
	
	public String toString(){
		String s = "Cell position: " + this.position + ", type: " + this.type + ".";
		return s;
	}
}
