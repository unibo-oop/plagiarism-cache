package it.unibo.plantsfarm.view.gamepanel;

import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONHOE;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONWATER;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.REMOVE_PLANT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.UP;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.SELECT_SEED;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.plantsfarm.controller.animation.ImplSelectorFrames;
import it.unibo.plantsfarm.controller.animation.api.SelectorFrames;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.PlantStatus;

import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.tiles.SoilImpl;
import it.unibo.plantsfarm.view.gamepanel.api.ViewGamePanel;
import it.unibo.plantsfarm.view.map.TileManager;
import it.unibo.plantsfarm.view.utility.SpriteLoader;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.music.api.MusicPlayer;
import it.unibo.plantsfarm.view.music.impl.MusicPlayerImpl;

/**
 * Implementation of the ViewGamePanel interface, responsible for rendering the game state,
 * handling user input, and displaying the game world, player, plants, and buffs.
 */
public final class ImplViewGamePanel extends JPanel implements ViewGamePanel {

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int SIDEBAR_WIDTH = 222;

    public static final int SCREEN_WIDTH = SCREEN_SIZE.width - SIDEBAR_WIDTH;
    public static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    public static final int VISIBLE_TILES_VERTICAL = 16;

    public static final int TILE_SIZE = SCREEN_HEIGHT / VISIBLE_TILES_VERTICAL;
    public static final int POD_SIZE = TILE_SIZE;

    public static final int PLAYER_SIZE = (int) (TILE_SIZE * 1.33);

    public static final int MAX_WORLD_COL = 66;
    public static final int MAX_WORLD_ROW = 23;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    private static final long serialVersionUID = 3L;

