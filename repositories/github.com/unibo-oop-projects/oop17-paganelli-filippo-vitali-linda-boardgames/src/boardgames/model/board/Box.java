package boardgames.model.board;


import java.util.Optional;
import boardgames.model.piece.Piece;
import boardgames.utility.Pair;

public class Box extends Pair<Integer, Integer> {

    private Optional<Piece> pieceInBox = Optional.empty();
    public Box(final Integer x,final Integer y,final Optional<Piece> currentPiece) {
        super(x, y);
        this.pieceInBox = currentPiece;
    }
    
    /*
     * ritorna true se il box è vuoto da un pezzo altrimenti false
     */
    public boolean isEmpty() {
        return !this.pieceInBox.isPresent();
    }

    /*
     * imposta il pezzo passato nel box
     * 
     */
    public void setPiece(final Optional<Piece> currentPiece) {
        this.pieceInBox = currentPiece;
    }

    public Pair<Integer, Integer> getPair() {
        return new Pair<>(this.getX(), this.getY());
    }
    
    /*
     * ritorna il pezzo che occupa il box
     * 
     */
    public Optional <Piece> getPiece() {
        return this.pieceInBox;
    }

    public String toString() {
        if (this.isEmpty()) {
            return super.toString();
        } else {
            return super.toString() + " " + this.getPiece().get().getName();
        }
    }

}
