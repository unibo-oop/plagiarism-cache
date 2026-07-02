package view;


import java.util.List;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;
import utility.ImageLoader;


/**
 * The class that will spawn the entities and the road on the main stage
 * @author denis
 *
 */
public class DrawEntities {


	private final ImageLoader loader = ImageLoader.getInstance();
	private Pane pane = new Pane();
	private Pane pone = new Pane();
	private ImageView backgrd1;
	private ImageView backgrd2;


	public DrawEntities() {	}

	/**
	 * 
	 * @param pane       The pane where the entities will spawn
	 * @param listEntity List with all entities created
	 */
	public void drawEntity(final Pane pane, List<Entity> listEntity) {

		this.pone = pane;
		pone.getChildren().clear();
		synchronized (listEntity) {
			if (!listEntity.isEmpty()) {
				for (Entity e : listEntity) {
					final ImageView img = loader.getEntity(e.getType(), e.getId());
					pone.getChildren().add(img);
					img.setX(e.getPosition().getX());
					img.setY(e.getPosition().getY());
					img.setFitWidth(e.getHitbox().getWidth());
					img.setFitHeight(e.getHitbox().getHeight());
				}

			}
		}
	}

	/**
	 * Method that will initialize the road with the background
	 * 
	 * @param panel The pane where the background will draw
	 */
	public void drawStarters(final Pane panel) {
		this.backgrd1 = new ImageView(loader.getBackground1());
		this.backgrd2 = new ImageView(loader.getBackground1());
		this.pane = panel;
		pane.getChildren().addAll(backgrd1, backgrd2);
		this.scrollRoad();
	}
	
	
	/**
	 * The method that will animate the road with a scroll effect
	 */
	public void scrollRoad() {

		TranslateTransition t1 = new TranslateTransition(Duration.millis(4000), backgrd1);
		t1.setFromY(0);
		t1.setToY(1280);
		t1.setInterpolator(Interpolator.LINEAR);

		TranslateTransition t2 = new TranslateTransition(Duration.millis(4000), backgrd2);
		t2.setFromY(-1280);
		t2.setToY(0);
		t2.setInterpolator(Interpolator.LINEAR);

		ParallelTransition seq = new ParallelTransition(t2, t1);
		seq.setCycleCount(Animation.INDEFINITE);
		seq.play();
	}

}
