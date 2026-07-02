package model.wave;

import java.util.ArrayList;
import model.enemy.Enemy;
import model.enemy.EnemyImpl;
import model.enemy.EnemyType;
import model.map.MapTile;


public class WaveImpl implements Wave{
	
	static ArrayList<MapTile> path = new ArrayList<>();
	
	private ArrayList<Enemy> ondata;
	private int Numero_Ondata;

	public WaveImpl(int numero_Ondata) {
		super();
		ondata = new ArrayList<>();
		Numero_Ondata = numero_Ondata;
		populate((1+numero_Ondata)*2, EnemyType.SIMPLE);
		
		
	}

	@Override
	public int getWave() {
		return this.Numero_Ondata;
	}

	@Override
	public void populate(int quantity, EnemyType type) {
		for(int a=0; a<quantity; a++) {
				Enemy e = new EnemyImpl(type);
				e.setPath(path);
				ondata.add(e);
		}
	}
	
	@Override
	public Enemy spawn() {
		Enemy e = null;
		if(!ondata.isEmpty()) {
		e = ondata.get(0);
		e.spawn();
		ondata.remove(0);
		return e;
		}
		throw new NullPointerException();
	}
	
	@Override
	public boolean hasEnemies() {
		return !ondata.isEmpty();
	}
	
	@Override
	public Wave nextWave() {
		return new WaveImpl(this.getWave()+1);
	}
	
	static public void setPath(ArrayList<MapTile> path) {
		WaveImpl.path=path;
	}
}
