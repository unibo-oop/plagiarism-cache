package hollowmen.view.juls.buttons;

import java.awt.Graphics;
import java.awt.Image;

import hollowmen.view.UtilitySingleton;

/**
 * The {@code NodeButton} class allows to graphically represent the nodes
 * of the SkillTree. Every node is a button.
 * 
 * NOTE: for now, the SkillTree menu is not implemented.
 * @author Juls
 */
public class NodeButton extends TranslucentButton {
	
	private static final long serialVersionUID = 7681825185715821047L;
	private Image nodeOver;
	private Image nodeUnlocked;
	private Image nodeNA;
	private Image nodeAvailable;

	
	public NodeButton() {
		super.addMouseListener(ma);
		this.loadImages();
	}
	
	public void loadImages() {
		nodeOver = UtilitySingleton.getInstance().getStorage().get("nodeOver").getImage();
		nodeUnlocked = UtilitySingleton.getInstance().getStorage().get("nodeUnlocked").getImage();
		nodeNA = UtilitySingleton.getInstance().getStorage().get("nodeNA").getImage();
		nodeAvailable = UtilitySingleton.getInstance().getStorage().get("nodeAvailable").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(nodeAvailable, 0, 8, null); // nodo disponibile
		if(isOver && isAvailable) { // mouse sopra, è disponibile, non è ancora sbloccato
			g.drawImage(nodeOver, 0, 8, null);
		} else if(isUnlocked) { // nodo sbloccato
			g.drawImage(nodeUnlocked, 0, 8, null);
		} else if (!isAvailable) { // nodo non disponibile
			g.drawImage(nodeNA, 0, 8, null);
		}
		super.paintComponent(g);
	}
}
