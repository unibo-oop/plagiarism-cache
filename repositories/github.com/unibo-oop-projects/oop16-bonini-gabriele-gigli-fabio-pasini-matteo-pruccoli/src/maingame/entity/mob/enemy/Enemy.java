package maingame.entity.mob.enemy;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import maingame.entity.mob.MobImpl;
import maingame.game.Game;
import maingame.graphics.AnimatedSprite;
import maingame.graphics.ScreenImpl;
import maingame.graphics.SpriteSheetImpl;
import maingame.level.tile.Tile;
import maingame.level.tile.TileImpl;
import maingame.sound.SoundImpl;
import util.Node;
import util.Vector2;
import util.Vector2Impl;

/**
 * Classe Enemy.
 */
public class Enemy extends MobImpl {
    private static final int DISTANCE_TO_ATTACK = 30;
    private static final int STUCK_TIMER = 30;
    private static final int TIMER_TO_ATTACK = 30;
    private static final int DISTANCE_TO_FIND_PATH = 100;
    private static final int ATTACK_RANGE = 4;
    private static final int TILE_LIMITI_TO_EVALUATE = 500;
    private final List<Integer> indices = Arrays.asList(1, 5, 7, 3, 2, 8, 6, 0);
    private static final int INDEX1 = 1;
    private static final int INDEX2 = 5;
    private static final int INDEX3 = 7;
    private static final int INDEX4 = 3;
    private List<Node> path = Collections.emptyList();
    private int stuckTimerCount;
    private boolean stuck;
    private Vector2<Integer> lastPosition;
    private Vector2<Integer> playerPosition;
    private boolean pathNotFound;
    private static final int NUM_ANIM_ENEMY1 = 2;
    private static final int NUM_ANIM_ENEMY2 = 2;
    // Dimensione animazione di tipo 1 del nemico di tipo 1
    private static final Dimension DIM_ANIM_TYPE1_ENEMY1 = new Dimension(1, 4);
    // Dimensione animazione di tipo 2 del nemico di tipo 1
    private static final Dimension DIM_ANIM_TYPE2_ENEMY1 = new Dimension(1, 3);
    private static final Dimension DIM_ANIM_TYPE1_ENEMY2 = new Dimension(1, 4);
    private static final Dimension DIM_ANIM_TYPE2_ENEMY2 = new Dimension(1, 3);
    private static final int TIME_TO_REFRESH_ANIM = 5;

    private static final int EXP = 100;

    /**
     * Crea un nuovo enemy nel punto position.
     * 
     * @param position
     *            coordinate dove posziona l'enemy alla creazione
     */
    public Enemy(final Vector2<Integer> position) {
        super(position, false);
        final int dimRectangle = 6;
        super.setHitbox(new Rectangle(position.getX() + 1, position.getY() + 1, dimRectangle, dimRectangle));
        getAnimations().add(AnimatedSprite.createAnimation(NUM_ANIM_ENEMY1,
                new Dimension[] { DIM_ANIM_TYPE1_ENEMY1, DIM_ANIM_TYPE2_ENEMY1 },
                new int[] { TIME_TO_REFRESH_ANIM, TIME_TO_REFRESH_ANIM }, SpriteSheetImpl.ENEMY));
        getAnimations().add(AnimatedSprite.createAnimation(NUM_ANIM_ENEMY2,
                new Dimension[] { DIM_ANIM_TYPE1_ENEMY2, DIM_ANIM_TYPE2_ENEMY2 },
                new int[] { TIME_TO_REFRESH_ANIM, TIME_TO_REFRESH_ANIM }, SpriteSheetImpl.ENEMY2));
        super.setMyAnimation(Animation.DOWN);

        setSprite((getAnimation().getSprite()));
        setRenderXOffset((int) getSprite().getDimension().getWidth() / 4);
    }

