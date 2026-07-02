package model.enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import controllers.movement.Movement;
import controllers.movement.animation.Animation;
import model.Direction;
import model.ID;
import model.gameObject.GameObject;
import other.Pair;

public abstract class Enemy extends GameObject implements EnemyInterface {
    private boolean tower;
    private boolean dragon;
    private boolean alive;
    private final Ray ray;
    private Animation animation;
    private List<BufferedImage> listUp;
    private List<BufferedImage> listDown;
    private List<BufferedImage> listLeft;
    private List<BufferedImage> listRight;

    /**
     * Constructor for Enemy with Ray images list and texture List.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param textureImages
     * @param rayImages
     */
    public Enemy(final ID id, final int posX, final int posY, final double velX, final double velY,
            final List<Pair<Direction, BufferedImage>> textureImages,
            final List<Pair<Direction, BufferedImage>> rayImages) {
        super(id, posX, posY, velX, velY, textureImages);
        this.alive = true;
        this.ray = this.new Ray(ID.RAY, posX, posY, 0, 0, rayImages);
        this.animation = new Animation(4);
        this.listUp = this.getTextureByDirection(Direction.UP);
        this.listDown = this.getTextureByDirection(Direction.DOWN);
        this.listRight = this.getTextureByDirection(Direction.RIGHT);
        this.listLeft = this.getTextureByDirection(Direction.LEFT);
    }

    /**
     * Constructor for Enemy with single texture and ray image.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     * @param rayImage
     */
    public Enemy(final ID id, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image, final BufferedImage rayImage) {
        super(id, posX, posY, velX, velY, image);
        this.ray = this.new Ray(ID.RAY, posX, posY, 0, 0, rayImage);
        this.alive = true;
    }

    /**
     * Constructor for Enemy with texture list and single ray image.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param textureImages
     * @param rayImage
     */
    public Enemy(final ID id, final int posX, final int posY, final double velX, final double velY,
            final List<Pair<Direction, BufferedImage>> textureImages, final BufferedImage rayImage) {
        super(id, posX, posY, velX, velY, textureImages);
        this.ray = this.new Ray(ID.RAY, posX, posY, 0, 0, rayImage);
        this.alive = true;
        this.animation = new Animation(4);
        this.listUp = this.getTextureByDirection(Direction.UP);
        this.listDown = this.getTextureByDirection(Direction.DOWN);
        this.listRight = this.getTextureByDirection(Direction.RIGHT);
        this.listLeft = this.getTextureByDirection(Direction.LEFT);
    }

    /**
     * Constructor for Enemy with texture list and no ray image.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param textureImages
     */
    public Enemy(final ID id, final int posX, final int posY, final double velX, final double velY,
            final List<Pair<Direction, BufferedImage>> textureImages) {
        super(id, posX, posY, velX, velY, textureImages);
        this.alive = true;
        this.ray = null;
        this.animation = new Animation(4);
        this.listUp = this.getTextureByDirection(Direction.UP);
        this.listDown = this.getTextureByDirection(Direction.DOWN);
        this.listRight = this.getTextureByDirection(Direction.RIGHT);
        this.listLeft = this.getTextureByDirection(Direction.LEFT);

    }

    @Override
    public Rectangle getRectangle() {
        return super.getRectangle().union(this.ray.getRectangle());
    }

    @Override
    public Rectangle getEnemyRectangle() {
        return super.getRectangle();
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public void setCoord(final Pair<Integer, Integer> coord) {
        super.setCoord(coord);
        if (this.ray != null) {
            this.ray.setCoord(new Pair<>(coord.getX() + this.getRay().rayX, coord.getY() + this.getRay().rayY));
        }

    }

    @Override
    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    @Override
    public boolean killLife() {
        this.alive = false;
        this.setVisible(false);
        this.ray.setVisible(false);
        return this.alive;
    }

    @Override
    public List<BufferedImage> getListUp() {
        return listUp;
    }

    @Override
    public List<BufferedImage> getListDown() {
        return listDown;
    }

    @Override
    public List<BufferedImage> getListLeft() {
        return listLeft;
    }

    @Override
    public List<BufferedImage> getListRight() {
        return listRight;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    public void tick() {
        final Pair<Integer, Integer> newCoord = new Pair<>(
                this.getCoord().getX() + this.getVelocity().getX().intValue(),
                this.getCoord().getY() + this.getVelocity().getY().intValue());
        this.setCoord(newCoord);

    }

    /**
     * @return dragon
     */
    public boolean isDragon() {
        return this.dragon;
    }

    /**
     * @return tower
     */
    public boolean isTower() {
        return this.tower;
    }

    /**
     * setter enemy dragon
     */
    public void setEnemyDragon() {
        this.dragon = true;
    }

    /**
     * setter enemy tower
     */
    public void setEnemyTower() {
        this.tower = true;
    }

    @Override
    public Ray getRay() {
        return this.ray;
    }

    @Override
    public abstract int getSpeed();

    @Override
    public abstract Movement getMovement();

    public class Ray extends GameObject {

        private boolean presentRay = true;
        private int rayX;
        private int rayY;

        public Ray(final ID id, final int posX, final int posY, final double velX, final double velY,
                final List<Pair<Direction, BufferedImage>> rayList) {
            super(id, posX, posY, velX, velY, rayList);
        }

        public Ray(final ID id, final int posX, final int posY, final double velX, final double velY,
                final BufferedImage rayImage) {
            super(id, posX, posY, velX, velY, rayImage);
        }

        @Override
        public void tick() {
        }

        /**
         * method to set ray status.
         * 
         * @param status
         */
        public void setRay(final boolean status) {
            this.presentRay = status;
        }

        /**
         * getter ray status.
         * 
         * @return rayStatus
         */
        public boolean isRayPresent() {
            return this.presentRay;
        }

        /**
         * @param x
         */
        public void setRayX(final int x) {
            this.rayX = x;
        }

        /**
         * @param y
         */
        public void setRayY(final int y) {
            this.rayY = y;
        }

    }

}
