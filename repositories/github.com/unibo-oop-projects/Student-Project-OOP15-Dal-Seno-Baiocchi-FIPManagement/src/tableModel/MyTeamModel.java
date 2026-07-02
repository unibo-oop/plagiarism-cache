package tableModel;

import model.Championship;
import model.IModel;
import model.MyTableModel;
import model.Team;
/**
 * Specific class that extends the table of the teams into a championship
 * @author francesco
 *
 */
public class MyTeamModel extends MyTableModel {

	private Championship champ;

	public MyTeamModel(IModel model, Championship champ) {
		super(model);
		setColumnNames(new String []{"Name","Home Jersey", "Transfer Jersey", "Company", "VAT"});
		this.champ = champ;
	}

	@Override
	public int getRowCount() {
		return model.getTeam(champ).size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Team team = ((Team)model.getTeam(champ).toArray()[rowIndex]);
		switch (columnIndex){
		case 0 :
			return team.getName();
		case 1 : 
			return team.getHomeJerseyColour();
		case 2 : 
			return team.getTransferJerseyColour();
		case 3 : 
			return team.getCompany();
		case 4 : 
			return team.getVatNumber();
		}
		return null;
	}

}
