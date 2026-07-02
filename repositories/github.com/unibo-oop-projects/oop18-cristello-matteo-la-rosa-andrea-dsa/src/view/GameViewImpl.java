package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controller.DSAController;
import view.board.BoardDrawer;
import view.board.ControlsPanel;
import view.board.CoordinatesForBoard;
import view.board.Coordinates;
import view.board.CoordinatesCircularImpl;
import view.board.CoordinatesZigZagImpl;
import view.board.CurrentSituationPanel;
import view.board.DicesPanel;
import view.board.AirPanel;
import view.board.TemplateEnum;
import view.menus.MainMenu;

/**
 * Implementation of GameViewImpl.
 */
public class GameViewImpl implements GameView {

    private final BoardDrawer boardDrawer = new BoardDrawer();
    private final Dimension tileDimension = DimensionUtils.resizeForScreenDimension(new Dimension(50, 50));
    private final Dimension playerDimension = DimensionUtils.resizeForScreenDimension(new Dimension(30, 30));
    private final Dimension boatDimension = DimensionUtils.resizeForScreenDimension(new Dimension(100, 100));
    private final Dimension dicesDimension = DimensionUtils.resizeForScreenDimension(new Dimension(100, 100));
    private final Dimension playerLabelDimension = DimensionUtils.resizeForScreenDimension(new Dimension(200, 50));
    private final Dimension dicepanelDimension = DimensionUtils.resizeForScreenDimension(new Dimension(200, 100));
    private final Dimension tileLinePanelDimension = DimensionUtils.resizeForScreenDimension(new Dimension(950, 350));
    private final Dimension controlButtonPanelDimension = DimensionUtils.resizeForScreenDimension(new Dimension(950, 100));
    private final Dimension playerMessageDimension = DimensionUtils.resizeForScreenDimension(new Dimension(600, 50));
    private final JPanel tmpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 37));
    private final CurrentSituationPanel currSituationPanel;
    private final JFrame playFrame = new JFrame("Deep Sea Adventure Boardgame");
    private final DSAController dsaController;
    private final CoordinatesForBoard coordForBoard;
    private final Coordinates coordTemplate;
    private final JPanel tileLinePanel = new JPanel();
    private final DicesPanel dicePanel;
    private final JPanel rightContainer = new JPanel(new BorderLayout(0, 0));
    private final JPanel leftContainer = new JPanel(new BorderLayout());
    private final JPanel rightBottomCornerContainer = new JPanel(new BorderLayout());
    private final AirPanel oxygenPanel = new AirPanel(25);
    private final JPanel rightUpPanel = new JPanel(new BorderLayout());
    private final JLabel playerInTurnLabel = new JLabel();
    private final JLabel playerMessage = new JLabel();
    private final ControlsPanel controlButtons;
    private final JPanel leftBottomPanel = new JPanel(new BorderLayout());
    private final JPanel leftBottomPanelPageStart = new JPanel(new FlowLayout());
    private final JButton returnToMainButton = new JButton("Return To main");
    private final JLabel gameTurnMessageLabel = new JLabel(" ");
    private final Font fontForTurnCounter = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    private final Font fontForPlayerMessage = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    private final Font fontForPlayerInTurn = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    /**
     * @param dsaController
     *                          The controller.
     * @param templateEnum
     *                          The kind of template.
     * @throws IOException
     */
    public GameViewImpl(final DSAController dsaController, final TemplateEnum templateEnum) {
        this.dsaController = dsaController;
        this.controlButtons = new ControlsPanel(this.dsaController);
        this.dsaController.setView(this);
        this.dicePanel = new DicesPanel(dicesDimension);
        if (templateEnum.equals(TemplateEnum.CIRCULAR)) {
            this.coordTemplate = new CoordinatesCircularImpl(
                    this.dsaController.getTileline().getTilesLine().size());
        } else if (templateEnum.equals(TemplateEnum.ZIGZAG)) {
            this.coordTemplate = new CoordinatesZigZagImpl(
                    this.dsaController.getTileline().getTilesLine().size());
        } else {
            this.coordTemplate = new CoordinatesZigZagImpl(
                    this.dsaController.getTileline().getTilesLine().size());
        }

        this.coordForBoard = new CoordinatesForBoard(this.coordTemplate);
        this.currSituationPanel = new CurrentSituationPanel(this.dsaController.getPlayerStatistic());
        this.tileLinePanel.setLayout(null);
        this.tileLinePanel.setPreferredSize(tileLinePanelDimension);
        this.boardDrawer.createPlayerDrawList(dsaController.getTileline(), coordForBoard, playerDimension)
                .forEach(x -> tileLinePanel.add(x));
        this.boardDrawer.createTilesDrawList(dsaController.getTileline(), coordForBoard, tileDimension)
                .forEach(x -> tileLinePanel.add(x));
        this.tileLinePanel.add(boardDrawer.drawBoat(coordForBoard, boatDimension));
        this.tileLinePanel.setBackground(Color.decode(BoardColorPalette.METALLIC_SEAWEED.getHexRGB()));
        this.controlButtons.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        this.controlButtons.setPreferredSize(controlButtonPanelDimension);
        this.dsaController.setView(this);
        this.returnToMainButton.setBackground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.returnToMainButton.setForeground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));

        /* LINE START */
        this.leftContainer.add(this.tileLinePanel, BorderLayout.LINE_START);
        this.leftBottomPanel.add(this.controlButtons, BorderLayout.LINE_END);
        this.leftContainer.add(this.leftBottomPanel, BorderLayout.PAGE_END);
        this.playFrame.getContentPane().add(this.leftContainer, BorderLayout.LINE_START);

        /* PAGE START in LINE START container */
        this.leftBottomPanelPageStart.setBackground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.playerInTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.playerInTurnLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.playerInTurnLabel.setPreferredSize(playerLabelDimension);
        this.playerInTurnLabel.setFont(this.fontForPlayerInTurn);
        this.playerMessage.setFont(this.fontForPlayerMessage);
        this.leftBottomPanel.add(this.leftBottomPanelPageStart, BorderLayout.PAGE_START);
        this.leftBottomPanelPageStart.add(playerInTurnLabel);
        this.leftBottomPanelPageStart.add(playerMessage);

        /* LINE END */
        this.gameTurnMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        this.gameTurnMessageLabel.setFont(this.fontForTurnCounter);
        this.gameTurnMessageLabel.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.rightUpPanel.add(this.gameTurnMessageLabel, BorderLayout.CENTER);
        this.rightUpPanel.setBackground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
        this.rightContainer.add(this.rightBottomCornerContainer, BorderLayout.PAGE_END);
        this.rightContainer.add(this.currSituationPanel, BorderLayout.CENTER);
        this.rightContainer.add(this.rightUpPanel, BorderLayout.PAGE_START);
        this.rightContainer.setBackground(Color.decode(BoardColorPalette.METALLIC_SEAWEED.getHexRGB()));
        this.playFrame.getContentPane().add(this.rightContainer, BorderLayout.CENTER);

        /* PAGE END in LINE END Container (Right Bottom Container) */

        this.oxygenPanel.setBackground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
        this.dicePanel.setBackground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.dicePanel.setPreferredSize(dicepanelDimension);
        this.rightUpPanel.add(this.gameTurnMessageLabel, BorderLayout.CENTER);
        this.rightBottomCornerContainer.add(this.dicePanel, BorderLayout.WEST);
        this.rightBottomCornerContainer.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        this.rightBottomCornerContainer.add(oxygenPanel, BorderLayout.CENTER);

        /* Main Boardgame Frame */
        this.playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.playFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.playFrame.setVisible(true);

        final ActionListener actionListenerReturnToMain = e -> {
            this.playFrame.setVisible(false);
            new MainMenu();
        };

        this.returnToMainButton.addActionListener(actionListenerReturnToMain);

    }

    @Override
    public final void refresh() {
        this.playFrame.repaint();

    }

    @Override
    public final void updateMovementButtons() {
        if (this.dsaController.getMovementPossibleAction().isEmpty()) {
            this.updateActionButtons();
        }
        this.controlButtons.setOnSpecific(this.dsaController.getMovementPossibleAction());

    }

    @Override
    public final void updateDices(final Integer value) {
        this.dicePanel.updateDicesPanel(value);
        this.refresh();

    }

    @Override
    public final void updateOxygen() {
        this.oxygenPanel.updateOxygenPanel(this.dsaController.getRemainingOxigen());
        this.refresh();

    }

    @Override
    public final void updatePlayersSituation() {

        this.currSituationPanel.updateCurrentSituation(this.dsaController.getPlayerStatistic());
        this.refresh();

    }

    @Override
    public final void updateGameBoard() {
        this.tileLinePanel.removeAll();
        this.boardDrawer
                .createPlayerDrawList(this.dsaController.getTileline(), this.coordForBoard, this.playerDimension)
                .forEach(x -> tileLinePanel.add(x));
        this.boardDrawer
                .createTilesDrawList(this.dsaController.getTileline(), this.coordForBoard, this.tileDimension)
                .forEach(x -> tileLinePanel.add(x));
        this.tileLinePanel.add(boardDrawer.drawBoat(coordForBoard, boatDimension));
        this.refresh();

    }

    @Override
    public final void updatePlayerInTurn() {
        this.playerInTurnLabel
                .setText("It's " + this.dsaController.getPlayerInTurn().getPlayerName() + " turn.");
    }

    @Override
    public final void updateActionButtons() {
        this.controlButtons.setOnSpecific(this.dsaController.getPoassibleActionAfterMove());

    }

    @Override
    public final void updateDisableAllButtons() {
        this.controlButtons.setOffAll();
    }

    @Override
    public final void updateMessageForPlayer(final String message) {
        this.playerMessage.setText(message);
    }

    @Override
    public final void updateEndGame() {
        this.updatePlayersSituation();
        this.leftBottomPanel.removeAll();
        this.leftBottomPanel.setLayout(new BorderLayout());
        this.leftBottomPanelPageStart.removeAll();
        this.leftBottomPanelPageStart.setLayout(new FlowLayout());
        this.leftBottomPanel.add(this.leftBottomPanelPageStart, BorderLayout.PAGE_START);
        tmpPanel.add(this.returnToMainButton);
        tmpPanel.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        this.playerMessage.setPreferredSize(playerMessageDimension);
        this.leftBottomPanelPageStart.add(playerMessage);
        this.leftBottomPanel.add(tmpPanel, BorderLayout.PAGE_END);
    }

    @Override
    public final void updateMessageGameTurn(final String message) {
        this.gameTurnMessageLabel.setText("It's turn nr." + this.dsaController.getGameTurnIndex().toString() + " ");
    }

}
