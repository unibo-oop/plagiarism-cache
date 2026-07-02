package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class StaticObjView implements IstaticObjView{
	
	private final Rectangle rect;

	public StaticObjView() {
		this.rect = new Rectangle();
	}

	@Override
	public void setObjPos(final int x, final int y) {
	   rect.setX(x);
	   rect.setY(y);
	}

	@Override
	public void setObjDim(final int width, final int height) {
		rect.setWidth(width);
	    rect.setHeight(height);
	}

	@Override
	public void setImg(String img) {
		rect.setFill(new ImagePattern(new Image(img)));
	}

	@Override
	public Rectangle getObj() {
		return this.rect;
	}  
}
