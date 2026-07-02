package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class GUI_LogUtente extends GUI {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_LogUtente frame = new GUI_LogUtente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_LogUtente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblGeneraleHotel = new JLabel("Log");
		lblGeneraleHotel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		panel_1.add(lblGeneraleHotel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				save(getX(), getY(), getWidth(), getHeight());
                MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza inventario");

                frame = new GUI_GeneraleHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());

                frame.setVisible(true);
                setVisible(false);
				
			}
		});
		btnIndietro.setPreferredSize(new Dimension(117, 50));
		panel.add(btnIndietro);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JButton btnMostra = new JButton("Mostra");
		btnMostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				stampaLog(textPane, GUI.utenteAccesso);
			}
		});
		btnMostra.setPreferredSize(new Dimension(117, 50));
		panel.add(btnMostra);
		
		
	}

}
