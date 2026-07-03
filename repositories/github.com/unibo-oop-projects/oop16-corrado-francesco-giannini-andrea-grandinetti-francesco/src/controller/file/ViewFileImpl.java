package controller.file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import controller.CircuitPlayable;
import javafx.scene.image.Image;

/**
 * A possible implementation of ViewFile interface.
 */
public class ViewFileImpl implements ViewFile {

    private final BufferedReader trackFile;
    private final BufferedReader coordFile;
    private final Image viewCircuit;
    private final CircuitPlayable cir;

    ViewFileImpl(final String track, final String coord, 
                 final String circuit, final CircuitPlayable cir) {
        final InputStream trackIS = this.getClass().getResourceAsStream(track);
        final InputStream coordIS = this.getClass().getResourceAsStream(coord);
        final InputStream circuitIS = this.getClass().getResourceAsStream(circuit);
        try {
            this.trackFile = new BufferedReader(new InputStreamReader(trackIS, "UTF-8"));
            this.coordFile = new BufferedReader(new InputStreamReader(coordIS, "UTF-8"));
            this.viewCircuit = new Image(circuitIS);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException();
        }
        this.cir = cir;
    }

    @Override
    public BufferedReader getTracks() {
        return this.trackFile;
    }

    @Override
    public BufferedReader getCoordinates() {
        return this.coordFile;
    }

    @Override
    public Image getCircuitImage() {
        return this.viewCircuit;
    }

    @Override
    public CircuitPlayable getCircuit() {
        return this.cir;
    }

}
