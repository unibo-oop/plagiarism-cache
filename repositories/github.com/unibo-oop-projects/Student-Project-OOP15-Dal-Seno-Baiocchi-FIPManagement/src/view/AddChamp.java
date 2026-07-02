package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Division;
import model.Zone;
import observer.ChampionshipObserver;
import exceptions.ChampionshipAlreadyExistException;
/**
 * Dialog that let you add a championship
 * @author lucadalseno
 *
 */
public class AddChamp extends JDialog implements ObserverInterface<ChampionshipObserver> {
    /**
     * 
     */
    private static final long serialVersionUID = 73893420932703563L;
    private final JPanel contentPanel = new JPanel();
    private ChampionshipObserver obs;
    private JComboBox<Zone> zoneBox;
    private JComboBox<Division> divisionBox;
    private JLabel lblNewLabel;
    private JLabel lblZone;
    private JPanel buttonPane;
    private JButton okButton;
    private JButton cancelButton;
    /**
     * Create the dialog.
     */
    public AddChamp() {
        setBounds(100, 100, 410, 231);
        this.setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        lblNewLabel = new JLabel("Division:");
        lblNewLabel.setBounds(42, 43, 75, 16);
        contentPanel.add(lblNewLabel);
        
        lblZone = new JLabel("Zone:");
        lblZone.setBounds(42, 106, 75, 16);
        contentPanel.add(lblZone);
        
        divisionBox = new JComboBox<>(Division.values());
        divisionBox.setBounds(188, 39, 145, 27);
        contentPanel.add(divisionBox);
        
        zoneBox = new JComboBox<>(Zone.values());
        zoneBox.setBounds(188, 102, 145, 27);
        contentPanel.add(zoneBox);
        {
            buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(e->{
                   try {
                    obs.addChampionship((Division)divisionBox.getSelectedItem(), (Zone) zoneBox.getSelectedItem());
                    this.setVisible(false);
                } catch (ChampionshipAlreadyExistException ex) {
                        JOptionPane.showMessageDialog(this, "Championship already exists", "Error",JOptionPane.ERROR_MESSAGE);                    
                } 
                });
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(e->{
                    this.setVisible(false);
                });
            }
        }
    }
    
    @Override
    public void attachObserver(ChampionshipObserver observer) {
        this.obs = observer;    
    }
}
