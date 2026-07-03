package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ExamController;
import model.Athlete;

public class ExamStartedImpl extends JFrame implements ExamStarted{

	private static final long serialVersionUID = 4727669161374169003L;
	ExamController examController;
	public DefaultTableModel modelTableAthlete;
	public JTable tableAthlete;
	private JScrollPane paneAthlete;
	private JPanel mainPanel = new JPanel();
	private JButton close = new JButton("Chiudi");
	Object[][] atleti;
	public List<Athlete> athletes;
	private int completamento=0;
	JLabel titolo = new JLabel();
	
	public ExamStartedImpl(List<Athlete> athletes){

		for(int i=0; i < athletes.size() ; i++){
		
			this.completamento+= athletes.get(i).getAvanzamento();
		}
	
		if(this.completamento / 3 < athletes.size()){
				
			this.setVisible(true);
			this.setResizable(false);
			this.setBounds(100, 100, 900, 700);
			mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setContentPane(mainPanel);
			mainPanel.setLayout(null);
				
				//tableAthlete.setFont(new Font("Serif", Font.BOLD, 20));
				//JTableHeader header = tableAthlete.getTableHeader();
				//header.setFont(new Font("Serif", Font.BOLD, 20));
				
				atleti = new Object[athletes.size()][4];
				
				for(int i=0 ; i < athletes.size(); i++){
					
					atleti[i][0] = athletes.get(i).getName();
					atleti[i][1] = athletes.get(i).getSurname();
					atleti[i][2] = athletes.get(i).getBelt();
					atleti[i][3] = athletes.get(i).getAvanzamento()+"/3";	
				}
				
				modelTableAthlete =  new DefaultTableModel(atleti,
					      new Object[] { "Nome", "Cognome","Cintura","Avanzamento"}){					
					
						  private static final long serialVersionUID = 2205643621514873449L;

					@Override
					    public boolean isCellEditable(int row, int column) {
					       return false;
					    }
				};
				
				titolo.setText("Esame di Taekwondo.  Data: "+new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				titolo.setForeground(new Color(0, 0, 0));
				titolo.setBounds(10, 0, 400, 40);
				titolo.setFont(new Font("Arial", Font.BOLD, 16));
				mainPanel.add(titolo);
				
				tableAthlete = new JTable(modelTableAthlete);
				paneAthlete = new JScrollPane(tableAthlete);
				
				paneAthlete.setBounds(0, 40, 895, 645);
				mainPanel.add(paneAthlete);
				
				tableAthlete.setFont(new Font("Arial", 0, 17));
				JTableHeader header = tableAthlete.getTableHeader();
				header.setFont(new Font("Arial", Font.BOLD, 20));
	
				this.tableAthlete.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					   
				    public void valueChanged(ListSelectionEvent event) {
				        if (tableAthlete.getSelectedRow() > -1) {

				        	String nome = (String) modelTableAthlete.getValueAt(tableAthlete.getSelectedRow(), 0);
				        			
				        	String cognome =(String)modelTableAthlete.getValueAt(tableAthlete.getSelectedRow(), 1);
						    
				        	for(int i=0; i < examController.getAthletes().size(); i++){
				        		
				        		if(examController.getAthletes().get(i).getName().equals(nome) 
				        				&& examController.getAthletes().get(i).getSurname().equals(cognome) &&
				        				examController.getAthletes().get(i).getAvanzamento() != 3){
				        			
				        			examController.votesAthleteView(examController.getAthletes().get(i));
				        			tableAthlete.clearSelection();
				        			dispose();
				        		} else if(examController.getAthletes().get(i).getName().equals(nome) 
				        				&& examController.getAthletes().get(i).getSurname().equals(cognome) &&
				        				examController.getAthletes().get(i).getAvanzamento() == 3)	{
				        			
				        			examCompletedMessage(nome + " " + cognome + " ha gia' terminato l' esame.");
				        			tableAthlete.clearSelection();
				        		}
				        	}
				        } 
				    }
				});
				
		}else{
			
			this.setVisible(true);
			this.setResizable(false);
			this.setBounds(100, 100, 700, 800);
			mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setContentPane(mainPanel);
			mainPanel.setLayout(null);
			//mainPanel.setBackground(new Color(255,255,255));
			close.setForeground(new Color(0, 0, 0));
			close.setFont(new Font("Arial", Font.BOLD, 15));
			close.setBounds(265, 710 , 150, 40);
			mainPanel.add(close);
			
			close.addActionListener(e->{				
				dispose();
			});
	
			JLabel l;
			int h = 15;
			
			for(Athlete atleta : athletes){
				
				int votoFinale=((atleta.getVoto(0))+(atleta.getVoto(1))+(atleta.getVoto(2)));
					atleta.isPromosso();
					if(atleta.getPromosso() == true){
						l = new JLabel(atleta.getSurname() + " " + atleta.getName() + " e' stato " 
								+ "promosso" + " con il voto di: " + votoFinale+"/30.");
						l.setBackground(new Color(0, 255, 0));
					}else{
						l = new JLabel(atleta.getSurname() + " " + atleta.getName() + " e' stato " 
								+ "bocciato" + " con il voto di: " + votoFinale+"/30.");
						l.setBackground(new Color(255, 0, 0));
					}	
				
				l.setForeground(new Color(0, 0, 0));
				l.setFont(new Font("Arial", Font.BOLD, 15));
				l.setBounds(130, h , 500, 40);
				l.setOpaque(true);
				mainPanel.add(l);
				h += 50;
			}
		}
		SwingUtilities.updateComponentTreeUI(this);	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Si","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Lo stato attuale dell'esame verrà perso, sei sicuro di vole uscire?", "Attenzione", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			    	dispose();            
			    }
			  }
		});
	}

	public void examCompletedMessage(String message){
		
	    JFrame frame = new JFrame("JOptionPane showMessageDialog example");			   
	    JOptionPane.showMessageDialog(frame, message);
	}
	
	public void addObserver(ExamController controller){
		
		this.examController = controller;	
	}
}