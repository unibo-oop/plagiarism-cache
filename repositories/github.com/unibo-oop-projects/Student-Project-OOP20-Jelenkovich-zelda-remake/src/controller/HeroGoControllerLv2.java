package controller;

import java.io.IOException;

import javafx.scene.Node;
import javafx.stage.Stage;
import view.EnemyView;
import view.GameViewManager;
import view.ProiettileView;
import view.StaticObjView;
import view.ThirdLevel;
import view.ViewObserver;

public class HeroGoControllerLv2 implements ViewObserver, IheroGoController{
	
	private static final int INIT_Y = 480;
    private static final int INIT_X = 20;
	private final IobjectController objControllerLv2;
    private final HeroController heroController;
    private final EnemyControllerLv2 enemyControllerLv2;
    private final ProiettileController proiettileController;
    private final GameViewManager gvm;
    private final GameLoopLv2 gameLoopLv2;
    private final Stage stage;
    private int count;
    
    private boolean flagdx = true;
    private boolean flagsx = true;
    private boolean flagup = true;
    private boolean flagdown = true;

	public HeroGoControllerLv2(Stage stage) {
		this.stage = stage;
		this.objControllerLv2 = new ObjectControllerLv2();
    	this.proiettileController = new ProiettileController();
    	this.heroController = new HeroController(INIT_X, INIT_Y);
    	this.enemyControllerLv2 = new EnemyControllerLv2();
    	this.gvm = new GameViewManager(this, stage);
    	gvm.secondStage(heroController.getHeroModel().getLife());
    	gameLoopLv2 = new GameLoopLv2(this, stage, heroController, proiettileController, enemyControllerLv2);
	}

	@Override
	public void newGame() {
		this.addNode(heroController.getHeroView().getRect());
        for (Node n : objControllerLv2.getNodeList()) {
            this.addNode(n);
        }
        for (Node n : enemyControllerLv2.getNodeList()) {
            this.addNode(n);
        }	
    }
	
	@Override
	public void addProiettile() {
		for (Node n : proiettileController.getNodeList()) {
            this.addNode(n);
        }
	}

    public void addNode(final Node n) {
        this.gvm.addChildren(n);
    }

    @Override
    public IobjectController getObjController() {
        return this.objControllerLv2;
    }

    @Override
    public HeroController getHeroController() {
        return heroController;
    }
    
    @Override
    public IenemyController getEnemyController() {
    	return enemyControllerLv2;
    }
    
    @Override
    public void checkCollision(int prevX, int prevY) throws IOException {
		double xHero = 0;
		double yHero = 0;
    	for (Node element : objControllerLv2.getNodeList()) {
    		if (element.getLayoutBounds().intersects(heroController.getHeroView().getRect().getLayoutBounds())) {
    			xHero = heroController.getHeroModel().getX();
    			yHero = heroController.getHeroModel().getY();
    			chekDir(xHero, yHero, prevX, prevY);
    		}
    		
    	}
    }
    
    @Override
    public boolean checkCollisionProiettili() {
    	boolean flag = false;
    	int i=-1;
    	for (ProiettileView p : proiettileController.getlistProiettileView()) {
    		i++;
    		for (StaticObjView tree : objControllerLv2.getObjListViewTree()) {
    			if (p.getRect().getBoundsInLocal().intersects(tree.getObj().getBoundsInLocal())) {
    				proiettileController.getlistProiettileView().get(i).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(i);
    				flag = true;
    				break;
    			}
    		}
    		for (StaticObjView stone : objControllerLv2.getObjListViewStone()) {
    			if (p.getRect().getBoundsInLocal().intersects(stone.getObj().getBoundsInLocal())) {
    				proiettileController.getlistProiettileView().get(i).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(i);
    				flag = true;
    				break;
    			}
    		}
    		i=-1;
    		for (EnemyView enemy : enemyControllerLv2.getlistEnemyView()) {
    			i++;
    			int ip=-1;
    			if (p.getRect().getBoundsInLocal().intersects(enemy.getRect().getBoundsInLocal())){
    				ip++;
    				proiettileController.getlistProiettileView().get(ip).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(ip);
    				enemy.setImg("res/img/deadEnemy.png");
    				enemy.setEnemyDim(25, 25);
    				enemyControllerLv2.deleteEnemy(i);
    				flag=true;
    				break;
    			}
    		}
    	}
    	return flag;
	}
    
