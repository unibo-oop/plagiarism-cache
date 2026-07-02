package manager;

import java.util.List;
import java.util.Optional;

import factory.EnumFactory;
import level.Levels;
import login.Player;
import piece.Piece;
import view.View;

/**
 * Interface of the controller manager of the application.
 */
public interface ControllerManager {

    /**
     * @param switchController : enum that identify which controller will take over
     *                         the application.
     */
    void setController(EnumFactory switchController);

    /**
     * @param view : specific view to show at the user.
     */
    void setView(View view);

    /**
     * @return a Levels enum that is the start level of the game choosen by the
     *         user.
     */
    Levels getSpeedLevel();

    /**
     * @param speedLevel : a Level enum that identifies the start level that will be
     *                   set at the beginning of the game .
     */
    void setSpeed(Levels speedLevel);

    /**
     * @param loggedPlayer : if there is no a logged user, it will be set, otherwise
     *                     the current logged user will be logged out.
     */
    void setPlayer(Optional<Player> loggedPlayer);

    /**
     * @return the current logged user as a Player or an Optional.Empty if there is
     *         none.
     */
    Optional<Player> getPlayer();

    /**
     * @return Optional of the list of custom pieces that will be used during the game.
     */
    Optional<List<Piece>> getTempCustom();

    /**
     * Method that initializes the run time list of custom pieces.
     */
    void initRtCustomList();

    /**
     * @return the current list of run-time pieces made by the user.
     */
    Optional<List<Piece>> getRtCustom();

    /**
     * Method that update the list of custom pieces made by the user that will be used during the game.
     * 
     * @param gameStartList : the current list that will be updated.
     */
    void setTempCustom(Optional<List<Piece>> gameStartList);

}
