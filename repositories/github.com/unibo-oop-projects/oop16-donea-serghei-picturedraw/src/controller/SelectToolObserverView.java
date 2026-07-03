package controller;

import java.awt.BasicStroke;
import java.awt.Color;

public interface SelectToolObserverView {

    void setToolChooser(boolean toolChooser);

    void setGraphicTool(int currentTool);

    void setColor(Color c);

    void setStroke(BasicStroke basicStroke);

    void setFillColor(Color c);

    void setComposite(double d);
    
    void setActiveFill(boolean activeFill);

}