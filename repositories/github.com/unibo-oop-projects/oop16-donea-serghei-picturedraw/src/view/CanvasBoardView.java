package view;

import java.awt.image.BufferedImage;

import controller.CanvasObserverView;

public interface CanvasBoardView {

    void setImage(BufferedImage image, boolean bool);

    void clear(BufferedImage bi, boolean bool);

    //void setColor(Color color);

    BufferedImage getOriginalImage();

    BufferedImage getCanvasImage();

     void showError(Throwable t);
     
    
    void setObserver(CanvasObserverView canvasObserver);

}