    /**
     * crea un nuovo nemico con delle impostazioni definite per identificarlo
     * all'interno di level.
     * 
     * @param levelColor
     *            indica a quale colore associare la classe enemy durante il
     *            caricamento delle entità.
     * @param name
     *            identifica l'enemy
     */
    public Enemy(final int levelColor, final String name) {
        super(levelColor, name);
    }

    @Override
    public void update() {

        if (!Game.getGame().isEditor()) {
            playerPosition = getLevel().getPlayer().getPosition();
            if (getWalking() || getAttacking()) {
                getAnimation().update();
                if (getAttacking() && getAnimation().getCount() == 1) {
                    getAnimation().resetCount();
                    setAttacking(false);
                    refreshAnimation(getAttacking());
                }
            } else {
                getAnimation().setFrame(0);
            }
            final Vector2<Integer> start = tileCoordinate(getPosition(), new Vector2Impl<Integer>(0, 0));
            final Vector2<Integer> destination = tileCoordinate(playerPosition, new Vector2Impl<Integer>(0, 0));
            final double distance = Vector2Impl.getDistance(getPosition(), playerPosition);

            if (distance <= DISTANCE_TO_FIND_PATH && distance >= DISTANCE_TO_ATTACK
                    && !getLevel().getPlayer().isSwimming()) {
                if (getTime() % 4 == 0 && !pathNotFound) {
                    path = findPath(start, destination);
                }
            } else {
                path = Collections.emptyList();
                pathNotFound = false;
            }

            if (!path.isEmpty()) {
                final Node step = path.get(path.size() - 1);
                final Vector2<Integer> nextStep = new Vector2Impl<>(step.getPosition().getX() * TileImpl.TILE_SIZE,
                        step.getPosition().getY() * TileImpl.TILE_SIZE);
                nextStep(nextStep, stuck);
            } else if (getLevel().getPlayer().isSwimming() || distance > DISTANCE_TO_FIND_PATH || pathNotFound) {
                super.randomMove();
            } else if (!stuck) {
                if (getTimerAttack() <= 0 && mobCollision(getLastMovement(), false) == getLevel().getPlayer()) {
                    if (!hitPlayer() && getTimerAttack() <= 0) {
                        nextStep(playerPosition, stuck);
                        if (getPosition().equals(lastPosition)) {
                            stuckTimerCount++;
                        }
                        if (stuckTimerCount >= STUCK_TIMER) {
                            stuck = true;
                        }
                    }
                } else if (getTimerAttack() <= 0) {
                    nextStep(playerPosition, stuck);
                }
            } else {
                nextStep(playerPosition, stuck);
            }
            super.update();
            if (getHealth() <= 0) {
                SoundImpl.DEAD.play(false);
                if (getLevel().getPlayer().getHealth() > 0) {
                    getLevel().getPlayer().setExp(EXP);
                    ScreenImpl.getScreen().setExpAnim(true, EXP);
                }
            }

            this.updatStuckTimer();
            this.setWalking();
        }

    }

    private void setWalking() {
        if (getMovement().getX() != 0 || getMovement().getY() != 0) {
            setLastMovement(getMovement());
            move(getMovement(), false);
            lastPosition = new Vector2Impl<Integer>(getPosition());
            getMovement().set(0, 0);
            setWalking(true);
        } else if ((getTime() % 2) == 0) {
            setWalking(false);
        }
    }

    private void updatStuckTimer() {
        if (stuck) {
            stuckTimerCount--;
        }
        if (stuckTimerCount <= 0) {
            stuck = false;
        }
    }

