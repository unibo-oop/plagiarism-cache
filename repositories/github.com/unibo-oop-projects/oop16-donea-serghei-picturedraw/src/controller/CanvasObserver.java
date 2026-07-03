package controller;

import java.awt.Graphics2D;
import java.awt.Point;


import model.ImageRendering;
import model.ImageRenderingView;

/**
 * L'observer della CanvasBoard(View), si preoccupa di intercettare le azioni che avvengono sulla CanvasBoard
 * e delega al Model la gestione
 * @author redim
 */
public class CanvasObserver implements CanvasObserverView {
    
    private static ImageRenderingView drawImage;
    private boolean toolChooser;
    
    
    public CanvasObserver() {
        CanvasObserver.drawImage = ImageRendering.getInstance();
    }


    public void setToolChooser(boolean toolChooser) {
        this.toolChooser = toolChooser;
    }
   

    /**
     * Seleziona in base allo strumento toolChooser l'opportuno metodo da chiamare 
     * @param toolChooser usato per scegliere se chiamare il metodo drawToImage oppure drawShapeToImage
     * @param drawOnImage utile per capire se il mouse è stato rilasciato, in tal caso l'immagine andrà disegnata sul JComponent
     */
    public void drawToImage(Point point, Graphics2D g, boolean drawOnImage) {
        if(toolChooser) {
        CanvasObserver.drawImage.drawToImage(point, g);
        
        } else {
            CanvasObserver.drawImage.drawShapeToImage(point, g, drawOnImage);
        }
    }
    
 
    public void setStartPoint(final Point point) {
        CanvasObserver.drawImage.setStartPoint(point);
    }
    
    public void setEndPoint(final Point point) {
        CanvasObserver.drawImage.setEndPoint(point);
    }

}
