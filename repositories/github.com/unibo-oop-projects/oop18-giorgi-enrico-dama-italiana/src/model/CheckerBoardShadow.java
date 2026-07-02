package model;
import javax.swing.*;
import javax.swing.border.LineBorder;
import org.junit.runners.Parameterized.Parameter;



import java.awt.*;
import java.util.List;
import java.util.*;

import view.DamaBox;
import view.Pair;

public class CheckerBoardShadow {
	
	/**
	 * 
	 * @piecesType enum WP=PedinaBianca  BP=PedinaNera WD=DamaBianca BD=DamaNera
	 * 
	 *
	 */
	public enum playerName {
		playerWhite , playerBlack
	}
	public enum piecesType {
		WP,BP,WD,BD,E;

		
	}
	public static int DIMENSION=8;
	private List<List<piecesType>> checkerBoard= new ArrayList<>();
	private int counterWhite=12;
	private int counterBlack=12;
    private int player=0;
    private int turn=0;
    private int eatPieceX=-1;
    private int eatPieceY=-1;
	private Pair<Integer, Integer> selectedItem;
	private List<Pair<Integer,Integer>> availableRedPath;
	
	public CheckerBoardShadow() {

		this.checkerBoard= new ArrayList<>();

		for (int r=0; r<DIMENSION; r++){
			this.checkerBoard.add(new ArrayList<>());
			for (int c=0; c<DIMENSION; c++){

				this.checkerBoard.get(r).add(piecesType.E);	
                final DamaBox jb = new DamaBox();
				

				if((r%2==0 && c%2==0)||(r%2==1 && c%2==1)) {

					if (r<3) {
						this.checkerBoard.get(r).set(c,piecesType.BP);
						

					}
					if(r>4 && r<8) {

						this.checkerBoard.get(r).set(c,piecesType.WP);
					}

				}


			}
		}
	
		for (int r=0; r<DIMENSION; r++){	System.out.println(this.checkerBoard.get(r));	}
	}
//stabilisce il turno del giocatore
	public int getPlayerTurn() {
		this.turn=this.turn+this.player;
		this.player++;
		return turn%2==0 ? 0:1;
		
	}
	
	public boolean Winner() {
		return false;
		
		
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public piecesType getType (int x, int y)
	{
		return this.checkerBoard.get(x).get(y);
	}
	
	public piecesType setType (int x, int y, piecesType pt)
	{
		return this.checkerBoard.get(x).set(y,pt);
	}
	
	public void selectItem(int x,int y, List<Pair<Integer,Integer>> availableRedpath )
	{
		this.selectedItem=new Pair<Integer, Integer>(x, y);
		this.availableRedPath=availableRedpath;
	}
	
	public boolean isSelected()
	{
		return this.selectedItem!=null;
		
	}
	
	public void unselectItem() {
		this.selectedItem=null;
		this.availableRedPath.clear();
		this.eatPieceX=-1;
		this.eatPieceY=-1;
	}
	public List<Pair<Integer, Integer>> RedPathCoordinates(List<Pair<Integer,Integer>> p) {
		
	
		//this.availableRedPath.addAll(p);
		this.availableRedPath = new ArrayList<>(p);
		for(int i=0;i<this.availableRedPath.size();i++) {
			System.out.println("checkerBoardShadow: "+this.availableRedPath.get(i).toString());
			}
		return this.availableRedPath;
		
	}
	public void movePiece(int x, int y ) {
		piecesType t = this.checkerBoard.get(this.selectedItem.getX()).get(this.selectedItem.getY());
	
		for(int i=0; i<this.availableRedPath.size();i++) {
			if((x==this.availableRedPath.get(i).getX() && y==this.availableRedPath.get(i).getY())){
				this.checkerBoard.get(x).set(y,t);
			
				
                this.checkerBoard.get(this.selectedItem.getX()).set(this.selectedItem.getY(),piecesType.E);//azzera il pezzo da spostare
                    if(this.eatPieceX != -1 && this.eatPieceY != -1) {
                	this.checkerBoard.get(this.eatPieceX).set(this.eatPieceY,piecesType.E);
                }
              
			}
		}
		
	}
	public void eatPiece(int x, int y) {
		// TODO Auto-generated method stub
		this.eatPieceX=x;
		this.eatPieceY=y;
		System.out.println("X pezzo bianco da mangiare:"+this.eatPieceX);
		System.out.println("Y pezzo bianco da mangiare:"+this.eatPieceY);
	}

	
	
	

}
