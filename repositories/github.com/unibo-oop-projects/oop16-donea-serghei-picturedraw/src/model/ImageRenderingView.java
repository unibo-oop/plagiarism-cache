package model;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;

import view.CanvasBoard;

public interface ImageRenderingView {

    RenderingHints getRenderingHints();

    int getActiveTool();

    int getOldX();

    int  getOldY();

    Color getColor();

    Stroke getStroke();

    void setColor(Color c);

    void setStroke(BasicStroke basicStroke);

    void setActiveTool(int currentTool);

    void addObserver(CanvasBoard imageView);

    Shape getShape();

    void setFillColor(Color c);

    Color getFillColor();

    void setComposite(double d);

    AlphaComposite getComposite();

    void drawToImage(Point point, Graphics2D g);

    void drawShapeToImage(Point point, Graphics2D g, boolean drawOnImage);

    void setStartPoint(Point point);

    void setEndPoint(Point point);

    void setActiveFill(boolean activeFill);
    
    boolean isActiveFill();
      
}