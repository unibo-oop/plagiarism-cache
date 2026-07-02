package controller;

import java.io.IOException;

import javafx.scene.Node;
import javafx.stage.Stage;
import view.EnemyView;
import view.GameViewManager;
import view.ProiettileView;
import view.StaticObjView;
import view.ViewObserver;

public class HeroGoControllerLv3 implements ViewObserver, IheroGoController{
	
	private static final int INIT_Y = 480;
    private static final int INIT_X = 20;
	private final IobjectController objControllerLv3;
    private final HeroController heroController;
    private final EnemyControllerLv3 enemyControllerLv3;
    private final ProiettileController proiettileController;
    private final GameViewManager gvm;
    private final GameLoopLv3 gameLoopLv3;
    private final Stage stage;
    private int count;
    
    private boolean flagdx = true;
    private boolean flagsx = true;
    private boolean flagup = true;
    private boolean flagdown = true;

	public HeroGoControllerLv3(Stage stage) {
		this.stage = stage;
		this.objControllerLv3 = new ObjectControllerLv3();
    	this.proiettileController = new ProiettileController();
    	this.heroController = new HeroController(INIT_X, INIT_Y);
    	this.enemyControllerLv3 = new EnemyControllerLv3();
    	this.gvm = new GameViewManager(this, stage);
    	gvm.thirdStage(heroController.getHeroModel().getLife());
    	gameLoopLv3 = new GameLoopLv3(this, stage, heroController, proiettileController, enemyControllerLv3);
	}

	@Override
	public void newGame() {
		this.addNode(heroController.getHeroView().getRect());
        for (Node n : objControllerLv3.getNodeList()) {
            this.addNode(n);
        }
        for (Node n : enemyControllerLv3.getNodeList()) {
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
        return this.objControllerLv3;
    }

    @Override
    public HeroController getHeroController() {
        return heroController;
    }
    
    @Override
    public IenemyController getEnemyController() {
    	return enemyControllerLv3;
    }
    
    @Override
    public void checkCollision(int prevX, int prevY) throws IOException {
		double xHero = 0;
		double yHero = 0;
    	for (Node element : objControllerLv3.getNodeList()) {
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
    		for (StaticObjView tree : objControllerLv3.getObjListViewTree()) {
    			if (p.getRect().getBoundsInLocal().intersects(tree.getObj().getBoundsInLocal())) {
    				proiettileController.getlistProiettileView().get(i).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(i);
    				flag = true;
    				break;
    			}
    		}
    		for (StaticObjView stone : objControllerLv3.getObjListViewStone()) {
    			if (p.getRect().getBoundsInLocal().intersects(stone.getObj().getBoundsInLocal())) {
    				proiettileController.getlistProiettileView().get(i).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(i);
    				flag = true;
    				break;
    			}
    		}
    		i=-1;
    		for (EnemyView enemy : enemyControllerLv3.getlistEnemyView()) {
    			i++;
    			int ip=-1;
    			if (p.getRect().getBoundsInLocal().intersects(enemy.getRect().getBoundsInLocal())){
    				ip++;
    				proiettileController.getlistProiettileView().get(ip).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(ip);
    				enemy.setImg("res/img/deadEnemy.png");
    				enemy.setEnemyDim(25, 25);
    				enemyControllerLv3.deleteEnemy(i);
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
		for(EnemyView enemyElem : enemyControllerLv3.getlistEnemyView()) {
			i++;
			for (StaticObjView element : objControllerLv3.getObjListViewStone()) {
				if (element.getObj().intersects(enemyElem.getRect().getLayoutBounds())){
					enemyControllerLv3.getlistEnemyView().get(i).setPosition(enemyControllerLv3.getlistEnemy().get(i).getPrevx(), 
													enemyControllerLv3.getlistEnemy().get(i).getPrevy());
					
					flag = true;
					break;
    			}
			}
			
			for (StaticObjView element : objControllerLv3.getObjListViewTree()) {
				if (element.getObj().intersects(enemyElem.getRect().getLayoutBounds())){
					enemyControllerLv3.getlistEnemyView().get(i).setPosition(enemyControllerLv3.getlistEnemy().get(i).getPrevx(), 
							enemyControllerLv3.getlistEnemy().get(i).getPrevy());
					count++;
					this.updateLife();
					flag=true;
					break;
    			}
			}
			
			if (enemyElem.getRect().getBoundsInLocal().intersects(heroController.getHeroView().getRect().getLayoutBounds())){
				enemyControllerLv3.getlistEnemyView().get(i).setPosition(enemyControllerLv3.getlistEnemy().get(i).getPrevx(), 
						enemyControllerLv3.getlistEnemy().get(i).getPrevy());
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
			count=0;
		}
	}

	@Override
	public void upPressed() {
		if (flagup) {
			try {
				gameLoopLv3.upEvent();
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
				gameLoopLv3.rightEvent();
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
				gameLoopLv3.leftEvent();
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
				gameLoopLv3.downEvent();
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
			gameLoopLv3.clickEvent(mousex, mousey);
			this.addProiettile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkNextLevel() {
		double xhero = heroController.getHeroView().getRect().getX();
		double yhero = heroController.getHeroView().getRect().getY();
		if (xhero>0 && xhero<50) {
			if (yhero<350 && yhero>200) {
				this.stage.close();
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

