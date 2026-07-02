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

import controller.ChampionshipController;
import model.Championship;
import model.IModel;
import observer.ChampionshipObserver;
import tableModel.MyChampionshipModel;


/**
 * The championship view of the app
 * @author lucadalseno
 *
 */
public class ChampionshipView extends JFrame  implements ObserverInterface<ChampionshipObserver>,CallBackInterface{
    /**
     * 
     */
    private static final long serialVersionUID = 4097624461142333134L;
    private JPanel contentPane;
    private JTable champTable;
    private JButton addChampBtn;
    private JButton deleteChamp;
    private ChampionshipObserver obs;
    private JButton btnBack;
    private JLabel lblChampionship;
    private JScrollPane champScroll;
    /**
     * Create the frame.
     * 
     */
    private ChampionshipView() {
        this.setTitle("Championship View");
        setBounds(100, 100, 692, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        addChampBtn = new JButton("Add Championship");
        addChampBtn.setBounds(133, 415, 179, 29);
        contentPane.add(addChampBtn);
        
        deleteChamp = new JButton("Delete Championship");
        deleteChamp.setBounds(388, 415, 179, 29);
        contentPane.add(deleteChamp);
        
        champTable = new JTable();
        champTable.setSelectionMode(0);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(18, 477, 117, 29);
        contentPane.add(btnBack);   
        
        lblChampionship = new JLabel("CHAMPIONSHIP");
        lblChampionship.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lblChampionship.setBounds(251, 23, 200, 53);
        contentPane.add(lblChampionship);
        
        champScroll = new JScrollPane(champTable);
        champScroll.setBounds(108, 131, 492, 245);
        contentPane.add(champScroll);
    }
    
    public ChampionshipView(final IModel model,final CallBackInterface callback){
    	this();
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setResizable(false);
    	champTable.setModel(new MyChampionshipModel(model));
    	champTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int index = ((JTable)e.getSource()).getSelectedRow();
					Championship ch = model.getChampionship().get(index);
					new TeamView(model, ch,ChampionshipView.this).setVisible(true);
					ChampionshipView.this.setVisibility(false);
				}
			}
		});
    	
    	
    	
        addChampBtn.addActionListener(e->{
            AddChamp c = new AddChamp();
            c.attachObserver(new ChampionshipController(model));
            c.setModal(true);
            c.setVisible(true);
            champTable.setVisible(false);
            champTable.setVisible(true);
        });
        deleteChamp.addActionListener(e->{
            if(champTable.getSelectedRow() == -1){
             JOptionPane.showMessageDialog(this, "No championship is selected","Error",JOptionPane.ERROR_MESSAGE);
            } else if((JOptionPane.showConfirmDialog(this, "You want to delete this championship and all the teams with it?",
                    "WARNING", JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.YES_OPTION){
                    this.attachObserver(new ChampionshipController(model));
                    obs.deleteChampionship(model.getChampionship().get(champTable.getSelectedRow()));
                            
            }
            champTable.setVisible(false);
            champTable.setVisible(true);
        });
        
        
        btnBack.addActionListener(e->{
                callback.onClose();
        	this.setVisible(false);
        });
    }

    @Override
    public void attachObserver(ChampionshipObserver observer) {
        this.obs = observer;  
    }

    @Override
    public void onClose() {
      this.setVisible(true);
    }

    @Override
    public void setVisibility(boolean b) {
        this.setVisible(b);        
    } 
}