package it.unibo.modularcheckers.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.List;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.ChessboardImpl;
import it.unibo.modularcheckers.model.Color;

import javax.swing.CellEditor;

/**
 * Basic implementation of GameDataDeserializer.
 */
public class GameDataDeserializerImpl implements GameDataDeserializer {

    private FileInputStream file;
    private ObjectInputStream in;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Color> deserializeColor() {
        List<Color> listToReturn = null;
        try {
            openStreams(ConfPathHelper.getColorsPath().toURI());
            listToReturn = (List<Color>) in.readObject();
            closeStreams();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        } catch (Exception ex) {
            System.out.println("URI Malformed: " + ex.getMessage());
        }
        return listToReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Chessboard deserializeCheckersBoard() {
        Chessboard boardConf = null;
        try {
            openStreams(ConfPathHelper.getCheckersPath().toURI());
            boardConf = (Chessboard) in.readObject();
            closeStreams();
        } catch (IOException ex) {
            System.out.println("IOException is caught: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        } catch (Exception ex) {
            System.out.println("URI Malformed: " + ex.getMessage());
        }
        return boardConf;
    }

    private void openStreams(final URI str) throws IOException {
        file = new FileInputStream(new File(str));
        in = new ObjectInputStream(file);
    }

    private void closeStreams() throws IOException {
        in.close();
        file.close();
    }
}
