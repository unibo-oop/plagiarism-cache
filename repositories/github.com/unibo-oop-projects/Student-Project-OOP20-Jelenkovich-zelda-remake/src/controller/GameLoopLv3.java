package controller;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class GameLoopLv3 implements IgameLoop {

	private static final int MOVE = 10;
	private int prevX;
	private int prevY;
	
	private HeroController heroController;
	private HeroGoControllerLv3 heroGoControllerLv3;
	private ProiettileController proiettileController;
	private EnemyControllerLv3 enemyControllerLv3;
	private AnimationTimer timer;


	public GameLoopLv3(HeroGoControllerLv3 heroGoControllerLv3, Stage stage, HeroController heroController,
			ProiettileController proiettileController, EnemyControllerLv3 enemyControllerLv3) {
		this.heroGoControllerLv3 = heroGoControllerLv3;
        this.heroController = heroController;
        this.proiettileController = proiettileController;
        this.enemyControllerLv3 = enemyControllerLv3;
        this.go();
	}

	@Override
	public void go() {
		this.timer = new AnimationTimer(){
		    @Override
		    public void handle(long now) {
		        moveProiettile(heroGoControllerLv3.checkCollisionProiettili());
		        moveEnemy(heroGoControllerLv3.checkCollisionEnemy());
		        heroGoControllerLv3.checkNextLevel();
		        heroGoControllerLv3.checkEnd();
		    }
		};

		timer.start();
	}

	@Override
	public void moveEnemy(boolean flag) {
		timer.stop();
		if (!flag) {
			int xhero = heroController.getHeroModel().getX();
			int yhero = heroController.getHeroModel().getY();
			enemyControllerLv3.move(xhero, yhero);
			heroGoControllerLv3.checkEnd();
		}
		timer.start();
	}


	@Override
	public void moveProiettile(boolean flag) {
		timer.stop();
		if (!flag) {
			proiettileController.move();
		}
		timer.start();
	}

	@Override
	public void checkCollision(int prevX, int prevY) throws IOException {
		this.timer.stop();
        heroGoControllerLv3.checkCollision(prevX, prevY);
        this.timer.start();
    }
	
	@Override
	public void clickEvent(double mousex, double mousey) throws IOException{
		this.proiettileController.createProiettile(heroGoControllerLv3.getHeroController().getHeroModel().getX(), 
				heroGoControllerLv3.getHeroController().getHeroModel().getY(), mousex, mousey);
	}

	@Override
	public void upEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setY(heroController.getHeroModel().getY()-MOVE);
		this.heroGoControllerLv3.getHeroController().getHeroView().updateY(heroController.getHeroModel().getY()-MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void rightEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setX(heroController.getHeroModel().getX()+MOVE);
		this.heroGoControllerLv3.getHeroController().getHeroView().updateX(heroController.getHeroModel().getX()+MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void leftEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setX(heroController.getHeroModel().getX()-MOVE);
		this.heroGoControllerLv3.getHeroController().getHeroView().updateX(heroController.getHeroModel().getX()-MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void downEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setY(heroController.getHeroModel().getY()+MOVE);
		this.heroGoControllerLv3.getHeroController().getHeroView().updateY(heroController.getHeroModel().getY()+MOVE);
		checkCollision(prevX, prevY);
	}
	
}