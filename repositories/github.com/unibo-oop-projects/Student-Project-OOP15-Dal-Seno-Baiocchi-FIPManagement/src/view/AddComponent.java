package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Player.PLAYEROLE;
import model.Staff.ROLE;
import observer.TeamComponentObserver;

import com.toedter.calendar.JDateChooser;

import exceptions.PersonAlreadyAddedException;
/**
 * View to add a component of a specific team
 * @author francesco
 *
 */
public class AddComponent extends JDialog implements ObserverInterface<TeamComponentObserver> {
    private static final long serialVersionUID = -1833110520800729619L;
    private final JPanel contentPanel = new JPanel();
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField cfField;
    private JTextField heightField;
    private TeamComponentObserver obs;
    private JComboBox<Object> roleBox;
    private String[] type = new String[]{"PLAYER","STAFF"};
    private JDateChooser calendar;
    private JComboBox<String> typeBox;
    private JLabel lblType;
    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblBirth;
    private JLabel lblCf;
    private JLabel lblHeight;
    private JLabel lblRole;
    private JPanel buttonPane;
    private JButton addButton;
    private JButton cancelButton;
    /**
     * Create the dialog.
     */
    public AddComponent() {
        setBounds(100, 100, 451, 411);
        this.setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        roleBox = new JComboBox<Object>();
        roleBox.setBounds(141, 72, 134, 27);
        contentPanel.add(roleBox);
        
        typeBox = new JComboBox<String>();
        typeBox.setModel(new DefaultComboBoxModel<String>(type));
        roleBox.setModel(new DefaultComboBoxModel<>(PLAYEROLE.values()));
        typeBox.addActionListener(e->{
            if(typeBox.getSelectedItem().equals(type[0])){
            roleBox.setModel(new DefaultComboBoxModel<Object>(PLAYEROLE.values()));
            heightField.setEnabled(true);
            } else {
                roleBox.setModel(new DefaultComboBoxModel<Object>(ROLE.values()));
                heightField.setEnabled(false);
            }
        });
        typeBox.setBounds(141, 18, 134, 27);
        contentPanel.add(typeBox);
        
        lblType = new JLabel("Type:");
        lblType.setBounds(24, 22, 61, 16);
        contentPanel.add(lblType);
        
        nameField = new JTextField();
        nameField.setBounds(141, 123, 134, 28);
        contentPanel.add(nameField);
        nameField.setColumns(10);
        
        surnameField = new JTextField();
        surnameField.setBounds(141, 163, 134, 28);
        contentPanel.add(surnameField);
        surnameField.setColumns(10);
        
        lblName = new JLabel("Name:");
        lblName.setBounds(24, 129, 61, 16);
        contentPanel.add(lblName);
        
        lblSurname = new JLabel("Surname:");
        lblSurname.setBounds(24, 169, 61, 16);
        contentPanel.add(lblSurname);
        
        lblBirth = new JLabel("Birth:");
        lblBirth.setBounds(24, 219, 61, 16);
        contentPanel.add(lblBirth);
        
        calendar = new JDateChooser();
        calendar.setBounds(141, 213, 200, 28);
        contentPanel.add(calendar);
        
        cfField = new JTextField();
        cfField.setBounds(122, 260, 134, 28);
        contentPanel.add(cfField);
        cfField.setColumns(10);
        
        lblCf = new JLabel("CF:");
        lblCf.setBounds(24, 266, 61, 16);
        contentPanel.add(lblCf);
        
        lblHeight = new JLabel("Height:");
        lblHeight.setBounds(24, 306, 61, 16);
        contentPanel.add(lblHeight);
        
        heightField = new JTextField();
        heightField.setBounds(122, 300, 134, 28);
        contentPanel.add(heightField);
        heightField.setColumns(10);
        
        lblRole = new JLabel("Role:");
        lblRole.setBounds(24, 76, 61, 16);
        contentPanel.add(lblRole);    
        {
            buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                addButton = new JButton("Add");
                addButton.setActionCommand("OK");
                buttonPane.add(addButton);
                addButton.addActionListener(e->{
                    if(checkField()){
                        if(checkIntField()){
                            try{
                        if(typeBox.getSelectedItem().equals(type[0])){
                                obs.addPlayer(nameField.getText(), surnameField.getText(), (PLAYEROLE)roleBox.getSelectedItem(), 
                                    Double.valueOf(heightField.getText()), cfField.getText(), calendar.getDate());
                        } else {
                            obs.addStaff(nameField.getText(), surnameField.getText(), (ROLE)roleBox.getSelectedItem(), 
                                cfField.getText(),calendar.getDate());
                        } 
                        this.setVisible(false);
                            } catch (PersonAlreadyAddedException ex){
                                JOptionPane.showMessageDialog(this, "Person with that CF already exists","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Height must be an Int","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                      JOptionPane.showMessageDialog(this, "Some fields are missing","Error",JOptionPane.ERROR_MESSAGE);
                    }
                });
                getRootPane().setDefaultButton(addButton);
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

    private boolean checkIntField() {
        try{
            if(heightField.isEnabled()){
                Integer.valueOf(heightField.getText());
            }
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public void attachObserver(TeamComponentObserver observer) {
        this.obs = observer;        
    }
    
    public boolean checkField(){
        boolean b = !(nameField.getText().isEmpty() || surnameField.getText().isEmpty()  || 
                cfField.getText().isEmpty() || calendar.getDate() == null);
        return b && (!heightField.isEnabled() || !heightField.getText().isEmpty());
    }
}