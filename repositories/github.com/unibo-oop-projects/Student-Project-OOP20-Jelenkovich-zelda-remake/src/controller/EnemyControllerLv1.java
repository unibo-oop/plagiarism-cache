package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import model.people.Enemy;
import view.EnemyView;

public class EnemyControllerLv1 implements IenemyController{
	
	private static final String IMG_PATH_ENEMY = "res/img/enemy.png";
	private final List<Enemy> listEnemy;
    private final List<EnemyView> listEnemyView;
	
	public EnemyControllerLv1() {
		this.listEnemy = new ArrayList<>();
		this.listEnemyView = new LinkedList<>();
		this.createEnemy();
	}
	
	@Override
	public List<EnemyView> startEnemyView() {
    	return this.listEnemy.stream().map((element) -> {
            EnemyView enemy = new EnemyView();
            enemy.setImg(IMG_PATH_ENEMY);
            enemy.setEnemyDim(element.getEnemyWidth(), element.getEnemyHeight());
            enemy.setPosition(element.getX(), element.getY());
            return enemy;
        }).collect(Collectors.toList());
    }
	
	@Override
	public List<Enemy> createEnemy() {
		Enemy enemy = new Enemy(420, 400);
		this.listEnemy.add(enemy);
		Enemy enemy1 = new Enemy(500, 120);
		this.listEnemy.add(enemy1);
		return listEnemy;
	}
	
	@Override
	public List<Node> getNodeList() {
		this.listEnemyView.addAll(this.startEnemyView());
    	List<Node> list = new LinkedList<>();
        for (EnemyView o : listEnemyView) {
            list.add(o.getRect());
        }
        return list;
	}

	@Override
	public void move(int xhero, int yhero) {
		double q;
		double m;
		double xenemy;
		double yenemy;
		double newx;
		double newy;
		int index;
		for (EnemyView enemy : listEnemyView) {
			xenemy = (int)enemy.getRect().getX();
			yenemy = (int)enemy.getRect().getY();
			m = ((yhero - yenemy)/(xhero - xenemy));
			q = (yhero-(m*xhero));
			if (xhero<=xenemy) {
				newx = xenemy-0.2;
				newy = ((m*newx) + q);
			} else {
				if (yhero<yenemy) {
					newy = yenemy-0.2;
					newx = ((newy-q)/m);
				} else {
					newx = xenemy+1;
					newy = ((m*newx) + q);
				}
			}
			index = listEnemyView.indexOf(enemy);
			listEnemy.get(index).setPrevPos(listEnemyView.get(index).getRect().getX(), listEnemyView.get(index).getRect().getY());
			enemy.setPosition((int)newx, (int)newy);
		}
	}	
	
	@Override
	public List<EnemyView> getlistEnemyView() {
		return this.listEnemyView;
	}

	@Override
	public void deleteEnemy (int index) {
		listEnemy.remove(index);
		listEnemyView.remove(index);
	}
	
	@Override
	public List<Enemy> getlistEnemy() {
		return this.listEnemy;
	}


}