    @Override
    public boolean checkCollisionEnemy() {
		boolean flag = false;
		int i =-1;
		for(EnemyView enemyElem : enemyControllerLv2.getlistEnemyView()) {
			i++;
			for (StaticObjView element : objControllerLv2.getObjListViewStone()) {
				if (element.getObj().intersects(enemyElem.getRect().getLayoutBounds())){
					enemyControllerLv2.getlistEnemyView().get(i).setPosition(enemyControllerLv2.getlistEnemy().get(i).getPrevx(), 
													enemyControllerLv2.getlistEnemy().get(i).getPrevy());
					
					flag = true;
					break;
    			}
			}
			
			for (StaticObjView element : objControllerLv2.getObjListViewTree()) {
				if (element.getObj().intersects(enemyElem.getRect().getLayoutBounds())){
					enemyControllerLv2.getlistEnemyView().get(i).setPosition(enemyControllerLv2.getlistEnemy().get(i).getPrevx(), 
							enemyControllerLv2.getlistEnemy().get(i).getPrevy());
					
					flag=true;
					break;
    			}
			}
			
			if (enemyElem.getRect().getBoundsInLocal().intersects(heroController.getHeroView().getRect().getLayoutBounds())){
				enemyControllerLv2.getlistEnemyView().get(i).setPosition(enemyControllerLv2.getlistEnemy().get(i).getPrevx(), 
						enemyControllerLv2.getlistEnemy().get(i).getPrevy());
				count++;
				this.updateLife();
				flag=true;
				break;
			}
		}
		return flag;
	}
    
    @Override
    public void chekDir(double xHero, double yHero, double prevX, double prevY) {
		if (xHero==prevX) {
			if (prevY < yHero) {
				this.flagdown = false;
				this.flagup = true;
				this.flagdx = true;
				this.flagsx = true;
			} else if (prevY > yHero) {
				this.flagdown = true;
				this.flagup = false;
				this.flagdx = true;
				this.flagsx = true;
			}
		} else if (prevY == yHero) {
			if (prevX < xHero) {
				this.flagdown = true;
				this.flagup = true;
				this.flagdx = false;
				this.flagsx = true;
			} else if(prevX > xHero) {
				this.flagdown = true;
				this.flagup = true;
				this.flagdx = true;
				this.flagsx = false;
			}
		}
	}


    @Override
	public void updateLife() {
		if (count > 10) {
			heroController.getHeroModel().downLife();
			gvm.updateLife(heroController.getHeroModel().getLife());
			count =0;
		}
	}

	@Override
	public void upPressed() {
		if (flagup) {
			try {
				gameLoopLv2.upEvent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.flagdown=true;
		this.flagdx=true;
		this.flagsx=true;
	}

	@Override
	public void rightPressed() {
		if (flagdx) {
			try {
				gameLoopLv2.rightEvent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.flagdown=true;
		this.flagup=true;
		this.flagsx=true;
	}

	@Override
	public void leftPressed() {
		if (flagsx) {
			try {
				gameLoopLv2.leftEvent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.flagdown=true;
		this.flagdx=true;
		this.flagup=true;
	}

	@Override
	public void downPressed() {
		if (flagdown) {
			try {
				gameLoopLv2.downEvent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.flagup=true;
		this.flagdx=true;
		this.flagsx=true;
	}


	@Override
	public void click(double mousex, double mousey) {
		try {
			gameLoopLv2.clickEvent(mousex, mousey);
			this.addProiettile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkNextLevel() {
		double xhero = heroController.getHeroView().getRect().getX();
		double yhero = heroController.getHeroView().getRect().getY();
		if (xhero<=gvm.getWidth() && xhero>gvm.getWidth()-50) {
			if (yhero < 600 && yhero >300) {
				System.out.println("finish");
				this.stage.close();
				heroController.getHeroView().setPosition(0, 0);
				new ThirdLevel();
			}
		}
	}

	@Override
	public void checkEnd() {
		if (heroController.getHeroModel().getLife()<=0) {
			this.stage.close();
		}
	}

}
