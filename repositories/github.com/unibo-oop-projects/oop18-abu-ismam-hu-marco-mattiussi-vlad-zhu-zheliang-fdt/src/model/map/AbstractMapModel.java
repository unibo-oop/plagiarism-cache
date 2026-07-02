package model.map;

import java.util.ArrayList;
import java.util.List;
import model.tower.Tower;
import model.entity.Entity;
import model.map.MapTile.Status;
import utilityclasses.Pair;

public abstract class AbstractMapModel implements Map {

    protected List<MapTile> grid = new ArrayList<>();
    protected List<MapTile> enemyPath = new ArrayList<>();
    private ArrayList<Entity> entity = new ArrayList<>();
    protected final int gridSize = 20;

    /**
     * Constructor to generate a grid with a path
     * first for cycle generate the arraylist that contain the grid
     * second for cycle generate the enemy moviment path.
     */
    public AbstractMapModel() {
        generateGrid(); //i metodi abstract dovrebbero essere di strategy, controlla
        generatePath();
    }

    private void generateGrid() {
        for (int i = 0; i < gridSize; i++) { //idea, fai un costruttore che richiama 2 metodi privati che fanno questi 2 cicli for
            for (int j = 0; j < gridSize; j++) {
                MapTile tile = new MapTileImpl(i, j);
                grid.add(tile);
            }
        }
    }

    abstract void generatePath();

    public int getGridSize() {
        return gridSize;
    }

    @Override
    public ArrayList<Entity> getEntityList(){
        ArrayList<Entity> copia = new ArrayList<>(entity);
        return copia;
    }

    @Override
    public ArrayList<MapTile> getTileList() {
        return (ArrayList<MapTile>) this.grid;
    }

    @Override
    public ArrayList<MapTile> getPathList() {
        return (ArrayList<MapTile>) this.enemyPath;
    }

    @Override
    public void addEntity(final Entity e) {
        this.entity.add(e);
    }

    @Override
    public void removeEntity(final Pair<Integer, Integer> location) {
        entity.removeIf(e -> e.getLocation().equals(location));
    }

    @Override
    public void removeEntity(final Entity e) {
        entity.remove(e);
    }

    @Override
    public MapTile initialPosition() {
        return enemyPath.get(0);
    }

    @Override
    public MapTile finalPosition() {
        return enemyPath.get(enemyPath.size());
    }

    @Override
    public boolean positionable(final Pair<Integer, Integer> position) {
        MapTile m = getTilePair(position);
        return m.getStatus() == Status.EMPTY;
    }

    @Override
    public MapTile getTileInt(final int position) {
        return grid.get(position);
    }

    @Override
    public MapTile getTilePair(final Pair<Integer, Integer> position) {
        for (int i = 0; i < this.gridSize * this.gridSize; i++) {
            if (this.grid.get(i).getPosition().equals(position)) {
                return this.grid.get(i);
            }
        }
        return null;
    }

    @Override
    public void setTile(final MapTile tile) {
        int tmp = tile.getPosition().getX() * this.gridSize + tile.getPosition().getY(); 
        grid.remove(tmp);
        grid.add(tmp, tile);
    }

    @Override
    public Pair<Integer, Integer> fromIntToPair(final int position) {
        int x = position / this.gridSize;
        int y = position % this.gridSize;
        return new Pair<>(x, y);
    }

    @Override
    public int fromPairToInt(final Pair<Integer, Integer> position) {
        return position.getX() * this.gridSize + position.getY();
    }
}