    /*
     * controlla tramite posizione in pixel la locazione del mob rispetto al
     * player
     */
    private void nextStep(final Vector2<Integer> nextStep, final boolean stuck) {
        final Vector2Impl<Integer> move = new Vector2Impl<>(0, 0);
        final int posY = getPosition().getY();
        final int posX = getPosition().getX();
        final int nextY = nextStep.getY();
        final int nextX = nextStep.getX();
        if (posY < nextY) {
            super.setMyAnimation(Animation.DOWN);
            move.setY(+((getTime() % 2)));
        }
        if (posY > nextY) {
            super.setMyAnimation(Animation.UP);
            move.setY(-((getTime() % 2)));
        }
        if (posX < nextX) {
            super.setMyAnimation(Animation.RIGHT);
            move.setX(+((getTime() % 2)));
        }
        if (posX > nextX) {
            super.setMyAnimation(Animation.LEFT);
            move.setX(-((getTime() % 2)));
        }
        final int stuckXMove = stuck ? -1 : 1;
        move.setX(move.getX() * stuckXMove);
        setEnemyMovement(move);
    }

    private void setEnemyMovement(final Vector2<Integer> move) {
        getMovement().set(move);
    }

    private boolean hitPlayer() {
        final int lastY = getLastMovement().getY();
        if (lastY < 0 && playerPosition.getY() < getPosition().getY()
                && getPosition().getX() < playerPosition.getX() + ATTACK_RANGE
                && getPosition().getX() > playerPosition.getX() - ATTACK_RANGE) {
            return attackPlayer();
        }
        if (lastY > 0 && playerPosition.getY() > getPosition().getY()
                && getPosition().getX() < playerPosition.getX() + ATTACK_RANGE
                && getPosition().getX() > playerPosition.getX() - ATTACK_RANGE) {
            return attackPlayer();
        }
        final int lastX = getLastMovement().getX();
        if (lastX < 0 && playerPosition.getX() < getPosition().getX()
                && getPosition().getY() < playerPosition.getY() + ATTACK_RANGE
                && getPosition().getY() > playerPosition.getY() - ATTACK_RANGE) {
            return attackPlayer();
        }
        if (lastX > 0 && playerPosition.getX() > getPosition().getX()
                && getPosition().getY() < playerPosition.getY() + ATTACK_RANGE
                && getPosition().getY() > playerPosition.getY() - ATTACK_RANGE) {
            return attackPlayer();
        }
        return false;
    }

    private boolean attackPlayer() {
        final boolean myAttack = attack();
        setAttacking(true);
        setTimer(TIMER_TO_ATTACK);
        refreshAnimation(getAttacking());
        return myAttack;
    }

    private void refreshAnimation(final boolean attacking) {
        switch (getDirection()) {
        case LEFT:
            super.setMyAnimation(attacking ? Animation.ATTACK_LEFT : Animation.LEFT);
            break;
        case RIGHT:
            super.setMyAnimation(attacking ? Animation.ATTACK_RIGHT : Animation.RIGHT);
            break;
        case UP:
            super.setMyAnimation(attacking ? Animation.ATTACK_UP : Animation.UP);
            break;
        case DOWN:
            super.setMyAnimation(attacking ? Animation.ATTACK_DOWN : Animation.DOWN);
            break;
        default:
            super.setMyAnimation(attacking ? Animation.ATTACK_UP : Animation.UP);
            break;
        }
    }

