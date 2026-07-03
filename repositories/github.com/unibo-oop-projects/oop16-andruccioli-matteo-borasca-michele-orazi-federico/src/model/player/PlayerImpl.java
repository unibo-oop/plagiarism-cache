package model.player;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.aim.Aim;
import model.bonus.Bonus;
import model.bonus.BonusCard;
import model.region.Region;
import model.state.State;
import utils.enumerations.Color;
import utils.enumerations.ControlType;

/**
 * 
 * This class implements the concept of player, contains parameters which
 * describe resources the player owns in the very moment.
 *
 */

public class PlayerImpl implements Player {

    private static final long serialVersionUID = 7820730216855667873L;
    private final String name;
    private final Color color;
    private final ControlType controller;
    private final Set<State> statesConquered;
    private final Set<Region> regionsConquered;
    private int totalTanksToDeploy;
    private int tanksToDeploy;
    private Map<Bonus, List<BonusCard>> bonusCards;
    private Aim aim;

    /**
     * this is a constructor for an object of class player.
     * 
     * @param name
     *            player's name in the game
     * @param color
     *            color associated to the player
     * @param controlledByAI
     *            specifies if the player is controlled by a human being or by
     *            the AI
     * @param totalTanksToDeploy
     *            n° of tanks owned by the player when the game starts
     */
    public PlayerImpl(final String name, final Color color, final ControlType controlledByAI,
            final int totalTanksToDeploy) {
        super();
        this.name = name;
        this.color = color;
        this.controller = controlledByAI;
        this.statesConquered = new HashSet<>();
        this.regionsConquered = new HashSet<>();
        this.totalTanksToDeploy = totalTanksToDeploy;
        this.tanksToDeploy = 0;
        this.aim = null;
        this.bonusCards = new HashMap<>();
        for (final Bonus b : Bonus.values()) {
            this.bonusCards.put(b, new LinkedList<>());
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public ControlType playerType() {
        return this.controller;
    }

    @Override
    public Set<State> getStates() {
        return new HashSet<>(this.statesConquered);
    }

    @Override
    public Set<Region> getRegions() {
        return new HashSet<>(this.regionsConquered);
    }

    @Override
    public int getTotalTanksToDeploy() {
        return this.totalTanksToDeploy;
    }

    @Override
    public void setTotalTanksToDeploy(final int nTanks) {
        if (nTanks <= 0) {
            throw new IllegalArgumentException("nTank must be positive");
        }
        this.totalTanksToDeploy = nTanks;
    }

    @Override
    public void decreaseTotalTanksToDeploy(final int nTanks) {
        if (nTanks <= 0) {
            throw new IllegalArgumentException("nTank must be positive");
        }
        this.totalTanksToDeploy -= nTanks;
    }

    @Override
    public int getTanksToDeploy() {
        return tanksToDeploy;
    }

    @Override
    public void resetTanksToDeploy() {
        this.tanksToDeploy = 0;
    }

    @Override
    public void addState(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("state must be non null object");
        }
        if (this.statesConquered.contains(state)) {
            throw new IllegalArgumentException("state shouldnm't be contained in player's statesConquered");
        }
        this.statesConquered.add(state);
        state.setPlayer(this);
    }

    @Override
    public void removeState(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("state must be non null object");
        }
        if (!this.statesConquered.contains(state)) {
            throw new IllegalArgumentException("state must be contained in player's statesConquered");
        }
        this.statesConquered.remove(state);
    }

    @Override
    public void addRegion(final Region region) {
        if (region == null) {
            throw new IllegalArgumentException("region must be non null object");
        }
        if (this.regionsConquered.contains(region)) {
            throw new IllegalArgumentException("region shouldn't be already contained in player's regionsConquered");
        }
        this.regionsConquered.add(region);
    }

    @Override
    public void removeRegion(final Region region) {
        if (region == null) {
            throw new IllegalArgumentException("region must be non null object");
        }
        if (!this.regionsConquered.contains(region)) {
            throw new IllegalArgumentException("region must be contained in player's regionsConquered");
        }
        this.regionsConquered.remove(region);
    }

    @Override
    public Aim getAim() {
        if (this.aim != null) {
            return this.aim;
        }
        throw new IllegalStateException("Aim field hasn't been set yet");
    }

    @Override
    public void setAim(final Aim aim) {
        if (this.aim != null) {
            throw new IllegalStateException("Aim already set");
        }
        if (aim == null) {
            throw new IllegalArgumentException("Aim field must be non null");
        }
        this.aim = aim;

    }

    @Override
    public Map<Bonus, List<BonusCard>> getBonusCards() {
        return new HashMap<>(this.bonusCards);
    }

    @Override
    public void addBonusCard(final BonusCard bonusCard) {
        if (bonusCard == null) {
            throw new IllegalArgumentException("bonusCard must be non null object");
        }
        this.bonusCards.get(bonusCard.getBonusType()).add(bonusCard);
    }

    @Override
    public List<BonusCard> getBonusCardsList() {
        final List<BonusCard> tmp = new LinkedList<>();
        Arrays.asList(Bonus.values()).forEach(b->tmp.addAll(this.bonusCards.get(b)));
        return tmp;
    }

    @Override
    public void removeCombo(final List<BonusCard> combo) {
        if (combo == null) {
            throw new IllegalArgumentException("combo must be non null");
        }
        if (combo.isEmpty()) {
            throw new IllegalArgumentException("combo must contain elements");
        }
        for (final BonusCard bc : combo) {
            if (!this.bonusCards.get(bc.getBonusType()).contains(bc)) {
                throw new IllegalArgumentException("Invalid combo");
            }
            this.bonusCards.get(bc.getBonusType()).remove(bc);
        } 
    }

    @Override
    public void addTanksToDeploy(final int tanksToDeploy) {
        if (tanksToDeploy <= 0) {
            throw new IllegalArgumentException("tanksToDeploy must be positive");
        }
        this.tanksToDeploy += tanksToDeploy;

    }

    @Override
    public void removeAllBonusCard() {
        if (!this.statesConquered.isEmpty()) {
            throw new IllegalStateException("player must have been defeated");
        }
        this.bonusCards = new HashMap<>();
        Arrays.asList(Bonus.values()).forEach(b->this.bonusCards.put(b, new LinkedList<>()));
    }

    @Override
    public void addBonusCardCollection(final Collection<BonusCard> bonusCardColl) {
        bonusCardColl.stream().forEach(bc -> this.addBonusCard(bc));

    }

    @Override
    public Player forwarder() {
        return this;
    }

    @Override
    public String toString() {
        return "Player: " + this.name + "  Color: " + this.color.toString();
    }

}
