package model;

import controller.ScreenManager;



public abstract class BorderGameObjectImpl extends GameObjectImpl implements BorderGameObject{

	
	public BorderGameObjectImpl(double width, double height, double positionX, double positionY) {
		super(width, height, positionX, positionY);
	}
	
	
	
	public void checkMoveX(double deltaX) {
		double tmpX;
		tmpX = this.getPositionX() + ScreenManager.getScaleX(deltaX);
		
		if (tmpX + this.width <= ScreenManager.widthScreen && tmpX >= 0 ) {
			this.setPositionX(this.x + ScreenManager.getScaleX(deltaX));
		}else {	
			if (ScreenManager.widthScreen-(tmpX+this.width)<deltaX && tmpX>=0) {
				this.setPositionX((int)ScreenManager.widthScreen-this.width);
			}else {
				if (tmpX<0) {
					this.setPositionX(0);
				}
			}
		}
	}
	
	public void checkMoveY(double deltaY) {
		double tmpY;
		tmpY = this.getPositionY() + ScreenManager.getScaleY(deltaY);
	
		if (tmpY + this.height <= ScreenManager.heightScreen && tmpY >= 0 ) {
			this.setPositionY(tmpY);
		}else {
			if (ScreenManager.heightScreen - (tmpY+this.height) < ScreenManager.getScaleY(deltaY) && tmpY >=0) {
				this.setPositionY(ScreenManager.heightScreen-this.height);
			}else {
				if (tmpY<0) {
					this.setPositionY(0);
				}
			}
		}
	}
}
