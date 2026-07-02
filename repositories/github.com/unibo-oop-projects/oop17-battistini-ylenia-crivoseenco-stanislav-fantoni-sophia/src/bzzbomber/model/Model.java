package bzzbomber.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import bzzbomber.controller.Controller;
import bzzbomber.game.Game;
import bzzbomber.model.entities.Block;
import bzzbomber.model.entities.Bomb;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.BzzBomberImpl;
import bzzbomber.model.entities.Door;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.Explosion;
import bzzbomber.model.entities.HealthPoint;
import bzzbomber.model.entities.Insects;
import bzzbomber.model.utilities.FileLoader;

/**
 * Model class.
 */
public final class Model implements ModelInterface {

    private static final int NUM_OF_LEVELS = 3;
    private static final double EXPLOSION_PERC = 1.5;
    private static final String[] BOMBER_POSITIONS = new String[] { "/Positions/bomber_pos_lvl1.txt",
            "/Positions/bomber_pos_lvl2.txt", "/Positions/bomber_pos_lvl3.txt" };

    private final List<Point> bomberPositions;
    private final List<Level> levels;
    private int currentLevel;
    private final BzzBomber hero;

    private final Timer timer;

    private Controller controller;

    /**
     * Constructor of Model.
     */
    public Model() {
        this.bomberPositions = new ArrayList<>();
        this.levels = new ArrayList<>();
        this.timer = new Timer(Controller.NUM_SEC_INIT_1);
        this.currentLevel = 0;

        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            this.levels.add(new Level(this, i));
            this.bomberPositions
                    .add(FileLoader.loadStartPositionBomber(BOMBER_POSITIONS[i], Game.TILE_WIDTH, Game.TILE_HEIGHT));
        }

        this.hero = new BzzBomberImpl(this.bomberPositions.get(0), this);

    }

    @Override
    public void reset(final ResetType rt) {
        this.hero.reset(rt);
        if (!this.bomberPositions.isEmpty()) {
            final Iterator<Point> it = this.bomberPositions.iterator();
            if (it.hasNext()) {
                final Point p = it.next();
                this.hero.setPosition(p);
            }
        }
        this.levels.forEach(e -> e.getEntityManager().removeWorldEntities());
        this.levels.forEach(e -> {
            e.createInsect();
            e.createBox();
            e.createHealth();
        });
        this.timer.setSecond(Controller.NUM_SEC_INIT_1);

        this.currentLevel = 0;
        this.goToLevel(this.currentLevel);
    }

    @Override
    public void nextLevel() {
        if (this.currentLevel < this.levels.size() - 1) {
            this.currentLevel++;
            this.goToLevel(this.currentLevel);
        } else {
            this.heroWin();
        }
    }

    private void goToLevel(final int level) {
        final Point point = this.bomberPositions.get(level);
        this.hero.setPosition(point);
        this.hero.reset(ResetType.LEVEL_CHANGED);
        int numSeconds;
        switch (controller.getLevelInt()) {
        case 1:
            numSeconds = Controller.NUM_SEC_INIT_2;
            break;
        case 2:
            numSeconds = Controller.NUM_SEC_INIT_3;
            break;
        default:
            numSeconds = Controller.NUM_SEC_INIT_1;
        }
        this.timer.setSecond(numSeconds);

        this.controller.levelChanged();
    }

    @Override
    public void changeLife() {
        this.controller.changeLife();
    }

    @Override
    public void changeEnemy() {
        this.controller.changeEnemy();
    }

    @Override
    public Level getCurrentLevel() {
        return this.levels.get(this.currentLevel);
    }

    @Override
    public synchronized BzzBomber getBomber() {
        return this.hero;
    }

    @Override
    public synchronized List<Entity> getEntities() {
        return this.levels.get(this.currentLevel).getEntityManager().getWorldEntities();
    }

    @Override
    public Set<Bomb> getAllBombs() {
        return new HashSet<>(this.getEntities().stream().filter((a) -> a instanceof Bomb).map(e -> (Bomb) e)
                .collect(Collectors.toSet()));
    }

    @Override
    public Set<HealthPoint> getAllHeart() {
        return new HashSet<>(this.getEntities().stream().filter((a) -> a instanceof HealthPoint)
                .map(e -> (HealthPoint) e).collect(Collectors.toSet()));
    }

    @Override
    public Set<Block> getAllBlock() {
        return new HashSet<>(this.getEntities().stream().filter((a) -> a instanceof Block).map(e -> (Block) e)
                .collect(Collectors.toSet()));
    }

    @Override
    public Set<Insects> getAllInsects() {
        return new HashSet<>(this.getEntities().stream().filter((a) -> a instanceof Insects).map(e -> (Insects) e)
                .collect(Collectors.toSet()));
    }

    @Override
    public Set<Explosion> getAllExplosions() {
        return new HashSet<>(this.getEntities().stream().filter((a) -> a instanceof Explosion).map(e -> (Explosion) e)
                .collect(Collectors.toSet()));
    }

    @Override
    public Optional<Door> getDoor() {
        return this.getEntities().stream().filter(d -> d instanceof Door).map(d -> (Door) d).findFirst();
    }

    @Override
    public synchronized void manageBombExplosion() {
        this.getAllBombs().forEach(e -> e.decrement());
        this.getAllExplosions().forEach(e -> e.decrement());
        for (final Bomb b : this.getAllBombs()) {
            if (b.isExploded()) {
                this.getCurrentLevel().getEntityManager()
                        .addEntity(new Explosion(
                                new Point(b.getPosition().x - (int) (b.getCollisionBox().width * EXPLOSION_PERC),
                                        b.getPosition().y - (int) (b.getCollisionBox().height * EXPLOSION_PERC)),
                                this));
                this.getCurrentLevel().getEntityManager().removeEntity(b);
            }
        }
        for (final Explosion e : this.getAllExplosions()) {
            if (e.isTimefinish()) {
                this.getCurrentLevel().getEntityManager().removeEntity(e);
                this.getBomber().setNumberBomb();
            }
        }
    }

    @Override
    public synchronized void manageInsects() {
        for (final Insects i : this.getAllInsects()) {
            i.move(this.getAllBlock(), this.getAllBombs(), this.getBomber());
            if (!i.isAlive()) {
                this.getBomber().addEnemyKilled();
                this.getCurrentLevel().getEntityManager().removeEntity(i);
            }
        }
    }

    @Override
    public synchronized void manageHealth() {
        for (final HealthPoint heart : this.getAllHeart()) {
            if (heart.isTaken()) {
                this.getCurrentLevel().getEntityManager().removeEntity(heart);
            }
        }
    }

    @Override
    public void setController(final Controller c) {
        this.controller = c;
    }

    @Override
    public Timer getTimer() {
        return this.timer;
    }

    @Override
    public void heroWin() {
        this.controller.setGameWin(true);
    }

    @Override
    public int getLevelInt() {
        return this.currentLevel;
    }
}
