package pvz.model.entity.plant;

import pvz.utility.Pair;

public class Peashooter extends Plant {

    public Peashooter(Pair<Double, Double> position) {
        super(position);
        this.type = PlantType.PEASHOOTER;
    }

    @Override
    public void update() {
        // TODO: shoot a pea every x seconds
    }

}
