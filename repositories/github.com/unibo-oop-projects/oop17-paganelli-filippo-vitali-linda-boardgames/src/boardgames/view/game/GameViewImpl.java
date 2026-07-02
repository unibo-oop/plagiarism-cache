package boardgames.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import boardgames.applauncher.*;
import javax.sound.midi.ControllerEventListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import boardgames.applauncher.ApplicationLauncher;
import boardgames.controller.GameControllerImpl;

import boardgames.controller.StopwatchImplThread;
import boardgames.model.board.Box;
import boardgames.model.board.ChessBoard;
import boardgames.model.piece.King;
import boardgames.model.piece.Piece;
import boardgames.model.piece.PieceType;
import boardgames.model.piece.Queen;
import boardgames.utility.BoardGame;
import boardgames.utility.Colour;
import boardgames.utility.Pair;
import boardgames.view.GameFrameFactory;
import boardgames.view.PopUpFactory;
import boardgames.view.board.BoardView;

//SPOSTO QUEI METODI DI MOSSA ECC IN BOARDVIEW ?

/**
 * This class creates a simple GUI to settle the BoardGames environment. Common
 * features are used for both a Chess and a Checkers match (either with or
 * without time).
 */
public class GameViewImpl implements GameView {
    private static final double PERC_RESIZE_CONSOLE_WIDTH = 0.07;
    private static final double PERC_RESIZE_CONSOLE_HEIGHT = 0.34;

    private static final double PERC_RESIZE_CHESSBOARD_SIDE = 0.6;

