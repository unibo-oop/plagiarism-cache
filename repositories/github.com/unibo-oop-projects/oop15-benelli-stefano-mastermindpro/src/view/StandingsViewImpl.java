package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.data.PersistentException;
import model.players.Decoder;
import view.interfaces.StandingsView;
import view.interfaces.ViewBase;
import view.navigator.Navigator;
import view.utils.FrameHelper;

public class StandingsViewImpl extends ViewBase<List<Decoder>> implements StandingsView {

	private class StandingTableModel extends DefaultTableModel {

		private static final long serialVersionUID = -3304666153168267246L;

		public StandingTableModel(String[] columns) {
			super(columns, 0);
		}

		public StandingTableModel(String[][] data, String[] columns) {
			super(data, columns);
		}

		public boolean isCellEditable(int row, int column){  
			return false;  
		}
	}
	
	private static final long serialVersionUID = 402566154086993537L;

	private final Navigator navigator;
	
	private final String[] columnNames = {"Ranking",
							              "Username",
							              "No. Attempts"};
	
	private JTable table;
	
	public StandingsViewImpl(Navigator nav) {
		
		this.navigator = nav;
		
		FrameHelper.setupWindow(this, new Dimension(600,800));
		this.setTitle("Standings");

		table = new JTable(new StandingTableModel(columnNames));
		table.setFillsViewportHeight(true);
		
		this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(table.getTableHeader(), BorderLayout.NORTH);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				navigator.home();
        	}
        });
		
		this.pack();
	}
	
	@Override
	public void update(List<Decoder> decoders) {
		
		int ranking = 1;
		String[][] data = new String[decoders.size()][3];
		for(int i=0; i< decoders.size(); i++) {
			
			Decoder d = decoders.get(i);
			
			data[i][0] = Integer.toString(ranking);
			data[i][1] = d.getUsername();
			data[i][2] = Integer.toString(d.getRoundsSubmitted());
			
			ranking++;
		}
		
		table.setModel(new StandingTableModel(data, columnNames));
	}

	@Override
	public void fillModel(List<Decoder> t) {
		
	}

}
