package view.start.world;

import controller.TurnOver;
import model.world.World;
import model.world.WorldImp;
import view.clock.ControlClockImpl;

/**
 * Class that launches the application.
 *
 */
public class Start implements Runnable {

    private final World world;
    private TurnOver t;

    /**
     * Builder who loses the world as a parameter and launches the graphics and iteration of the world
     * @param world the world to view
     */
    public Start(final World world) {
       this.world = world;
       t = new TurnOver((WorldImp) world);
    }

    @Override
    public final void run() {
        ApplicationFrame f = new ApplicationFrameImpl(world);
        f.show();
        new ControlClockImpl(f).start(t);
    }
}
