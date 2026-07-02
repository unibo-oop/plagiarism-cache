package model.board;

import java.util.List;
import java.util.Optional;

public class TableImpl {
	
	private final List<UpsideDown> udList;
	private final int height;
	private final int width;
	
	public TableImpl(List<UpsideDown> udList, int height, int width) {
		this.udList = udList;
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public boolean isCellEmpty(Coordinate cell) {
		return !udList.stream().anyMatch(x -> x.isInPosition(cell));
	}
	
	public Optional<Coordinate> getNewPosition(Coordinate start) {
		return udList.stream().filter(x -> x.isInPosition(start)).map(x -> x.getTarget()).findFirst();
	}

}
