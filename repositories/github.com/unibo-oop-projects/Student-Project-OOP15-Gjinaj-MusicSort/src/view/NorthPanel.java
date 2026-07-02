package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.MainController;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;

public class NorthPanel extends JPanel {

	/**
	 * 
	 */

	JPanel noMenuPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	MainController controller;
	String playlistFilePath;
	WestPanel westPanel; 
	private static final long serialVersionUID = 1L;

	public NorthPanel(MainController controller) {
		this.controller = controller;
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.CYAN);

		noMenuPanel.setBorder(UIManager.getBorder("Menu.border"));

		Choice choice = new Choice();
		choice.setBounds(827, 16, 89, 27);
		choice.setName("choice");
		choice.add("Sorting By");
		choice.add("Name");
		choice.add("Star");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		JMenu menu = new JMenu("Opzioni");
		menuBar.add(menu);

		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setBackground(Color.DARK_GRAY);
		menu.setForeground(Color.YELLOW);
		menu.setBounds(0, 0, 146, 22);

		noMenuPanel.setLayout(new BoxLayout(noMenuPanel, BoxLayout.X_AXIS));
		menuPanel.setLayout(new BorderLayout(0, 0));

		JMenuItem menuCreaPlaylist = new JMenuItem("Crea Playlist");
		menu.add(menuCreaPlaylist);

		JMenuItem menuCaricaPlaylist = new JMenuItem("Carica Playlist");
		//menu.add(menuCaricaPlaylist);

		JMenuItem menuEsci = new JMenuItem("Esci");
		menu.add(menuEsci);

		menuPanel.add(menuBar);
		menuEsci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.getLibraryManager().persistPlaylists();
				System.exit(0);
			}
		});
		menuCreaPlaylist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFrame newPlaylistFrame = new JFrame("Crea la tua Playlist");
				final JPanel panelChoosePlaylist = new JPanel();
				final JLabel lblChoosePlaylist = new JLabel("Inserisci il nome:");
				final JTextField edtNamePlaylist = new JTextField(10);
				final JButton btnCreatePlaylist = new JButton("Crea");
				btnCreatePlaylist.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(final ActionEvent e) {
						newPlaylistFrame.dispose();
						controller.createPlaylist(edtNamePlaylist.getText());
					}
				});

				panelChoosePlaylist.add(lblChoosePlaylist);
				panelChoosePlaylist.add(edtNamePlaylist);
				panelChoosePlaylist.add(btnCreatePlaylist);
				newPlaylistFrame.add(panelChoosePlaylist);
				newPlaylistFrame.setSize(new Dimension(400, 100));
				newPlaylistFrame.setLocation(300, 300);
				newPlaylistFrame.setVisible(true);
			}
		});

		menuCaricaPlaylist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = null;
				fileChooser = new JFileChooser();
				FileFilter Filter = new FileFilter() {
					@Override
					public String getDescription() {
						return "Playlist file (*.msort)";
					}

					@Override
					public boolean accept(File file) {
						if (file.isDirectory()) {
							return true;
						} else {
							return file.getName().toLowerCase().endsWith(".msort");
						}
					}
				};
				fileChooser.setFileFilter(Filter);
				fileChooser.setDialogTitle("Open Playlist File");
				fileChooser.setAcceptAllFileFilterUsed(false);
				int userChoice = fileChooser.showOpenDialog(fileChooser);
				if (userChoice == JFileChooser.APPROVE_OPTION) {
					playlistFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				} 

			}
		});
		//noMenuPanel.add(choice);
		JTextField txtSearch = new JTextField(0);
		
		txtSearch.setText("tba searching");
		txtSearch.setEditable(false);
		//noMenuPanel.add(txtSearch);
		setLayout(new BorderLayout(0, 0));
		new BorderLayout();
		this.add(menuPanel, BorderLayout.NORTH);
		this.add(noMenuPanel, BorderLayout.SOUTH);
	}

}
