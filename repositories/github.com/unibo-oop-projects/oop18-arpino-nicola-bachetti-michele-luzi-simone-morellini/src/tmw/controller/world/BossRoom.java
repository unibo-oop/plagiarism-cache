package tmw.controller.world;

import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.entities.BossController;
import tmw.controller.item.ItemControllerImpl;
import tmw.model.item.Item;
import tmw.model.item.consumable.HealingItem;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.equipment.Equipment;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.SugarCanePowerUp;
import utils.ProportionalPosUtils;
import utils.Rooms;

/**
 * This Class implements {@link WorldDispenser}, represent the boss room of the
 * game. Check {@link Room1} for better description.
 *
 */
public class BossRoom extends AbstractRoom {

    private static final P2d WHOLE = new P2d(350, 300);
    private static final P2d SUGAR_CANE = new P2d(50, 250);
    private static final P2d COFFEE = new P2d(200, 400);

    private final P2d bossPos;

    /**
     * Public constructor.
     * 
     * @param worldController {@link WorldController} world controller reference
     * @param type            {@link Rooms} type of the room
     */
    public BossRoom(final WorldController worldController, final Rooms type) {
        super(worldController, type);
        this.bossPos = new P2d(worldController.getGameWorld().getWorldArea().getWidth() / 2,
                worldController.getGameWorld().getWorldArea().getHeight() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createEntities() {
        super.getEntities().add(new BossController(super.getWorldController(),
                super.getWorldController().getFactory().createBoss(bossPos, new V2d(0, 0))));
        super.getItems().add(new ItemControllerImpl<Item>(new HealingItem(HealingItemType.WHOLE_MILK,
                ProportionalPosUtils.propDimention(WHOLE.getX(), WHOLE.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(new SugarCanePowerUp(
                ProportionalPosUtils.propDimention(SUGAR_CANE.getX(), SUGAR_CANE.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(new Equipment(EquipmentType.COFFEE,
                ProportionalPosUtils.propDimention(COFFEE.getX(), COFFEE.getY(), super.getGameRes()),
                super.getGameRes()), super.getWorldController()));

    }
}
