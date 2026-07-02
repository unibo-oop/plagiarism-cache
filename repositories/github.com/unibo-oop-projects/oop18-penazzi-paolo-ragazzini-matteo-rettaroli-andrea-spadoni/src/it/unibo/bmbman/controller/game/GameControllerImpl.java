package it.unibo.bmbman.controller.game;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import it.unibo.bmbman.model.Level;
import it.unibo.bmbman.model.LevelImpl;
import it.unibo.bmbman.model.engine.GameEngine;
import it.unibo.bmbman.model.engine.GameEngineImp;
import it.unibo.bmbman.model.entities.BombImpl;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.leaderboard.PlayerScoreImpl;
import it.unibo.bmbman.model.utilities.EntityFeature;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.view.EndView;
import it.unibo.bmbman.view.MainMenuView;
import it.unibo.bmbman.view.SinglePlayerView;
import it.unibo.bmbman.view.entities.EntityView;
/**
 * An implementation of {@link GameController}.
 */
public class GameControllerImpl implements GameController {
    private List<Entity> worldEntity;
    private Set<EntityController> setController;
    private SinglePlayerView spv;
    private BombControllerImpl bc;
    private PlayerScoreImpl ps;
    private final MainMenuView mainView; 
    private GameEngine engine;
    private boolean inPause;
    private final Level lv = new LevelImpl(); 
    /**
     * Construct an implementation of {@link GameController}.
     * @param menuView {@link MainMenuView}
     */
    public GameControllerImpl(final MainMenuView menuView) {
        this.worldEntity = new CopyOnWriteArrayList<>();
        this.setController = new HashSet<>();
        this.mainView = menuView;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.bc = new BombControllerImpl();
        this.ps = new PlayerScoreImpl();
        this.engine = new GameEngineImp(this);
        final LoadWorld lw = new LoadWorld(this);
        lw.loadEntity();
        this.spv = new SinglePlayerView(new KeyInput(this), this.ps, this.getHero());
        this.spv.getFrame().setVisible(true);
        this.engine.startEngine();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        inPause = !inPause;
        this.engine.setPause(inPause);
        if (inPause) {
            this.spv.stopTimer();
        } else {
            this.spv.startTimer();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        this.spv.getFrame().setVisible(false);
        final EndView end = new EndView(mainView, this, spv, ps);
        reset();
        end.getFrame().setVisible(true);
    }
    /**
     * 
     * @return true if the hero is dead
     */
    public boolean isGameOver() {
        return !getHero().isAlive();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasWon() {
        return getHero().hasWon();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Level getLevel() {
        return lv; 
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity entity, final EntityView entityView) {
        this.worldEntity.add(entity);
        this.setController.add(new EntityControllerImpl(entity, entityView));
    }
    /**
     * {@inheritDoc}
     */
    public void addBomb() {

        final Optional<BombImpl> plantedBomb = this.bc.plantBomb(getHero());
        if (plantedBomb.isPresent()) {
            this.worldEntity.add(plantedBomb.get());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getUnwalkableEntity() {
        return new CopyOnWriteArraySet<>(worldEntity.stream().filter(x -> x.getType().getIsWalkable() == EntityFeature.UNWALKABLE)
                .collect(Collectors.toSet()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getWalkableEntity() {
        return worldEntity.stream().filter(x -> x.getType().getIsWalkable() == EntityFeature.WALKABLE)
                .collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getBreakableEntity() {
        return new CopyOnWriteArraySet<>(worldEntity.stream().filter(x -> x.getType().getIsBreakable() == EntityFeature.BREAKABLE)
                .collect(Collectors.toSet()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getUnbreakableEntity() {
        return worldEntity.stream().filter(x -> x.getType().getIsBreakable() == EntityFeature.UNBREAKABLE)
                .collect(Collectors.toSet());
    }

    private void detectCollision() {
        this.setController.stream().map(c -> c.getCollisionManager()).forEach(c -> c.ifPresent(cc -> cc.detectCollision(getUnwalkableEntity())));
        this.bc.collision(getBreakableEntity());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public HeroImpl getHero() {
        return (HeroImpl) this.worldEntity.stream().filter(e -> e.getType() == EntityType.HERO).findFirst().get();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        removeEntities();
        detectCollision();
        this.setController.forEach(ec -> ec.update());
        this.spv.render(this.setController.stream().map(ec -> ec.getEntityView()).collect(Collectors.toSet()), this.bc.getBombView());
        this.bc.update();
    }
    private void removeEntities() {
        final List<Entity> entityToRemoved = this.worldEntity.stream().filter(e -> e.remove()).collect(Collectors.toList());
        this.ps.updateScore(entityToRemoved);
        this.worldEntity.removeAll(entityToRemoved);
        Set<EntityController> controllerToRemoved = this.setController.stream()
                .filter(c -> entityToRemoved.contains(c.getEntity()))
                .collect(Collectors.toSet());
        controllerToRemoved.forEach(c -> c.remove());
        controllerToRemoved = controllerToRemoved.stream().filter(ec -> ec.getEntity().getType() != EntityType.POWER_UP).collect(Collectors.toSet());
        this.setController.removeAll(controllerToRemoved);
        this.bc.removeBomb();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.worldEntity = new CopyOnWriteArrayList<>();
        this.setController = new CopyOnWriteArraySet<>();
    }
}
