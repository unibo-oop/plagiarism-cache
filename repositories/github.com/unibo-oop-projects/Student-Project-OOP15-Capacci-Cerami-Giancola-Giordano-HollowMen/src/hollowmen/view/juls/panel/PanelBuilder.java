package hollowmen.view.juls.panel;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * The {@code PanelBuilder} class contains the builder inner class.
 * @author Juls
 */
public class PanelBuilder{

	public PanelBuilder() {};
	
	/**
	 * The {@code getBuilder} method allows to obtain a built panel.
	 * @return - a Builder instance, so a new JPanel.
	 * @see {@link Builder}
	 */
	public static JPanelBuilder getBuilder(){
		return new Builder();
	}
	
	/**
	 * The {@code Builder} class is the concrete builder; it extends JPanel and implements
	 * {@link JPanelBuilder}.
	 * @author Juls
	 */
	private static class Builder extends JPanel implements JPanelBuilder {

		private static final long serialVersionUID = -3585215555536977182L;
		
		@Override
		public JPanelBuilder layout(int rows, int columns, int horizontalGap, int verticalGap) {
			super.setLayout(new GridLayout(rows, columns, horizontalGap, verticalGap));
			return this;
		}

		@Override
		public JPanelBuilder bound(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);
			return this;
		}

		@Override
		public JPanelBuilder addTo(Component component) {
			super.add(component);
			return this;
		}

		@Override
		public JPanel build() {
			super.setOpaque(false);
			return this;
		}
	}
}