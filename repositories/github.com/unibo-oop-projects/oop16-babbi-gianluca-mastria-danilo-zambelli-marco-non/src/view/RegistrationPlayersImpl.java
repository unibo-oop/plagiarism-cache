package view;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.ApplicationImpl;

/**this class shows the page that allow to each user to register his name
 * @author danil
 *
 */
public class RegistrationPlayersImpl extends JFrame implements RegistrationPlayers {
 
	private static final long serialVersionUID = 1L;
	
	private JTextField txtPlayerBlue = new JTextField();
	private JTextField txtPlayerOrange = new JTextField();
	private JTextField txtPlayerPurple = new JTextField();
	private JTextField txtPlayerRed = new JTextField();
	private JTextField txtPlayerGreen;
	private JTextField txtPlayerYellow;
	private JCheckBox chbDicePlayerBlue = new JCheckBox("");
	private JCheckBox chbDicePlayerOrange = new JCheckBox("");
	private JCheckBox chbDicePlayerPurple = new JCheckBox("");
	private JCheckBox chbDicePlayerRed = new JCheckBox("");
	private JCheckBox chbDicePlayerGreen;
	private JCheckBox chbDicePlayerYellow;
	private JCheckBox chbCpuPlayerBlue = new JCheckBox();
	private JCheckBox chbCpuPlayerOrange = new JCheckBox();
	private JCheckBox chbCpuPlayerPurple = new JCheckBox();
	private JCheckBox chbCpuPlayerRed = new JCheckBox();
	private JCheckBox chbCpuPlayerGreen;
	private JCheckBox chbCpuPlayerYellow;
	private static String[] names = new String[6];
	private static Boolean[] thieves = new Boolean[6];
	private static Boolean[] humans = new Boolean[6];
	
	public RegistrationPlayersImpl(){
		
	}
	
