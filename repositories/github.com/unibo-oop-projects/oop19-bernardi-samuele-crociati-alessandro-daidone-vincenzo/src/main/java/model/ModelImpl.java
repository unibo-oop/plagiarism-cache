package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import javafx.util.Pair;
import model.entities.AttackStatus;
import model.entities.AttackType;
import model.entities.Entity;
import model.entities.EntityStatus;
import model.entities.Hero;
import model.entities.MovementStatus;
import model.entities.MovementType;
import model.entities.Obstacle;
import model.entities.Teams;
import model.utilities.ActionPerformed;
import model.utilities.GameStatus;
import model.utilities.PlayerTurn;
import model.utilities.TerrainType;
import model.utilities.exceptions.MismatchedPlayerTurnException;
import model.utilities.exceptions.TeamAlreadyFullException;

public final class ModelImpl implements Model {
    private static final int DEFAULT_MAP_HEIGHT = 8;
    private static final int DEFAULT_TEAM_SIZE = 4;
    private static final int DEFAULT_STARTING_PICKER_SELECTION_POSITION = 0;
    private static final PlayerTurn DEFAULT_STARTING_PICKER_PLAYER = PlayerTurn.PLAYER_ONE;
    private static final PlayerTurn DEFAULT_STARTING_GAME_PLAYER_TURN = PlayerTurn.PLAYER_ONE;
    private static final GameStatus DEFAULT_STARTING_GAME_STATUS_TURN = GameStatus.UNINITIALIZED;

    private final List<Pair<Hero, Boolean>> heroPool;
    private final List<Hero> team1;
    private final List<Hero> team2;
    private final List<Obstacle> obstacles;
    private PlayerTurn pickerPlayerTurn;
    private PlayerTurn gamePlayerTurn;
    private GameStatus gameStatus;
    private int teamSize;
    private int pickerSelectionPosition;
    private Optional<Pair<Integer, Integer>> gameSelectionPosition;
    private Pair<Integer, Integer> oldGameSelection;
    private Pair<Integer, Integer> gameCursorPosition;
    private Map<Pair<Integer, Integer>, TerrainType> arenaMap;
    private int arenaHeight;

    /**
     * Generates the default ModelImpl.<br>
     * Each setting can be later modified with the appropriate methods.
     */
    public ModelImpl() {
        this(Collections.emptyList(), DEFAULT_TEAM_SIZE, DEFAULT_STARTING_PICKER_PLAYER,
                DEFAULT_STARTING_GAME_PLAYER_TURN, DEFAULT_STARTING_GAME_STATUS_TURN,
                DEFAULT_STARTING_PICKER_SELECTION_POSITION);
    }

    /**
     * Generates the ModelImpl with the specified settings.
     * 
     * @param heroPool
     *            The starting hero pool. If not specified (null or an empty
     *            collection) an empty list will be generated that can be later
     *            populated
     * @param teamSize
     *            The starting team size. It can be later modified.
     * @param startingPickerPlayerTurn
     *            The starting picker player turn. It can be later modified.
     * @param startingGamePlayerTurn
     *            The starting game player turn. It can be later modified.
     * @param startingGameStatus
     *            The starting game status. It can be later modified.
     * @param startingPosition
     *            The starting picker player position.
     */
    public ModelImpl(final Collection<Hero> heroPool, final int teamSize, final PlayerTurn startingPickerPlayerTurn,
            final PlayerTurn startingGamePlayerTurn, final GameStatus startingGameStatus, final int startingPosition) {
        this.team1 = new ArrayList<>();
        this.team2 = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.heroPool = new ArrayList<>();
        heroPool.stream().forEach(h -> this.addHeroToPool(h));
        this.teamSize = teamSize > 0 ? teamSize : DEFAULT_TEAM_SIZE;
        this.pickerPlayerTurn = startingPickerPlayerTurn;
        this.gamePlayerTurn = startingGamePlayerTurn;
        this.gameStatus = startingGameStatus;
        this.pickerSelectionPosition = startingPosition >= 0 ? startingPosition
                : DEFAULT_STARTING_PICKER_SELECTION_POSITION;
        this.oldGameSelection = new Pair<>(0, 0);
        this.gameCursorPosition = new Pair<>(0, 0);
        this.gameSelectionPosition = Optional.empty();
        this.arenaMap = new HashMap<>();
        this.arenaHeight = DEFAULT_MAP_HEIGHT;
    }

