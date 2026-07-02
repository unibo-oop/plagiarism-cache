package thatlevelagain.view.sprite;


import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.panel.JPanelColor;
import thatlevelagain.view.state.various_state.LevelStateGeneral;
import thatlevelagain.view.various_jbutton.CloseOption;
import thatlevelagain.view.various_jbutton.DoorUnlock;
import thatlevelagain.view.various_jbutton.ExistentSave;
import thatlevelagain.view.various_jbutton.Exit;
import thatlevelagain.view.various_jbutton.Levels;
import thatlevelagain.view.various_jbutton.Light;
import thatlevelagain.view.various_jbutton.NewSave;

/**
 * 
 * represent entity image and position for option button.
 *
 */
public class Impostazioni extends SpriteImpl {


    private static final int LVL_5 = 5;
    private static final int LVL_7 = 7;
    private final int initialHeight;
    private final int initialWidth;
    private final int initialY;
    private final int initialX;

    /**
     * constructor.
     * @param x
     *         x position
     * @param y
     *         y position
     * @param width
     *         shape's width
     * @param height
     *         shape's height
     */
    public Impostazioni(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.IMPOSTAZIONI.getPosition()));
        this.initialHeight = height;
        this.initialWidth = width;
        this.initialX = x;
        this.initialY = y;
    }

    /**
     * 
     * @return
     *         initial Height.
     */
    public int getInitialHeight() {
        return this.initialHeight;
    }
    /**
     * 
     * @return
     *         initial Height.
     */
    public int getInitialWidth() {
        return this.initialWidth;
    }

    /**
     * 
     * @param pressed
     *        if true, this increase image's size.
     */
    public void bigImage(final boolean pressed) {
        if (pressed) {
            this.setHeight(this.initialHeight + GamePanel.BLOCK_HEIGHT / 2);
            this.setWidth(this.initialWidth + GamePanel.BLOCK_WIDTH / 2); 
            this.setY(this.initialY - GamePanel.BLOCK_HEIGHT / 4);
            this.setX(this.initialX - GamePanel.BLOCK_HEIGHT / 4);
        } else {
            this.setHeight(this.initialHeight);
            this.setWidth(this.initialWidth);
            this.setY(this.initialY);
            this.setX(this.initialX);
        }
    }

    /**
     * create an OptionPane with Menu Option.
     * @param gamePanel
     *          panel where put JOptionPane
     * @param level
     *          actual level
     */
    public static void menu(final GamePanel gamePanel, final LevelStateGeneral level) {
        final JDialog dialog = new JDialog();
        final JPanelColor panel1 = new JPanelColor(new FlowLayout());
        final JPanelColor panel2 = new JPanelColor(new FlowLayout());
        final JPanelColor panel3 = new JPanelColor(new FlowLayout());
        final JPanelColor panel4 = new JPanelColor(new FlowLayout());
        final JPanelColor panel5 = new JPanelColor(new FlowLayout());
        final JPanelColor panel6 = new JPanelColor(new FlowLayout());
        final Exit exit = new Exit();
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        panel1.add(new NewSave(gamePanel, level));
        dialog.add(panel1);
        panel2.add(new ExistentSave(gamePanel, level));
        dialog.add(panel2);
        panel4.add(new Levels(level, dialog));
        if (level.getMap().isSkipLevel()) {
            dialog.add(panel4);
        }
        panel3.add(new DoorUnlock(level, dialog));
        if (level.getMap().isDoorLevel()) {
            dialog.add(panel3);
        }
        panel5.add(new CloseOption(dialog, level));
        dialog.add(panel5);
        panel6.add(exit);
        if (level.getMap().isLights()) {
            final JPanelColor panel7 = new JPanelColor(new FlowLayout());
            panel7.add(new Light(level, dialog));
            dialog.add(panel7);
        }
        exit.addActionListener(e -> {
            if (level.getMap().getLevel() == LVL_5) {
                level.getMap().getGomitolo().setActive(false);
            }
            if (level.getMap().getLevel() == LVL_7) {
                level.getMap().getDog().setStop(false);
            }
            System.exit(0);
            dialog.dispose();
        });
        dialog.add(panel6);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
