package view.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.PositionImpl;
import model.RaceDirectionImpl;
import utility.Position;
/**
 * Class to create map of slots.
 *
 */
public final class SlotCreatorImpl implements SlotCreator {
    private static final SlotCreator SINGLETON = new SlotCreatorImpl();
    private final Map<Position, Slot> slotMap = new HashMap<>();

    private SlotCreatorImpl() {
    }

    @Override
    public Map<Position, Slot> createSlots(final BufferedReader br) {
        this.slotMap.clear();
        int pSlot = 1;
        int slot = 0;
        int track = 0;
        try { 
            String line;
            line = br.readLine();
            while (line != null) {
                if (line.equals("BOX")) {
                    line = br.readLine();
                    if (line == null) {
                        throw new IllegalArgumentException("There isn't line with coordinate of box");
                    }
                    final List<Double> str = Stream.of(line.split(","))
                                                .map(x->Double.parseDouble(x))
                                                .collect(Collectors.toList());
                    this.slotMap.put(RaceDirectionImpl.BOX_POS, new SlotImpl(RaceDirectionImpl.BOX_POS, str.get(0), str.get(1), str.get(2)));
                } else if (line.equals("END")) {
                        line = br.readLine();
                        if (line == null) {
                            throw new IllegalArgumentException("There isn't line with coordinate of end slot");
                        }
                        final List<Double> str = Stream.of(line.split(","))
                                                    .map(x->Double.parseDouble(x))
                                                    .collect(Collectors.toList());
                        this.slotMap.put(RaceDirectionImpl.END_POS, new SlotImpl(RaceDirectionImpl.END_POS, str.get(0), str.get(1), str.get(2)));
                } else if (line.equals("+")) {
                    track++;
                    pSlot = 1;
                    slot = 0;
                } else if (line.equals("-")) {
                    pSlot++;
                    slot = 0;
                } else {
                    final List<Double> str = Stream.of(line.split(","))
                                              .map(x->Double.parseDouble(x))
                                              .collect(Collectors.toList());
                    final Position pos = new PositionImpl(pSlot, slot, track);
                    this.slotMap.put(pos, new SlotImpl(pos, str.get(0), str.get(1), str.get(2)));
                    slot++;
                }
                line = br.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        if (this.slotMap.size() == 0) {
            throw new IllegalStateException("The file has no valid tracks");
        }
        return this.slotMap;
    }

    /**
     * Getter.
     * @return the only instance of the CircuitCreator implementation
     */
    public static SlotCreator getSingleton() {
        return SINGLETON;
    }
}
