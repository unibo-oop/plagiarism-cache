package pvz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pvz.model.entity.Entity;
import pvz.model.entity.plant.Peashooter;
import pvz.model.entity.plant.Plant;
import pvz.model.entity.plant.PlantType;
import pvz.model.entity.plant.Potato;
import pvz.model.entity.plant.Sunflower;
import pvz.utility.Pair;

public class Model implements ModelInterface {

    private List<Entity> entities;
    private int energy;
    private GameStatus status;
    private int level; // TODO: level should be a class

    public Model() { // This is a very basic implementation
        this.entities = new ArrayList<Entity>();
        this.energy = 0;
        this.status = GameStatus.PLAYING;
        this.level = 0;
    }

    @Override
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    @Override
    public void update() {
        // Delete all the entities that should be removed. Update the rest.
        this.entities.removeIf(e -> e.shouldBeRemoved());
        this.entities.forEach(e -> e.update());
    }

    @Override
    public void putPlant(Pair<Double, Double> position, PlantType plant) {
        final double plantX = this.clipToCenter(position.getX());
        final double plantY = this.clipToCenter(position.getY());

        switch (plant) {
        case SUNFLOWER:
            this.entities.add(new Sunflower(new Pair<Double, Double>(plantX, plantY)));
            break;
        case PEASHOOTER:
            this.entities.add(new Peashooter(new Pair<Double, Double>(plantX, plantY)));
            break;
        case POTATO:
            this.entities.add(new Potato(new Pair<Double, Double>(plantX, plantY)));
            break;
        }
    }

    @Override
    public void removePlant(Pair<Double, Double> position) {
        final double plantX = this.clipToCenter(position.getX());
        final double plantY = this.clipToCenter(position.getY());
        // TODO: should refactor in Cell class
        this.entities.removeIf(e -> e instanceof Plant && e.getX() == plantX && e.getY() == plantY);
    }

    @Override
    public void harvestEnergy(Pair<Double, Double> position) {
        // TODO: select all suns, delete the selected one and increase energy
    }

    @Override
    public int getCurrentEnergy() {
        return this.energy;
    }

    @Override
    public GameStatus getStatus() {
        return this.status;
    }

    @Override
    public int getCurrentLevel() {
        return this.level;
    }

    /**
     * Transforms the given coordinate to the center of the cell containing the
     * coordinate.
     * 
     * @param coord
     *            coordinate
     * @return center of the cell
     */
    private double clipToCenter(double coord) {
        return (int) ((coord * WorldConstants.BACKYARD_WIDTH) / WorldConstants.CELL_WIDTH) * WorldConstants.CELL_WIDTH
                + WorldConstants.CELL_WIDTH / 2;
    }

}
