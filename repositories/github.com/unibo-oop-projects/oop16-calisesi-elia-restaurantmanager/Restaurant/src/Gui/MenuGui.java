package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import menu.MenuItem;
import menu.MenuImpl;
import menu.MenuModel;

public class MenuGui {
	
	private LinkedList<JButton> jb;
	private MenuModel m;
	
	public MenuGui() throws ClassNotFoundException, IOException {
		this.m = new MenuImpl();
		JFrame jf = new JFrame("Menu");
		BorderLayout bl = new BorderLayout(5,5);
		bl.setHgap(5);
		bl.setVgap(5);
		jf.setLayout(bl);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JPanel pLeft = new JPanel();
		JPanel pRight = new JPanel();
		pLeft.setLayout(new GridLayout(6,6));
		this.updateView();
		for (JButton b : this.jb) {
			pLeft.add(b);
		}
		
		JButton add = new JButton("Add Item to Menu");
		
		add.addActionListener( e  -> {
			jf.setAlwaysOnTop(false);
			String ans1 = JOptionPane.showInputDialog("Insert product's name");
			if ( ans1 != null) {
				if (!m.isPresentInMenu(ans1)) {
					String ans = JOptionPane.showInputDialog("Insert product's price");
					try {
						ans = ans.replace(",", ".");
						m.addItem(ans1, Double.parseDouble(ans));
						pLeft.removeAll();
						pLeft.repaint();
						this.updateView();
						for (JButton b : this.jb) {
							pLeft.add(b);
						}
						pLeft.revalidate();
						jf.revalidate();
						jf.setAlwaysOnTop(true);
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "The product alredy exists","Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}			
		});
		JButton remove = new JButton("Remove Item");
		remove.setSize(20, 20);
		remove.addActionListener(e-> {
			try {
				jf.setAlwaysOnTop(false);
				if (!searchActiveTable()) {
					try {
						JDialog jd = new JDialog();
						jd.setLocationRelativeTo(jf);
						jd.setSize(new Dimension(150,100));
						JComboBox<String> cb = new JComboBox<>();
						cb.setSize(cb.getPreferredSize());
						for (MenuItem item : m.getMenu()) {
							cb.addItem(item.getName());
						}
						jd.add(cb);				
						cb.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								try {
									 int ans = JOptionPane.showConfirmDialog(null ,
											 "Are you sure?\nThis Operation will cancel "
											+ "the item from menu", "Attention!", JOptionPane.OK_OPTION);
									 if (ans == 0) {
										m.removeItem((String)cb.getSelectedItem());
										pLeft.removeAll();
										pLeft.repaint();
										updateView();
										for (JButton b : jb) {
											pLeft.add(b);
										}
										pLeft.revalidate();
										jf.revalidate();
										jf.setAlwaysOnTop(true);
										jd.dispose(); 
									 }
								} catch (IOException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						jd.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "You cannot remove item while table is open!","Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SecurityException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		pRight.add(add);
		jf.add(pLeft,BorderLayout.WEST);
		pRight.add(remove);
		jf.add(pRight,BorderLayout.EAST);
		jf.setAlwaysOnTop(true);
		jf.setVisible(true);
		
	}
	
	private boolean searchActiveTable() throws FileNotFoundException, IOException {
		int n = this.readFromFile();
		String tab;
		for (int i=1; i<=n ;i++) {
			tab = "Table" + Integer.toString(i) + ".txt";
			if (new File(tab).exists()) {
				return true;
			}
		}
		return false;
	
	}
	
	private int readFromFile() throws FileNotFoundException, IOException {
		int number;
		try (BufferedReader r = new BufferedReader(new FileReader(Lounge.f.getAbsolutePath()))) {
			number = Integer.parseInt(r.readLine());
			r.close();			
		}
		return number;
	}

	private void updateView() throws ClassNotFoundException, IOException {
		this.jb = new LinkedList<>();
		for (MenuItem mi : m.getMenu()) {
			if(!this.jb.contains(mi))
				jb.add(new JButton(mi.getName()));
		}
		for (JButton butt : this.jb) {
			butt.addActionListener( e -> {
				try {
					new MenuItemGui(butt.getText(),m);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			});
		}
	}
	
}
