package view.config;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ConfigController;
import model.players.Decoder;
import model.players.Player;
import view.utils.InputFieldFactory;

public class ConfigDecoder extends JPanel {

	private static final long serialVersionUID = 4305358439841234313L;

	private JTextField txtUsername;
	
	private ConfigController controller;
	private Decoder decoder;
		
	public String getUsername() {
		return this.txtUsername.getText();
	}
	
	public void setPlayer(Decoder decoder) {
		this.decoder = decoder;
		txtUsername.setText(decoder.getUsername());
	}
	
	public Player getDecoder() {
		return this.decoder;
	}

	public void setController(ConfigController controller) {
		this.controller = controller;
	}

	public ConfigDecoder () {

		JPanel panel = new JPanel(new FlowLayout());

		//Username
		txtUsername = new JTextField();
		panel.add(InputFieldFactory.getLabelTextPanel(new JLabel("Username"), txtUsername));

		JButton remove = new JButton();
		remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	controller.removeDecoder(decoder);
            }
		});

		remove.setText("REMOVE");
		panel.add(remove);
		
		this.add(panel);
	}
}
