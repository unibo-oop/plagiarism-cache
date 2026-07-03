package maingame.entity.mob;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import maingame.entity.EntityImpl;
import maingame.entity.mob.enemy.Enemy;
import maingame.entity.mob.player.Player;
import maingame.entity.projectile.Projectile;
import maingame.game.Game;
import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import maingame.graphics.SpriteImpl;
import maingame.level.tile.TileImpl;
import maingame.sound.SoundImpl;
import maingame.statistics.StatisticsImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione di mob.
 */
public abstract class MobImpl extends EntityImpl implements Mob {

    private boolean walking;
    private boolean swimming;
    private boolean attacking;
    private boolean damaged;
    private final String name;
    private final int levelColor;
    private boolean canSwim;
    private int renderXOffset;
    private int time;
    private int health = 100;
    private int damagemelee;
    private Vector2<Integer> movement;
    private int timer;
    private int damagedTimer;
    private AnimatedSprite animation;
    private Vector2<Integer> lastMovement;
    private int n;
    private Random random = new Random();
    private List<List<AnimatedSprite>> animations = new ArrayList<List<AnimatedSprite>>();
    private int skin;

    /**
     * enum per animation.
     */
    protected enum Animation {
        /**
         * animation di movimento di attacco.
         */
        DOWN, UP, LEFT, RIGHT, ATTACK_DOWN, ATTACK_UP, ATTACK_LEFT, ATTACK_RIGHT;
    }

    /**
     * . enum per la direzione
     */
    public enum Direction {
        /**
         * . direzione su
         */
        UP,
        /**
         * . direzione giu
         */
        DOWN,
        /**
         * . direzione sinistra
         */
        LEFT,
        /**
         * . direzione destra
         */
        RIGHT;
    }

    private Direction dir = Direction.DOWN;

    /**
     * . costruttore mob per il gioco
     * 
     * @param position
     *            vettore con la posizione
     * @param canSwim
     *            variabile se puo nuotare
     */
    public MobImpl(final Vector2<Integer> position, final boolean canSwim) {
        super(position);
        this.walking = false;
        this.canSwim = canSwim;
        this.swimming = false;
        this.attacking = false;
        this.damaged = false;
        this.time = 0;
        this.damagemelee = 10;
        this.movement = new Vector2Impl<>(0, 0);
        this.lastMovement = new Vector2Impl<Integer>(0, 0);
        this.random = new Random();
        levelColor = 0;
        name = null;
    }

    /**
     * . costruttore mob per l'editor
     * 
     * @param levelColor
     *            .
     * @param name
     *            nome
     */
    public MobImpl(final int levelColor, final String name) {
        this.name = name;
        this.levelColor = levelColor;
    }

    /**
     * . funzione per il calcolo del danno a un mob
     * 
     * @param pos
     *            posizione del mob che colpisce
     * @param mob
     *            mob colpito
     */
    private void damage(final Vector2<Integer> pos, final Mob mob) {
        mob.move(pos, true);
        movement.set(0, 0);
        mob.setHealth(mob.getHealth() - damagemelee);
        mob.setDamaged(true);
        mob.setDamagedTimer(10);
        if (mob instanceof Enemy || mob instanceof Player) {
            SoundImpl.DAMAGEZOMBI.play(false);
        }
    }

    @Override
    public void move(final Vector2<Integer> pos, final boolean knockback) {

        // se ti muovi in diagonale, chiami move 2 volte move(x,0), move(0,y)
        if (pos.getX() != 0 && pos.getY() != 0) {
            move(new Vector2Impl<Integer>(pos.getX(), 0), false);
            move(new Vector2Impl<Integer>(0, pos.getY()), false);
            return;
        }

        if (!knockback) {
            if (pos.getX() > 0) {
                dir = Direction.RIGHT;
            }
            if (pos.getX() < 0) {
                dir = Direction.LEFT;
            }
            if (pos.getY() > 0) {
                dir = Direction.DOWN;
            }
            if (pos.getY() < 0) {
                dir = Direction.UP;
            }
        }

        // se non c'è collisione con oggetti o mob, ti sposti
        if (!tileCollision(pos) && mobCollision(pos, false) == null && itemCollision(pos) == null) {
            getPosition().setX(getPosition().getX() + pos.getX());
            getPosition().setY(getPosition().getY() + pos.getY());
        }
    }

    /**
     * . update del mob
     */

