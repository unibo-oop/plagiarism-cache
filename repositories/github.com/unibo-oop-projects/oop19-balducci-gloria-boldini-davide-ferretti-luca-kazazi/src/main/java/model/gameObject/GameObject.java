package model.gameObject;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.Direction;
import model.ID;
import other.Pair;

public abstract class GameObject implements GameObjectInterface {

    private boolean visible;
    private Pair<Integer, Integer> coordinates;
    private Pair<Double, Double> velocity; // Pair riferito alla velocità dell'oggetto nel gioco
    private Pair<Integer, Integer> dimension; // Pair riferito alle dimensioni dell'oggetto nel gioco
    private ID id; // Identificatore del tipo di oggetto nel gioco
    private Map<Direction, List<BufferedImage>> imagesMap;
    private BufferedImage image;

    /**
     * Constructor for GameObject with texture list.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param newImages
     */
    public GameObject(final ID id, final int posX, final int posY, final double velX, final double velY,
            final List<Pair<Direction, BufferedImage>> newImages) {
        this.id = Objects.requireNonNull(id);
        this.coordinates = new Pair<>(Objects.requireNonNull(posX), Objects.requireNonNull(posY));
        this.velocity = new Pair<>(Objects.requireNonNull(velX), Objects.requireNonNull(velY));

        this.imagesMap = new HashMap<>();
        this.fillMapImages(Objects.requireNonNull(newImages));

        this.dimension = new Pair<>(newImages.get(0).getY().getWidth(), newImages.get(0).getY().getHeight());
        this.visible = true;

    }

    /**
     * Constructor for GameObject with single texture.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     */
    public GameObject(final ID id, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image) {
        this.id = Objects.requireNonNull(id);

        this.coordinates = new Pair<>(Objects.requireNonNull(posX), Objects.requireNonNull(posY));
        this.velocity = new Pair<>(Objects.requireNonNull(velX), Objects.requireNonNull(velY));

        this.image = Objects.requireNonNull(image);
        this.dimension = new Pair<>(this.image.getWidth(), this.image.getHeight());
        this.visible = true;
    }

    private void fillMapImages(final List<Pair<Direction, BufferedImage>> list) {
        this.imagesMap.put(Direction.UP, new LinkedList<>());
        this.imagesMap.put(Direction.DOWN, new LinkedList<>());
        this.imagesMap.put(Direction.LEFT, new LinkedList<>());
        this.imagesMap.put(Direction.RIGHT, new LinkedList<>());

        for (final Pair<Direction, BufferedImage> pair : list) {
            if (pair.getX().equals(Direction.UP)) {
                imagesMap.get(Direction.UP).add(pair.getY());
            } else if (pair.getX().equals(Direction.DOWN)) {
                imagesMap.get(Direction.DOWN).add(pair.getY());
            } else if (pair.getX().equals(Direction.LEFT)) {
                imagesMap.get(Direction.LEFT).add(pair.getY());
            } else if (pair.getX().equals(Direction.RIGHT)) {
                imagesMap.get(Direction.RIGHT).add(pair.getY());
            }
        }
    }

    @Override
    public Map<Direction, List<BufferedImage>> getImages() {
        return imagesMap;
    }

    @Override
    public void setImages(final List<Pair<Direction, BufferedImage>> images) {
        imagesMap.clear();
        this.fillMapImages(images);
    }

    @Override
    public void setDimension(final Pair<Integer, Integer> dimension) {
        this.dimension = dimension;
    }

    @Override
    public void setId(final ID id) {
        this.id = id;
    }

    @Override
    public Pair<Integer, Integer> getCoord() {
        return this.coordinates;
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        return this.dimension;
    }

    @Override
    public Pair<Double, Double> getVelocity() {
        return this.velocity;
    }

    @Override
    public void setCoord(final Pair<Integer, Integer> coord) {
        this.coordinates = coord;
    }

    @Override
    public void setVelocity(final Pair<Double, Double> vel) {
        this.velocity = vel;
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    public List<BufferedImage> getListUP() {
        return this.imagesMap.get(Direction.UP);
    }

    @Override
    public List<BufferedImage> getListDOWN() {
        return this.imagesMap.get(Direction.DOWN);
    }

    @Override
    public List<BufferedImage> getListRIGHT() {
        return this.imagesMap.get(Direction.RIGHT);
    }

    @Override
    public List<BufferedImage> getListLEFT() {
        return this.imagesMap.get(Direction.LEFT);
    }

    @Override
    public void setImage(final BufferedImage newImage) {
        this.image = newImage;
        this.dimension = new Pair<>(this.image.getWidth(), this.image.getHeight());
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.coordinates.getX(), this.coordinates.getY(), this.dimension.getX(),
                this.dimension.getY());
    }

    @Override
    public List<BufferedImage> getTextureByDirection(final Direction direction) {
        final List<BufferedImage> newImage = new ArrayList<>();
        imagesMap.entrySet().stream().filter(p -> p.getKey().equals(direction))
                .forEach(p -> newImage.addAll(p.getValue()));
        return newImage;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    @Override
    public abstract void tick();

}
