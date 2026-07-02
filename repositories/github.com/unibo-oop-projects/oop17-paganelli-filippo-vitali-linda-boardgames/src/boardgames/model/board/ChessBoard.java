package boardgames.model.board;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import boardgames.model.piece.Piece;
import boardgames.model.piece.Bishop;
import boardgames.model.piece.King;
import boardgames.model.piece.Knight;
import boardgames.model.piece.Pawn;
import boardgames.model.piece.Queen;
import boardgames.model.piece.Rook;
import boardgames.utility.Colour;
import boardgames.utility.PieceUtils;

public class ChessBoard implements BoardType {
 
    private final static int PLAYER_PAWNS = 8;
    
    private final static int PLAYER_WHITE_PAWNS_ROW = 1;
    private final static int PLAYER_BLACK_PAWNS_ROW = 6;
    
    private final static int WHITE_KING_X = 4;
    private final static int WHITE_KING_Y = 0;
    
    private final static int BLACK_KING_X = 4;
    private final static int BLACK_KING_Y = 7;
    
    private final static int WHITE_QUEEN_X = 3;
    private final static int WHITE_QUEEN_Y = 0;
    
    private final static int BLACK_QUEEN_X = 3;
    private final static int BLACK_QUEEN_Y = 7;
    
    private final static int WHITE_1ROOK_X = 0;
    private final static int WHITE_1ROOK_Y = 0;
    
    private final static int WHITE_2ROOK_X = 7;
    private final static int WHITE_2ROOK_Y = 0;
    
    private final static int BLACK_1ROOK_X = 0;
    private final static int BLACK_1ROOK_Y = 7;
    
    private final static int BLACK_2ROOK_X = 7;
    private final static int BLACK_2ROOK_Y = 7;
    
    private final static int WHITE_1BISHOP_X = 2;
    private final static int WHITE_1BISHOP_Y = 0;
    
    private final static int WHITE_2BISHOP_X = 5;
    private final static int WHITE_2BISHOP_Y = 0;
    
    private final static int BLACK_1BISHOP_X = 2;
    private final static int BLACK_1BISHOP_Y = 7;
    
    private final static int BLACK_2BISHOP_X = 5;
    private final static int BLACK_2BISHOP_Y = 7;
    
    private final static int WHITE_1KNIGHT_X = 1;
    private final static int WHITE_1KNIGHT_Y = 0;
    
    private final static int WHITE_2KNIGHT_X = 6;
    private final static int WHITE_2KNIGHT_Y = 0;
    
    private final static int BLACK_1KNIGHT_X = 1;
    private final static int BLACK_1KNIGHT_Y = 7;
    
    private final static int BLACK_2KNIGHT_X = 6;
    private final static int BLACK_2KNIGHT_Y = 7;

    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private List<List<Box>> allPiecesOnBoard;
    
    public ChessBoard() {
        this.whitePieces = new LinkedList<>();
        this.blackPieces = new LinkedList<>();
        this.allPiecesOnBoard = new LinkedList<>();
        
        for (int i = 0; i <= PieceUtils.BOARD_SIZE; i++) {
            this.allPiecesOnBoard.add(new LinkedList<Box>());
            for (int y = 0; y <= PieceUtils.BOARD_SIZE; y++) {
                this.allPiecesOnBoard.get(i).add(new Box(i, y, Optional.empty()));
            }
        }
    }
    
