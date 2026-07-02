package justanotherchessgame.ai;

import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.MovesChecker;
import justanotherchessgame.model.ChessboardModel;
import justanotherchessgame.model.Piece;
import justanotherchessgame.model.Queen;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//TODO: Implement AI interface

//This AI just returns a random move out of the legal ones

public class DumbAI {
    public static MoveInfoImpl getMove(final ChessboardModel cb, final boolean color) {
        List<Piece> pieces = cb.getPieceOnBoard().stream().filter(x -> x.isWhite() == color).collect(Collectors.toList());
        Collections.shuffle(pieces);
        for (Piece p : pieces) {
            List<MoveInfoImpl> lm = MovesChecker.possibleMoves(cb, p.getPoint());
            if (!lm.isEmpty()) {
                MoveInfoImpl chosen = lm.get(new Random().nextInt(lm.size()));
                if (MovesChecker.willPromote(p, chosen.getTo())) {
                    chosen.setPromotion(Queen.class);
                }
                return chosen;
            }
        }
        //If no moves were found, returns a null reference
        return null;
    }
}
