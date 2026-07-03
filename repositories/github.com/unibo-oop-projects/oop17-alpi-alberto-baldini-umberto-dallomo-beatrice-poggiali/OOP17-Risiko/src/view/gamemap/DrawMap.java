package view.gamemap;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import data.PositionCoordinates;
import lands.Lands.MyJButton;
/**
 * This class draws the world map with the colored buttons related to players
 *
 */
public class DrawMap {
	private final static int ONE=1;
	
	public static void setMap(JPanel w,LinkedList<MyJButton> terr) {

		int index=0;
		GridBagLayout layout=new GridBagLayout();
		GridBagConstraints c=new GridBagConstraints();
		w.setLayout(layout);
		
	    //alaska
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.ALASKA.getGridx();
		c.gridy=PositionCoordinates.ALASKA.getGridy();
		c.anchor=GridBagConstraints.SOUTH;
		c.weightx=ONE;
		c.weighty=ONE;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//nordwestterritories 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.NORTHWESTTERRITORIES.getGridx();
		c.gridy=PositionCoordinates.NORTHWESTTERRITORIES.getGridy();
		c.anchor=GridBagConstraints.SOUTH;
		layout.setConstraints(terr.get(index) , c);
		w.add(terr.get(index));
		index++;
		
		//greenland
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.GREENLAND.getGridx();
		c.gridy=PositionCoordinates.GREENLAND.getGridy();
		c.anchor=GridBagConstraints.SOUTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//alberta
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.ALBERTA.getGridx();
		c.gridy=PositionCoordinates.ALBERTA.getGridy();
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//ontario 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.ONTARIO.getGridx();
		c.gridy=PositionCoordinates.ONTARIO.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//quebec 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.QUEBEC.getGridx();
		c.gridy=PositionCoordinates.QUEBEC.getGridy();
		c.anchor=GridBagConstraints.WEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//eastern united states 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.EASTERNUNITEDSTATES.getGridx();
		c.gridy=PositionCoordinates.EASTERNUNITEDSTATES.getGridy();
		c.anchor=GridBagConstraints.SOUTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//western united states 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.WESTERNUNITEDSTATES.getGridx();
		c.gridy=PositionCoordinates.WESTERNUNITEDSTATES.getGridy();
		c.anchor=GridBagConstraints.NORTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//central america 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.CENTRALAMERICA.getGridx();
		c.gridy=PositionCoordinates.CENTRALAMERICA.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;

		//venezuela 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.VENEZUELA.getGridx();
		c.gridy=PositionCoordinates.VENEZUELA.getGridy();
		c.anchor=GridBagConstraints.SOUTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//peru 
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.PERU.getGridx();
		c.gridy=PositionCoordinates.PERU.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//brazil
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.BRAZIL.getGridx();
		c.gridy=PositionCoordinates.BRAZIL.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//argentina
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.ARGENTINA.getGridx();
		c.gridy=PositionCoordinates.ARGENTINA.getGridy();
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//iceland
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.ICELAND.getGridx();
		c.gridy=PositionCoordinates.ICELAND.getGridy();
		c.anchor=GridBagConstraints.SOUTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//great britain
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.GREITBRITAIN.getGridx();
		c.gridy=PositionCoordinates.GREITBRITAIN.getGridy();
		c.anchor=GridBagConstraints.WEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//scandinavia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.SCANDINAVIA.getGridx();
		c.gridy=PositionCoordinates.SCANDINAVIA.getGridy();
		c.anchor=GridBagConstraints.NORTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//ukraine
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.UKRAINE.getGridx();
		c.gridy=PositionCoordinates.UKRAINE.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//western europe
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.WESTERNEUROPE.getGridx();
		c.gridy=PositionCoordinates.WESTERNEUROPE.getGridy();
		c.anchor=GridBagConstraints.NORTHWEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//northern europe
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.NORTHERNEUROPE.getGridx();
		c.gridy=PositionCoordinates.NORTHERNEUROPE.getGridy();
		c.anchor=GridBagConstraints.SOUTHWEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//southern europe
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.SOUTHERNEUROPE.getGridx();
		c.gridy=PositionCoordinates.SOUTHERNEUROPE.getGridy();
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//northern africa
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.NORTHERNAFRICA.getGridx();
		c.gridy=PositionCoordinates.NORTHERNAFRICA.getGridy();
		c.anchor=GridBagConstraints.NORTHWEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//egypt
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.EGYPT.getGridx();
		c.gridy=PositionCoordinates.EGYPT.getGridy();
		c.anchor=GridBagConstraints.NORTHWEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//congo
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.CONGO.getGridx();
		c.gridy=PositionCoordinates.CONGO.getGridy();
		c.anchor=GridBagConstraints.SOUTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//eastern africa
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.EASTERNAFRICA.getGridx();
		c.gridy=PositionCoordinates.EASTERNAFRICA.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//southern africa
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.SOUTHERNAFRICA.getGridx();
		c.gridy=PositionCoordinates.SOUTHERNAFRICA.getGridy();
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//madagascar
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.MADAGASCAR.getGridx();
		c.gridy=PositionCoordinates.MADAGASCAR.getGridy();
		c.anchor=GridBagConstraints.NORTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//siberia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.SIBERIA.getGridx();
		c.gridy=PositionCoordinates.SIBERIA.getGridy();
		c.anchor=GridBagConstraints.NORTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//jacuzia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.JACUZIA.getGridx();
		c.gridy=PositionCoordinates.JACUZIA.getGridy();
		c.anchor=GridBagConstraints.SOUTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//kamchatka
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.KAMCHATKA.getGridx();
		c.gridy=PositionCoordinates.KAMCHATKA.getGridy();
		c.anchor=GridBagConstraints.SOUTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//urals
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.URALS.getGridx();
		c.gridy=PositionCoordinates.URALS.getGridy();
		c.anchor=GridBagConstraints.WEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//cita
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.CITA.getGridx();
		c.gridy=PositionCoordinates.CITA.getGridy();
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//japan
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.JAPAN.getGridx();
		c.gridy=PositionCoordinates.JAPAN.getGridy();
		c.anchor=GridBagConstraints.WEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//afghanistan
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.AFGHANISTAN.getGridx();
		c.gridy=PositionCoordinates.AFGHANISTAN.getGridy();
		c.anchor=GridBagConstraints.NORTHWEST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//mongolia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.MONGOLIA.getGridx();
		c.gridy=PositionCoordinates.MONGOLIA.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//china
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.CHINA.getGridx();
		c.gridy=PositionCoordinates.CHINA.getGridy();
		c.anchor=GridBagConstraints.NORTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//middle east
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.MIDDLEEAST.getGridx();
		c.gridy=PositionCoordinates.MIDDLEEAST.getGridy();
		c.anchor=GridBagConstraints.SOUTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//india
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.INDIA.getGridx();
		c.gridy=PositionCoordinates.INDIA.getGridy();
		c.anchor=GridBagConstraints.SOUTHEAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//siam
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.SIAM.getGridx();
		c.gridy=PositionCoordinates.SIAM.getGridy();
		c.anchor=GridBagConstraints.SOUTH;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//indonesia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.INDONESIA.getGridx();
		c.gridy=PositionCoordinates.INDONESIA.getGridy();
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//new guinea
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.NEWGUINEA.getGridx();
		c.gridy=PositionCoordinates.NEWGUINEA.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//western australia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.WESTERNAUSTRALIA.getGridx();
		c.gridy=PositionCoordinates.WESTERNAUSTRALIA.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));
		index++;
		
		//eastern australia
		terr.get(index).setBorderPainted(false);
		terr.get(index).setBackground(terr.get(index).getColor());
		c.gridx=PositionCoordinates.EASTERNAUSTRALIA.getGridx();
		c.gridy=PositionCoordinates.EASTERNAUSTRALIA.getGridy();
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(terr.get(index), c);
		w.add(terr.get(index));	
	}

}
