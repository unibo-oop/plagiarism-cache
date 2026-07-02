package model.environment;

import java.util.ArrayList;
import java.util.List;
import model.animated.Animated;
import model.animated.Enemy;
import model.animated.EnemyFactory;
import model.animated.EnemyFactoryImpl;
import model.animated.EntityStats;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import model.hitbox.RectangularHitBox;
import model.inanimated.Button;
import model.inanimated.ButtonImpl;
import model.inanimated.DamageUpImpl;
import model.inanimated.Door;
import model.inanimated.DoorImpl;
import model.inanimated.HeartImpl;
import model.inanimated.Inanimated;
import model.inanimated.RangeUpImpl;
import model.inanimated.VelocityUpImpl;
import model.inanimated.Wall;
import model.inanimated.WallImpl;
import model.room.Room;
import model.room.RoomFactory;
import model.room.RoomFactoryImpl;
import model.utility.ModelUtility;
import model.utility.ProportionUtility;
import model.utility.RoomEnum;
import utility.ImageType;

/**
 * World Environment Implement.
 */
public class WorldEnvironmentImpl implements WorldEnvironment {

    private final List<Room> listRoom = new ArrayList<>();
    private final RoomFactory rf = new RoomFactoryImpl();
    private final HitBox hbRoom = new RectangularHitBox(ProportionUtility.getWallVerticalWidth(),
            ProportionUtility.getWallHorizontalHeight(), ProportionUtility.getHeight(), ProportionUtility.getWidth());
    private final HitBox hbDoorr = new RectangularHitBox(
            ModelUtility.getWorldWidth() - ProportionUtility.getWidthDoor(),
            ModelUtility.getWorldHeight() / 2 - ProportionUtility.getHeightDoor() / 2,
            ProportionUtility.getHeightDoor(), ProportionUtility.getWidthDoor());
    private final HitBox hbDoorl = new RectangularHitBox(0,
            ModelUtility.getWorldHeight() / 2 - ProportionUtility.getHeightDoor() / 2,
            ProportionUtility.getHeightDoor() + ProportionUtility.getWidthDoor(), ProportionUtility.getWidthDoor());
    private final HitBox hbBtn = new CircleHitBox(ModelUtility.getWorldWidth() / 2, ModelUtility.getWorldHeight() / 2,
            ProportionUtility.getRadiusButton());
    private final Button bt = new ButtonImpl(hbBtn, false);
    private Door rightDoorFromMainToShop;
    private Door leftDoorFromShopToMain;
    private Door rightDoorFromShopToBoss;
    private Door rightDoorFromBossToShop;
    private final List<Wall> lw = new ArrayList<>();
    private final List<Inanimated> items = new ArrayList<>();
    private Animated boss;
    private boolean considerDoor;

    /**
     * Create rooms. The first Room of the list is the MainRoom. The second Room of
     * the list is the ShopRoom. The third Room of the list is the BossRoom.
     */
    @Override
    public List<Room> createWorld() {
        createWalls();
        listRoom.add(createMainRoom());
        listRoom.add(createShopRoom());
        listRoom.add(createBossRoom());
        return listRoom;
    }

    /**
     * @return create main room.
     */
    private Room createMainRoom() {
        final List<Wall> lwMain = new ArrayList<>();
        this.rightDoorFromMainToShop = new DoorImpl(hbDoorr, false, RoomEnum.SHOPROOM,
                ImageType.RIGHT_SHOP_DOOR_LOCKED);
        final List<Door> ld = new ArrayList<>();
        lwMain.addAll(this.lw);
        lwMain.addAll(wallMainRoom());
        ld.add(this.rightDoorFromMainToShop);
        return rf.createMainRoom(hbRoom, ld, bt, lwMain);
    }

