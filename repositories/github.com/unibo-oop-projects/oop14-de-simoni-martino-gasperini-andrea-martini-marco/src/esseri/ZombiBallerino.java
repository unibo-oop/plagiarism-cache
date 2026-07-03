package esseri;

import javax.swing.JPanel;

import controller.InsertionPanelController;

public class ZombiBallerino extends Zombi {

	int tempo_per_un_azione=1500;
	int tempo_in_ms=0;

	public ZombiBallerino(final Posizione2D posizione,final InsertionPanelController<Azione,? extends JPanel> controller) {
	
		super(posizione,controller,"zombi_ballerino.jpg");
	
	}

	
	
}
