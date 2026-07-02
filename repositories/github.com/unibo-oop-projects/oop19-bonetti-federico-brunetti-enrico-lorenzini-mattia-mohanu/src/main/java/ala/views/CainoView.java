package ala.views;

import ala.models.CainoModel;
import ala.models.LuciferModel;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
/**
 * CainoView class.
 * 
 */
public class CainoView implements Boss {
    //Attributes:
    private static final Image IMG_CAINO_CALM_POSE_LEFT = new Image(CainoView.class.getResource("/CainoLeft.gif").toExternalForm()); 
    private static final Image IMG_CAINO_ATTACK_LEFT = new Image(CainoView.class.getResource("/cainoAttackLeft.gif").toExternalForm());
    private static final Image IMG_CAINO_KNOCK_BACK_LEFT = new Image(CainoView.class.getResource("/knockbackCainoLeft.gif").toExternalForm());
    private static final Image IMG_CAINO_CALM_POSE_RIGHT = new Image(CainoView.class.getResource("/CainoRight.gif").toExternalForm());
    private static final Image IMG_CAINO_ATTACK_RIGHT = new Image(CainoView.class.getResource("/cainoAttackRight.gif").toExternalForm());
    private static final Image IMG_CAINO_KNOCK_BACK_RIGHT = new Image(CainoView.class.getResource("/knockbackCainoRight.gif").toExternalForm());

    private Thread thread;
    private Thread asteroidsThread;
    private Thread deathThread;

    private TranslateTransition transition;
    private TranslateTransition moonTransition;
    private Pane cainoLayer;
    private PathTransition pathTransition;
    private PathTransition pathTransitionAttack;
    private PathTransition pathTransitionToMoon;
    private PathTransition pathTransitionDeath;
    private Path path1;
    private Path path2;
    private CubicCurve cubicCurve;
    private CubicCurve cubicCurveDeath;

    private CainoVisualEntity caino;
    private MoonView moonView;

    private CainoModel cainoModel;
    private LuciferModel luciferModel;
    private BossHealthView cainoHealthView;
    private AsteroidView asteroidView;

    private Thread father;

    //Graphic calibration variables:
    private static final int START_X = 370; 
    private static final int START_Y = 200;
    private static final int CONTROL_X1 = 700; 
    private static final int CONTROL_Y1 = 500;
    private static final int CONTROL_X2 = 800; 
    private static final int CONTROL_Y2 = 600;
    private static final int END_X = 600; 
    private static final int END_Y = 770;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param cainoLayer
     * @param cainoModel
     * @param luciferModel
     * @param moonView
     * @param asteroidView
     * @param cainoHealthBar
     * @param father
     * 
     */
    public CainoView(final Pane cainoLayer, final CainoModel cainoModel, final LuciferModel luciferModel, final MoonView moonView, final AsteroidView asteroidView, final BossHealthView cainoHealthBar, final Thread father) {
        this.caino = new CainoVisualEntity(cainoLayer, IMG_CAINO_CALM_POSE_LEFT, cainoModel);
        this.cainoLayer = cainoLayer;
        this.cainoModel = cainoModel;
        this.cainoHealthView = cainoHealthBar;
        this.luciferModel = luciferModel;
        this.asteroidView = asteroidView;
        this.cubicCurve = new CubicCurve();
        this.cubicCurveDeath = new CubicCurve();

        this.pathTransition = new PathTransition();
        this.pathTransitionAttack = new PathTransition();
        this.pathTransitionToMoon = new PathTransition();
        this.pathTransitionDeath = new PathTransition();
        this.path1 = new Path();
        this.path2 = new Path();
        this.transition = new TranslateTransition();
        this.moonTransition = new TranslateTransition();

        this.moonView = moonView;

        this.father = father;
        run();
    }

    //Getters&Setters:
    public final CainoVisualEntity getCainoVisualEntity() {
        return caino;
    }

    public final MoonView getMoonView() {
        return moonView;
    }

    public final Pane getCainoLayer() {
        return cainoLayer;
    }

    public final void setCainoLayer(final Pane cainoLayer) {
        this.cainoLayer = cainoLayer;
    }