    /**
     * @return create shop room.
     */
    private Room createShopRoom() {
        this.leftDoorFromShopToMain = new DoorImpl(hbDoorl, false, RoomEnum.MAINROOM, ImageType.LEFT_SHOP_DOOR_LOCKED);
        this.rightDoorFromShopToBoss = new DoorImpl(hbDoorr, true, RoomEnum.MAINROOM,
                ImageType.RIGHT_SHOP_DOOR_UNLOCKED);
        final List<Door> ld = new ArrayList<>();
        ld.add(this.leftDoorFromShopToMain);
        ld.add(this.rightDoorFromShopToBoss);
        final HitBox hbVelocityUp = new RectangularHitBox(ModelUtility.getWorldWidth() / 2,
                ModelUtility.getWorldHeight() / 2 - ProportionUtility.getPowerUpHeight() / 2
                        - ProportionUtility.getUnderShopY(),
                ProportionUtility.getPowerUpHeight(), ProportionUtility.getPowerUpWidth());
        final HitBox hbHeart = new RectangularHitBox(
                ModelUtility.getWorldWidth() / 2 - ProportionUtility.getUnderShopX(),
                ModelUtility.getWorldHeight() / 2 - ProportionUtility.getPowerUpHeight() / 2
                        - ProportionUtility.getUnderShopY(),
                ProportionUtility.getPowerUpHeight(), ProportionUtility.getPowerUpWidth());
        final HitBox hbDamageUp = new RectangularHitBox(
                ModelUtility.getWorldWidth() / 2 - ProportionUtility.getUnderShopX() * 2,
                ModelUtility.getWorldHeight() / 2 - ProportionUtility.getPowerUpHeight() / 2
                        - ProportionUtility.getUnderShopY(),
                ProportionUtility.getPowerUpHeight(), ProportionUtility.getPowerUpWidth());
        final HitBox hbRangeUp = new RectangularHitBox(
                ModelUtility.getWorldWidth() / 2 + ProportionUtility.getUnderShopX(),
                ModelUtility.getWorldHeight() / 2 - ProportionUtility.getPowerUpHeight() / 2
                        - ProportionUtility.getUnderShopY(),
                ProportionUtility.getPowerUpHeight(), ProportionUtility.getPowerUpWidth());
        items.add(new DamageUpImpl(hbDamageUp, true));
        items.add(new VelocityUpImpl(hbVelocityUp, true));
        items.add(new HeartImpl(hbHeart, true));
        items.add(new RangeUpImpl(hbRangeUp, true));
        return rf.createShopRoom(hbRoom, ld, items, lw);
    }

    /**
     * The Boss stays in the right spot and shots to the left.
     * 
     * @return create boss room.
     */
    private Room createBossRoom() {
        final List<Wall> wallBossRoom = new ArrayList<>();
        final EnemyFactory e = new EnemyFactoryImpl();
        this.rightDoorFromBossToShop = new DoorImpl(hbDoorl, false, RoomEnum.MAINROOM, ImageType.LEFT_BOSS_DOOR);
        final List<Door> ld = new ArrayList<>();
        ld.add(this.rightDoorFromBossToShop);
        wallBossRoom.addAll(lw);
        wallBossRoom.addAll(wallBossRoom());
        final HitBox bossHB = new CircleHitBox(ProportionUtility.getWidth(), ProportionUtility.getHeight() / 2,
                EntityStats.BOSS.getEntityRadius());
        this.boss = e.createBoss(bossHB);
        return rf.createBossRoom(hbRoom, ld, (Enemy) this.boss, wallBossRoom);
    }

