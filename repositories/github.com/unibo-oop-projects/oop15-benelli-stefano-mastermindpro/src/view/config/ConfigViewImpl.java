package view.config;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ConfigController;
import controller.data.PersistentException;
import model.exceptions.InvalidConfigException;
import model.games.Game;
import model.games.GameConfig;
import model.players.Decoder;
import view.interfaces.ConfigView;
import view.interfaces.ViewBase;
import view.navigator.Navigator;
import view.utils.InputFieldFactory;
import view.utils.FrameHelper;

public class ConfigViewImpl extends ViewBase<Game> implements ConfigView {

	private static final long serialVersionUID = 6085556005589664763L;

	private JComboBox<Integer> noChoices;
	private JComboBox<Integer> codeLen;
	private JComboBox<Integer> noRounds;
	private JPanel decodersPanel;
	//private PlayerPanel encoderPanel;
	
	private ConfigController controller;
	
	public ConfigViewImpl(final Navigator navigator) {

		FrameHelper.setupWindow(this, new Dimension(500,500));

		this.setTitle("Game Settings");

		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

		////////////////////////FIELDS///////////////////////
		JPanel fieldsPanel = new JPanel(new GridLayout(2,1,10,10));
		fieldsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel configPanel = new JPanel(new GridLayout(1,3));
		
		//no. of Choices
		noChoices = InputFieldFactory.getIntegerComboBox(GameConfig.MIN_NO_CHOICES, GameConfig.MAX_NO_CHOICES);
		configPanel.add(InputFieldFactory.getLabelTextPanel(new JLabel("No. of Colors"), noChoices));

		//code length
		codeLen = InputFieldFactory.getIntegerComboBox(GameConfig.MIN_CODE_LEN, GameConfig.MAX_CODE_LEN);
		configPanel.add(InputFieldFactory.getLabelTextPanel(new JLabel("Code Length"), codeLen));

		//no. of Rounds
		noRounds = InputFieldFactory.getIntegerComboBox(GameConfig.MIN_NO_ROUNDS, GameConfig.MAX_NO_ROUNDS);
		configPanel.add(InputFieldFactory.getLabelTextPanel(new JLabel("No. of Rounds"), noRounds));
		fieldsPanel.add(configPanel);
		////////////////////////////////////////////////

		/*
		////////////////////ENCODER//////////////////////
		encoderPanel = InputFieldFactory.getLabelTextPanel(new JLabel("No. of Rounds"), noRounds)
		encoderPanel.setBorder(BorderFactory.createTitledBorder("Encoder"));
		fieldsPanel.add(encoderPanel);
		////////////////////////////////////////////////
		*/

		JButton addDecoder = new JButton("Add Decoder");
		addDecoder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.addDecoder();
			}

		});
		fieldsPanel.add(addDecoder);
		
		mainPanel.add(fieldsPanel, BorderLayout.NORTH);
		////////////////////////////////////////////////////////////////

		//decoders row
		decodersPanel = new JPanel();
		decodersPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.add(decodersPanel, BorderLayout.CENTER);
		
		/////////////////// BUTTONS /////////////////////////
		JPanel buttonRow = new JPanel(new GridLayout(1,2));
		JButton btnStart = new JButton();
		btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	try {
					controller.saveSettings();
				} catch (InvalidConfigException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
            	navigator.play();
            }
		});

		btnStart.setText("START");
		buttonRow.add(btnStart);

		JButton btnCancel = new JButton();
		btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	navigator.home();
            }
		});
		btnCancel.setText("CANCEL");
		buttonRow.add(btnCancel);
		mainPanel.add(buttonRow, BorderLayout.SOUTH);
		/////////////////////////////////////////////////////
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
    	    	navigator.home();
			}
        });
		
		this.pack();
	}
	
	public void setController(ConfigController controller) {
		this.controller = controller;
		//this.encoderPanel.setController(controller);
	}

	@Override
	public void update(Game game) {
		fillUI(game);
	}

	public void fillModel(Game game) {
		GameConfig config = game.getGameConfig();
		
		config.setNoOfChoices(noChoices.getItemAt(noChoices.getSelectedIndex()));
		config.setNoOfRounds(noRounds.getItemAt(noRounds.getSelectedIndex()));
		config.setCodeLength(codeLen.getItemAt(codeLen.getSelectedIndex()));
		
		for(Component c : decodersPanel.getComponents()) {
			if(c instanceof ConfigDecoder) {
				ConfigDecoder p = (ConfigDecoder) c;
				p.getDecoder().setUsername(p.getUsername());
			}
		}
	}

	private void fillUI(Game game) {
		GameConfig config = game.getGameConfig();
		
		noChoices.setSelectedItem(config.getNoOfChoices());
		noRounds.setSelectedItem(config.getNoOfRounds());
		codeLen.setSelectedItem(config.getCodeLength());
		//encoderPanel.setPlayer(game.getEncoder());
		
		loadDecoders(game.getDecoders());
	}
		
	private void loadDecoders(List<Decoder> decoders) {

		decodersPanel.removeAll();

		if(decoders.size() == 0) {
			return;
		}

		decodersPanel.setLayout(new GridLayout(decoders.size(),1,10,10));
		
		for(int i=0; i<decoders.size(); i++) {
			ConfigDecoder dPanel = new ConfigDecoder();
			dPanel.setBorder(BorderFactory.createTitledBorder("Decoder " + (i+1)));
			dPanel.setController(controller);
			dPanel.setPlayer(decoders.get(i));
			decodersPanel.add(dPanel);			
		}
		
		this.pack();
	}
}
