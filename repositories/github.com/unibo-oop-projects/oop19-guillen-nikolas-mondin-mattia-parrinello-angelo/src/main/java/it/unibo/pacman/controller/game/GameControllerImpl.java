package it.unibo.pacman.controller.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.pacman.controller.SoundController;
import it.unibo.pacman.controller.entities.BlinkyController;
import it.unibo.pacman.controller.entities.EntityController;
import it.unibo.pacman.controller.entities.GhostController;
import it.unibo.pacman.controller.entities.GhostControllerImpl;
import it.unibo.pacman.controller.entities.MovableController;
import it.unibo.pacman.controller.entities.PacmanController;
import it.unibo.pacman.controller.entities.PacmanControllerImpl;
import it.unibo.pacman.controller.entities.SimpleEntityController;
import it.unibo.pacman.controller.utilities.MapIO;
import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.entities.Mortal;
import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.leaderboard.PlayerScoreImpl;
import it.unibo.pacman.model.loop.GameLoop;
import it.unibo.pacman.model.loop.GameLoopImpl;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.EndView;
import it.unibo.pacman.view.GUIFactory;
import it.unibo.pacman.view.MainMenuView;
import it.unibo.pacman.view.entities.MovableView;
import it.unibo.pacman.view.entities.ViewFactory;
import it.unibo.pacman.view.entities.ViewFactoryImpl;
import it.unibo.pacman.view.game.GameView;

/**
 * An Implementation for {@link GameController} Interface.
 */
