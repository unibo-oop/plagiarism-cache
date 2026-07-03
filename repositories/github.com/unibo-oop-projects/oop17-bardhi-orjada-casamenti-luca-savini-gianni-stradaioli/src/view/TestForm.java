package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.IntStream;

import javax.swing.WindowConstants;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
public class TestForm extends Home{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method that return at Home if you close the page
	 */
	class WindowEventHandler extends WindowAdapter {
		  public void windowClosing(WindowEvent evt) { // operazione che si attiva alla chiusura della finestra
			  EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Home window = new Home();
							window.frameHome.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		  }
	}
	
	JFrame frame;
	public JComboBox<Integer> comboPlayer;
	public JComboBox<Integer> comboPoint;
	public JComboBox<Integer> comboLife;
	public JComboBox<Integer> comboCash;
	private static int nPlayer = 2;
	private static int Life = 10;
	private static int Cash = 0;
	private static int Point = 20;

	/**
	 * Create the application.
	 */
	public TestForm() {
		initialize();
	}
	/* costruttore con elementi*/
	public TestForm(int nPlayer, int Life, int Cash, int Point) {
		TestForm.nPlayer = nPlayer;
		TestForm.Life = Life;
		TestForm.Cash = Cash;
		TestForm.Point = Point;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.addWindowListener(new WindowEventHandler());
		frame.getContentPane().setBackground(new Color (176,224,230));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Benvenuti in King of Forli'!");
		lblWelcome.setBounds(117, 12, 197, 15);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblPlayer = new JLabel("Seleziona il numero di giocatori: ");
		lblPlayer.setBounds(46, 63, 243, 15);
		frame.getContentPane().add(lblPlayer);
	
		comboPlayer = new JComboBox<>();
		comboPlayer.setBounds(354, 58, 50, 24);
		IntStream.range(2, 5).forEach(comboPlayer::addItem);
		comboPlayer.setSelectedItem(nPlayer);

		frame.getContentPane().add(comboPlayer);
		
		JLabel lblPoint = new JLabel("Seleziona il punteggio da raggiungere: ");
		lblPoint.setBounds(46, 99, 290, 15);
		frame.getContentPane().add(lblPoint);
		
		comboPoint = new JComboBox<>();
		comboPoint.setBounds(354, 94, 50, 24);
		IntStream.range(2, 7).map(i -> i*5).forEach(comboPoint::addItem);
		comboPoint.setSelectedItem(Point);
		frame.getContentPane().add(comboPoint);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(240, 230, 117, 25);
		frame.getContentPane().add(btnAvanti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(80, 230, 117, 25);
		frame.getContentPane().add(btnIndietro);
		
		JLabel lblLife = new JLabel("Seleziona la vita di partenza: ");
		lblLife.setBounds(46, 132, 226, 15);
		frame.getContentPane().add(lblLife);
		
		JLabel lblCash = new JLabel("Seleziona il denaro iniziale:");
		lblCash.setBounds(46, 165, 218, 15);
		frame.getContentPane().add(lblCash);
		
		comboCash = new JComboBox<>();
		comboCash.setBounds(354, 160, 50, 24);
		IntStream.range(0, 11).forEach(comboCash::addItem);
		comboCash.setSelectedItem(Cash);
		frame.getContentPane().add(comboCash);

		
		comboLife = new JComboBox<>();
		comboLife.setBounds(354, 127, 50, 24);
		IntStream.range(2, 5).map(i -> i*5).forEach(comboLife::addItem);
		comboLife.setSelectedItem(Life);
		frame.getContentPane().add(comboLife);
//		comboLife.addItem(10);
//		comboLife.addItem(15);
//		comboLife.addItem(20);
		
		btnAvanti.addActionListener(e ->{	
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						NameForm window = new NameForm((int)comboPlayer.getSelectedItem(), (int)comboLife.getSelectedItem(),(int)comboCash.getSelectedItem(), (int)comboPoint.getSelectedItem() );
						window.frameName.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frame.dispose();
		});
		
		btnIndietro.addActionListener(f -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Home window = new Home();
						window.frameHome.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frame.dispose();
		});
	}
}

