package laterunner.model.level;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import laterunner.core.Dimensions;
import laterunner.model.vehicle.Obstacle;
import laterunner.model.vehicle.Vehicles;
import laterunner.physics.P2d;
import laterunner.physics.S2d;

/**
 * The class in witch is implemented the method of levels' creation.
 *
 */
public class CreateLevelImpl implements CreateLevel {
    private static final int DISTANCE = 150;
    private static final int WIDTH_ROAD = 100;
    private static final int WIDTH_ROAD_PARALLEL = (int) (WIDTH_ROAD - Dimensions.getDimensions().getVehicleWidth(Vehicles.BUS));
    private static final int MOTOBIKE_GAP = 85;
    private static final int BUS_FRONT_GAP = 10;
    private static final int BUS_BACK_GAP = 83;
    private static final int CAR_FRONT_GAP = 55;
    private static final int CAR_BACK_GAP = 27;
    private static final int INITIAL_GAP = -400;
    private static final double QUEUE_RATE = 0.3;
    private static final double PARALLEL_RATE = 0.5;
    private double gap;
    private static final int LEFT = 220;
    private static final int CENTER = 410;
    private static final int RIGHT = 600;
    private double cont;
    private Random rand = new Random();

    /**
     * 
     */
    public CreateLevelImpl() {
         this.cont = 0;
         this.gap = INITIAL_GAP;
    }
    @Override
    public Level generateLevel(final List<Vehicles> initialList, final S2d speed, final int distance) {
        Level level = new LevelImpl();
        List<Obstacle> finalList = new LinkedList<>();
        int i = initialList.size();

        while (i > 0) {
            int v = (int) (Math.random() * i);
            if (finalList.isEmpty()) {
                P2d pos = new P2d(getRandomPos(), 0);
                finalList.add(new Obstacle(initialList.get(v), pos, speed));
            } else {
                P2d pos = new P2d(getRandomPos(), finalList.get(finalList.size() - 1).getCurrentPos().getY() + this.setY(distance, initialList.get(v), finalList));
                if (finalList.size() >= 3) {
                    changePos(finalList, pos);
                }
                cont = cont + (pos.getY() - finalList.get(finalList.size() - 1).getCurrentPos().getY());
                finalList.add(new Obstacle(initialList.get(v), pos, speed));
            }
            initialList.remove(v);
            i--;
        }
        gap = gap - cont;

       for (Obstacle o : finalList) {
                o.getCurrentPos().setY(o.getCurrentPos().getY() + gap);
        }
        level.setLevel(finalList);
        return level;
    }

    private int getRandomPos() {
        int v = new Random().nextInt(3) + 1;
        switch(v) {
        case 1:
            return (int) new Random().nextInt(WIDTH_ROAD) + LEFT;
        case 2:
            return (int) new Random().nextInt(WIDTH_ROAD) + CENTER;
        default: return (int) new Random().nextInt(WIDTH_ROAD) + RIGHT;
        }
    }

    private int setY(final int d, final Vehicles o, final List<Obstacle> list) {
        int dist = (int) (rand.nextInt(d) + ((CreateLevelImpl.DISTANCE) +  Dimensions.getDimensions().getVehicleHeight(o)));
        if (o.equals(Vehicles.MOTORBIKE)) {
                dist = dist + MOTOBIKE_GAP;
        }
        if (o.equals(Vehicles.BUS)) {
                dist = dist + BUS_FRONT_GAP;
        }
        if (o.equals(Vehicles.OBSTACLE_CAR)) {
                dist = dist + CAR_FRONT_GAP;
        }
        if (list.get(list.size() - 1).getType().equals(Vehicles.BUS)) { 
                dist = dist + BUS_BACK_GAP;
        }

        if (list.get(list.size() - 1).getType().equals(Vehicles.OBSTACLE_CAR)) { 
                dist = dist + CAR_BACK_GAP;
        }
        return dist;
    }

    private int getRange(final P2d pos) {
        if (pos.getX() >= LEFT && pos.getX() <= WIDTH_ROAD + LEFT) {
            return LEFT;
        } else if (pos.getX() >= CENTER && pos.getX() <= WIDTH_ROAD + CENTER) {
            return CENTER;
        } else {
            return RIGHT;
        }
    }

    private boolean isInTheSameRange(final List<Obstacle> list, final P2d pos, final int x) {
        return (this.getRange(list.get(list.size() - x).getCurrentPos()) == this.getRange(pos));
    }

    private boolean checkPos(final List<Obstacle> list, final int x, final int range) {
        return this.getRange(list.get(list.size() - x).getCurrentPos()) == range;
    }

