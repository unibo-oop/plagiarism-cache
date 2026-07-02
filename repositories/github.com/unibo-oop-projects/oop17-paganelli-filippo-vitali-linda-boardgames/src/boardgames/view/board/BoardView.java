package boardgames.view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boardgames.controller.GameControllerImpl;
import boardgames.model.board.Box;
import boardgames.model.piece.Piece;

public abstract class BoardView {


    private static final Integer RGB1 = 11435303;
    private static final Integer RGB2 = 13483421;
    private static final int NUM_BUTTONS = 8;
    private static final double PERC_RESIZE_ICON = 0.8;
    private final JPanel gridPanel;
    private final Map<JButton, Box> buttons;
    final GameControllerImpl controller;

    /* GUARDARE ABRSTRACT METHOD E TEMPLATE METHOD */
    
    /*per ora passo un action listener al metodo initBoardGrid, ma valutare di portare i movimenti della
     * scacchiera in questa classe invece che in gameView facendo i modo che si occupi di grafica 
     * e dei vari popup tanto alla fine quelle cose sono riferite alla scacchire (i movimenti il
     * mangiare ecc. e anche lo scacco matto (credo)).
     * portando di qua le cose non c'è bisogno di passare l'action listener di qua ma posso implementarlo
     * semplicemente come è ora: jb.addActionListener(e -> this.setMovement((JButton) e.getSource()));*/

    public BoardView(GameControllerImpl controller) {
        this.gridPanel = new JPanel(new GridLayout(NUM_BUTTONS, NUM_BUTTONS));
        this.buttons = new HashMap<>();
        this.controller = controller;
    }

    public Map<JButton, Box> getButtons() {
        return buttons;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    /**
     * This methods sets the JButtons in the grid (with their colors) and the axis,
     * used to define the coordinates for each button.
     * 
     * @param myPanel 
     * @param numButtons 
     * @param al 
     */
    public void initBoardGrid(final ActionListener al) { 
        IntStream.range(0, NUM_BUTTONS).boxed().sorted(Comparator.reverseOrder())
        .forEach(i -> IntStream.range(0, NUM_BUTTONS).forEach(j -> {
            final JButton jb = new JButton(/*j + "," + i*/);
            this.setBoxesBackground(j, i, jb);
            jb.addActionListener(al);
            this.buttons.put(jb, this.controller.getGameBoard().getBox(j, i));
            jb.setBorderPainted(false);
            this.gridPanel.add(jb);
        }));
    }
    /**
     * Method that repaint the board's color after every move.
     */
    public void repaintBoard() {
        this.buttons.keySet().stream().filter(j -> j.getBackground().equals(Color.cyan))
                .peek(j -> j.setBorderPainted(false))
                .forEach(jb -> this.setBoxesBackground(buttons.get(jb).getX(), buttons.get(jb).getY(), jb));

    }

    /**
     * Place the images corresponding to the pieces on the board.
     */
    public void arrangeInitPiecesIcon() {

       controller.getGamePieces().forEach(gamePiece -> this.buttons.keySet().stream()
               .filter(bj -> this.buttons.get(bj).getPair().equals(gamePiece.getBox().getPair()))
               .forEach(bj -> {
                   this.buttons.get(bj).setPiece(Optional.of(gamePiece));

                   bj.setIcon(this.resizeIcon(gamePiece.getColour().toString(), gamePiece.getName(), PERC_RESIZE_ICON));

               }));
    }

    /**
     * Set the colors of the board that are inverse for checkers and chess.
     * 
     * @param i  first coordinate
     * @param j  second coordinate
     * @param jb button to color
     */
    private void setBoxesBackground(final int i, final int j, final JButton jb) {
        if ((j % 2 == 0 && i % 2 == 0) || (j % 2 != 0 && i % 2 != 0)) {
            jb.setBackground(this.getFirstBoxColor());
        } else {
            jb.setBackground(this.getSecondBoxColor());
        }
    }

    public ImageIcon resizeIcon(String color, String name, double perc) {
        ImageIcon ia = new ImageIcon(BoardView.class.getResource("/images/" + color + name + this.nameIconExtension()));
        JButton bj = buttons.keySet().stream().findFirst().get();
        Image resizableImage = ia.getImage();
        ImageIcon im = new ImageIcon(resizableImage.getScaledInstance((int) (bj.getSize().getWidth() * perc),
                (int) (bj.getSize().getHeight() * perc), Image.SCALE_SMOOTH));
        return im;

    }

    /**
     * @return the string piece of the icon name that specifies the name and its extension
     */
    abstract String nameIconExtension();


    /**
     * @return the first color that will be put as a background on the button,
     * since the checkersboard and chessboard colors are reversed
     */
    abstract Color getFirstBoxColor();


    /**
     * @return the second color that will be put as a background on the button,
     * since the checkerboard and chessboard colors are reversed
     */
    abstract Color getSecondBoxColor();


    /**
     * @return darker color of the board's box
     */
    public static Integer getRgb1() {
        return RGB1;
    }

    /**
     * @return lighter color of the board's box
     */
    public static Integer getRgb2() {
        return RGB2;
    }

}
