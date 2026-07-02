package ala.views;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import ala.models.MicheleModel;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
/**
 * MicheleView class.
 * 
 */
public final class MicheleView implements Boss {
    //Attributes:
    private static final Image IMG_MICHELE_CALM_POSE = new Image(MicheleView.class.getResource("/Michele.gif").toExternalForm()); //"calm" pose
    private static final Image IMG_MICHELE_FIRE_ATTACK = new Image(MicheleView.class.getResource("/MicheleAtt.gif").toExternalForm());
    private static final Image IMG_MICHELE_SWORD_ATTACK = new Image(MicheleView.class.getResource("/MicheleSwordAtt.gif").toExternalForm());

    private MicheleVisualEntity michele;
    private BossHealthView micheleHealthBar;
    private LightBallView lightBallView;

    private Pane micheleLayer;
    private Thread thread;
    private Thread thread2;
    private TranslateTransition transition;
    private PathTransition pTransition;
    private Random random = new Random();
    private MicheleModel micheleModel;
    private Path path1;
    private Path path2;
    private Path pathSword;
    private boolean fireAttacksRunning;

    private Thread father;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param micheleLayer
     * @param micheleModel
     * @param lightBallView
     * @param micheleHealthBar
     * @param father
     * 
     */
    public MicheleView(final Pane micheleLayer, final MicheleModel micheleModel, final LightBallView lightBallView, final BossHealthView micheleHealthBar, final Thread father) {
        this.micheleModel = micheleModel;
        this.michele = new MicheleVisualEntity(micheleLayer, IMG_MICHELE_CALM_POSE, micheleModel);

        this.lightBallView = lightBallView;

        this.micheleLayer = micheleLayer;

        transition = new TranslateTransition();
        pTransition = new PathTransition();

        path1 = new Path();
        path2 = new Path();
        pathSword = new Path();

        this.micheleHealthBar = micheleHealthBar;

        this.fireAttacksRunning = false;

        this.father = father;
        run();
    }

    //Getters&Setters:
    public static Image getImgMicheleCalmPose() {
        return IMG_MICHELE_CALM_POSE;
    }

    public Pane getMichele() {
        return this.micheleLayer;
    }

    public MicheleModel getMicheleModel() {
        return micheleModel;
    }

    public BossHealthView getMicheleHealthBar() {
        return micheleHealthBar;
    }

    public MicheleVisualEntity getMicheleVisualEntity() {
        return michele;
    }

    public void setMichele(final MicheleVisualEntity michele) {
        this.michele = michele;
    }

    public static Image getImgMicheleFireAttack() {
        return IMG_MICHELE_FIRE_ATTACK;
    }

    public static Image getImgMicheleSwordAttack() {
        return IMG_MICHELE_SWORD_ATTACK;
    }

    public boolean isFireAttacksRunning() {
        return fireAttacksRunning;
    }

    public void setFireAttackRunning(final boolean fireAttackRunning) {
        this.fireAttacksRunning = fireAttackRunning;
    }

    public LightBallView getLightBallView() {
        return this.lightBallView;
    }

    public Thread getFather() {
        return father;
    }
  //Methods:
    /**
     * Michele fires LightBall.
     * 
     */
    public void micheleFire() {
        michele.getImageView().setImage(IMG_MICHELE_FIRE_ATTACK);
        lightBall();
    }
    /**
     * Set Michele to calm position.
     * 
     */
    public void micheleCalmPose() {
        michele.getImageView().setImage(IMG_MICHELE_CALM_POSE);
    }

