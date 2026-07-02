package controllers.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import controllers.texture.imageLoader.ImageLoader;
import model.Direction;
import model.ID;
import other.Pair;

public class GetTexture implements GetTextureInterface {

    private static final List<Direction> MOVEMENT = Arrays.asList(Direction.values());

    private static final int COUNTLEVEL = 6;

    private final Pair<Integer, Integer> sizePlayerEnemiesPair;

    private final Pair<Integer, Integer> sizeElemTilesheetPair;

    private final Pair<Integer, Integer> sizePowerUPDebuffPair;

    private final Pair<Integer, Integer> sizeDragonPair;

    private final ImageLoader loadImage;

    private BufferedImage tilesheetTexture;

    private BufferedImage pUPDebuffTexture;

    private BufferedImage towerEnemyImage;

    private BufferedImage towerEnemyRayImage;

    private final List<Pair<Direction, BufferedImage>> dragonEnemyRayListTexture;

    private final List<Pair<Direction, BufferedImage>> baseEnemyRayListTexture;

    private final List<Pair<Direction, BufferedImage>> playerListTexture;

    private final List<Pair<Direction, BufferedImage>> baseEnemyListTexture;

    private final List<Pair<Direction, BufferedImage>> dragonEnemyListTexture;

    private final List<Pair<Direction, BufferedImage>> averageEnemyListTexture;

    private final List<Pair<Direction, BufferedImage>> supportEnemyListTexture;

    private final List<Pair<Direction, BufferedImage>> advanceEnemyListTexture;

    private final List<BufferedImage> chestListTexture; // chestListTexture[0] = chest1,
                                                        // chestListTexture[1] = chest2,
                                                        // chestListTexture[2] = chest3

    private final List<BufferedImage> wallListTexture; // wallListTexture[0] = Wall1,
                                                       // wallListTexture[1] = Wall2,
                                                       // wallListTexture[2] = Wall3

    private final List<BufferedImage> levelListStructure; // levelListStructure[0] = level1,
                                                          // levelListStructure[1] = level2,
                                                          // levelListStructure[2] = level3

    private final List<Pair<ID, BufferedImage>> powerUPListTexture;

    private final List<Pair<ID, BufferedImage>> debuffListTexture;

    /**
     * Contructor for GetTexture
     */
    public GetTexture() {

        this.sizePlayerEnemiesPair = new Pair<>(32, 48);
        this.sizeElemTilesheetPair = new Pair<>(64, 64);
        this.sizePowerUPDebuffPair = new Pair<>(32, 32);
        this.sizeDragonPair = new Pair<>(96, 96);

        this.loadImage = new ImageLoader();
        this.playerListTexture = new LinkedList<>();
        this.baseEnemyListTexture = new LinkedList<>();
        this.averageEnemyListTexture = new LinkedList<>();
        this.supportEnemyListTexture = new LinkedList<>();
        this.advanceEnemyListTexture = new LinkedList<>();
        this.dragonEnemyListTexture = new LinkedList<>();
        this.chestListTexture = new LinkedList<>();
        this.wallListTexture = new LinkedList<>();
        this.levelListStructure = new LinkedList<>();
        this.powerUPListTexture = new LinkedList<>();
        this.debuffListTexture = new LinkedList<>();
        this.dragonEnemyRayListTexture = new LinkedList<>();
        this.baseEnemyRayListTexture = new LinkedList<>();

        this.init();

    }