    /**
     * create walls.
     */
    private void createWalls() {
        for (double i = 0; i < ModelUtility.getWorldWidth(); i += ProportionUtility.getWallHorizontalWidth()) {
            lw.add(new WallImpl(new RectangularHitBox(i, 0, ProportionUtility.getWallHorizontalHeight(),
                    ProportionUtility.getWallHorizontalWidth()), false, ImageType.MAP_VERTICAL_BORDER));
            lw.add(new WallImpl(
                    new RectangularHitBox(i,
                            ModelUtility.getWorldHeight() - ProportionUtility.getWallHorizontalHeight(),
                            ProportionUtility.getWallHorizontalHeight(), ProportionUtility.getWallHorizontalWidth()),
                    false, ImageType.MAP_VERTICAL_BORDER));
        }
        for (double i = ProportionUtility.getWallHorizontalHeight(); i < ModelUtility.getWorldHeight()
                - ProportionUtility.getWallHorizontalHeight(); i += ProportionUtility.getWallVerticalHeight()) {
            if ((ModelUtility.getWorldHeight() / 2 - ProportionUtility.getHeightDoor() / 2) - i < ModelUtility
                    .getEpsilon() && !this.considerDoor) {
                this.considerDoor = true;
                i += ProportionUtility.getHeightDoor();
            }
            lw.add(new WallImpl(new RectangularHitBox(0, i, ProportionUtility.getWallVerticalHeight(),
                    ProportionUtility.getWallVerticalWidth()), false, ImageType.MAP_HORIZONTAL_BORDER));
            lw.add(new WallImpl(
                    new RectangularHitBox(ModelUtility.getWorldWidth() - ProportionUtility.getWallVerticalWidth(), i,
                            ProportionUtility.getWallVerticalHeight(), ProportionUtility.getWallVerticalWidth()),
                    false, ImageType.MAP_HORIZONTAL_BORDER));
        }
    }

    /**
     * @return rightDoorFromMainToShop.
     */
    @Override
    public Door getRightDoorFromMainToShop() {
        return this.rightDoorFromMainToShop;
    }

    /**
     * @return leftDoorFromShopToMain.
     */
    @Override
    public Door getLeftDoorFromShopToMain() {
        return this.leftDoorFromShopToMain;
    }

    /**
     * @return rightDoorFromShopToBoss.
     */
    @Override
    public Door getRightDoorFromShopToBoss() {
        return this.rightDoorFromShopToBoss;
    }

    /**
     * @return rightDoorFromBossToShop.
     */
    @Override
    public Door getLeftDoorFromBossToShop() {
        return this.rightDoorFromBossToShop;
    }

    /**
     * @return boss.
     */
    public Animated getBoss() {
        return this.boss;
    }

    /**
     * @return list of items in the shop.
     */
    @Override
    public List<Inanimated> getItems() {
        return this.items;
    }

    /**
     * @return the button of the main room.
     */
    @Override
    public Button getButton() {
        return this.bt;
    }

    /**
     * @return the hb of the playable room.
     */
    @Override
    public HitBox getRoomHB() {
        return this.hbRoom;
    }

    /**
     * @return wall main room.
     */
    private List<Wall> wallMainRoom() {
        final List<Wall> l = new ArrayList<>();
        for (double i = ModelUtility.getWorldHeight() / 2
                - ProportionUtility.getHeightDoor() / 2; i < ModelUtility.getWorldHeight() / 2
                        + ProportionUtility.getHeightDoor() / 2; i += ProportionUtility.getWallVerticalHeight()) {
            l.add(new WallImpl(new RectangularHitBox(0, i, ProportionUtility.getWallVerticalHeight(),
                    ProportionUtility.getWallVerticalWidth()), false, ImageType.MAP_HORIZONTAL_BORDER));
        }
        return l;
    }

    /**
     * @return wall main room.
     */
    private List<Wall> wallBossRoom() {
        final List<Wall> l = new ArrayList<>();
        for (double i = ModelUtility.getWorldHeight() / 2
                - ProportionUtility.getHeightDoor() / 2; i < ModelUtility.getWorldHeight() / 2
                        + ProportionUtility.getHeightDoor() / 2; i += ProportionUtility.getWallVerticalHeight()) {
            l.add(new WallImpl(
                    new RectangularHitBox(ModelUtility.getWorldWidth() - ProportionUtility.getWallVerticalWidth(), i,
                            ProportionUtility.getWallVerticalHeight(), ProportionUtility.getWallVerticalWidth()),
                    false, ImageType.MAP_HORIZONTAL_BORDER));
        }
        return l;
    }
}
