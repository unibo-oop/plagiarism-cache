package it.unibo.biscia.core;

import it.unibo.biscia.core.Player.PlayerManaged;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.GenericEventSubject;
import it.unibo.biscia.events.GenericEventSubjectImpl;
import it.unibo.biscia.events.StateObserver;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * a basic implementation of controller of game.
 *
 */
public final class ControllerImpl implements Controller, ActionObserver {
    private final GenericEventSubject<StateObserver> stateSubject = new GenericEventSubjectImpl<>();
    private Controller.Speed speed;
    private final boolean increaseSpeed;
    private volatile boolean pause = true;
    private boolean started;
    private boolean gameOver;
    private final Map<Player.PlayerManaged, Optional<Direction>> players = new LinkedHashMap<>();
    private Timer timer;
    private final LevelLoader levelLoader;
    private Level.LevelManaged level;
    private EntityFactory entityFactory;
    private int intFodEnergy;

    /**
     * make a new controller.
     * 
     * @param players       list of names of players, must be > 0, uniqueness and
     *                      not empty or blank
     * @param initialSpeed  speed to start, {@value MIN_SPEED} to
     *                      {@value MAX_SPEED}, is the speed for all entities to
     *                      move
     * @param increaseSpeed if speed increase between levels
     */
    public ControllerImpl(final List<String> players, final int initialSpeed, final boolean increaseSpeed) {
        final String sep = System.getProperty("file.separator");
        this.levelLoader = new LevelLoaderImpl(
                System.getProperty("user.dir") + sep + ".." + sep + "core" + sep + "assets" + sep + "levels" + sep);
        if (initialSpeed < 0 || initialSpeed > Controller.MAX_SPEED) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(players);
        if (players.isEmpty()) {
            throw new IllegalArgumentException("no player's names passed");
        }
        if (players.size() > players.stream().map(s -> s.hashCode()).distinct().count()) {
            throw new IllegalArgumentException("not unique player's names");
        }
        if (players.stream().filter(s -> s.isEmpty() || s.isBlank()).count() > 0) {
            throw new IllegalArgumentException("absent player name");
        }

        for (final String name : players) {
            this.players.put(new PlayerImpl(name), Optional.empty());
        }

        this.increaseSpeed = increaseSpeed;
        if (initialSpeed == 0) {
            this.speed = Speed.STATIC;
        } else {
            if (initialSpeed == Controller.MAX_SPEED) {
                this.speed = Speed.MAX;
            } else {
                this.speed = Speed.values()[Math.floorDiv(initialSpeed - 1, Speed.values().length) + 1];
            }
        }
    }

    @Override
    public void move(final Player player, final Direction direction) {
        verifyIsStarted();
        // accept command only out of pause
        if (!this.pause) {
            final Optional<PlayerManaged> op = this.players.keySet().stream().filter(p -> p.equals(player)).findAny();
            if (op.isEmpty()) {
                throw new InvalidParameterException();
            }
            synchronized (this.players) {
                this.players.put(op.get(), Optional.of(direction));
            }
        }
    }

    private void startTimer() {
        if (!Objects.isNull(this.timer) && !this.timer.stop) {
            this.timer.stopTimer();
        }
        int interval;

        if (this.speed.equals(Speed.STATIC)) {
            interval = (Timer.MAX_TIMER_INTERVAL - Timer.MIN_TIMER_INTERVAL) / 2 + Timer.MIN_TIMER_INTERVAL;
        } else {
            if (this.speed.equals(Speed.MAX)) {
                interval = Timer.MIN_TIMER_INTERVAL;
            } else {
                interval = Speed.values().length - this.speed.ordinal() - 1;
                interval = ((Timer.MAX_TIMER_INTERVAL - Timer.MIN_TIMER_INTERVAL) / (Speed.values().length - 1))
                        * interval + Timer.MIN_TIMER_INTERVAL;
            }
        }
        this.pause = false;
        this.timer = new ControllerImpl.Timer(interval);
        timer.start();
    }

    private void stopTimer() {
        this.pause = true;
        if (!Objects.isNull(this.timer)) {
            this.timer.stopTimer();
        }

    }

    @Override
    public void pauseAndResume() {
        verifyIsStarted();
        if (this.pause) {
            this.pause = false;
            this.stateSubject.notify(o -> o.gameResume());
            this.startTimer();
        } else {
            this.pause = true;
            this.stopTimer();
            this.stateSubject.notify(o -> o.gamePause());
        }
    }

