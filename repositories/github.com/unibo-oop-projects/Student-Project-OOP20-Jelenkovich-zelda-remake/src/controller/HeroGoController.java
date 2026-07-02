package controller;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.*;
import java.io.IOException;

public class HeroGoController implements ViewObserver, IheroGoController{
	
	private static final int INIT_Y = 225;
    private static final int INIT_X = 20;
	private final IobjectController objControllerLv1;
    private final HeroController heroController;
    private final IenemyController enemyControllerLv1;
    private final ProiettileController proiettileController;
    private final GameViewManager gvm;
    private final GameLoop gameLoop;
    private final Stage stage;
    private int count;
    
    private boolean flagdx = true;
    private boolean flagsx = true;
    private boolean flagup = true;
    private boolean flagdown = true;
    
    public HeroGoController (final Stage firstStage){
    	this.stage=firstStage;
    	this.objControllerLv1 = new ObjectControllerLv1();
    	this.proiettileController = new ProiettileController();
    	this.heroController = new HeroController(INIT_X, INIT_Y);
    	this.enemyControllerLv1 = new EnemyControllerLv1();
    	this.gvm = new GameViewManager(this, firstStage);
    	gvm.initStage(heroController.getHeroModel().getLife());
    	gameLoop = new GameLoop(this, firstStage, heroController, proiettileController, enemyControllerLv1);
    }

	@Override
	public void newGame() {
		this.addNode(heroController.getHeroView().getRect());
        for (Node n : objControllerLv1.getNodeList()) {
            this.addNode(n);
        }
        for (Node n : enemyControllerLv1.getNodeList()) {
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
        return this.objControllerLv1;
    }

    @Override
    public HeroController getHeroController() {
        return heroController;
    }
    
    public IenemyController getEnemyController() {
    	return enemyControllerLv1;
    }

    @Override
    public void checkCollision(int prevX, int prevY) throws IOException {
		double xHero = 0;
		double yHero = 0;
    	for (Node element : objControllerLv1.getNodeList()) {
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
    		for (StaticObjView tree : objControllerLv1.getObjListViewTree()) {
    			if (p.getRect().getBoundsInLocal().intersects(tree.getObj().getBoundsInLocal())) {
    				proiettileController.getlistProiettileView().get(i).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(i);
    				flag = true;
    				break;
    			}
    		}
    		for (StaticObjView stone : objControllerLv1.getObjListViewStone()) {
    			if (p.getRect().getBoundsInLocal().intersects(stone.getObj().getBoundsInLocal())) {
    				proiettileController.getlistProiettileView().get(i).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(i);
    				flag = true;
    				break;
    			}
    		}
    		i=-1;
    		for (EnemyView enemy : enemyControllerLv1.getlistEnemyView()) {
    			i++;
    			int ip=-1;
    			if (p.getRect().getBoundsInLocal().intersects(enemy.getRect().getBoundsInLocal())){
    				ip++;
    				proiettileController.getlistProiettileView().get(ip).setProiettileDim(0, 0);
    				proiettileController.deleteProiettile(ip);
    				enemy.setImg("res/img/deadEnemy.png");
    				enemy.setEnemyDim(25, 25);
    				enemyControllerLv1.deleteEnemy(i);
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
		for(EnemyView enemyElem : enemyControllerLv1.getlistEnemyView()) {
			i++;
			for (StaticObjView element : objControllerLv1.getObjListViewStone()) {
				if (element.getObj().intersects(enemyElem.getRect().getLayoutBounds())){
					enemyControllerLv1.getlistEnemyView().get(i).setPosition(enemyControllerLv1.getlistEnemy().get(i).getPrevx(), 
													enemyControllerLv1.getlistEnemy().get(i).getPrevy());
					
					flag = true;
					break;
    			}
			}
			
			for (StaticObjView element : objControllerLv1.getObjListViewTree()) {
				if (element.getObj().intersects(enemyElem.getRect().getLayoutBounds())){
					enemyControllerLv1.getlistEnemyView().get(i).setPosition(enemyControllerLv1.getlistEnemy().get(i).getPrevx(), 
							enemyControllerLv1.getlistEnemy().get(i).getPrevy());
					
					flag=true;
					break;
    			}
			}
			
			if (enemyElem.getRect().getBoundsInLocal().intersects(heroController.getHeroView().getRect().getLayoutBounds())){
				enemyControllerLv1.getlistEnemyView().get(i).setPosition(enemyControllerLv1.getlistEnemy().get(i).getPrevx(), 
				enemyControllerLv1.getlistEnemy().get(i).getPrevy());
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
				gameLoop.upEvent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				gameLoop.rightEvent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				gameLoop.leftEvent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				gameLoop.downEvent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
			gameLoop.clickEvent(mousex, mousey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addProiettile();
	}

	@Override
	public void checkNextLevel() {
		double xhero = heroController.getHeroView().getRect().getX();
		double yhero = heroController.getHeroView().getRect().getY();
		if (xhero<=gvm.getWidth() && xhero>gvm.getWidth()-50) {
			if (yhero < 500 && yhero > 450) {
				this.stage.close();
				heroController.getHeroView().setPosition(0, 0);
				new SecondLevel();
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