    private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UP,
        KeyEvent.VK_A, LEFT,
        KeyEvent.VK_D, RIGHT,
        KeyEvent.VK_S, DOWN,
        KeyEvent.VK_E, ACTIONWATER,
        KeyEvent.VK_Q, ACTIONHOE,
        KeyEvent.VK_R, REMOVE_PLANT,
        KeyEvent.VK_P, SELECT_SEED
    );

  private final transient Image buffImage = new SpriteLoader("/plantStatus/xp.png").getImage();
  private final transient TileManager tileM;
  private final transient MusicPlayer musicPlayer = new MusicPlayerImpl();
  private int cameraX;
  private int cameraY;
  private double playerPosX;
  private double playerPosY;
  private transient ControllerGamePanel controller;
  private transient SelectorFrames selector;
  private List<SoilImpl> soilList = List.of();
  @SuppressFBWarnings(value = "SE_TRANSIENT_FIELD_NOT_RESTORED",
        justification = "The list is transient because it is populated by the Controller "
                      + "via the show() method at every frame; it doesn't need to be persisted.")
  private transient List<Buff> buffList = List.of();

  /**
   * Creates a new ImplViewGamePanel, initializing the layout, size, and key listeners for user input.
   *
   * @param controllerGamePanel The controller that will handle user input and game logic.
   * @param selectorFrames The animation selector for rendering player animations.
   */
  public ImplViewGamePanel(final ImplControllerGamePanel controllerGamePanel, final ImplSelectorFrames selectorFrames) {
    super();
    setController(controllerGamePanel);
    setSelectorFrames(selectorFrames);
    this.setLayout(null);
    this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setDoubleBuffered(true);
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.setBackground(Color.BLACK);
    this.tileM = new TileManager(this);
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(final KeyEvent e) {

        if (KEY_MAPPER.containsKey(e.getKeyCode()) && controller != null) {
            controller.takeInput(KEY_MAPPER.get(e.getKeyCode()));
        }

        if (KeyEvent.VK_F == e.getKeyCode()) {
          controller.openInventory();
        }
      }

      @Override
      public void keyReleased(final KeyEvent e) {
        if (KEY_MAPPER.containsKey(e.getKeyCode()) && controller != null) {
            controller.takeInput(UserInput.FERMO);
        }
      }
    });
  }

    /**
     * Plays the sound effect for planting or removing a plant.
     */
    public void playPlant() {
        this.musicPlayer.playEffect("music/gameSound/plant.wav");
    }

    /**
     * Plays the sound effect for watering a plant.
     */
    public void playWater() {
        this.musicPlayer.playEffect("music/gameSound/watering.wav");
    }

    /**
     * Plays the sound effect for selecting a seed.
     */
    public void playSeed() {
        this.musicPlayer.playEffect("music/gameSound/seedSelect.wav");
    }

    /**
     * Plays the sound effect for plant growth.
     */
    public void playGrowth() {
        this.musicPlayer.playEffect("music/gameSound/growth.wav");
    }

    /**
     * Plays the sound effect for collecting an experience point.
     */
    public void playExp() {
        this.musicPlayer.playEffect("music/gameSound/exp.wav");
    }

    @Override
    public void show(final double posX, final double posY,
                    final int camX, final int camY,
                    final List<SoilImpl> pods, final List<Buff> buffs
                ) {
        SwingUtilities.invokeLater(() -> {
            this.playerPosX = posX;
            this.playerPosY = posY;
            this.cameraX = camX;
            this.cameraY = camY;
            this.soilList = pods;
            this.buffList = buffs;
            repaint();
        });
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2D = (Graphics2D) g;

        if (tileM != null) {
            tileM.drawTile(g2D, cameraX, cameraY);
        }

        for (final Buff buff : buffList) {
            g2D.drawImage(buffImage, buff.getBuffPosition().x - cameraX,
                buff.getBuffPosition().y - cameraY, 64, 64, null
            );
        }

           selector.render(g2D, playerPosX, playerPosY, cameraX, cameraY);

        if (soilList != null) {
            for (final SoilImpl pod : soilList) {
                if (pod.isPlanted()) {
                    drawPlant(g2D, pod);
                }
            }
        }
        g2D.dispose();
    }

    private void drawPlant(final Graphics2D g2D, final SoilImpl pod) {
        final PlantImpl plant = pod.getPlant();
        final String plantName = plant.getName();
        final int stage = plant.getGrowthStage() + 1;

        final ImageIcon icon = Texture.getPlantStageIcon(plantName, stage);

        if (icon != null) {
            final Image image = icon.getImage();
            final double scale = 2;
            final int plantSize = (int) (POD_SIZE * scale);
            final int offset = (plantSize - POD_SIZE) / 2;

            g2D.drawImage(image,
                pod.getCoordinate().x - cameraX - offset,
                pod.getCoordinate().y - cameraY - offset * 2,
                plantSize,
                plantSize,
                null
            );

            final PlantStatus status = controller.getPlantStatus(plant);
            ImageIcon statusIcon = null;

            if (status != null) {
                switch (status) {
                    case READY_TO_HARVEST -> statusIcon = Texture.READY_ICON;
                    case EFFECT_SPEED -> statusIcon = Texture.SPEED_ICON;
                    case EFFECT_HARVEST -> statusIcon = Texture.HARVEST_ICON;
                    case NEEDS_WATER -> statusIcon = Texture.WATER_ICON;
                    default -> { }
                }
            }

            if (statusIcon != null) {
                final int statusSize = Texture.STATUS_ICON_SIZE;
                final int iconX = pod.getCoordinate().x - cameraX - (statusSize / 3);
                final int iconY = pod.getCoordinate().y - cameraY - statusSize;

                g2D.drawImage(statusIcon.getImage(), iconX, iconY, statusSize, statusSize, null);
            }
        }
    }

    private void setController(final ImplControllerGamePanel controllerGamePanel) {
        this.controller = controllerGamePanel;
    }

    private void setSelectorFrames(final SelectorFrames selectorFrames) {
        this.selector = selectorFrames;
    }
}
