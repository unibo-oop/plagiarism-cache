package gamegraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import gamemenu.GameOverMenuImpl;
import gamemenu.GameMenu;
import gamemenu.PauseMenuImpl;
import pair.Pair;
import playcontroller.PlayController;

/**
 * View for when the Game runs.
 */
public class ViewGameImpl implements ViewGame {
    private final PlayController playController;
    private final GameMenu pauseMenu;
    private final GameMenu gameOverMenu;
    private GamePanel gamePanel;

    /**
     * @param playController : PlayController Object
     */
    public ViewGameImpl(final PlayController playController) {
        this.playController = playController;
        this.pauseMenu = new PauseMenuImpl(playController, this);
        this.gameOverMenu = new GameOverMenuImpl(playController, this);
    }

    @Override
    public final void pauseMenu(final int score) {
        this.pauseMenu.activate(score);
    }

    @Override
    public final void gameOverMenu(final int score) {
        this.gameOverMenu.activate(score);
    }

    @Override
    public final void setScore(final int score) {
        this.gamePanel.getScore().setText("<html>Score: <br>" + score + "</html>");
    }

    @Override
    public final void drawPiece(final Set<Pair<Integer, Integer>> piece, final Color color) {
        this.gamePanel.getTetrisPanel().getLogics().setPiece(piece);
        this.gamePanel.getTetrisPanel().getLogics().setColor(color);
        this.gamePanel.getTetrisPanel().repaint();
    }

    @Override
    public final void drawProjection(final Set<Pair<Integer, Integer>> piece, final Color color) {
        this.gamePanel.getTetrisPanel().getLogics().setProjection(piece);
        this.gamePanel.getTetrisPanel().getLogics().setProjectionColor(color);
        this.gamePanel.getTetrisPanel().repaint();
    }

    @Override
    public final void drawBoard(final Map<Pair<Integer, Integer>, Color> board) {
        this.gamePanel.getTetrisPanel().getLogics().setBoard(board);
        this.gamePanel.getTetrisPanel().repaint();
    }

    @Override
    public final void drawHoldbox(final Set<Pair<Integer, Integer>> piece, final Color color) {
        this.gamePanel.getHoldbox().getLogics().setPiece(piece);
        this.gamePanel.getHoldbox().getLogics().setColor(color);
        this.gamePanel.getHoldbox().repaint();
    }

    @Override
    public final void drawPreview(final Set<Pair<Integer, Integer>> piece, final Color color) {
        this.gamePanel.getPreview().getLogics().setPiece(piece);
        this.gamePanel.getPreview().getLogics().setColor(color);
        this.gamePanel.getPreview().repaint();
    }

    @Override
    public final JPanel getMainPanel() {
        return this.gamePanel;
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        this.gamePanel = new GamePanel(frameDimension, this.playController);
    }
}
