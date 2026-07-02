package tmw.controller.world;

import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.entities.EnemyController;
import tmw.controller.item.ItemControllerImpl;
import tmw.model.item.Item;
import tmw.model.item.consumable.HealingItem;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.equipment.Equipment;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.WhiteSugarPowerUp;
import tmw.model.objects.Obstacle;
import utils.ProportionalPosUtils;
import utils.Rooms;

/**
 * This class implements {@link WorldDispenser}, represents room n3 of the game.
 * Check {@link Room1} for better description.
 *
 */
public class Room3 extends AbstractRoom {

    private static final P2d ABBRACCIO1 = new P2d(750, 550);
    private static final P2d CHOCOLATE = new P2d(50, 100);
    private static final P2d WHOLE = new P2d(350, 300);
    private static final P2d WHITE_SUGAR = new P2d(100, 200);
    private static final P2d STELLA1 = new P2d(100, 350);
    private static final P2d STELLA2 = new P2d(350, 500);
    private static final P2d STELLA3 = new P2d(450, 150);
    private static final P2d STELLA4 = new P2d(600, 350);
    private static final P2d OBS1 = new P2d(150, 100);
    private static final P2d OBS2 = new P2d(50, 250);
    private static final P2d OBS3 = new P2d(82, 250);
    private static final P2d OBS4 = new P2d(200, 500);
    private static final P2d OBS5 = new P2d(350, 350);
    private static final P2d OBS6 = new P2d(500, 400);
    private static final P2d OBS7 = new P2d(500, 432);
    private static final P2d OBS8 = new P2d(500, 464);
    private static final P2d OBS9 = new P2d(650, 150);
    private static final P2d OBS10 = new P2d(650, 180);

    /**
     * Public constructor.
     * 
     * @param worldController {@link WorldController} world controller reference
     * @param type            {@link Rooms} type of the room
     */
    public Room3(final WorldController worldController, final Rooms type) {
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
                super.getWorldController().getFactory().createStelle(
                        ProportionalPosUtils.propDimention(STELLA1.getX(), STELLA1.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createStelle(
                        ProportionalPosUtils.propDimention(STELLA2.getX(), STELLA2.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createStelle(
                        ProportionalPosUtils.propDimention(STELLA3.getX(), STELLA3.getY(), super.getGameRes()),
                        new V2d(0, 0))));
        super.getEntities().add(new EnemyController(super.getWorldController(),
                super.getWorldController().getFactory().createStelle(
                        ProportionalPosUtils.propDimention(STELLA4.getX(), STELLA4.getY(), super.getGameRes()),
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
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS8.getX(), OBS8.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles().add(new Obstacle(
                ProportionalPosUtils.propDimention(OBS9.getX(), OBS9.getY(), super.getGameRes()), super.getGameRes()));
        super.getObstacles()
                .add(new Obstacle(ProportionalPosUtils.propDimention(OBS10.getX(), OBS10.getY(), super.getGameRes()),
                        super.getGameRes()));

        super.getItems().add(new ItemControllerImpl<Item>(new HealingItem(HealingItemType.WHOLE_MILK,
                ProportionalPosUtils.propDimention(WHOLE.getX(), WHOLE.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(new WhiteSugarPowerUp(
                ProportionalPosUtils.propDimention(WHITE_SUGAR.getX(), WHITE_SUGAR.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));
        super.getItems()
                .add(new ItemControllerImpl<Item>(
                        new Equipment(EquipmentType.CHOCOLATE, ProportionalPosUtils.propDimention(CHOCOLATE.getX(),
                                CHOCOLATE.getY(), super.getGameRes()), super.getGameRes()),
                        super.getWorldController()));
    }

}
