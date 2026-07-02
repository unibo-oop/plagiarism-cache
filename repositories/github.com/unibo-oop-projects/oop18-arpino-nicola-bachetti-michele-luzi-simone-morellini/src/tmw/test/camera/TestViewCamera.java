package tmw.test.camera;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;

import javafx.scene.Camera;

import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tmw.common.EntityFactory;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.camera.CameraControllerImpl;
import tmw.controller.hud.HudControllerImpl;
import tmw.controller.world.WorldController;
import tmw.controller.world.WorldControllerImpl;
import tmw.model.entities.MilkEntity;
import tmw.model.world.GameWorld;
import tmw.model.world.Level;
import utils.ImageUtils;

/**
 * This class is a simple view in which there is a camera. It's goal is to
 * check, by starting this view in the game, if the camera follows an object
 * moving.
 * 
 * @version 1.1
 */
public class TestViewCamera {

//    private final Stage mainStage;
//    private final Scene scene;
//    private final Canvas canvas;
//    private GraphicsContext gc;
//    private final Camera camera;
//    private final Dimension2D gameRes;
//    private final CameraControllerImpl cameraController;
//    private final StackPane stack;
//    private final GameWorld world;
//    private MilkEntity player;
//    private Image back = new Image("background/simple.jpg");
//
//    //HUD
//    private final Parent hud;
//
//    private int i = 0;
//
//    /**
//     * Public constructor
//     * 
//     * @param anyStage    stage of the javafx application
//     * @param gameViewRes game resolution
//     */
//    public TestViewCamera(final Stage anyStage, Dimension2D gameViewRes) {
//        this.gameRes = gameViewRes;
//        this.camera = new ParallelCamera();
//        this.stack = new StackPane();
//        this.scene = new Scene(stack, gameViewRes.getWidth(), gameRes.getHeight());
//        this.mainStage = anyStage;
//
//        this.player = GameFactory.getInstance().createMilk(new P2d(0, 0), new V2d(0, 0), this.gameRes);
//        this.world = new LevelOne(new Rectangle(back.getWidth(), back.getHeight()));
//        world.insertPlayer(player);
//
//        //canvas on which render the game
//        this.canvas = new Canvas(this.gameRes.getWidth(), this.gameRes.getHeight());
//        this.gc = canvas.getGraphicsContext2D();
//        this.gc.drawImage(back, 0, 0);
//
//        stack.prefWidthProperty().bind(scene.widthProperty());
//        stack.prefHeightProperty().bind(scene.heightProperty());
//
//        //HUD, it's an overlay panel which should display game info
//
//        this.hud = new HudControllerImpl().getHud();
//        this.cameraController = new CameraControllerImpl(camera, Optional.ofNullable(hud), gameViewRes);
//
//        //Stack panel, represents the whole scene made by multiple layers
//        this.stack.getChildren().addAll(canvas, hud);
//        this.scene.setCamera(camera);
//        mainStage.setScene(scene);
//        mainStage.show();
//
//        //call the test method
//        this.testFollowing();
//
//        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                Platform.exit();
//            }
//        });
//
//    }
//
//    /**
//     * This is the test method. It moves an object and according to his position,
//     * moves also camera.
//     * 
//     */
//    public void testFollowing() {
//
//        this.scene.setFill(Color.BLACK);
//
//        //PRESS "SPACE" button to check if camera works correctly.
//        //
//        //Notes:
//        //This test does not use the final player implementation because at this
//        //moment is not yet implemented. Player is seen as a simple image drown on screen.
//
//        scene.setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.SPACE) {
//
//                //clear
//                this.gc.clearRect(0, 0, gameRes.getWidth(), gameRes.getHeight());
//                this.gc.drawImage(back, 0, 0);
//
//                i++;
//                //move the player
//                player.setPos(new P2d(i, i));
//                this.gc.drawImage(utilities.ImageUtility.getZolletta(), player.getCurrentPos().getX(),
//                        player.getCurrentPos().getY());
//
//                //This method moves both camera and hud in order to follow player's position
//                this.cameraController
//                        .followEntiy(new P2d(player.getCurrentPos().getX(), player.getCurrentPos().getY()));
//
//                //Camera position should be the same of player
//                System.out.println("CAMERA POS" + this.cameraController.getCamPosition().toString());
//                System.out.println("PLAYER POS" + player.getCurrentPos().toString());
//
//            }
//
//            //PRESS "ENTER" button to check if camera zooms on player.
//
//            if (e.getCode() == KeyCode.ENTER) {
//
//                //A value which represents 150% of zoom
//                final double scaleFactor = 0.5;
//                //                    this.cameraController.zoomOnPlayer(scaleFactor);          
//
//                //needs to pass to camera right resolution like this
//                this.hud.setScaleX(scaleFactor);
//                this.hud.setScaleY(scaleFactor);
//                this.hud.setLayoutX(((1 - scaleFactor) * 0.5 * mainStage.getWidth()));
//                this.hud.setLayoutY(((1 - scaleFactor) * 0.5 * mainStage.getHeight()));
//
//                this.camera.setScaleX(scaleFactor);
//                this.camera.setScaleY(scaleFactor);
//                this.camera.setLayoutX(((1 - scaleFactor) * 0.5 * mainStage.getWidth()));
//                this.camera.setLayoutY(((1 - scaleFactor) * 0.5 * mainStage.getHeight()));
//            }
//        });
//    }
}
