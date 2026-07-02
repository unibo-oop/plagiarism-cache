package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.dyn4j.collision.Collidable;
import org.dyn4j.collision.broadphase.BroadphaseDetector;
import org.dyn4j.collision.broadphase.Sap;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Vector2;

import model.bonus.Bonus;
import model.enemy.Enemy;
import model.entities.Character;
import model.palace.Floor;
import model.palace.Window;
import view.AudioPlayer;
import view.SFX;

/**
 * Manage the collisions between entities.
 */
public final class CollisionController implements CollisionObserver {

    private final MainController main;
    private final BroadphaseDetector<Collidable<BodyFixture>, BodyFixture> bp = new Sap<>();
    private final AudioPlayer audioPlayer;

    /**
     * @param main        The MainController
     * @param audioPlayer The Audio Player.
     */
    public CollisionController(final MainController main, final AudioPlayer audioPlayer) {
        this.main = main;
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void collideWithClosedWindow(final Character stuntman, final List<Floor> floors) {
        final List<Vector2> windows = floors.stream()
                                            .map(fl -> fl.getWindows())
                                            .flatMap(List::stream)
                                            .filter(win -> win.getStatus().equals(Window.StatusOfWindow.CLOSE))
                                            .map(win -> win.getPosition())
                                            .collect(Collectors.toList());
        if (windows.stream().anyMatch(pos -> stuntman.getEnvironment().getShape().contains(pos))) {
            while (!stuntman.getLife().isConsummate()) {
                stuntman.getLife().decrement();
            }
            this.main.gameOver(stuntman.getCounterFloors().getValue());
        }
    }

    @Override
    public void collideWithEnemy(final Character stuntman, final Enemy enemy) {
        final Collidable<BodyFixture> collStuntman = new Body();
        final Collidable<BodyFixture> collEnemy = new Body();
        collStuntman.addFixture(stuntman.getEnvironment().getShape());
        collEnemy.addFixture(enemy.getEnvironment().getShape());
        if (bp.detect(collStuntman, collEnemy)) {
            audioPlayer.playSFX(SFX.SFXCOLLISION.getPath());
            stuntman.getLife().decrement();
            enemy.setInGame(false);
            if (stuntman.getLife().isConsummate()) {
                this.main.gameOver(stuntman.getCounterFloors().getValue());
            }
        }
    }

    @Override
    public void collideWithBonus(final Character stuntman, final Bonus bonus) {
        final Collidable<BodyFixture> collStuntman = new Body();
        final Collidable<BodyFixture> collBonus = new Body();
        collStuntman.addFixture(stuntman.getEnvironment().getShape());
        collBonus.addFixture(bonus.getEnvironment().getShape());
        if (bp.detect(collStuntman, collBonus)) {
            audioPlayer.playSFX(SFX.SFXBONUS.getPath());
            bonus.setInGame(false);
            bonus.applyEffect(stuntman);
        }
    }

}
