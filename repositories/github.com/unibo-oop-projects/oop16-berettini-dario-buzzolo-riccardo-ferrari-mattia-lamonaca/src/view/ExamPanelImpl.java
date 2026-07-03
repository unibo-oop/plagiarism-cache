package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.ExamController;
import model.TableMouseListener;

public class ExamPanelImpl extends JFrame implements ExamPanel{

	private static final long serialVersionUID = 8156983749692058143L;

	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
	private Date date = new Date();
	private final JButton atleta = new JButton("Aggiungi atleta");
	private final JButton maestro = new JButton("Aggiungi maestro");	
	private final JButton start = new JButton("Inizia!");
	//private JButton stampa = new JButton("Stampa Storico");
	private final JLabel title = new JLabel("Esame: "+dateFormat.format(date));
	private JPanel mainPanel = new JPanel();
	public DefaultTableModel modelTableMaster;
	public DefaultTableModel modelTableAthlete;
	public JTable tableMaster;
	public JTable tableAthlete;
	private JScrollPane paneMaster;
	private JScrollPane paneAthlete;
	public ExamController examController;
	
	private int rowMaster=0;
	private int rowAthlete=0;
	
	public ExamPanelImpl() {

		super("Registro Maestri/Studenti");
		
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 1200, 900);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
	//	BUTTONS	
		mainPanel.add(title);
		
		maestro.setForeground(new Color(0, 0, 0));
		maestro.setFont(new Font("Arial", Font.BOLD, 15));
		maestro.setBounds(10, 15 , 180, 40);
		mainPanel.add(maestro);
		
		atleta.setForeground(new Color(0, 0, 0));
		atleta.setFont(new Font("Arial", Font.BOLD, 15));
		atleta.setBounds(200, 15 , 150, 40);
		mainPanel.add(atleta);
		
		start.setForeground(new Color(0, 0, 0));
		start.setFont(new Font("Arial", Font.BOLD, 15));
		start.setBounds(360, 15 , 150, 40);
		mainPanel.add(start);

		/*stampa.setForeground(new Color(0, 0, 0));
		stampa.setFont(new Font("Arial", Font.BOLD, 15));
		stampa.setBounds(520, 15 , 150, 40);
		mainPanel.add(stampa);*/
		
		title.setForeground(new Color(0, 0, 0));
		title.setFont(new Font("Arial", Font.BOLD, 15));
		title.setBounds(680, 15 , 150, 40);
		mainPanel.add(title);
	
	 // TABLES
		modelTableMaster = new DefaultTableModel(new Object[][] 
				{},
		      new Object[] { "Nome", "Cognome","Dan" }){				
				
					private static final long serialVersionUID = 2205643621514873449L;

				@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		modelTableAthlete = new DefaultTableModel(new Object[][] 
				{},
		      new Object[] { "Nome", "Cognome","Cintura","Stato avanzamento" }){					
				
					private static final long serialVersionUID = 2205643621514873449L;

			@Override
			public boolean isCellEditable(int row, int column) {
			     //all cells false
			     return false;
			}
		};
		
		tableMaster = new JTable(modelTableMaster);
		tableAthlete = new JTable(modelTableAthlete);
		paneMaster = new JScrollPane(tableMaster);
		paneAthlete = new JScrollPane(tableAthlete);
		
		paneMaster.setBounds(0, 70 , 1195, 300);
		paneAthlete.setBounds(0, 370 , 1195, 495);
		
		mainPanel.add(paneMaster);
		mainPanel.add(paneAthlete);
		
		tableMaster.setFont(new Font("Arial", 0, 20));
		tableAthlete.setFont(new Font("Arial", 0, 20));
		JTableHeader header = tableAthlete.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		JTableHeader header1 = tableMaster.getTableHeader();
		header1.setFont(new Font("Arial", Font.BOLD, 20));
		
		JPopupMenu popupMenuMaster = new JPopupMenu();
		JPopupMenu popupMenuAthlete	 = new JPopupMenu();
		
		JMenuItem menuItemRemoveMaster = new JMenuItem("Cancella maestro selezionato");
		JMenuItem menuItemRemoveAthlete = new JMenuItem("Cancella atleta selezionato");
		
		menuItemRemoveMaster.addActionListener(e->{
        	
        	this.removeCurrentRowMaster();
                	
        });
		menuItemRemoveAthlete.addActionListener(e->{
				
			this.removeCurrentRowAthlete();
		});
		popupMenuMaster.add(menuItemRemoveMaster);
		popupMenuAthlete.add(menuItemRemoveAthlete);
		
		tableMaster.setComponentPopupMenu(popupMenuMaster);
		tableAthlete.setComponentPopupMenu(popupMenuAthlete);
		
		TableMouseListener tableMLMaster = new TableMouseListener(tableMaster);	
		tableMaster.addMouseListener(tableMLMaster);

		TableMouseListener tableMLAthlete = new TableMouseListener(tableAthlete);
		tableAthlete.addMouseListener(tableMLAthlete);
		
	//	METHODS
		maestro.addActionListener(e -> {
			
			examController.insertMasterView();
		});
		
		atleta.addActionListener(e -> {
			
			examController.insertAthleteView();
		});
			
		start.addActionListener(e -> {
			if(examController.getAthletes().isEmpty()){
				JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "E' necesserio inserire almeno un atleta.");
			}else if(examController.getMasters().isEmpty()){
				JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "E' necesserio inserire almeno un maestro.");			
			}else{
				examController.startExamView();
				dispose();
			}
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Si","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Lo stato dei registri verrà perso, sei sicuro di vole uscire?", "Attenzione", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {	
			    	dispose();            
			    }
			  }
		});
		this.setLocationRelativeTo(null);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void setMainPanel() {
		
		this.getContentPane().add(mainPanel);
	}
	
	public void addObserver(ExamController controller) {
		this.examController = controller;		
	}

	public void updateMaster(){
		
		this.modelTableMaster.insertRow(rowMaster, new Object[]{examController.getLastMaster().getName(),
				examController.getLastMaster().getSurname(),
				examController.getLastMaster().getDan()});
		rowMaster++;
	}
	public void updateAthlete(){
		
		this.modelTableAthlete.insertRow(rowAthlete, new Object[]{examController.getLastAthlete().getName(),
				examController.getLastAthlete().getSurname(),
				examController.getLastAthlete().getBelt()});
		rowAthlete++;
	}
	private void  removeCurrentRowMaster() {
		
        int selectedRow = tableMaster.getSelectedRow();
        examController.deleteMaster(selectedRow);
        modelTableMaster.removeRow(selectedRow);
        rowMaster--;
    }
	private void removeCurrentRowAthlete(){
		
		int selectedRow = tableAthlete.getSelectedRow();
		examController.deleteAthlete(selectedRow);
		modelTableAthlete.removeRow(selectedRow);
		rowAthlete--;
	}
}
