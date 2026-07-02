package it.unibo.view.gamelaunchedview.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.gamelaunched.api.GameLaunchedController;
import it.unibo.controller.gamelaunched.api.GameLaunchedInputController;
import it.unibo.controller.gamelaunched.impl.GameLaunchedControllerImpl;
import it.unibo.view.gamelaunchedview.input.impl.LaunchedGameInputHandlerImpl;
import it.unibo.view.gamelaunchedview.renderers.impl.AlienRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.CoinRender;
import it.unibo.view.gamelaunchedview.renderers.impl.EnemyRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.GadgetRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.MovingPlatformRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.OnTouchPlatformRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.PlatformRenderer;
import it.unibo.view.utilities.SpriteManager;

/**
 * Rapresent the application's panel seen when the game is launched.
 */
public class GameLaunchedViewPanelImpl extends JPanel {

  private static final long serialVersionUID = 1L;

  private static final double LOGICAL_WIDTH = 600.0;
  private static final double LOGICAL_HEIGHT = 1000.0;
  private static final int GRID_SPACING = 40;
  private static final Color BG_COLOR = new Color(250, 250, 230);
  private static final Color STRIPE_COLOR = new Color(230, 230, 210);
  private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 24);
  private static final int SCORE_X = 15;
  private static final int SCORE_Y = 30;
  private static final int COINS_Y = 60;

  /**
   * The {@link GameLaunchedControllerImpl} which provide the game elements to
   * render.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
      justification = "The launched controller is shared across the game and "
          + "it's not modified by the view, but only read to get the game state for rendering.")
  private final transient GameLaunchedController launchedController;
  private final transient GameLaunchedInputController inputController;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Alien} entity within the game
   * view panel.
   */
  private final transient AlienRenderer alienRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Platform} entities within the
   * game view panel.
   */
  private final transient PlatformRenderer platformRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Enemy} entities within the game
   * view panel.
   */
  private final transient EnemyRenderer enemyRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Coin} entities within the game
   * view panel.
   */
  private final transient CoinRender coinRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Gadget} entities within the
   * game view panel.
   */
  private final transient GadgetRenderer gadgetRenderer;

  /**
   * Renders the moving {@link it.unibo.model.gameobj.api.Platform} entities
   * within the
   * game view panel.
   */
  private final transient MovingPlatformRenderer movingPlatformRenderer;

  /**
   * Renders the moving {@link it.unibo.model.gameobj.api.Platform} entities which
   * have onTouch behaviour within the
   * game view panel.
   */
  private final transient OnTouchPlatformRenderer onTouchPlatformRenderer;

  /**
   * Construct a new {@link GameLaunchedViewPanelImpl}.
   *
   * @param launchedController the {@link GameLaunchedControllerImpl} which
   *                           provide the game elements to render.
   * @param inputController    the {@link GameLaunchedInputController} which
   *                           handle the input from the user.
   */
  public GameLaunchedViewPanelImpl(final GameLaunchedController launchedController,
      final GameLaunchedInputController inputController) {
    super();
    this.launchedController = launchedController;
    this.inputController = inputController;

    final SpriteManager spriteManager = new SpriteManager();

    this.alienRenderer = new AlienRenderer(spriteManager, this.launchedController.getActiveSkin());
    this.platformRenderer = new PlatformRenderer(spriteManager);
    this.enemyRenderer = new EnemyRenderer(spriteManager);
    this.coinRenderer = new CoinRender(spriteManager);
    this.gadgetRenderer = new GadgetRenderer(spriteManager);
    this.movingPlatformRenderer = new MovingPlatformRenderer(spriteManager);
    this.onTouchPlatformRenderer = new OnTouchPlatformRenderer(spriteManager);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Adds a key listener to the panel to handle user input for the launched game.
   */
  @Override
  public void addNotify() {
    super.addNotify();
    this.addKeyListener(new LaunchedGameInputHandlerImpl(this.inputController));
    setDoubleBuffered(true);
  }

  /**
   * {@inheritDoc}
   *
   * @param g the {@code Java.awt.Graphics} the copy of the graphics used to paint
   *          component
   */
  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    final Graphics2D g2d = (Graphics2D) g;

    final double scale = Math.min(this.getWidth() / LOGICAL_WIDTH, this.getHeight() / LOGICAL_HEIGHT);
    final int xOffset = (int) ((this.getWidth() - (LOGICAL_WIDTH * scale)) / 2.0);
    final int yOffset = (int) ((this.getHeight() - (LOGICAL_HEIGHT * scale)) / 2.0);

    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    g2d.translate(xOffset, yOffset);
    g2d.scale(scale, scale);

    g2d.setColor(BG_COLOR);
    g2d.fillRect(0, 0, (int) LOGICAL_WIDTH, (int) LOGICAL_HEIGHT);

    g2d.setColor(STRIPE_COLOR);
    for (int i = 0; i < (int) LOGICAL_WIDTH; i += GRID_SPACING) {
      g2d.drawLine(i, 0, i, (int) LOGICAL_HEIGHT);
    }
    for (int i = 0; i < (int) LOGICAL_HEIGHT; i += GRID_SPACING) {
      g2d.drawLine(0, i, (int) LOGICAL_WIDTH, i);
    }

    this.renderAll(g2d);

    g2d.setColor(Color.BLACK);
    g2d.setFont(SCORE_FONT);
    g2d.drawString("Score: " + this.launchedController.getCurrentScore(), SCORE_X, SCORE_Y);
    g2d.drawString("Coins: " + this.launchedController.getCollectedCoins(), SCORE_X, COINS_Y);

    g2d.scale(1.0 / scale, 1.0 / scale);
    g2d.translate(-xOffset, -yOffset);
  }

  /**
   * Renders all game elements on the provided graphics context.
   *
   * @param g the {@code Graphics2D} object used for rendering the game elements.
   */
  private void renderAll(final Graphics2D g) {
    this.launchedController.getAlien().ifPresent(alien -> this.alienRenderer.render(List.of(alien), g));
    this.launchedController.getMovingPlatforms()
        .ifPresent(platforms -> this.movingPlatformRenderer.render(platforms, g));
    this.launchedController.getOnTouchPlatform()
        .ifPresent(platforms -> this.onTouchPlatformRenderer.render(platforms, g));
    this.launchedController.getStaticPlatforms().ifPresent(platforms -> this.platformRenderer.render(platforms, g));
    this.launchedController.getEnemy().ifPresent(enemies -> this.enemyRenderer.render(enemies, g));
    this.launchedController.getCoins().ifPresent(coins -> this.coinRenderer.render(coins, g));
    this.launchedController.getGadgets().ifPresent(gadgets -> this.gadgetRenderer.render(gadgets, g));
  }
}
