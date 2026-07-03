package controller.file;

import java.util.Objects;

import controller.CircuitPlayable;

/**
 * Builder to create a new ViewFile object.
 */
public class BuilderViewFile {

    private String track = "";
    private String coord;
    private String circuit;
    private CircuitPlayable cir;

    /**
     * Set the file that has all the circuit's tracks.
     * @param track the name of the track file
     * @return the BuilderViewFile object used
     */
    public BuilderViewFile trackFile(final String track) {
        Objects.requireNonNull(track);
        this.track = track;
        return this;
    }

    /**
     * Set the file that has all the coordinates of the circuit's cells.
     * @param coord the name of the coordinates file
     * @return the BuilderViewFile object used
     */
    public BuilderViewFile coordinatesFile(final String coord) {
        Objects.requireNonNull(coord);
        this.coord = coord;
        return this;
    }

    /**
     * Set the file that has the circuit's image.
     * @param circuit the name of the image
     * @return the BuilderViewFile object used
     */
    public BuilderViewFile circuitImageFile(final String circuit) {
        Objects.requireNonNull(circuit);
        this.circuit = circuit;
        return this;
    }

    /**
     * Set the circuit of the game.
     * @param circuit the circuit
     * @return the BuilderViewFile object used
     */
    public BuilderViewFile circuitGame(final CircuitPlayable circuit) {
        Objects.requireNonNull(circuit);
        this.cir = circuit;
        return this;
    }

    /**
     * Creates a new object of type ViewFile.
     * @return the new ViewFile object created
     * @throws MalformedURLException 
     */
    public ViewFile build() {
        Objects.requireNonNull(this.circuit);
        Objects.requireNonNull(this.coord);
        Objects.requireNonNull(this.track);
        Objects.requireNonNull(this.cir);
        return new ViewFileImpl(track, coord, circuit, cir);
    }

}
