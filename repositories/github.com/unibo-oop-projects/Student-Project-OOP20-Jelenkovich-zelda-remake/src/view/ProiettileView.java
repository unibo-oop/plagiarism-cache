package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ProiettileView implements IdinamicObjView{

	private final Rectangle rect;
	
	public ProiettileView() {
		this.rect = new Rectangle();
	}
	
	@Override
	public void setWidth(int width) {
        this.rect.setWidth(width);
    }

	@Override
    public void setHeight(int height) {
        this.rect.setHeight(height);
    }

	@Override
    public void setImg(String img) {
        this.rect.setFill(new ImagePattern(new Image(img)));
    }
	
	@Override
	public Rectangle getRect() {
		return this.rect;
	}

	/**
	 * settaggio coordinate x e y
	 * @param x
	 * @param y
	 */
    public void updatePos(double x, double y) {
    	this.rect.setX(x);
    	this.rect.setY(y);
    }
    
    /**
     * settaggio dimensioni
     * @param width
     * @param height
     */
    public void setProiettileDim(final int width, final int height) {
		this.rect.setWidth(width);
		this.rect.setHeight(height);
	}

}
