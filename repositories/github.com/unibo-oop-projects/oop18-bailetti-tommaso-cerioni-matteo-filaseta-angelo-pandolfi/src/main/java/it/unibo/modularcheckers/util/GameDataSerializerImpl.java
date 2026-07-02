package it.unibo.modularcheckers.util;

import it.unibo.modularcheckers.checkers.model.piece.Man;
import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.ChessboardImpl;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic Implementation of GameDataSerializer.
 */
public class GameDataSerializerImpl implements GameDataSerializer {

    private FileOutputStream fileStr;
    private ObjectOutputStream outStr;

    // CHECKSTYLE: MagicNumber OFF
    private static Chessboard initCheckersChessboard() {
        Chessboard board = new ChessboardImpl();
        board.getBlock(new Coordinate(0, 0)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(0, 2)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(0, 4)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(0, 6)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(1, 1)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(1, 3)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(1, 5)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(1, 7)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(2, 0)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(2, 2)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(2, 4)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(2, 6)).setPiece(new Man(Color.WHITE));
        board.getBlock(new Coordinate(7, 1)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(7, 3)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(7, 5)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(7, 7)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(6, 0)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(6, 2)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(6, 4)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(6, 6)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(5, 1)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(5, 3)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(5, 5)).setPiece(new Man(Color.BLACK));
        board.getBlock(new Coordinate(5, 7)).setPiece(new Man(Color.BLACK));
        return board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serializeColors() {
        List<Color> colors = new ArrayList<>();
        colors.add(0, Color.WHITE);
        colors.add(1, Color.BLACK);
        try {
            openStreams(ConfPathHelper.getColorsPath().toURI());
            outStr.writeObject(colors);
            closeStreams();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("The color configuration has been serialized.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serializeCheckersBoard() {
        Chessboard cb = initCheckersChessboard();
        try {
            openStreams(ConfPathHelper.getCheckersPath().toURI());
            outStr.writeObject(cb);
            closeStreams();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("The checkers board has been serialized.");
    }

    private void openStreams(final URI uri) throws IOException {
        fileStr = new FileOutputStream(new File(uri));
        outStr = new ObjectOutputStream(fileStr);
    }

    private void closeStreams() throws IOException {
        outStr.close();
        fileStr.close();
    }
    // CHECKSTYLE: MagicNumber ON

}
