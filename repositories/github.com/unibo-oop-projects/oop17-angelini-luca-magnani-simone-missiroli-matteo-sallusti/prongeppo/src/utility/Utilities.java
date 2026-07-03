package utility;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.GraphicManager;
import model.Ball;
import model.GenericPickUp;
import model.PickUp;
import view.GraphicElem;
import view.PongElement;


/**
 * @author Missi&Simone&Paolo
 * the class with the methods we need in this game.
 */
public final class Utilities {
    private Utilities() { }

    /**
     * @param frame **The frame in which change the panels**
     * @param prev **The panel to replace**
     * @param next **The replacing panel**
     */
    public static void changePanel(final JFrame frame, final JPanel prev, final JPanel next) {
        prev.setVisible(false);
        frame.remove(prev);
        frame.add(next);
    }

    /**
     * @param s **The string that has to appear on this button**
     * @param bg **The background Color of this button**
     * @param fg **The foreground Color of this button**
     * @param fontDim **The dimension of the string's font**
     * @param size **The preferred size of this button**
     * @return The button
     */
    public static JButton initButton(final String s, final Color bg, final Color fg, final int fontDim, final Dimension size) {
        final JButton newButton = new JButton(s);
        newButton.setBackground(bg);
        newButton.setFont(new Font(s, Font.BOLD, fontDim));
        newButton.setForeground(fg);
        if (size != null) {
            newButton.setPreferredSize(size);
        }
        newButton.setFocusable(false);
        return newButton;
    }
    /**
     * 
     * @param s **The string that has to appear on this JLabel**
     * @param algn **alignment of the text**
     * @param font **The dimension of the string's font**
     * @param bordercolor **The border Color of this JLabel**
     * @param fgColor **The foreground Color of this JLabel**
     * @return the JLabel
     */
    public static JLabel initLabel(final String s, final int algn, final int font, final Color bordercolor, final Color fgColor) {
        final JLabel toReturn = new JLabel(s);
        toReturn.setHorizontalAlignment(algn);
        toReturn.setFont(new Font(s, Font.BOLD, font));
        toReturn.setBorder(BorderFactory.createLineBorder(bordercolor));
        toReturn.setForeground(fgColor);
        return toReturn;
    }

    /**
     * @param s  **the String of the Image**
     * @param type **the GraphicType u want to get **
     * @return the PongElement request
     */
    public static PongElement createPongElement(final String s, final GraphicType type) {
        PongElement toBeCreate;
        if (type == GraphicType.BALL) {
            toBeCreate = new GraphicElem(s, GameValues.BALL_DIMENSION, GameValues.BALL_DIMENSION);
            return toBeCreate;
        } else if (type == GraphicType.BAR) {
            toBeCreate = new GraphicElem(s, GameValues.BARX, GameValues.BARY);
            return toBeCreate;
        } else if (type == GraphicType.PU) {
                toBeCreate = new GraphicElem(s, GameValues.PU_DIMENSION, GameValues.PU_DIMENSION);
                return toBeCreate;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param <T> **an object**
     * @param count **the index**
     * @param list **the list**
     * @param goingForward **true if needed next item in the list, false if needed the previous one**
     * @return the next index in the list or 0 if next is OutOfBound
     */
    public static <T> int updateItToList(final int count, final List<T> list, final boolean goingForward) {
        return goingForward ? (count + 1 >= list.size() ? 0 : count + 1) : (count - 1 < 0 ? list.size() - 1 : count - 1);
    }

    /**
     * @param <T> **type that u wanna write**
     * @param list **the List of all u need to write**
     * @param writer **the writer that will overwrite/write the file**
     */
    public static <T> void writeToFile(final List<T> list, final BufferedWriter writer) {
        list.forEach(i -> {
            try {
                writer.write(i.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * if file format is wrong it just printStackTrace.
     * @param path **the path of a created file**
     * @return the List of all keys code
     */
    public static List<Integer> readFromFile(final String path) {
        final List<Integer> keys = new LinkedList<>();
        try (BufferedReader keystxt = new BufferedReader(new FileReader(new File(path)))) {
            try {
                IntStream.range(0, 8).forEach(i -> {
                    try {
                        keys.add(Integer.parseInt(keystxt.readLine()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "     Keybinding.txt it's corrupted\n Please erase it and restart the game",
                                                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (IOException | NumberFormatException e4) {
            e4.printStackTrace();
            }
        return keys;
    }

    /**
     * @return a black new panel
     */
    public static JPanel fillerPanel() {
        final JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        return panel;
    }

    /**
     * @return a new Clip
     */
    public static Clip createClip() {
        try {
            return AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param gmanager **the ponelem creator**
     * @return an Optional of the pickUp
     */
    public static PickUp createPickUp(final GraphicManager gmanager) {
        PickUp newPickUp = null;
            if (Math.random() < 0.5) {
                newPickUp = new GenericPickUp(gmanager.createPongElement("PU_FANTASMA.png", GraphicType.PU)) {
                    @Override
                    public void trigger(final Ball ball) {
                        if (ball.getSpeed().x < 0) {
                            if (ball.getPosition().x > GameValues.WORLDWIDTH / 2) {
                                ball.setVisible(false);
                            }
                        } else {
                            if (ball.getPosition().x < GameValues.WORLDWIDTH / 2) {
                                ball.setVisible(false);
                            }
                        }
                    }
                };
            } else {
                newPickUp = new GenericPickUp(gmanager.createPongElement("PU_SPEEDUP.png", GraphicType.PU)) {

                    @Override
                    public void trigger(final Ball ball) {
                        ball.setVisible(true);
                        ball.setSpeed(new Point(ball.getSpeed().x > 0 ? ball.getSpeed().x + GameValues.PU_SPEEDINCREASE : ball.getSpeed().x - GameValues.PU_SPEEDINCREASE,
                                                ball.getSpeed().y > 0 ? ball.getSpeed().y + GameValues.PU_SPEEDINCREASE : ball.getSpeed().y - GameValues.PU_SPEEDINCREASE));
                    }
                };
        }
        newPickUp.setPosition(new Point(Utilities.distanceBound(GameValues.WORLDWIDTH), 
                                        Utilities.distanceBound(GameValues.WORLDHEIGHT)));
        return newPickUp;
    }

    private static int distanceBound(final int dim) {
        return (int) ((Math.random() * (1 - 2 * GameValues.PU_DISTANCEBOUND)
                                              + GameValues.PU_DISTANCEBOUND) * dim);
    }

}