    private static final double CONSOLE_DIM = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    private static final int GRAVEYARD_DIM = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.13);
    private static final double CHESSBOARD_SIDE = Toolkit.getDefaultToolkit().getScreenSize().getHeight()
            * PERC_RESIZE_CHESSBOARD_SIDE;
    private static final double PERC_RESIZE_GRAVEYARD_ICON = 0.4;

    private static final String TITLE = "BoardsGames";

    private static final String EXIT_BUTTON = "Exit";
    private static final String GRAVEYARD_P1 = "White Graveyard";
    private static final String GRAVEYARD_P2 = "Black Graveyard";
    private static final String MATCH_TIME = "Match Time:";

    private static final String STARTGAME_STATEMENT1 = "Welcome to BoardGames! A new match begins, ready?"
            + " White player moves first";

    private static JTextArea stopwatchArea = new JTextArea("00:00:00");
    private StopwatchImplThread stopwatchThread = new StopwatchImplThread();

    private final JFrame myFrame = new JFrame(TITLE);
    private final JPanel graveyardBlack = new JPanel();
    private final JPanel graveyardWhite = new JPanel();

    private GameControllerImpl controller;
    private BoardView boardView;
    private final ActionListener al;

    /**
     * @param c 
     * @param bv 
     */
    public GameViewImpl(final GameControllerImpl c, final BoardView bv) {

        this.controller = c;
        this.boardView = bv;

        al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                setMovement((JButton) e.getSource());
            }
        };
    }

    @Override
    public final void drawGraphics() {

        this.boardView.initBoardGrid(al);

        graveyardWhite.setBorder(new TitledBorder(GRAVEYARD_P1));

        graveyardBlack.setBorder(new TitledBorder(GRAVEYARD_P2));

        //
        // final JPanel console = new JPanel(new GridBagLayout());
        //
        //
        // console.setBorder(new TitledBorder(STARTGAME_STATEMENT1));
        // console.setEditable(false);
        // console.setLineWrap(true);
        // console.setWrapStyleWord(true);

        // console.setText(" " + STARTGAME_STATEMENT1 + "\n");

        final JScrollPane consoleContainer = new JScrollPane(new JLabel("ciao"));
        consoleContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        consoleContainer.setBackground(Color.white);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.anchor = GridBagConstraints.WEST;
        // gbc_scrollPane.fill = GridBagConstraints.VERTICAL;
        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
        gbc_scrollPane.gridx = 2;
        gbc_scrollPane.gridy = 1;
        consoleContainer.add(new JLabel("ciao"));
        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
        gbc_scrollPane.gridx = 2;
        gbc_scrollPane.gridy = 3;
        consoleContainer.add(new JLabel("ciao"));

        final JButton exitButton = new JButton(EXIT_BUTTON);

        exitButton.addActionListener(jb -> System.exit(0));

        final JPanel stopwatchPanel = new JPanel(new GridLayout(2, 1));
        stopwatchArea.setOpaque(false); /* Set to make the background area invisible */
        stopwatchArea.setEditable(false);
        stopwatchPanel.add(new JLabel(MATCH_TIME));
        stopwatchPanel.add(stopwatchArea);

        final JPanel centralPanel = new JPanel();
        final GroupLayout centralPanelLayout = new GroupLayout(centralPanel);
        centralPanel.setLayout(centralPanelLayout);
        centralPanelLayout.setAutoCreateGaps(true); /* Gaps are auto-created between components */
        centralPanelLayout.setAutoCreateContainerGaps(true);

        centralPanelLayout.setHorizontalGroup(centralPanelLayout.createSequentialGroup()
                .addGroup(centralPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(graveyardWhite, GroupLayout.PREFERRED_SIZE, GRAVEYARD_DIM,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(stopwatchPanel, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(centralPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(this.boardView.getGridPanel(), 0, (int) CHESSBOARD_SIDE, Short.MAX_VALUE)
                        .addComponent(consoleContainer, GroupLayout.PREFERRED_SIZE,
                                (int) (CONSOLE_DIM * PERC_RESIZE_CONSOLE_HEIGHT), GroupLayout.PREFERRED_SIZE))
                .addGroup(centralPanelLayout
                        .createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(graveyardBlack,
                                GroupLayout.PREFERRED_SIZE, GRAVEYARD_DIM, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exitButton)));

        centralPanelLayout.setVerticalGroup(centralPanelLayout.createSequentialGroup()
                .addGroup(centralPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(graveyardWhite)
                        .addComponent(this.boardView.getGridPanel(), 0, (int) CHESSBOARD_SIDE, Short.MAX_VALUE)
                        .addComponent(graveyardBlack))
                .addGroup(centralPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(centralPanelLayout.createSequentialGroup()
                                .addGroup(centralPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(consoleContainer, GroupLayout.PREFERRED_SIZE,
                                                (int) (CONSOLE_DIM * PERC_RESIZE_CONSOLE_WIDTH),
                                                GroupLayout.PREFERRED_SIZE)))
                        .addComponent(stopwatchPanel, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exitButton)));

        /* The main frame for both games */
        this.myFrame.getContentPane().add(centralPanel);

        this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myFrame.pack();

        this.myFrame.setVisible(true);

        this.boardView.arrangeInitPiecesIcon();

        this.myFrame.setLocationRelativeTo(null); /* Sets the frame at the center of desktop */

        stopwatchThread.start();

    }

    /**
     * @param from
     * @param to
     * @param isEated
     */
    @Override
    public void drawMove(final Box from, final Box to, final boolean isEated) {
        System.out.println("drawMoves: " + " From: " + from + " To: " + to);

        for (final JButton arrivalButton : boardView.getButtons().keySet()) {
            if (boardView.getButtons().get(arrivalButton).equals(to)) {
                for (final JButton startButton : boardView.getButtons().keySet()) {
                    if (boardView.getButtons().get(startButton).equals(from)) {
                        arrivalButton.setIcon(startButton.getIcon());
                        startButton.setIcon(null);

                    }
                }

            }
        }

        this.boardView.repaintBoard();
    }

    /**
     * 
     */
    @Override
    public void setMovement(final JButton b) {
        // implementare try catch
        if (!boardView.getButtons().get(b).getPiece().equals(Optional.empty())
                && !controller.getPieceToMove().equals(Optional.empty())
                && controller.getPossibleMoves(controller.getPieceToMove())
                        .contains(boardView.getButtons().get(b).getPiece().get().getBox())) {

            System.out.println(controller.getPieceToMove() + " mangia " + boardView.getButtons().get(b).getPiece());

            controller.setPieceToEat(boardView.getButtons().get(b).getPiece());
            controller.movePieceTo(boardView.getButtons().get(b));

        } else if (!boardView.getButtons().get(b).getPiece().equals(Optional.empty())) {
            controller.setPieceToMove(boardView.getButtons().get(b).getPiece());
            this.lightUpPossibleMoves(b);

        } else if (!controller.getPieceToMove().equals(Optional.empty())) {
            controller.movePieceTo(boardView.getButtons().get(b));

        }
    }

    /**
     * @param jb
     */
    @Override
    public void lightUpPossibleMoves(final JButton jb) {
        final List<Box> moves = controller.getPossibleMoves(boardView.getButtons().get(jb).getPiece());
        this.boardView.repaintBoard();
        boardView.getButtons().keySet().stream()
                .filter(button -> moves.stream()
                        .anyMatch(box -> box.getPair().equals(boardView.getButtons().get(button).getPair())))
                .forEach(j -> {
                    j.setBackground(Color.cyan);
                    j.setBorderPainted(true);
                });
    }

    /**
     * This method updates the view of the Stopwatch, using the information taken
     * from the model. It has been made static to become thread-safe.
     * 
     * @param stopwatchTime 
     */
    public static void updateGUI(final String stopwatchTime) {
        stopwatchArea.setText(stopwatchTime);
    }

    // e separare game view in classe astratta in modo che chess e checkers
    // implementino diversamente
    // il popup promotion

    @Override
    public void sendPieceToGraveyard() {
        ImageIcon im = boardView.resizeIcon(controller.getEatedPiece().get().getColour().toString(),
                controller.getEatedPiece().get().getName(), PERC_RESIZE_GRAVEYARD_ICON);

        if (controller.getTurn().equals(Colour.Black)) {
            graveyardWhite.add(new JLabel(im));

        } else {
            graveyardBlack.add(new JLabel(im));

        }
    }

    // mettere i pop up in una classe comune e chiamarli ognuno il suo senza crearli
    // ogni volta ?
    // fare nella factory un metodo per il gridbaglayout anche se noon sarebbe
    // factory?

    @Override
    public void updateCheckStatus() {
        final GameFrameFactory factory = new PopUpFactory();
        final JFrame frame = factory.createFrame("King under check");
        final JPanel panel = factory.cratePanel("King " + "color " + " is under check");
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        panel.add(factory.createButton(e -> frame.dispose(), "Ok"));
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // popup re sotto scacco
        // deve fare per forza quella mossa
    }

    @Override
    public void updateCheckMateStatus() {
        final GameFrameFactory factory = new PopUpFactory();
        final JFrame frame = factory.createFrame("The match is over");
        final JPanel panel = factory.cratePanel("The winner is: ");

        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        panel.add(factory.createJLabel("PLAYER " + "COLOR "), cnst);
        cnst.gridy++;
        panel.add(factory.createButton(e -> System.exit(0), "Terminate"), cnst);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // popUp fine gioco
        // dico chi ha vinto e si chiude il gioco
    }

    @Override
    public void updatePromotionStatus() {
        final GameFrameFactory factory = new PopUpFactory();
        final JFrame frame = factory.createFrame("Select Piece");
        final JPanel panel = factory.cratePanel("Please select the piece to replace with");

        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.fill = GridBagConstraints.HORIZONTAL;
        cnst.gridy = 0;
        panel.add(factory.createButton(e -> this.replaceAction(frame, "Queen"), "Queen"), cnst);
        cnst.gridy++;
        panel.add(factory.createButton(e -> this.replaceAction(frame, "Rook"), "Rook"), cnst);
        cnst.gridy++;
        panel.add(factory.createButton(e -> this.replaceAction(frame, "Bishop"), "Bishop"), cnst);
        cnst.gridy++;
        panel.add(factory.createButton(e -> this.replaceAction(frame, "Knight"), "Knight"), cnst);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // popUp con scelta pezzi da mettere nella posizione del pezzo che ci è arrivato
    }

    private void replaceAction(final JFrame frame, final String name) {
        controller.doPiecePromotion(name);
        this.replacePromotedPieceIcon(controller.pieceMovedTo().getPiece(), name);
        frame.dispose();
    }

    private void replacePromotedPieceIcon(final Optional<Piece> piece, final String name) {
        boardView.getButtons().keySet().stream()
                .filter(jb -> boardView.getButtons().get(jb).equals(controller.pieceMovedTo())).findFirst().get()
                .setIcon(boardView.resizeIcon(piece.get().getColour().toString(), name, 0.8));

    }

    /**
     * This method updates the console below the chessboard grid, with different
     * statements, during the match.
     */
    public static void updateConsoleLog() {
    }

}
