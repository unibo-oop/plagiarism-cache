package model.updateLevel;

import java.util.Objects;

import controllers.camera.Camera;
import controllers.timer.DeathTimer;
import model.handler.Handler;
import model.player.Player;

public class UpdateLevel implements UpdateLevelInterface {

    private final Handler handler;
    private final Camera camera;
    private final Player player;
    private DeathTimer deathTimer;

    /**
     * Constructor for UpdateLevel.
     * 
     * @param camera
     * @param handler
     */
    public UpdateLevel(final Camera camera, final Handler handler) {
        this.handler = Objects.requireNonNull(handler);
        this.player = Objects.requireNonNull(handler.getPlayer());
        this.camera = Objects.requireNonNull(camera);
    }

    @Override
    public void tick() {
        player.tick();
        if (this.player.isGameOver() && this.deathTimer == null) {
            this.deathTimer = new DeathTimer(this.player);
            this.deathTimer.start();
        }
        this.camera.tick(player);
        handler.getGameObjectList().stream().forEach(p -> p.tick());
    }

}
