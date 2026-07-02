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
 * This class implements {@link WorldDispenser} so it's a specific strategy
 * operation. Multiple gameRooms made up a gameLevel. Each room is different
 * from another one tanks to ceateEntities method.
 * 
 * This class represents first room of the game.
 * 
 * @version 1.2
 *
 */
public class Room1 extends AbstractRoom {

    private static final P2d ABBRACCIO1 = new P2d(750, 50);
    private static final P2d ABBRACCIO2 = new P2d(300, 550);
    private static final P2d COFFEE = new P2d(300, 100);
    private static final P2d LACTOSE_FREE = new P2d(400, 550);
    private static final P2d WHITE_SUGAR = new P2d(100, 400);
    private static final P2d STELLA1 = new P2d(150, 150);
    private static final P2d STELLA2 = new P2d(650, 200);
    private static final P2d STELLA3 = new P2d(100, 550);
    private static final P2d STELLA4 = new P2d(700, 450);
    private static final P2d OBS1 = new P2d(150, 100);
    private static final P2d OBS2 = new P2d(182, 100);
    private static final P2d OBS3 = new P2d(214, 100);
    private static final P2d OBS4 = new P2d(246, 100);
    private static final P2d OBS5 = new P2d(50, 300);
    private static final P2d OBS6 = new P2d(400, 50);
    private static final P2d OBS7 = new P2d(340, 500);
    private static final P2d OBS8 = new P2d(650, 364);
    private static final P2d OBS9 = new P2d(650, 300);
    private static final P2d OBS10 = new P2d(650, 332);
    private static final P2d OBS11 = new P2d(400, 200);
    private static final P2d OBS12 = new P2d(400, 230);
    private static final P2d OBS13 = new P2d(400, 260);

    /**
     * Public constructor.
     * 
     * @param worldController {@link WorldController} world controller reference
     * @param type            {@link Rooms} type of the room
     */
    public Room1(final WorldController worldController, final Rooms type) {
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
        super.getObstacles()
                .add(new Obstacle(ProportionalPosUtils.propDimention(OBS11.getX(), OBS11.getY(), super.getGameRes()),
                        super.getGameRes()));
        super.getObstacles()
                .add(new Obstacle(ProportionalPosUtils.propDimention(OBS12.getX(), OBS12.getY(), super.getGameRes()),
                        super.getGameRes()));
        super.getObstacles()
                .add(new Obstacle(ProportionalPosUtils.propDimention(OBS13.getX(), OBS13.getY(), super.getGameRes()),
                        super.getGameRes()));

        super.getItems().add(new ItemControllerImpl<Item>(new WhiteSugarPowerUp(
                ProportionalPosUtils.propDimention(WHITE_SUGAR.getX(), WHITE_SUGAR.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(new Equipment(EquipmentType.COFFEE,
                ProportionalPosUtils.propDimention(COFFEE.getX(), COFFEE.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(
                new HealingItem(HealingItemType.LACTOSE_FREE_MILK, ProportionalPosUtils.propDimention(
                        LACTOSE_FREE.getX(), LACTOSE_FREE.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));
    }

}
