package zengine.interfaces;

/**
 * This interface inherits all the methods of each component of the engine and
 * so it describes the functionality offered by the game engine.
 */
public interface GameEngine extends GameFunctionsGUI, GameFunctionsInput, GameFunctionsLogger, GameFunctionsSystem,
        GameFunctionsUtilities, GameFunctionsAudio, GameFunctionsAssets {
}
