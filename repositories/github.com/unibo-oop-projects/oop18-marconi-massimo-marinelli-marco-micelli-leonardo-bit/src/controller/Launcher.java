package controller;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import controller.input.PlayerInputUtil;
import controller.physics.PhysicsUtil;
import controller.settings.GameSetter;
import model.entities.GameEntityFactory;
import view.level.generator.GameEngineUtil;

/**
 * 
 * Entry Point.
 *
 */
public class Launcher extends GameApplication {

        /**
         * Modifies the macro-game settings such as
         * Game Title, Game Version and window dimensions.
         * 
         * @param settings
         *                      An object storing game settings
         */
        @Override
        protected void initSettings(final GameSettings settings) {
            final GameSetter gameSetter = new GameSetter(settings);
            gameSetter.configureBasicSettings(settings);
        }

        /**
         * Modifies game variables.
         */
        @Override
        protected void initGameVars(final Map<String, Object> vars) {
            vars.put("PlayerHealth", 1000);

        }

        /**
         * Spawns entities in the game world.
         */
        @Override
        protected void initGame() {
            getGameWorld().addEntityFactory(new GameEntityFactory());
            GameEngineUtil.initLevels();
        }

        /**
         * Sets the User input options.
         * 
         */
        @Override
        protected void initInput() {
            PlayerInputUtil.setPlayerInput();
         }

        /**
         * Initialises the physic world.
         */
        @Override
        protected void initPhysics() {
            final PhysicsWorld world = getPhysicsWorld();
            world.setGravity(0, 0);

            PhysicsUtil.configureCollisions();
        }

        /**
         * Entry Point.
         * 
         * @param args
         *              Command line arguments
         */
        public static void main(final String[] args) {
                launch(args);
        }
}
