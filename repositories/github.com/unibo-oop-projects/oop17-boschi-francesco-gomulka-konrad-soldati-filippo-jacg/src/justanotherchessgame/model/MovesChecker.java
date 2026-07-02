package justanotherchessgame.model;

import justanotherchessgame.util.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to manage all the moves related logic.
 */
public final class MovesChecker {

    private MovesChecker() {
    }

    /**
     * Method that given a chessboard and a point on it,
     * returns all possible moves that can be performed
     * by moving the piece located there.
     * @param chessboard is the chessboard we are working on.
     * @param p is the given point from which the moves will be calculated.
     * @return an array of all the possible moves.
     */
    public static List<MoveInfoImpl> possibleMoves(final ChessboardModel chessboard, final Point p) {
        final List<MoveInfoImpl> result = new ArrayList<>();
        // Check for empty arguments and invalid Point
        if (chessboard == null || p == null || !p.onBoard()) {
            System.out.println("unexpected return: " + chessboard + " " + p);
            return result;
        }
        Piece piece;
        piece = chessboard.getSquare(p);
        // Check for empty square
        if (piece == null) {
            return result;
        }
        //for Pieces that move more than 1 base move (Bishop, Rook, Queen)
        int multiMoveCount;
        multiMoveCount = 1;
        if (!piece.usesSingleMove()) {
            multiMoveCount = 8;
        }

        //Current player's color
        final boolean color = piece.isWhite();
        //runs all the possible moves of the piece
        Point stretchedMove;
        MoveInfoImpl move;
        Piece temp;
        MoveLoop: //TODO: check if this is a proper coding style
            for (final Moves m : piece.getMoves()) {
                for (int i = 1; i <= multiMoveCount; i++) {
                    // Calculate the move offset
                    stretchedMove = Point.mul(new Point(m.getX(), m.getY()), i);
                    // Apply move to current point
                    final Point reach = Point.sum(stretchedMove, p);
                    // If reach is outside boundaries, no need to check this move further
                    if (!reach.onBoard()) {
                        break;
                    }
                    move = new MoveInfoImpl(p, reach);
                    // The piece occupying reach, if any
                    temp = chessboard.getSquare(reach);
                    // If we meet a Piece
                    if (temp != null) {
                        // If it's a pawn and we are not moving diagonally, we stop
                        if (piece instanceof Pawn && !piece.canEatTo(reach)) {
                            break MoveLoop;
                        }
                        // If we meet a piece of the same color, we just stop
                        if (temp.getColor().equals(piece.getColor())) {
                            break;
                        }
                    } else {
                        // If no collision, check for Pawns, as we want to skip diagonals
                        if (piece instanceof Pawn && piece.canEatTo(reach)) {
                            break;
                        }
                    }

                    // Store previous move
                    final MoveInfoImpl previous = chessboard.getLast();
                    // We need to track eaten
                    final Piece eaten = chessboard.simulateMove(move);
                    // We add the move only if legal
                    if (!kingCheck(chessboard, chessboard.getKing(color).getPoint(), color)) {
                        result.add(move);
                    }
                    // Restore chessboard state
                    chessboard.undoMove(move, eaten, previous);
                    // If we had a collision, we break
                    if (temp != null) {
                        break;
                    }
                }
            }
        // End of label MoveLoop

        //Add castlings
        if (piece instanceof King) {
            for (final MoveInfoImpl m : getCastlings(chessboard, (King) piece)) {
                result.add(m);
            }
        }

        //Add en passant
        if (checkEnPassant(chessboard, piece)) {
            final Point enemyPawn = chessboard.getLast().getTo();
            // If we are white, we move forward, otherwise backwards
            final MoveInfoImpl m = new MoveInfoImpl(p, new Point(enemyPawn.getX(), p.getY() + (color ? 1 : -1)));
            result.add(m);
        }
        return result;
    }

    /**
     * Method that returns if an enPassant move is possible for piece p.
     * @param chessboard is the chessboard we are considering.
     * @param p is the piece we want to use for En Passant.
     * @return a boolean indicating if the En Passant is possible.
     */
    private static boolean checkEnPassant(final ChessboardModel chessboard, final Piece p) {
        // Piece must be a pawn
        if (!(p instanceof Pawn)) {
            return false;
        }
        final MoveInfoImpl last = chessboard.getLast();
        // Cannot be first move
        if (last == null) {
            return false;
        }
        // Same y, delta x = +/- 1
        return (last.getTo().getY() == p.getY() && Math.abs(last.getTo().getX() - p.getX()) == 1 && chessboard.getSquare(last.getTo()) instanceof Pawn && Math.abs(last.getTo().getY() - last.getFrom().getY()) == 2);
    }

    /**
     * Method that checks if a given move respects standard chess rules.
     * @param chessboard is the chessboard we are considering.
     * @param move is the Move whose legality is questioned.
     * @return a boolean indicating if the move is legal.
     */
    public static boolean isLegal(final ChessboardModel chessboard, final MoveInfoImpl move) {
        // We avoid null parameters
        if (chessboard == null || move == null) {
            return false;
        }
        // We generate all possible moves and look for the requested one
        final List<MoveInfoImpl> list = possibleMoves(chessboard, move.getFrom());
        for (final MoveInfoImpl m : list) {
            // If we find a move with the same Points
            if (m.getTo().equals(move.getTo()) && m.getFrom().equals(move.getFrom())) {
                return true;
            }
        }
        // If the move wasn't found, it means it's not possible
        return false;
    }

