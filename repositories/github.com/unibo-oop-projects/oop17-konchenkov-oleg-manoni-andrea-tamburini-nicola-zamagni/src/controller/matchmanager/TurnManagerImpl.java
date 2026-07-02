package controller.matchmanager;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import controller.soundmanager.SoundManager;
import javafx.animation.AnimationTimer;
import model.Model;
import model.collidable.tank.Tank;
import model.explosion.Explosion;
import model.explosion.ExplosionImpl;
import view.View;

/**
 *
 * @author Oleg
 *
 */
public final class TurnManagerImpl implements TurnManager {
    private static final double TIME_STEP = 1.0 / 6.0;
    private static final double COR_PROJECTILE_VS_TANK = 0.0;
    private static final double COR_PROJECTILE_VS_TERRAIN = 0.75;
    private static final double COR_TANK_VS_TERRAIN = 0.0;
    private static final double EXPLOSION_TIME = 10.0;
    private static final int PROJECTILE_INDEX = 0;
    private final View view;
    private final Model model;
    private GameManager gameManager;
    private Status status = Status.PHASE0;
    private boolean turnStarted;
    private AnimationTimer loopTimer;
    private SoundManager soundManager;
    private boolean terrainExplosionSound;
    private boolean tankExplosionSound;
    private boolean settlmentSound;
    private boolean specialProjectile;
    private final GameModeType gameModeType;

    /**
     *
     * @param view
     *            view
     * @param model
     *            model
     * @param gameModeType
     *            game mode type
     */
    public TurnManagerImpl(final View view, final Model model, final GameModeType gameModeType) {
        super();
        this.view = view;
        this.model = model;
        this.gameModeType = gameModeType;
    }

