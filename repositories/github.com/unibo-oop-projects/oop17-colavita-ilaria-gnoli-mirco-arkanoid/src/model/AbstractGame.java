package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javafx.util.Pair;
import model.entities.Ball;
import model.entities.Bar;
import model.entities.Brick;
import model.entities.EntityFactory;
import model.entities.IEntity;
import model.entities.IEntityFactory;
import model.entities.PowerUp;
import model.entities.Wall;
import utility.CollisionUtility;

/**
 * Classe astratta che modella una generica partita. Implementa {@link IBasicGame}.
 * @author Gnoli Mirco
 *
 */
public abstract class AbstractGame implements IAdvancedGame {

    private GameStatus status;
    private int score;
    private int lives;

    private  List<Ball> balls;
    private List<Wall> walls;
    private Bar bar;
    private Map<Integer, List<Brick>> bricks;
    private List<PowerUp> pow;

    private IEntityFactory factory;

    /**
     * Costruttore che inizializza tutti i parametri di gioco.
     */
    public AbstractGame() {
        this.status = GameStatus.START;
        this.score = ModelCostant.SCORE_ON_START; 
        this.lives = ModelCostant.LIVES_ON_START;

        this.factory = new EntityFactory();
        this.balls = new ArrayList<>();
        this.pow = new ArrayList<>();

        this.bricks = factory.randomBrickMap();
        this.walls = factory.standardWalls();
        this.bar = factory.standardBar();
    }

    /**
     * 
     * Metodo che reinizializza il gioco ad ogni pallina persa.
     * Inserisce una nuova pallina ed una nuova barra a centro schermo.
     */
    public void initOnStart() {
        this.balls.add(factory.standardBall());
        this.bar = factory.standardBar();
    }

    /**
     * 
     * @param b - {@link Ball} persa.
     */
    private void lostBall(final Ball b) {
        this.balls.remove(b);
        if (this.balls.size() == 0) {
            this.status = GameStatus.START;
            lostLife();
        }
    }

    /**
     * 
     * Metodo per decrementare il numero delle vite.
     */
    private void lostLife() {
        this.lives--;
        if (this.lives == 0) {
            this.status = GameStatus.LOST;
        }
    }

    @Override
    public final void incLives() {
        this.lives++;
    }

    /**
     * Metodo per aggiungere una {@link Ball} alla partita.
     * @param ball 
     */
    public void addBall(final Ball ball) {
        this.balls.add(ball);
    }

    @Override
    public final void addBalls() { //puo servire x aggiungere le multiple
        List<Ball> newBallList = new ArrayList<>();
        for (Ball ball : this.balls) {
            newBallList.add(ball);
            newBallList.addAll(this.factory.multipleBall(ball));
        }
        this.balls = newBallList;
    }

    /**
     * Metodo usato per "rompere" un mattone e rimuoverlo da quelli presenti in gioco.
     * @param key - numero della riga in cui si trova il mattone
     * @param brick - mattone da rimuovere
     */
    private void breakBrick(final Integer row, final Brick brick) {
        List<Brick> newRow = this.bricks.get(row);
        newRow.remove(brick);

        if (newRow.isEmpty()) {
            newRow = doIfBricksRowIsEmpty(brick);
        }

        this.bricks.replace(row, newRow);
        addPowerUp(brick);
        incScore(brick);

        if (counterBrick() == 0) {
            this.status = GameStatus.WON;
        }
    }
    /**
     * Metodo per aggiungere un power up alla lista.
     */
    private void addPowerUp(final Brick generator) {
        Optional<PowerUp> powerUp = this.factory.randomPowerUp(generator);
        if (powerUp.isPresent()) {
            this.pow.add(powerUp.get());
        }
    }

    /**
     * Metodo astratto per gestire il caso in cui l'ultimo mattone di una riga sia stato distrutto.
     *
     * @param template - ultimo {@link Brick} della riga, andato distrutto. 
     * @return List(Brick)
     */
    protected abstract List<Brick> doIfBricksRowIsEmpty(Brick template);

