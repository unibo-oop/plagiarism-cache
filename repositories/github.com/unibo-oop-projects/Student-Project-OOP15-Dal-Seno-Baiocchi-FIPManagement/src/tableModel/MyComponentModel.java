package tableModel;

import java.text.SimpleDateFormat;

import model.MyTableModel;
import model.Player;
import model.Staff;
import model.Team;
/**
 * Specific class that extends the table of the components of a team
 * @author francesco
 *
 */
public class MyComponentModel extends MyTableModel{

	private Team team;
	private CompononentType type;
	
	public static enum CompononentType {STAFF, PLAYER}

	public MyComponentModel(Team team, CompononentType type) {
		super(null);
		this.type = type;
		this.team = team;
		if(type == CompononentType.PLAYER){
			setColumnNames(new String[]{"Name","Surname","Birth","CF","Height","Role"});
		}else{
			setColumnNames(new String[]{"Name","Surname","Birth","CF","Role"});
		}
	}
	
	@Override
	public int getRowCount() {		
		return type == CompononentType.PLAYER ? team.getPlayers().size() : team.getStaff().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		if(type == CompononentType.PLAYER){		
			Player player = team.getPlayers().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return	player.getName();
			case 1 : 
				return player.getSurname();
			case 2 : 
				return player.getHeight();
			case 3 : 
				return player.getCF();
			case 4 : 
				return player.getRole();
			case 5 : 
				return ft.format(player.getBirth());
			default : 
				return null;
			}
		}else{
			Staff staff = team.getStaff().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return	staff.getName();
			case 1 : 
				return staff.getSurname();
			case 2 : 
				return staff.getCF();
			case 3 : 
				return staff.getRole();
			case 4 : 
				return ft.format(staff.getBirth());
			default : 
				return null;
			}
		}
	}

}
