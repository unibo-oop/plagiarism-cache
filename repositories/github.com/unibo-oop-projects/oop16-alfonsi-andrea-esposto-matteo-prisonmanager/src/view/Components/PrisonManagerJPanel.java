package view.Components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import java.awt.Color;

/**
 * jpanel predefinito per il programma
 */
public class PrisonManagerJPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3177915859799100383L;

    /**
     * Constructor.
     */
    public PrisonManagerJPanel() {
        super();
        this.setBackground(new Color(210, 210, 210));
    }
    /**
     * Constructor with layout.
     * @param layout The layout of the panel.
     */
    public PrisonManagerJPanel(final LayoutManager layout) {
        super(layout);

        this.setBackground(new Color(210, 210, 210));
    }
}
