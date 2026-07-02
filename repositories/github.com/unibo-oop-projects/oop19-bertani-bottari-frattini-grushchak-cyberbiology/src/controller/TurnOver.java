package controller;
import model.world.WorldImp;

public class TurnOver {

    private final RunnerImp starter;
    private int iteration = 0;

     public TurnOver(final WorldImp screen) {
         starter = new RunnerImp(screen);
     }

    public final void start() {
        iteration++;
        starter.aliveCells();
        starter.deadCells();
    }

    public final int getIteration() {
        return iteration;
    }
}
