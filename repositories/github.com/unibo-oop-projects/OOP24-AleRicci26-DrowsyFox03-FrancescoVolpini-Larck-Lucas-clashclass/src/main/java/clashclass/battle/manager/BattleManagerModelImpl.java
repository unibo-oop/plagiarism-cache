package clashclass.battle.manager;

import clashclass.ai.behaviourtree.BehaviourTree;
import clashclass.ai.behaviourtree.blackboard.BlackboardPropertyImpl;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.pathfinding.AiNodesBuilder;
import clashclass.ai.pathfinding.AiNodesBuilderImpl;
import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.battle.battlereport.BattleReportController;
import clashclass.battle.battlereport.BattleReportControllerImpl;
import clashclass.battle.battlereport.BattleReportModelImpl;
import clashclass.battle.battlereport.BattleReportView;
import clashclass.battle.battlereport.VillageDestructionManager;
import clashclass.battle.battlereport.VillageDestructionManagerImpl;
import clashclass.battle.destruction.BattleTroopsBehaviorManager;
import clashclass.battle.destruction.BattleTroopsBehaviorManagerImpl;
import clashclass.battle.destruction.EndBattleAllVillageDestroyedImpl;
import clashclass.battle.destruction.DestructionObservable;
import clashclass.battle.destruction.EndBattleAllVillageDestroyed;
import clashclass.battle.destruction.EndBattleTimerIsOver;
import clashclass.battle.destruction.EndBattleTimerIsOverImpl;
import clashclass.battle.timer.TimerGameImpl;
import clashclass.battle.troopdeath.DefenseBuildingsBattleBehaviorManager;
import clashclass.battle.troopdeath.EndBattleAllTroopsDead;
import clashclass.battle.troopdeath.EndBattleAllTroopsDeadGameImpl;
import clashclass.battle.troopdeath.TroopDeathObservable;
import clashclass.commons.BuildingFlagsComponent;
import clashclass.commons.BuildingTypeComponent;
import clashclass.commons.ConversionUtility;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.BuildingFlag;
import clashclass.elements.buildings.VillageElementData;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import clashclass.elements.troops.TroopType;
import clashclass.elements.troops.TroopFactory;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.BattleVillageDecoderImpl;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageDecoder;
import clashclass.saveload.VillageSaveLoadManager;
import clashclass.saveload.VillageEncoderImpl;
import clashclass.saveload.SimpleFileWriterImpl;
import clashclass.village.Village;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Set;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a {@link BattleManagerModel} implementation.
 */
public class BattleManagerModelImpl implements BattleManagerModel {
    private static final String SUPPRESS_WARNING_MESSAGE = "Intentional access";
    private static final String TROOPS_PROP = "troops";
    private static final double BATTLE_DURATION_SECONDS = 120.0;
    private final Village playerVillage;
    private final Village battleVillage;
    private final Map<TroopType, Function<Vector2D, GameObject>> troopCreatorsMap;
    private final Set<GameObject> activeTroops;
    private final AiNodesBuilder aiNodesBuilder;
    private GameStateManager gameStateManager;
    private TroopType currentSelectedTroop;
    private DefenseBuildingsBattleBehaviorManager defenseBuildingsBattleBehaviorManager;
    private BattleTroopsBehaviorManager battleTroopsBehaviorManager;
    private VillageDestructionManager villageDestructionManager;
    private EndBattleAllVillageDestroyed endBattleAllVillageDestroyedObserver;
    private EndBattleTimerIsOver endBattleTimerIsOverObserver;
    private EndBattleAllTroopsDead endBattleAllTroopsDeadObserver;
    private BattleReportController battleReportController;
    private final clashclass.battle.timer.Timer battleTimer;
    private boolean battleStarted;

    /**
     * Constructs the model.
     *
     * @param playerVillageCsvPath the player village csv file path
     * @param battleVillageCsvPath the battle village csv file path
     */
    public BattleManagerModelImpl(final Path playerVillageCsvPath, final Path battleVillageCsvPath) {
        this.playerVillage = this.loadVillageFromSaveDataOrElseFromResources(
                playerVillageCsvPath,
                new PlayerVillageDecoderImpl());
        this.battleVillage = this.loadVillageFromResources(
                battleVillageCsvPath,
                new BattleVillageDecoderImpl());

        this.activeTroops = new HashSet<>();
        this.aiNodesBuilder = new AiNodesBuilderImpl();

        final TroopFactory troopFactory = new BattleTroopFactoryImpl();
        this.troopCreatorsMap = new EnumMap<>(TroopType.class);
        this.troopCreatorsMap.put(TroopType.BARBARIAN, troopFactory::createBarbarian);
        this.troopCreatorsMap.put(TroopType.ARCHER, troopFactory::createArcher);

        this.battleTimer = new TimerGameImpl();

        this.handleBattleVillageDefenseBuildings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.battleReportController.setGameStateManager(gameStateManager);
    }

