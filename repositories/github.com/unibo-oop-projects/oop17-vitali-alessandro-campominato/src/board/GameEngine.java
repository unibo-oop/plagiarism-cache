package board;
import enumeration.Action;
import enumeration.GameState;
import interfaces.GameEngineInterface;

/**
 * classe che gestice il gioco lavorando sulle celle
 * 
 * @author Alessandro
 *
 */
public class GameEngine implements GameEngineInterface{

    private Board board;
    
    private Cell[][] cells;
    
    private int totCell;
    
    public GameState gameState = GameState.STOP;
    
    /**
     * costruttore
     */
    public GameEngine() {}
    
    /**
     * viene creata la Board e stampata
     * 
     * @param rows
     *    righe della board
     * @param columns
     *    colonne della board
     * @param bombs
     *    bombe della board
     */
    public void newGame(int rows, int columns, int bombs) {
    	this.board = new Board(rows, columns, bombs);
    	this.totCell = new Integer(rows*columns-bombs);
    	this.cells = board.getCells();
    	this.gameState = GameState.ONGOING;
    	
    	this.printBoard(rows, columns);
    }
    
    /**
     * stampa della board
     * 
     * @param rows
     *    righe della board
     * @param columns
     *    colonne della board
     */
    private void printBoard(int rows, int columns) {
    	for(int x = 0; x < rows; x++) {
    		for(int y = 0; y < columns; y++) {
    			if(this.cells[x][y].isBomb()) {
    				System.out.print("| X |");
    			} else {
    				System.out.print("| " + this.cells[x][y].getNumberNeighborBombs() + " |");
    			}
    			if(y+1 == columns) {
    				System.out.print("\n");
    			}
    		}
    	}
    }
    
    /**
     * gestione del click su una cella, in base all'Action ricevuto dal GameView viene eseguita un azione
     * viene poi impostato lo stato della partita per permettere alla view di gestire i casi tramite GameState
     * 
     * @param cell
     *    cella su cui è stato fatto il click
     * @param action
     *    azione da applicare alla cella
     */
    public void click(Cell cell, Action action) {
    	switch(action) {
    	case PLAY:
    		if(cell.isBomb()) {
    			this.gameState = GameState.LOST;
    		} else {
        		this.gameState = GameState.ONGOING;
    			if(cell.getNumberNeighborBombs() == 0) {
        			this.revealeNeighbors(cell);
        			cell.setRevealed();
        		} else {
        			cell.setRevealed();
        		}
    			int totRevealed = 0;
    			for(int x = 0; x < this.cells.length; x++) {
    				for(int y = 0; y < this.cells[x].length; y++) {
    					if(this.cells[x][y].isRevealed()) {
    						totRevealed++;
    						if(totRevealed == this.totCell) {
    							this.gameState = GameState.WON;
    						}
    					}
    				}
    			}
    		}
    	break;
    	case SET_FLAG :
    		cell.addFlag();
    	break;
    	case REMOVE_FLAG :
    		cell.removeFlag();
    	break;
		default:
		break;
    	
    	}
    }

    /**
     * funzione ricorsiva che rivela tutte le celle confinanti con 0 bombe vicine, fino a trovare celle con bombe vicine
     * 
     * @param cell
     *    cella da rivelare
     */
    private void revealeNeighbors(Cell cell) {
    	Cell[] neighbors = cell.getNeighbors();
    	for(int i = 0; i < cell.numberOfNeighbors(); i++) {
    		if(!neighbors[i].isBomb()) {
    			if(!neighbors[i].isRevealed()) {
    				if(!neighbors[i].isFlag()) {
    					neighbors[i].setRevealed();
    					if(neighbors[i].getNumberNeighborBombs() == 0) {
    						this.revealeNeighbors(neighbors[i]);
    					}
    				}
    			}
    		}
    	}
    }
    
    /**
     * 
     * @return le celle della board
     */
    public Cell[][] getCells() {
    	return this.cells;
    } 
}
