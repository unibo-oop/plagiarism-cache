package View;

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

public class ViewPrenotation extends JFrame
{

	private final JButton nuovo;
	private final JFrame finestra;
	private JButton but;

	// contruttore principale
	public ViewPrenotation()
	{
		this.finestra = new JFrame("finestra");
		Container contenuto = finestra.getContentPane();
		contenuto.setLayout(new BoxLayout(contenuto, BoxLayout.PAGE_AXIS));

		// buttone per aggiungere nuovi clienti
		this.nuovo = new JButton("new client");
		contenuto.add(nuovo);
		table(finestra);
		finestra.setDefaultCloseOperation(EXIT_ON_CLOSE);
		finestra.setSize(500, 600);
		finestra.setVisible(true);
	}

	public void update()
	{
		this.table(this.finestra);
		SwingUtilities.updateComponentTreeUI(this.finestra);
	}

	public void addActionListener(ActionListener listener)
	{
		this.nuovo.addActionListener(listener);
	}

	// creazione tavoli per ogni cliente
	public void table(JFrame frame)
	{
		JPanel panel = new JPanel();
		JLabel tab = new JLabel("");
		tab.setIcon(new ImageIcon(ImageLoader.getImage("rsz_ico-table.jpg")));
		tab.setBounds(94, 67, 51, 55);

		JLabel colore = new JLabel("");
		colore.setIcon(new ImageIcon(ImageLoader.getImage("ico-pointvert.png")));
		colore.setBounds(122, 429, 34, 30);

		this.but = new JButton("prenota");
		but.setBounds(31, 381, 89, 23);
		// cambiamento colore posto
		but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				colore.setIcon(new ImageIcon(ImageLoader.getImage("150605104437964180.png")));
				new ViewMenu();
			}
		});
		panel.add(but);
		panel.add(colore);
		panel.add(tab);
		frame.add(panel);

	}

	public void addButtonListener(ActionListener listener)
	{
		this.but.addActionListener(listener);
	}

}
