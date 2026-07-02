package controller;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import java.io.IOException;

public class GameLoopLv2 implements IgameLoop{
	private static final int MOVE = 10;
	private int prevX;
	private int prevY;
	
	private HeroController heroController;
	private HeroGoControllerLv2 heroGoControllerLv2;
	private ProiettileController proiettileController;
	private EnemyControllerLv2 enemyControllerLv2;
	private AnimationTimer timer;


	public GameLoopLv2(HeroGoControllerLv2 heroGoControllerLv2, Stage stage, HeroController heroController,
			ProiettileController proiettileController2, EnemyControllerLv2 enemyControllerLv2) {
		this.heroGoControllerLv2 = heroGoControllerLv2;
        this.heroController = heroController;
        this.proiettileController = proiettileController2;
        this.enemyControllerLv2 = enemyControllerLv2;
        this.go();
	}

	@Override
	public void go() {
		this.timer = new AnimationTimer(){
		    @Override
		    public void handle(long now) {
		        moveProiettile(heroGoControllerLv2.checkCollisionProiettili());
		        moveEnemy(heroGoControllerLv2.checkCollisionEnemy());
		        heroGoControllerLv2.checkNextLevel();
		        heroGoControllerLv2.checkEnd();
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
			enemyControllerLv2.move(xhero, yhero);
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
        heroGoControllerLv2.checkCollision(prevX, prevY);
        this.timer.start();
    }
	
	@Override
	public void clickEvent(double mousex, double mousey) throws IOException{
		this.proiettileController.createProiettile(heroGoControllerLv2.getHeroController().getHeroModel().getX(), 
				heroGoControllerLv2.getHeroController().getHeroModel().getY(), mousex, mousey);
	}

	@Override
	public void upEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setY(heroController.getHeroModel().getY()-MOVE);
		this.heroGoControllerLv2.getHeroController().getHeroView().updateY(heroController.getHeroModel().getY()-MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void rightEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setX(heroController.getHeroModel().getX()+MOVE);
		this.heroGoControllerLv2.getHeroController().getHeroView().updateX(heroController.getHeroModel().getX()+MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void leftEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setX(heroController.getHeroModel().getX()-MOVE);
		this.heroGoControllerLv2.getHeroController().getHeroView().updateX(heroController.getHeroModel().getX()-MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void downEvent() throws IOException {
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setY(heroController.getHeroModel().getY()+MOVE);
		this.heroGoControllerLv2.getHeroController().getHeroView().updateY(heroController.getHeroModel().getY()+MOVE);
		checkCollision(prevX, prevY);
	}
	
}