	public RegistrationPlayersImpl(int nPlayers, JFrame framePrincipale, JFrame modality) {
		
		JFrame registration = new JFrame();
		if (nPlayers == 6) {
			registration.setSize(600, 400);
		} else {
			registration.setSize(600, 300);
		}
    	registration.getContentPane().setLayout(null);
    	framePrincipale.setEnabled(false);
    	modality.setEnabled(false);
    	registration.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	registration.addWindowListener(new WindowAdapter() {
	    	public void windowClosing (WindowEvent e)
	    	{	
	    		registration.dispose();
	    		modality.setEnabled(true);
	    		modality.requestFocus();
	    	}
	    });
    	
    	txtPlayerBlue.setBounds(268, 45, 116, 22);
    	registration.getContentPane().add(txtPlayerBlue);
    	txtPlayerBlue.setColumns(10);
    	controlTextField(txtPlayerBlue);

    	txtPlayerOrange.setBounds(268, 80, 116, 22);
    	registration.getContentPane().add(txtPlayerOrange);
    	txtPlayerOrange.setColumns(10);
    	controlTextField(txtPlayerOrange);

    	txtPlayerPurple.setBounds(268, 115, 116, 22);
    	registration.getContentPane().add(txtPlayerPurple);
    	txtPlayerPurple.setColumns(10);
    	controlTextField(txtPlayerPurple);

    	txtPlayerRed.setBounds(268, 150, 116, 22);
    	registration.getContentPane().add(txtPlayerRed);
    	txtPlayerRed.setColumns(10);
    	controlTextField(txtPlayerRed);
    	
    	chbCpuPlayerBlue.setBounds(420, 45, 25, 25);
    	chbCpuPlayerBlue.addActionListener(l -> {
    		if (chbCpuPlayerBlue.isSelected()) {
        		txtPlayerBlue.setEditable(false);
        		txtPlayerBlue.setText("PlayerCPU1");
        	} else {
        		txtPlayerBlue.setEditable(true);
        		txtPlayerBlue.setText(null);
        	}
    	});
    	registration.getContentPane().add(chbCpuPlayerBlue);
    	
    	chbCpuPlayerOrange.setBounds(420, 80, 25, 25);
    	chbCpuPlayerOrange.addActionListener(l -> {
    		if (chbCpuPlayerOrange.isSelected()) {
        		txtPlayerOrange.setEditable(false);
        		txtPlayerOrange.setText("PlayerCPU2");
        	} else {
        		txtPlayerOrange.setEditable(true);
        		txtPlayerOrange.setText(null);
        	}
    	});
    	registration.getContentPane().add(chbCpuPlayerOrange);
    	
    	chbCpuPlayerPurple.setBounds(420, 115, 25, 25);
    	chbCpuPlayerPurple.addActionListener(l -> {
    		if (chbCpuPlayerPurple.isSelected()) {
        		txtPlayerPurple.setEditable(false);
        		txtPlayerPurple.setText("PlayerCPU3");
        	} else {
        		txtPlayerPurple.setEditable(true);
        		txtPlayerPurple.setText(null);
        	}
    	});
    	registration.getContentPane().add(chbCpuPlayerPurple);
    	
    	chbCpuPlayerRed.setBounds(420, 150, 25, 25);
    	chbCpuPlayerRed.addActionListener(l -> {
    		if (chbCpuPlayerRed.isSelected()) {
        		txtPlayerRed.setEditable(false);
        		txtPlayerRed.setText("PlayerCPU4");
        	} else {
        		txtPlayerRed.setEditable(true);
        		txtPlayerRed.setText(null);
        	}
    	});
    	registration.getContentPane().add(chbCpuPlayerRed);
    	
    	JLabel lblPlayer1 = new JLabel("Player BLUE name: ");
    	lblPlayer1.setBounds(120, 48, 136, 16);
    	registration.getContentPane().add(lblPlayer1);
    	
    	JLabel lblPlayer2 = new JLabel("Player ORANGE name: ");
    	lblPlayer2.setBounds(120, 80, 136, 16);
    	registration.getContentPane().add(lblPlayer2);
    	
    	JLabel lblPlayer3 = new JLabel("Player PURPLE name: ");
    	lblPlayer3.setBounds(120, 115, 136, 16);
    	registration.getContentPane().add(lblPlayer3);
    	
    	JLabel lblPlayer4 = new JLabel("Player RED name: ");
    	lblPlayer4.setBounds(120, 150, 136, 16);
    	registration.getContentPane().add(lblPlayer4);
    	
    	
		chbDicePlayerBlue.setBounds(498, 39, 35, 35);
    	registration.getContentPane().add(chbDicePlayerBlue);

		chbDicePlayerOrange.setBounds(498, 74, 35, 35);
    	registration.getContentPane().add(chbDicePlayerOrange);

		chbDicePlayerPurple.setBounds(498, 109, 35, 35);
    	registration.getContentPane().add(chbDicePlayerPurple);

		chbDicePlayerRed.setBounds(498, 145, 35, 33);
    	registration.getContentPane().add(chbDicePlayerRed);
    	
    	if (nPlayers==6) {
	    	txtPlayerGreen = new JTextField();
	    	txtPlayerGreen.setBounds(268, 185, 116, 22);
	    	registration.getContentPane().add(txtPlayerGreen);
	    	txtPlayerGreen.setColumns(10);
	    	controlTextField(txtPlayerGreen);
	    	
	    	txtPlayerYellow = new JTextField();
	    	txtPlayerYellow.setBounds(268, 220, 116, 22);
	    	registration.getContentPane().add(txtPlayerYellow);
	    	txtPlayerYellow.setColumns(10);
	    	controlTextField(txtPlayerYellow);
	    	
	    	chbCpuPlayerGreen = new JCheckBox();
	    	chbCpuPlayerGreen.setBounds(420, 185, 25, 25);
	    	chbCpuPlayerGreen.addActionListener(l -> {
	    		if (chbCpuPlayerGreen.isSelected()) {
	        		txtPlayerGreen.setEditable(false);
	        		txtPlayerGreen.setText("PlayerCPU5");
	        	} else {
	        		txtPlayerGreen.setEditable(true);
	        		txtPlayerGreen.setText(null);
	        	}
	    	});
	    	registration.getContentPane().add(chbCpuPlayerGreen);
	    	
	    	chbCpuPlayerYellow = new JCheckBox();
	    	chbCpuPlayerYellow.setBounds(420, 220, 25, 25);
	    	chbCpuPlayerYellow.addActionListener(l -> {
	    		if (chbCpuPlayerYellow.isSelected()) {
	        		txtPlayerYellow.setEditable(false);
	        		txtPlayerYellow.setText("PlayerCPU6");
	        	} else {
	        		txtPlayerYellow.setEditable(true);
	        		txtPlayerYellow.setText(null);
	        	}
	    	});
	    	registration.getContentPane().add(chbCpuPlayerYellow);
	    	
	    	JLabel lblPlayer5 = new JLabel("Player YELLOW name: ");
	    	lblPlayer5.setBounds(120, 185, 136, 16);
	    	registration.getContentPane().add(lblPlayer5);
	    	
	    	JLabel lblPlayer6 = new JLabel("Player GREEN name: ");
	    	lblPlayer6.setBounds(120, 220, 136, 16);
	    	registration.getContentPane().add(lblPlayer6);
	    	
	    	chbDicePlayerGreen = new JCheckBox();
	    	chbDicePlayerGreen.setBounds(500, 185, 25, 25);
	    	registration.getContentPane().add(chbDicePlayerGreen);
	    	
	    	chbDicePlayerYellow = new JCheckBox();
			chbDicePlayerYellow.setBounds(500, 220, 25, 25);
	    	registration.getContentPane().add(chbDicePlayerYellow);
    	}
    	Font f = new Font("Calibri", 20, 20);
    	
    	JLabel lblCpu = new JLabel("CPU");
    	lblCpu.setBounds(415, 13, 35, 23);
    	lblCpu.setFont(f);
    	registration.getContentPane().add(lblCpu);
    	registration.setLocationRelativeTo(null);
    	registration.setResizable(false);
    	registration.setVisible(true);
    	
    	JButton play =  new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/play.png")));
    	if (nPlayers == 6) {
       	 	play.setLocation(420, 319);
    	} else {
   	 		play.setLocation(420, 219);
    	}
		play.setSize(162, 33);
		play.setRolloverEnabled(true);
		play.setFocusPainted(false);
		play.setBorderPainted(false);
		play.setContentAreaFilled(false);
		play.addActionListener(l-> {
			if (nPlayers == 4) {
				if (!txtPlayerBlue.isEditable() && !txtPlayerOrange.isEditable() && !txtPlayerPurple.isEditable() && !txtPlayerRed.isEditable())
				{
					JOptionPane.showMessageDialog(null, "At least one player must be present.");
				} else if (txtPlayerBlue.getText().isEmpty() || txtPlayerOrange.getText().isEmpty() || txtPlayerPurple.getText().isEmpty() || txtPlayerRed.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Every player must have a name.");
				} else {
					framePrincipale.dispose();
					modality.dispose();
					registration.dispose();
					
					initializePlayers(4);
					
					ApplicationImpl appC = new ApplicationImpl();
					appC.startMatch(nPlayers, false);
					
					new Start4Impl(txtPlayerBlue.getText(), txtPlayerOrange.getText(), txtPlayerPurple.getText(), txtPlayerRed.getText(), false);				
				}
			} else {
				if (!txtPlayerBlue.isEditable() && !txtPlayerOrange.isEditable() && !txtPlayerPurple.isEditable() && !txtPlayerRed.isEditable() && !txtPlayerGreen.isEditable() && !txtPlayerYellow.isEditable())
				{
					JOptionPane.showMessageDialog(null, "At least one player must be present.");
				} else if (txtPlayerBlue.getText().isEmpty() || txtPlayerOrange.getText().isEmpty() || txtPlayerPurple.getText().isEmpty() || txtPlayerRed.getText().isEmpty() || txtPlayerGreen.getText().isEmpty() || txtPlayerYellow.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Every player must have a name.");
				} else {
					framePrincipale.dispose();
					modality.dispose();
					registration.dispose();
					
					initializePlayers(6);
					
					ApplicationImpl appC = new ApplicationImpl();
					appC.startMatch(nPlayers, false);
					
					new Start6Impl(txtPlayerBlue.getText(), txtPlayerOrange.getText(), txtPlayerPurple.getText(), txtPlayerRed.getText(), txtPlayerGreen.getText(), txtPlayerYellow.getText(), false);		
				}
			}
		});
		registration.getContentPane().add(play);
    	
	    JButton turnBack = new JButton("");
	    if (nPlayers == 6) {
	    	turnBack.setLocation(12, 288);
		} else {
	    	turnBack.setLocation(12, 188);
	    }
	    turnBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/turn_back.png")));
	    turnBack.setRolloverEnabled(true);
	    turnBack.setFocusPainted(false);
	    turnBack.setBorderPainted(false);
	    turnBack.setContentAreaFilled(false);
	    turnBack.setSize(84, 64);
	    turnBack.addActionListener(l-> {
	    	registration.dispose();
	    	modality.setEnabled(true);
	    	modality.requestFocus();
	    });
	    registration.getContentPane().add(turnBack);
	    
	    JLabel lblDiceFalse = new JLabel("DICE FALSE");
	    lblDiceFalse.setBounds(471, 16, 91, 16);
	    registration.getContentPane().add(lblDiceFalse);
	    lblDiceFalse.setFont(f);
	    
	}
	
