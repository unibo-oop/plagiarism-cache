package it.tbt.model.fight.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.entities.characters.Character;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.CharacterFactory;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.entities.characters.Status;
import it.tbt.model.entities.characters.skills.Skill;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.Antidote;
import it.tbt.model.entities.items.Item;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;

/**
 * This class represents an implementation of the FightModel interface in a
 * JavaFX application.
 * It handles the logic and functionality of a fight between allies and enemies.
 */
public final class FightModelImpl implements FightModel {
    private static final double BASECRITCHANCE = 0.05;
    private static final double ENEMYPOISONCHANCE = 0.03;
    private static final double POISONDAMAGE = 0.05;
    private static final double BOOSTEDCRITCHANCE = 0.25;
    private static final int ENEMYLIMIT = 5;
    private static final Random RANDOM = new Random();

    private Map<Item, Double> drops;
    private StateObserver stateObserver;
    private List<Ally> allies;
    private List<Enemy> enemies;
    private List<Character> turnOrder;
    private Ally currentAlly;
    private Character currentCharacter;
    private IParty party;
    private int selectedTargetIndex;
    private boolean usingSkill;
    private boolean usingPotion;
    private boolean usingAntidote;

    /**
     * Constructs a FightModelImpl object with the provided average enemy stat and
     * item drops.
     *
     * @param averageEnemyStat the average stat of the enemies
     * @param drops            a map of items and their drop rates
     */
    public FightModelImpl(final int averageEnemyStat, final Map<Item, Double> drops) {
        if (averageEnemyStat < 1 || drops == null) {
            throw new IllegalArgumentException(
                    "è stato passato un argomento non lecito alla creazione di FightModelImpl");
        }
        this.enemies = new ArrayList<>();
        for (int c = 1; c < ENEMYLIMIT; c++) {
            this.enemies.add(CharacterFactory.createRandomEnemy("Enemy " + c, averageEnemyStat));
        }
        this.selectedTargetIndex = 0;
        this.usingSkill = false;
        this.usingPotion = false;
        this.usingAntidote = false;
        this.drops = Map.copyOf(drops);
    }