    private List<Node> findPath(final Vector2<Integer> start, final Vector2<Integer> goal) {
        final int evaluate1 = 2;
        final int evaluate2 = 8;
        final int evaluate3 = 6;
        final int evaluate4 = 0;
        // fonte https://en.wikipedia.org/wiki/A*_search_algorithm
        //contiene nodi che sto valutando
        final List<Node> openList = new ArrayList<>();
        //nodi gia valutati
        final List<Node> closedList = new ArrayList<>();
        /*
         * nodo attuale che sto valutando, start:tile partenza, poi nodo da
         * quale arrivo, costo dall'inizio del percorso in tile, costo da adesso
         * a fine(distanza euclidea)
         */
        Node current = new Node(start, null, 0, Vector2Impl.getDistance(start, goal));
        openList.add(current);
        while (!openList.isEmpty()) {
            // ordino nodi in base alla distanza, fcost distanza tot
            // lamba comparator
            Collections.sort(openList);
            current = openList.get(0);
            // se sono arrivato alla fine, ricreo il cammino all'inverso
            if (current.getPosition().equals(goal)) {
                final List<Node> path = new ArrayList<>();
                while (current.getParent() != null) {
                    path.add(current);
                    current = current.getParent();
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            // se la current non è == a goal la metto tra i nodi valutati
            openList.remove(current);
            closedList.add(current);
            if (closedList.size() > TILE_LIMITI_TO_EVALUATE) {
                pathNotFound = true;
                break;
            }

            final boolean[] solid = { false, false, false, false };
            final Iterator<Integer> it = indices.iterator();
            while (it.hasNext()) {
                /*
                 * verfico prima le tile adiaceti non oblique, perchè in caso
                 * fossero solide, le tile oblique confinanti alla tile solida
                 * non le devo prendere in considerazione dato che per colpa dell'hitbox
                 * risulterebbero non attraversabili
                 */
                final int i = it.next();
                final int xi = (i % 3) - 1;
                final int yi = (i / 3) - 1;

                final Vector2<Integer> vect = new Vector2Impl<>(current.getPosition().getX() + xi,
                        current.getPosition().getY() + yi);
                // prendo la tile adiacente
                final Tile at = Game.getGame().getLevel().getTile(vect);

                if (at == null) {
                    continue;
                }
                if (at.isSolid() || at.isSwimmable() || controllImpact(vect)) {
                    switch (i) {
                    case INDEX1:
                        solid[0] = true;
                        break;
                    case INDEX2:
                        solid[1] = true;
                        break;
                    case INDEX3:
                        solid[2] = true;
                        break;
                    case INDEX4:
                        solid[3] = true;
                        break;
                    default:
                        break;
                    }
                    continue;
                }

                /*
                 * guardo se la tile obliqua è adiacente a una tile solida, in
                 * tal caso non la considero
                 */
                if ((i == evaluate1 && (solid[0] || solid[1])) || (i == evaluate2 && (solid[1] || solid[2]))
                        || (i == evaluate3 && (solid[2] || solid[3])) || (i == evaluate4 && (solid[3] || solid[0]))) {
                    continue;
                }

                // costo passo che devo fare
                double gCost;
                final double linearCost = 1.0;
                final double obliqueCost = 1.01;
                /*
                 * costo da tile adiacente a current(quella che sto
                 * considerando) alla fine
                 */
                if (xi != 0 && yi != 0) {
                    gCost = current.getGCost() + linearCost;
                } else {
                    gCost = current.getGCost() + obliqueCost;
                }
                 //hcost costo da vect a fine
                final double hCost = Vector2Impl.getDistance(vect, goal);

                final Node neighbour = new Node(vect, current, gCost, hCost);
                /*
                 * guardo se nella lista dei nodi valutati è gia presente un
                 * nodo con le stesse coordinate
                 * di neighbour ma con costi differenti
                 */
                final Node n = vecInList(closedList, vect);
                final Node n1 = vecInList(openList, vect);
                // se l'ho gia considerato continuo a controllare gli altri
                if (n != null) {
                    continue;
                }
                // se non l'ho considerato allora lo aggiungo alla openlist
                if (n1 == null) {
                    openList.add(neighbour);
                } else
                // se c'è gia controllo se fcost delle tile adiacenti a quella
                // che sto considerando è
                // maggiore di fcost del nodo uguale a neighbour che c'è gia
                if (gCost + hCost >= n1.getFCost()) {
                    continue;
                } else {
                    openList.remove(n1);
                    openList.add(neighbour);
                }
            }

        }
        closedList.clear();
        return Collections.emptyList();
    }

    private Node vecInList(final List<Node> list, final Vector2<Integer> vector) {
        for (final Node n : list) {
            if (n.getPosition().equals(vector)) {
                return n;
            }
        }
        return null;
    }
}
