package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.filechooser.FileFilter;

import controller.MainController;
import util.ProgressBarThread;
public class SouthPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonOpen = new JButton("Apri");
	private JButton buttonPlay = new JButton();
	private JButton buttonNext = new JButton();
	private JButton buttonBack = new JButton();
	private JLabel labelFileReproducing = new JLabel("File in riprodzione: nessun File");
	private JLabel labelTimeCounter = new JLabel("-:-:-");
	private JLabel labelDuration = new JLabel("-:-:-");
	private JPanel sEstPanel = new JPanel();
	private MainController controller;
	private JSlider progressSlider = new JSlider();
	

	
	
	
	private ImageIcon iconOpen = new ImageIcon(getClass().getResource(
			"/images/Open.png"));
	private ImageIcon iconPlay = new ImageIcon(getClass().getResource(
			"/images/play-icon.png"));
	private ImageIcon iconPause = new ImageIcon(getClass().getResource(
			"/images/pause-icon.png"));
	private ImageIcon iconNext = new ImageIcon(getClass().getResource(
			"/images/next.png"));
	private ImageIcon iconBack = new ImageIcon(getClass().getResource(
			"/images/back.png"));

	public ProgressBarThread barThread=null;
	
	public SouthPanel(MainController controller) {
		// TODO Auto-generated constructor stub
		progressSlider.setValue(0);
		progressSlider.setEnabled(false);
		this.controller= controller;
		Image btnplay = iconPlay.getImage(); // transform it 
		Image newimgPlay = btnplay.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconPlay = new ImageIcon(newimgPlay);

		Image btnPauseScaled = iconPause.getImage(); // transform it 
		Image newimgPause = btnPauseScaled.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconPause = new ImageIcon(newimgPause);

		barThread = new ProgressBarThread(progressSlider, this, controller,labelTimeCounter);
		buttonBack.setIcon(iconBack);
		buttonNext.setIcon(iconNext);
		buttonPlay.setIcon(iconPlay);
		buttonOpen.setIcon(iconOpen);
		
		buttonPlay.setEnabled(false);
		buttonNext.setEnabled(false);
		buttonBack.setEnabled(false);
		
		
		this.setLayout(new GridLayout());
		this.add(buttonOpen);
		this.add(buttonBack);
		this.add(buttonPlay);
		this.add(buttonNext);
		
		sEstPanel.setLayout(new BorderLayout());
		sEstPanel.add(labelFileReproducing, BorderLayout.NORTH);
		sEstPanel.add(labelTimeCounter,BorderLayout.WEST);
		sEstPanel.add(progressSlider,BorderLayout.CENTER);
		sEstPanel.add(labelDuration,BorderLayout.EAST);
        this.add(sEstPanel);
        
		this.setBounds(101, 0, 60, 63);
		this.setForeground(Color.DARK_GRAY);
		buttonOpen.addActionListener(MusicListener);
		buttonPlay.addActionListener(MusicListener);
		buttonNext.addActionListener(MusicListener);
		buttonBack.addActionListener(MusicListener);
		
	}

	public void setPlayIcon( ) {
			buttonPlay.setIcon(iconPlay);
	}
	public void setPauseIcon( ) {
		buttonPlay.setIcon(iconPause);
}

	ActionListener MusicListener = new ActionListener() {
		

		@Override
		public void actionPerformed(ActionEvent event)  {
			// TODO Auto-generated method stub
			Object source = event.getSource();
			if (source instanceof JButton) {
				JButton button = (JButton) source;
				if (button == buttonOpen) {
					String audioFilePath = null;
					JFileChooser fileChooser = null;
					fileChooser = new JFileChooser();
					FileFilter Filter = new FileFilter() {
						@Override
						public String getDescription() {
							return "Sound file (*.mp3)";
						}
						@Override
						public boolean accept(File file) {
							if (file.isDirectory()) {
								return true;
							} else {
								return file.getName().toLowerCase().endsWith(".mp3");
							}
						}
					};
					fileChooser.setFileFilter(Filter);
					fileChooser.setDialogTitle("Open Audio mp3 File");
					fileChooser.setAcceptAllFileFilterUsed(false);
					int userChoice = fileChooser.showOpenDialog(fileChooser);
					if (userChoice == JFileChooser.APPROVE_OPTION) {
						audioFilePath = fileChooser.getSelectedFile().getAbsolutePath();
						System.out.println(audioFilePath);
						controller.openTrack(audioFilePath);
				
						buttonPlay.setIcon(iconPause);
						//controller.onPlaylistSelected(controller.getQueue().getId());
					}
					
				
				}  
				if (button == buttonPlay) {
					if(button.getIcon()==iconPlay)
					{
						System.out.println("resume");
						controller.resume();
						//controller.play();
						barThread.setPaused(false);
						buttonPlay.setIcon(iconPause);
					}
					else{
						System.out.println("pause");
						barThread.setPaused(true);
						controller.pause();
						//barThread.setStopped(true);
						buttonPlay.setIcon(iconPlay);
					}
				}
				if(button == buttonNext) {
					
					if(!controller.getLibraryManager().canPlayNext()){
						JOptionPane.showMessageDialog(new Frame(),
								"non ci sono canzoni da produrre.",
							    "Warning",JOptionPane.WARNING_MESSAGE);
					}
					else{
						controller.nextTrack();
					}
				} 
				if(button == buttonBack) {
					
					if(!controller.getLibraryManager().canPlayNext()){
						JOptionPane.showMessageDialog(new Frame(),
								"non ci sono canzoni da produrre.",
							    "Warning",JOptionPane.WARNING_MESSAGE);
					}
					else{
						controller.previuosTrack();
					}
				} 
			}
		}

	};

	public void setSouthData(String labelDuration, int secondsDuration) {
		this.labelDuration.setText(labelDuration);
		progressSlider.setMaximum(secondsDuration);
		barThread.cleanBarData();
		labelFileReproducing.setText(controller.getReproducingSongInfo());
	}
	public void enableButtons() {
		
		this.buttonPlay.setEnabled(true);
		this.buttonNext.setEnabled(true);
		this.buttonBack.setEnabled(true);
	}
	
	public void DisableButtons() {
		this.buttonPlay.setEnabled(false);
		this.buttonNext.setEnabled(false);
		this.buttonBack.setEnabled(false);
	}
	
	public void Stop(){
			barThread.setPaused(true);
		 labelFileReproducing.setText("No Song To Play, Start a new one");
		 labelTimeCounter = new JLabel("-:-:-");
		 labelDuration = new JLabel("-:-:-");
		 this.buttonPlay.setIcon(iconPlay);
		
	}

}
