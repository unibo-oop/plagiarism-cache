package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import observer.TeamObserver;
/**
 * View to add a team to a championship
 * @author francesco
 *
 */
public class AddTeam extends JDialog implements ObserverInterface<TeamObserver> {
    /**
     * 
     */
    private static final long serialVersionUID = 5849733983802992020L;
    private final JPanel contentPanel = new JPanel();
    private JTextField nameTextfield;
    private JTextField homeColourTextfield;
    private JTextField transferColourTextField;
    private JTextField companyTextfield;
    private TeamObserver obs;
    private JTextField vatTextfield;
    private JLabel lblName;
    private JLabel lblHomeColour;
    private JLabel lblTransferColour;
    private JLabel lblCompanyName;
    private JLabel lblCompanyVat;
    private JPanel buttonPane;
    private JButton addButton;
    private JButton cancelButton;
    /**
     * Create the dialog.
     */
    public AddTeam() {
        setBounds(100, 100, 474, 365);
        this.setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        nameTextfield = new JTextField();
        nameTextfield.setBounds(148, 23, 134, 28);
        contentPanel.add(nameTextfield);
        nameTextfield.setColumns(10);
        
        homeColourTextfield = new JTextField();
        homeColourTextfield.setBounds(148, 78, 134, 28);
        contentPanel.add(homeColourTextfield);
        homeColourTextfield.setColumns(10);
        
        transferColourTextField = new JTextField();
        transferColourTextField.setBounds(148, 132, 134, 28);
        contentPanel.add(transferColourTextField);
        transferColourTextField.setColumns(10);
        
        lblName = new JLabel("Name:");
        lblName.setBounds(6, 29, 61, 16);
        contentPanel.add(lblName);
        
        lblHomeColour = new JLabel("Home Colour:");
        lblHomeColour.setBounds(6, 84, 98, 16);
        contentPanel.add(lblHomeColour);
        
        lblTransferColour = new JLabel("Transfer Colour:");
        lblTransferColour.setBounds(6, 138, 113, 16);
        contentPanel.add(lblTransferColour);
        
        lblCompanyName = new JLabel("Company Name:");
        lblCompanyName.setBounds(6, 196, 113, 16);
        contentPanel.add(lblCompanyName);
        
        companyTextfield = new JTextField();
        companyTextfield.setBounds(148, 190, 134, 28);
        contentPanel.add(companyTextfield);
        companyTextfield.setColumns(10);
        
        lblCompanyVat = new JLabel("Company VAT:");
        lblCompanyVat.setBounds(6, 247, 98, 16);
        contentPanel.add(lblCompanyVat);
        
        vatTextfield = new JTextField();
        vatTextfield.setBounds(148, 241, 134, 28);
        contentPanel.add(vatTextfield);
        vatTextfield.setColumns(10);
        {
            buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                addButton = new JButton("Add");
                addButton.setActionCommand("Add");
                buttonPane.add(addButton);
                addButton.addActionListener(e->{
	            	try {
	            	        if(checkField()){
	            	            obs.addTeam(nameTextfield.getText(), homeColourTextfield.getText(), 
	            	                    transferColourTextField.getText(), companyTextfield.getText(),
	            	                    vatTextfield.getText());
	            	            this.setVisible(false);
	            	        } else {
	            	            JOptionPane.showMessageDialog(this, "Some fields are missing",
	            	                    "Error",JOptionPane.ERROR_MESSAGE);
	            	        }
	            	    } catch (Exception e2) {
	            	        JOptionPane.showMessageDialog(this, "Team already exists", 
	            	                "Error",JOptionPane.ERROR_MESSAGE);                    
	            	    }
                });
                getRootPane().setDefaultButton(addButton);
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(e->{
                    this.setVisible(false);
                });
                buttonPane.add(cancelButton);
            }
        }
    }

    @Override
    public void attachObserver(TeamObserver observer) {
        this.obs = observer;        
    }

    public boolean checkField(){
        return !(nameTextfield.getText().isEmpty() || homeColourTextfield.getText().isEmpty() || 
                transferColourTextField.getText().isEmpty() || companyTextfield.getText().isEmpty() ||
                vatTextfield.getText().isEmpty());
    }
}
