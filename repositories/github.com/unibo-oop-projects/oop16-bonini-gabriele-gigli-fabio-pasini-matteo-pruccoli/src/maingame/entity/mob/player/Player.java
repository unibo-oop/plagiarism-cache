package maingame.entity.mob.player;

import java.awt.Dimension;
import java.awt.Rectangle;

import maingame.entity.item.Item;
import maingame.entity.mob.MobImpl;
import maingame.entity.projectile.NormalProjectile;
import maingame.entity.projectile.Projectile;
import maingame.entity.projectile.RedSuperProjectile;
import maingame.entity.projectile.SuperProjectile;
import maingame.game.Game;
import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import maingame.graphics.SpriteImpl;
import maingame.graphics.SpriteSheetImpl;
import maingame.input.ModelInput;
import maingame.level.LevelEnum;
import maingame.level.tile.TileImpl;
import maingame.sound.SoundImpl;
import maingame.statistics.StatisticsImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Classe del Player.
 */
public class Player extends MobImpl {

    private ModelInput modelInput;
    private int fireRate;
    private int maxHealth;
    private boolean gotKey;
    private boolean injured; // HA 1 CUORE
    private boolean hasnotkey;

    private int[] ammo = new int[2];
    private boolean gatherAmmo;
    private boolean drinkPotion;

    private int exp;
    private int nextLevel;
    private int levelPlayer;
    private boolean levelUpAnimation;
    private double oneHeart;
    private int dannolevelup;
    private boolean shooting;
    private int stepCount = TileImpl.TILE_SIZE;
    private static final int NIGHT_LUMINOSITY = 150;

    private enum Animation {
        DOWN, UP, LEFT, RIGHT, SWIMMING_DOWN, SWIMMING_UP, SWIMMING_LEFT, SWIMMING_RIGHT, ATTACK_DOWN, ATTACK_UP, ATTACK_LEFT, ATTACK_RIGHT, ATTACK_MOVING_DOWN, ATTACK_MOVING_UP, ATTACK_MOVING_LEFT, ATTACK_MOVING_RIGHT;
    }

    /**
     * costruttore del player per il gioco.
     * 
     * @param position
     *            posizione
     * @param input
     *            input da tastiera associato a player
     */
    public Player(final Vector2<Integer> position, final ModelInput input) {
        super(position, true);

        final int cost5 = 5;
        final int cost300 = 300;
        this.fireRate = 0;
        this.gotKey = false;
        this.injured = false;
        this.hasnotkey = false;
        this.modelInput = input;
        this.gatherAmmo = false;
        this.drinkPotion = false;
        this.exp = 0;
        this.levelPlayer = 0;
        this.levelUpAnimation = false;
        this.dannolevelup = cost5;
        nextLevel = cost300;
        final int cost6 = 6;
        final int cost15 = 15;
        final int cost20 = 20;

        getAnimations().add(AnimatedSprite.createAnimation(4,
                new Dimension[] { new Dimension(1, 4), new Dimension(1, 4), new Dimension(1, 3), new Dimension(1, 3) },
                new int[] { cost5, cost15, cost6, cost6 }, SpriteSheetImpl.PLAYER));
        getAnimations().add(AnimatedSprite.createAnimation(4,
                new Dimension[] { new Dimension(1, 4), new Dimension(1, 4), new Dimension(1, 3), new Dimension(1, 3) },
                new int[] { cost5, cost15, cost6, cost6 }, SpriteSheetImpl.PLAYER2));
        getAnimations().add(AnimatedSprite.createAnimation(4,
                new Dimension[] { new Dimension(1, 4), new Dimension(1, 4), new Dimension(1, 3), new Dimension(1, 3) },
                new int[] { cost5, cost15, cost6, cost6 }, SpriteSheetImpl.PLAYER3));
        setAnimation(getAnimations().get(getSkin()).get(Animation.DOWN.ordinal()));
        setHitbox(new Rectangle(position.getX(), position.getY(), 8, 8));
        setSprite(getAnimation().getSprite());
        setRenderXOffset((int) getSprite().getDimension().getWidth() / 4);
        maxHealth = getHealth();
        oneHeart = maxHealth / 10.0;
        setDamage(cost20);
        setAmmo(cost15, cost5);
    }

    /**
     * costruttore del player per l' editor.
     * 
     * @param levelColor
     *            colore associato
     * @param name
     *            nome
     */
    public Player(final int levelColor, final String name) {
        super(levelColor, name);
    }

