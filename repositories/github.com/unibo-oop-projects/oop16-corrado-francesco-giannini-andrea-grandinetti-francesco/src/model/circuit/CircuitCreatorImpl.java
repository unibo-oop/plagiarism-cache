package model.circuit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * 
 */
public final class CircuitCreatorImpl implements CircuitCreator {

    private static final CircuitCreator SINGLETON = new CircuitCreatorImpl();
    private final List<Track> trackList = new ArrayList<>();

    private CircuitCreatorImpl() {
    }

    @Override
    public List<Track> createCircuit(final BufferedReader br) {
        this.trackList.clear();
        try { 
            String line = br.readLine();
            while (line != null) {
                final List<Integer> str = Stream.of(line.split(","))
                                                .map(x->Integer.parseInt(x))
                                                .collect(Collectors.toList());
                if (str.size() >= 3 && str.get(0) > 0 && str.get(1) > 0) {
                    this.trackList.add(new TrackImpl(str.get(0), str.get(1), str.get(2) > 0));
                }
                line = br.readLine();
            }
            br.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        if (this.trackList.size() == 0) {
            throw new IllegalStateException("The file has no valid tracks");
        }
        return this.trackList;
    }

    /**
     * Getter.
     * @return the only instance of the CircuitCreator implementation
     */
    public static CircuitCreator getSingleton() {
        return SINGLETON;
    }

}