public class GameControllerImpl implements GameController {
    private GameView view;
    private LoadMap loadMap;
    private ViewFactory viewFactory;
    private final MainMenuView mainView;
    private PlayerScoreImpl ps;
    private final GUIFactory gf;
    private Difficulty difficulty;
    private final Set<GhostController> ghostControllers = new HashSet<>();
    private PacmanController pacmanController;
    private Set<EntityController> wallControllers = new HashSet<>();
    private final Set<EntityController> entitySetControllers = new HashSet<>();
    private final Set<MovableController> movableControllers = new HashSet<>();
    private final Map<Movable, MovableView> ghosts = new HashMap<>();
    /**
     * Construct an implementation of {@link GameController}.
     * @param menuView {@link MainMenuView}
     * @param gf {@link GUIFactory}
     */
    public GameControllerImpl(final MainMenuView menuView, final GUIFactory gf) {
        this.mainView = menuView;
        this.gf = gf;
    }
    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public void startGame(final String mapName, final Difficulty difficulty) {
        this.difficulty = difficulty;
        Set<EntityController> pillControllers = new HashSet<>();
        Set<EntityController> powerpillControllers = new HashSet<>();
         Mortal pacman;
        final GameLoop engine = new GameLoopImpl(this);
        this.ps = new PlayerScoreImpl();
        try {
            this.loadMap = new LoadMap(mapName, difficulty);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.viewFactory = new ViewFactoryImpl();

        pacman = this.loadMap.getEntities().stream()
                                 .filter(p -> p.getType().equals(EntityType.PACMAN))
                                 .map(p -> (Mortal) p)
                                 .findFirst().get();
        this.ghosts.put(this.loadMap.getEntities().stream()
                                                  .filter(p -> p.getType().equals(EntityType.BLINKY))
                                                  .map(p -> (Movable) p)
                                                  .findFirst().get(), this.viewFactory.getBlinkyView());
        this.ghosts.put(this.loadMap.getEntities().stream()
                                                  .filter(p -> p.getType().equals(EntityType.INKY))
                                                  .map(p -> (Movable) p)
                                                  .findFirst().get(), this.viewFactory.getInkyView());
        this.ghosts.put(this.loadMap.getEntities().stream()
                                                  .filter(p -> p.getType().equals(EntityType.CLYDE))
                                                  .map(p -> (Movable) p)
                                                  .findFirst().get(), this.viewFactory.getClydeView());
        this.ghosts.put(this.loadMap.getEntities().stream()
                                                  .filter(p -> p.getType().equals(EntityType.PINKY))
                                                  .map(p -> (Movable) p)
                                                  .findFirst().get(), this.viewFactory.getPinkyView());

        this.wallControllers = this.createWallController(collectEntities(EntityType.WALL));
        pillControllers = this.createPillController(collectEntities(EntityType.PILL));
        powerpillControllers = this.createPowerPillController(collectEntities(EntityType.POWERPILL));

        this.entitySetControllers.addAll(pillControllers);
        this.entitySetControllers.addAll(powerpillControllers);
        this.entitySetControllers.addAll(wallControllers);

        this.ghostControllers.addAll(ghosts.entrySet().stream()
                                                      .filter(e -> e.getKey().getType().equals(EntityType.CLYDE) 
                                                                || e.getKey().getType().equals(EntityType.INKY))
                                                      .map(e -> new GhostControllerImpl(e.getValue(), e.getKey(), this.wallControllers))
                                                      .collect(Collectors.toSet()));

        this.ghostControllers.addAll(ghosts.entrySet().stream()
                                                      .filter(e -> e.getKey().getType().equals(EntityType.BLINKY) 
                                                              || e.getKey().getType().equals(EntityType.PINKY))
                                                      .map(e -> new BlinkyController(e.getValue(), e.getKey(), this.wallControllers))
                                                      .collect(Collectors.toSet()));
        this.pacmanController = new PacmanControllerImpl(pacman, this.viewFactory.getPacManView(), 
                this.entitySetControllers, this.ghostControllers, ps);
        this.view = new GameView.Builder(this.gf)
                                .map(new KeyInput(this.pacmanController), MapIO.getRows(), MapIO.getColumns())
                                .scoreboard(pacman, this.ps)
                                .build();

        this.movableControllers.add(this.pacmanController);
        this.movableControllers.addAll(this.ghostControllers);


        this.entitySetControllers.addAll(this.movableControllers);
        this.mainView.close();
        engine.startLoop();
    }
    /**
     * 
     * @param type
     * @return Set of Entities.
     */
    private Set<Entity> collectEntities(final EntityType type) {
        return this.loadMap.getEntities().stream()
                                         .filter(p -> p.getType().equals(type))
                                         .collect(Collectors.toSet());
    }
    /**
     * 
     * @param entities
     * @return Set Entity Controller.
     */
    private Set<EntityController> createPillController(final Set<Entity> pill) {
        return pill.stream()
                        .map(p -> new SimpleEntityController(p, this.viewFactory.getPillView()))
                        .collect(Collectors.toSet()); 
    }
    /**
     * 
     * @param entities
     * @return Set Entity Controller.
     */
    private Set<EntityController> createWallController(final Set<Entity> wall) {
        return wall.stream()
                        .map(p -> new SimpleEntityController(p, this.viewFactory.getWallView()))
                        .collect(Collectors.toSet()); 
    }
    /**
     * 
     * @param entities
     * @return Set Entity Controller.
     */
    private Set<EntityController> createPowerPillController(final Set<Entity> powerPill) {
        return powerPill.stream()
                        .map(p -> new SimpleEntityController(p, this.viewFactory.getPowerPillView()))
                        .collect(Collectors.toSet()); 
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        this.view.close();
        final EndView end = new EndView(this.mainView, gf, this.ps.getScore(), difficulty, this.hasWon());
        end.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        if (this.pacmanController.isRemoved()) {
        SoundController.getPacmanDeathSound().ifPresent(s -> s.play());
        }
        return this.pacmanController.isRemoved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasWon() {
        if (this.entitySetControllers.stream().filter(e -> e.getType().equals(EntityType.PILL)).collect(Collectors.toSet()).isEmpty()) {
            SoundController.getTriumphantSound().ifPresent(s -> s.play());
        }
        return this.entitySetControllers.stream().filter(e -> e.getType().equals(EntityType.PILL)).collect(Collectors.toSet()).isEmpty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.entitySetControllers.removeAll(this.entitySetControllers.stream()
                                                                     .filter(e -> e.isRemoved())
                                                                     .collect(Collectors.toSet()));
        this.entitySetControllers.stream()
                                 .filter(e -> e instanceof MovableController)
                                 .map(e -> (MovableController) e)
                                 .forEach(e -> e.move());
        this.entitySetControllers.stream()
                                 .filter(e -> e instanceof PacmanController)
                                 .map(e -> (PacmanController) e)
                                 .peek(e -> e.eatPowerPill())
                                 .peek(e -> e.eatGhost())
                                 .forEach(e -> e.eatPill());
        this.entitySetControllers.stream().forEach(e -> e.updateView());
        this.view.render(this.entitySetControllers);
    }

}
