package ala.views;

import java.util.Random;

import ala.models.CerberoModel;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
/**
 * CerberoView class.
 * 
 */
public final class CerberoView implements Boss {
    //Attributes:
    private static Image imgCerberoCalmPose = new Image(CerberoView.class.getResource("/Cerbero1.gif").toExternalForm()); //"calm" pose
    private static Image imgCerberoBiting = new Image(CerberoView.class.getResource("/Cerbero2.gif").toExternalForm());
    private static Image imgCerberoFireAttack = new Image(CerberoView.class.getResource("/Cerbero3.gif").toExternalForm());
    private static Image imgCerberoBody = new Image(CerberoView.class.getResource("/CerberoBody.gif").toExternalForm());
    private static Image imgExplosion = new Image(CerberoView.class.getResource("/bigExplosion.gif").toExternalForm());

    private CerberoBodyPartView firstHead;
    private CerberoBodyPartView secondHead;
    private CerberoBodyPartView thirdHead;
    private CerberoBodyPartView body;

    private BossHealthView cerberoHealthBar;

    private Pane cerberoLayer;
    private Thread thread;
    private TranslateTransition transition;
    private Random random = new Random();
    private CerberoModel cerberoModel;

    private FireBallView fireBallView;
    private FireRainView fireRainView;

    private Thread father;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param cerberoLayer
     * @param cerberoModel
     * @param fireBallView
     * @param fireRainView
     * @param cerberoHealthBar
     * @param father
     * 
     */
    public CerberoView(final Pane cerberoLayer, final CerberoModel cerberoModel, final FireBallView fireBallView, final FireRainView fireRainView, final BossHealthView cerberoHealthBar, final Thread father) {
        this.cerberoLayer = cerberoLayer;
        body = new CerberoBodyPartView(cerberoLayer, imgCerberoBody, cerberoModel.getCerberoBody());
        firstHead = new CerberoBodyPartView(cerberoLayer, imgCerberoCalmPose, cerberoModel.getFirstHead());
        secondHead = new CerberoBodyPartView(cerberoLayer, imgCerberoCalmPose, cerberoModel.getSecondHead());
        thirdHead = new CerberoBodyPartView(cerberoLayer, imgCerberoCalmPose, cerberoModel.getThirdHead());

        this.cerberoHealthBar = cerberoHealthBar;

        this.cerberoModel = cerberoModel;

        this.fireBallView = fireBallView;
        this.fireRainView = fireRainView;

        transition = new TranslateTransition();

        alignThreeHeads(cerberoModel.getFirstHead().getX(), cerberoModel.getFirstHead().getY());

        this.father = father;
        run();
    }

    //Getters&Setters:
    public CerberoModel getCerberoModel() {
        return this.cerberoModel;
    }

    public static Image getImgCerberoCalmPose() {
        return imgCerberoCalmPose;
    }

    public static void setImgCerberoCalmPose(final Image imgCerberoCalmPose) {
        CerberoView.imgCerberoCalmPose = imgCerberoCalmPose;
    }

    public static Image getImgCerberoBiting() {
        return imgCerberoBiting;
    }

    public static void setImgCerberoBiting(final Image imgCerberoBiting) {
        CerberoView.imgCerberoBiting = imgCerberoBiting;
    }

    public static Image getImgCerberoFireAttack() {
        return imgCerberoFireAttack;
    }

    public static void setImgCerberoFireAttack(final Image imgCerberoFireAttack) {
        CerberoView.imgCerberoFireAttack = imgCerberoFireAttack;
    }

    public static Image getImgCerberoBody() {
        return imgCerberoBody;
    }

    public static void setImgCerberoBody(final Image imgCerberoBody) {
        CerberoView.imgCerberoBody = imgCerberoBody;
    }

    public Pane getCerberoLayer() {
        return cerberoLayer;
    }

    public FireBallView getFireBallView() {
        return fireBallView;
    }

    public FireRainView getFireRainView() {
        return fireRainView;
    }

    public BossHealthView getCerberoHealthBar() {
        return cerberoHealthBar;
    }

    public CerberoBodyPartView getFirstHead() {
        return firstHead;
    }

    //Methods:
    /**
     * Set Cerbero's heads positions.
     * 
     * @param x
     *        Cerbero x position
     * @param y
     *        Cerbero y position
     */
    public void setPos(final double x, final double y) {
        this.firstHead.getGameObjectModel().setX(x);
        this.secondHead.getGameObjectModel().setX(x + 100);
        this.thirdHead.getGameObjectModel().setX(x + 100 * 2);
        this.firstHead.getGameObjectModel().setY(y);
        this.secondHead.getGameObjectModel().setY(y);
        this.thirdHead.getGameObjectModel().setY(y);
    }

    /**
     * Align Cerbero's heads.
     * 
     * @param x
     *        Cerbero x position
     * @param y
     *        Cerbero y position
     */
    public void alignThreeHeads(final double x, final double y) {
        this.firstHead.setTo(x, y);
        this.secondHead.setTo(x + CerberoModel.SECOND_HEAD_ALIGN, y);
        this.thirdHead.setTo(x + CerberoModel.THIRD_HEAD_ALIGN, y);
        this.body.setTo(x + CerberoModel.CERBERO_ALIGN, y);
    }

    public Pane getCerbero() {
        return this.cerberoLayer;
    }

    /**
     * Set Cerbero's calm position.
     * 
     */
    public void calmPose() {
        firstHead.getImageView().setImage(imgCerberoCalmPose);
        secondHead.getImageView().setImage(imgCerberoCalmPose);
        thirdHead.getImageView().setImage(imgCerberoCalmPose);
    }

