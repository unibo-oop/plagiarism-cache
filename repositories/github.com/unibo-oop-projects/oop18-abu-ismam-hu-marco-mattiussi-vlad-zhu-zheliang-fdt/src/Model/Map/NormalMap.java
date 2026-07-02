package model.map;

import model.map.MapTile.Status;

/**
 * normal map of the game
 */

public class NormalMap extends AbstractMapModel{

	@Override
	void generatePath() {

		for (int i=0;i<gridSize*gridSize;i++) {			// da 5,0 a 5,10 da sx a dx
			if (grid.get(i).getPosition().getX()==5 && grid.get(i).getPosition().getY()<=10) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		for (int i=0;i<gridSize*gridSize;i++) {			// da 5,10 a 15,10 da sopra a sotto
			if (grid.get(i).getPosition().getX()>=5 && grid.get(i).getPosition().getX()<=15 && grid.get(i).getPosition().getY()==10) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		for (int i=0;i<gridSize*gridSize;i++) {			// da 15,10 a 15,20 da sx a dx
			if (grid.get(i).getPosition().getX()==15 && grid.get(i).getPosition().getY()>=10) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
	}

}
