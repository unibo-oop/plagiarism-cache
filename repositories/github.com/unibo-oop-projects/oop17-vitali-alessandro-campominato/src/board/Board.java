package board;
import java.util.ArrayList;
import java.util.Collections;

import interfaces.BoardInterface;

/**
 * Creazione della tabella di gioco
 * 
 * @author Alessandro
 *
 */
public class Board implements BoardInterface{
	
	private Cell[][] cells;
	private int nCells;
	
	private int rows;
	private int columns;
	private int bombs;

	/**
	 * Creazione arrayList per inserire le celle e mescolarle tramite il metodo shuffle.
	 * Inserimento dentro un array di Celle bidimensionale
	 * Infine viene fatta la chiamata alla funzione che imposta le celle vicine
	 * 
	 * @param rows
	 *   righe della tabella
	 * @param columns
	 *   colonne della tabella
	 * @param nBombs
	 *   numbero bombe
	 */
	public Board(int rows, int columns, int nBombs) {
		this.rows = rows;
		this.columns = columns;
		this.bombs = nBombs;
		this.nCells = rows * columns;
		ArrayList<Cell> cellList = createCellList(this.nCells, nBombs);
		Collections.shuffle(cellList);
		this.cells = new Cell[rows][columns];
		for(int i = 0; i < this.cells.length; i++) {
			for(int j = 0; j < this.cells[i].length; j++) {
				this.cells[i][j] = cellList.remove(0);
				this.cells[i][j].setX(i);
				this.cells[i][j].setY(j);
			}
		}
		this.setCellNeighbors();
	}
	
	/**
	 * Inserimento delle celle passandogli il valore true se sono bombe oppure false
	 * 
	 * @param nCells 
	 *   numero di celle totali
	 * @param nBombs
	 *   numbero bombe
	 * @return
	 */
	private static ArrayList<Cell> createCellList(int nCells, int nBombs) {
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		for(int i = 0; i < nCells - nBombs; i++) {
			cellList.add(new Cell(false));
		}
		for(int i = 0; i < nBombs; i++) {
			cellList.add(new Cell(true));
		}
		return cellList;
	}
	
	/*
	 * per ogni cella viene creato un array di celle confinanti ad essa
	 */
	
	/**
	 * Funzione che scorre tutte le celle e per ogni cella imposta quelle vicine dentro un arrayList
	 * Poi vengono inserite dentro un array
	 */
	private void setCellNeighbors() {
		for(int i = 0; i < this.cells.length; i++) {
			for(int j = 0; j < this.cells[i].length; j++) {
				ArrayList<Cell> neighborsList = new ArrayList<Cell>();
				for(int relativeI = i - 1; relativeI < i + 2; relativeI++){
					for(int relativeJ = j - 1; relativeJ < j + 2; relativeJ++) {
						if(relativeI >= 0 && relativeI < this.cells.length && relativeJ >= 0 && relativeJ < this.cells[i].length && 
						    this.cells[i][j] != this.cells[relativeI][relativeJ]) {
									neighborsList.add(this.cells[relativeI][relativeJ]);
						}
					}
				}
				Cell[] neighborsArray = new Cell[neighborsList.size()];
				neighborsList.toArray(neighborsArray);
				this.cells[i][j].setNeighbors(neighborsArray);
			}
		}
	}
	
	/**
	 * restituisce il numero di righe
	 */
	public int getRows() {
		return this.rows;
	}
	
	/**
	 * restituisce il numero di colonne
	 */
	public int getColumns() {
		return this.columns;
	}

	/**
	 * restituisce il numero di bombe
	 */
	public int getBombs() {
		return this.bombs;
	}

	/**
	 * restituisce le l'array di celle creato
	 */
	public Cell[][] getCells() {
		return this.cells;
	}
	

	
}
