package view;

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.IModel;
import model.Player;
import model.Player.PLAYEROLE;
import model.Staff;
import model.Staff.ROLE;
import model.Statistics;

import com.toedter.calendar.JDateChooser;

import controller.Utils;
/**
 * A view that shows all the statistic of a certain player
 * and gives also the possibility to change informations of
 * both players or staff members
 * @author lucadalseno
 *
 */
public class StatisticView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2072569797603702405L;
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField cfField;
    private JTextField heightField;
    private JDateChooser calendar;
    private JComboBox<Object> roleBox;
    private JButton btnCancel;
    private JButton btnApplyChanges;
    private JLabel setPoints;
    private JLabel setOffRebounds;
    private JLabel setDefRebounds;
    private JLabel setAssists;
    private JLabel setBlocks;
    private JLabel setPersonalFouls;
    private JLabel setTurnovers;
    private JLabel setSteals;
    private JLabel lblSteals;
    private JLabel lblLoseBalls;
    private JLabel lblPersonalFouls;
    private JLabel lblBlocks;
    private JLabel lblAssist;
    private JLabel lblDefRebounds;
    private JLabel lblOffRebounds;
    private JLabel lblPoints;
    /**
     * Create the frame.
     */
    private StatisticView() {
        setBounds(100, 100, 587, 408);
        this.setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(93, 61, 134, 28);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);
        
        surnameTextField = new JTextField();
        surnameTextField.setBounds(93, 116, 134, 28);
        contentPane.add(surnameTextField);
        surnameTextField.setColumns(10);
        
        calendar = new JDateChooser();
        calendar.setBounds(79, 182, 134, 28);
        contentPane.add(calendar);
        
        cfField = new JTextField();
        cfField.setBounds(79, 251, 134, 28);
        contentPane.add(cfField);
        cfField.setColumns(10);
        
        roleBox = new JComboBox<>();
        roleBox.setBounds(93, 17, 153, 27);
        contentPane.add(roleBox);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(6, 67, 61, 16);
        contentPane.add(lblName);
        
        JLabel lblSurname = new JLabel("Surname:");
        lblSurname.setBounds(6, 122, 61, 16);
        contentPane.add(lblSurname);
        
        JLabel lblBirth = new JLabel("Birth:");
        lblBirth.setBounds(6, 188, 61, 16);
        contentPane.add(lblBirth);
        
        JLabel lblCf = new JLabel("CF:");
        lblCf.setBounds(6, 257, 61, 16);
        contentPane.add(lblCf);
        
        JLabel lblHeight = new JLabel("Height:");
        lblHeight.setBounds(6, 320, 61, 16);
        contentPane.add(lblHeight);
        
        heightField = new JTextField();
        heightField.setBounds(79, 314, 134, 28);
        contentPane.add(heightField);
        heightField.setColumns(10);
        
        JLabel lblRole = new JLabel("Role:");
        lblRole.setBounds(6, 21, 61, 16);
        contentPane.add(lblRole);
        
        JPanel panel = new JPanel();
        panel.setBounds(262, 20, 319, 271);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(0, 2, 0, 0));
        
        lblPoints = new JLabel("Points:");
        panel.add(lblPoints);
        
        setPoints = new JLabel("");
        panel.add(setPoints);
        
        lblOffRebounds = new JLabel("Off Rebounds:");
        panel.add(lblOffRebounds);
        
        setOffRebounds = new JLabel("");
        panel.add(setOffRebounds);
        
        lblDefRebounds = new JLabel("Def Rebounds:");
        panel.add(lblDefRebounds);
        
        setDefRebounds = new JLabel("");
        panel.add(setDefRebounds);
        
        lblAssist = new JLabel("Assists:");
        panel.add(lblAssist);
        
        setAssists = new JLabel("");
        panel.add(setAssists);
        
        lblBlocks = new JLabel("Blocks:");
        panel.add(lblBlocks);
        
        setBlocks = new JLabel("");
        panel.add(setBlocks);
        
        lblPersonalFouls = new JLabel("Personal Fouls:");
        panel.add(lblPersonalFouls);
        
        setPersonalFouls = new JLabel("");
        panel.add(setPersonalFouls);
        
        lblLoseBalls = new JLabel("Lose Balls:");
        panel.add(lblLoseBalls);
        
        setTurnovers = new JLabel("");
        panel.add(setTurnovers);
        
        lblSteals = new JLabel("Steals:");
        panel.add(lblSteals);
        
        setSteals = new JLabel("");
        panel.add(setSteals);
        
        btnApplyChanges = new JButton("Apply Changes");
        btnApplyChanges.setBounds(280, 326, 134, 29);
        contentPane.add(btnApplyChanges);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(426, 326, 127, 29);
        contentPane.add(btnCancel);
        btnCancel.addActionListener(e->{
            this.setVisible(false);
        });
    }
    
    public StatisticView(Player p,final IModel model){
        this();
        calculateStatistic(p);
        this.roleBox.setModel(new DefaultComboBoxModel<>(PLAYEROLE.values()));
        this.roleBox.setSelectedItem(p.getRole());
        this.nameTextField.setText(p.getName());
        this.surnameTextField.setText(p.getSurname());
        this.calendar.setDate(p.getBirth());
        this.cfField.setText(p.getCF());
        this.heightField.setText(""+p.getHeight());
        btnApplyChanges.addActionListener(e->{
            p.setName(nameTextField.getText());
            p.setSurname(surnameTextField.getText());
            p.setBirth(calendar.getDate());
            p.setRole((PLAYEROLE)roleBox.getSelectedItem());
            p.setCF(cfField.getText());
            p.setHeight(Double.valueOf(heightField.getText()));
            Utils.save(model);
            JOptionPane.showMessageDialog(this, "Changes Applied", "Info",
                    JOptionPane.INFORMATION_MESSAGE); 
            this.setVisible(false);
        });
    }
    
    private void calculateStatistic(Player p) {
        int points = 0;
        int offReb = 0;
        int defReb = 0;
        int assists = 0;
        int turnOver = 0;
        int steals = 0;
        int blocks = 0;
        int personFouls = 0;
        for(Statistics s: p.getStatistics()){
            points += s.getPoints();
            offReb += s.getOffRebounds();
            defReb += s.getDefRebounds();
            assists += s.getAssists();
            turnOver += s.getTurnovers();
            steals += s.getSteals();
            blocks += s.getBlocks();
            personFouls += s.getPersonalFouls();
        }
        setPoints.setText(""+points);
        setOffRebounds.setText(""+offReb);
        setDefRebounds.setText(""+defReb);
        setAssists.setText(""+assists);
        setTurnovers.setText(""+turnOver);
        setSteals.setText(""+steals);
        setBlocks.setText(""+blocks);
        setPersonalFouls.setText(""+personFouls);
    }

    public StatisticView(Staff s,final IModel model){
        this();
        lblSteals.setVisible(false);
        lblLoseBalls.setVisible(false);
        lblPersonalFouls.setVisible(false);
        lblBlocks.setVisible(false);
        lblAssist.setVisible(false);
        lblDefRebounds.setVisible(false);
        lblOffRebounds.setVisible(false);
        lblPoints.setVisible(false);
        this.roleBox.setModel(new DefaultComboBoxModel<>(ROLE.values()));
        this.roleBox.setSelectedItem(s.getRole());
        this.nameTextField.setText(s.getName());
        this.surnameTextField.setText(s.getSurname());
        this.calendar.setDate(s.getBirth());
        this.cfField.setText(s.getCF());
        this.heightField.setEnabled(false);
        btnApplyChanges.addActionListener(e->{
            s.setName(nameTextField.getText());
            s.setSurname(surnameTextField.getText());
            s.setBirth(calendar.getDate());
            s.setRole((ROLE)roleBox.getSelectedItem());
            s.setCF(cfField.getText());
            Utils.save(model);
            JOptionPane.showMessageDialog(this, "Changes Applied", "Info",
                    JOptionPane.INFORMATION_MESSAGE); 
            this.setVisible(false);
        });
    }
    
   
}