    private void lowHealth() {
        injured = true;
        if (!SoundImpl.RAIN.isStopped()) {
            SoundImpl.RAIN.stop();
        }
        if (!SoundImpl.NIGHT.isStopped()) {
            SoundImpl.NIGHT.stop();
        }
        if (!SoundImpl.BACKGROUND.isStopped()) {
            SoundImpl.BACKGROUND.stop();
        }

        if (SoundImpl.HEART.isStopped() && !Game.getGame().isGamewin()) {
            SoundImpl.HEART.play(true);
        }
    }

    private void changeSound() {
        if (!Game.getGame().isGameOver() && !Game.getGame().isGamewin()) {
            if (Game.getGame().getNamelevel() == LevelEnum.MAIN) {
                // Dopo che i cuori si sono ripristinati, riavvio la pioggia nel
                // caso piova.
                if (ScreenImpl.getScreen().isRain() && SoundImpl.RAIN.isStopped()) {
                    SoundImpl.RAIN.play(true);
                }
                // Riavvio il giorno nel caso sia giorno, o vicerversa la notte.
                if (!ScreenImpl.getScreen().isThunder() && !ScreenImpl.getScreen().isRain()) {
                    if (SoundImpl.BACKGROUND.isStopped()
                            && Game.getGame().getLevel().getBrightness() <= NIGHT_LUMINOSITY) {
                        SoundImpl.BACKGROUND.play(true);
                    } else if (SoundImpl.NIGHT.isStopped()
                            && Game.getGame().getLevel().getBrightness() > NIGHT_LUMINOSITY
                            && !ScreenImpl.getScreen().isThunder()) {
                        SoundImpl.NIGHT.play(true);
                    }
                }

            }
            if (Game.getGame().getNamelevel() == LevelEnum.PIT && SoundImpl.CAVE.isStopped()) {

                SoundImpl.CAVE.play(true);
            }
            if (Game.getGame().getNamelevel() == LevelEnum.HOUSE && SoundImpl.HOUSE.isStopped()) {

                SoundImpl.HOUSE.play(true);
            }
        }
        SoundImpl.HEART.stop();
        SpriteImpl.HEART.setFrame(0);
        injured = false;
    }

