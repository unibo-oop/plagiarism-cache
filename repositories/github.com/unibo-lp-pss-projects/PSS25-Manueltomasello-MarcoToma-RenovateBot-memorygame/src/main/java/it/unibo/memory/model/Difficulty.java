package it.unibo.memory.model;

//difficoltà
public enum Difficulty {
    //matrici di 4x4, 6x6 e 8x8 per le difficoltà facile, media e difficile
    EASY(4, 4),
    MEDIUM(6, 6),
    HARD(8, 8);

    private final int rows;
    private final int cols;
    // la difficoltà è data dal rapporto di righe e colonne
    Difficulty(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    //ritorna righe
    public int getRows() {
        return rows;
    }
    //ritorna colonne
    public int getCols() {
        return cols;
    }
    //facendo righe x colonne trovo le carte 
    public int totalCards() {
        return rows * cols;
    }
    // per le coppie invece il totale diviso 2
   public int totalPairs() {
        return totalCards() / 2;
    } 

}
