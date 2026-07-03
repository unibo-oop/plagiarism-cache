package Gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Lounge{
	
	private List<String> nameRest;
	private int number;
	private Color c = new Color(211,211,211);
	public static File f = new File("data.dat");
	public static final File NAME  = new File("gen.dat");  
	private List<JButton> tables;
	
	public Lounge() throws IOException {
		if (NAME.createNewFile()){
			this.writeOnFile("insert your name, address from settings", Lounge.NAME);
		}
		if (f.createNewFile()) {
			this.number = Integer.parseInt(JOptionPane.showInputDialog("Insert the Number of Table"));
			this.writeOnFile(Integer.toString(this.number),f);
		} else {
			this.readFromFile();
		}
		JFrame jf = new JFrame();
		jf.setTitle("Home");
		jf.setResizable(true);
		JPanel jp = new JPanel();
		GridLayout gd = new GridLayout(5,3);
		jf.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JMenuBar jbar = new JMenuBar();
		JMenu menuFile = new JMenu("Setup");
		JMenuItem menu = new JMenuItem("Menu settings");
		JMenuItem resName = new JMenuItem("Name Restaurant");
		JMenu table = new JMenu("Table");
		JMenuItem nTable = new JMenuItem("Set number of table");
    	JMenu colorTable = new JMenu("Set color of tables");	
    	JRadioButton g = new JRadioButton ("Green");
    	JRadioButton y = new JRadioButton ("Yellow");
    	JRadioButton c = new JRadioButton ("Cyan");
    	JRadioButton d = new JRadioButton ("Default");
    	colorTable.add(g);
    	colorTable.add(y);
    	colorTable.add(c);
    	colorTable.add(d);
    	ButtonGroup bg = new ButtonGroup();
    	bg.add(g);
    	bg.add(y);
    	bg.add(c);
    	bg.add(d);
    	
    	JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener( e -> {
			System.exit(0);
		});
		nTable.addActionListener(e -> {
				try {
					this.tables = new LinkedList<>();
					this.setTables();
					jp.removeAll();
					jp.repaint();
					this.updateLounge();
					for (JButton b : this.tables) {
						jp.add(b);
					}
					jp.revalidate();
					jf.revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		});	
		gd.setHgap(4);
		gd.setVgap(4);
		jp.setLayout(gd);
		
		this.tables = new LinkedList<>(); 
		this.updateLounge();
		for (JButton b : this.tables) {
			jp.add(b);
		}
		g.addActionListener(e-> {
			this.setColor(Color.GREEN);
		});
		y.addActionListener(e-> {
			this.setColor(Color.YELLOW);
		});
		c.addActionListener(e-> {
			this.setColor(Color.CYAN);
		});
		d.addActionListener(e-> {
			this.setColor(Color.LIGHT_GRAY);
		});
		resName.addActionListener(e -> {
			this.setName();
		});
		
		jf.add(jp);
		menuFile.add(menu);
		menuFile.add(resName);
		table.add(nTable);
		table.add(colorTable);
		menuFile.addSeparator();
		menuFile.add(exit);
		menu.addActionListener(e -> {
			try {
				new MenuGui();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});
		jbar.add(menuFile);
		jbar.add(table);
		jf.setJMenuBar(jbar);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		jf.setVisible(true);
	}
	
	private void setName() {
		nameRest = new ArrayList<>(4);
		JDialog jd = new JDialog();
		JTextArea jTe = new JTextArea("Insert restaurant's name");
		JTextArea jTe2 = new JTextArea("Insert restaurant's address");
		JTextArea jTe3 = new JTextArea("Insert restaurant's phone");
		JTextArea jTe4 = new JTextArea("Insert restaurant's mail");
		GridLayout grid = new GridLayout(15,30);
		grid.setVgap(10);
		jd.setLayout(grid);
		jTe2.setLineWrap(true);
		jTe3.setLineWrap(true);
		jTe4.setLineWrap(true);
		jTe.setLineWrap(true);
		JButton jok = new JButton("Ok");
		jTe.setEditable(true);
		jTe.setVisible(true);
		jok.addActionListener(e -> {
			Lounge.NAME.delete();
			nameRest.add(jTe.getText());
			nameRest.add(jTe2.getText());
			nameRest.add(jTe3.getText());
			nameRest.add(jTe4.getText());
			try {
				for(String s : this.nameRest) {
					this.writeOnFile(s, Lounge.NAME);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jd.dispose();
		});
		jd.setSize(600,600);
	  	jd.add(jTe);
	  	jd.add(jTe2);
	  	jd.add(jTe3);
	  	jd.add(jTe4);
		jd.add(jok);
		jd.setVisible(true);
	}

	private void readFromFile() throws IOException {
		try (BufferedReader r = new BufferedReader(new FileReader(f))) {
			this.number = Integer.parseInt(r.readLine());
			r.close();			
		}
	}

	private void setTables() throws IOException {
		String ans = JOptionPane.showInputDialog("Insert the Number of Table");
		this.number = Integer.parseInt(ans);
		Lounge.f.delete();
		this.writeOnFile(Integer.toString(this.number),Lounge.f);
	}
	
	private void writeOnFile(final String i, final File f) throws IOException {		
		try(BufferedWriter w = new BufferedWriter(new FileWriter(f.getAbsolutePath(),true))) {
			w.append(i);
			w.newLine();
			w.close();
		}
	}
	private void updateLounge() throws IOException {
		for (int i=0;i<number;i++) {
			tables.add(new JButton(Integer.toString(i+1)));
		}
		for (JButton jbutt : tables) {
			jbutt.setBackground(this.c);
			jbutt.addActionListener(e -> {
				try {
					new TableGui(Integer.parseInt(jbutt.getText()));
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			});
		}
	}
	
	private void setColor(final Color c) {
		for (JButton b : this.tables) {
			if(!(b.getBackground().equals(Color.RED)))
				b.setBackground(c);
		}
	}
	
	public static void main(String args[]) throws IOException {
		new Lounge();
	}
}

