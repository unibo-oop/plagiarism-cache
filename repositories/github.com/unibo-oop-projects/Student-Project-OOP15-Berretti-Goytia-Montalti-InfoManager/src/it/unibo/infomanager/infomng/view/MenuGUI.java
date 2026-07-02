package it.unibo.infomanager.infomng.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
/**
 * Classe che definisce viewMenu.
 * @author Alessandro
 *
 */
public class MenuGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8257069661304151100L;
	private JFrame menu = this;
	private JPanel contentPane;
	private JLabel lblGestioneClienti = new JLabel("Clienti");
	private JLabel lblGestioneFornitori = new JLabel("Fornitori");
	private JLabel lblGestioneFattureAcquisti = new JLabel("Fatture");
	private JLabel lblGestioneScontrini = new JLabel("Scontrini");
	private JLabel lblGestioneMagazzino = new JLabel("Magazzino");
	private JLabel lblIvaAcquisti = new JLabel("Registri IVA");
	private JLabel lblReportVendite = new JLabel("Report Vendite");
	private JLabel lblGestioneRiunioni = new JLabel("Riunioni");
	private JLabel lblMenu = new JLabel("MENU");
	private JButton bClienti = new JButton("");
	private JButton bScontrini = new JButton("");
	private JButton bFornitori = new JButton("");
	private JButton bMagazzino = new JButton("");
	private JButton bFattAcqu = new JButton("");
	private JButton bIvaAcqu = new JButton("");
	private JButton bReport = new JButton("");
	private JButton bRiunioni = new JButton("");
	private JButton bLogout = new JButton("Logout");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel pAcquVend = new JPanel();
	private JPanel pAcqu = new JPanel();
	private JPanel pVend = new JPanel();
	private GroupLayout groupLayout;
	private static final String TITOLO = "Menu";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(938, 476);
	

	/**
	 * Costruttore del MenuGUI frame.
	 * @param o
	 * 			Oggetto ObserverInterface
	 */
	//CHECKSTYLE:OFF: checkstyle:magicnumber    
	public MenuGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MenuGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.groupLayout = new GroupLayout(contentPane);
		this.bClienti.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/Cliente.png")));
		this.bClienti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				o.mostraClienti();
				dispose();
			}
		});

		this.bScontrini.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/Scontrino.jpg")));
		this.bScontrini.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				o.mostraScontrini();
				dispose();
			}
		});
		
		this.bFornitori.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/Fornitore.jpg")));
		this.bFornitori.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				o.mostraFornitori();
				dispose();
			}
		});
		
		this.bMagazzino.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/Magazzino.jpg")));
		this.bMagazzino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				try{
					o.setAttuale(menu);
					o.mostraMagazzino();
					dispose();
				} catch (NullPointerException e1){
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Non sono ancora stati inseriti prodotti");
				} 
			}
		});
		
		this.bFattAcqu.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/Fattura.png")));
		this.bFattAcqu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				o.mostraFatture();
				dispose();
			}
		});
		
		this.bIvaAcqu.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/IVA.jpg")));
		this.bIvaAcqu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				try{
					o.setAttuale(menu);
					o.mostraRegistiIva();
					dispose();
				} catch (NullPointerException e1){
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Non è ancora stata registrata nessuna operazione con IVA");
				} 
			}
		});
		
		this.bReport.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/vendite-salgono-1024x1024.png")));
		this.bReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				try{
					o.setAttuale(menu);
					o.mostraReportVendite();
					dispose();
				} catch (NullPointerException e1){
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Non sono ancora state registrate vendite");
				} 
			}
		});
		
		this.bRiunioni.setIcon(new ImageIcon(LoginGUI.class.getResource("/view/icon/Riunione.jpg")));
		this.bRiunioni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				o.mostraRiunioni();
				dispose();
			}
		});
		
		this.bLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				o.start();
				dispose();
				
			}
		});
		
		this.lblMenu.setFont(new Font("Tahoma", Font.ITALIC, 48));
		this.tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		this.groupLayout.setHorizontalGroup(
				this.groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(bFornitori, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(bClienti, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(bReport, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(bFattAcqu, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblReportVendite, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneFattureAcquisti, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneFornitori, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneClienti, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(bScontrini, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addComponent(bMagazzino, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addComponent(bRiunioni, Alignment.LEADING,GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addComponent(bIvaAcqu, Alignment.LEADING,GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblGestioneMagazzino, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneScontrini, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIvaAcquisti, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneRiunioni, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMenu, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
							.addGap(292)
							.addComponent(bLogout, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addGap(104)))
					.addGap(33))
		);
		this.groupLayout.setVerticalGroup(
				this.groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(bLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
						.addComponent(lblMenu, GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bScontrini, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneScontrini, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(bClienti, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGestioneClienti, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(bFornitori, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(bMagazzino, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGestioneFornitori, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
								.addComponent(lblGestioneMagazzino, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bFattAcqu, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIvaAcquisti, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addComponent(lblGestioneFattureAcquisti, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addComponent(bIvaAcqu))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblGestioneRiunioni, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(bRiunioni, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addComponent(bReport, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReportVendite, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
							.addGap(31))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
							.addContainerGap())))
		);
		
		this.tabbedPane.addTab("Acquisti & Vendite", null, pAcquVend, null);
		this.tabbedPane.addTab("Acquisti", null, pAcqu, null);
		this.tabbedPane.addTab("Vendite", null, pVend, null);
		this.contentPane.setLayout(groupLayout);
		

	}
}
