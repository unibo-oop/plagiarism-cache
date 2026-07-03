package controller;


import java.awt.BasicStroke;
import java.awt.Color;

import model.ImageRendering;
import model.ImageRenderingView;
import view.CanvasBoard;
import view.MainPanel;
import view.ShowTheGUI;

/**
 * L'observer del MainPanel(View), si preoccupa di intercettare le azioni rigurdanti gli strumenti di selezione
 * e modificare di conseguenza lo stato del Model
 * @author redim
 */

public class SelectToolObserver implements SelectToolObserverView  {
    
    private final ShowTheGUI showTheGUI;
    private static ImageRenderingView drawImage;
    private final CanvasObserverView canvasObserver;

    public SelectToolObserver() {
        SelectToolObserver.drawImage = ImageRendering.getInstance();
        CanvasBoard.setModel(ImageRendering.getInstance());
        this.showTheGUI = new ShowTheGUI();
        this.showTheGUI.mainPanel.setObserver(this);
        this.canvasObserver = new CanvasObserver();
        MainPanel.imageView.setObserver(canvasObserver);     
        this.showTheGUI.start();
    }
    
  /**
   * seleziona gli strumenti da usare
   * @param toolChooser
   */
    public void setToolChooser(boolean toolChooser) {
        this.canvasObserver.setToolChooser(toolChooser);
    }
    
    public void setGraphicTool(int currentTool) {
        SelectToolObserver.drawImage.setActiveTool(currentTool);
    }
    

    public void setColor(Color c) {
       
        SelectToolObserver.drawImage.setColor(c);
    }

    public void setStroke(BasicStroke basicStroke) {
        SelectToolObserver.drawImage.setStroke(basicStroke);
        
    }
   
    public void setFillColor(Color c) {
        SelectToolObserver.drawImage.setFillColor(c);
        
    }

    public void setComposite(double d) {
        SelectToolObserver.drawImage.setComposite(d);
        
    }  
    
    public void setActiveFill(boolean activeFill) {
        SelectToolObserver.drawImage.setActiveFill(activeFill);
    }
}
