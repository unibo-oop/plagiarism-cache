package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Classe utilizzata per visualizzare il Log
 * della propria repository
 *
 * @author Filippo Solazzi
 *
 */
public class Log_View extends JFrame{

	private static final long serialVersionUID = 7315245824750770316L;
	final GridBagConstraints  cnst = new  GridBagConstraints ();
	final List<JLabel> nome = new ArrayList<>();
	final List<JLabel> valore = new ArrayList<>();
	
	/**
	* Costruttore che inizializza la propria View
	*
	* @param list
	*          Questa è la lista da gestire per visualizzare in modo corretto il Log
	*          che viene passato dal Control
	*/
	public Log_View(List<String> list){
		
		this.setTitle("Prova");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(320,385);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel bp = new JPanel();
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		gridPanel.setAutoscrolls(true);
		
		cnst.gridy = 0;								// 1-a riga
		cnst.insets = new  Insets(3,3,3,3);         //  spazio  attorno  al comp.
		cnst.fill = GridBagConstraints.HORIZONTAL;     //  estensione  in  orizzont.
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).equals("changeset:") && i != 0){
				gridPanel.add(new JLabel(" "), cnst);
				cnst.gridy ++;
			}
			if(i % 2 == 0){
				nome.add(new JLabel(list.get(i)));
				nome.get(i / 2).setForeground(Color.RED);
				gridPanel.add(nome.get(i / 2), cnst);
			}else{
				valore.add(new JLabel(list.get(i)));
				valore.get(i / 2).setForeground(Color.BLUE);
				gridPanel.add(valore.get(i / 2), cnst);
				cnst.gridy ++;
			}
		}
		
		JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 10, 300, 300);
		
		JButton b = new JButton("OK");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		JPanel tp = new JPanel(null);
		tp.setPreferredSize(new Dimension(310, 310));
		tp.add(scrollPane);
		bp.add(b);
		this.add(tp, BorderLayout.NORTH);
		this.add(bp, BorderLayout.SOUTH);
	}
}
