package thatlevelagain.view.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import thatlevelagain.ScreenSize;
import thatlevelagain.input.KeyDetect;
import thatlevelagain.input.MouseDetect;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 *    JPanel for GameFrame.
 */
public class GamePanel extends JPanel implements Runnable {

/**
 * 
 */
    private static final long serialVersionUID = 1L;

    /**
     * constant relative block's WIDTH.
     */
    public static final int BLOCK_WIDTH = (int) ScreenSize.getScala().getWidth();
    /**
     * constant relative block's HEIGHT.
     */
    public static final int BLOCK_HEIGHT = (int) ScreenSize.getScala().getHeight();
/**
 * constant relative panel's WIDTH.
 */
       public static final int WIDTH = (int) (ScreenSize.NUMBER_BLOCK_WIDTH * BLOCK_WIDTH);
/**
 * constant relative panel's HEIGHT.
 */
       public static final int HEIGHT = (int) (ScreenSize.NUMBER_BLOCK_HEIGHT * BLOCK_HEIGHT); 

       //game state manager
       private GameStateManagerImpl manager;

       //image
       private final BufferedImage image;
       private final Graphics2D g;

       private boolean playing;
       private boolean attivo;
       private boolean stopped;
       private static final int FPS = 60;

       private long lastTimer1;

       private static final int NS = 1000000000;

       private static final long MILLE = 1000;

       private static final long WAIT = 100;
       private final double amountOfTicks = FPS;
       private final double nsPerFrame = NS / amountOfTicks;
       private double unprocessed;
       @SuppressWarnings("unused")
       private int ticks;
       @SuppressWarnings("unused")
       private int frames;
       ////////////////////////////////

       //frame
       private final JFrame frame;
/**
 * constructor. 
 * @param frame
 *         set the frame 
 */
       public GamePanel(final JFrame frame) {
           super();
           final Thread th;
           final MouseDetect mouse;
           final KeyDetect key;
           this.unprocessed = 0;
           this.ticks = 0;
           this.frames = 0;
           this.playing = true;
           this.stopped = false;
           this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
           this.setFocusable(true);
           this.requestFocus();
           this.frame = frame;
           image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
           g = (Graphics2D) image.getGraphics();
           th = new Thread(this, "Gameloop");
           key = new KeyDetect(this);
           mouse = new MouseDetect(this);
           addKeyListener(key);
           addMouseListener(mouse);
           th.start();
       }

       /**
        * Init values.
        * g is a Graphics2D, which can be used to draw into this image
        */
       private void init() {
        manager = new GameStateManagerImpl(this);
   }

       /**
        * Updates all changes that will draw.
        */
       private void update() {
            manager.update();
       }

       /**
        * Draw all updates in an image.
        * Fill the g rectangle of image.
        * After this, it draw all manager changes in this Graphics 2D
        */
       private void draw() {
          g.fillRect(0, 0, WIDTH, HEIGHT);
          this.manager.draw(g);
       }

       /**
        * Draw the image in the screen.
        * Create a g2 relative this panel graphics. After this, it draw the changes in it and dispose them.
        */
       private void drawToScreen() {
            final Graphics g2 = getGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();
       }

       /**
        * This function was taken from an internet user that advise to use this function for draw images in a swing application.
        * This method represent the game loop that update and draw the images.
        * Until all events or methods are correctly processed, it will do another update.
        * If changes make possible to draw anything, every image will draw.
        * When attivo became false, like also playing, thread will stop his loop and stopped became true. When this became true, game will close. 
        * 
        */
       @Override
       public final void run() {
           init();
           this.attivo = true;
           while (attivo) {
               long lastTime = System.nanoTime();
               while (playing) {
                   final long now = System.nanoTime();
                   unprocessed += (now - lastTime) / nsPerFrame;
                   lastTime = now;
                   boolean shouldRender = true;
                   while (unprocessed >= 1) {
                       this.update();
                       unprocessed -= 1;
                       shouldRender = true;
                   }
                   if (shouldRender) {
                       frames++;
                       this.draw();
                       this.drawToScreen();
                   }
                   if (System.currentTimeMillis() - lastTimer1 > MILLE) {
                       lastTimer1 += MILLE;
                       frames = 0;
                       ticks = 0;
                   }
               }
           }
           this.stopped = true;

           try {
               Thread.sleep(WAIT);
           } catch (InterruptedException e) {
                e.printStackTrace();
           }
       }

    /**
     * 
     * @return
     *   manager
     */
    public GameStateManagerImpl getManager() {
         return this.manager;
    }
    /**
     * 
     * @return
     *         frame
     */
    public JFrame getGameFrame() {
         return this.frame;
    }

    /**
     * 
     * @return
     *         if thread is running
     */
    public boolean isAttivo() {
        return attivo;
    }

    /**
     * 
     * @param attivo
     *         set boolean condition for running thread
     */
    public void setAttivo(final boolean attivo) {
        this.attivo = attivo;
    }

    /**
     * 
     * @return
     *         playing value
     */
    public boolean isPlaying() {
        return this.playing;
    }

    /**
    /**
     * 
     * @param playing
     *         set boolean condition for running thread
     */
    public void setPlaying(final boolean playing) {
        this.playing = playing;
    }
    /**
     * return if the thread is stopped.
     * @return
     *         stopped value
     */
    public boolean isStopped() {
        return this.stopped;
    }
    /**
     * 
     * @param stopped
     *         set boolean condition for stopped thread
     */
    public void setStopped(final boolean stopped) {
        this.stopped = stopped;
    }
    /**
     * @return
     *         value unprocessed
     */
    public double getUnprocessed() {
        return this.unprocessed;
    }
    /**
     * @param unprocessed
     *          set value
     */
    public void setUnprocessed(final double unprocessed) {
        this.unprocessed = unprocessed;
    }
}
