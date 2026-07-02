package vg.model.levels;

import vg.model.entity.boss.BossImpl;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.entity.dynamicEntity.enemy.Boss;
import vg.model.entity.dynamicEntity.enemy.Mosquitoes;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Enum for levels and what entities to generate, used by {@link vg.model.MapFactory}.
 */
public enum LEVEL {
    /**
     * Self-explanatory.
     */
    LV1, LV2, LV3, LV4;

    public Boss getBoss() {

        if (this == LEVEL.LV1) {
            return lv1boss;
        } else {
            return lv2boss;
        }

    }

    public Set<DynamicEntity> getEnemies() {
        Set<DynamicEntity> rt = new HashSet<>();
        Random r = new Random();
        switch (LEVEL.this) {
            case LV1:
            case LV2:
            case LV3:
            case LV4:
            default:
                while (rt.size() < 5) {
                    var t = new Mosquitoes(new V2D(r.nextInt(200), r.nextInt(150)), new V2D(r.nextInt(7), r.nextInt(7)), 3, Shape.CIRCLE, MassTier.LOW);
                    if (rt.stream().noneMatch(e -> e.isInShape(t)) && !getBoss().isInShape(t)) {
                        rt.add(t);
                    }
                }
                return rt;
        }
    }
    private static BossImpl lv1boss = new BossImpl(new V2D(2,2));
    private static BossImpl lv2boss = new BossImpl(new V2D(2,2));
    private static BossImpl lv3boss = new BossImpl(new V2D(2,2));





}