    /**
     * Constructs a FightModelImpl object with the provided party, average enemy
     * stat, and item drops.
     *
     * @param party            the party participating in the fight
     * @param averageEnemyStat the average stat of the enemies
     * @param drops            a map of items and their drop rates
     */
    public FightModelImpl(final IParty party, final int averageEnemyStat, final Map<Item, Double> drops) {
        this(averageEnemyStat, drops);
        if (party == null || averageEnemyStat < 1 || drops == null) {
            throw new IllegalArgumentException(
                    "è stato passato un argomento non lecito alla creazione di FightModelImpl");
        }
        initializeParty(party);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = {
            "EI2" }, justification = "The Component needs to access the exact instance of the Party the game is using.")
    @Override
    public void initializeParty(final IParty party) {
        if (party == null) {
            throw new IllegalArgumentException(
                    "è stato passato un argomento non lecito a initializeParty");
        }
        this.party = party;
        if (party.getMembers() == null) {
            throw new IllegalStateException("La lista dei membri del party è null");
        }
        final List<Ally> tmpAllies = new ArrayList<>(party.getMembers());
        this.allies = new ArrayList<>();
        final int limit = Math.min(4, tmpAllies.size());
        for (int i = 0; i < limit; i++) {
            if (tmpAllies.get(i) != null) {
                // per curare gli alleati quando inizia un fight
                // party.getMembers().get(i).setHealth(party.getMembers().get(i).getMaxHealth());
                this.allies.add(party.getMembers().get(i));
            }
        }
        if (limit < 4) {
            for (int i = limit; i < 4; i++) {
                this.allies.add(CharacterFactory.createAlly("", 0, 0, 0));
            }
        }
        this.turnOrder = new ArrayList<>();
        this.turnOrder.addAll(allies);
        this.turnOrder.addAll(enemies);
        // ordina il turno in base alla speed, in modo decrescente
        Collections.sort(this.turnOrder, new Comparator<Character>() {

            @Override
            public int compare(final Character arg0, final Character arg1) {
                return arg1.getSpeed() - arg0.getSpeed();
            }

        });

        this.currentCharacter = this.turnOrder.get(0);
        if (this.currentCharacter instanceof Ally) {
            this.currentAlly = (Ally) currentCharacter;
        } else {
            enemyAttack();
        }
        // System.out.println(party.getInventory().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Ally> getAllies() {
        return List.copyOf(allies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ally getCurrentAlly() {
        final Ally returnAlly = new Ally(this.currentAlly.getName(), this.currentAlly.getMaxHealth(),
                this.currentAlly.getAttack(),
                this.currentAlly.getSpeed(), this.currentAlly.getSkills());
        for (final Status s : this.currentAlly.getStatuses()) {
            returnAlly.addStatus(s);
        }
        if (this.currentAlly.getArmor().isPresent()) {
            returnAlly.equipeArmor(this.currentAlly.getArmor().get());
        }
        if (this.currentAlly.getWeapon().isPresent()) {
            returnAlly.equipeWeapon(this.currentAlly.getWeapon().get());
        }

        return returnAlly;
    }

    /**
     * Returns the currently selected ally.
     *
     * @return the selected ally
     */
    public Ally getSelectedAlly() {
        return this.allies.get(this.selectedTargetIndex);
    }

    /**
     * Returns the currently selected enemy.
     *
     * @return the selected enemy
     */
    public Enemy getSelectedEnemy() {
        return this.enemies.get(this.selectedTargetIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedTargetIndex() {
        return this.selectedTargetIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingSkill() {
        return this.usingSkill;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingPotion() {
        return this.usingPotion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingAntidote() {
        return this.usingAntidote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectPreviousTarget() {
        final int previousTarget = Math.max(0, selectedTargetIndex - 1);
        if (isUsingSkill() || (!isUsingAntidote() && !isUsingPotion() && !isUsingSkill())
                && this.enemies.get(previousTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = previousTarget;
        } else if ((isUsingAntidote() || isUsingPotion()) && this.allies.get(previousTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = previousTarget;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectNextTarget() {
        final int nextTarget = Math.min(enemies.size() - 1, selectedTargetIndex + 1);
        if (isUsingSkill() || (!isUsingAntidote() && !isUsingPotion() && !isUsingSkill())
                && this.enemies.get(nextTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = nextTarget;
        } else if ((isUsingAntidote() || isUsingPotion()) && this.allies.get(nextTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = nextTarget;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectAction(final boolean changeUsingSkillState, final boolean changeUsingPotionState,
            final boolean changeUsingAntidoteState) {
        selectedTargetIndex = 0;
        this.usingSkill = changeUsingSkillState;
        this.usingPotion = changeUsingPotionState;
        this.usingAntidote = changeUsingAntidoteState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performSelectedAction() {
        if (currentAlly.getSkills() == null) {
            throw new IllegalStateException("L'alleato corrente non ha la lista di skills istanziata");
        }

        final Character character = (Character) this.currentAlly;
        final Character selectedAlly = (Character) getSelectedAlly();
        final Character target = (Character) getSelectedEnemy();

        if (usingSkill && currentAlly.getSkills().size() != 0) {
            if (currentAlly.getSkills().get(0) == null) {
                throw new IllegalStateException("La prima skill dell'alleato corrente non è istanziata");
            }
            if (target.getHealth() != 0) {
                final Skill skill = currentAlly.getSkills().get(0); // prende la skill equipaggiata (quella in posizione
                                                                    // 0)
                if (skill.getRemainingCooldown() == 0) {
                    double damage = (character.getAttack() + character.getWeaponAttack()) * skill.getAttackMultiplier();
                    final Double critProb = RANDOM.nextDouble();
                    if (critProb <= FightModelImpl.BASECRITCHANCE
                            || skill.isIncProbCritical() && critProb <= FightModelImpl.BOOSTEDCRITCHANCE) {
                        damage *= 2.0;
                        // System.out.println("Critical Hit!");
                    }
                    target.takeDamage((int) damage);
                    if (skill.getPossibleStatus() != null && skill.getPossibleStatus().get() == Status.POISONED) {
                        target.addStatus(Status.POISONED);
                    }
                    skill.resetCooldown();
                    usingSkill = false;
                } else {
                    return;
                }
            }
        } else if (usingSkill) { // se il player sta provando ad usare una skill mentre è in cooldown
            return;
        } else if (usingPotion) { // codice che gestisce l'uso di pozioni

            if (selectedAlly.getMaxHealth() == selectedAlly.getHealth()) { // se il personaggio non può essere curato
                                                                           // esce
                return;
            }

            if (party.getInventory() == null) {
                throw new IllegalStateException("L'inventario del party non è istanziato");
            }
            final List<Potion> potions = new ArrayList<>();
            for (final Map.Entry<Item, Integer> item : party.getInventory().entrySet()) { // si tira giù tutte le
                                                                                          // pozioni
                if (item == null) {
                    throw new IllegalStateException("Un'entry della mappa dell'inventario è null");
                }
                final Item key = item.getKey();
                if (key == null) {
                    throw new IllegalStateException("C'è un oggetto non è istanziato");
                }
                if (key instanceof Potion && item.getValue() > 0) {
                    potions.add((Potion) key);
                }
            }

            if (potions.isEmpty()) {
                return; // se non ha pozioni non fa nulla
            }

            final int missingHealth = selectedAlly.getMaxHealth() - selectedAlly.getHealth();
            Potion currenPotion = null;
            for (final Potion p : potions) {
                if (currenPotion == null) {
                    currenPotion = p;
                } else if (currenPotion.getHealPower() > p.getHealPower() && p.getHealPower() > missingHealth
                        && currenPotion.getHealPower() > missingHealth) {
                    // usa la pozione che "sprecherebbe" meno hp
                    currenPotion = p;
                } else if (p.getHealPower() < missingHealth && currenPotion.getHealPower() > missingHealth) {
                    // tende ad usare pozioni che non eccedono il massimo degli hp
                    currenPotion = p;
                } else if (p.getHealPower() < missingHealth && currenPotion.getHealPower() < missingHealth
                        && currenPotion.getHealPower() < p.getHealPower()) {
                    // se la pozione checkata cura di più di quella corrente E non supera gli hp
                    // massimi tende ad usarla
                    currenPotion = p;
                }
            }

            if (currenPotion == null) {
                return;
            }

            int healAmount = selectedAlly.getHealth() + currenPotion.getHealPower();
            final int scartoHeal = healAmount - selectedAlly.getMaxHealth();
            if (scartoHeal > 0) {
                healAmount -= scartoHeal;
            }
            selectedAlly.setHealth(healAmount); // Cura l'alleato, non superando gli hp massimi

            party.removeItemFromInventory(currenPotion);

            // System.out.println(potions.size());

            usingPotion = false;
        } else if (usingAntidote) { // codice che gestisce l'uso di antidoti
            Antidote antidote = null;
            for (final Map.Entry<Item, Integer> item : party.getInventory().entrySet()) {
                final Item key = item.getKey();
                if (key instanceof Antidote && item.getValue() > 0) {
                    antidote = (Antidote) key;
                }
            }

            if (antidote == null || !selectedAlly.getStatuses().contains(Status.POISONED)) {
                // System.out.println("O non è avvelenato o non hai antidoti");
                return; // se non ha antitodi non fa nulla
            }

            selectedAlly.removeStatus(Status.POISONED);
            party.removeItemFromInventory(antidote); // rimuove l'antidoto dall'inventario
            // System.out.println("Hai usato un antidoto");
            usingAntidote = false;
        } else if (target.getHealth() != 0) { // codice che gestisce l'attacco normale
            double damage = character.getAttack() + character.getWeaponAttack();
            if (RANDOM.nextDouble() <= FightModelImpl.BASECRITCHANCE) {
                damage *= 2.0;
                // System.out.println("Critical Hit!");
            }
            target.takeDamage((int) damage);
        } else if (target.getHealth() == 0) {
            // System.out.println("Stai attaccando un nemico con 0 hp");
            return;
        } else {
            // System.out.println("???");
            return;
        }

        if (!checkBattleEnd()) {
            advanceTurn();
        }
    }

    /**
     * Decreases the cooldowns of skills for all allies.
     */
    private void decreaseCooldowns() {
        for (final Ally ally : this.allies) {
            if (ally == null) {
                throw new IllegalStateException("C'è un alleato non istanziato");
            }
            if (ally.getSkills() == null) {
                throw new IllegalStateException("La lista di skills di un alleato non è istanziata");
            }
            if (ally.getSkills().size() >= 1) {
                if (ally.getSkills().get(0) == null) {
                    throw new IllegalStateException("La prima skill si un alleato non è istanziata");
                }
                ally.getSkills().get(0).decreaseCooldown();
            }
        }
    }

    /**
     * Applies poison damage to all allies and enemies that are poisoned.
     */
    private void applyPoisonDamage() {
        for (final Enemy enemy : enemies) {
            if (enemy == null) {
                throw new IllegalStateException("C'è un nemico non istanziato");
            }
            if (enemy.getStatuses() == null) {
                throw new IllegalStateException("La lista di status di un nemico non è istanziata");
            }
            if (enemy.getStatuses().contains(Status.POISONED)) {
                final double poisonDamage = enemy.getMaxHealth() * FightModelImpl.POISONDAMAGE + 1;
                enemy.takeDamage((int) poisonDamage);
            }
        }
        for (final Ally ally : allies) {
            if (ally == null) {
                throw new IllegalStateException("C'è un alleato non istanziato");
            }
            if (ally.getStatuses() == null) {
                throw new IllegalStateException("La lista di status di un alleato non è istanziata");
            }
            if (ally.getStatuses().contains(Status.POISONED)) {
                final double poisonDamage = ally.getMaxHealth() * FightModelImpl.POISONDAMAGE + 1;
                ally.takeDamage((int) poisonDamage);
            }
        }
    }

    /**
     * Performs an attack by the enemy.
     */
    public void enemyAttack() {
        if (checkBattleEnd()) {
            return;
        }
        final Enemy currentEnemy = (Enemy) this.currentCharacter;
        int currentTarget = RANDOM.nextInt(0, 4);
        while (this.allies.get(currentTarget).getHealth() == 0) {
            currentTarget = RANDOM.nextInt(0, 4);
        }
        double damage = currentEnemy.getAttack();
        if (RANDOM.nextDouble() <= FightModelImpl.BASECRITCHANCE) {
            damage *= 2.0;
            // System.out.println("Critical Hit!");
        }
        this.allies.get(currentTarget)
                .takeDamage(Math.max(((int) damage) - this.allies.get(currentTarget).getArmorDefence(), 0));
        if (RANDOM.nextDouble() <= FightModelImpl.ENEMYPOISONCHANCE) {
            this.allies.get(currentTarget).addStatus(Status.POISONED);
            // System.out.println(this.allies.get(currentTarget).getName() + " è stato
            // avvelenato!");
        }
        if (!checkBattleEnd()) {
            advanceTurn();
        }
    }

    /**
     * Advances the turn to the next character in the turn order.
     */
    public void advanceTurn() {
        if (checkBattleEnd()) {
            return;
        }
        final int currentIndex = turnOrder.indexOf(this.currentCharacter);
        int nextIndex = (currentIndex + 1) % this.turnOrder.size();
        while (this.turnOrder.get(nextIndex).getHealth() == 0) {
            nextIndex++;
            nextIndex = nextIndex % this.turnOrder.size();
        }
        this.currentCharacter = this.turnOrder.get(nextIndex);
        if ((nextIndex) % this.turnOrder.size() == 0) {
            decreaseCooldowns();
            applyPoisonDamage();
            // System.out.println("cooldown skill diminuito e danno da veleno applicato");
            if (checkBattleEnd()) {
                return;
            }
        }
        if (this.currentCharacter instanceof Ally) {
            this.currentAlly = (Ally) currentCharacter;
        } else {
            enemyAttack();
        }
    }

    /**
     * Checks if the battle has ended.
     *
     * @return true if the battle has ended, false otherwise
     */
    private boolean checkBattleEnd() {
        boolean allAlliesDefeated = true;
        for (final Ally ally : allies) {
            if (ally == null) {
                throw new IllegalStateException("C'è un alleato non istanziato");
            }
            if (ally.getHealth() > 0) {
                allAlliesDefeated = false;
                break;
            }
        }
        boolean allEnemiesDefeated = true;
        for (final Enemy enemy : enemies) {
            if (enemy == null) {
                throw new IllegalStateException("C'è un nemico non istanziato");
            }
            if (enemy.getHealth() > 0) {
                allEnemiesDefeated = false;
                break;
            }
        }
        if (allAlliesDefeated) {
            // System.out.println("Enemies win!");
            this.party.setMembers(this.allies);
            this.stateObserver.onEnding("Hai perso. Premi un tasto per uscire");
        } else if (allEnemiesDefeated) {
            this.party.setMembers(this.allies);
            // System.out.println("Allies win!");
            for (final Map.Entry<Item, Double> e : this.drops.entrySet()) {
                if (RANDOM.nextDouble() <= (Double) e.getValue()) {
                    party.addItemToInventory((Item) e.getKey());
                    // System.out.println("Hai droppato " + e.getKey().toString());
                }
            }
            for (final Enemy e : this.enemies) {
                this.party.addCash(e.getMaxHealth());
            }
            this.stateObserver.onExplore();
        }
        return allAlliesDefeated || allEnemiesDefeated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        if (stateObserver == null) {
            throw new IllegalArgumentException("è stato passato uno stateObserver null");
        }
        this.stateObserver = stateObserver;
    }

}
