package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.ApplicationImpl;

/**
 * This class creates the page that shows the match statics
 */
public class MatchStatic extends JFrame {
   
	private static final long serialVersionUID = 1L;
	private JTable table;

    public MatchStatic(int nPlayers, ApplicationImpl appC, boolean close, JFrame back) {
    	
    JFrame modalita = new JFrame();
    modalita.addWindowListener(new WindowAdapter() {		
    	public void windowClosing (WindowEvent e)
    	{
			back.setEnabled(true);
			back.requestFocus();
    	}
    });
	
    int x = 80;
	int y = 65;

	JButton exit =  new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/close.png")));
	exit.setLocation(0, 215);
	exit.setSize(162, 50);
	exit.setRolloverEnabled(true);
	exit.setFocusPainted(false);
	exit.setBorderPainted(false);
	exit.setContentAreaFilled(false);
	exit.addActionListener(l -> {
		//if close is true means that the match is finish
		if (close){
			System.exit(0);
		} else {
			modalita.dispose();
			back.setEnabled(true);
			back.requestFocus();
		}
	});
	modalita.getContentPane().add(exit);
    
	if (nPlayers == 4) {
	    modalita.setSize(844, 547);
	    exit.setLocation(650, 450);
	} else {
	    modalita.setSize(844, 750);
	    exit.setLocation(650, 650);
	}

	modalita.getContentPane().setLayout(null);
	modalita.getContentPane().setBackground(Color.LIGHT_GRAY);
	modalita.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	modalita.addWindowListener(new WindowAdapter() {
    	public void windowClosing (WindowEvent e)
    	{	
    		modalita.dispose();
    	}
    });
    
	for (int i = 0; i < nPlayers; i++) {
	    JPanel panel = new JPanel();
	    panel.setBounds(x, y, 250, 170);
	    modalita.getContentPane().add(panel);
	    panel.setLayout(null);

	    JLabel player1 = new JLabel(appC.getPlayers().get(i).getName().toString());
	    player1.setFont(new Font("Talking to the Moon", Font.BOLD, 22));
	    player1.setBounds(12, 0, 232, 30);
	    panel.add(player1);
	    
	    table = new JTable(8, 2);
	    table.setModel(new DefaultTableModel(
		    new Object[][] {
			{"Color token: ", appC.getPlayers().get(i).getColor().toString()},
			{"N\u00B0 token arrived", appC.getPlayers().get(i).getnTokenArrived()},
			{"N\u00B0 token at home", appC.getPlayers().get(i).getnTokenAtHome()},
			{"N\u00B0 opponents' tokens ate", appC.getPlayers().get(i).getNTimesHasEated()},
			{"N\u00B0 my tokens ate", appC.getPlayers().get(i).getNTimesHasBeenEating()},	
		    },
		    new String[] {
			    "A", "B"
		    }
		    ));
	    table.getColumnModel().getColumn(0).setPreferredWidth(156);
	    table.getColumnModel().getColumn(1).setPreferredWidth(52);
	    table.setEnabled(false);
	    table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    table.setBounds(12, 39, 232, 126);
	    table.getTableHeader().setVisible(false);


	    panel.add(table);
	    //set the coordinates of the tables
	    switch (i) {
	    case 0 : x = 80; y = 260; break;
	    case 1 : x = 460 ; y = 65; break;
	    case 2 : x = 460 ; y = 260; break;
	    case 3 : x = 80; y = 455; break;
	    case 4 : x = 460; y = 455; break;
	    default : break;
	    }
	}

	JLabel title = new JLabel("Match Statics");
	title.setFont(new Font("Talking to the Moon", Font.BOLD, 32));
	title.setBounds(324, 13, 260, 41);
	modalita.getContentPane().add(title);
	modalita.setLocationRelativeTo(null);
	modalita.setResizable(false);
	modalita.setVisible(true);	
    }
   
}
