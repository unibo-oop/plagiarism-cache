package view.clock;

import javax.swing.JLabel;

import controller.TurnOver;
import data.DataProgramImpl;
import view.menu.data.setting.AddElemValue;
import view.start.world.ApplicationFrame;
import view.start.world.UpdateSpecific;
import view.start.world.UpdateWorld;
/**
 * Controller class that manages the world update at predetermined times and stops when the stop button is pressed.
 *
 */
public class ControlClockImpl implements ControlClock {

    private static LogicClock time;
    private static int upDateFrame = DataProgramImpl.getUpDateView();
    private JLabel clockPanel;
    private UpdateWorld viewWorld;
    private AddElemValue<Boolean> stop;
    private UpdateSpecific specific;
    private static final int MILLS_PAUSE = 20;

    public ControlClockImpl(final ApplicationFrame mainFrame) {
        this.stop = mainFrame.getButton();
        this.viewWorld = mainFrame.getWorld();
        this.clockPanel = mainFrame.getClockView();
        this.specific = mainFrame.getSpecific();
    }
    public final void start(final TurnOver t) {
        int thisSecond;
        int lastSecond = 1;
        time = new LogicClockImpl();
        while (stop.getValue()) {
            clockPanel.setText(time.getMinute() + " : " + time.getSecond());
            thisSecond = time.getIntSecond();
            if (thisSecond % upDateFrame == 0 && thisSecond != lastSecond) {
                lastSecond = thisSecond;
                thisSecond = time.getIntSecond();
                updateFrame(t.getIteration());
            }
            t.start();
            try {
                Thread.sleep(MILLS_PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        viewWorld.enablePressCells();
    }

    private void updateFrame(final int contDay) {
        viewWorld.updateStatus();
        specific.updateSpecific(viewWorld.getCoutUpDate(), contDay);
    }

}
