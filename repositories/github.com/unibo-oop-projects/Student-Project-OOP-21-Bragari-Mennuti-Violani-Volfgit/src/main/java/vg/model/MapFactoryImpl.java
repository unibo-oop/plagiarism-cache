package vg.model;

import vg.model.entity.dynamicEntity.player.BasePlayer;
import vg.model.entity.dynamicEntity.player.Player;
import vg.model.entity.staticEntity.StaticEntity;
import vg.model.levels.LEVEL;
import vg.model.levels.levelGenerator;
import vg.utils.V2D;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * {@inheritDoc}.
 */
public class MapFactoryImpl implements MapFactory<V2D> {

    MapFactoryImpl() {
    }

    /**
     * This was an initial attempt to create fixed levels,
     * but was a bit ugly, now it's a "backup" plan if {@link #fromSerialized(int)}
     * fails.
     * @param lv the
     * @return {@link Map}
     */
    @Override
    public Map<V2D> fromEnum(final LEVEL lv) {
        return new MapImpl(BasePlayer.newPlayer(new V2D(0, 0)), lv.getBoss(), new HashSet<>(), lv.getEnemies(),
                IntStream.rangeClosed(0, 200).boxed().
                flatMap(e -> Stream.of(new V2D(e, 0), new V2D(0, e), new V2D(200, e), new V2D(e, 150)))
                        .filter(e -> e.getY() <= 150).collect(Collectors.toSet()));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Map<V2D> fromSerialized(final int lv) {
        var lg = new levelGenerator();
        Map<V2D> map;
        try {
            map = lg.deserializeLevel(Integer.toString(lv));
        } catch (IOException | ClassNotFoundException e) {
            try {
                lg.serializeDefaults();
                map = lg.deserializeLevel(Integer.toString(lv));
            } catch (Exception ex) {
                System.out.println("IO error in MapFactoryImpl.fromSerialized");
                map = fromEnum(LEVEL.values()[lv - 1]);
            }
        }
        return map;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Map<V2D> fromSave() {
        var lg = new levelGenerator();
        Map<V2D> map;
        try {
            map = lg.deserializeSaved();
        } catch (IOException | ClassNotFoundException e) {
          throw new RuntimeException("Failed to load save file.");
        }
        return map;
    }
    @Override
    public Map<V2D> fromData() {
        return null;
    }
}