    public void update() {
        getHitbox().setLocation(getPosition().getX(), getPosition().getY());
        time++;
        timer--;
        damagedTimer--;
        if (damagedTimer == 0) {
            damaged = false;
        }
        if (health <= 0) {
            setRemove();
            if (getLevel().getPlayer().getHealth() > 0) {
                StatisticsImpl.getStatistics().incrementKill(1);
            }
        }
        if (getLevel().getTile(tileCoordinate(getPosition(), new Vector2Impl<Integer>(0, 0))).getName()
                .equals("Sand")) {
            getLevel().addSandStep(tileCoordinate(
                    new Vector2Impl<Integer>(getPosition().getX() + renderXOffset,
                            getPosition().getY() + (int) getSprite().getDimension().getHeight() / 2),
                    new Vector2Impl<Integer>(0, 0)));
        }
    }

    @Override
    public void renderBodies() {
        final Sprite animationSprite = getAnimationSprite();
        setSprite(SpriteImpl.spriteFromSprite(
                new Dimension((int) animationSprite.getDimension().getWidth(),
                        (int) animationSprite.getDimension().getHeight() / 2),
                new Vector2Impl<Integer>(0, 1), animationSprite));
        final double light = getLevel().getLightIntensity(this);
        if (light != -1) {
            if (damaged) {
                ScreenImpl.getScreen().render(
                        new Vector2Impl<Integer>(getPosition().getX() - renderXOffset, getPosition().getY()),
                        getSprite(), light, true, false);
            } else {
                ScreenImpl.getScreen().render(
                        new Vector2Impl<Integer>(getPosition().getX() - renderXOffset, getPosition().getY()),
                        getSprite(), light, false, false);
            }
        } else {
            if (damaged) {
                ScreenImpl.getScreen().render(
                        new Vector2Impl<Integer>(getPosition().getX() - renderXOffset, getPosition().getY()),
                        getSprite(), 1.0, true, false);
            } else {
                ScreenImpl.getScreen().render(
                        new Vector2Impl<Integer>(getPosition().getX() - renderXOffset, getPosition().getY()),
                        getSprite(), 1.0, false, false);
            }
        }
    }

    @Override
    public void renderHeads() {
        final Sprite animationSprite = getAnimationSprite();
        setSprite(SpriteImpl.spriteFromSprite(
                new Dimension((int) animationSprite.getDimension().getWidth(),
                        (int) animationSprite.getDimension().getHeight() / 2),
                new Vector2Impl<Integer>(0, 0), animationSprite));
        final double light = getLevel().getLightIntensity(this);
        if (light != -1) {
            if (damaged) {
                ScreenImpl.getScreen()
                        .render(new Vector2Impl<Integer>(getPosition().getX() - renderXOffset,
                                getPosition().getY() - ((int) getSprite().getDimension().getHeight())), getSprite(),
                                light, true, false);
            } else {
                ScreenImpl.getScreen()
                        .render(new Vector2Impl<Integer>(getPosition().getX() - renderXOffset,
                                getPosition().getY() - ((int) getSprite().getDimension().getHeight())), getSprite(),
                                light, false, false);
            }
        } else {
            if (damaged) {
                ScreenImpl.getScreen()
                        .render(new Vector2Impl<Integer>(getPosition().getX() - renderXOffset,
                                getPosition().getY() - ((int) getSprite().getDimension().getHeight())), getSprite(),
                                1.0, true, false);
            } else {
                ScreenImpl.getScreen()
                        .render(new Vector2Impl<Integer>(getPosition().getX() - renderXOffset,
                                getPosition().getY() - ((int) getSprite().getDimension().getHeight())), getSprite(),
                                1.0, false, false);
            }
        }
    }

    @Override
    public Sprite getAnimationSprite() {
        return getAnimation().getSprite();
    }

    private boolean hit(final Mob m) {
        final int cost3 = 3;
        if (getLastMovement().getY() < 0 && m.getPosition().getY() < getPosition().getY()
                && getPosition().getX() < m.getPosition().getX() + 4
                && getPosition().getX() > m.getPosition().getX() - 4) {

            damage(new Vector2Impl<Integer>(0, -cost3), m);
            return true;
        }
        if (getLastMovement().getY() > 0 && m.getPosition().getY() > getPosition().getY()
                && getPosition().getX() < m.getPosition().getX() + 4
                && getPosition().getX() > m.getPosition().getX() - 4) {
            damage(new Vector2Impl<Integer>(0, cost3), m);
            return true;
        }
        if (getLastMovement().getX() < 0 && m.getPosition().getX() < getPosition().getX()
                && getPosition().getY() < m.getPosition().getY() + 4
                && getPosition().getY() > m.getPosition().getY() - 4) {
            damage(new Vector2Impl<Integer>(-cost3, 0), m);
            return true;
        }
        if (getLastMovement().getX() > 0 && m.getPosition().getX() > getPosition().getX()
                && getPosition().getY() < m.getPosition().getY() + 4
                && getPosition().getY() > m.getPosition().getY() - 4) {
            damage(new Vector2Impl<Integer>(3, 0), m);
            return true;
        }
        return false;
    }

