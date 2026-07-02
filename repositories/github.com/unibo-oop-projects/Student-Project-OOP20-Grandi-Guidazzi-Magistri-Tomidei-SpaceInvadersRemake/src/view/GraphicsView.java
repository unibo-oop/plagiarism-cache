package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ViewGameController;
import model.entitiesutil.MappedEntity;
import util.Constants;
import util.Strings;

/**
 * A class that manages the view.
 */
public class GraphicsView extends JPanel{

	private static final long serialVersionUID = 1L;
	private UpdateManager imageManager;
	private Set<MappedEntity> entity;
	private Image imageBackgound;
	private Image resizedImage;
	
	/**
	 * The constructor that create the objects to manage the images.
	 */
	public GraphicsView(String uriSkin, ViewGameController ctrl) throws IOException{
		try {
			imageBackgound = ImageIO.read(ClassLoader.getSystemResource(Strings.BackgroundImages.GAME_BACKGROUND));
			this.resizedImage = imageBackgound.getScaledInstance(Constants.ObjectDimension.preferDimension.width,
				Constants.ObjectDimension.preferDimension.height, Image.SCALE_DEFAULT);
		} catch(IOException e) {
			this.setOpaque(false);
		}
		this.imageManager = new ImageManagerImpl(uriSkin);
		this.entity = Collections.synchronizedSet(new HashSet<>());	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics g2D = (Graphics2D)graphics;
		
		g2D.drawImage(resizedImage, 0, 0, null);
		
		this.entity.forEach(e -> {
			Image image;
			try {
				image = this.imageManager.drawEntity(e);
				g2D.drawImage(image, (int)e.getX()-e.getWidth()/2, (int)e.getY()-e.getHeight()/2, null);
			} catch (IOException e1) {
				System.exit(1);
			}
		});
	}
	
	/**
	 * Method that update the screen.
	 * @param set
	 */
	public void refreshGui(Set<MappedEntity> set) {
		this.entity.clear();
		this.entity.addAll(set);
		SwingUtilities.invokeLater(()-> this.repaint());
		
	}

}
