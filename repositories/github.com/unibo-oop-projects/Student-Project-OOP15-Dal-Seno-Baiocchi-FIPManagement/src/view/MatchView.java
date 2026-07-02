package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.IModel;
import model.Player;
import model.StatisticModel;
import model.StatisticModelImpl;
import model.Statistics;
import model.Team;
import observer.MatchViewObserver;
import tableModel.MyMatchModel;
import controller.MatchViewController;
import controller.Utils;
import exceptions.InvalidStatisticException;
/**
 * View to manage and save a match between 2 teams 
 * @author francesco
 *
 */
public class MatchView extends JFrame implements ObserverInterface<MatchViewObserver>{

    /**
     * 
     */
    private static final long serialVersionUID = -6679819020260822815L;
    private JPanel contentPane;
    private JButton addOnePoint;
    private JButton removeOnePoint;
    private JButton addTwoPoints;
    private JButton removeTwoPoints;
    private JButton addThreePoints;
    private JButton removeThreePoints;
    private JButton addOffRebound;
    private JButton removeOffRebound;
    private JButton addDefRebound;
    private JButton removeDefRebound;
    private JButton addAssist;
    private JButton removeAssist;
    private JButton addBlock;
    private JButton removeBlock;
    private JButton addPersonalFoul;
    private JButton removePersonaFoul;
    private JButton addTurnover;
    private JButton removeTurnover;
    private JButton addSteal;
    private JButton removeSteal;
    private JButton saveMatch;
    private JButton cancel;
    private JTable homeTable;
    private JTable guestTable;
    private JScrollPane homeScrollPane;
    private JScrollPane guestScrollPane;
    private JLabel lblHomeTeam;
    private JLabel lblGuestTeam;
    private MatchViewObserver obs;


	/**
	 * Create the frame.
	 */
	private MatchView() {
		setBounds(100, 100, 1208, 563);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomeTeam.setBounds(22, 11, 133, 38);
		contentPane.add(lblHomeTeam);
		
		lblGuestTeam = new JLabel("Guest Team");
		lblGuestTeam.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblGuestTeam.setBounds(751, 11, 133, 38);
		contentPane.add(lblGuestTeam);
		
		JPanel panel = new JPanel();
		panel.setBounds(466, 37, 260, 476);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		addOnePoint = new JButton("Add 1 Point");
		panel.add(addOnePoint);
		
		removeOnePoint = new JButton("Remove 1 Point");
		panel.add(removeOnePoint);
		
		addTwoPoints = new JButton("Add 2 Points");
		panel.add(addTwoPoints);
		
		removeTwoPoints = new JButton("Remove 2 Points");
		panel.add(removeTwoPoints);
		
		addThreePoints = new JButton("Add 3 Points");
		panel.add(addThreePoints);
		
		removeThreePoints = new JButton("Remove 3 Points");
		panel.add(removeThreePoints);
		
		addOffRebound = new JButton("Add OFF Rebound");
		panel.add(addOffRebound);
		
		removeOffRebound = new JButton("Remove OFF.Rebound");
		removeOffRebound.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		panel.add(removeOffRebound);
		
		addDefRebound = new JButton("Add DEF Rebound");
		panel.add(addDefRebound);
		
		removeDefRebound = new JButton("Remove DEF Rebound");
		removeDefRebound.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		panel.add(removeDefRebound);
		
		addAssist = new JButton("Add Assist");
		panel.add(addAssist);
		
		removeAssist = new JButton("Remove Assist");
		panel.add(removeAssist);
		
		addBlock = new JButton("Add Block");
		panel.add(addBlock);
		
		removeBlock = new JButton("Remove Block");
		panel.add(removeBlock);
		
		addPersonalFoul = new JButton("Add  Personal Foul");
		panel.add(addPersonalFoul);
		
		removePersonaFoul = new JButton("Remove Personal Foul");
		removePersonaFoul.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		panel.add(removePersonaFoul);
		
		addTurnover = new JButton("Add Turnover");
		panel.add(addTurnover);
		
		removeTurnover = new JButton("Remove Turnover");
		panel.add(removeTurnover);
		
		addSteal = new JButton("Add Steal");
		panel.add(addSteal);
		
		removeSteal = new JButton("Remove Steal");
		panel.add(removeSteal);
		
		saveMatch = new JButton("SaveMatch");
		saveMatch.setBounds(973, 470, 86, 43);
		contentPane.add(saveMatch);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(1085, 470, 86, 43);
		contentPane.add(cancel);
		
		homeTable = new JTable();
		homeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		homeTable.setSelectionBackground(Color.RED);
		
		guestTable = new JTable();
		guestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		homeScrollPane = new JScrollPane(homeTable);
		homeScrollPane.setBounds(10, 49, 434, 416);
		contentPane.add(homeScrollPane);
		
		guestScrollPane = new JScrollPane(guestTable);
		guestScrollPane.setBounds(751, 49, 434, 416);
		contentPane.add(guestScrollPane);
		
		
	}

