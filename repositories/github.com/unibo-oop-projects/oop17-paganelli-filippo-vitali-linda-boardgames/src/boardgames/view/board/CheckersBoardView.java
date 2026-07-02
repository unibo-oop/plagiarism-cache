package boardgames.view.board;

import java.awt.Color;
import java.net.URL;
import java.util.Map;

import javax.swing.JButton;

import boardgames.controller.GameControllerImpl;

import boardgames.model.board.Box;


public class CheckersBoardView extends BoardView{
    
    private static final URL BLACKKINGCHECKERS = CheckersBoardView.class.getResource("/images/BlackKing(Checkers).png");
    private static final URL WHITEKINGCHECKERS = CheckersBoardView.class.getResource("/images/WhiteKing(Checkers).png");
   
    public CheckersBoardView(final GameControllerImpl ctr) {
        super(ctr); 
    }

    @Override
    Color getFirstBoxColor() {
        return new Color(BoardView.getRgb2());

    }
    @Override
    Color getSecondBoxColor() {
        return new Color(BoardView.getRgb1());

    }

    @Override
    final String nameIconExtension() {
        return "(Checkers).png";
    }
    
}
