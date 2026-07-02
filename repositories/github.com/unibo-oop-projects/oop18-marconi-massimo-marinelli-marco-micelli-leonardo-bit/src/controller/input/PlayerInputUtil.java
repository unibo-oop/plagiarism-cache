package controller.input;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import model.entities.GameEntityTypes;
import model.entities.components.PlayerController;
import model.entities.components.PlayerBehaviours;
/**
 * This class is suited for modifying, enabling and disabling the in-game player input actions.
 * N.B. this specific class only cares about playing character's actions.
 */
public final class PlayerInputUtil {

    private PlayerInputUtil() { }

    /**
     * Sets all the UserAction.
     */
    public static void setPlayerInput() {

        FXGL.getInput().addAction(new UserAction("MoveRight") {
            @Override
            protected void onAction() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).moveRight();
            }
        }, KeyCode.RIGHT);

        FXGL.getInput().addAction(new UserAction("MoveLeft") {
            @Override
            protected void onAction() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).moveLeft();
            }
        }, KeyCode.LEFT);

        FXGL.getInput().addAction(new UserAction("MoveUp") {
            @Override
            protected void onAction() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).moveUp();
            }
        }, KeyCode.UP);

        FXGL.getInput().addAction(new UserAction("MoveDown") {
            @Override
            protected void onAction() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).moveDown();
            }
        }, KeyCode.DOWN);

        FXGL.getInput().addAction(new UserAction("attack") {

            @Override 
            protected void onActionBegin() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).setBehaviour(PlayerBehaviours.ATTACKING); 
            }

            @Override
            protected void onAction() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).attack();
            }

            @Override 
            protected void onActionEnd() {
                FXGL.getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0)
                .getComponent(PlayerController.class).setBehaviour(PlayerBehaviours.PACIFIC); 
            }
        }, KeyCode.A);
    }

}
