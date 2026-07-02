package justanotherchessgame.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import justanotherchessgame.util.Point;

/**
 * Class representing the Chessboard

    private void track(Piece p) {
        // If piece is not interesting
        if (toTrack(p) == false) {
            return;
        }
        //TODO: implement
    }
    private void untrack(Piece p) {
        // If piece is not interesting
        if (toTrack(p) == false) {
            return;
        }
        //TODO: positions[p.Type].remove(p.Point);
    }

    //Track non-null Kings only
    //TODO: track knights?
    private boolean toTrack(Piece p) {
        return p != null && p instanceof King;
    }

 */
public final class ChessboardModelImpl implements ChessboardModel {
    private static final int BOARD_SIZE = 8;
    private Piece[][] board;
    private King wking;
    private King bking;
    private MoveInfoImpl lastMove;

    //TODO: are other constructors appropriate?
    /**
     * Standard Constructor of the Chessboard.
     * @param standardBoard is a boolean which indicates if the board has to be empty.
     */
    public ChessboardModelImpl(final boolean standardBoard) {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        if (standardBoard) {
            //TODO: either generalize everything or leave this as is
            //For each color
            for (int player = 0; player < 2; player++) {
                final boolean color = player == 0 ? true : false;
                //Insert pawns
                for (int c = 0; c < BOARD_SIZE; c++) {
                    addPiece(new Pawn(color, c, color ? 1 : 6));
                }
                //Insert rooks
                addPiece(new Rook(color, 0, color ? 0 : 7));
                addPiece(new Rook(color, 7, color ? 0 : 7));
                //Insert knights
                addPiece(new Knight(color, 1, color ? 0 : 7));
                addPiece(new Knight(color, 6, color ? 0 : 7));
                //Insert bishop
                addPiece(new Bishop(color, 2, color ? 0 : 7));
                addPiece(new Bishop(color, 5, color ? 0 : 7));
                //Insert queen and king
                addPiece(new Queen(color, 3, color ? 0 : 7));
                if (color) {
                    wking = new King(true, 4, 0);
                    addPiece(wking);
                } else {
                    bking = new King(false, 4, 7);
                    addPiece(bking);
                }
            }
        }
    }

    /**
     * Class constructor which reconstructs a chessboard from a given list of moves.
     * @param lm is the list of moves which will be performed.
     */
    public ChessboardModelImpl(final List<MoveInfoImpl> lm) {
        this(true);
        lm.stream().forEach(m -> move(m));
    }


    @Override
    public Piece[][] getBoard() {
        return Arrays.copyOf(board, board.length);
    }

    @Override
    public King getKing(final boolean color) {
        if (color) {
            return wking;
        }
        return bking;
    }

    /**
     * Function used to add a piece to the chessboard.
     * @param p is the added piece.
     * @return true on success, false otherwise.
     */
    private boolean addPiece(final Piece p) {
        final int x = p.getPoint().getX();
        final int y = p.getPoint().getY();
        // If the board is not empty, the piece cannot be added accordingly.
        if (board[x][y] != null) {
            return false;
        }
        board[x][y] = p;
        return true;
    }

    @Override
    public List<Piece> getPieceOnBoard() {
        final List<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != null) {
                    result.add(board[i][j]);
                }
            }
        }
        return result;
    }

    @Override
    public boolean move(final MoveInfoImpl move) {
        // If move is legal, perform it
        if (MovesChecker.isLegal(this, move)) {
            simulateMove(move);
            return true;
        } else {
            //TODO: consider logging wrong moves somewhere
            return false;
        }
    }

    @Override
    public MoveInfoImpl getLast() {
        return lastMove;
    }

    @Override
    public Piece getSquare(final Point p) {
        return board[p.getX()][p.getY()];
    }

    @Override
    public Piece simulateMove(final MoveInfoImpl move) {
        Piece eaten = getSquare(move.getTo());
        Piece moving = getSquare(move.getFrom());
        final Point from = move.getFrom();
        final Point to = move.getTo();

        // Mark piece as moved
        moving.increaseMoved();

        // Piece in From moves in To
        moving.setPoint(to);

        // Promote piece if required
        if (move.getPromotion() != null) {
            try {
                moving = move.getPromotion().getConstructor(boolean.class, int.class, int.class).newInstance(moving.isWhite(), moving.getX(), moving.getY());
            } catch (Exception ex) {
                System.out.println("There is a issue with this promotion!");
            }
        }

        board[to.getX()][to.getY()] = moving;

        // Piece in From gets removed
        board[from.getX()][from.getY()] = null;

        // Check EnPassant
        if (moving instanceof Pawn && eaten == null && from.getX() != to.getX()) {
            // Assigns the eaten piece
            eaten = board[to.getX()][from.getY()];
            // Clears the eaten piece
            board[to.getX()][from.getY()] = null;
        }

        // Check Castling
        if (moving instanceof King && Math.abs(from.getX() - to.getX()) == 2) {
            final Point rookFrom = new Point(to.getX() == 6 ? 7 : 0, from.getY());
            final Piece rook = getSquare(rookFrom);
            final Point rookTo = new Point(rookFrom.getX() == 7 ? 5 : 3, rookFrom.getY());
            board[rookTo.getX()][rookTo.getY()] = rook;
            board[rookFrom.getX()][rookFrom.getY()] = null;
            rook.setPoint(rookTo);
        }
        // Updates reference to lastMove
        lastMove = move;
        // Returns the eaten piece, that can be tracked
        return eaten;
    }

    @Override
    public void undoMove(final MoveInfoImpl move, final Piece eaten, final MoveInfoImpl previousMove) {
        Piece moving = getSquare(move.getTo());
        final Point from = move.getFrom();
        final Point to = move.getTo();
        // Check promotion
        if (move.getPromotion() != null) {
            moving = new Pawn(moving.isWhite(), to.getX(), to.getY());
            // We make sure it will still be marked as moved
            moving.increaseMoved();
            moving.increaseMoved();
        }
        // Un-marks piece as moved
        moving.decreaseMoved();
        //Moves back moving
        moving.setPoint(from);
        board[from.getX()][from.getY()] = moving;
        board[to.getX()][to.getY()] = null;
        // We place eaten where it was before, so it works in En Passant too
        final Point prev = eaten == null ? to : eaten.getPoint();
        board[prev.getX()][prev.getY()] = eaten;
        // Check castling
        if (moving instanceof King && Math.abs(from.getX() - to.getX()) == 2) {
            // Restore rook
            final Point rookFrom = new Point(to.getX() == 6 ? 7 : 0, from.getY());
            final Point rookTo = new Point(rookFrom.getX() == 7 ? 5 : 3, rookFrom.getY());
            final Piece rook = getSquare(rookTo);
            board[rookFrom.getX()][rookFrom.getY()] = rook;
            board[rookTo.getX()][rookTo.getY()] = null;
            rook.setPoint(rookFrom);
        }
        // Updates reference to lastMove
        lastMove = previousMove;
    }
}
