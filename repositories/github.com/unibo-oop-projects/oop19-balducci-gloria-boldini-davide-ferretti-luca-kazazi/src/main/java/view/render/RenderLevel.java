package view.render;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

import controllers.camera.Camera;
import controllers.movement.keyInput.KeyInput;
import controllers.texture.GetTexture;
import controllers.timer.GameTime;
import model.enemy.Enemy;
import model.gameObject.GameObject;
import model.handler.Handler;
import model.player.Player;
import view.WindowStart;
import view.HUD.HUD;

public class RenderLevel extends Canvas implements RenderLevelInterface {

    private static final long serialVersionUID = -5363696930745037527L;
    private final HUD hud;
    private final Camera camera;
    private final BufferedImage floor;
    private final GameTime time;

    private final Player player;

    private final List<GameObject> enemyList;
    private final List<GameObject> wallList;
    private final List<GameObject> otherList;

    /**
     * Constructor for RenderLevel.
     * 
     * @param handler
     * @param level
     * @param camera
     * @param time
     */
    public RenderLevel(final Handler handler, final int level, final Camera camera, final GameTime time) {
        this.time = Objects.requireNonNull(time);
        this.camera = Objects.requireNonNull(camera);
        this.player = handler.getPlayer();
        this.hud = new HUD(player);
        this.floor = new GetTexture().getFloor(Objects.requireNonNull(level));
        this.enemyList = handler.getEnemyList();
        this.wallList = handler.getWallList();
        this.otherList = handler.getOtherList();

        this.setFocusable(true);

        this.addKeyListener(this.player);

    }

    private void addKeyListener(final Player player) {
        this.addKeyListener(new KeyInput(player));
    }

    @Override
    public void renderFloor(final Graphics g) {
        g.drawImage(floor, 0, 0, null);
    }

    @Override
    public void renderPlayer(final Graphics g) {
        if (!player.isGameOver()) {
            player.getAnimation().drawAnimation(g, player.getCoord().getX(), player.getCoord().getY());
        }
    }

    @Override
    public void renderEnemies(final Graphics g) {
        enemyList.stream().forEach(gameObj -> {
            if (gameObj.isVisible()) {
                final Enemy e = (Enemy) gameObj;
                if (!e.isTower()) {
                    e.getAnimation().drawAnimation(g, e.getCoord().getX(), e.getCoord().getY());
                } else {
                    g.drawImage(e.getImage(), e.getCoord().getX(), e.getCoord().getY(), null);
                }
                if (e.getRay() != null && e.getRay().isRayPresent()) {
                    g.drawImage(e.getRay().getImage(), e.getRay().getCoord().getX(), e.getRay().getCoord().getY(),
                            null);
                }
            }
        });
    }

    @Override
    public void renderWalls(final Graphics g) {
        wallList.stream().forEach(p -> g.drawImage(p.getImage(), p.getCoord().getX(), p.getCoord().getY(), null));
    }

    @Override
    public void renderOther(final Graphics g) {
        otherList.stream().filter(p -> p.isVisible())
                .forEach(p -> g.drawImage(p.getImage(), p.getCoord().getX(), p.getCoord().getY(), null));
    }

    @Override
    public void render() {

        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        g.translate((int) -camera.getX(), (int) -camera.getY());
        this.renderFloor(g);
        this.renderWalls(g);
        this.renderOther(g);
        this.renderEnemies(g);
        this.renderPlayer(g);
        this.hud.displayHUD(g, camera);
        this.time.getTimerTask().drawTime(g);
        WindowStart.drawName(g, camera);
        g.dispose();
        bs.show();

    }

}
