package justanotherchessgame.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.MovesChecker;
import justanotherchessgame.model.Piece;
import justanotherchessgame.model.Pawn;
import justanotherchessgame.model.Bishop;
import justanotherchessgame.model.ChessboardModel;
import justanotherchessgame.model.Knight;
import justanotherchessgame.model.Rook;
import justanotherchessgame.util.Point;
import justanotherchessgame.model.Queen;
import justanotherchessgame.model.King;

public class MinMaxAI {
    public static int cont = 0;
    public static MoveInfoImpl getMove(final ChessboardModel cb, final boolean color) {
        MinMaxSol best = bestMoveNMab(cb, color, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        System.out.println(best.getScore() + " - " + best.getMove() + " cont: " + cont);
        cont = 0;
        return best.getMove();
    }
    private static class MinMaxSol {
        private double score;
        private MoveInfoImpl move;
        MinMaxSol(final double score, final MoveInfoImpl move) {
            this.score = score;
            this.move = move;
        }
        public double getScore() {
            return score;
        }
        public MoveInfoImpl getMove() {
            return move;
        }
        public void setScore(final double d) {
            score = d;
        }
    }

    //Basic negamax with fixed depth implementation
    private static MinMaxSol bestMoveNMab(final ChessboardModel cb, final boolean color, final int depth, final double alpha, final double beta) {
        cont++;
        // If depth 0, we simply return current board's score
        if (depth == 0) {
            //TODO: avoid returning null
            return new MinMaxSol(evaluate(cb, color), null);
        }
        // We recur deeper
        MinMaxSol best = new MinMaxSol(Double.NEGATIVE_INFINITY, null);
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        List<MinMaxSol> choices = new ArrayList<MinMaxSol>();
        for (Piece p : cb.getPieceOnBoard()) {
            // We skip unwanted pieces
            if (p.isWhite() != color) {
                continue;
            }
            List<MoveInfoImpl> tmp = MovesChecker.possibleMoves(cb, p.getPoint());
            moves.addAll(tmp);
        }
        
        for (MoveInfoImpl m : moves) {
            Point from = m.getFrom();
            Point to = m.getTo();
            Piece p = cb.getSquare(m.getFrom());
            Piece hold = cb.getSquare(m.getTo());
            // If move is a promotion
            if (MovesChecker.willPromote(p, m.getTo())) {
                m.setPromotion(Queen.class);
            }
            // Store previous move
            MoveInfoImpl previousMove = cb.getLast();
            // Move piece
            Piece eaten = cb.simulateMove(m);
            // NegaMax alpha-beta pruning recursion
            // Upper bound is current lower bound and vice-versa
            MinMaxSol tmp = bestMoveNMab(cb, !color, depth - 1, -beta, -alpha);
            tmp.setScore(-tmp.getScore());
            // Undo move
            cb.undoMove(m, eaten, previousMove);
            // If move is better 
            if (best == null || tmp.getScore() > best.getScore()) {
                best = tmp;
                choices.clear();
            }
            if (tmp.getScore() == best.getScore()) {
                //We add some salt by considering equal moves randomly
                choices.add(new MinMaxSol(best.getScore(), m));
            }

            // Update alpha
            final double val = Math.max(alpha, tmp.getScore());

            // Alpha-beta check
            if (val >= beta) {
                // We found a move that's better than the best one in another
                break;
            }
        }
        // If no moves are available, current player has lost / draw
        if (choices.isEmpty()) {
            //Check if current player is being checkmated
            if (MovesChecker.kingCheck(cb, cb.getKing(color).getPoint(), color)) {
                return best;
            }
            best.setScore(0);
            return best;
        }
        return choices.get(0);
        // return choices.get(new Random().nextInt(choices.size()));
    }
    // Basic evaluation, only piece values
    private static double evaluate(final ChessboardModel cb, final boolean color) {
        //TODO: valuate stalemate and checkmate separately
        double score = 0;
        for (Piece p : cb.getPieceOnBoard()) {
            if (p.isWhite() == color) {
                score += valueOf(p);
            } else {
                score -= valueOf(p);
            }
        }
        return score;
    }
    //TODO: move to another class ?
    private static double[][] kingPositions = {
            {2, 2, -1, -2, -3, -3, -3, -3},
            {3, 2, -2, -3, -4, -4, -4, -4},
            {1, 0, -2, -3, -4, -4, -4, -4},
            {0, 0, -2, -4, -5, -5, -5, -5},
            {0, 0, -2, -4, -5, -5, -5, -5},
            {1, 0, -2, -3, -4, -4, -4, -4},
            {3, 2, -2, -3, -4, -4, -4, -4},
            {2, 2, -1, -2, -3, -3, -3, -3}
    };

    private static double[][] queenPositions = {
            {-2, -1, -1, 0, -0.5, -1, -1, -2},
            {-1, 0, 0.5, 0, 0, 0, 0, -1},
            {-1, 0.5, 0.5, 0.5, 0.5, 0.5, 0, 1},
            {-0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5},
            {-0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5},
            {-1, 0, 0.5, 0.5, 0.5, 0.5, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, -1},
            {-2, -1, -1, -0.5, -0.5, -1, -1, -2}
    };

    private static double[][] rookPositions = {
            {0, -0.5, -0.5, -0.5, -0.5, -0.5, 0.5, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0.5, 0, 0, 0, 0, 0, 1, 0},
            {0.5, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, -0.5, -0.5, -0.5, -0.5, -0.5, 0.5, 0}
    };

    private static double[][] bishopPositions = {
            {-2, -1, -1, -1, -1, -1, -1, -2},
            {-1, 0.5, 1, 0, 0.5, 0, 0, -1},
            {-1, 0, 1, 1, 0.5, 0.5, 0, -1},
            {-1, 0, 1, 1, 1, 1, 0, -1},
            {-1, 0, 1, 1, 1, 1, 0, -1},
            {-1, 0, 1, 1, 0.5, 0.5, 0, -1},
            {-1, 0.5, 1, 0, 0.5, 0, 0, -1},
            {-2, -1, -1, -1, -1, -1, -1, -2}
    };

    private static double[][] knightPositions = {
            {-5, -4, -3, -3, -3, -3, -4, -5},
            {-4, -2, 0.5, 0, 0.5, 0, -2, -4},
            {-3, 0, 1, 1.5, 1.5, 1, 0, -3},
            {-3, 0.5, 1.5, 2, 2, 1.5, 0, -3},
            {-3, 0.5, 1.5, 2, 2, 1.5, 0, -3},
            {-3, 0, 1, 1.5, 1.5, 1, 0, -3},
            {-4, -2, 0.5, 0, 0.5, 0, -2, -4},
            {-5, -4, -3, -3, -3, -3, -4, -5}
    };

    private static double[][] pawnPositions = {
            {0, 0.5, 0.5, 0, 0.5, 1, 5, 0},
            {0, 1, -0.5, 0, 0.5, 1, 5, 0},
            {0, 1, -1, 0, 1, 2, 5, 0},
            {0, -2, 0, 2, 2.5, 3, 5, 0},
            {0, -2, 0, 2, 2.5, 3, 5, 0},
            {0, 1, -1, 0, 1, 2, 5, 0},
            {0, 1, -0.5, 0, 0.5, 1, 5, 0},
            {0, 0.5, 0.5, 0, 0.5, 1, 5, 0}
    };

    //TODO: make fancier?
    private static double valueOf(final Piece p) {
        int x = p.getX();
        int y = p.getY();
        // Flip y for black piece evaluation
        if (!p.isWhite()) {
            y = 7 - y;
        }
        if (p instanceof Pawn) {
            return 10 +  pawnPositions[x][y];
        } else if (p instanceof Bishop) {
            return 30 +  bishopPositions[x][y];
        } else if (p instanceof Knight) {
            return 30 +  knightPositions[x][y];
        } else if (p instanceof Rook) {
            return 50 +  rookPositions[x][y];
        } else if (p instanceof Queen) {
            return 100 +  queenPositions[x][y];
        } else if (p instanceof King) {
            return 1000 +  kingPositions[x][y];
        } else {
            return 0;
            //TODO: throw NotImplementedException();
        }
    }
}
