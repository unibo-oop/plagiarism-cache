package model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
/**
 * Classe utility, passate le cordinate specifiche, si preoccupa di ritornare una Shape relativa.
 * @author redim
 *
 */



public final class ShapeGenerator {
    
    private ShapeGenerator() {
        
    }

    public static Line2D.Float drawLine(
            int x1, int y1, int x2, int y2)
    {

            return new Line2D.Float(
                    x1, y1, x2, y2);
    }
    
    public static RoundRectangle2D.Float  drawRoundRectangle(
            int x1, int y1, int x2, int y2, int round, int round2)
    {
         
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);        
            
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            return new RoundRectangle2D.Float(
                    x, y, width, height, round, round);
    }
    
    public static Rectangle2D.Float  drawRectangle(
            int x1, int y1, int x2, int y2)
    {
            
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            
            
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            return new Rectangle2D.Float(
                    x, y, width, height);
    }
    
    public static Ellipse2D.Float drawEllipse(
            int x1, int y1, int x2, int y2)
    {
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);

            return new Ellipse2D.Float(
                    x, y, width, height);
    }
    
    public static Ellipse2D.Float drawBrush(
            int x1, int y1, int brushStrokeWidth, int brushStrokeHeight)
    {
            
            return new Ellipse2D.Float(
                x1, y1, brushStrokeWidth, brushStrokeHeight);
            
    }
}
