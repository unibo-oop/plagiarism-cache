package tableModel;

import model.Championship;
import model.IModel;
import model.MyTableModel;
/**
 * Specific class that extends the table for the championship
 * @author francesco
 *
 */
public class MyChampionshipModel extends MyTableModel{

	public MyChampionshipModel(IModel model) {
		super(model);
		setColumnNames(new String []{"Championship", "Zone"});
	}

	@Override
	public int getRowCount() {
		return model.getChampionship().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Championship ch = (Championship) model.getChampionship().toArray()[rowIndex];
		if(columnIndex == 0){
			return ch.getDivision().toString();	
		}else{
			return ch.getZone().toString();
		}
	}

}
