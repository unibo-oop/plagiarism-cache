package boardgames.model.piece;

import boardgames.model.ChessGame;
import boardgames.model.Game;
import boardgames.model.board.Board;
import boardgames.model.board.Box;
import boardgames.model.board.ChessBoard;
import boardgames.utility.Colour;

public class TestPiecesMoves {

    public static void main(String[] args) {
   
//        for (int i = 0; i < 8; i++) {
//        b.getAllBoxes().get(i).get(1).getPiece().possibleMoves(b).forEach(a -> System.out.println(a.toString()));
//        System.out.println(b.getAllBoxes().get(i).get(1).getPiece().toString());
//        System.out.println(b.getAllBoxes().get(i).get(1).getPiece().possibleMoves(b).size());
//       /* b.whiteStartPieces().forEach(a -> System.out.println(a.getBox().toString()));*/
//        }
      
        
//        ChessGame cb = new ChessGame(false);
//        
//        for(int i=0; i < 16; i++) {
//            System.out.println(cb.getPlayer(Colour.Black).getPlayerPieces().get(i).getName());
//        }
//        
//        System.out.println(cb.getBoard().getBox(0, 1).getPiece().getName());
//        System.out.println(cb.getBoard().getBox(1, 2).getPiece().getName());
//        
//        cb.move(Colour.White, cb.getBoard().getBox(0, 1).getPiece(), cb.getBoard().getBox(1, 2));
//        
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        
//        for(int i=0; i < 15; i++) {
//            System.out.println(cb.getPlayer(Colour.Black).getPlayerPieces().get(i).getName());
//        }
//        
//        System.out.println(cb.getBoard().getBox(1, 2).getPiece().getName());
//        System.out.println(cb.getBoard().getBox(0, 1).getPiece().getName());
        
//        ChessGame cg = new ChessGame(false);
//        
//        System.out.println(cg.getBoard().getBox(0, 4).getPiece().get().getName());
//        
//        cg.move(Colour.White, cg.getBoard().getBox(0, 4).getPiece().get(), cg.getBoard().getBox(0, 3));
//        
//        System.out.println(cg.getWinner());
//     
        
//        //check mossa possibile pedone dopo aver fatto la prima mossa
//        ChessGame cb = new ChessGame(false);
//        //cb.getBoard().getWhiteStartPieces().forEach(e -> System.out.println(e.toString()));
//        //cb.getBoard().getBlackStartPieces().forEach(e -> System.out.println(e.toString()));
//        
//        Pawn a = (Pawn) cb.getBoard().getBox(1, 1).getPiece().get();
//        
//        
//        if(cb.move(Colour.White, a, cb.getBoard().getBox(1, 3))) {
//            System.out.println(a.toString());
//        }
//        
//        System.out.println(cb.getBoard().getBox(1, 1).toString());
//        
//        a.possibleMoves(cb.getBoard()).forEach(b -> System.out.println(b.toString()));
//        System.out.println(a.possibleMoves(cb.getBoard()).size());
//        
//        // check mosse possibili pedone con pezzi avversari mangiabili
//        cb.move(Colour.White, a, cb.getBoard().getBox(1, 4));
//        cb.move(Colour.White, a, cb.getBoard().getBox(1, 5));
//        a.possibleMoves(cb.getBoard()).forEach(b -> System.out.println(b.toString()));
//        System.out.println(a.possibleMoves(cb.getBoard()).size());
//        
//        
//        
//        Bishop r = (Bishop) cb.getBoard().getBox(2, 0).getPiece().get();
//        
//        
//        if(cb.move(Colour.White, r, cb.getBoard().getBox(1, 1))) {
//            System.out.println(r.toString());
//        }
//        
//        System.out.println(cb.getBoard().getBox(1, 1).toString());
//        
//        r.possibleMoves(cb.getBoard()).forEach(b -> System.out.println(b.toString()));
//        System.out.println(r.possibleMoves(cb.getBoard()).size());
//        
//        // check mosse possibili pedone con pezzi avversari mangiabili
//        cb.move(Colour.White, r, cb.getBoard().getBox(2, 2));
//        cb.move(Colour.White, r, cb.getBoard().getBox(3, 3));
//        r.possibleMoves(cb.getBoard()).forEach(b -> System.out.println(b.toString()));
//        System.out.println(r.possibleMoves(cb.getBoard()).size());
    }
}
