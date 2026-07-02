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

import model.Championship;
import model.IModel;
import model.Team;
import observer.TeamObserver;
import tableModel.MyTeamModel;
import controller.TeamController;

/**
 * The team view of the app
 * @author lucadalseno
 *
 */
public class TeamView extends JFrame implements CallBackInterface,ObserverInterface<TeamObserver> {
    /**
     * 
     */
    private static final long serialVersionUID = -3615263799621093916L;
    private JPanel contentPane;
    private JTable teamTable;
    private JButton btnAddTeam;
    private JButton btnDeleteTeam;
    private JButton btnBack;
    private JLabel lblTeams;
    private JScrollPane teamScroll;
    private TeamObserver obs;
    /**
     * Create the frame.
     */
    private TeamView() {
        this.setTitle("Team View");
        this.setResizable(false);
    	setBounds(100, 100, 692, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        teamTable = new JTable();
        teamTable.setSelectionMode(0);
        
        btnAddTeam = new JButton("Add Team");
        btnAddTeam.setBounds(133, 415, 179, 29);
        contentPane.add(btnAddTeam);
        
        btnDeleteTeam = new JButton("Delete Team");
        btnDeleteTeam.setBounds(388, 415, 179, 29);
        contentPane.add(btnDeleteTeam);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(18, 477, 117, 29);
        contentPane.add(btnBack);
      
        lblTeams = new JLabel("TEAMS");
        lblTeams.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lblTeams.setBounds(293, 23, 91, 53);
        contentPane.add(lblTeams);
        
        teamScroll = new JScrollPane(teamTable);
        teamScroll.setBounds(108, 131, 492, 245);
        contentPane.add(teamScroll);
    }
    
    public TeamView(final IModel model, Championship ch,CallBackInterface callback){
        this();
        teamTable.setModel(new MyTeamModel(model, ch));
        teamTable.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					int index = teamTable.getSelectedRow();
					if(index>=0){
					Team team = model.getTeam(ch).get(index);
					new TeamComponentView(model,team,TeamView.this).setVisible(true);
					TeamView.this.setVisibility(false);
					}
				}
			}
		});
        
        btnAddTeam.addActionListener(e->{
           AddTeam t = new AddTeam();
           t.attachObserver(new TeamController(model,ch));
           t.setModal(true);
           t.setVisible(true);
           teamTable.setVisible(false);
           teamTable.setVisible(true);
        });
        
        btnDeleteTeam.addActionListener(e->{
            if(teamTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(this, "No team is selected","Error",JOptionPane.ERROR_MESSAGE);
            } else if((JOptionPane.showConfirmDialog(this, "You want to delete this team and roster with it?",
                    "WARNING", JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.YES_OPTION){
        	int i = teamTable.getSelectedRow();
        	if(i>=0){
        	        this.attachObserver(new TeamController(model, ch));
	                obs.removeTeam(model.getTeam(ch).get(i));
	        	teamTable.repaint();
        	}
            }
            teamTable.setVisible(false);
            teamTable.setVisible(true);
        });
        
        
        btnBack.addActionListener(e->{
                callback.onClose();
        	this.setVisible(false);
        });
    }

    @Override
    public void onClose() {
        this.setVisible(true);
    }

    @Override
    public void setVisibility(boolean b) {
        this.setVisible(b);
    }

    @Override
    public void attachObserver(TeamObserver observer) {
        this.obs = observer;        
    }
}