    private void init() {
        this.setLevelStructure();
        this.setTilesheet();
        this.setpUPDebuff();
        this.setPlayerImage();
        this.setBaseEnemyImage();
        this.setAvarageEnemyImage();
        this.setSupportEnemyImage();
        this.setAdvanceEnemyImage();
        this.setDragonEnemyImage();
        this.setTowerEnemyImage();
        this.setChestImage();
        this.setWallImage();
        this.setPowerUPImage();
        this.setDebuffImage();
        this.setDragonEnemyRayImage();
        this.setBaseEnemyRayImage();
        this.setTowerEnemyRayImage();
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getSupportEnemyListTexture() {
        return this.supportEnemyListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getPlayerListTexture() {
        return this.playerListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getBaseEnemyListTexture() {
        return this.baseEnemyListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getBaseEnemyRayListTexture() {
        return this.baseEnemyRayListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getAvarageEnemyListTexture() {
        return this.averageEnemyListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getAdvanceEnemyListTexture() {
        return this.advanceEnemyListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getDragonEnemyListTexture() {
        return this.dragonEnemyListTexture;
    }

    @Override
    public List<Pair<Direction, BufferedImage>> getDragonEnemyRayListTexture() {
        return this.dragonEnemyRayListTexture;
    }

    @Override
    public BufferedImage getChest(final int level) {
        return this.chestListTexture.get(0);
    }

    @Override
    public BufferedImage getWall(final int level) {
        return this.wallListTexture.get(level - 1);
    }

    @Override
    public BufferedImage getTowerEnemyImage() {
        return this.towerEnemyImage;
    }

    @Override
    public BufferedImage getTowerEnemyRayImage() {
        return this.towerEnemyRayImage;
    }

    @Override
    public BufferedImage getLevel(final int level) {
        return levelListStructure.get(level - 1);
    }

    private void setLevelStructure() {
        BufferedImage temp = null;
        for (int i = 1; i < COUNTLEVEL; i++) {
            try {
                temp = ImageIO.read(ClassLoader.getSystemResource("level/livello" + Integer.toString(i) + ".png"));
                this.levelListStructure.add(temp);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTilesheet() {
        try {
            this.tilesheetTexture = ImageIO.read(ClassLoader.getSystemResource("textures/material/tileSheets.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setpUPDebuff() {
        try {
            this.pUPDebuffTexture = ImageIO.read(ClassLoader.getSystemResource("textures/material/buffDebuff.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setPlayerImage() {

        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/Player.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            playerListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(3), 0,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            playerListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(2), 1,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            playerListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(1), 2,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            playerListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(0), 3,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
        }

    }

    @Override
    public BufferedImage getFloor(final int level) {
        BufferedImage image = null;
        try {
            image = ImageIO
                    .read(ClassLoader.getSystemResource("textures/material/floor" + Integer.toString(level) + ".png"));

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (image != null) {
            return loadImage.getOptimizedImage(image);
        }
        return null;

    }

    private void setWallImage() {

        this.wallListTexture.add(loadImage.getImageByRowColumn(this.tilesheetTexture, 2, 11,
                sizeElemTilesheetPair.getX(), sizeElemTilesheetPair.getY()));
        this.wallListTexture.add(loadImage.getImageByRowColumn(this.tilesheetTexture, 2, 1,
                sizeElemTilesheetPair.getX(), sizeElemTilesheetPair.getY()));
        this.wallListTexture.add(loadImage.getImageByRowColumn(this.tilesheetTexture, 2, 7,
                sizeElemTilesheetPair.getX(), sizeElemTilesheetPair.getY()));
        this.wallListTexture.add(loadImage.getImageByRowColumn(this.tilesheetTexture, 2, 10,
                sizeElemTilesheetPair.getX(), sizeElemTilesheetPair.getY()));
        this.wallListTexture.add(loadImage.getImageByRowColumn(this.tilesheetTexture, 7, 3,
                sizeElemTilesheetPair.getX(), sizeElemTilesheetPair.getY()));
    }

    private void setBaseEnemyImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/BaseEnemy.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            baseEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(3), 0,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            baseEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(2), 1,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            baseEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(1), 2,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            baseEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(0), 3,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
        }

    }

    private void setBaseEnemyRayImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/ray.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            baseEnemyRayListTexture.add(new Pair<Direction, BufferedImage>(MOVEMENT.get(3), temp));
            baseEnemyRayListTexture
                    .add(new Pair<Direction, BufferedImage>(MOVEMENT.get(1), this.loadImage.rotateImage(-90, temp)));
            baseEnemyRayListTexture
                    .add(new Pair<Direction, BufferedImage>(MOVEMENT.get(0), this.loadImage.rotateImage(-180, temp)));
            baseEnemyRayListTexture
                    .add(new Pair<Direction, BufferedImage>(MOVEMENT.get(2), this.loadImage.rotateImage(-270, temp)));
        }

    }

    private void setAvarageEnemyImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/AvarageEnemy.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            averageEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(3), 0,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            averageEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(2), 1,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            averageEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(1), 2,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            averageEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(0), 3,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
        }

    }

    private void setAdvanceEnemyImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/AdvanceEnemy.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            advanceEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(3), 0,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            advanceEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(2), 1,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            advanceEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(1), 2,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
            advanceEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(0), 3,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getY()));
        }

    }

    private void setSupportEnemyImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/SupportEnemy.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            supportEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(3), 0,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getX()));
            supportEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(2), 1,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getX()));
            supportEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(1), 2,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getX()));
            supportEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(0), 3,
                    sizePlayerEnemiesPair.getX(), sizePlayerEnemiesPair.getX()));
        }

    }

    private void setDragonEnemyImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/DragonEnemy.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            dragonEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(3), 0,
                    sizeDragonPair.getX(), sizeDragonPair.getY()));
            dragonEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(2), 1,
                    sizeDragonPair.getX(), sizeDragonPair.getY()));
            dragonEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(1), 2,
                    sizeDragonPair.getX(), sizeDragonPair.getY()));
            dragonEnemyListTexture.addAll(this.loadImage.textureByDirectionList(temp, MOVEMENT.get(0), 3,
                    sizeDragonPair.getX(), sizeDragonPair.getY()));
        }

    }

    private void setDragonEnemyRayImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/ray3.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            dragonEnemyRayListTexture.add(new Pair<Direction, BufferedImage>(MOVEMENT.get(3), temp));
            dragonEnemyRayListTexture
                    .add(new Pair<Direction, BufferedImage>(MOVEMENT.get(1), this.loadImage.rotateImage(-90, temp)));
            dragonEnemyRayListTexture
                    .add(new Pair<Direction, BufferedImage>(MOVEMENT.get(0), this.loadImage.rotateImage(-180, temp)));
            dragonEnemyRayListTexture
                    .add(new Pair<Direction, BufferedImage>(MOVEMENT.get(2), this.loadImage.rotateImage(-270, temp)));
        }

    }

    private void setChestImage() {
        this.chestListTexture.add(this.loadImage.getImageByRowColumn(this.tilesheetTexture, 5, 4,
                sizeElemTilesheetPair.getX(), sizeElemTilesheetPair.getY()));
    }

    private void setPowerUPImage() {
        powerUPListTexture
                .add(new Pair<ID, BufferedImage>(ID.SPEEDUPPU, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0,
                        1, sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        powerUPListTexture
                .add(new Pair<ID, BufferedImage>(ID.INVISIBLEPU, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0,
                        2, sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        powerUPListTexture
                .add(new Pair<ID, BufferedImage>(ID.GAINTIMEPU, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0,
                        3, sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        powerUPListTexture
                .add(new Pair<ID, BufferedImage>(ID.LIFEUPPU, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0, 0,
                        sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        powerUPListTexture
                .add(new Pair<ID, BufferedImage>(ID.KNIFEPU, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0, 8,
                        sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
    }

    private void setDebuffImage() {
        debuffListTexture
                .add(new Pair<ID, BufferedImage>(ID.FIREDB, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0, 6,
                        sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        debuffListTexture
                .add(new Pair<ID, BufferedImage>(ID.FREEZEDB, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0, 5,
                        sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        debuffListTexture
                .add(new Pair<ID, BufferedImage>(ID.TIMEDOWNDB, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0,
                        7, sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
        debuffListTexture
                .add(new Pair<ID, BufferedImage>(ID.SLOWDOWNDB, loadImage.getImageByRowColumn(this.pUPDebuffTexture, 0,
                        4, sizePowerUPDebuffPair.getX(), sizePowerUPDebuffPair.getY())));
    }

    @Override
    public BufferedImage getPowerUPImageByID(final ID id) {
        return powerUPListTexture.stream().filter(p -> p.getX().equals(id)).findFirst().get().getY();
    }

    @Override
    public BufferedImage getDebuffImageByID(final ID id) {
        return debuffListTexture.stream().filter(p -> p.getX().equals(id)).findFirst().get().getY();
    }

    @Override
    public BufferedImage getLifeImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/life.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            return loadImage.getOptimizedImage(temp);
        }
        return null;
    }

    @Override
    public BufferedImage getBaseRayImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/ray.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            return loadImage.getOptimizedImage(temp);
        }
        return null;
    }

    @Override
    public BufferedImage getComplexRayImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/ray2.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            return loadImage.getOptimizedImage(temp);
        }
        return null;
    }

    private void setTowerEnemyImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/TowerEnemy.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            this.towerEnemyImage = loadImage.getOptimizedImage(temp);
        }
    }

    private void setTowerEnemyRayImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ClassLoader.getSystemResource("textures/characters/ray4.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            this.towerEnemyRayImage = loadImage.getOptimizedImage(temp);
        }
    }

    @Override
    public BufferedImage getLavaImage() {
        return loadImage.getImageByRowColumn(this.tilesheetTexture, 4, 4, 64, 64);
    }

    @Override
    public BufferedImage getDarkWallImage() {
        return loadImage.getImageByRowColumn(this.tilesheetTexture, 2, 1, 64, 64);
    }

    @Override
    public BufferedImage getFinalDoorImage() {
        return loadImage.getImageByRowColumn(this.tilesheetTexture, 5, 9, sizeElemTilesheetPair.getX(),
                sizeElemTilesheetPair.getY());
    }

    @Override
    public BufferedImage getInitialDoorImage() {
        return loadImage.getImageByRowColumn(this.tilesheetTexture, 5, 1, sizeElemTilesheetPair.getX(),
                sizeElemTilesheetPair.getY());
    }

    @Override
    public BufferedImage getPortalImage() {
        return loadImage.getImageByRowColumn(this.tilesheetTexture, 7, 0, sizeElemTilesheetPair.getX(),
                sizeElemTilesheetPair.getY());
    }

}