    @Override
    public void start() {
        turnStarted = true;
        soundManager = view.getSceneMainController().getSoundManager();

        // TURN LOGICS
        loopTimer = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                switch (status) {

                case PHASE0:
                    projectileMotion();
                    if (model.getFlyingProjectiles().isEmpty()) {
                        status = Status.PHASE1;
                    }
                    break;

                case PHASE1:
                    landingExplosions();
                    if (model.getLandingExplosion().stream().allMatch(e -> e.isExploded())) {
                        status = Status.PHASE2;
                    }
                    break;

                case PHASE2:
                    tanksExplosions();
                    if (model.getTankExplosion().stream().allMatch(e -> e.isExploded())) {
                        status = Status.PHASE3;
                    }
                    break;

                case PHASE3:
                    removeExplosions();
                    status = Status.PHASE4;
                    break;

                case PHASE4:
                    terrainSettlement();
                    if (model.getTerrain().isStationary()) {
                        status = Status.PHASE5;
                    }
                    break;

                case PHASE5:
                    tankSettlement();
                    if (getAlivePlayersTank().stream().allMatch(tank -> tank.isStationary())) {
                        gameManager.randomizeAngleIfProhibited();
                        status = Status.PHASE6;
                    }
                    break;

                case PHASE6:
                    turnEnd();
                    status = Status.PHASE0;
                    break;

                default:
                    break;
                }
            }
        };
        loopTimer.start();

    }

    @Override
    public void stop() {
        loopTimer.stop();
    }

    @Override
    public boolean isTurnStarted() {
        return turnStarted;
    }

    @Override
    public void setGameManger(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * projectile motion until landing, sets player's kills and creation of an
     * explosion list.
     */
    private void projectileMotion() {
        // UPDATE
        model.getFlyingProjectiles().get(PROJECTILE_INDEX).update(TIME_STEP);
        // COLLISION
        /* check collision projectile vs tank */
        model.getPlayers().stream().map(player -> player.getTank()).filter(t -> !t.isDestroyed()).forEach(tank -> {
            if (model.getFlyingProjectiles().get(PROJECTILE_INDEX).checkAnyFragmentCollision(tank.getPolycollider())) {
                model.getFlyingProjectiles().get(PROJECTILE_INDEX).collide(tank.getPolycollider(), TIME_STEP,
                        COR_PROJECTILE_VS_TANK);
                tank.setDestroyed(true);
                /* populates list of tank explosions */
                model.addTankExplosion(
                        new ExplosionImpl(tank.getPosition(), tank.getExplosionRadius(), EXPLOSION_TIME));
                if (!model.getPlayers().get(gameManager.getTurn()).getTank().getPosition().equals(tank.getPosition())) {
                    specialProjectile = true;
                    model.getPlayers().get(gameManager.getTurn()).getScoreManager().setKill(1);
                } else {
                    model.getPlayers().get(gameManager.getTurn()).getScoreManager().setKill(-1);
                }
            }
        });
        /* check collision projectile vs terrain */
        if (model.getFlyingProjectiles().get(PROJECTILE_INDEX)
                .checkAnyFragmentCollision(model.getTerrain().getPolycollider())) {
            model.getFlyingProjectiles().get(PROJECTILE_INDEX).collide(model.getTerrain().getPolycollider(), TIME_STEP,
                    COR_PROJECTILE_VS_TERRAIN);
        }

        // RENDER
        /* CHECK PROJECTILE REMOTION!! */
        renderTerrainAndTanks();
        view.getRenderer().renderProjectile(model.getFlyingProjectiles().get(PROJECTILE_INDEX));
        /* builds list of landing explosions */
        if (model.getFlyingProjectiles().get(PROJECTILE_INDEX).getNumberOfFragments() == model.getFlyingProjectiles()
                .get(PROJECTILE_INDEX).getFragmentLandingPoint().size()) {
            model.getFlyingProjectiles().get(PROJECTILE_INDEX).getFragmentLandingPoint().stream()
                    .map(p -> new ExplosionImpl(p, model.getFlyingProjectiles().get(PROJECTILE_INDEX).getBlastRadius(),
                            EXPLOSION_TIME))
                    .forEach(e -> model.addLandingExplosion(e));
            /* removing projectile from the battlefield after landing explosion creation */
            model.removeProjectilesFromBattelfield();
        }
    }

    /**
     * Projectiles explosions against the terrain.
     */
    private void landingExplosions() {
        model.getLandingExplosion().forEach(e -> {
            e.update(TIME_STEP);
        });

        if (!terrainExplosionSound) {
            soundManager.getSoundExplosion().play();
            terrainExplosionSound = true;
        }
        view.getRenderer().clearScreen();
        /*
         * when explosion apex is reached, chunks of terrain must be removed from
         * terrain models
         */
        if (model.getLandingExplosion().stream().allMatch(e -> e.isApexTime(TIME_STEP))) {
            model.getTerrain().explode(model.getLandingExplosion());
        }
        renderTerrainAndTanks();
        view.getRenderer().renderExplosionList(model.getLandingExplosion());

    }

    /**
     * hitted tanks explosion against the terrain.
     */
    private void tanksExplosions() {
        view.getRenderer().clearScreen();
        /*
         * when explosion apex is reached, chunks of terrain must be removed from
         * terrain models
         */
        model.getTankExplosion().forEach(e -> {
            e.update(TIME_STEP);
            if (model.getTankExplosion().stream().allMatch(ex -> ex.isApexTime(TIME_STEP))) {
                model.getTerrain().explode(model.getTankExplosion());
            }
        });
        if (!tankExplosionSound) {
            soundManager.getSoundExplosion().play();
            tankExplosionSound = true;
        }
        view.getRenderer().renderTerrain(model.getTerrain());
        /* render the right tanks */
        getMissedTank().forEach(t -> view.getRenderer().renderTank(t));
        getHittedTank().stream()
                .map(t -> IntStream.range(0, getExplosionApexNotReached().size())
                        .mapToObj(i -> getExplosionApexNotReached().get(i))
                        .filter(e -> e.getPosition().equals(t.getPosition())));
        getExplosionApexNotReached().stream()
                .map(e -> IntStream.range(0, getHittedTank().size()).mapToObj(i -> getHittedTank().get(i))
                        .filter(t -> t.getPosition().equals(e.getPosition())))
                .flatMap(Function.identity()).forEach(t -> view.getRenderer().renderTank(t));
        view.getRenderer().renderExplosionList(model.getTankExplosion());
    }

    /**
     * Players which tank was destroyed are set to dead, removing explosions from
     * the battlefield.
     */
    private void removeExplosions() {
        model.getPlayers().stream().filter(p -> p.getTank().isDestroyed()).forEach(p -> p.setAlive(false));
        model.removeLandingExplosions();
        model.removeTankExplosions();
    }

    /**
     * Settlement of the terrain after projectiles or tanks explosions.
     */
    private void terrainSettlement() {
        if (!settlmentSound) {
            soundManager.getSoundFallingRocks().play();
            settlmentSound = true;
        }
        model.getTerrain().update(TIME_STEP);
        renderTerrainAndTanks();
    }

    /**
     * If after terrain settlement the tanks is no more colliding whit the terrain,
     * this is going to fall a settle himself.
     */
    private void tankSettlement() {

        getAlivePlayersTank().forEach(t -> {
            t.setFalling();
            t.update(TIME_STEP);
            if (model.getTerrain().getPolycollider().isIntersected(t.getPreviousPosition(), t.getPosition(),
                    t.getVelocity())) {
                t.collide(model.getTerrain().getPolycollider(), TIME_STEP, COR_TANK_VS_TERRAIN);
            }
            renderTerrainAndTanks();
        });
    }

    /**
     * ends the current player turn.
     */
    private void turnEnd() {
        turnStarted = false;
        sounds();
        turnScore();
        addProjectile();
        gameManager.isGameEnded();
        view.getSceneMainController().enableGameNodes();
        stop();
    }

    /**
     * sets player's score in the score manager.
     */
    private void turnScore() {
        if (model.getPlayers().get(gameManager.getTurn()).isAlive()) {
            model.getPlayers().get(gameManager.getTurn()).getScoreManager().survivedNewTurn();
        }
        if (getAlivePlayersTank().size() == 1 && model.getPlayers().get(gameManager.getTurn()).isAlive()) {
            model.getPlayers().get(gameManager.getTurn()).getScoreManager().wonMatch();
        }
    }

    /**
     * if player get a kill in this turn he will get a special projectile if he
     * survived a normal one.
     */
    private void addProjectile() {
        if (model.getPlayers().get(gameManager.getTurn()).isAlive()) {
            model.getPlayers().get(gameManager.getTurn()).addProjectile(specialProjectile, gameModeType);
        }
        specialProjectile = false;
    }

    /**
     * clear screen, render terrain and all alive players tanks.
     */
    private void renderTerrainAndTanks() {
        view.getRenderer().clearScreen();
        view.getRenderer().renderTerrain(model.getTerrain());
        view.getRenderer().renderTankList(getAlivePlayersTank());
    }

    /**
     *
     * @return list of missed tanks.
     */
    private List<Tank> getMissedTank() {
        return model.getPlayers().stream().filter(p -> p.isAlive()).map(p -> p.getTank()).filter(t -> !t.isDestroyed())
                .collect(Collectors.toList());

    }

    /**
     *
     * @return list of hitted tanks.
     */
    private List<Tank> getHittedTank() {
        return model.getPlayers().stream().filter(p -> p.isAlive()).map(p -> p.getTank()).filter(t -> t.isDestroyed())
                .collect(Collectors.toList());
    }

    /**
     *
     * @return list of explosion that hasn't reached the explosion apex.
     */
    private List<Explosion> getExplosionApexNotReached() {
        return model.getTankExplosion().stream().filter(e -> !e.isApexReached()).collect(Collectors.toList());
    }

    /**
     *
     * @return list of alive tanks
     */
    private List<Tank> getAlivePlayersTank() {
        return model.getPlayers().stream().filter(p -> p.isAlive()).map(p -> p.getTank()).collect(Collectors.toList());
    }

    /**
     * resets sounds.
     */
    private void sounds() {
        terrainExplosionSound = false;
        tankExplosionSound = false;
        settlmentSound = false;
    }

    /**
     *
     * @author Oleg
     *
     */
    enum Status {
        PHASE0, PHASE1, PHASE2, PHASE3, PHASE4, PHASE5, PHASE6;

    }
}
