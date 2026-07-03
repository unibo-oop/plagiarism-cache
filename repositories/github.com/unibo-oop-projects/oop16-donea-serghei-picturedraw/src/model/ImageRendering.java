package model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import controller.DrawTool;
import controller.ShapeTool;
import view.CanvasBoard;

/**
 * La classe che detiene lo stato dell'applicazione in un determinato momento, in base a quest'ultimo vengono guidate le operazioni.
 * @author redim
 *
 */

public class ImageRendering implements ImageRenderingView {
    
    private Color color = Color.WHITE;
    private Color fillColor = Color.WHITE;
    float transparentVal = 1.0f; 
    private final static int ROUND = 15;
    private final static int BRUSHSIZE = 8;
    Stroke stroke = new BasicStroke(
            3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.7f);
    private RenderingHints renderingHints;

    private static CanvasBoard imageView;
    private static ImageRenderingView instance = new ImageRendering();
    private boolean activeFill;
    private int drawTool = -1;
    private Point endPoint;
    private Point startPoint;
    private Shape shape;
    public ImageRendering() {
        Map<Key, Object> hintsMap = new HashMap<RenderingHints.Key,Object>();
        hintsMap.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hintsMap.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        hintsMap.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        hintsMap.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.renderingHints = new RenderingHints(hintsMap); 
    }


    
    public void setActiveTool(int currentTool) {
        this.drawTool = currentTool;
    }
    
   
    private Graphics2D setGraphics(Graphics2D g) {
        g.setRenderingHints(renderingHints);
        g.setColor(this.color);
        g.setStroke(stroke);
        return g;
    }
     
    /**
     * Il metodo, in base allo strumento ShapeTool selezionato, si preoccupa di disegnare la figura, e di aggiornare di conseguenza la CanvasBoard 
     * @param point punto corrente
     * @param g l'oggetto grafico dell'immagine, in base al suo stato corrente viengono determinate le operazioni di rendering.
     */
    public void drawShapeToImage(Point point, Graphics2D g, boolean drawOnImage) {
        if(drawTool == ShapeTool.ROUND_RECTANGLE.ordinal()) {
           g = setGraphics(g);
            shape = ShapeGenerator.drawRoundRectangle(startPoint.x, startPoint.y, point.x, point.y, ROUND, ROUND);
            if(drawOnImage) {
                g.setComposite(AlphaComposite.getInstance(
                                    AlphaComposite.SRC_OVER, transparentVal));
               g.draw(shape);
               g.setColor(fillColor);
               if(!this.isActiveFill()) {
               g.fill(shape);
               }
               shape = null;
            }
            g.dispose();
            imageView.repaint();
        } else if((drawTool == ShapeTool.RECTANGLE.ordinal())) {
            g = setGraphics(g);
            shape = ShapeGenerator.drawRectangle(startPoint.x, startPoint.y, point.x, point.y);
            if(drawOnImage) {
                g.setComposite(AlphaComposite.getInstance(
                                    AlphaComposite.SRC_OVER, transparentVal));
               g.draw(shape);
               g.setColor(fillColor);
               if(!this.isActiveFill()) {
                   g.fill(shape);
                   }
               shape = null;
            }
            g.dispose();
            imageView.repaint();
        } else if(drawTool == ShapeTool.LINE.ordinal()) {
            g = setGraphics(g);
            shape = ShapeGenerator.drawLine(startPoint.x, startPoint.y, point.x, point.y);
            if(drawOnImage) {
                g.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, transparentVal));
                g.draw(shape);
                g.setColor(fillColor);
                if(!this.isActiveFill()) {
                    g.fill(shape);
                    }
                shape = null;
             }
            g.dispose();
            imageView.repaint();
        } else if(drawTool == ShapeTool.ELLIPSE.ordinal()) {
            g = setGraphics(g);
            shape = ShapeGenerator.drawEllipse(startPoint.x, startPoint.y, point.x, point.y);
            if(drawOnImage) {
                g.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, transparentVal));
                g.draw(shape);
                g.setColor(fillColor);
                if(!this.isActiveFill()) {
                    g.fill(shape);
                    }
                shape = null;
             }
            g.dispose();
            imageView.repaint(); 
        } else {
            
                JOptionPane.showMessageDialog(null, "Select a draw tool!");
        }
    }
    
    
    /**
     * Il metodo, in base allo strumento DrawTool selezionato, si preoccupa di disegnare quest'ultimo sull'immagine
     * e di aggiornare di conseguenza la CanvasBoard 
     */
    public void drawToImage(Point point, Graphics2D g) {
        
        if(drawTool == DrawTool.DRAW_TOOL.ordinal()) {
            g = setGraphics(g);
            g.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, transparentVal));
        g.drawLine(endPoint.x, endPoint.y, point.x, point.y);
        g.dispose();
        imageView.repaint();
        endPoint.x = point.x;
        endPoint.y = point.y; 
        } else if(drawTool == DrawTool.BRUSH.ordinal()) {
            g = setGraphics(g);
            g.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, transparentVal));
            shape = ShapeGenerator.drawBrush(point.x, point.y, BRUSHSIZE, BRUSHSIZE);
            g.fill(shape);
            g.dispose();
            shape = null;
            imageView.repaint();
        } else if(drawTool == DrawTool.TEXT_TOOL.ordinal()) {
            String text = JOptionPane.showInputDialog(imageView, "Text to add", "Text"); 
            if (text!=null) {
                g = setGraphics(g);
                g.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, transparentVal));
                g.setFont(new Font(null, Font.BOLD, 20));
                g.drawString(text,point.x,point.y);
                g.dispose();
                imageView.repaint();
            }
        } else if(drawTool == DrawTool.BUCKET_TOOL.ordinal()) {
            g.setRenderingHints(renderingHints);
            g.setColor(color);
            g.fillRect(0, 0, imageView.getWidth(), imageView.getHeight());
            g.dispose();
            imageView.repaint();        
            }
    }
        
    
    
    public static ImageRenderingView getInstance() 
    {
            return instance;
    }


   
    public void setColor(Color c) {
       this.color = c;
        
    }


   
    public void setStroke(BasicStroke basicStroke) {
       this.stroke = basicStroke;
        
    }


    public Color getColor() {
       
        return this.color;
    }
    
    public void addObserver(CanvasBoard imageView) {
        ImageRendering.imageView = imageView;
    }


   
    public int getActiveTool() {
        return this.drawTool;
        
    }



    public int getOldX() {
        return this.endPoint.x;
        
    }


   
    public int getOldY() {
        return this.endPoint.y;
        
    }
    
    public Shape getShape() {
        return this.shape;
        
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }



    public void setStartPoint(Point point) {
        this.startPoint = point;      
    }
    
    public void setEndPoint(Point point) {
        this.endPoint = point;       
      }



    @Override
    public void setFillColor(Color c) {
      this.fillColor = c;
        
    }



    @Override
    public Color getFillColor() {
       return this.fillColor;
    }



    @Override
    public void setComposite(double d) {
      this.transparentVal = (float)d;
        
    }

    @Override
    public AlphaComposite getComposite() {
        return AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, transparentVal);
    }



    @Override
    public RenderingHints getRenderingHints() {
       return this.renderingHints;
    }



    public boolean isActiveFill() {
        return activeFill;
    }



    public void setActiveFill(boolean activeFill) {
        this.activeFill = activeFill;
    }

}
