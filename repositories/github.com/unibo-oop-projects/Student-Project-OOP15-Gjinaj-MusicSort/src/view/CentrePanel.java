package view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.MainController;
import model.IPlaylist;
import model.CentreTableModel;
import view.RightClickCentreTable;

/**
 * 
 * @author rrok
 *
 */
public class CentrePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable showSongJTable;
	private CentreTableModel centreTableModel;
	private int rowSelected;
	
	public CentrePanel(MainController controller) {
		JScrollPane scrollPaneShowSongs = new JScrollPane();
		this.add(scrollPaneShowSongs, "");

		centreTableModel = new CentreTableModel();
		
		
		showSongJTable = new JTable();
		showSongJTable.setRowHeight(35);
		showSongJTable.setFont(new Font("Kokonor", Font.PLAIN, 16));
		Vector<String> columnNameS = new Vector<String>();
        columnNameS.add("Artist");
        columnNameS.add("Title");
        columnNameS.add("Album");
        
        centreTableModel.setColumnNames(columnNameS);
        
		showSongJTable.setModel(centreTableModel);
		showSongJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPaneShowSongs.setViewportView(showSongJTable);	
		
		showSongJTable.addMouseListener(new MouseAdapter(){
			

			@Override
			public void mousePressed(MouseEvent e){
		        if (e.isPopupTrigger()){
		            JTable source = (JTable)e.getSource();
		            rowSelected = source.rowAtPoint( e.getPoint() );
		            int column = source.columnAtPoint( e.getPoint() );
		            
		            if (! source.isRowSelected(rowSelected))
		                source.changeSelection(rowSelected, column, false, false);
		            System.out.println("riga numero "+rowSelected+" "+"colonna numero:"+ column);
		            doPop(e);
		        }
		    }

		    @Override
			public void mouseReleased(MouseEvent e){
		        if (e.isPopupTrigger()){
		            doPop(e);
		        }
		    }

		    private void doPop(MouseEvent e){
		        RightClickCentreTable menu = new RightClickCentreTable(controller,controller.getSongPosShowPlaylist(rowSelected));
		        menu.show(e.getComponent(), e.getX(), e.getY());
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		     // TODO Auto-generated method stub
		     super.mouseClicked(e);
		     JTable source = (JTable)e.getSource();
	            rowSelected = source.rowAtPoint( e.getPoint() );
	            int column = source.columnAtPoint( e.getPoint() );
	            if(e.getClickCount()==2){
	            	//se clicco due volte
		            if (!source.isRowSelected(rowSelected))
		                source.changeSelection(rowSelected, column, false, false);
		            controller.onSongDoubleClick(rowSelected);
		     }
	            
		    }
		});
		this.setLayout(new GridBagLayout());
	}
	
	public void setTableSongSelected(int index0) {
		// TODO Auto-generated method stub
		showSongJTable.setRowSelectionInterval(index0, index0);
	}
	
	public void changeModel(IPlaylist playlist){
		centreTableModel.refresh(playlist);
	}
}
