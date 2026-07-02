package controller;

import model.people.Hero;
import view.HeroView;

public class HeroController implements IheroController{
	
	private final HeroView heroView;
    private final Hero heroModel;
    
    public HeroController(int initx, int inity) {
    	this.heroModel = new Hero(initx, inity);
    	this.heroView = new HeroView();
    	this.start();
    }

    @Override
	public void start() {
		heroView.setImg(heroModel.getImgPath());
		heroView.setPosition(heroModel.getX(), heroModel.getY());
		heroView.setHeight(heroModel.getHeroHeight());
		heroView.setWidth(heroModel.getHeroWidth());
	}
	
    @Override
	public Hero getHeroModel() {
		return this.heroModel;
	}
	
    @Override
    public HeroView getHeroView() {
		return this.heroView;
	}

}
