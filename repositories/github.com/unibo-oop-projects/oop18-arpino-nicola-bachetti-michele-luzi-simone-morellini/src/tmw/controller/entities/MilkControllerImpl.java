package tmw.controller.entities;

import java.util.Optional;

import tmw.common.CharacterStates;
import tmw.common.Dim2D;
import tmw.common.EntityDirection;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.input.PlayerInputController;
import tmw.controller.item.AbstractItemController;
import tmw.controller.world.WorldController;
import tmw.model.entities.MilkEntity;
import tmw.model.inventory.Inventory;
import tmw.model.item.Item;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.PowerUpType;

/**
 * This class represents a controller for the main character.
 */
public class MilkControllerImpl implements MilkController {

    private static final int SECOND_IN_MILLIS = 1000;

    private static final int GOING_DOWN = 0;
    private static final int GOING_LEFT = 1;
    private static final int GOING_UP = 2;
    private static final int GOING_RIGHT = 3;

    private final WorldController worldController;
    private final MilkEntity milk;
    private final Inventory inventory;
    private final PlayerInputController input;
    private final IntersectChecker checker;
    private boolean isEquipmentUsed;
    private boolean isSpeedPowerUpUsed;
    private boolean isShootPowerUpUsed;

    /**
     * Construct a new controller for the main character.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param pos             - the initial position of the main character as a
     *                        {@link P2d}
     * @param input           - the input class that is used to read the input of
     *                        the user to update the information of the main
     *                        controller
     * @param inventory       - the inventory class that handle the storing and
     *                        using of the items
     */
    public MilkControllerImpl(final WorldController worldController, final P2d pos, final PlayerInputController input,
            final Inventory inventory) {

        this.worldController = worldController;
        this.milk = this.worldController.getFactory().createMilk(pos, new V2d(0, 0));
        this.inventory = inventory;
        this.input = input;
        this.isEquipmentUsed = false;
        this.isSpeedPowerUpUsed = false;
        this.isShootPowerUpUsed = false;

        this.checker = new IntersectChecker(pos, milk.getDimension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (!this.getEntity().isThisTheEnd()) {
            final CharacterStates state = this.input.update();

            Optional<P2d> updatePosition = Optional.empty();

            if (state.equals(CharacterStates.MOVE)) {
                this.getEntity().setVel(this.input.getVel());
                final P2d newPosition = this.milk.getCurrentPos()
                        .sum(this.milk.getCurrentVel().getNormalized().mul(this.milk.getSpeed()));

                updatePosition = this.checker.isPositionClear(newPosition, this.worldController)
                        ? Optional.of(newPosition)
                        : Optional.empty();

                this.checkItem();
            }

            if (state.equals(CharacterStates.SHOOT) && this.getEntity().readyToShoot()) {
                this.shoot(this.input.getVel());
            }

            if (state.equals(CharacterStates.INVENTORY) && this.inventory.getItem(input.getItemPos()).isPresent()
                    && !isItemUsed(this.inventory.getItem(input.getItemPos()).get())) {
                this.inventory.getItem(input.getItemPos()).ifPresent(x -> x.useItem(this.milk));
                this.inventory.removeItem(input.getItemPos());
            }

            this.milk.update(updatePosition);

        } else {
            this.delete();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double vx = this.milk.getCurrentVel().getX();
        final double vy = this.milk.getCurrentVel().getY();
        int dir = GOING_DOWN;

        if (vx > 0) {
            if (vx >= Math.abs(vy)) {
                dir = GOING_RIGHT;
            } else if (vy > 0) {
                dir = GOING_DOWN;
            } else {
                dir = GOING_UP;
            }
        } else {
            if (Math.abs(vx) >= Math.abs(vy)) {
                dir = GOING_LEFT;
            } else if (vy > 0) {
                dir = GOING_DOWN;
            } else {
                dir = GOING_UP;
            }
        }

        this.worldController.getView().render(this.milk, EntityDirection.getDirection(dir), this.milk.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() {
        this.worldController.killPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resizeEntity(final Dim2D dimension) {
        this.getEntity().resetDefaultDimension(dimension);
        this.checker.resetDefaultDimension(this.getEntity().getDimension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHp() {
        return this.milk.getCurrentHealth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHp() {
        return this.milk.getMaxHp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MilkEntity getEntity() {
        return this.milk;
    }

    /**
     * This method perform the shoot action of the entity.
     * 
     * @param shootDirection - the direction that the bullet shot has to follow as a
     *                       {@link V2d}
     */
    private void shoot(final V2d shootDirection) {
        this.milk.shoot();
        this.worldController.addBullet(new CharacterBulletController(this.worldController,
                this.milk.getCentralPosition(), shootDirection, this.milk.getDamage()));
    }

    /**
     * this method check if in the current player position there is a collectible
     * item.
     */
    private void checkItem() {
        for (final AbstractItemController item : this.worldController.getItemLoaded()) {
            if (item.getItem().intersect(this.milk) && !this.inventory.isFull()) {
                this.inventory.addItem(item.getItem());
                this.worldController.removeItem(item);
                this.worldController.incrementScore(item.getItem().getPoints());
            }
        }
    }

    /**
     * This method check if there is already an item in use of the same type of the
     * parameter type.
     * 
     * @param item item that player want to use
     * @return true if there is an object in use, false otherwise
     */
    private boolean isItemUsed(final Item item) {
        if (item.getName().equals(EquipmentType.CHOCOLATE.getName())
                || item.getName().equals(EquipmentType.COFFEE.getName())) {
            if (!this.isEquipmentUsed) {
                this.isEquipmentUsed = true;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(Math.max(EquipmentType.CHOCOLATE.getDuration(), EquipmentType.COFFEE.getDuration())
                                    * SECOND_IN_MILLIS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        isEquipmentUsed = false;
                    }
                }.start();
                return false;
            } else {
                return true;
            }
        }
        if (item.getName().equals(PowerUpType.SUGAR_CANE.getName())) {
            if (!isSpeedPowerUpUsed) {
                this.isSpeedPowerUpUsed = true;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(PowerUpType.SUGAR_CANE.getDuration() * SECOND_IN_MILLIS);
                            isSpeedPowerUpUsed = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                return false;
            } else {
                return true;
            }
        }
        if (item.getName().equals(PowerUpType.WHITE_SUGAR.getName())) {
            if (!isShootPowerUpUsed) {
                this.isShootPowerUpUsed = true;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(PowerUpType.WHITE_SUGAR.getDuration() * SECOND_IN_MILLIS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        isShootPowerUpUsed = false;
                    }
                }.start();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
