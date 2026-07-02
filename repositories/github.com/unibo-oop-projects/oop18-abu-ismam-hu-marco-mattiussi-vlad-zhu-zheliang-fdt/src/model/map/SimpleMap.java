package model.map;

import model.map.MapTile.Status;

/**
 * 
 * @author ismam
 *
 */
public class SimpleMap extends AbstractMapModel {

	@Override	//ogni coppia è scritta come (x,y)
	void generatePath() {
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 5,0 a 5,10 da sx a dx
			if (grid.get(i).getPosition().getX()==5 && grid.get(i).getPosition().getY()<=10) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 5,10 a 10,10 da sopra a sotto
			if (grid.get(i).getPosition().getX()>=5 && grid.get(i).getPosition().getX()<=10 && grid.get(i).getPosition().getY()==10) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 10,10 a 10,5 da dx a sx
			if (grid.get(i).getPosition().getX()==10 && grid.get(i).getPosition().getY()>=5 && grid.get(i).getPosition().getY()<=10) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 10,5 a 15,5 da sopra a sotto
			if (grid.get(i).getPosition().getX()>=10 && grid.get(i).getPosition().getX()<=15 && grid.get(i).getPosition().getY()==5) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 15,5 a 15,15 da sx a dx
			if (grid.get(i).getPosition().getX()==15 && grid.get(i).getPosition().getY()>=5 && grid.get(i).getPosition().getY()<=15) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 15,15 a 2,15 da sotto a sopra
			if (grid.get(i).getPosition().getX()>=2 && grid.get(i).getPosition().getX()<=15 && grid.get(i).getPosition().getY()==15) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		for (int i=0;i<gridSize*gridSize;i++) {			// da 2,15 a 2,20 da sx a dx
			if (grid.get(i).getPosition().getX()==2 && grid.get(i).getPosition().getY()>=15) {
				grid.get(i).setStatus(Status.PATH);
				enemyPath.add(grid.get(i));	//aggiungo alla lista il path
			}
		}
		
		
	}

}
