package controller;

import model.people.Hero;
import view.HeroView;

public interface IheroController {
	
	/**
	 * inizializzazione heroView
	 */
	void start();
	
	/**
	 * 
	 * @return hero model
	 */
	Hero getHeroModel();
	
	/**
	 * 
	 * @return hero View
	 */
	HeroView getHeroView();

}