    private void setParallelInCenter(final List<Obstacle> list, final P2d pos, final int first, final int second) {
        list.get(list.size() - 1).getCurrentPos().setX((int) new Random().nextInt(WIDTH_ROAD_PARALLEL) + first);
        pos.setX((int) new Random().nextInt(WIDTH_ROAD_PARALLEL) + second);
        pos.setY(list.get(list.size() - 1).getCurrentPos().getY());
    }

    private void setParallelInSide(final List<Obstacle> list, final P2d pos, final int side) {
        pos.setX((int) new Random().nextInt(WIDTH_ROAD) + side);
        pos.setY(list.get(list.size() - 1).getCurrentPos().getY());
    }

    private boolean checkEmptyLine(final List<Obstacle> list, final P2d pos, final int first, final int second) {
        return this.getRange(pos) == first
                        && this.checkPos(list, 1, second) 
                        && this.checkPos(list, 2, second)
                        && this.checkPos(list, 3, first);
    }

    private boolean checkEmptyCenterLine(final List<Obstacle> list, final int side) {
        return this.checkPos(list, 1, side) 
                        && this.checkPos(list, 2, side)
                        && this.getRange(list.get(list.size() - 3).getCurrentPos()) != CENTER;
    }

    private boolean checkTrianglePosition(final List<Obstacle> list, final P2d pos, final int first, final int second) {
        return this.getRange(pos) == first 
                && this.checkPos(list, 1, second)
                && this.checkPos(list, 2, first) 
                && this.getRange(list.get(list.size() - 3).getCurrentPos()) != second
                && !(list.get(list.size() - 1).getCurrentPos().getY() == list.get(list.size() - 2).getCurrentPos().getY());
    }

    private void checkDuplicateObstacle(final List<Obstacle> list, final P2d pos) {
        if (pos.getY() == list.get(list.size() - 1).getCurrentPos().getY()
                && this.isInTheSameRange(list, pos, 1)) {
            pos.setY(pos.getY() + CreateLevelImpl.DISTANCE);
        }
    }

    private void changePos(final List<Obstacle> list, final P2d pos) {

        if (this.isInTheSameRange(list, pos, 1) && (double) rand.nextDouble() > QUEUE_RATE) {
            double x = this.getRange(list.get(list.size() - 2).getCurrentPos());
            if (this.isInTheSameRange(list, pos, 2)) {
            pos.setX(this.getRandomPos());

            } else if (x == LEFT && this.getRange(pos) == CENTER || x == CENTER && this.getRange(pos) == LEFT) {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + RIGHT);
            } else if (x == LEFT && this.getRange(pos) == RIGHT || x == RIGHT && this.getRange(pos) == LEFT) {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + CENTER);
            } else {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + LEFT);
            }
           }

        if (this.checkEmptyLine(list, pos, LEFT, RIGHT)) {
                this.setParallelInCenter(list, pos, RIGHT, CENTER);
                this.checkDuplicateObstacle(list, pos);
                }
        if (this.checkEmptyLine(list, pos, RIGHT, LEFT)) {
                this.setParallelInCenter(list, pos, LEFT, CENTER);
                }
        if (this.checkEmptyLine(list, pos, LEFT, CENTER)) {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + RIGHT);
        }
        if (this.checkEmptyLine(list, pos, RIGHT, CENTER)) {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + LEFT);
        }
        if (this.checkEmptyLine(list, pos, CENTER, LEFT)) {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + RIGHT);
        }
        if (this.checkEmptyLine(list, pos, CENTER, RIGHT)) {
                pos.setX((int) new Random().nextInt(WIDTH_ROAD) + LEFT);
        }

        if (this.checkTrianglePosition(list, pos, LEFT, RIGHT)) {
                this.setParallelInSide(list, pos, LEFT);
        }
        if (this.checkTrianglePosition(list, pos, RIGHT, LEFT)) {
                this.setParallelInSide(list, pos, RIGHT);
                this.checkDuplicateObstacle(list, pos);
        }

        if (this.checkTrianglePosition(list, pos, LEFT, CENTER)
                && (double) rand.nextDouble() > PARALLEL_RATE) {
                this.setParallelInCenter(list, pos, CENTER, LEFT);
                this.checkDuplicateObstacle(list, pos);
                }

        if (this.checkTrianglePosition(list, pos, RIGHT, CENTER)
                && (double) rand.nextDouble() > PARALLEL_RATE) {
                this.setParallelInCenter(list, pos, CENTER, RIGHT);
                this.checkDuplicateObstacle(list, pos);
                }
        if (this.checkEmptyCenterLine(list, RIGHT)) {
                this.setParallelInCenter(list, pos, RIGHT, CENTER);
                this.checkDuplicateObstacle(list, pos);
                }
        if (this.checkEmptyCenterLine(list, LEFT)) {
                this.setParallelInCenter(list, pos, LEFT, CENTER);
                this.checkDuplicateObstacle(list, pos);
                }
    }
}
