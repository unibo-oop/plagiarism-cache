package data;
/**
 * 
 * This enumeration indicates the buttons' coordinates in a GridBagLayout related to each state on the map
 *
 */
public enum PositionCoordinates {
	ALASKA(0,0),
	NORTHWESTTERRITORIES(1,0),
	GREENLAND(2,0),
	ALBERTA(0,1),
	ONTARIO(1,1),
	QUEBEC(2,1),
	EASTERNUNITEDSTATES(0,1),
	WESTERNUNITEDSTATES(1,2),
	CENTRALAMERICA(0,2),
	VENEZUELA(0,2),
	PERU(0,3),
	BRAZIL(1,3),
	ARGENTINA(0,4),
	ICELAND(3,0),
	SCANDINAVIA(3,1),
	GREITBRITAIN(3,1),
	WESTERNEUROPE(3,2),
	NORTHERNEUROPE(4,1),
	SOUTHERNEUROPE(3,2),
	URALS(5,1),
	UKRAINE(4,1),
	AFGHANISTAN(5,2),
	JACUZIA(5,0),
	CITA(5,1),
	MONGOLIA(6,1),
	KAMCHATKA(6,0),
	CHINA(6,2),
	JAPAN(7,1),
	SIAM(6,2),
	INDONESIA(6,3),
	NEWGUINEA(7,3),
	WESTERNAUSTRALIA(6,4),
	EASTERNAUSTRALIA(7,4),
	INDIA(5,2),
	MIDDLEEAST(4,2),
	NORTHERNAFRICA(3,3),
	EGYPT(4,3),
	EASTERNAFRICA(4,3),
	CONGO(3,3),
	SOUTHERNAFRICA(3,4),
	MADAGASCAR(4,4),
	SIBERIA(5,1);
	
	private int gridx;
	private int gridy;
	
	PositionCoordinates(int gridx,int gridy) {
		this.gridx=gridx;
		this.gridy=gridy;
	}

	public int getGridx() {
		return gridx;
	}

	public int getGridy() {
		return gridy;
	}
}
