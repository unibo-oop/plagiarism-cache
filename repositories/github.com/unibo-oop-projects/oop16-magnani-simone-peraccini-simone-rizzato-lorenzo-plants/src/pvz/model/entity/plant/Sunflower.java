package pvz.model.entity.plant;

import pvz.utility.Pair;

public class Sunflower extends Plant {

    public Sunflower(Pair<Double, Double> position) {
        super(position);
        this.type = PlantType.SUNFLOWER;
    }

    @Override
    public void update() {
        // TODO: spawn a sun every x seconds
    }

}
