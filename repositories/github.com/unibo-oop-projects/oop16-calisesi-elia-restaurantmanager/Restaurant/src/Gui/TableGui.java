package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.print.PrintException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import menu.MenuItem;
import menu.MenuImpl;
import menu.MenuModel;
import tables.TableModel;
import tables.TableImpl;

public class TableGui {
	public static final String[] columnName = {"Quantity","Description","Price"};
	private MenuModel menu = new MenuImpl();
	private TableModel m;
	private JTable tbl;
	public double disc = 0;
	
	TableGui(final int number) throws ClassNotFoundException, IOException {		
		m = new TableImpl(number);
		this.tbl = new JTable(m.getOrders(),columnName);
		tbl.setVisible(true);
		tbl.setFillsViewportHeight(true);
		JFrame jf = new JFrame();
		JPanel pRight = new JPanel();
		pRight.setLayout(new BorderLayout());
		jf.setTitle("Table " + number);
		jf.setResizable(true);
		jf.setExtendedState(Frame.MAXIMIZED_BOTH);
		jf.setLayout(new BorderLayout());
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new GridLayout(5,3));
		List<JButton> mb = new LinkedList<>();		//button per menuitem
		for (MenuItem m : menu.getMenu()) {
			mb.add(new JButton(m.getName()));
		}
		for (JButton jb : mb) {
			pLeft.add(jb);
			jb.addActionListener(e -> {
				try {
					m.addToOrder(jb.getText());
					this.refreshTable();
					jf.revalidate();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		}
		JButton bill = new JButton("Get Bill");
		bill.addActionListener(e -> { 
			jf.setAlwaysOnTop(false);
			if ((JOptionPane.showConfirmDialog(null ,"Are you sure?\nThis Operation will cancel "
					+ "all the table's orders", "Attention!", JOptionPane.OK_OPTION))== 0) {
				try {
					m.getBill(disc);
				} catch (FileNotFoundException | DocumentException e1) {
					e1.printStackTrace();
				} catch (PrintException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				jf.setAlwaysOnTop(true);
				jf.dispose();
			 }
		});
		JButton remove = new JButton("Remove");
		remove.addActionListener(e -> {
			jf.setAlwaysOnTop(false);
			try {
				JDialog jd = new JDialog();
				jd.setLocationRelativeTo(jf);
				jd.setSize(new Dimension(150,100));
				JComboBox<String> cb = new JComboBox<>();
				cb.setSize(cb.getPreferredSize());
				for (MenuItem m : menu.getMenu()) {
					cb.addItem(m.getName());
				}
				jd.add(cb);
				cb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						jd.dispose();
						String ans = JOptionPane.showInputDialog("Qty:");
						if (ans != null) {
							try {
								m.removeItem((String)cb.getSelectedItem(), Integer.parseInt(ans));
								refreshTable();
								jf.revalidate();
								jf.setAlwaysOnTop(true);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}	
					}
				});
				jd.setVisible(true);
			} catch (HeadlessException | NumberFormatException | IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		pRight.add(remove,BorderLayout.SOUTH);
		pRight.add(new JScrollPane(this.tbl),BorderLayout.CENTER);
		pRight.add(bill,BorderLayout.NORTH);
		JPanel pSouth = new JPanel();
		JButton var = new JButton("Discount");
		pSouth.add(var);
		var.addActionListener(e-> {
			jf.setAlwaysOnTop(false);
			String str = JOptionPane.showInputDialog("% Discount");
			str.replace(",",".");
			this.disc = Double.parseDouble(str);
		});
		jf.add(pSouth, BorderLayout.SOUTH);
		jf.add(pRight, BorderLayout.EAST);
		jf.add(pLeft, BorderLayout.WEST);
		jf.setAlwaysOnTop(true);
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (m.getOrders().length == 0 ) {
					m.deleteTable();
				}
			}
			public void windowIconified(WindowEvent e) {
				jf.setExtendedState(Frame.MAXIMIZED_BOTH);			
			}
		});
		jf.setVisible(true);
		
	}

	private void refreshTable() {
		DefaultTableModel dm = new DefaultTableModel(m.getOrders(),columnName);
		this.tbl.setModel(dm);
		this.tbl.setEnabled(false);
	}

}