    private Village loadVillageFromResources(
            final Path csvPath,
            final VillageDecoder decoder) {
        decoder.setComponentFactory(new ComponentFactoryImpl());
        return decoder.decode(this.readCsvFileFromResources(csvPath));
    }

    private Village loadVillageFromSaveDataOrElseFromResources(
            final Path csvPath,
            final VillageDecoder decoder) {
        decoder.setComponentFactory(new ComponentFactoryImpl());
        if (Files.exists(csvPath)) {
            return decoder.decode(this.readCsvFile(csvPath));
        }
        return decoder.decode(this.readCsvFileFromResources(csvPath));
    }

    private String readCsvFile(final Path csvPath) {
        try {
            return Files.readString(csvPath);
        } catch (final IOException e) {
            return "";
        }
    }

    private String readCsvFileFromResources(final Path csvPath) {
        final var fileStream = Objects.requireNonNull(ClassLoader
                .getSystemResourceAsStream(csvPath.toString().replace("\\", "/")));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return "";
        }
    }

    private void handleBattleVillageDefenseBuildings() {
        this.battleVillage.getGameObjects().stream()
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).isPresent())
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BuildingFlag.DEFENSE))
                .forEach(defenseBuilding -> {
                    final var behaviourTree = defenseBuilding.getComponentOfType(BehaviourTree.class).get();
                    final var blackboard = behaviourTree.getBlackboard();

                    blackboard.setProperty("actor", new BlackboardPropertyImpl<>(defenseBuilding, GameObject.class));
                    blackboard.setProperty(TROOPS_PROP, new BlackboardPropertyImpl<>(new GameObjectListWrapper(
                            this.activeTroops.stream().toList()), GameObjectListWrapper.class));
                });
    }

    private void handleBattleVillageBuildings() {
        this.battleVillage.getGameObjects().stream()
                .filter(x -> x.getComponentOfType(DestructionObservable.class).isPresent())
                .map(x -> x.getComponentOfType(DestructionObservable.class).get())
                .forEach(destructionObservable -> {
                    destructionObservable.addObserver(this.villageDestructionManager);
                    destructionObservable.addObserver(this.battleTroopsBehaviorManager);
                    destructionObservable.addObserver(this.endBattleAllVillageDestroyedObserver);
                    destructionObservable.addObserver(this.endBattleTimerIsOverObserver);
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSelectedTroop(final TroopType troopType) {
        this.currentSelectedTroop = troopType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public Village getPlayerVillage() {
        return this.playerVillage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public Village getBattleVillage() {
        return this.battleVillage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TroopType getCurrentSelectedTroop() {
        return this.currentSelectedTroop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTroop(final Vector2D position) {
        final var gridCoordinates = ConversionUtility.convertWorldToGridPosition(position);
        if (this.battleVillage.isCellOutsideOfGrid(gridCoordinates)
         || this.battleVillage.isCellBusy(gridCoordinates)) {
            return;
        }

        final var player = this.playerVillage.getPlayer();
        if (player.hasArmyCampTroop(this.currentSelectedTroop)) {
            player.removeArmyCampTroop(this.currentSelectedTroop, 1);
            final var troopGameObject = this.troopCreatorsMap
                    .get(this.currentSelectedTroop)
                    .apply(position);

            this.activeTroops.add(troopGameObject);

            final var deathObservable = troopGameObject.getComponentOfType(TroopDeathObservable.class).get();
            deathObservable.addObserver(this.defenseBuildingsBattleBehaviorManager);
            deathObservable.addObserver(this.endBattleAllTroopsDeadObserver);

            final var behaviourTree = troopGameObject.getComponentOfType(BehaviourTree.class).get();
            final var blackboard = behaviourTree.getBlackboard();

            final var pathNodeGrid = aiNodesBuilder.buildPathNodeList(this.battleVillage);

            blackboard.setProperty("actor", new BlackboardPropertyImpl<>(troopGameObject, GameObject.class));
            blackboard.setProperty("potentialTargets", new BlackboardPropertyImpl<>(new GameObjectListWrapper(
                    this.battleVillage.getGameObjects().stream().toList()), GameObjectListWrapper.class));
            blackboard.setProperty("pathNodeGrid", new BlackboardPropertyImpl<>(pathNodeGrid, PathNodeGrid.class));

            this.gameStateManager.getGameEngine().addGameObject(troopGameObject);
            behaviourTree.start();

            this.battleReportController.setTroopCount(this.battleReportController.getTroopCount() + 1);
        }

        this.battleVillage.getGameObjects().stream()
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).isPresent())
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BuildingFlag.DEFENSE))
                .forEach(defenseBuilding -> {
                    final var behaviourTree = defenseBuilding.getComponentOfType(BehaviourTree.class).get();
                    final var blackboard = behaviourTree.getBlackboard();

                    blackboard.getProperty(TROOPS_PROP, GameObjectListWrapper.class)
                            .setValue(new GameObjectListWrapper(this.activeTroops.stream().toList()));
                });

        if (!this.battleStarted) {
            this.battleStarted = true;
            this.battleTimer.start();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public Set<GameObject> getActiveTroops() {
        return this.activeTroops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final BattleManagerController controller) {
        this.battleTroopsBehaviorManager = new BattleTroopsBehaviorManagerImpl(
                controller);
        this.defenseBuildingsBattleBehaviorManager = new DefenseBuildingsBattleBehaviorManager(
                controller);
        this.villageDestructionManager = new VillageDestructionManagerImpl(
                this.battleReportController);
        this.endBattleAllVillageDestroyedObserver = new EndBattleAllVillageDestroyedImpl(
                controller, this.battleReportController);
        this.endBattleTimerIsOverObserver = new EndBattleTimerIsOverImpl(
                controller);
        this.endBattleAllTroopsDeadObserver = new EndBattleAllTroopsDeadGameImpl(
                controller);
        this.handleBattleVillageBuildings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateVillageState(final GameObject destroyedBuilding) {
        this.battleVillage.removeBuilding(destroyedBuilding);
        final var pathNodeGrid = aiNodesBuilder.buildPathNodeList(destroyedBuilding);
        final var potentialTargets = new GameObjectListWrapper(
                this.battleVillage.getGameObjects().stream().toList());

        this.activeTroops.forEach(troopGameObject -> {
            final var behaviourTree = troopGameObject.getComponentOfType(BehaviourTree.class).get();
            final var blackboard = behaviourTree.getBlackboard();

            blackboard.getProperty("potentialTargets", GameObjectListWrapper.class)
                    .setValue(potentialTargets);

            final var currentTarget = blackboard.getProperty("target", GameObject.class)
                    .getValue();
            if (currentTarget != null && currentTarget.equals(destroyedBuilding)) {
                blackboard.getProperty("pathNodeGrid", PathNodeGrid.class)
                        .setValue(pathNodeGrid);
                behaviourTree.restart();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTroopsState(final GameObject destroyedTroop) {
        this.activeTroops.remove(destroyedTroop);
        final var troops = new GameObjectListWrapper(this.activeTroops.stream().toList());

        this.battleVillage.getGameObjects().stream()
                .filter(gameObject -> gameObject
                        .getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BuildingFlag.DEFENSE))
                .forEach(defenseBuilding -> {
                    final var behaviourTree = defenseBuilding
                            .getComponentOfType(BehaviourTree.class).get();
                    final var blackboard = behaviourTree.getBlackboard();

                    blackboard.getProperty(TROOPS_PROP, GameObjectListWrapper.class)
                            .setValue(troops);

                    final var currentTarget = blackboard.getProperty("target", GameObject.class).getValue();
                    if (currentTarget != null && currentTarget.equals(destroyedTroop)) {
                        behaviourTree.restart();
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.battleVillage.getGroundObjects().forEach(GameObject::destroy);
        this.battleVillage.getGameObjects().forEach(GameObject::destroy);
        this.activeTroops.forEach(GameObject::destroy);
        this.battleReportController.clearScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildBattleReport(final BattleReportView view) {
        final var nonWallBuildingsCount = (int) this.battleVillage.getBuildings().stream()
                .filter(x -> !x.getComponentOfType(BuildingTypeComponent.class).get()
                        .getBuildingType().equals(VillageElementData.WALL))
                .count();

        this.battleReportController = new BattleReportControllerImpl(
                new BattleReportModelImpl(nonWallBuildingsCount),
                view
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBattleStarted() {
        return this.battleStarted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBattleTimeFinished() {
        return this.battleTimer.getElapsedTime() >= BATTLE_DURATION_SECONDS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areAllTroopsDead() {
        return this.activeTroops.isEmpty()
            && this.playerVillage.getPlayer().getArmyCampTroopTypes().stream()
                        .map(type -> this.playerVillage.getPlayer()
                                .getArmyCampTroopCount(type))
                        .allMatch(count -> count == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showBattleReport() {
        this.activeTroops.forEach(troop ->
                troop.getComponentOfType(BehaviourTree.class).get().stop());
        this.battleVillage.getBuildings().stream()
                .filter(x -> x.getComponentOfType(BuildingFlagsComponent.class).get()
                        .getFlags().contains(BuildingFlag.DEFENSE))
                .forEach(defenseBuilding -> defenseBuilding
                        .getComponentOfType(BehaviourTree.class).get().stop());

        final var saveLoadManager = new VillageSaveLoadManager(
                new VillageEncoderImpl(),
                new PlayerVillageDecoderImpl(),
                new BattleVillageDecoderImpl(),
                new SimpleFileWriterImpl(),
                Paths.get("Villages-Data")
        );
        try {
            saveLoadManager.saveVillage(this.playerVillage, "player-village");
        } catch (final IOException e) {
            this.battleReportController.show();
            return;
        }
        this.battleReportController.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getBattleRemainingTime() {
        return (long) Math.max(0, BATTLE_DURATION_SECONDS - this.battleTimer.getElapsedTime());
    }
}