    /**
     * funzione che controlla se un mob � nella giusta posizione per colpirne un
     * altro.
     * 
     * @return se colpito
     */
    protected boolean attack() {
        final int cost20 = 20;
        final List<Mob> mobs = getLevel().getMobs(this, cost20);
        if (this instanceof Player) {
            for (final Mob e : mobs) {
                if (hit(e)) {
                    return true;
                }
            }
        } else {
            if (hit(getLevel().getPlayer())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param position
     *            del mob
     * @param offset
     *            offset
     * @return le coordinate della tile
     */

    public Vector2<Integer> tileCoordinate(final Vector2<Integer> position, final Vector2<Integer> offset) {
        return new Vector2Impl<Integer>((position.getX() / TileImpl.TILE_SIZE) + offset.getX(),
                (position.getY() / TileImpl.TILE_SIZE) + offset.getY());
    }

    /**
     * 
     * @param projectile
     *            proiettile che spara
     */
    protected void shoot(final Projectile projectile) {
        final Projectile p = projectile;
        getLevel().add(p);
    }

    @Override
    public int getRenderXOffset() {
        return renderXOffset;
    }

    /**
     * @param offset
     *            setta la Offset del render.
     */
    public void setRenderXOffset(final int offset) {
        renderXOffset = offset;
    }

    @Override
    public int getHealth() {
        return health;
    }

    /**
     * 
     * @return il danno corpo a corpo del mob
     */

    public int getDamage() {
        return damagemelee;
    }

    @Override
    public void setHealth(final int health) {
        this.health = health;
    }

    @Override
    public void setDamagedTimer(final int timer) {
        damagedTimer = timer;
    }

    @Override
    public void setDamage(final int danno) {
        damagemelee = danno;
    }

    @Override
    public void setSkin(final int skin) {
        this.skin = skin;
    }

    @Override
    public int getSkin() {
        return skin;
    }

    /**
     * 
     * @param iswalking
     *            setta se sta camminando
     */
    public void setWalking(final boolean iswalking) {
        this.walking = iswalking;
    }

    /**
     * 
     * @return ritorna se sta camminando
     */
    protected Boolean getWalking() {
        return this.walking;
    }

    /**
     * 
     * @return ritorna se sta nuotando
     */
    public Boolean getSwimming() {
        return this.swimming;
    }

    /**
     * 
     * @param isswimming
     *            setta se sta nuotando
     */
    public void setSwimming(final boolean isswimming) {
        this.swimming = isswimming;
    }

    /**
     * 
     * @return ritorna se sta attaccando
     */
    protected Boolean getAttacking() {
        return this.attacking;
    }

    /**
     * 
     * @param isattacking
     *            setta se sta attaccando
     */
    public void setAttacking(final boolean isattacking) {
        this.attacking = isattacking;
    }

    /**
     * 
     * @return ritorna se danneggiato
     */
    public Boolean getDamaged() {
        return this.damaged;
    }

    /**
     * @param isdamaged
     *            setta se danneggiato
     */
    public void setDamaged(final boolean isdamaged) {
        this.damaged = isdamaged;
    }

    /**
     * 
     * @return ritorna il nome del mob
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return ritorna il levelColor
     */
    public int getLevelColor() {
        return this.levelColor;
    }

    /**
     * 
     * @return il time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * 
     * @return timer
     */
    public int getTimerAttack() {
        return this.timer;
    }

    /**
     * 
     * @param direction
     *            setta la direzione
     */
    public void setDirection(final Direction direction) {
        this.dir = direction;
    }

    /**
     * .
     * 
     * @return direzione
     */
    public Direction getDirection() {
        return dir;
    }

    /**
     * 
     * @return vector integer di movement
     */
    public Vector2<Integer> getMovement() {
        return movement;
    }

    /**
     * 
     * @param settertimer
     *            setter del timer
     */
    protected void setTimer(final int settertimer) {
        timer = settertimer;
    }

    @Override
    public boolean isCanSwim() {
        return canSwim;
    }

    /**
     * setta se si puo nuotare sopra.
     * 
     * @param canSwim
     *            se si puo nuotare sopra.
     */
    protected void setCanSwim(final boolean canSwim) {
        this.canSwim = canSwim;
    }

    /**
     * @return l'animation
     */
    protected AnimatedSprite getAnimation() {
        return animation;
    }

    /**
     * @param animation
     *            setta l'animation con quella passata
     */
    protected void setAnimation(final AnimatedSprite animation) {
        this.animation = animation;
    }

    /**
     * @return ritorna la lista della animations.
     */
    protected List<List<AnimatedSprite>> getAnimations() {
        return animations;
    }

    /**
     * @param animations
     *            setta l'animations con quella passata
     */
    protected void setAnimations(final List<List<AnimatedSprite>> animations) {
        this.animations = animations;
    }

    @Override
    public Vector2<Integer> getLastMovement() {
        return lastMovement;
    }

    @Override
    public void setLastMovement(final Vector2<Integer> movement) {
        this.lastMovement.set(movement);
    }

    /**
     * funzione per far muovere i mob random.
     */
    protected void randomMove() {
        int movementX = 0;
        int movementY = 0;
        final int cost30 = 30;
        final int cost7 = 7;
        boolean collision = false;
        if (getTime() % cost30 == 0 || collision) {
            n = random.nextInt(cost7);
            collision = false;
        }
        if (n <= 3) {
            if (n == 0) {
                if (isCrossable(new Vector2Impl<Integer>(0, 1))) {
                    setMyAnimation(Animation.DOWN);
                    movementY += getTime() % 2;
                } else {
                    collision = true;
                }
            }
            if (n == 1) {
                if (isCrossable(new Vector2Impl<Integer>(0, -1))) {
                    setMyAnimation(Animation.UP);
                    movementY -= getTime() % 2;
                } else {
                    collision = true;
                }
            }
            if (n == 2) {
                if (isCrossable(new Vector2Impl<Integer>(1, 0))) {
                    setMyAnimation(Animation.RIGHT);
                    movementX += getTime() % 2;
                } else {
                    collision = true;
                }
            }
            if (n == 3) {
                if (isCrossable(new Vector2Impl<Integer>(-1, 0))) {
                    setMyAnimation(Animation.LEFT);
                    movementX -= getTime() % 2;
                } else {
                    collision = true;
                }
            }
            getMovement().setX(getMovement().getX() + movementX);
            getMovement().setY(getMovement().getY() + movementY);
        }

    }

    /**
     * @param anim
     *            setter dell'animation per ogni mob.
     */
    protected void setMyAnimation(final Animation anim) {
        setAnimation(getAnimations().get(getSkin()).get(anim.ordinal()));
    }

    private boolean isCrossable(final Vector2<Integer> movement) {
        return !tileCollision(movement) && !controllImpactRandom(movement);
    }

    /**
     * Controlla se alla posizione passata come parametro c'e un mob o un
     * oggetto solido.
     * 
     * @param movement
     *            Movimento del mob di cui controllare la presenza di
     *            collisione.
     * @return True se sulla tile specificata è presente un oggetto solid o un
     *         mob.
     */
    protected boolean controllImpactRandom(final Vector2<Integer> movement) {
        return mobCollision(movement, false) != null || solidTileCollision(movement);
    }

    /**
     * Controlla se alla posizione passata come parametro c'e un mob o un
     * oggetto solido.
     * 
     * @param position
     *            Posizione del mob di cui controllare la presenza di
     *            collisione.
     * @return True se sulla tile specificata è presente un oggetto solid o un
     *         mob.
     */
    protected boolean controllImpact(final Vector2<Integer> position) {
        return Game.getGame().getLevel().getCoordinateMobs(this).contains(position) || getLevel()
                .getSolidTiles()[position.getX() + position.getY() * (int) getLevel().getDimension().getWidth()];
    }

    @Override
    public boolean solidTileCollision(final Vector2<Integer> movement) {
        /* se x < 0 direzione ovest */
        if (movement.getX() < 0) {
            for (int y = (int) getHitbox().getMinY(); y < (int) getHitbox().getMaxY(); y++) {
                if (getLevel().isSolidTileItem(new Vector2Impl<Integer>((int) getHitbox().getMinX(), y), movement)) {
                    return true;
                }
            }
        } else if (movement.getX() > 0) {
            for (int y = (int) getHitbox().getMinY(); y < (int) getHitbox().getMaxY(); y++) {
                if (getLevel().isSolidTileItem(new Vector2Impl<Integer>((int) getHitbox().getMaxX(), y), movement)) {
                    return true;
                }
            }
        }
        /* se y > 0 direzione nord */
        if (movement.getY() > 0) {
            for (int x = (int) getHitbox().getMinX(); x < (int) getHitbox().getMaxX(); x++) {
                if (getLevel().isSolidTileItem(new Vector2Impl<Integer>(x, (int) getHitbox().getMaxY()), movement)) {
                    return true;
                }
            }
        } else if (movement.getY() < 0) {
            for (int x = (int) getHitbox().getMinX(); x < (int) getHitbox().getMaxX(); x++) {
                if (getLevel().isSolidTileItem(new Vector2Impl<Integer>(x, (int) getHitbox().getMinY()), movement)) {
                    return true;
                }
            }
        }
        return false;
    }
}