    /**
     * controlla cosa deve fare il player in base all'input dato e alla tile in
     * cui si trova.
     */
    @Override
    public void update() {
        if (!Game.getGame().isEditor()) {
            final int cost30 = 30;

            if (ScreenImpl.getScreen().isRain()) {
                SpriteImpl.RAIN.update();
            }

            if (injured) {
                SpriteImpl.HEART.update();
                SpriteImpl.BLOOD.update();
            }
            if (getSkin() == 0 || getSkin() == 2) {
                if (this.getHealth() <= oneHeart * 2) { // 2 CUORI
                    this.setSkin(2);
                } else {
                    this.setSkin(0);
                }
            }
            if (getHealth() <= oneHeart) { // 1 CUORE
                lowHealth();
            } else {
                changeSound();
            }
            if (getLevel().getTile(tileCoordinate(
                    new Vector2Impl<Integer>(getPosition().getX() + getRenderXOffset(),
                            getPosition().getY() + (int) getSprite().getDimension().getHeight() / 2),
                    new Vector2Impl<Integer>(0, 0))).isSwimmable()) {
                if (getAttacking()) {
                    setAttacking(false);
                }
                setSwimming(true);
                if (getDirection() == Direction.UP) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_UP.ordinal()));
                }
                if (getDirection() == Direction.DOWN) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_DOWN.ordinal()));
                }
                if (getDirection() == Direction.LEFT) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_LEFT.ordinal()));
                }
                if (getDirection() == Direction.RIGHT) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_RIGHT.ordinal()));
                }
            } else {
                setSwimming(false);
            }
            if (modelInput != null) {

                if (modelInput.isPressedSpace() && getTimerAttack() <= 0 && !getSwimming() && !shooting) {
                    setAttacking(true);
                    setTimer(cost30);
                    SoundImpl.PUNCH.play(false);
                    if (getDirection() == Direction.UP && !getWalking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_UP.ordinal()));
                    }
                    if (getDirection() == Direction.DOWN && !getWalking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_DOWN.ordinal()));
                    }
                    if (getDirection() == Direction.LEFT && !getWalking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_LEFT.ordinal()));
                    }
                    if (getDirection() == Direction.RIGHT && !getWalking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_RIGHT.ordinal()));
                    }
                    attack();
                }
                if (getWalking() || getSwimming() || getAttacking()) {
                    getAnimation().update();
                    if (getAttacking() && getAnimation().getCount() == 1) {
                        getAnimation().resetCount();
                        setAttacking(false);
                        if (getDirection() == Direction.UP) {
                            setAnimation(getAnimations().get(getSkin()).get(Animation.UP.ordinal()));
                        }
                        if (getDirection() == Direction.DOWN) {
                            setAnimation(getAnimations().get(getSkin()).get(Animation.DOWN.ordinal()));
                        }
                        if (getDirection() == Direction.LEFT) {
                            setAnimation(getAnimations().get(getSkin()).get(Animation.LEFT.ordinal()));
                        }
                        if (getDirection() == Direction.RIGHT) {
                            setAnimation(getAnimations().get(getSkin()).get(Animation.RIGHT.ordinal()));
                        }
                    }
                } else {
                    getAnimation().setFrame(0);
                }

                if (fireRate > 0) {
                    fireRate--;
                }
                if (modelInput.isPressedUp()) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.UP.ordinal()));
                    if (getSwimming()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_UP.ordinal()));
                    }
                    if (getAttacking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_MOVING_UP.ordinal()));
                    }
                    getMovement().setY(getMovement().getY() - 1);
                } else if (modelInput.isPressedDown()) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.DOWN.ordinal()));
                    if (getSwimming()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_DOWN.ordinal()));
                    }
                    if (getAttacking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_MOVING_DOWN.ordinal()));
                    }
                    getMovement().setY(getMovement().getY() + 1);
                }
                if (modelInput.isPressedLeft()) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.LEFT.ordinal()));
                    if (getSwimming()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_LEFT.ordinal()));
                    }
                    if (getAttacking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_MOVING_LEFT.ordinal()));
                    }
                    getMovement().setX(getMovement().getX() - 1);

                } else if (modelInput.isPressedRight()) {
                    setAnimation(getAnimations().get(getSkin()).get(Animation.RIGHT.ordinal()));
                    if (getSwimming()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.SWIMMING_RIGHT.ordinal()));
                    }
                    if (getAttacking()) {
                        setAnimation(getAnimations().get(getSkin()).get(Animation.ATTACK_MOVING_RIGHT.ordinal()));
                    }
                    getMovement().setX(getMovement().getX() + 1);
                }
                super.update();
                if (getMovement().getX() != 0 || getMovement().getY() != 0) {
                    if (stepCount == 0) {
                        StatisticsImpl.getStatistics().incrementSteps(1);
                        stepCount = TileImpl.TILE_SIZE;
                    } else {
                        stepCount--;
                    }
                    setLastMovement(getMovement());
                    move(getMovement(), false);
                    getMovement().set(0, 0);
                    setWalking(true);
                } else {
                    setWalking(false);

                }
                clear();
                updateShooting();
            }
        }
    }

    private void clear() {
        for (int i = 0; i < getLevel().getProjectiles().size(); i++) {
            final Projectile p = getLevel().getProjectiles().get(i);
            if (p.isRemoved()) {
                getLevel().getProjectiles().remove(i);
            }
        }
    }

    /**
     * funzione per sparare premendo i tasti del mouse
     */
    private void updateShooting() {
        if ((modelInput.getButton() == 1 || modelInput.getButton() == 3)) {
            shooting = true;
            if (fireRate <= 0 && !getSwimming()) {
                final double dx = modelInput.getMouseCoordinate().getX() - getLevel().getPlayerScreenPosition().getX();
                final double dy = modelInput.getMouseCoordinate().getY() - getLevel().getPlayerScreenPosition().getY();
                final double dir = Math.atan2(dy, dx);
                shooting = true;
                // SE PREMO MOUSE SX NORMALPROJECTILE
                if (modelInput.getButton() == 1 && ammo[0] > 0) {
                    SoundImpl.SHOT.play(false);

                    shoot(new NormalProjectile(
                            new Vector2Impl<Integer>(getPosition().getX() + getRenderXOffset(), getPosition().getY()),
                            dir));
                    ammo[0]--;
                    fireRate = NormalProjectile.FIRE_RATE;
                    StatisticsImpl.getStatistics().incrementProjectile(1);
                }
                // SE PREMO MOUSE DX SUPERPROJECTILE
                if (modelInput.getButton() == 3 && ammo[1] > 0) {
                    SoundImpl.KAMEHAMEHA.play(false);
                    ammo[1]--;
                    if (getSkin() == 1) {
                        shoot(new RedSuperProjectile(new Vector2Impl<Integer>(getPosition().getX() + getRenderXOffset(),
                                getPosition().getY()), dir));
                        fireRate = RedSuperProjectile.FIRE_RATE;
                    } else {
                        shoot(new SuperProjectile(new Vector2Impl<Integer>(getPosition().getX() + getRenderXOffset(),
                                getPosition().getY()), dir));
                        fireRate = SuperProjectile.FIRE_RATE;
                    }
                    StatisticsImpl.getStatistics().incrementProjectile(1);
                }
            }

        } else {
            shooting = false;
        }
    }

    /**
     * controlla se player collide con un item.
     * 
     * @param item
     *            oggetto con cui pu� collidere
     * @return se collide
     */
    public boolean checkItemCollision(final Item item) {

        return itemCollision(getLastMovement()) == item;
    }

    @Override
    public Sprite getAnimationSprite() {
        return getAnimation().getSprite();
    }

    /**
     * 
     * @return vita massima del player
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * 
     * @return se sta nuotando
     */
    public boolean isSwimming() {
        return super.getSwimming();
    }

    /**
     * 
     * @return la tastiera per l'input
     */
    public ModelInput getInput() {
        return modelInput;
    }

    /**
     * chiamata per aumentare l'esperienza del player.
     * 
     * @param expnem
     *            esperienza da aumetare
     */
    public void setExp(final int expnem) {
        exp = exp + expnem;
        levelup();
    }

    /**
     * funzione che controlla se il player � superato di livello Se il livello
     * >1, aumenta il danno corpo a corpo
     */
    private void levelup() {
        if (exp >= nextLevel) {
            levelPlayer = levelPlayer + 1;
            nextLevel = nextLevel + ((levelPlayer) * nextLevel);
            SoundImpl.LEVELUP.play(false);
            setLevelUpAnimation(true);
            this.setDamage(this.getDamage() + dannolevelup);
        }
    }

    /**
     * @return se è salito di livello, per animazione.
     */
    public boolean isLevelUpAnimation() {
        return levelUpAnimation;
    }

    /**
     * Setta l' attivazione dell'animazione da fuori.
     * 
     * @param levelUpAnimation
     *            booleano di levelupAnimation.
     */
    public void setLevelUpAnimation(final boolean levelUpAnimation) {
        this.levelUpAnimation = levelUpAnimation;
    }

    /**
     * 
     * @return se ha la chiave.
     */
    public boolean isGotKey() {
        return gotKey;
    }

    /**
     * setta se ha raccolto la chiave.
     * 
     * @param gotKey
     *            se ha raccolta la chiave
     */
    public void setGotKey(final boolean gotKey) {
        this.gotKey = gotKey;
    }

    /**
     * 
     * @return se sta sanguinando
     */
    public boolean isInjured() {
        return injured;
    }

    /**
     * 
     * @return le munizione che ha
     */
    public int[] getAmmo() {

        return ammo.clone();
    }

    /**
     * aggiunge munuzioni.
     * 
     * @param norm
     *            munizione normali
     * @param sup
     *            munizioni speciali
     */
    public void setAmmo(final int norm, final int sup) {
        this.ammo[0] = norm;
        this.ammo[1] = sup;
    }

    /**
     * 
     * @param injured
     *            setta se sta sanguinando
     */
    public void setInjured(final boolean injured) {
        this.injured = injured;
    }

    /**
     * Ritorna l'esperienza del player.
     * 
     * @return Esperienza del player.
     */
    public int getExp() {
        return exp;
    }

    /**
     * Ritorna se ha raccolto ammo.
     * 
     * @return true se raccolte munizioni.
     */
    public boolean isGatherAmmo() {
        return gatherAmmo;
    }

    /**
     * Setta raccolte ammo.
     * 
     * @param gatherAmmo
     *            booleano raccolta munizioni.
     */
    public void setGatherAmmo(final boolean gatherAmmo) {
        this.gatherAmmo = gatherAmmo;
    }

    /**
     * Ritorna se ha bevuto potion.
     * 
     * @return true se bevuto potion.
     */
    public boolean isDrinkPotion() {
        return drinkPotion;
    }

    /**
     * Setta drink potion a true o false.
     * 
     * @param drink
     *            booleano bevuta pozione.
     */
    public void setDrinkPotion(final boolean drink) {
        this.drinkPotion = drink;

    }

    /**
     * Ritorna se il player tocca la porta ma senza la chiave.
     * 
     * @return booleano true o false
     */
    public boolean isHasnotKey() {
        return hasnotkey;
    }

    /**
     * Setta il booleano a true o false, per l' animazione.
     * 
     * @param hasnotKey
     *            player ha toccato la porta ma senza la chiave.
     */
    public void setHasnotKey(final boolean hasnotKey) {
        this.hasnotkey = hasnotKey;
    }
}
