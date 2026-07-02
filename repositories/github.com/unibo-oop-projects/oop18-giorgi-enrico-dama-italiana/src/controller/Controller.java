package controller;

import view.CheckerBoard;
import view.DamaBox;
import view.Pair;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;

import org.omg.CORBA.ExceptionList;

import model.CheckerBoardShadow;
import model.CheckerBoardShadow.piecesType;



public class Controller {
	public static int DIMENSION=8;
	//private 	List<Pair<Integer,Integer>> p;
	private int blackFlagRight = 0;
	private int blackFlagLeft=0;
	private int whiteFlag=0;
	private CheckerBoard checkerBoard;
	private CheckerBoardShadow checkerBoardShadow;
	public void startGame() {
		this.checkerBoard = new CheckerBoard(8,8, this);//this è il controller
		this.checkerBoardShadow = new CheckerBoardShadow();
		updateCheckerBoard();

	}
	/**
	 * 
	 * @param ev event, the current piece clicked on the view with Coordinates
	 */
	public void click(Pair<Integer,Integer> ev){

		//aggiorniamo il modello con la logica di business
		//...
		int x=ev.getX();
		int y=ev.getY();

		piecesType t = this.checkerBoardShadow.getType(ev.getX(), ev.getY());

		if (t!=piecesType.E )
		{
			
				this.checkerBoardShadow.selectItem(x, y, this.checkerBoardShadow.RedPathCoordinates(this.availableRedChoices(t, ev)));
			
				// TODO Auto-generated catch block
				
			

		}else {

			if (this.checkerBoardShadow.isSelected())//logica secondo click
			{
				this.checkerBoardShadow.movePiece(x, y );
				//this.checkerBoardShadow.eatPiece(x,y);
				this.checkerBoardShadow.unselectItem();//unselect
				this.checkerBoard.resetRedBorder(); //tolgo le due scelte rosse vecchie

			}else {
				System.err.println("seleziona un pezzo");
			}




		}

		updateCheckerBoard();//aggiorniamo la vista


	}



