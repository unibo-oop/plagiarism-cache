package giocoscudetto.model.impl.match;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.impl.boxes.BackToStartBox;
import giocoscudetto.model.impl.boxes.CesariniBox;
import giocoscudetto.model.impl.boxes.CornerBox;
import giocoscudetto.model.impl.boxes.EmptyBox;
import giocoscudetto.model.impl.boxes.FinishBox;
import giocoscudetto.model.impl.boxes.FirstHalfBox;
import giocoscudetto.model.impl.boxes.FreeKickBox;
import giocoscudetto.model.impl.boxes.GoalConceidedBox;
import giocoscudetto.model.impl.boxes.GoalRemovedBox;
import giocoscudetto.model.impl.boxes.JoinBox;
import giocoscudetto.model.impl.boxes.PenaltyBox;
import giocoscudetto.model.impl.boxes.ResultBox;
import giocoscudetto.model.impl.boxes.SkipTurnBox;
import giocoscudetto.model.impl.boxes.StartBox;
import giocoscudetto.model.impl.boxes.SuspendMatchBox;

//CHECKSTYLE: MagicNumber OFF
/**
 * This class is a factory for the boxes of the board, 
 * it is used to create the boxes for each position of the board.
 */
public final class BoxFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     */
    private BoxFactory() { }

    /**
     * This method creates a box for the given position of the board.
     * 
     * @param position the position of the box to create.
     * @return the box.
     */
    public static Box createBox(final int position) {
        return switch (position) {
            case 0 -> new StartBox(position);
            case 1, 3, 5, 7, 9, 11, 13, 18, 20, 22, 25, 27 -> new EmptyBox(position);
            case 4, 12 -> new FreeKickBox(position);
            case 2, 8, 14, 23, 29 -> new ResultBox(position);
            case 6 -> new BackToStartBox(position);
            case 10, 28 -> new CornerBox(position);
            case 15 -> new SuspendMatchBox(position);
            case 16 -> new FirstHalfBox(position);
            case 17 -> new JoinBox(position);
            case 19, 26 -> new PenaltyBox(position);
            case 21 -> new SkipTurnBox(position);
            case 24 -> new GoalConceidedBox(position);
            case 30 -> new GoalRemovedBox(position);
            case 31 -> new CesariniBox(position);
            case 32 -> new FinishBox(position);
            default -> throw new IllegalArgumentException("Invalid box position: " + position);
        };
    }
}