    /**
     * Run method for Michele's animation.
     * 
     */
    @Override
    public void run() {
        thread = new Thread() {
            public void run() {
                int rand;
                enterStageMichelePath();
                try {
                    Thread.sleep(1000 * 4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                transition.stop();

                while (micheleModel.isAlive()) {
                    if (getFather().getState().toString().equals("TERMINATED")) {
                        micheleModel.setHealth(0);
                    }
                        rand = random.nextInt(2);
                        switch (rand) {
                            case 0:
                                if (micheleModel.isAlive() || !getFather().getState().toString().equals("TERMINATED")) {
                                    micheleGoLeftFirstTime();
                                }

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                pTransition.stop();
                                if (micheleModel.isAlive() || !getFather().getState().toString().equals("TERMINATED")) {
                                    movingMichele();
                                    setFireAttackRunning(true);
                                    fireCounter();
                                }
                                try {
                                    Thread.sleep(1000 * 4 + 10 * 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                setFireAttackRunning(false);
                                if (micheleModel.isAlive() || getFather().getState().toString().equals("TERMINATED")) {
                                    micheleFromLeftToCenter();
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 1:
                                if (micheleModel.isAlive() ||  !getFather().getState().toString().equals("TERMINATED")) {
                                    micheleSwordAttack();
                                }
                                try {
                                    Thread.sleep(1000 * 3 + 1000 * 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                break;
                                default:
                                break;
                        }
                        pTransition.stop();
                    }
                }
        };
        thread.start();
    }
    /**
     * Counter for firing intervalls.
     * 
     */
    public void fireCounter() {
        thread2 = new Thread() {
            public void run() {
                int rand;
                while (isFireAttacksRunning() && micheleModel.isAlive()) {
                    if (!getFather().getState().toString().equals("TERMINATED")) { 
                        rand = random.nextInt(3);
                        try {
                            TimeUnit.SECONDS.sleep(rand);
                        } catch (InterruptedException e) {
                            e.printStackTrace(); 
                        }
                        if ((isFireAttacksRunning() && micheleModel.isAlive())) {
                            micheleFire();
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            transition.stop();
                        }
                        if (micheleModel.isAlive()) {
                            micheleCalmPose();
                        }
                    }
                }
            }
        };
        thread2.start();
    }
    /**
     * Stop all transitions when Michele dies.
     * 
     */
    public void stop() {
        pTransition.stop();
        transition.stop();
    }
    /**
     * Michele entering transition.
     * 
     */
    public void enterStageMichelePath() {
        transition.setNode(getMichele());
        transition.setDuration(Duration.millis(1000 * 4));
        transition.setToY(100 * 4);
        transition.play();
    }
    /**
     * Move Michele to left for first time.
     * 
     */
    public void micheleGoLeftFirstTime() {
        pTransition.setNode(getMichele());
        pTransition.setDuration(Duration.millis(1000));
        pTransition.setAutoReverse(false);

        path1.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET));
        path1.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.X_OFFSET));

        pTransition.setPath(path1);

        pTransition.play();
        path1.getElements().clear();
    }
    /**
     * Move Michele from left to center.
     * 
     */
    public void micheleFromLeftToCenter() {
        pTransition.setNode(getMichele());
        pTransition.setDuration(Duration.millis(1000));
        pTransition.setAutoReverse(false);
        pTransition.setCycleCount(1);

        path1.getElements().add(new MoveTo(MicheleModel.Y_OFFSET, MicheleModel.X_OFFSET));
        path1.getElements().add(new LineTo(360, MicheleModel.X_OFFSET));

        pTransition.setPath(path1);

        pTransition.play();
        path1.getElements().clear();
    }
    /**
     * Move Michele.
     * 
     */
    public void movingMichele() {
        pTransition.setNode(getMichele());
        pTransition.setDuration(Duration.millis(1000 * 2));
        pTransition.setAutoReverse(true);
        pTransition.setCycleCount(2);

        path2.getElements().add(new MoveTo(MicheleModel.Y_OFFSET, MicheleModel.X_OFFSET));
        path2.getElements().add(new LineTo(MicheleModel.Y_OFFSET2 * 3, MicheleModel.X_OFFSET));

        pTransition.setPath(path2);

        pTransition.play();
        path2.getElements().clear();
    }
    /**
     * Michele LightBall attack.
     * 
     */
    public void lightBall() {
        transition.setNode(lightBallView.getLayer());
        transition.setDuration(Duration.millis(1000));

        transition.setFromY(MicheleModel.Y_OFFSET2);
        transition.setFromX(micheleModel.getX() + 100);
        transition.setToY(1000);
        transition.setToX(micheleModel.getX() + 100);
        transition.play();
    }
    /**
     * Michele sword attack in random directions.
     * 
     */
    public void micheleSwordAttack() {
        michele.getImageView().setImage(MicheleView.IMG_MICHELE_SWORD_ATTACK);
        int rand = random.nextInt(3 * 2);

        pTransition.setNode(getMichele());
        pTransition.setDuration(Duration.millis(1000 * 3));
        pTransition.setAutoReverse(true);

        switch (rand) {
            case 0:
                pathSword.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET4, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET4, MicheleModel.X_OFFSET));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.Y_OFFSET3));

                pTransition.setPath(pathSword);
                break;
            case 1:
                pathSword.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET2, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET2, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(0, MicheleModel.Y_OFFSET3));

                pTransition.setPath(pathSword);
                break;
            case 2:
                pathSword.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.Y_OFFSET4));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.X_OFFSET2));

                pTransition.setPath(pathSword);
                break;
            case 3:
                pathSword.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.Y_OFFSET4));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.X_OFFSET2));

                pTransition.setPath(pathSword);
                break;
            case 4:
                pathSword.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET3, MicheleModel.X_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET3, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET3, MicheleModel.X_OFFSET3));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.Y_OFFSET3));

                pTransition.setPath(pathSword);
                break;
            case 3 + 2:
                pathSword.getElements().add(new MoveTo(360, MicheleModel.X_OFFSET2));
                pathSword.getElements().add(new LineTo(1000, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET3, MicheleModel.X_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET3, MicheleModel.Y_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.X_OFFSET3, MicheleModel.X_OFFSET3));
                pathSword.getElements().add(new LineTo(MicheleModel.Y_OFFSET, MicheleModel.Y_OFFSET3));

                pTransition.setPath(pathSword);
                break;
                default:
                break;
            }

        pTransition.setCycleCount(2);
        pTransition.play();
        pathSword.getElements().clear();
    }
}