	 public void initializePlayers(int nPlayers){
		names[0] = txtPlayerBlue.getText();
		names[1] = txtPlayerOrange.getText();
		names[2] = txtPlayerPurple.getText();
		names[3] = txtPlayerRed.getText();
		humans[0] = !chbCpuPlayerBlue.isSelected();
		humans[1] = !chbCpuPlayerOrange.isSelected();
		humans[2] = !chbCpuPlayerPurple.isSelected();
		humans[3] = !chbCpuPlayerRed.isSelected();
		thieves[0] = chbDicePlayerBlue.isSelected();
		thieves[1] = chbDicePlayerOrange.isSelected();
		thieves[2] = chbDicePlayerPurple.isSelected();
		thieves[3] = chbDicePlayerRed.isSelected();
		if (nPlayers == 6) {
			names[4] = txtPlayerGreen.getText();
			names[5] = txtPlayerYellow.getText();
			humans[4] = !chbCpuPlayerGreen.isSelected();
			humans[5] = !chbCpuPlayerYellow.isSelected();
			thieves[4] = chbDicePlayerGreen.isSelected();
			thieves[5] = chbDicePlayerYellow.isSelected();
		}
	}
	
	public String[] getNames(){
		return names;
	}
	
	public Boolean[] getThieves(){
		return thieves;
	}
	
	public Boolean[] getHumans(){
		return humans;
	}
	
	public void controlTextField(JTextField txtPlayer){
		txtPlayer.setDocument(new PlainDocument() {
			private static final long serialVersionUID = 1L;
			
			int maxChar = 10;
			public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
				if(getLength() >= maxChar) return;
					super.insertString(offset,str,a);
			}
		});
	}
}
