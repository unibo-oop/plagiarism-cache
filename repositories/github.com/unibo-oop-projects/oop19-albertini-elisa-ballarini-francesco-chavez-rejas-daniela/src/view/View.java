package view;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * General interface of a view in the application.
 */
public interface View {

    /**
     * @return a JPanel that will be set as view in the main frame.
     */
    JPanel getMainPanel();

    /**
     * Method that initializes all components of the view based on the
     * frameDimension.
     * 
     * @param frameDimension : the current dimension of the main frame of the
     *                        application.
     */
    void initView(Dimension frameDimension);

}