    @Override
    public void startGame() {
        this.setTeamPositions();
    }

    @Override
    public List<Hero> getTeamOne() {
        return ImmutableList.copyOf(team1);
    }

    @Override
    public List<Hero> getTeamTwo() {
        return ImmutableList.copyOf(team2);
    }

    @Override
    public List<Obstacle> getObstacles() {
        return ImmutableList.copyOf(this.obstacles);
    }

    @Override
    public boolean isTeamOneFull() {
        return this.team1.size() == this.teamSize;
    }

    @Override
    public boolean isTeamTwoFull() {
        return this.team2.size() == this.teamSize;
    }

    @Override
    public void addToTeamOne(final Hero hero) throws TeamAlreadyFullException, MismatchedPlayerTurnException {
        if (this.pickerPlayerTurn != PlayerTurn.PLAYER_ONE) {
            throw new MismatchedPlayerTurnException();
        }
        if (team1.size() < teamSize) {
            this.team1.add(Objects.requireNonNull(hero));
            this.heroPool.set(this.pickerSelectionPosition, new Pair<Hero, Boolean>(hero, false));
        } else {
            throw new TeamAlreadyFullException();
        }
    }

    @Override
    public void addToTeamTwo(final Hero hero) throws TeamAlreadyFullException, MismatchedPlayerTurnException {
        if (this.pickerPlayerTurn != PlayerTurn.PLAYER_TWO) {
            throw new MismatchedPlayerTurnException();
        }
        if (team2.size() < teamSize) {
            this.team2.add(Objects.requireNonNull(hero));
            this.heroPool.set(this.pickerSelectionPosition, new Pair<Hero, Boolean>(hero, false));
        } else {
            throw new TeamAlreadyFullException();
        }
    }

