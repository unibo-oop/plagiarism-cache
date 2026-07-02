package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class HeroView implements IdinamicObjView{
	
	private final Rectangle rect;

    public HeroView() {
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
     * settaggio coordinata x
     * @param x
     */
    public void updateX(int x) {
    	this.rect.setX(x);
    }
    
    /**
     * settaggio coordinata y
     * @param y
     */
    public void updateY(int y) {
    	this.rect.setY(y);
    }

    /**
     * settaggio coordinate x e y
     * @param x
     * @param y
     */
	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
	}
}
