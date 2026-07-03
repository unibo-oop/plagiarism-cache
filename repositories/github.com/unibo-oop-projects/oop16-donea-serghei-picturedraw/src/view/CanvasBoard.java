package view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.RenderingHints.Key;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import controller.CanvasObserverView;
import model.ImageRenderingView;

/**
 * Il componente sui cui vengono eseguite le operazioni concernenti il disegno
 * @author redim
 *
 */

@SuppressWarnings("serial")
public class CanvasBoard extends JComponent implements CanvasBoardView {

    
    private CanvasObserverView observer;
    private static CanvasBoard instance = new CanvasBoard();
    private static ImageRenderingView drawImage;

    private BufferedImage originalImage;
    
    private BufferedImage canvasImage;       
    
     Stroke stroke = new BasicStroke(
            3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.7f);
    Graphics2D graphics2D;
    private RenderingHints renderingHints;
     private CanvasBoard() {
         Map<Key, Object> hintsMap = new HashMap<RenderingHints.Key,Object>();
         hintsMap.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         hintsMap.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
         hintsMap.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         hintsMap.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         this.renderingHints = new RenderingHints(hintsMap); 
         
         final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
         final int sw = (int) screen.getWidth();
         final int sh = (int) screen.getHeight();         
         this.setImage(new BufferedImage(sw, sh, BufferedImage.TYPE_INT_RGB), true);
         
         this.addMouseListener(new MouseAdapter() 
                 {
             @Override
             public void mousePressed(MouseEvent e) {
                     observer.setStartPoint(new Point(e.getX(), e.getY()));
                     observer.setEndPoint(new Point(e.getX(), e.getY()));
                     graphics2D = (Graphics2D)canvasImage.createGraphics();
                     observer.drawToImage(e.getPoint(), graphics2D, true);
                 
             }

             @Override
             public void mouseReleased(MouseEvent e) {
                 graphics2D = (Graphics2D)canvasImage.createGraphics();
                 observer.drawToImage(e.getPoint(), graphics2D, true);
             }
                 });
       
         this.addMouseMotionListener(new MouseMotionAdapter()
         {
                 
           public void mouseDragged(MouseEvent e)
           {           
                  graphics2D = (Graphics2D)canvasImage.createGraphics();
                   observer.drawToImage(e.getPoint(), graphics2D,false);
                   
               }
         });
        
    }
    
   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D)g;
        graphics2D.setRenderingHints(drawImage.getRenderingHints());
          if (canvasImage != null) {
             // int x = (this.getWidth() - canvasImage.getWidth(null)) / 2;
              //int y = (this.getHeight() - canvasImage.getHeight(null)) / 2;
              graphics2D.drawImage(canvasImage, 0, 0, null);
          }
          
          /*Chiamato finquando non si rilascia il mouse, dentro mouseDragged
           * 
           */
        if(drawImage.getShape() != null) {
            graphics2D.setComposite(drawImage.getComposite());
            graphics2D.draw(drawImage.getShape());
            if(!drawImage.isActiveFill()) {
            graphics2D.setColor(drawImage.getFillColor());
            graphics2D.fill(drawImage.getShape());
            }
        }         
    }
    
    
    /**
     * Viene utilizzato per il settaggio dell'immagine originale e per disegnare la canvasImage sulla CanvasBoard
     *  @param image
     *      l'immagine da utilizzare come base di disegno
     *  @param bool
     *         necessario per sapere se l'originalImage vada o meno aggiornata
     */
    public void setImage(BufferedImage image, boolean bool) {
        if(bool == true) {
        this.originalImage = image;
        }
        int sw = image.getWidth();
        int sh = image.getHeight();
        this.setPreferredSize(new Dimension(sw, sh));  
        this.setMaximumSize(new Dimension(sw, sh)); 
        this.setMinimumSize(new Dimension(sw, sh));
        canvasImage = new BufferedImage(sw, sh,BufferedImage.TYPE_INT_RGB);

        Graphics2D g = this.canvasImage.createGraphics();
        g.setRenderingHints(this.renderingHints);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        this.repaint();
    }
    
   
    
   /**
    * Viene utilizzato per cambiare il colore dell'immagine in base al colore corrente, oppure per aggiornare il colore
    * delle immagini degli strumenti di selezione del colore
    * @param bi
    *       l'immagine su cui operare le modifche
    * @param 
    *       necessario per distingure tra fill e stroke
    */
    public void clear(BufferedImage bi, boolean bool) {
        Graphics2D g = bi.createGraphics();
        g.setRenderingHints(drawImage.getRenderingHints());
        if(bool) {
            g.setColor(drawImage.getColor());
        } else {
            g.setColor(drawImage.getFillColor());
        }
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        this.repaint();
    }
    
   
    /**
     * Singleton
     * @return instance
     *         The instance of the object
     */
    public static CanvasBoard getInstance()
    {    
            return instance;
    }

    /**
     * @return the originalImage
     */
    
    public BufferedImage getOriginalImage() {
        return this.originalImage;
    }
    
    
   /**
    * @return the canvasImage
    */
    public BufferedImage getCanvasImage() {
        return this.canvasImage;
    }


    /**
     * Imposta l'observer per la CanvasBoard
     * @param observer
     */
    public void setObserver(CanvasObserverView canvasObserver) {
       this.observer = canvasObserver;
        
    }

    /**
     * Imposta il Model per la CanvasBoard cossiché possa essere interrogato e
     * aggiunge la CanvasBoard come observer del Model
     * @param viewModel
     */
   public static void setModel(ImageRenderingView viewModel) {
      CanvasBoard.drawImage = viewModel;
      CanvasBoard.drawImage.addObserver(getInstance());
        
   }
    
   public void showError(Throwable t) {
       JOptionPane.showMessageDialog(
               this, 
               t.getMessage(), 
               t.toString(), 
               JOptionPane.ERROR_MESSAGE);
   }
}
