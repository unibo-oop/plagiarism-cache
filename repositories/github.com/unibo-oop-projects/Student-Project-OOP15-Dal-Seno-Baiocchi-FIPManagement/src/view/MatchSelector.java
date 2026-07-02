package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Championship;
import model.IModel;
import model.Team;
/**
 * View to begin a new match between 2 teams of the same championship
 * @author francesco
 *
 */
public class MatchSelector extends JFrame  {

	/**
     * 
     */
    private static final long serialVersionUID = -7149268289002655560L;
    private JPanel contentPane;
	private JComboBox<Team> comboBoxHome;
	private JComboBox<Team> comboBoxGuest;
	private JButton btnStartMatch;
	private JButton btnBack;
	private JLabel lblSelectChampionship;
	private JComboBox<Championship> comboBoxChampionship;
	/**
	 * Create the frame.
	 */
	private MatchSelector() {
		setBounds(100, 100, 450, 235);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setBounds(34, 77, 91, 30);
		contentPane.add(lblHomeTeam);
		
		JLabel lblGuestTeam = new JLabel("Guest Team");
		lblGuestTeam.setBounds(252, 77, 98, 30);
		contentPane.add(lblGuestTeam);
		
		comboBoxHome = new JComboBox<>();
		comboBoxHome.setBounds(34, 116, 134, 20);
		contentPane.add(comboBoxHome).setEnabled(false);;
		
		comboBoxGuest = new JComboBox<>();
		comboBoxGuest.setBounds(252, 116, 134, 20);
		contentPane.add(comboBoxGuest).setEnabled(false);;
		
		btnStartMatch = new JButton("Start Match");
		btnStartMatch.setBounds(34, 147, 134, 23);
		contentPane.add(btnStartMatch);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(252, 147, 134, 23);
		contentPane.add(btnBack);
		
		lblSelectChampionship = new JLabel("Select Championship");
		lblSelectChampionship.setBounds(34, 11, 118, 20);
		contentPane.add(lblSelectChampionship);
		
		comboBoxChampionship = new JComboBox<>();
		comboBoxChampionship.setBounds(34, 46, 134, 20);
		contentPane.add(comboBoxChampionship);
	}
	
	public MatchSelector(final IModel model){
		this();
		Championship[] ch = new Championship[model.getChampionship().size()];
		model.getChampionship().toArray(ch);
		comboBoxChampionship.setModel(new DefaultComboBoxModel<>(ch));
		
		comboBoxChampionship.addActionListener(e->{
			Championship champ = (Championship) (comboBoxChampionship.getSelectedItem());
			Team[] teams = new Team[model.getTeam(champ).size()];
			model.getTeam(champ).toArray(teams);
			comboBoxHome.setEnabled(true);
			comboBoxHome.setModel(new DefaultComboBoxModel<>(teams));
			comboBoxGuest.setEnabled(true);
			comboBoxGuest.setModel(new DefaultComboBoxModel<>(teams));
		});
		
		comboBoxChampionship.getActionListeners()[0].actionPerformed(null);
		
		btnStartMatch.addActionListener(e->{
			Team team1 = (Team) comboBoxHome.getSelectedItem();
			Team team2 = (Team) comboBoxGuest.getSelectedItem();
			if(team1.equals(team2)){
				JOptionPane.showMessageDialog(this, "You have selected the same team");
			}else{
				new MatchView(model, team1, team2).setVisible(true);	
				this.setVisible(false);
			}
		});
		
		
		btnBack.addActionListener(e->{
			this.setVisible(false);
		});
	}
}