    @Override
    public void addObstacle(final Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    @Override
    public void removeFromTeamOne(final Hero hero) throws MismatchedPlayerTurnException {
        if (this.pickerPlayerTurn != PlayerTurn.PLAYER_ONE) {
            throw new MismatchedPlayerTurnException();
        }
        this.team1.remove(hero);
        this.heroPool.set(this.pickerSelectionPosition, new Pair<Hero, Boolean>(hero, true));
    }

    @Override
    public void removeFromTeamTwo(final Hero hero) throws MismatchedPlayerTurnException {
        if (this.pickerPlayerTurn != PlayerTurn.PLAYER_TWO) {
            throw new MismatchedPlayerTurnException();
        }
        this.team2.remove(hero);
        this.heroPool.set(this.pickerSelectionPosition, new Pair<Hero, Boolean>(hero, true));
    }

    @Override
    public List<Pair<Hero, Boolean>> getHeroPool() {
        return ImmutableList.copyOf(this.heroPool);
    }

    @Override
    public void setHeroPool(final Collection<Hero> heroPool) {
        this.heroPool.clear();
        heroPool.stream().forEach(h -> addHeroToPool(h));
    }

    @Override
    public void changePickerTurn() {
        this.pickerPlayerTurn = this.pickerPlayerTurn == PlayerTurn.PLAYER_ONE ? PlayerTurn.PLAYER_TWO
                : PlayerTurn.PLAYER_ONE;
    }

    @Override
    public PlayerTurn getPickerPlayerTurn() {
        return this.pickerPlayerTurn;
    }

    @Override
    public void changeGameTurn() {
        this.gamePlayerTurn = this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? PlayerTurn.PLAYER_TWO
                : PlayerTurn.PLAYER_ONE;
        this.gameSelectionPosition = Optional.empty();
        Teams.energizeAllTeam(this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? this.team1 : this.team2);
    }

    @Override
    public PlayerTurn getGamePlayerTurn() {
        return this.gamePlayerTurn;
    }

    @Override
    public Map<Pair<Integer, Integer>, TerrainType> getArenaMap() {
        return new HashMap<>(this.arenaMap);
    }

    @Override
    public void setTeamSize(final int teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public void generateArenaMap(final List<TerrainType> arenaMap, final int height, final int width) {
        int i = 0;
        int j = 0;
        this.arenaHeight = height;
        for (final var tt : arenaMap) {
            this.arenaMap.put(new Pair<>(i, j), tt);
            j++;
            if (j == width) {
                i++;
                j = 0;
            }
        }
    }

    @Override
    public void setPickerSelection(final int index) {
        this.pickerSelectionPosition = index;
    }

    @Override
    public ActionPerformed selectPickerSelection() throws TeamAlreadyFullException, MismatchedPlayerTurnException {
        final Pair<Hero, Boolean> p = this.heroPool.get(this.pickerSelectionPosition);
        final boolean isPickable = p.getValue();
        final Hero selectedHero = p.getKey();
        if (isPickable) {
            if (this.pickerPlayerTurn == PlayerTurn.PLAYER_ONE) {
                this.addToTeamOne(selectedHero);
                return ActionPerformed.ADD_TO_TEAM_ONE;
            } else {
                this.addToTeamTwo(selectedHero);
                return ActionPerformed.ADD_TO_TEAM_TWO;
            }
        } else {
            if (this.team1.contains(selectedHero)) {
                this.removeFromTeamOne(selectedHero);
                return ActionPerformed.REMOVE_FROM_TEAM_ONE;
            } else {
                this.removeFromTeamTwo(selectedHero);
                return ActionPerformed.REMOVE_FROM_TEAM_TWO;
            }
        }
    }

    @Override
    public int getPickerSelectionPosition() {
        return this.pickerSelectionPosition;
    }

    @Override
    public List<String> getPickerSelectionInfo() {
        return this.heroPool.get(this.pickerSelectionPosition).getKey().getInfo();
    }

    @Override
    public void setGameCursorPosition(final Pair<Integer, Integer> newPosition) {
        this.gameCursorPosition = newPosition;
    }

    @Override
    public void setGameSelectionPosition(final Pair<Integer, Integer> newPosition) {
        this.gameSelectionPosition = Optional.ofNullable(newPosition);
    }

    @Override
    public Optional<Pair<Integer, Integer>> getGameSelectionPosition() {
        return this.gameSelectionPosition;
    }

    @Override
    public Pair<Integer, Integer> getGameCursorSelectionPosition() {
        return this.gameCursorPosition;
    }

    @Override
    public ActionPerformed selectGameCursorSelection() {
        final Optional<Entity> cursorEntity = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameCursorPosition)).findFirst();
        if (cursorEntity.isPresent()) {
            if (this.gameSelectionPosition.isPresent()) {
                final Entity selectionEntity = this.getAliveEntities().stream()
                        .filter(e -> e.getPosition().equals(this.gameSelectionPosition.get())).findFirst().get();
                if (!selectionEntity.isAttackExhausted() && this.canBeFought(cursorEntity.get())) {
                    cursorEntity.get().loseHP(selectionEntity.getAttack());
                    if (cursorEntity.get().getCurrentHP() <= 0) {
                        cursorEntity.get().setStatus(EntityStatus.DEAD);
                    }
                    selectionEntity.setAttackStatus(AttackStatus.EXHAUSTED);
                    if (Teams.isTeamDead(this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? this.team2 : this.team1)) {
                        this.gameStatus = this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? GameStatus.PLAYER1_WON
                                : GameStatus.PLAYER2_WON;
                        return this.gameStatus == GameStatus.PLAYER1_WON ? ActionPerformed.PLAYER1_WON
                                : ActionPerformed.PLAYER2_WON;
                    }
                    if (Teams.isTeamExhausted(this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? this.team1 : this.team2)) {
                        this.changeGameTurn();
                    }
                    return ActionPerformed.ATTACK_ENTITY;
                } else {
                    this.gameSelectionPosition = Optional.empty();
                    return ActionPerformed.UNSELECT_ENTITY;
                }
            } else {
                final boolean isPickable = this.gamePlayerTurn == PlayerTurn.PLAYER_ONE
                        ? this.team1.contains(cursorEntity.get())
                        : this.team2.contains(cursorEntity.get());
                if (isPickable) {
                    this.setGameSelectionPosition(cursorEntity.get().getPosition());
                    return ActionPerformed.SELECT_ENTITY;
                }
            }
        } else {
            if (this.gameSelectionPosition.isPresent()) {
                final Entity selectionEntity = this.getAliveEntities().stream()
                        .filter(e -> e.getPosition().equals(this.gameSelectionPosition.get())).findFirst().get();
                if (!selectionEntity.isMovementExhausted() && this.canBeMovedTo(this.gameCursorPosition)) {
                    selectionEntity.move(this.gameCursorPosition);
                    selectionEntity.setMovementStatus(MovementStatus.EXHAUSTED);
                    this.oldGameSelection = this.gameSelectionPosition.get();
                    this.gameSelectionPosition = Optional.empty();
                    if (Teams.isTeamExhausted(this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? this.team1 : this.team2)) {
                        this.changeGameTurn();
                    }
                    return ActionPerformed.MOVE_ENTITY;
                } else {
                    this.gameSelectionPosition = Optional.empty();
                    return ActionPerformed.UNSELECT_ENTITY;
                }
            }
        }
        return ActionPerformed.NONE;
    }

