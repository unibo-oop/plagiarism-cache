package boardgames.model;

import java.util.List;
import boardgames.model.board.Box;
import boardgames.model.piece.Piece;
import boardgames.view.game.GameViewImpl;

public interface CheckMate {

    // ritorna true se il re è in scacco matto, quindi il giocatore 
    // avversario ha vinto. Altrimenti false
    public boolean isCheckMate(final Piece lastPieceMoved);
    
    // ritorna le mosse che il re può fare per liberarsi dallo scacco
    public List<Box> getMustMovements();
    
    // ritorna il re su cui si sta controllando la situazione di scacco
    public Piece getKing();
    
    public void attach(final GameViewImpl gv);
    
    public void notifyCheckMate();
    
    public void notifyCheck();
}