    public final CainoModel getCainoModel() {
        return cainoModel;
    }

    public final void setCainoModel(final CainoModel cainoModel) {
        this.cainoModel = cainoModel;
    }

    public final LuciferModel getLuciferModel() {
        return luciferModel;
    }

    public final void setLuciferModel(final LuciferModel luciferModel) {
        this.luciferModel = luciferModel;
    }

    public final BossHealthView getCainoHealthView() {
        return cainoHealthView;
    }

    public final void setCainoHealthView(final BossHealthView cainoHealthView) {
        this.cainoHealthView = cainoHealthView;
    }

    public final AsteroidView getAsteroidView() {
        return asteroidView;
    }

    public final void setAsteroidView(final AsteroidView asteroidView) {
        this.asteroidView = asteroidView;
    }

    public final Thread getFather() {
        return father;
    }

    public static Image getImgCainoKnockBackLeft() {
        return IMG_CAINO_KNOCK_BACK_LEFT;
    }

    public static Image getImgCainoKnockBackRight() {
        return IMG_CAINO_KNOCK_BACK_RIGHT;
    }

    //Methods:
    /**
      * run method for Caino's animation.
      * 
      */
    @Override
    public void run() {
        thread = new Thread() {
            public void run() {
                cainoEnterWithMoon();
                try {
                    Thread.sleep(1000 * 4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                transition.stop();
                cainoGoToBattlefield();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pathTransition.stop();

                while (cainoModel.getHealth() >= cainoModel.getMaxHealth() / 2 && !getFather().getState().toString().equals("TERMINATED")) {
                    cainoAttack();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pathTransitionAttack.stop();

                    cainoAttackAnimation();
                    try {
                        Thread.sleep(1000 * 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cainoCalmPose();
                }

                backToTheMoon();
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pathTransitionToMoon.stop();

                asteroidsAttack();

                try {
                    Thread.sleep(1000 * 2 * 3 + 1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                cainoGoToBattlefield();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pathTransition.stop();

                while (cainoModel.isAlive() && !getFather().getState().toString().equals("TERMINATED")) {
                    cainoAttack();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pathTransitionAttack.stop();

                    if (cainoModel.isAlive()) {
                        cainoAttackAnimation();
                        try {
                            Thread.sleep(1000 * 3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cainoCalmPose();
                    }
                }
                transition.stop();
            }
        };
        thread.start();
    }

    /**
     * make asteroids attack starts.
     * 
     */
    public final void asteroidsAttack() {
        asteroidsThread = new Thread() {
            public void run() {
                while (cainoModel.isAlive() && !getFather().getState().toString().equals("TERMINATED")) {
                    moonAttack();
                    try {
                        Thread.sleep(1000 * 2);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        asteroidsThread.start();
    }

    /**
     * make Caino enter to main scene.
     * 
     */
    public final void cainoEnterWithMoon() {
        transition.setNode(getCainoLayer());
        transition.setDuration(Duration.millis(1000 * 4));
        transition.setToY(+100);
        transition.play();

        moonTransition.setNode(moonView.getLayer());
        moonTransition.setDuration(Duration.millis(1000 * 4));
        moonTransition.setToY(+100);
        moonTransition.play();
    }

    /**
     * make Caino going down up to Lucifer height.
     * 
     */
    public final void cainoGoToBattlefield() {
        pathTransition.setNode(getCainoLayer());
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setAutoReverse(false);
        pathTransition.setCycleCount(1);

        this.cubicCurve.setStartX(START_X);
        this.cubicCurve.setStartY(START_Y);
        this.cubicCurve.setControlX1(CONTROL_X1);
        this.cubicCurve.setControlY1(CONTROL_Y1);
        this.cubicCurve.setControlX2(CONTROL_X2);
        this.cubicCurve.setControlY2(CONTROL_Y2);
        this.cubicCurve.setEndX(END_X);
        this.cubicCurve.setEndY(END_Y);

        pathTransition.setPath(cubicCurve);
        pathTransition.play();
    }

    /**
     * Make caino going against Lucifer to attack him.
     * 
     */
    public final void cainoAttack() {
        pathTransitionAttack.setNode(getCainoLayer());
        pathTransitionAttack.setDuration(Duration.millis(1000));
        pathTransitionAttack.setAutoReverse(false);
        pathTransitionAttack.setCycleCount(1);

        path1.getElements().add(new MoveTo(cainoModel.getX() - START_Y - 100, END_Y));
        path1.getElements().add(new LineTo(luciferModel.getX() - START_Y, END_Y));

        pathTransitionAttack.setPath(path1);
        pathTransitionAttack.play();
        path1.getElements().clear();
    }

    /**
     * make Caino come up to moon when he lose half of his life.
     * 
     */
    public final void backToTheMoon() {
        pathTransitionToMoon.setNode(getCainoLayer());
        pathTransitionToMoon.setDuration(Duration.millis(1000 * 3));
        pathTransitionToMoon.setAutoReverse(false);
        pathTransitionToMoon.setCycleCount(1);

        path2.getElements().add(new MoveTo(cainoModel.getX() - START_Y - 100, END_Y));
        path2.getElements().add(new LineTo(START_X, END_Y));
        path2.getElements().add(new LineTo(START_X, START_Y));

        pathTransitionAttack.setPath(path2);
        pathTransitionAttack.play();
        path2.getElements().clear();
    }

    /**
     * Change Caino sprites for differents attack animations.
     * 
     */
    public final void cainoAttackAnimation() {
        if (luciferModel.getX() < cainoModel.getX()) {
            caino.getImageView().setImage(IMG_CAINO_ATTACK_LEFT);
        } else {
            caino.getImageView().setImage(IMG_CAINO_ATTACK_RIGHT);
        }
    }

    /**
     * Change Caino sprites for differents calm pose animations.
     * 
     */
    public final void cainoCalmPose() {
        if (luciferModel.getX() < cainoModel.getX()) {
            caino.getImageView().setImage(IMG_CAINO_CALM_POSE_LEFT);
        } else {
            caino.getImageView().setImage(IMG_CAINO_CALM_POSE_RIGHT);
        }
    }

    /**
     * Make rocks falling from the moon vertically basing on Lucifer position.
     * 
     */
    public final void moonAttack() {
        transition.setNode(asteroidView.getLayer());
        transition.setDuration(Duration.millis(1000));
        transition.setFromY(START_Y);
        transition.setFromX(luciferModel.getX());
        transition.setToY(END_Y);
        transition.setToX(luciferModel.getX());
        transition.play();
    }

    /**
     * Caino death animation (falling out of the screen).
     * 
     */
    public final void cainoDeath() {
        pathTransitionDeath.setNode(getCainoLayer());
        pathTransitionDeath.setDuration(Duration.millis(1000));
        pathTransitionDeath.setAutoReverse(false);
        pathTransitionDeath.setCycleCount(1);

        this.cubicCurveDeath.setStartX(this.cainoModel.getX() - START_Y - 100);
        this.cubicCurveDeath.setStartY(END_Y);
        this.cubicCurveDeath.setControlX1(END_Y - 10 * 2);
        this.cubicCurveDeath.setControlY1(CONTROL_Y1 + 100 + 10 * 3 + 10 * 2);
        this.cubicCurveDeath.setControlX2(CONTROL_Y1 + 100 + 10 * 3 + 10 * 2);
        this.cubicCurveDeath.setControlY2(START_Y * 2);
        this.cubicCurveDeath.setEndX(1000);
        this.cubicCurveDeath.setEndY(1000);

        pathTransitionDeath.setPath(cubicCurveDeath);
        pathTransitionDeath.play();

    }

    /**
     * Make Caino explode.
     * 
     */
    public final void explode() {
        deathThread = new Thread() {
            public void run() {
                cainoDeath();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopCaino();
            }
        };
        deathThread.start();
    }

    /**
     * Stop all Caino's animations when he die.
     * 
     */
    public final void stopCaino() {
        pathTransition.stop();
        pathTransitionAttack.stop();
        pathTransitionToMoon.stop();
        transition.stop();
        pathTransitionDeath.stop();
        asteroidView.getImageView().setVisible(false);
    }
}