    @Override
    public List<String> getGameSelectionInfo() {
        final Optional<Entity> entity = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameSelectionPosition.orElse(null))).findFirst();
        return entity.isPresent() ? entity.get().getInfo() : null;
    }

    @Override
    public List<Pair<Integer, Integer>> getGameSelectionAttackCandidates() {
        if (this.gameSelectionPosition.isEmpty()) {
            return Collections.emptyList();
        }
        final int attackRange = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameSelectionPosition.get())).findFirst().get().getAttackType()
                .getRange();
        return this.getAttackCandidates(this.gameSelectionPosition.get(), attackRange);
    }

    @Override
    public List<Pair<Integer, Integer>> getGameSelectionMovementCandidates() {
        if (this.gameSelectionPosition.isEmpty()) {
            return Collections.emptyList();
        }
        final MovementType movementType = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameSelectionPosition.get())).findFirst().get()
                .getMovementType();
        return this.getMovementCandidates(this.gameSelectionPosition.get(), movementType);
    }

    @Override
    public List<String> getGameCursorSelectionInfo() {
        final Optional<Entity> entity = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameCursorPosition)).findFirst();
        return entity.isPresent() ? entity.get().getInfo() : null;
    }

    @Override
    public List<Pair<Integer, Integer>> getGameCursorSelectionAttackCandidates() {
        final Optional<Entity> ent = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameCursorPosition)).findFirst();
        if (ent.isEmpty()) {
            return Collections.emptyList();
        }
        final int attackRange = ent.get().getAttackType().getRange();
        return this.getAttackCandidates(this.gameCursorPosition, attackRange);
    }

    @Override
    public List<Pair<Integer, Integer>> getGameCursorSelectionMovementCandidates() {
        final Optional<Entity> ent = this.getAliveEntities().stream()
                .filter(e -> e.getPosition().equals(this.gameCursorPosition)).findFirst();
        if (ent.isEmpty()) {
            return Collections.emptyList();
        }
        final MovementType movementType = ent.get().getMovementType();
        return this.getMovementCandidates(this.gameCursorPosition, movementType);
    }

    @Override
    public List<Entity> getAliveEntities() {
        return new ArrayList<>(Stream
                .of(this.team1.stream().filter(e -> !e.getStatus().equals(EntityStatus.DEAD)),
                        this.team2.stream().filter(e -> !e.getStatus().equals(EntityStatus.DEAD)),
                        this.obstacles.stream().filter(o -> !o.getStatus().equals(EntityStatus.DEAD)))
                .flatMap(s -> s).collect(Collectors.toList()));
    }

    @Override
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    @Override
    public Pair<Integer, Integer> getOldGameSelection() {
        return this.oldGameSelection;
    }

    /**
     * Sets the initial positions for every hero in each team depending on map size.
     * and attack type (ranged units are further from enemy team)
     */
    private void setTeamPositions() {
        int i = 0;
        int j = 0;
        for (final Hero h : this.team1) {
            if (h.getAttackType() != AttackType.BOW) {
                h.move(new Pair<>(1, i));
                i++;
            } else {
                h.move(new Pair<>(0, j));
                j++;
            }
        }
        i = 0;
        j = 0;
        for (final Hero h : this.team2) {
            if (h.getAttackType() != AttackType.BOW) {
                h.move(new Pair<>(this.arenaHeight - 2, i));
                i++;
            } else {
                h.move(new Pair<>(this.arenaHeight - 1, j));
                j++;
            }
        }
    }

    /**
     * Calculates all of the positions reachable to attack from the hero positioned
     * on initialPosition.
     * 
     * @param initialPosition
     *            the initial position
     * @param attackRange
     *            the attack range
     * @return the list of candidates the hero in initialPosition can attack
     */
    private List<Pair<Integer, Integer>> getAttackCandidates(final Pair<Integer, Integer> initialPosition,
            final int attackRange) {
        final List<Pair<Integer, Integer>> candidates = new ArrayList<>();
        candidates.add(initialPosition);
        for (int i = attackRange; i > 0; i--) {
            final List<Pair<Integer, Integer>> tmp = new ArrayList<>(candidates);
            for (final Pair<Integer, Integer> oldCandidate : tmp) {
                if (!this.obstacles.stream().map(o -> o.getPosition()).collect(Collectors.toList())
                        .contains(oldCandidate)) {
                    final List<Pair<Integer, Integer>> newCandidates = this.arenaMap.entrySet().stream()
                            .map(e -> e.getKey())
                            .filter(p -> p.getKey() == oldCandidate.getKey()
                                    && (p.getValue() == oldCandidate.getValue() - 1
                                            || p.getValue() == oldCandidate.getValue() + 1)
                                    || p.getValue() == oldCandidate.getValue()
                                            && (p.getKey() == oldCandidate.getKey() - 1
                                                    || p.getKey() == oldCandidate.getKey() + 1))
                            .distinct().collect(Collectors.toList());
                    newCandidates.forEach((candidate) -> {
                        if (!candidates.contains(candidate)) {
                            candidates.add(candidate);
                        }
                    });
                }
            }
        }
        candidates.remove(initialPosition);
        return candidates;
    }

    /**
     * Calculates all of the positions reachable to move to from the hero positioned
     * on initialPosition.
     * 
     * @param initialPosition
     *            the initial position
     * @param movementType
     *            the movement type
     * @return the list of candidates the hero in initialPosition can move to
     */
    private List<Pair<Integer, Integer>> getMovementCandidates(final Pair<Integer, Integer> initialPosition,
            final MovementType movementType) {
        final List<Pair<Integer, Integer>> candidates = new ArrayList<>();
        candidates.add(initialPosition);
        for (int i = movementType.getRange(); i > 0; i--) {
            final List<Pair<Integer, Integer>> tmp = new ArrayList<>(candidates);
            for (final Pair<Integer, Integer> oldCandidate : tmp) {
                final List<Pair<Integer, Integer>> newCandidates = this.arenaMap.entrySet().stream()
                        .filter(e -> movementType.canStepOn(e.getValue())).map(e -> e.getKey())
                        .filter(p -> p.getKey() == oldCandidate.getKey()
                                && (p.getValue() == oldCandidate.getValue() - 1
                                        || p.getValue() == oldCandidate.getValue() + 1)
                                || p.getValue() == oldCandidate.getValue() && (p.getKey() == oldCandidate.getKey() - 1
                                        || p.getKey() == oldCandidate.getKey() + 1))
                        .distinct().collect(Collectors.toList());
                newCandidates.forEach((candidate) -> {
                    if (!candidates.contains(candidate) && !this.obstacles.stream().map(o -> o.getPosition())
                            .collect(Collectors.toList()).contains(candidate)) {
                        candidates.add(candidate);
                    }
                });
            }
        }
        candidates.remove(initialPosition);
        return candidates;
    }

    /**
     * Calculates if the currently selected hero can move on the cell specified by
     * newPosition.
     * 
     * @param newPositon
     *            the position you want to move to
     * @return whether the currently selected hero can move towards the position or
     *         not
     */
    private boolean canBeMovedTo(final Pair<Integer, Integer> newPositon) {
        return this.getGameSelectionMovementCandidates().contains(newPositon) ? true : false;
    }

    /**
     * Calculates if the currently selected hero can attack the specified entity.
     * 
     * @param entity
     *            the entity the currently selected hero wants to attack
     * @return whether the currently selected hero can attack the provided entity or
     *         not
     */
    private boolean canBeFought(final Entity entity) {
        if ((this.gamePlayerTurn == PlayerTurn.PLAYER_ONE ? this.team2.contains(entity) : this.team1.contains(entity))
                || this.obstacles.contains(entity)) {
            return this.getGameSelectionAttackCandidates().contains(entity.getPosition()) ? true : false;
        }
        return false;
    }

    /**
     * Adds an hero to the pool.
     * 
     * @param hero
     *            the hero to add
     */
    private void addHeroToPool(final Hero hero) {
        this.heroPool.add(new Pair<>(Objects.requireNonNull(hero), true));
    }
}