	//turniiii
	/**
	 * 
	 * @param t enum, specifies the pieces if BlackDama, WhitePiece, Empty etc..
	 * @param ev Coordinates of pieces t
	 * @return the Red coordinates of the path chosen to move the piece 
	 */
	public List<Pair<Integer,Integer>> availableRedChoices(piecesType t, Pair<Integer,Integer> ev)  {
		int flagLeftBlack=-1;
		int flagRightBlack=-1;
		int flagLeftWhite=-1;
		int flagRightWhite=-1;
		this.checkerBoard.resetRedBorder(); 
		int x=ev.getX();
		int y=ev.getY();
		List<Pair<Integer,Integer>> p= new ArrayList<>();
	/*	if((ev.getX()==1 && this.checkerBoardShadow.getType(x, y)==piecesType.WP)||(ev.getX()==6 && this.checkerBoardShadow.getType(x, y)==piecesType.BP) ) 
		{
			this.DamaRoutine( t, ev);
		}*/
		if(t==piecesType.BP) {
		
			//sinistra
			if((ev.getY()!=0 && ev.getY()!=1 && ev.getX()!=6 && ev.getX()!=7) && (this.checkerBoardShadow.getType(x+1, y-1)==piecesType.WP || this.checkerBoardShadow.getType(x+1, y-1)==piecesType.WD))
			{
				if(this.checkerBoardShadow.getType(x+2, y-2)==piecesType.E)
				{
					this.checkerBoard.setBorder(ev.getX()+2, ev.getY()-2);
					//this.checkerBoard.SetPieces(ev.getX()+1, ev.getY()-1, piecesType.E);
					this.checkerBoardShadow.eatPiece(x+1,y-1);
					flagLeftBlack++;
					p.add(new Pair<>(x+2,y-2));
				}
			}else if((ev.getY()!=7 && ev.getY()!=6 && ev.getX()!=6 && ev.getX()!=7) && (this.checkerBoardShadow.getType(x+1, y+1)==piecesType.WP || this.checkerBoardShadow.getType(x+1, y+1)==piecesType.WD))
			{
				if(this.checkerBoardShadow.getType(x+2, y+2)==piecesType.E) 
				{
					this.checkerBoard.setBorder(ev.getX()+2, ev.getY()+2);
					//this.checkerBoard.SetPieces(ev.getX()+1, ev.getY()+1, piecesType.E);
					this.checkerBoardShadow.eatPiece(x+1,y+1);
					p.add(new Pair<>(x+2,y+2));
					flagRightBlack++;
					//System.out.println("ciaooooooo destra:"+ x+1, y+1);
				}                //modifica
			}else if(ev.getY()!=0 && ev.getX()!=7 && flagRightBlack==-1) 
			{ //left
				if (this.checkerBoardShadow.getType(x+1, y-1)==piecesType.E) 
				{
					
					this.checkerBoard.setBorder(ev.getX()+1, ev.getY()-1);
					p.add(new Pair<>(x+1,y-1)); //dava un errore strano se non sovrascrivo
				}
			}//destra
			 if(ev.getY()!=7  && ev.getX()!=7 && flagLeftBlack==-1) 
			{ //right
				if(this.checkerBoardShadow.getType(x+1, y+1)==piecesType.E ) 
				{ //
					this.checkerBoard.setBorder(ev.getX()+1, ev.getY()+1);
					p.add(new Pair<>(x+1,y+1));
				}
			}
			//////////////////////////////////////////////////////////////////pezzo BIANCOOOOOOOOOOOOOOO
		} 	
		else if(t==piecesType.WP) 
		{
			if((ev.getY()!=7 && ev.getY()!=6 && ev.getX()!=0  && ev.getX()!=1) && (this.checkerBoardShadow.getType(x-1, y+1)==piecesType.BP || this.checkerBoardShadow.getType(x-1, y+1)==piecesType.BD))
			{
				if(this.checkerBoardShadow.getType(x-2, y+2)==piecesType.E)//right
				{
					this.checkerBoard.setBorder(ev.getX()-2, ev.getY()+2);
					//this.checkerBoard.SetPieces(ev.getX()+1, ev.getY()-1, piecesType.E);
					this.checkerBoardShadow.eatPiece(x-1,y+1);
					p.add(new Pair<>(x-2,y+2));
					flagRightWhite++;
				}
			}	else if((ev.getY()!=0 && ev.getY()!=1 && ev.getX()!=0  && ev.getX()!=1) && (this.checkerBoardShadow.getType(x-1, y-1)==piecesType.BP || this.checkerBoardShadow.getType(x-1, y-1)==piecesType.BD))
			{
				if(this.checkerBoardShadow.getType(x-2, y-2)==piecesType.E) //left
				{
					this.checkerBoard.setBorder(ev.getX()-2, ev.getY()-2);
					//this.checkerBoard.SetPieces(ev.getX()+1, ev.getY()+1, piecesType.E);
					this.checkerBoardShadow.eatPiece(x-1,y-1);
					p.add(new Pair<>(x-2,y-2));
					flagLeftWhite++;

					//System.out.println("ciaooooooo destra:"+ x+1, y+1);
				}
			}else if(ev.getY()!=0 && ev.getX()!=0  && flagRightWhite==-1)
			{ //left
				if (this.checkerBoardShadow.getType(x-1, y-1)==piecesType.E) 
				{
					this.checkerBoard.setBorder(ev.getX()-1, ev.getY()-1);
					p.add(new Pair<>(x-1,y-1)); //dava un errore strano se non sovrascrivo
					
				}
			}
			if(ev.getY()!=7 && ev.getX()!=0  && flagLeftWhite==-1) 
			{  //right
				if(this.checkerBoardShadow.getType(x-1, y+1)==piecesType.E)
				{
					this.checkerBoard.setBorder(ev.getX()-1, ev.getY()+1);
					p.add(new Pair<>(x-1,y+1));
					
				}
			}
		}
	
	
		
		for(int i=0;i<p.size();i++) {
			System.out.println("Controller: "+p.get(i).toString());
		}
		return p;

	}
	private void DamaRoutine(piecesType t, Pair<Integer, Integer> ev) {
		// TODO Auto-generated method stub
		this.checkerBoard.resetRedBorder(); 
		int x=ev.getX();
		int y=ev.getY();
		List<Pair<Integer,Integer>> p= new ArrayList<>();
		if(t==piecesType.BP) {
			
			if (this.checkerBoardShadow.getType(x+1, y-1)==piecesType.E) 
			{
				this.checkerBoard.setBorder(ev.getX()+1, ev.getY()-1);
				p.add(new Pair<>(x+1,y-1)); //dava un errore strano se non sovrascrivo
				this.checkerBoard.SetPieces(x+1, y+1, piecesType.BD);
			}
		}
		
	}
	/**
	 * Update the checkerBoard with pieces taken from checkerBoardShadow
	 */
	private void updateCheckerBoard() {
		for (int r=0; r<DIMENSION; r++){
			for (int c=0; c<DIMENSION; c++){

				this.checkerBoard.SetPieces(r, c, this.checkerBoardShadow.getType(r, c));

			}
		}
	}
}
