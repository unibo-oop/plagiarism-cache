package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Model.Type;

/**
 * This panel contains the legend whit the color and Type of any course. The
 * panel is addes in the PanelButto class
 * 
 * @author Anna Termopoli
 *
 */
public class PanelLegend {

	private final JPanel panelLegend;

	public PanelLegend() {

		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		this.panelLegend = new JPanel(new GridBagLayout());
		this.panelLegend.setBorder(new TitledBorder("LEGENDA"));
		for (final Type t : Type.values()) {
			c.gridx = 0;
			final JLabel color = new JLabel("   ");
			color.setOpaque(true);
			color.setBackground(t.getColor());
			this.panelLegend.add(color, c);
			c.gridx++;
			this.panelLegend.add(new JLabel(t.getCharacter()), c);
			c.gridy++;

		}
	}

	public JPanel getPanelLegend() {
		return panelLegend;
	}

}