    /**
     * Incrementa il punteggio.
     * @param brick - {@link Brick} da cui deriva l'incremento del punteggio.
     */
    private void incScore(final Brick brick) {
        //this.score += b.getScoreOnBreak();
        this.score++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModel() {
        for (Ball ball : this.balls) {
            ball.refreshPosition();

            if (CollisionUtility.intersects(ball, this.bar)) {
                ball.bounce(this.bar);
            } else if (ball.getMaxY() > this.bar.getMaxY()) {
               // lostBall(ball);
               // break;
            }

            for (Wall l : this.walls) {
                if (CollisionUtility.intersects(ball, l)) {
                    ball.bounce(l);
                }
            }

            Optional<Pair<Integer, Brick>> brickToRemove = Optional.empty();

            for (Entry<Integer, List<Brick>> entry : this.bricks.entrySet()) {
                for (Brick brick : entry.getValue()) {
                    if (CollisionUtility.intersects(ball, brick)) {
                        brickToRemove = Optional.of(new Pair<>(entry.getKey(), brick)); //se il mattone è da rimuovere che esplode
                        ball.bounce(brick);
                    }
                }
            }

            if (brickToRemove.isPresent()) {
                breakBrick(brickToRemove.get().getKey(), brickToRemove.get().getValue());
            }

            
            /* **************************************************************************** */
/*
            bar.getMinX();
            System.out.println("Stampa x :" + bar.getMinX());
            bar.moveRight();
//            System.out.println("Stampa x2 :" + bar.getMinX());
            bar.moveRight();
            System.out.println("Nuova x: " + bar.getMaxX());
//            System.out.println("Stampa x3 :" + bar.getMinX());
            //PROVA
//            System.out.println("Stampa x: " + bar.getMinX());
//            bar.moveLeft();
//            System.out.println("Stampa x: " + bar.getMinX());
//                      System.out.println("Stampa x: " + bar.getPosition());

            /* **************************************************************************** */
        }
        updatePowerUp();
    }
    private void updatePowerUp() {
        List<PowerUp> toDelete = new ArrayList<>();
        for (PowerUp p : this.pow) {
            p.refreshPosition();
            
            if (CollisionUtility.intersects(p, this.bar)) {
                p.active(this);
                toDelete.add(p);
            } else if (p.getMaxY() > this.bar.getMaxY()) {
                toDelete.add(p);
            }
        }
        if (toDelete.size() > 0) {
            this.pow.removeAll(toDelete);
            toDelete = new ArrayList<>();
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPause() {
        if (this.status.equals(GameStatus.RUNNING)) {
            this.status = GameStatus.PAUSE;
        } else if (this.status.equals(GameStatus.PAUSE) || this.status.equals(GameStatus.START)) {
            this.status = GameStatus.RUNNING;
        }
    }

    @Override
    public final List<Ball> getBalls() {
        return this.balls;
    }

    @Override
    public final Map<Integer, List<Brick>> getBricks() {
        return this.bricks;
    }

    @Override
    public final Bar getBar() {
        return this.bar;
    }

    @Override
    public final List<Wall> getWalls() {
        return this.walls;
    }

    @Override
    public final GameStatus getStatus() {
        return this.status;
    }

    @Override
    public final int getScore() {
        return this.score;
    }

    @Override
    public final int getLives() {
        return this.lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GameModel [status=" + status + ", score=" + score + ", lives=" + lives + "]";
    }

    @Override
    public final List<IEntity> getAllEntities() {
        List<IEntity> list = new ArrayList<>();

        list.addAll(this.balls);

        list.add(this.bar);

        list.addAll(this.pow);

        for (Entry<Integer, List<Brick>> entry : this.bricks.entrySet()) {
            list.addAll(entry.getValue());
        }

        return list;
    }

    /**
     * Metodo che torna la factory di Entity.
     * @return {@link IEntityFactory}
     */
    protected final IEntityFactory getFactory() {
        return this.factory;
    }

    //  ** UTILITA'  **  // METTERE in una classe a parte e farlo generico?
    //valutare adapter per trasformare mappa in una lista.
    private int counterBrick() {
        int count = 0;
        for (List<Brick> l : this.bricks.values()) {
            count += l.size();
        }
        return count;
    }
}
