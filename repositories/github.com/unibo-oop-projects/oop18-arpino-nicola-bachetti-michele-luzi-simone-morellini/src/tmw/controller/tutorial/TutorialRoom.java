package tmw.controller.tutorial;

import javafx.application.Platform;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.entities.EnemyController;
import tmw.controller.item.ItemControllerImpl;
import tmw.controller.world.AbstractRoom;
import tmw.controller.world.WorldController;
import tmw.model.item.Item;
import tmw.model.item.consumable.HealingItem;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.equipment.Equipment;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.SugarCanePowerUp;
import tmw.model.item.powerup.WhiteSugarPowerUp;
import tmw.model.objects.VerticalTrigger;
import utils.ProportionalPosUtils;
import utils.Rooms;

/**
 * Class to represent the room of the tutorial, where the entities that populate
 * it are created.
 * <p>
 * Implementation of {@link WorldDispenser}
 */
public class TutorialRoom extends AbstractRoom {

    private static final P2d ABBRACCIO_POSITION = new P2d(1700, 300);
    private static final P2d SKIMMED_POSITION = new P2d(300, 200);
    private static final P2d CHOCOLATE_POSITION = new P2d(500, 400);
    private static final P2d COFFEE_POSITION = new P2d(550, 350);
    private static final P2d SUGAR_POSITION = new P2d(700, 150);
    private static final P2d SUGAR_CANE_POSITION = new P2d(400, 300);
    private static final P2d DOOR_POSSITION = new P2d(1700, 240);
    private static final int TRIGGER1 = 150;
    private static final int TRIGGER2 = 900;
    private static final int TRIGGER3 = 1100;
    private static final int TRIGGER4 = 1200;
    private static final int TRIGGER5 = 300;
    private static final int TRIGGER6 = 400;
    private static final int TRIGGER7 = 500;
    private static final int TRIGGER8 = 700;
    private static final int TRIGGER9 = 1400;
    private static final int DURATION = 10000;

    /**
     * @param worldController {@link WorldController}The controller of the world
     * @param type            {@link Rooms} type of the room
     */
    public TutorialRoom(final WorldController worldController, final Rooms type) {
        super(worldController, type);
        super.getEscapeDoor().setPos(DOOR_POSSITION);
        this.writeOnHud("Move with WASD");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createEntities() {
        super.getItems().add(new ItemControllerImpl<HealingItem>(
                new HealingItem(HealingItemType.SKIMMED_MILK, ProportionalPosUtils.propDimention(
                        SKIMMED_POSITION.getX(), SKIMMED_POSITION.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(
                new Equipment(EquipmentType.CHOCOLATE, ProportionalPosUtils.propDimention(CHOCOLATE_POSITION.getX(),
                        CHOCOLATE_POSITION.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));
        super.getItems()
                .add(new ItemControllerImpl<Item>(
                        new Equipment(EquipmentType.COFFEE, ProportionalPosUtils.propDimention(COFFEE_POSITION.getX(),
                                COFFEE_POSITION.getY(), super.getGameRes()), super.getGameRes()),
                        super.getWorldController()));
        super.getItems()
                .add(new ItemControllerImpl<Item>(
                        new WhiteSugarPowerUp(ProportionalPosUtils.propDimention(SUGAR_POSITION.getX(),
                                SUGAR_POSITION.getY(), super.getGameRes()), super.getGameRes()),
                        super.getWorldController()));
        super.getItems().add(new ItemControllerImpl<Item>(
                new SugarCanePowerUp(ProportionalPosUtils.propDimention(SUGAR_CANE_POSITION.getX(),
                        SUGAR_CANE_POSITION.getY(), super.getGameRes()), super.getGameRes()),
                super.getWorldController()));

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER4, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                getEntities()
                        .add(new EnemyController(getWorldController(),
                                getWorldController().getFactory().createAbbraccio(
                                        ProportionalPosUtils.propDimention(ABBRACCIO_POSITION.getX(),
                                                ABBRACCIO_POSITION.getY(), getGameRes()),
                                        new V2d(0, 0))));
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER2, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Press 1,2,3,4,5 to use the items");
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER1, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Interact with items to collect them");
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER3, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Shoot with the arrow keys");
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER5, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Milk briks restore life");
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER6, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Sugar cane increases the speed");
            }
        });
        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER7, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Chocolate and coffee increase the damage");
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER8, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Sugar increase the frequency of bullets");
            }
        });

        super.getTriggers().add(new VerticalTrigger(
                ProportionalPosUtils.propTrigger(TRIGGER9, super.getWorldController().getView().getGameRes()),
                super.getWorldController().getView().getGameRes()) {
            @Override
            public void activateTrigger() {
                writeOnHud("Enter the door to continue");
            }
        });
    }

    /**
     * 
     * @param string
     */
    private void writeOnHud(final String string) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                getWorldController().getHud().setInfoLabel(string);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(DURATION);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Platform.runLater(new Runnable() {

                            @Override
                            public void run() {
                                if (getWorldController().getHud().getInfoLabel().equals(string)) {
                                    getWorldController().getHud().setInfoLabel("");
                                }
                            }
                        });
                    }
                }.start();
            }
        });
    }
}
