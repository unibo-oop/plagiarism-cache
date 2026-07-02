package View.cardlayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import util.ImageLoader;

public class ViewPrenotation extends JPanel
{

	//the index in the cardlayout stack of the main window
	private static final int index = 1;
	private final JButton nuovo;
	private JButton but;
    private JLabel colore;

	// contruttore principale
	public ViewPrenotation()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// buttone per aggiungere nuovi clienti
		this.nuovo = new JButton("new client");
		this.add(nuovo);
		table();
	}

	public void addActionListener(ActionListener listener)
	{
		this.nuovo.addActionListener(listener);
	}

	// creazione tavoli per ogni cliente
	public void table()
	{
		JPanel panel = new JPanel();
		JLabel tab = new JLabel("");
		tab.setIcon(new ImageIcon(getClass().getResource("/images/rsz_ico-table.jpg")));
		tab.setBounds(94, 67, 51, 55);

		this.colore = new JLabel("");
		this.colore.setIcon(new ImageIcon(getClass().getResource("/images/ico-pointvert.png")));
		this.colore.setBounds(122, 429, 34, 30);

		this.but = new JButton("prenota");
		but.setBounds(31, 381, 89, 23);
		
		this.add(but);
		this.add(this.colore);
		this.add(tab);
		this.add(panel);

	}
	
	public void update()
	{
		this.table();
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void addButtonListener(ActionListener listener)
	{
		this.but.addActionListener(listener);
	}
    
	public void setColore()
	{
		this.colore.setIcon(new ImageIcon(ImageLoader.getImage("/images/150605104437964180.png")));
	}
	
	public static int getIndex()
    {
    	return index;
    }
}
