package controller;

import java.awt.Graphics2D;
import java.awt.Point;

public interface CanvasObserverView {

    void setToolChooser(boolean toolChooser);

    void drawToImage(Point point, Graphics2D g, boolean drawOnImage);

    void setStartPoint(Point point);

    void setEndPoint(Point point);

}