package spacesurvival.controller.gui;

import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.loading.EngineLoading;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.view.GUI;
import spacesurvival.view.loading.GUILoading;


public class ControllerLoading extends Thread implements ControllerGUI {
    /**
     * Loading duration.
     */
    public static final int DURATION_LOADIN = 2000 / ThreadUtils.MEDIUM_SLEEP;

    /**
     * Step of loading.
     */
    public static final int STEP_TIMING = 20 / ThreadUtils.MEDIUM_SLEEP;

    private final GUILoading gui;
    private final EngineLoading engine;

    private final SwitchGUI switchGUI;

    /**
     * Create a control loading GUI with its model and view.
     * @param engine of model.
     * @param gui of view.
     */
    public ControllerLoading(final EngineLoading engine, final GUILoading gui) { 
        this.engine = engine;
        this.gui = gui;

        this.switchGUI = new SwitchGUI(this.engine, this.gui);
        this.turn(this.engine.getVisibility());
    }

    /**
     * Initialize loading GUI.
     */
    public void initLoading() {
        this.assignLinks();
        this.assignTexts();
        this.assignBounds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignLinks() {
        this.gui.setMainAction(this.engine.getMainLink());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignTexts() {
        this.gui.setTitleGUI(this.engine.getTitleGUI());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignBounds() {
        this.gui.setBounds(this.engine.getRectangle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while (!this.engine.isLoad()) {
            this.engine.incrLoading();

            this.gui.setLoading(this.engine.getLoading() / STEP_TIMING);

            if (this.engine.getLoading() >= DURATION_LOADIN) {
                this.engine.load();
            }

            ThreadUtils.sleep(ThreadUtils.MEDIUM_SLEEP);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkActionGUI getMainLink() {
        return this.engine.getMainLink();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EngineGUI getEngine() {
        return this.engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisibility() {
        return this.engine.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turn(final Visibility visibility) {
        this.switchGUI.turn(visibility);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeVisibility() {
        this.switchGUI.changeVisibility();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeGUI() {
        this.gui.close();
    }

    /**
     * Get the GUI has finished loading.
     * @return boolean if loading.
     */
    public boolean isLoad() {
        return this.engine.isLoad();
    }
}
