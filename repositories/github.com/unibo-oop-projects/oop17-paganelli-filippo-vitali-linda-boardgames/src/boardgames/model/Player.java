package boardgames.model;

import java.util.List;
import boardgames.model.piece.Piece;
import boardgames.utility.Colour;

public interface Player {

    /*
     * ritorna la lista dei pezzi del giocatore
     * 
     */
    public List<Piece> getPlayerPieces();

    /*
     * rimuove il pezzo indicato dai pezzi del giocatore
     * 
     */
    public void removePiece(final Piece p);

    public void addPiece(final Piece p);
    
    public Colour getColour();
}