    /**
     * Set Cerbero's byting animation.
     * 
     */
    public void bytingAnimation() {
        firstHead.getImageView().setImage(imgCerberoBiting);
        secondHead.getImageView().setImage(imgCerberoBiting);
        thirdHead.getImageView().setImage(imgCerberoBiting);
    }

    /**
     * Set Cerbero fire attack animation.
     * 
     */
    public void fireAttack() {
        firstHead.getImageView().setImage(imgCerberoCalmPose);
        secondHead.getImageView().setImage(imgCerberoFireAttack);
        thirdHead.getImageView().setImage(imgCerberoCalmPose);
    }

    /**
      * Cerbero fire rain animation.
      * 
      */
    public void fireRain() {
        firstHead.getImageView().setImage(imgCerberoCalmPose);
        secondHead.getImageView().setImage(imgCerberoCalmPose);
        thirdHead.getImageView().setImage(imgCerberoFireAttack);
    }

    /**
     * Cerbero explode animation.
     * 
     */
    public void explode() {
        firstHead.getImageView().setImage(imgExplosion);
        secondHead.getImageView().setImage(imgExplosion);
        thirdHead.getImageView().setImage(imgExplosion);
    }

    public Thread getFather() {
        return this.father;
    }

    /**
     * Run method for Cerbero's animation.
     * 
     */
    @Override
    public void run() {
        this.thread = new Thread() {
            public void run() {
                    int rand;
                        enterStageCerberoPath();
                        try {
                            Thread.sleep(1000 * 4);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        transition.stop();
                    while (cerberoModel.getFirstHead().isAlive() && !getFather().getState().toString().equals("TERMINATED")) {
                        for (int i = 0; i < 3; i++) {
                                rand = random.nextInt(3);
                                if (!cerberoModel.getFirstHead().isAlive()) {
                                    rand = -1;
                                }
                                if (rand == 0) {
                                    cerberoByte();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    transition.stop();
                                    ceberoByteBack();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    transition.stop();
                                }
                                if (rand == 1) {
                                    cerberoFireBall();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    transition.stop();
                                    fireBallEnd();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    transition.stop();
                                }
                                if (rand == 2) {
                                    cerberoFireRain();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    transition.stop();
                                    fireRainEnd();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    transition.stop();
                                }
                            }
                        //Cerbero Recover Time
                        if (cerberoModel.getFirstHead().isAlive() && !getFather().getState().toString().equals("TERMINATED")) {
                            calmPose();
                            try {
                                Thread.sleep(1000 * 3 + 1000 * 2);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    explode();
                }
        };
        thread.start();
    }
    /**
     * Cerbero entering transition.
     * 
     */
        public void enterStageCerberoPath() {
            transition.setNode(getCerbero());
            transition.setDuration(Duration.millis(1000 * 4));
            transition.setFromY(0);
            transition.setToX(-CerberoModel.CERBERO_ALIGN);
            transition.setToY(0);
            transition.play();
        }

        /**
         * Cerbero byte.
         * 
         */
        public void cerberoByte() {
            bytingAnimation();
            transition.setNode(getCerbero());
            transition.setDuration(Duration.millis(1000));
            //transition.setToX(-1800);
            transition.setFromY(0);
            transition.setFromX(-CerberoModel.CERBERO_ALIGN);
            transition.setToX(-CerberoModel.FIRST_HEAD_X_OFFSET - 100);
            transition.setToY(0);
            transition.play();
        }

        /**
         * Cerbero byte back.
         * 
         */
        public void ceberoByteBack() {
            transition.setNode(getCerbero());
            transition.setDuration(Duration.millis(1000));
            transition.setFromY(0);
            transition.setFromX(-CerberoModel.FIRST_HEAD_X_OFFSET - 100);
            transition.setToX(-CerberoModel.CERBERO_ALIGN);
            transition.setToY(0);
            transition.play();
        }

        /**
         * start fireBall.
         * 
         */
        public void cerberoFireBall() {
            fireAttack();
            transition.setNode(this.fireBallView.getLayer());
            transition.setDuration(Duration.millis(1000));
            //transition.setToX(-1600);
            transition.setFromY(CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT / 2);
            transition.setFromX(1000);
            transition.setToX(0);
            transition.setToY(CerberoModel.FIRERAIN_MANUAL_HITBOX_X_ADJUSTEMENT / 2);
            //canCreateObjectsInScene.addGameObject(objectsInScene, new FireBallController(new FireBallModel(cerberoModel.getCerbero(), 0, 0, 0), 2, 0, 0));
            transition.play();
        }

        /**
         * end fireBall.
         * 
         */
        public void fireBallEnd() {
            transition.setNode(this.fireBallView.getLayer());
            transition.setDuration(Duration.millis(1));
            transition.setFromY(0);
            transition.setFromX(0);
            transition.setToX(0);
            transition.play();
        }
        /**
         * start fireRain.
         * 
         */
        public void cerberoFireRain() {
            fireRain();
            transition.setNode(this.fireRainView.getFireRainLayer());
            transition.setDuration(Duration.millis(1000));
            transition.setFromY(0);
            transition.setToY(CerberoModel.FIRST_HEAD_X_OFFSET);
            transition.setFromX(0);
            transition.setToX(0);
            transition.play();
        }
        /**
         * End fireRain.
         * 
         */
        public void fireRainEnd() {
            transition.setNode(this.fireRainView.getFireRainLayer());
            transition.setDuration(Duration.millis(1));
            transition.setToY(0);
            transition.play();
        }
}