    public List<List<Box>> reset() {
       for (int i = 0; i < PLAYER_PAWNS; i++) {
           /*   Initialization of white player's pawns
            */

           final Piece pW = new Piece(allPiecesOnBoard.get(i).get(PLAYER_WHITE_PAWNS_ROW), Colour.White);
           pW.setPieceType(new Pawn(allPiecesOnBoard.get(i).get(PLAYER_WHITE_PAWNS_ROW)));
           this.addWhitePiece(pW);
           allPiecesOnBoard.get(i).get(PLAYER_WHITE_PAWNS_ROW).setPiece(Optional.of(pW));
           
           /*   Initialization of black player's pawns
            */
           final Piece pB = new Piece(allPiecesOnBoard.get(i).get(PLAYER_BLACK_PAWNS_ROW), Colour.Black);
           pB.setPieceType(new Pawn(allPiecesOnBoard.get(i).get(PLAYER_BLACK_PAWNS_ROW)));
           this.addBlackPiece(pB);
           allPiecesOnBoard.get(i).get(PLAYER_BLACK_PAWNS_ROW).setPiece(Optional.of(pB));
       }
       
       final Piece kW = new Piece(allPiecesOnBoard.get(WHITE_KING_X).get(WHITE_KING_Y), Colour.White);
       kW.setPieceType(new King());
       this.addWhitePiece(kW);
       allPiecesOnBoard.get(WHITE_KING_X).get(WHITE_KING_Y).setPiece(Optional.of(kW));
       
       final Piece kB = new Piece(allPiecesOnBoard.get(BLACK_KING_X).get(BLACK_KING_Y), Colour.Black);
       kB.setPieceType(new King());
       this.addBlackPiece(kB);
       allPiecesOnBoard.get(BLACK_KING_X).get(BLACK_KING_Y).setPiece(Optional.of(kB));
       
       final Piece qW = new Piece(allPiecesOnBoard.get(WHITE_QUEEN_X).get(WHITE_QUEEN_Y), Colour.White);
       qW.setPieceType(new Queen());
       this.addWhitePiece(qW);
       allPiecesOnBoard.get(WHITE_QUEEN_X).get(WHITE_QUEEN_Y).setPiece(Optional.of(qW));
       
       final Piece qB = new Piece(allPiecesOnBoard.get(BLACK_QUEEN_X).get(BLACK_QUEEN_Y), Colour.Black);
       qB.setPieceType(new Queen());
       this.addBlackPiece(qB);
       allPiecesOnBoard.get(BLACK_QUEEN_X).get(BLACK_QUEEN_Y).setPiece(Optional.of(qB));
       
       final Piece r1W = new Piece(allPiecesOnBoard.get(WHITE_1ROOK_X).get(WHITE_1ROOK_Y), Colour.White);
       r1W.setPieceType(new Rook());
       this.addWhitePiece(r1W);
       allPiecesOnBoard.get(WHITE_1ROOK_X).get(WHITE_1ROOK_Y).setPiece(Optional.of(r1W));

       final Piece r2W = new Piece(allPiecesOnBoard.get(WHITE_2ROOK_X).get(WHITE_2ROOK_Y), Colour.White);
       r2W.setPieceType(new Rook());
       this.addWhitePiece(r2W);
       allPiecesOnBoard.get(WHITE_2ROOK_X).get(WHITE_2ROOK_Y).setPiece(Optional.of(r2W));
       
       final Piece r1B = new Piece(allPiecesOnBoard.get(BLACK_1ROOK_X).get(BLACK_1ROOK_Y), Colour.Black);
       r1B.setPieceType(new Rook());
       this.addBlackPiece(r1B);
       allPiecesOnBoard.get(BLACK_1ROOK_X).get(BLACK_1ROOK_Y).setPiece(Optional.of(r1B));
       
       final Piece r2B = new Piece(allPiecesOnBoard.get(BLACK_2ROOK_X).get(BLACK_2ROOK_Y), Colour.Black);
       r2B.setPieceType(new Rook());
       this.addBlackPiece(r2B);
       allPiecesOnBoard.get(BLACK_2ROOK_X).get(BLACK_2ROOK_Y).setPiece(Optional.of(r2B));
       
       final Piece b1W = new Piece(allPiecesOnBoard.get(WHITE_1BISHOP_X).get(WHITE_1BISHOP_Y), Colour.White);
       b1W.setPieceType(new Bishop());
       this.addWhitePiece(b1W);
       allPiecesOnBoard.get(WHITE_1BISHOP_X).get(WHITE_1BISHOP_Y).setPiece(Optional.of(b1W));

       final Piece b2W = new Piece(allPiecesOnBoard.get(WHITE_2BISHOP_X).get(WHITE_2BISHOP_Y), Colour.White);
       b2W.setPieceType(new Bishop());
       this.addWhitePiece(b2W);
       allPiecesOnBoard.get(WHITE_2BISHOP_X).get(WHITE_2BISHOP_Y).setPiece(Optional.of(b2W));
       
       final Piece b1B = new Piece(allPiecesOnBoard.get(BLACK_1BISHOP_X).get(BLACK_1BISHOP_Y), Colour.Black);
       b1B.setPieceType(new Bishop());
       this.addBlackPiece(b1B);
       allPiecesOnBoard.get(BLACK_1BISHOP_X).get(BLACK_1BISHOP_Y).setPiece(Optional.of(b1B));
       
       final Piece b2B = new Piece(allPiecesOnBoard.get(BLACK_2BISHOP_X).get(BLACK_2BISHOP_Y), Colour.Black);
       b2B.setPieceType(new Bishop());
       this.addBlackPiece(b2B);
       allPiecesOnBoard.get(BLACK_2BISHOP_X).get(BLACK_2BISHOP_Y).setPiece(Optional.of(b2B));
       
       final Piece k1W = new Piece(allPiecesOnBoard.get(WHITE_1KNIGHT_X).get(WHITE_1KNIGHT_Y), Colour.White);
       k1W.setPieceType(new Knight());
       this.addWhitePiece(k1W);
       allPiecesOnBoard.get(WHITE_1KNIGHT_X).get(WHITE_1KNIGHT_Y).setPiece(Optional.of(k1W));

       final Piece k2W = new Piece(allPiecesOnBoard.get(WHITE_2KNIGHT_X).get(WHITE_2KNIGHT_Y), Colour.White);
       k2W.setPieceType(new Knight());
       this.addWhitePiece(k2W);
       allPiecesOnBoard.get(WHITE_2KNIGHT_X).get(WHITE_2KNIGHT_Y).setPiece(Optional.of(k2W));
       
       final Piece k1B = new Piece(allPiecesOnBoard.get(BLACK_1KNIGHT_X).get(BLACK_1KNIGHT_Y), Colour.Black);
       k1B.setPieceType(new Knight());
       this.addBlackPiece(k1B);
       allPiecesOnBoard.get(BLACK_1KNIGHT_X).get(BLACK_1KNIGHT_Y).setPiece(Optional.of(k1B));
       
       final Piece k2B = new Piece(allPiecesOnBoard.get(BLACK_2KNIGHT_X).get(BLACK_2KNIGHT_Y), Colour.Black);
       k2B.setPieceType(new Knight());
       this.addBlackPiece(k2B);
       allPiecesOnBoard.get(BLACK_2KNIGHT_X).get(BLACK_2KNIGHT_Y).setPiece(Optional.of(k2B));   
       
       return this.allPiecesOnBoard;
    }

    private void addWhitePiece(final Piece pw) {
        this.whitePieces.add(pw);
    }

    private void addBlackPiece(final Piece pb) {
        this.blackPieces.add(pb);
    }

    public List<Piece> getWhiteStartPieces() {
        return this.whitePieces;
    }

    public List<Piece> getBlackStartPieces() {
        return this.blackPieces;
    }
}
