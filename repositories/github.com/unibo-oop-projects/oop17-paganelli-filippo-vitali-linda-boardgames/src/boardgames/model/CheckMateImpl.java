package boardgames.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.piece.Piece;
import boardgames.utility.Colour;
import boardgames.view.game.GameView;
import boardgames.view.game.GameViewImpl;

public class CheckMateImpl implements CheckMate {

    private final Board board;
    private final ChessGame cg;
    private final Piece king;
    private final Player player;
    private final Player opponents;
    private List<GameView> observers;
    private List<Box> liberty;
    
    public CheckMateImpl(final Game cg, final Piece king, final Player player) {
        this.cg = (ChessGame) cg;
        this.board = cg.getBoard();
        this.king = king;
        this.player = player;
        this.opponents = this.getOpponents(player);
        this.observers = new LinkedList<>();
        this.liberty = new LinkedList<>();
    }

    public List<Box> getMustMovements() {
        return this.liberty;
    }

    public Piece getKing() {
        return this.king;
    }

    public boolean isCheckMate(final Piece lastPieceMoved) {
        final List<Box> allPossibleMoves = new LinkedList<>();    
        final List<Box> kingPM = this.king.possibleMoves(board);
        Piece temp;
        kingPM.forEach(a -> System.out.println(a.toString()));
        // inserisco in allMovesPossible tutte le mosse possibili dei pezzi dell'avversario
        this.opponents.getPlayerPieces().forEach(a -> allPossibleMoves.addAll(a.possibleMoves(board)));
        
        //controllo se il pezzo appena mosso mette il re sotto scacco
        if (lastPieceMoved.possibleMoves(board).contains(this.king.getBox())) {
            // se tutte le mosse del re dove non mangia (isEmpty) sono contenute in allPossibleMoves significa che
            // il re è in scacco matto           
            for (Box c : kingPM) {
                if (!c.isEmpty()) {
                    // memorizzo il pezzo che il re può mangiare
                    temp = c.getPiece().get();
                    // simulo che il re lo abbia mangiato
                    this.board.getBox(c.getX(), c.getY()).setPiece(Optional.empty());
                    allPossibleMoves.clear();
                    // ricalcolo le possibili mosse
                    this.opponents.getPlayerPieces().forEach(a -> allPossibleMoves.addAll(a.possibleMoves(board)));
                    // se il box in cui il re ha mangiato non è contenuto nella lista allora significa che può muoversi 
                    // li per uscire dallo scacco
                    if (!allPossibleMoves.contains(c)) {
                        this.liberty.add(c);
                    }
                    allPossibleMoves.clear();
                    // rimetto a posto il pezzo temporaneamente mangiato
                    this.board.getBox(c.getX(), c.getY()).setPiece(Optional.of(temp));
                    // ricalcolo le mosse possibili
                    this.opponents.getPlayerPieces().forEach(a -> allPossibleMoves.addAll(a.possibleMoves(board)));
                } else {
                    if (!allPossibleMoves.contains(c)) {
                        this.liberty.add(c);
                    }
                }
            }
            
            // se liberty è vuota significa che scacco matto
            if (liberty.isEmpty()) {
                this.notifyCheckMate();
                this.liberty.forEach(a -> System.out.println(a.toString()));
                System.out.println("ciaone");
                return true;
            } else {
                this.notifyCheck();
                System.out.println("ciao");
                this.liberty.forEach(a -> System.out.println(a.toString()));
                return false;
            }     
        }
        return false;
    }

    private Player getOpponents(final Player player) {
        if (player.getColour() == Colour.White) {
            return cg.getPlayer(Colour.Black);
        } else {
            return cg.getPlayer(Colour.White);
        }
    }

    @Override
    public void attach(final GameViewImpl gv) {
        this.observers.add(gv);
    }

    @Override
    public void notifyCheckMate() {
       this.observers.forEach(a -> a.updateCheckMateStatus());
    }

    @Override
    public void notifyCheck() {
        this.observers.forEach(a -> a.updateCheckStatus());
    }
}
