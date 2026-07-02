package tableModel;

import model.IModel;
import model.MyTableModel;
import model.Player;
import model.StatisticModel;
import model.Team;
/**
 * Specific class that extends the tables for the match view
 * @author francesco
 *
 */
public class MyMatchModel extends MyTableModel{

	private Team team;
	private StatisticModel statmodel;

	public MyMatchModel(IModel model, Team team, StatisticModel statmod) {
		super(model);
		this.team = team;
		this.statmodel  = statmod;
		setColumnNames(new String[]{"Player","P","OffR","DefR","Ass","Blo","Foul","To","Ste"});
		
	}

	@Override
	public int getRowCount() {
		return team.getPlayers().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
			Player player = team.getPlayers().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return	player.getName() + " " + player.getSurname();
			case 1 :
				return statmodel.getStatistic(player).getPoints();
			case 2 : 
				return statmodel.getStatistic(player).getOffRebounds();
			case 3 : 
				return statmodel.getStatistic(player).getDefRebounds();
			case 4 : 
				return statmodel.getStatistic(player).getAssists();
			case 5 : 
				return statmodel.getStatistic(player).getBlocks();
			case 6 : 
				return statmodel.getStatistic(player).getPersonalFouls();
			case 7 :
				return statmodel.getStatistic(player).getTurnovers();
			case 8 :
				return statmodel.getStatistic(player).getSteals();
			default : 
				return null;
		}
	}
}