    @Override
    public void end() {
        this.pause = true;
        this.stopTimer();
    }

    @Override
    public void attachStateObserver(final StateObserver observer) {
        stateSubject.attach(observer);
    }

    @Override
    public void detachStateObserver(final StateObserver observer) {
        stateSubject.detach(observer);
    }

    @Override
    public List<Player> getPlayers() {
        return Collections
                .unmodifiableList(this.players.keySet().stream().map(p -> (Player) p).collect(Collectors.toList()));
    }

    private Set<Entity.EntityManaged> addSnakeAndFood() {
        final Set<Entity.EntityManaged> ret = new HashSet<>();
        this.players.forEach((p, s) -> {
            if (Objects.isNull(p.getEntity()) && p.getLives() > 0) {

                p.setDirectable(this.entityFactory.makeBabySnake(!this.speed.equals(Speed.STATIC)));
                this.level.addEntity(p.getDirectable());
                ret.add(p.getDirectable());
            }
        });
        if (this.level.getEntities().stream().filter(e -> e.getType().equals(EntityType.FOOD)).findAny().isEmpty()) {
            this.intFodEnergy++;
            final var food = this.entityFactory.makeCasualFood(this.intFodEnergy);
            this.level.addEntity(food);
            ret.add(food);
        }
        return ret;
    }

    private Set<Entity.EntityManaged> prepareNewLevel(final Level.LevelManaged level) {
        this.level = level;
        this.intFodEnergy = 0;
        this.entityFactory = new EntityFactoryImpl(this.level);
        this.players.keySet().forEach(p -> p.setDirectable(null));
        return addSnakeAndFood();
    }

    @Override
    public void start() {
        if (this.started) {
            throw new IllegalStateException();
        }
        this.pause = true;
        this.started = true;
        this.prepareNewLevel(this.levelLoader.getFirstLevel());
        this.sendNewLevelMessage();
    }

    private void sendEntityMessage(final Consumer<List<Entity>> event, final Set<Entity> entities) {
        if (!Objects.isNull(event) && !Objects.isNull(entities) && !entities.isEmpty()) {
            event.accept(Collections.unmodifiableList(entities.stream().collect(Collectors.toList())));
        }
    }

    private void sendEntityMessages(final Set<Entity> removed, final Set<Entity> updated, final Set<Entity> added) {
        this.sendEntityMessage(l -> this.stateSubject.notify(o -> o.remove(l)), removed);
        this.sendEntityMessage(l -> this.stateSubject.notify(o -> o.update(l)), updated);
        this.sendEntityMessage(l -> this.stateSubject.notify(o -> o.add(l)), added);
    }

    private void sendPlayerMessages(final Set<Player.PlayerManaged> players) {
        if (!players.isEmpty()) {
            players.forEach(p -> {
                this.stateSubject.notify(o -> o.updatePlayer((Player) p));
            });
        }
    }

    private void sendNewLevelMessage() {
        this.stopTimer();
        this.stateSubject.notify(o -> o.newLevel((Level) this.level));
        sendPlayerMessages(this.players.keySet());
    }

    private Set<PlayerManaged> getPlayerEat(final Map<Entity, Integer> eat) {
        return this.players.keySet().stream().filter(p -> eat.keySet().contains(p.getEntity()))
                .peek(p -> p.addPoints(eat.get(p.getEntity()) * Player.POINTS_FOR_FOOD_ENERGY))
                .collect(Collectors.toSet());
    }

    private Set<PlayerManaged> getPlayerDead(final Set<Entity> dead) {
        return this.players.keySet().stream().filter(p -> dead.contains(p.getEntity())).peek(p -> p.dead())
                .collect(Collectors.toSet());
    }

    private Set<PlayerManaged> getPlayerTrimmed(final Map<Entity, Integer> trimmed) {
        return this.players.keySet().stream().filter(p -> trimmed.keySet().contains(p.getEntity()))
                .peek(p -> p.addPoints(trimmed.get(p.getEntity()) * Player.POINTS_FOR_FOOD_ENERGY * -1))
                .collect(Collectors.toSet());
    }

    private void increaseSpeed() {
        if (this.increaseSpeed && this.speed != Controller.Speed.MAX) {
            this.speed = Controller.Speed.values()[this.speed.ordinal() + 1];
        }
    }