	public MatchView(final IModel model, Team team1, Team team2){
		this();
		
		lblHomeTeam.setText(team1.getName());
		lblGuestTeam.setText(team2.getName());
		
		StatisticModel stmod = new StatisticModelImpl();

        this.attachObserver(new MatchViewController(model, stmod));
		for(Player p : team1.getPlayers()){
			stmod.addStatistic(p, new Statistics());
		}
		
		for(Player p : team2.getPlayers()){
			stmod.addStatistic(p, new Statistics());
		}
		
		homeTable.setModel(new MyMatchModel(model, team1, stmod));
		guestTable.setModel(new MyMatchModel(model, team2, stmod));
		
		homeTable.getColumnModel().getColumn(0).setMinWidth(180);
                homeTable.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < homeTable.getColumnCount(); i++){
		    homeTable.getColumnModel().getColumn(i).setResizable(false);
		}
		
		guestTable.getColumnModel().getColumn(0).setMinWidth(180);
		guestTable.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < guestTable.getColumnCount(); i++){
		    guestTable.getColumnModel().getColumn(i).setResizable(false);
                }
		
		homeTable.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				guestTable.getSelectionModel().removeSelectionInterval(0, 1000);;
			}
		});
		
		guestTable.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				homeTable.getSelectionModel().removeSelectionInterval(0, 1000);
			}
		});
		
		
		addOnePoint.addActionListener(e->{
			
			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increasePoints(p, 1);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increasePoints(p, 1);
				guestTable.repaint();
			}
		});
		
		removeOnePoint.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
	                                this.obs.decreasePoints(p, 1);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreasePoints(p, 1);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				guestTable.repaint();
			}
		});
		
		addTwoPoints.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increasePoints(p, 2);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increasePoints(p, 2);
				guestTable.repaint();
			}
		});
		
		removeTwoPoints.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreasePoints(p, 2);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreasePoints(p, 2);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				guestTable.repaint();
			}
		});
		
		addThreePoints.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increasePoints(p, 3);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increasePoints(p, 3);
				guestTable.repaint();
			}
		});
		
		removeThreePoints.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreasePoints(p, 3);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreasePoints(p, 3);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				guestTable.repaint();
			}
		});		
		
		addOffRebound.addActionListener(e->{
			
			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increaseOffRebounds(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increaseOffRebounds(p);
				guestTable.repaint();
			}
		});
		
		removeOffRebound.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
	                             this.obs.decreaseOffRebounds(p);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreaseOffRebounds(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				guestTable.repaint();
			}			
		});

		addDefRebound.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increaseDefRebounds(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increaseDefRebounds(p);
				guestTable.repaint();
			}
		});
		
		removeDefRebound.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreaseDefRebounds(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreaseDefRebounds(p);
                                } catch (InvalidStatisticException ex){
                                   errorDialog();
                                }
				guestTable.repaint();
			}			
		});

		addAssist.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increseAssists(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increseAssists(p);
				guestTable.repaint();
			}
		});
		
		removeAssist.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreaseAssists(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreaseAssists(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				guestTable.repaint();
			}			
		});

		addBlock.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increaseBlocks(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increaseBlocks(p);
				guestTable.repaint();
			}
		});
		
		removeBlock.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreaseBlocks(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreaseBlocks(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				guestTable.repaint();
			}			
		});

		addPersonalFoul.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.incresePersonalFouls(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.incresePersonalFouls(p);
				guestTable.repaint();
			}
		});
		
		removePersonaFoul.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
				    this.obs.decreasePeronsalFouls(p);
				} catch (InvalidStatisticException ex){
				    errorDialog();
				}
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreasePeronsalFouls(p);
                                } catch (InvalidStatisticException ex){
                                    errorDialog();
                                }
				guestTable.repaint();
			}			
		});

		addTurnover.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increaseTurnovers(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increaseTurnovers(p);
				guestTable.repaint();
			}
		});
		
		removeTurnover.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreaseTurnovers(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreaseTurnovers(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				guestTable.repaint();
			}			
		});

		addSteal.addActionListener(e->{

			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();

			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				this.obs.increaseSteals(p);
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				this.obs.increaseSteals(p);
				guestTable.repaint();
			}
		});
		
		removeSteal.addActionListener(e->{
			int homeindex = homeTable.getSelectedRow();
			int guestindex = guestTable.getSelectedRow();
			Player p ;
			
			if(homeindex>=0){
				p = team1.getPlayers().get(homeindex);
				try{
                                    this.obs.decreaseSteals(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				homeTable.repaint();
			}else if(guestindex>=0){
				p = team2.getPlayers().get(guestindex);
				try{
                                    this.obs.decreaseSteals(p);
                               } catch (InvalidStatisticException ex){
                                   errorDialog();
                               }
				guestTable.repaint();
			}			
		});

		
		saveMatch.addActionListener(e->{
			try {
				if((JOptionPane.showConfirmDialog(this, "Are you sure you want to export this match?", 
                    "Alert",JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.YES_OPTION){
					JFrame parentFrame = new JFrame();
            	 
	            	JFileChooser fileChooser = new JFileChooser();
	            	fileChooser.setDialogTitle("Saving Match");   
	            	fileChooser.setSelectedFile(new File("" + lblHomeTeam.getText() + lblGuestTeam.getText() + "view"));
	            	fileChooser.setFileFilter(new FileNameExtensionFilter("Excel File","xlsx"));
	            	int userSelection = fileChooser.showSaveDialog(parentFrame);
	            	
	            	if (userSelection == JFileChooser.APPROVE_OPTION) {
	            	    File fileToSave = fileChooser.getSelectedFile();
	            	    
	            	    this.obs.saveMatch(homeTable,guestTable,lblHomeTeam.getText(),lblGuestTeam.getText(), fileToSave.getAbsolutePath());
	            	    JOptionPane.showMessageDialog(this, "File created successfully",
	                            "Done!",JOptionPane.INFORMATION_MESSAGE);
	                        stmod.applyStatistic();
	                        Utils.save(model);
	            			this.setVisible(false);
	                }else{
	                    	    JOptionPane.showMessageDialog(this, "File not created",
	                                    "Nothing happened",JOptionPane.INFORMATION_MESSAGE);                                        	
	                        }
	                }
	            	
	            } catch (IOException e1) {
	                JOptionPane.showMessageDialog(this, ""+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
		});
		
		cancel.addActionListener(e->{
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(false);
		});
		
	}
	@Override
	public void attachObserver(MatchViewObserver observer) {
		this.obs = observer;	
	}
	
	public void errorDialog(){
	    JOptionPane.showMessageDialog(this, "Statistics cannot be negative", 
                    "Statistic Error", JOptionPane.ERROR_MESSAGE);
	}
}