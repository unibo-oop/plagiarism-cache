package bzzbomber.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import bzzbomber.controller.Controller;
import bzzbomber.game.Game;
import bzzbomber.model.ResetType;
import bzzbomber.view.gamefield.GameFieldPanel;
import bzzbomber.view.hud.HudPanel;
import bzzbomber.view.menu.GUIFactory;
import bzzbomber.view.menu.GUIFactoryImpl;

/**
 * Represents a window which contains gameplay field and HUD.
 */
public class GamePlayView implements GenericView {

    private final JFrame frame;
    private final HudPanel hud;
    private final GameFieldPanel gameFieldPanel;
    private final Controller controller;

    /**
     * @param c
     *            Reference of controller.
     */
    public GamePlayView(final Controller c) {
        this.controller = c;

        final GUIFactory guiFactory = new GUIFactoryImpl();
        this.frame = guiFactory.createBasicFrame();
        this.frame.setPreferredSize(new Dimension(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT));
        this.hud = new HudPanel(controller.getTimer(), controller);
        this.gameFieldPanel = new GameFieldPanel(controller, Game.GAME_FIELD_WIDTH, Game.GAME_FIELD_HEIGHT);

        this.initView();
        this.frame.setResizable(false);
        this.frame.pack();
    }

    private void initView() {
        // HUD
        this.frame.add(hud, BorderLayout.NORTH);

        // Game Field
        this.frame.add(this.gameFieldPanel, BorderLayout.CENTER);
    }

    @Override
    public final void show() {
        this.hud.setVisible(true);
        this.hud.init();
        this.frame.setVisible(true);
    }

    /**
     * This method has to be called every time to simulate frames per second
     * concept, hence showing the current state of the Model.
     */
    public void draw() {
        this.frame.requestFocus(); // This line is very important in order to resume focus on jframe
        if (this.gameFieldPanel != null) {
            this.gameFieldPanel.draw();
        } else {
            System.out.println("GameFieldPanel is null");
        }
    }

    /**
     * @param l
     *            A key listener to attach to this frame.
     */
    public void addKeyListener(final KeyListener l) {
        this.frame.addKeyListener(l);
    }

    /**
     * @param rt
     *            Type of reset logic to apply.
     */
    public void reset(final ResetType rt) {
        if (rt.equals(ResetType.TOTAL)) {
            this.frame.setVisible(false);
        } else if (rt.equals(ResetType.LEVEL_CHANGED)) {
            this.gameFieldPanel.levelChanged();
            this.hud.setNumMonster(this.controller.getCurrentLevel().getNumOfInsects());
        }
    }

    /**
     * @return Hud panel component of this frame.
     */
    public HudPanel getHudPanel() {
        return this.hud;
    }

    /**
     * Hide this frame from the screen.
     */
    public void hide() {
        this.frame.setVisible(false);
    }
}
