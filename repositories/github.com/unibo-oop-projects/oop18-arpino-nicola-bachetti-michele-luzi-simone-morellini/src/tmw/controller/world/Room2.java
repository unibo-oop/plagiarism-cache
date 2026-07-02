package tmw.controller.world;

import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.entities.EnemyController;
import tmw.controller.item.ItemControllerImpl;
import tmw.model.item.Item;
import tmw.model.item.consumable.HealingItem;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.powerup.SugarCanePowerUp;
import tmw.model.objects.Obstacle;
import utils.ProportionalPosUtils;
import utils.Rooms;

/**
 * This class implements {@link WorldDispenser}, represents room n2 of the game.
 * Check {@link Room1} for better description.
 *
 */
public class Room2 extends AbstractRoom {

    private static final P2d ABBRACCIO1 = new P2d(100, 500);
    private static final P2d ABBRACCIO2 = new P2d(700, 100);
    private static final P2d ABBRACCIO3 = new P2d(450, 400);
    private static final P2d STELLA1 = new P2d(200, 250);
    private static final P2d STELLA2 = new P2d(400, 450);;
    private static final P2d OBS1 = new P2d(150, 100);
    private static final P2d OBS2 = new P2d(250, 400);
    private static final P2d OBS3 = new P2d(282, 400);
    private static final P2d OBS4 = new P2d(314, 400);
    private static final P2d OBS5 = new P2d(400, 200);
    private static final P2d OBS6 = new P2d(400, 232);
    private static final P2d OBS7 = new P2d(600, 500);
    private static final P2d SKIMMED = new P2d(250, 350);
    private static final P2d SUGAR_CANE = new P2d(50, 250);
    private static final P2d LACTOSE_FREE = new P2d(500, 100);

    /**
     * Public constructor.
     * 
     * @param worldController {@link WorldController} world controller reference
     * @param type            {@link Rooms} type of the room
     */
    public Room2(final WorldController worldController, final Rooms type) {
        super(worldController, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createEntities() {

        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createAbbraccio(
                        ProportionalPosUtils.propDimention(ABBRACCIO1.getX(), ABBRACCIO1.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createAbbraccio(
                        ProportionalPosUtils.propDimention(ABBRACCIO2.getX(), ABBRACCIO2.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createAbbraccio(
                        ProportionalPosUtils.propDimention(ABBRACCIO3.getX(), ABBRACCIO3.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createStelle(
                        ProportionalPosUtils.propDimention(STELLA1.getX(), STELLA1.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createStelle(
                        ProportionalPosUtils.propDimention(STELLA2.getX(), STELLA2.getY(), super.getGameRes()),
                        new V2d(0, 0))));

        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS1.getX(), OBS1.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS2.getX(), OBS2.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS3.getX(), OBS3.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS4.getX(), OBS4.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS5.getX(), OBS5.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS6.getX(), OBS6.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS7.getX(), OBS7.getY(), super.getGameRes()), super.getGameRes()));
        super.getItems().add(new ItemControllerImpl<Item>(
                new HealingItem(HealingItemType.LACTOSE_FREE_MILK, ProportionalPosUtils.propDimention(
                        LACTOSE_FREE.getX(), LACTOSE_FREE.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(new HealingItem(HealingItemType.SKIMMED_MILK,
                ProportionalPosUtils.propDimention(SKIMMED.getX(), SKIMMED.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(new SugarCanePowerUp(
                ProportionalPosUtils.propDimention(SUGAR_CANE.getX(), SUGAR_CANE.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));

    }

}
