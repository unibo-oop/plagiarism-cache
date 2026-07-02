/**
 *@author Ceccarelli 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.observer.ViewObserver;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MainViewImpl extends JFrame implements MainView, ActionListener, WindowListener {
	private NorthPanelImpl northPanel;
	private ViewObserver observer;
	private JMenuBar menuBar;
	private JMenuItem mntmExit;
	private JMenuBar menuBar_1;
	private JMenu mnShop;
	private JMenuItem mntmSearchBook;
	private JMenuItem mntmManageSubscriptions;
	private JMenu mnWarehouse;
	private JMenuItem mntmOrderStocks;
	private JMenu mnFile;
	private JMenuItem mntmAddEmployee;
	private static final long serialVersionUID = 1L;
	private JMenuItem mntmRelocateBooks;
	private JPanel ImagePanel;
	private JLabel lblImage;

	public MainViewImpl() {
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setTitle("Library-Managment-System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);
		this.setSize(900, 700);

		new JFileChooser();

		Container c = this.getContentPane();
		northPanel = new NorthPanelImpl();
		c.add(northPanel, BorderLayout.NORTH);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/book.png")));
		ImagePanel = new JPanel();
		ImagePanel.setBackground(SystemColor.activeCaption);
		ImagePanel.setLayout(null);

		c.add(ImagePanel, BorderLayout.CENTER);

		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 894, 550);
		lblImage.setIcon(new ImageIcon(LoginPanel.class.getResource("/BookShop.png")));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		ImagePanel.add(lblImage);

		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(this);

		menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);

		mnFile = new JMenu("File");
		menuBar_1.add(mnFile);

		mntmExit = new JMenuItem("Esci");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);

		mnShop = new JMenu("Negozio");
		mnShop.setEnabled(false);
		menuBar_1.add(mnShop);

		mntmSearchBook = new JMenuItem("Cerca libro");
		mnShop.add(mntmSearchBook);
		mntmSearchBook.addActionListener(this);

		mntmManageSubscriptions = new JMenuItem("Gestisci abbonamenti");
		mnShop.add(mntmManageSubscriptions);

		mntmAddEmployee = new JMenuItem("Aggiungi dipendente");
		mnShop.add(mntmAddEmployee);
		mntmAddEmployee.addActionListener(this);
		mntmManageSubscriptions.addActionListener(this);

		mnWarehouse = new JMenu("Magazzino");
		mnWarehouse.setEnabled(false);
		menuBar_1.add(mnWarehouse);

		mntmOrderStocks = new JMenuItem("Ordina scorte");
		mnWarehouse.add(mntmOrderStocks);
		mntmOrderStocks.addActionListener(this);

		mntmRelocateBooks = new JMenuItem("Tasferisci libri");
		mnWarehouse.add(mntmRelocateBooks);
		mntmRelocateBooks.addActionListener(this);

		this.setVisible(true);

	}

	public void windowOpened(WindowEvent arg0) {
		this.observer.dataLoad();
	}

	public void windowClosing(WindowEvent arg0) {
		this.observer.saveData();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();

		if (isPressed == mntmExit) {
			this.observer.exitCommand();
		} else if (isPressed == mntmSearchBook) {
			this.observer.bookShopClicked();
		} else if (isPressed == mntmManageSubscriptions) {
			this.observer.addSubscriptionClicked();
		} else if (isPressed == mntmOrderStocks) {
			this.observer.addBooksClicked();
		} else if (isPressed == mntmAddEmployee) {
			this.observer.addEmployeeClicked();
		} else if (isPressed == mntmRelocateBooks) {
			this.observer.warehouseClicked();
		}

	}

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public void replaceMainPanel(JPanel panel) {
		this.resetFrame();
		Container c = this.getContentPane();
		c.add(panel, BorderLayout.CENTER);
		this.repaint();
		panel.updateUI();
	}

	@Override
	public NorthPanel getNorthPanel() {
		return this.northPanel;
	}

	@Override
	public void attachObserver(ViewObserver observer) {
		this.observer = observer;

	}

	private void resetFrame() {
		Container c = this.getContentPane();
		c.removeAll();
		c.add(northPanel, BorderLayout.NORTH);
	}

	@Override
	public void changeLogStatus(Boolean logged) {
		if (logged == true) {
			mnShop.setEnabled(true);
			mnWarehouse.setEnabled(true);
		} else {
			mnShop.setEnabled(false);
			mnWarehouse.setEnabled(false);
		}

	}
}