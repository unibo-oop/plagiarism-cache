package view.scene;

import java.io.IOException;

import controller.Controller;
import view.stage.EndingGameStage;
import view.stage.EndingLevelStage;
import view.stage.GameWorldStage;
import view.stage.InstructionStage;
import view.stage.LoadingStage;
import view.stage.LoseStage;
import view.stage.MenuStage;
import view.stage.SettingStage;

/**
 * 
 * Implementation of the enumeration that regulates the scene switching.
 *
 */
public enum ViewScenes {

    /**
     * The menu stage.
     */
    MENU {
        private SceneChanger menu = new MenuStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            menu.setStage(width, height, controller);
        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return menu;
        }

    },

    /**
     * The settings stage.
     */
    SETTING {
        private SceneChanger setting = new SettingStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            setting.setStage(width, height, controller);

        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return setting;
        }

    },

    /**
     * The instruction stage.
     */
    ISTRUCTION {
        private SceneChanger istruction = new InstructionStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            istruction.setStage(width, height, controller);

        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return istruction;
        }

    },

    /**
     * The loading level stage.
     */
    LOADING {

        private SceneChanger loader = new LoadingStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            loader.setStage(width, height, controller);

        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return loader;
        }

    },

    /**
     * The game world stage.
     */
    GAME_WORLD {

        private SceneChanger world = new GameWorldStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            world.setStage(width, height, controller);
        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return world;

        }
    },

    /**
     * The game over stage.
     */
    LOSE {

        private SceneChanger gameOver = new LoseStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            gameOver.setStage(width, height, controller);

        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return gameOver;
        }

    },

    /**
     * The ending level stage.
     */
    END_LEVEL {

        private SceneChanger endLevel = new EndingLevelStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            endLevel.setStage(width, height, controller);

        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return endLevel;
        }

    },

    /**
     * The ending game stage.
     */
    END_GAME {

        private SceneChanger endGame = new EndingGameStage();

        @Override
        public void setGameStage(final double width, final double height, final Controller controller) throws IOException {
            endGame.setStage(width, height, controller);

        }

        @Override
        public SceneChanger getGameStage() throws IOException {
            return endGame;
        }

    };

    /**
     * This method allows to switch the stages.
     * @param width
     *          the scene width.
     * @param height
     *          the scene height.
     * @param controller
     *          the game {@link Controller}.
     * @throws IOException
     *          throw a new {@link IOException}.
     */
    public abstract void setGameStage(double width, double height, Controller controller) throws IOException;

    /**
     * Getter of the game stage.
     * 
     * @return the current game stage.
     * @throws IOException
     *          throw a new {@link IOException}.
     */
    public abstract SceneChanger getGameStage() throws IOException;

}
