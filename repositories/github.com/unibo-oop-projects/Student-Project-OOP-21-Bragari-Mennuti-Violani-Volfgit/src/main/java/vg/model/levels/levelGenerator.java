package vg.model.levels;

import vg.model.entity.boss.BossImpl;
import vg.model.Map;
import vg.model.MapImpl;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.entity.dynamicEntity.enemy.Boss;
import vg.model.entity.dynamicEntity.enemy.EmptyBoss;
import vg.model.entity.dynamicEntity.enemy.Mosquitoes;
import vg.model.entity.dynamicEntity.player.BasePlayer;
import vg.model.entity.staticEntity.FixedSquare;
import vg.model.entity.staticEntity.StaticEntity;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class levelGenerator {

    /**
     * The borders are the same for every level at the beginning.
     */
    private final Set<V2D> defaultBorders = Stream.concat(
            Stream.concat(
            IntStream.rangeClosed(0, MapImpl.MAXBORDERX).boxed().flatMap(e -> Stream.of(new V2D(e,0))),
            IntStream.rangeClosed(0, MapImpl.MAXBORDERY).boxed().flatMap(e -> Stream.of(new V2D(MapImpl.MAXBORDERX,e)))),
            Stream.concat(
            IntStream.rangeClosed(0, MapImpl.MAXBORDERX).boxed().flatMap(e -> Stream.of(new V2D(MapImpl.MAXBORDERX-e,MapImpl.MAXBORDERY))),
            IntStream.rangeClosed(0, MapImpl.MAXBORDERY).boxed().flatMap(e -> Stream.of(new V2D(0,MapImpl.MAXBORDERY-e))))
    ).distinct().collect(Collectors.toSet());

    /**
     * Lv1.
     */
    private final Boss bossLv1 = new EmptyBoss(new V2D(100, 100), new V2D(1, 1), 3,  Shape.CIRCLE, MassTier.HIGH);

    private final Set<StaticEntity> ssLv1 = new HashSet<StaticEntity>(Stream.of(
            new FixedSquare(new V2D(100,75), 5),

            new FixedSquare(new V2D(6,145), 5),
            new FixedSquare(new V2D(194,145), 5),
            new FixedSquare(new V2D(50,145), 5),
            new FixedSquare(new V2D(100,145), 5),
            new FixedSquare(new V2D(150,145), 5)).collect(Collectors.toSet())
    );
    private final Set<DynamicEntity> dsLv1 = generatesEnemies(ssLv1, bossLv1, 4 );
    private final Map<V2D> lv1 = new MapImpl(BasePlayer.newPlayer(new V2D(0, 0)),
            bossLv1,
            ssLv1,
            dsLv1,
            defaultBorders);

    private final Map<V2D> lv2 = new MapImpl(BasePlayer.newPlayer(new V2D(0, 0)),
            bossLv1,
            ssLv1,
            dsLv1,
            defaultBorders);;
    private final Map<V2D> lv3 = new MapImpl(BasePlayer.newPlayer(new V2D(0, 0)),
            bossLv1,
            ssLv1,
            dsLv1,
            defaultBorders);;


    /**
     * Generates n {@link Mosquitoes} that are in borders and not colliding
     * with other entities on the map.
     * @param ss the set of {@link StaticEntity}
     * @param boss the boss of the map
     * @param nEnemies number of enemies to generate
     * @return a {@link Set<DynamicEntity>}
     */
    private Set<DynamicEntity> generatesEnemies(Set<StaticEntity> ss, Boss boss, int nEnemies) {
        var r = new Random();
        Set<DynamicEntity> rt = new HashSet<>();
        while (rt.size() < nEnemies) {
            var t = new Mosquitoes(new V2D(r.nextInt(MapImpl.MAXBORDERX), r.nextInt(MapImpl.MAXBORDERY)), new V2D(r.nextInt(2), r.nextInt(2)), 3, Shape.SQUARE, MassTier.LOW);
            if (rt.stream().noneMatch(e -> e.isInShape(t)) &&
                    !boss.isInShape(t) &&
                    defaultBorders.stream().noneMatch(t::isInShape) &&
                    !t.isInShape(BasePlayer.newPlayer(new V2D(0, 0)))) {
                if(!t.getSpeed().equals(new V2D(0,0))) {
                    rt.add(t);
                }
            }
        }
        return rt;
    }

    /**
     * Serialize MapImpl with default params defined here.
     * @throws IOException exception with IO
     */

    public void serializeDefaults() throws IOException {
        FileOutputStream fileOutLv1 = new FileOutputStream("1");
        ObjectOutputStream lv1 = new ObjectOutputStream(fileOutLv1);
        lv1.writeObject(this.lv1);
        lv1.flush();
        lv1.close();
        //do this for every level (also to find a better way)
        FileOutputStream fileOutLv2 = new FileOutputStream("2");
        ObjectOutputStream lv2 = new ObjectOutputStream(fileOutLv2);
        lv2.writeObject(this.lv2);
        lv2.flush();
        lv2.close();

        FileOutputStream fileOutLv3 = new FileOutputStream("3");
        ObjectOutputStream lv3 = new ObjectOutputStream(fileOutLv3);
        lv3.writeObject(this.lv3);
        lv3.flush();
        lv3.close();
    }

    /**
     * Used to serialize a "state" of the {@link MapImpl} and use it
     * as a save file. An actual implementation needs a wrapper as
     * {@link MapImpl} does not contain the level.
     * @param map
     * @throws IOException
     */
    public void serializeState(final Map<V2D> map) throws IOException {
        FileOutputStream out = new FileOutputStream("save");
        ObjectOutputStream oOut = new ObjectOutputStream(out);
        oOut.writeObject(map);
        oOut.flush();
        oOut.close();
    }

    /**
     * Used to deserialize a {@link MapImpl} serialized class and
     * returns it.
     * @param lv the level to deserialize
     * @return {@link MapImpl}
     * @throws IOException if the file is not found
     * @throws ClassNotFoundException if the file is corrupted
     */
    public Map<V2D> deserializeLevel(final String lv) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(lv);
        ObjectInputStream oIn = new ObjectInputStream(in);
        var map = (Map<V2D>)oIn.readObject();
        oIn.close();
        return map;
    }

    /**
     * Used to deserialize a saved file. An actual usable method
     * needs a wrapper. {@link #serializeState(Map)}.
     * @return {@link Map}
     * @throws IOException if the save file does not exist
     * @throws ClassNotFoundException if the file is corrputed
     */
    public Map<V2D> deserializeSaved() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("save");
        ObjectInputStream oIn = new ObjectInputStream(in);
        var map = (Map<V2D>)oIn.readObject();
        oIn.close();
        return map;
    }
}
