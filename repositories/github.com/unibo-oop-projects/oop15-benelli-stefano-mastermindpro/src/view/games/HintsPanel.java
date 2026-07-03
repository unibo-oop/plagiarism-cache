package view.games;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.games.Hint;
import view.images.ImageSetFactory;

public class HintsPanel extends JPanel {

	private static final long serialVersionUID = 6415847874990937227L;

	public HintsPanel() {

	}

	public void initialize(int codeLength) {

		this.removeAll();

		this.setLayout(new GridLayout(1,codeLength));

		for(int i=0; i<codeLength; i++) {
			JLabel hintLabel = new JLabel();
			hintLabel.setHorizontalAlignment(JLabel.CENTER);
			hintLabel.setIcon(ImageSetFactory.getCurrentInstance().getImage(Hint.EMPTY));
			this.add(hintLabel);
		}
	}
	
	public void update(Hint[] hints) { 
		for(int i=0; i<hints.length; i++) {
			((JLabel)this.getComponents()[i]).setIcon(ImageSetFactory.getCurrentInstance().getImage(hints[i]));
		}
	}
}
