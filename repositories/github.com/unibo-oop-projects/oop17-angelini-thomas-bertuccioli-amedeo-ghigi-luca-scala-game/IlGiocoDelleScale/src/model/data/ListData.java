package model.data;

import model.dice.*;
import java.util.List;

public interface ListData {

	Data classicMode();
	
	Data personalizedMode(int cellNumber,List<Dice> diceList);
	
}
