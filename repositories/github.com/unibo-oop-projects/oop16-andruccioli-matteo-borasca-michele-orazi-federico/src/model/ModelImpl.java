package model;

import java.util.Objects;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Set;

import model.player.Player;
import model.player.PlayerInfo;
import model.region.Region;
import model.state.State;
import model.state.StateInfo;
import utils.CircularList;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;

/**
 * Implementation of {@linkplain Model} interface.
 */
public class ModelImpl implements Model {

    private final CircularList<Player> players;
    private final Set<State> states;
    private final Set<Region> regions;
    private final MapType map;
    private Player actualPlayer;


    /**
     * Creates a ModelImpl object.
     * @param players
     *              the players list.
     * @param states
     *              the states set.
     * @param regions
     *              the regions set.
     * @param map
     *              the map type.
     */
    public ModelImpl(final CircularList<Player> players, final Set<State> states, final Set<Region> regions, final MapType map) {
        this.players = players;
        this.states = states;
        this.regions = regions;
        this.actualPlayer = this.players.getHead();
        this.map = map;
    }

    @Override
    public void reorganize() {
        this.players.setHead(0);
        this.actualPlayer = this.players.getHead();
    }

    @Override
    public void shiftPlayer() {
        this.players.shift();
        this.actualPlayer = this.players.getHead();
        AttackManager.getInstance().turnShifted();
    }

    @Override
    public CircularList<? extends PlayerInfo> getPlayers() {
        return this.players;
    }

    @Override
    public Set<? extends StateInfo> getStates() {
        return this.states;
    }

    @Override
    public MapType getMap() {
        return this.map;
    }

    @Override
    public AttackManagerInterface getAttackManager() {
        return AttackManager.getInstance();
    }

    @Override
    public AIManagerInterface getAIManager() {
        return AIManager.getInstance();
    }

    @Override
    public DeployManagerInterface getDeployManager() {
        return DeployManager.getInstance();
    }

    @Override
    public BonusCardManagerInterface getBonusCardManager() {
        return BonusCardManager.getInstance();
    }

    @Override
    public PlayerInfo getActualPlayer() {
        return this.actualPlayer;
    }

    @Override
    public void autoSave() {
        CareMementoTaker.getInstance().addMemento(new ModelMemento(this.players, this.states, 
                this.regions, BonusCardManager.getInstance().getDeck(), this.map));
    }

    @Override
    public void saveOnFile(final String fileName) {
        Objects.requireNonNull(fileName);
//        if (fileName == null || fileName.equals("")) {
//            fileName = new SimpleDateFormat("yyyyMMdd-hhmmss'.rsk'").format(new Date());
//        }
//        this.autoSave();
        LoadingManager.getInstance().saveGame(CareMementoTaker.getInstance().getMemento(), fileName);
    }

    @Override
    public void resetGame() {
        GameLoader.getGameLoader().gameClosed();
        AimManager.getInstance().resetInitCalled();
        BonusCardManager.getInstance().resetInitCalled();
        AttackManager.getInstance().clear();
        DeployManager.getInstance().clear();
        AIManager.getInstance().reset();
    }

    @Override
    public boolean allAIPlayers() {
        return this.players.toStream().filter(player -> player.playerType().equals(ControlType.AI)).count() == this.players.size();
    }

}
