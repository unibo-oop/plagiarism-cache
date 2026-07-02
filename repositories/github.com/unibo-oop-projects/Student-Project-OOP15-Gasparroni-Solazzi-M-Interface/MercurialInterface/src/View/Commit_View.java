package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Control;

/**
 * Classe utilizzata per inserire il commento per effettuare il commit
 * nella propria repository
 *
 * @author Filippo Solazzi
 *
 */
public class Commit_View extends JFrame{

	private static final long serialVersionUID = 3948271553377081701L;
	private JTextField commento = new JTextField(25);
	private JLabel label = new JLabel();
	private JButton ok = new JButton("OK");
	private JButton exit = new JButton("Annulla");
	
	/**
	* Costruttore che inizializza la propria View
	* con i rispettivi eventi
	*
	* @param ctr
	*          Control utilizzato per l'intercetto degli eventi
	*/
	public Commit_View(Control ctr){
		this.setTitle("Commit");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500,120);
		this.setLocationRelativeTo(null);
		
		label.setText("Inserire il commento");
		JPanel panel = new JPanel();
		JPanel bpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					ctr.commit(commento.getText());
					dispose();
			}
		});
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		panel.add(label);
		panel.add(commento);
		bpanel.add(ok);
		bpanel.add(exit);
		this.add(panel, BorderLayout.NORTH);
		this.add(bpanel, BorderLayout.SOUTH);
	}

}
