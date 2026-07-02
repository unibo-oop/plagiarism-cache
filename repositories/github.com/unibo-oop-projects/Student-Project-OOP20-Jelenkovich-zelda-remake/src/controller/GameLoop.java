package controller;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import java.io.IOException;

public class GameLoop implements IgameLoop{
	private static final int MOVE = 10;
	private int prevX;
	private int prevY;
	
	private HeroGoController heroGoController;
	private final HeroController heroController;
	private ProiettileController proiettileController;
	private IenemyController enemyControllerLv1;
	private AnimationTimer timer;
	
	public GameLoop(HeroGoController heroGoController, Stage firstStage, HeroController heroController, ProiettileController proiettileController,
			IenemyController enemyControllerLv1) {
		this.heroGoController = heroGoController;
        this.heroController = heroController;
        this.proiettileController = proiettileController;
        this.enemyControllerLv1 = enemyControllerLv1;
        this.go();
	}

	@Override
	public void go() {
		this.timer = new AnimationTimer(){
			@Override
			public void handle(long now) {
				moveProiettile(heroGoController.checkCollisionProiettili());
			    moveEnemy(heroGoController.checkCollisionEnemy());
			    heroGoController.checkNextLevel();
			    heroGoController.checkEnd();
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
			enemyControllerLv1.move(xhero, yhero);
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
        try {
			heroGoController.checkCollision(prevX, prevY);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.timer.start();
    }
	
	@Override
	public void clickEvent(double mousex, double mousey) throws IOException{
		timer.stop();
		this.proiettileController.createProiettile(heroGoController.getHeroController().getHeroModel().getX(), 
				heroGoController.getHeroController().getHeroModel().getY(), mousex, mousey);
		timer.start();
	}

	@Override
	public void upEvent() throws IOException{
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setY(heroController.getHeroModel().getY()-MOVE);
		this.heroGoController.getHeroController().getHeroView().updateY(heroController.getHeroModel().getY()-MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void rightEvent() throws IOException{
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setX(heroController.getHeroModel().getX()+MOVE);
		this.heroGoController.getHeroController().getHeroView().updateX(heroController.getHeroModel().getX()+MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void leftEvent() throws IOException{
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setX(heroController.getHeroModel().getX()-MOVE);
		this.heroGoController.getHeroController().getHeroView().updateX(heroController.getHeroModel().getX()-MOVE);
		checkCollision(prevX, prevY);
	}

	@Override
	public void downEvent() throws IOException{
		prevX=heroController.getHeroModel().getX();
		prevY=heroController.getHeroModel().getY();
		this.heroController.getHeroModel().setY(heroController.getHeroModel().getY()+MOVE);
		this.heroGoController.getHeroController().getHeroView().updateY(heroController.getHeroModel().getY()+MOVE);
		checkCollision(prevX, prevY);
	}
	
}