    /**
     * Method that checks if a given point (presumably where the king is or will be) is under check.
     * @param cb is the chessboard we are considering.
     * @param check is the point to check.
     * @param color is the current player color.
     * @return a boolean indicating if the point can be eaten by an enemy piece.
     */
    public static boolean kingCheck(final ChessboardModel cb, final Point check, final boolean color) {
        //check the king
        final ArrayList<Moves> mosse = new ArrayList<Moves>();
        Point p;
        //normal pieces
        mosse.add(Moves.UP);
        mosse.add(Moves.DOWN);
        mosse.add(Moves.LEFT);
        mosse.add(Moves.RIGHT);
        mosse.add(Moves.UP_LEFT);
        mosse.add(Moves.UP_RIGHT);
        mosse.add(Moves.DOWN_LEFT);
        mosse.add(Moves.DOWN_RIGHT);

        //for each move in the moves list
        for (final Moves m : mosse) {
            p = Point.sum(check, new Point(m.getX(), m.getY()));

            //if its on board and the space is free
            while (p.onBoard() && (cb.getSquare(p) == null || p.equals(cb.getKing(color).getPoint()))) {
                p = Point.sum(p, new Point(m.getX(), m.getY()));
            }
            //if the piece in next position can eat the king false 
            return (p.onBoard() && cb.getSquare(p) != null && cb.getSquare(p).color != color && cb.getSquare(p).canEatTo(check));
        }
        //knight
        mosse.clear();
        mosse.add(Moves.KNIGHT_UPL);
        mosse.add(Moves.KNIGHT_UPR);
        mosse.add(Moves.KNIGHT_LEFTU);
        mosse.add(Moves.KNIGHT_LEFTD);
        mosse.add(Moves.KNIGHT_RIGHTU);
        mosse.add(Moves.KNIGHT_RIGHTD);
        mosse.add(Moves.KNIGHT_DOWNL);
        mosse.add(Moves.KNIGHT_DOWNR);

        for (final Moves m : mosse) {
            p = Point.sum(check, new Point(m.getX(), m.getY()));
            return (p.onBoard() && cb.getSquare(p) != null && cb.getSquare(p).color != color && cb.getSquare(p) instanceof Knight);
        }
        return false;
    }

    /**
     * Method that checks if a castling is possible, considering a certain King.
     * @param chessboard is the chessboard we are considering.
     * @param king is the King which may perform a castling.
     * @return a list of the possible castlings (can be empty).
     */
    //TODO: fix the use of coordinates
    public static List<MoveInfoImpl> getCastlings(final ChessboardModel chessboard, final King king) {
        final List<MoveInfoImpl> castlings = new ArrayList<MoveInfoImpl>();
        if (canShortCastle(chessboard, king)) {
            castlings.add(new MoveInfoImpl(king.getPoint(), new Point(king.getX() + 2, king.getY())));
        }
        if (canLongCastle(chessboard, king)) {
            castlings.add(new MoveInfoImpl(king.getPoint(), new Point(king.getX() - 2, king.getY())));
        }
        return castlings;
    }

    /**
     * Method that checks if a long castling is possible.
     * @param chessboard is the chessboard we are considering.
     * @param King is the King which may perform a castling.
     * @return a boolean indicating if the Long castling is possible.
     */
    private static boolean canLongCastle(final ChessboardModel cb, final King king) {
        // King must have not moved
        if (king.hasMoved()) {
            return false;
        }
        // Two empty squares on the left
        final Point e1 = new Point(king.getX() - 1, king.getY());
        final Point e2 = new Point(king.getX() - 2, king.getY());
        if (cb.getSquare(e1) != null || cb.getSquare(e2) != null) {
            return false;
        }
        final boolean color = king.isWhite();
        // King, e1, e2 not in check
        if (kingCheck(cb, king.getPoint(), color) 
                || kingCheck(cb, e1, color) 
                || kingCheck(cb, e2, color)) {
            return false;
        }
        final Point e4 = new Point(king.getX() - 4, king.getY());
        final Piece p = cb.getSquare(e4);
        // p has to be a Rook and never moved
        return p != null && p instanceof Rook && !p.hasMoved();
    }

    /**
     * Method that checks if a short castling is possible.
     * @param chessboard is the chessboard we are considering.
     * @param King is the King which may perform a castling.
     * @return a boolean indicating if the Short castling is possible.
     */
    private static boolean canShortCastle(final ChessboardModel cb, final King king) {
        // King must have not moved
        if (king.hasMoved()) {
            return false;
        }
        // Two empty squares on the right
        final Point e1 = new Point(king.getX() + 1, king.getY());
        final Point e2 = new Point(king.getX() + 2, king.getY());
        if (cb.getSquare(e1) != null || cb.getSquare(e2) != null) {
            return false;
        }
        final boolean color = king.isWhite();
        // King, e1, e2 not in check
        if (kingCheck(cb, king.getPoint(), color) 
                || kingCheck(cb, e1, color) 
                || kingCheck(cb, e2, color)) {
            return false;
        }
        final Point e3 = new Point(king.getX() + 3, king.getY());
        final Piece p = cb.getSquare(e3);

        // p has to be a Rook and never moved
        return p != null && p instanceof Rook && !p.hasMoved();
    }

    /**
     * Method that checks if a piece is a Pawn about to promote.
     * @param p is the piece which might promote.
     * @param point is the Coordinates of the piece.
     * @return a boolean indicating if the piece is a Pawn that can promote.
     */
    public static boolean willPromote(final Piece p, final Point point) {
        // Only pawns promote
        if (!(p instanceof Pawn)) {
            return false;
        }
        return (p.isWhite() && point.getY() == 7) || (!p.isWhite() && point.getY() == 0);
    }
}
