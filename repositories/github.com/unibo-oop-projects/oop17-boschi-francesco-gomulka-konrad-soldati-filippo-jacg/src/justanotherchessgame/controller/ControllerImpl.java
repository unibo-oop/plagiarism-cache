package justanotherchessgame.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;
import justanotherchessgame.model.MovesChecker;
import justanotherchessgame.util.FileManagement;
import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.Point;
import justanotherchessgame.view.game.BoxView;
import justanotherchessgame.view.game.CheckMateView;
import justanotherchessgame.view.game.CheckMateViewImpl;
import justanotherchessgame.view.game.ChessboardViewImpl;
import justanotherchessgame.view.game.GameView;
import justanotherchessgame.view.game.GameViewImpl;
import justanotherchessgame.view.utils.PiecePromotionView;
import justanotherchessgame.model.Player;
import justanotherchessgame.model.LocalPlayer;

/**
 * This class is interposed between local player and game view, and it's able to manage all the interactions between user and the game.
 */
public final class ControllerImpl implements Controller {

    private final LocalPlayer whitePlayer;
    private final LocalPlayer blackPlayer;
    private final ChessboardViewImpl chessboard;
    private boolean saved;
    private BoxView lastClickedSpace;
    private final List<BoxView> reachableSpaces = new ArrayList<BoxView>();
    private static GameView view;

    /**
     * Class constructor.
     * @param white is the White player of the game.
     * @param black is the Black player of the game.
     */
    public ControllerImpl(final LocalPlayer white, final LocalPlayer black) {
        // Assigns board control to one of the players
        whitePlayer = white;
        blackPlayer = black;
        whitePlayer.setController(this);
        //Creates and sets the initial chessboard
        chessboard = new ChessboardViewImpl(true, this);
        chessboard.defineStartPositions();
        //Creates the proper gameView
        setView(new GameViewImpl(this, chessboard));
    }

    private static void setView(final GameView view) {
        ControllerImpl.view = view;
    }

    /**
     * Function used to set the last clicked space.
     * @param b: The new space.
     */
    private void setClickedSpace(final BoxView b) {
        lastClickedSpace = b;
    }

    /**
     * Function used after a chessboard box is clicked to keep track of the last space clicked and to add CSS.
     * @param s: The clicked cell.
     */
    private void clickedspace(final BoxView s) {
        // Remove style from old active space
        if (getClickedSpace() != null) {
            getClickedSpace().deselectBox();
        }
        setClickedSpace(s);
        // Add style to new active space
        if (getClickedSpace() != null) {
            getClickedSpace().selectBox();
        }
    }

    /**
     * Class getter to get the GameView.
     * @return the view of the game.
     */
    public static GameView getGame() {
        return view;
    }

    @Override
    public void logClicked(final int index) {
        ChessboardViewImpl cb = null;
        List<MoveInfoImpl> list = new ArrayList<MoveInfoImpl>();
        if (whitePlayer != null) {
            //since the move log is shown on the opposite direction, we need to start counting from the last
            list = whitePlayer.getList(whitePlayer.getMoveCount() - index);
        } else if (blackPlayer != null) {
            list = blackPlayer.getList(blackPlayer.getMoveCount() - index);
        }
        cb = new ChessboardViewImpl(true, list);
        view.createLogView(cb);
    }

    @Override
    public void onSpaceClicked(final int x, final int y) {
        final BoxView[][] boxes = chessboard.getTable();
        final BoxView newSpace = boxes[x][y];
        // if there is already a selected piece
        //TODO: calls to getters within the same class?
        if (getClickedSpace() != null && getClickedSpace().getPiece() != null) {
            final Piece p = lastClickedSpace.getPiece();
            //Click on "apparently legal" position
            if (reachableSpaces.contains(newSpace)) {
                final Point to = new Point(x, y);
                Class<? extends Piece> promotion = null;
                if (MovesChecker.willPromote(p, to)) {
                    promotion = askForPromotion(p);
                    //If nothing was chosen, we just ignore this request
                    if (promotion == null) {
                        return;
                    }
                }
                final Point from = new Point(lastClickedSpace.getX(), lastClickedSpace.getY());
                final MoveInfoImpl move = new MoveInfoImpl(from, to, promotion);
                final Player owner = p.isWhite() ? whitePlayer : blackPlayer;
                owner.requestMove(move);
            }
            //Move's been requested / not valid
            deselect();
        } else {
            //no piece was selected
            //if there's a piece on the selected square when no active square, you can move it!
            if (boxes[x][y].hasPiece()) {
                for (final BoxView res : reachableSpaces) {
                    res.notReachableBox();
                }
                reachableSpaces.clear();
                final Piece pi = boxes[x][y].getPiece();
                // ask the owner of the piece about the possible moves
                final Player owner = (pi.isWhite()) ? whitePlayer : blackPlayer;
                for (final MoveInfoImpl res : owner.possibleMoves(new Point(newSpace.getX(), newSpace.getY()))) {
                    final Point p = res.getTo();
                    boxes[p.getX()][p.getY()].reachableBox();
                    reachableSpaces.add(boxes[p.getX()][p.getY()]);
                }
                //make active square clicked square
                clickedspace(boxes[x][y]);
            }
        }
    }

    @Override
    public void deselect() {
        for (final BoxView b : reachableSpaces) {
            b.notReachableBox();
        }
        clickedspace(null);
    }

    @Override
    public Class<? extends Piece> askForPromotion(final Piece p) {
        return PiecePromotionView.showPromotionView(p.isWhite());
    }

    @Override
    public void saveGame(final File file) {
        if (whitePlayer != null) {
            FileManagement.saveOnFile(file, whitePlayer.getMoveHistory());
        } else if (blackPlayer != null) {
            FileManagement.saveOnFile(file, blackPlayer.getMoveHistory());
        }
    }

    @Override
    public void addTakenpiece(final Piece p) {
        view.addTakenpiece(p);
    }

    @Override
    public BoxView getClickedSpace() {
        return lastClickedSpace;
    }

    @Override
    public void notifyMove(final MoveInfoImpl m) {
    	Platform.runLater(()->applyMove(m));
    }
    
    private void applyMove(final MoveInfoImpl m) {
        this.saved = false;
        final Point from = m.getFrom();
        final Point to = m.getTo();
        if (chessboard.getTable()[to.getX()][to.getY()].hasPiece()) {
            view.addTakenpiece(chessboard.getTable()[to.getX()][to.getY()].getPiece());
        }
        final Piece p = chessboard.getTable()[from.getX()][from.getY()].getPiece();
        view.addLog(p, m);
        view.changeTimerState();
        chessboard.drawMove(m);
    }

    @Override
    public boolean isSaved() {
        return this.saved;
    }

    @Override
    public void setSaved() {
        this.saved = true;
    }

    @Override
    public void notifyGameEnd(final GameResult result) {
        final CheckMateView end = new CheckMateViewImpl(result);
        // We display a message and freeze the chessboard
        end.showCheckMateView();
        // Disable board
        deselect();
        view.stopTimers();
        chessboard.disabelAllSpaces();
    }
}