    private void clickTimer() {
        final Map<Entity.EntityManaged.Movable.Directable, Direction> commands = new HashMap<>();
        // make a list of command from players and reset previous command
        synchronized (this.players) {
            this.players.entrySet().stream().filter(e -> e.getValue().isPresent())
                    .filter(e -> !Objects.isNull(e.getKey().getDirectable()))
                    .peek(e -> commands.put(e.getKey().getDirectable(), e.getValue().get()))
                    .forEach(e -> e.setValue(Optional.empty()));
        }
        // perform movement and detect interactions
        final InteractionManager interactionManager = this.level.getInteractionManager();
        if (!interactionManager.performMovement(commands)) {
            return;
        }

        // analyze interaction and progress of game
        final Set<PlayerManaged> playersUpdates = new HashSet<>();
        Set<Player.PlayerManaged> mod;
        mod = this.getPlayerEat(interactionManager.getEat());
        boolean mangiato = !mod.isEmpty();
        if (mangiato) {
            playersUpdates.addAll(mod);
        }
        mod = this.getPlayerDead(interactionManager.getRemoved());
        final boolean morto = !mod.isEmpty();
        if (morto) {
            playersUpdates.addAll(mod);
        }
        playersUpdates.addAll(this.getPlayerTrimmed(interactionManager.getTrimmed()));
        if (!mangiato && this.level.getAnalyzer().getEntityOfType(EntityType.FOOD).isEmpty()) {
            mangiato = true;
        }
        // non è successo niente (non morto non mangiato)
        // prosegui (non morto, mangiato ma non finito il cibo)
        // reset livello (morto ma non finite le vite)
        // nuovo livello (non morto, mangiato e finitoil cibo)
        // gameover (morto e finite le vite
        final Set<Entity> added = new HashSet<>();
        if (!(mangiato || morto)) {
            sendEntityMessages(interactionManager.getRemoved(), interactionManager.getUpdated(), added);
            sendPlayerMessages(playersUpdates);
        } else {
            if (!morto) {
                if (this.level.getAnalyzer().getEatables().isEmpty()) {
                    if (this.intFodEnergy < Entity.EntityManaged.Eatable.MAX_ENERGY) {
                        // proseguo
                        addSnakeAndFood().forEach(e -> added.add(e));
                        sendEntityMessages(interactionManager.getRemoved(), interactionManager.getUpdated(), added);
                        sendPlayerMessages(playersUpdates);
                    } else {
                        // nuovo livello
                        this.stopTimer();
                        this.increaseSpeed();
                        sendEntityMessages(interactionManager.getRemoved(), interactionManager.getUpdated(),
                                Collections.emptySet());
                        this.prepareNewLevel(this.levelLoader.getNextLevel(this.level));
                        this.sendNewLevelMessage();

                    }
                }
            } else {
                if (this.players.keySet().stream().filter(p -> p.getLives() == 0).findAny().isPresent()) {
                    // gameover
                    this.gameOver = true;
                    this.stopTimer();
                    sendEntityMessages(Collections.emptySet(), interactionManager.getUpdated(), Collections.emptySet());
                    sendPlayerMessages(playersUpdates);
                    this.stateSubject.notify(o -> o.gameOver());
                } else {
                    // reset livello
                    this.stopTimer();
                    this.prepareNewLevel(this.levelLoader.getLevel(this.level.getCardinal()));
                    this.sendNewLevelMessage();
                }
            }
        }
    }

    private void verifyIsStarted() {
        if (!this.started) {
            throw new IllegalStateException("game not started");
        } else {
            if (this.gameOver) {
                throw new IllegalStateException("game ended");
            }
        }
    }

    private final class Timer extends Thread {
        private static final int MIN_TIMER_INTERVAL = 10;
        private static final int MAX_TIMER_INTERVAL = 200;
        private volatile boolean stop;
        private final int interval;

        Timer(final int interval) {
            if (interval < MIN_TIMER_INTERVAL) {
                this.interval = MIN_TIMER_INTERVAL;
            } else {
                if (interval > MAX_TIMER_INTERVAL) {
                    this.interval = MAX_TIMER_INTERVAL;
                } else {
                    this.interval = interval;
                }
            }
        }

        public void stopTimer() {
            this.stop = true;
        }

        @Override
        public void run() {
            while (!(this.isInterrupted() || this.stop)) {
                ControllerImpl.this.clickTimer();
                try {
                    sleep(this.interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
