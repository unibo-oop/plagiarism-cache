package org.lkyhro.gui;

//import org.lkyhro.HeroObserver;

import javax.swing.*;

/**
 * Created by Migani Luca on 16/02/2016.
 */
public class InitialGUI{
    private final static int WIDTH=500;
    private final static int HEIGHT=500;
    private final JFrame frame=new JFrame("Lucky Hero");
    private JComponent actualPanel=new JPanel();

    /**
     * Constructor method for InitialGui.
     *
     */
    public InitialGUI(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(actualPanel);
        frame.setSize(WIDTH, HEIGHT);
    }

    /**
     * This method is used to switch the panel displayed in the frame.
     * @param component JComponent new component, usually a JPanel, to be displayed in the frame
     */
    public void switchPanel(JComponent component){
        frame.remove(actualPanel);
        frame.add(component);
        frame.revalidate();
        actualPanel=component;
    }


}
