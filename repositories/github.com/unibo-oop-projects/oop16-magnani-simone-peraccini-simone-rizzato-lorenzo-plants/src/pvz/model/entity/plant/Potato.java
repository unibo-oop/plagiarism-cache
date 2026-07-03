package pvz.model.entity.plant;

import pvz.utility.Pair;

public class Potato extends Plant {

    public Potato(Pair<Double, Double> position) {
        super(position);
        this.type = PlantType.POTATO;
    }

    @Override
    public void update() {
        // TODO: basically do nothing
    }

}
