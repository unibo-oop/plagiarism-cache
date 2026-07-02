package boardgames.view.board;

import java.awt.Color;

import java.net.URL;

import java.util.Map;
import javax.swing.JButton;

import boardgames.controller.GameControllerImpl;

import boardgames.model.board.Box;

public class ChessBoardView extends BoardView {


    public ChessBoardView(GameControllerImpl ctr) {
        super(ctr);
    }


    @Override
    Color getFirstBoxColor() {
        return new Color(BoardView.getRgb1());

    }
    @Override
    Color getSecondBoxColor() {
        return new Color(BoardView.getRgb2());

    }

    @Override
    final String nameIconExtension() {
        return "(Chess).png";
    }

}
