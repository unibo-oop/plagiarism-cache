package hollowmen.view.ale;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class ValueManager extends JLabel{
	
	private static final long serialVersionUID = -6890648156245232259L;
	private Border border= BorderFactory.createLineBorder(Color.BLACK, 3, true);
	private Font fontA=new Font("Chiller",Font.BOLD, 35);
	private String internalText; 
		
	public ValueManager(String internalText, Color color){
		
		this.internalText=internalText;
		this.setLayout(null);
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.setBorder(border);
		this.setForeground(color);
		this.setFont(fontA);
		this.setVisible(true);
			
	}
		
	public void updateValue(int c, Color color){
		this.setForeground(color);
		this.setText(this.internalText+":"+c);
		this.setHorizontalAlignment(CENTER);
	}
}
