package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.ComponentController;
import model.IModel;
import model.Player;
import model.Staff;
import model.Team;
import observer.TeamComponentObserver;
import tableModel.MyComponentModel;
import tableModel.MyComponentModel.CompononentType;

/**
 * The team component view of the app
 * @author lucadalseno
 *
 */
public class TeamComponentView extends JFrame implements ObserverInterface<TeamComponentObserver>{

    /**
     * 
     */
    private static final long serialVersionUID = 6121024019716588087L;
    private JPanel contentPane;
    private JTable componentsTable;
    private JButton deleteComponent;
    private JButton addComponent;
    private JButton btnBack;
    private TeamComponentObserver observer;
    private JTable staffTable;
    private JButton removeStaff;
    private JLabel lblRoster;
    private JScrollPane playerScroll;
    private JScrollPane staffScroll;
    /**
     * Create the frame.
     */
    private TeamComponentView() {
        this.setTitle("Team Component View");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setResizable(false);
        setBounds(100, 100, 692, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        componentsTable = new JTable();
        componentsTable.setSelectionMode(0);
        
        addComponent = new JButton("Add Component");
        addComponent.setBounds(108, 415, 146, 29);
        contentPane.add(addComponent);
        
        deleteComponent = new JButton("Remove Player");
        deleteComponent.setBounds(476, 415, 124, 29);
        contentPane.add(deleteComponent);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(18, 477, 117, 29);
        contentPane.add(btnBack);
        
        JLabel lblTeamName = new JLabel("Name");
        lblTeamName.setBounds(108, 103, 50, 16);
        contentPane.add(lblTeamName);
        
        JLabel lblCompany = new JLabel("Role");
        lblCompany.setBounds(438, 103, 50, 16);
        contentPane.add(lblCompany);
        
        lblRoster = new JLabel();
        lblRoster.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lblRoster.setBounds(216, 23, 282, 53);
        contentPane.add(lblRoster);
        
        staffTable = new JTable();
        staffTable.setSelectionMode(0);
        
        removeStaff = new JButton("Remove Staff");
        removeStaff.setBounds(366, 415, 109, 29);
        contentPane.add(removeStaff);
        
        playerScroll = new JScrollPane(componentsTable);
        playerScroll.setBounds(108, 131, 492, 160);
        contentPane.add(playerScroll);
        
        staffScroll = new JScrollPane(staffTable);
        staffScroll.setBounds(108, 302, 492, 91);
        contentPane.add(staffScroll);
    }
    
    public TeamComponentView(final IModel model, final Team team,final CallBackInterface callback){
    	this();
    	lblRoster.setText(team.getName()+" ROSTER");
    	observer = new ComponentController(model, team);
    	componentsTable.setModel(new MyComponentModel(team, CompononentType.PLAYER));
    	staffTable.setModel(new MyComponentModel(team, CompononentType.STAFF));
    	staffTable.addMouseListener(new MouseAdapter() {
    	    public void mouseClicked(MouseEvent e){
    	        staffTable.repaint();
    	        if(e.getClickCount() == 2){
    	            int index = ((JTable)e.getSource()).getSelectedRow();
    	            Staff staff = team.getStaff().get(index);
    	            new StatisticView(staff,model).setVisible(true);;
    	        }
    	    }
        });
    	componentsTable.addMouseListener(new MouseAdapter(){
    		@Override
			public void mouseClicked(MouseEvent e) {
    			componentsTable.repaint();
				if(e.getClickCount() == 2){
					int index = ((JTable)e.getSource()).getSelectedRow();
					Player player = team.getPlayers().get(index);
					new StatisticView(player,model).setVisible(true);;
				}
			}
    	});
    	addComponent.addActionListener(e->{
    		AddComponent ac = new AddComponent();
    		ac.attachObserver(observer);
    		ac.setModal(true);
    		ac.setVisible(true);
                staffTable.setVisible(false);
                componentsTable.setVisible(false);
    		staffTable.setVisible(true);
    		componentsTable.setVisible(true);
    	});
    	
    	deleteComponent.addActionListener(e->{
    	 if(componentsTable.getSelectedRow() == -1){
             JOptionPane.showMessageDialog(this, "No Player is selected","Error",JOptionPane.ERROR_MESSAGE);
    	 } else if((JOptionPane.showConfirmDialog(this, "You want to delete this Player?",
                  "WARNING", JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.YES_OPTION){
    		int index = componentsTable.getSelectedRow();
    		if(index>=0){
			Player player = team.getPlayers().get(index);
	    		observer.removePlayer(player);
    		}
    	  }
    	  componentsTable.setVisible(false);
    	  componentsTable.setVisible(true);
    	});
    	
    	removeStaff.addActionListener(e->{
    	 if(staffTable.getSelectedRow() == -1){
             JOptionPane.showMessageDialog(this, "No Staff is selected","Error",JOptionPane.ERROR_MESSAGE);
    	 }else if((JOptionPane.showConfirmDialog(this, "You want to delete this Staff?",
                  "WARNING", JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.YES_OPTION){
    		int index = staffTable.getSelectedRow();
    		if(index>=0){
        		Staff staff = team.getStaff().get(index);
    			observer.removeStaff(staff);
    		}
    	  }
    	  staffTable.setVisible(false);
    	  staffTable.setVisible(true);
    	});
    	

    	btnBack.addActionListener(e->{
    	        callback.onClose();
    		this.setVisible(false);
    	});
    }
    
	@Override
	public void attachObserver(TeamComponentObserver observer) {
		this.observer = observer;
	}
}
