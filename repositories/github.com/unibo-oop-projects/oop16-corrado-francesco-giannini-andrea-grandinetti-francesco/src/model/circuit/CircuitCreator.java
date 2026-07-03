package model.circuit;
import java.io.BufferedReader;
import java.util.List;
/**
 * Create the circuit.
 */
public interface CircuitCreator {
   /**
     * @param br the file's bufferedReader from which the circuit has to be created
     * @return list of tracks
     */
    List<Track> createCircuit(BufferedReader br);
}
