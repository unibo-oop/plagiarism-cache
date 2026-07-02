package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import libs.observe.ObservableElement;
import libs.resources.Resources;

/**
 * A class implementing a factory of views for the "Swing" graphic library.
 * 
 * @author Davide Merli
 *
 */
public class SwingViewFactory implements ViewFactory {

	private JFrame frame = new JFrame("BANG!");
	private ObservableElement<String> changeSceneObservable = new ObservableElement<String>();

	@Override
	public View getMenuView(final ObservableElement<Integer> numberOfPlayers) {
		return new AbstractSwingView(frame) {

			private static final int LABEL_FONT_SIZE = 50;
			private static final int BUTTON_FONT_SIZE = 30;

			@Override
			public void initView() {
				Font labelFont = new Font(null, Font.BOLD, LABEL_FONT_SIZE);
				Font buttonFont = new Font(null, Font.PLAIN, BUTTON_FONT_SIZE);
				panel.setLayout(new GridBagLayout());
				JPanel jp = new JPanel();
				jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
				JLabel label = new JLabel("BANG!");
				label.setFont(labelFont);
				label.setAlignmentX(JPanel.CENTER_ALIGNMENT);
				JButton play = new JButton("Play");
				JButton howToPlay = new JButton("How to play");
				JButton quit = new JButton("Quit");
				play.setFont(buttonFont);
				howToPlay.setFont(buttonFont);
				quit.setFont(buttonFont);

				play.addActionListener(e -> {
					List<Integer> options = List.of(4, 5, 6, 7);
					Optional<Integer> playerNum = Optional.ofNullable((Integer) JOptionPane.showInputDialog(frame,
							"Insert the number of players:", "Choose players", JOptionPane.PLAIN_MESSAGE, null,
							options.toArray(), options.get(0)));
					if (playerNum.isPresent()) {
						numberOfPlayers.set(playerNum.get());
						changeView("game");
					}
				});
				howToPlay.addActionListener(e -> changeView("rules"));
				quit.addActionListener(e -> System.exit(0));

				jp.add(label);
				jp.add(play);
				jp.add(howToPlay);
				jp.add(quit);
				play.setAlignmentX(Component.CENTER_ALIGNMENT);
				howToPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
				quit.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(jp);
			}
		};
	}

	@Override
	public View getRulesView() {
		return new AbstractSwingView(frame) {

			private static final String ROLES_FILENAME = "files/Rules_Roles.txt";
			private static final String BROWN_FILENAME = "files/Rules_BrownCards.txt";
			private static final String BLUE_FILENAME = "files/Rules_BlueCards.txt";

			@Override
			public void initView() {
				JPanel south = new JPanel();
				panel.setLayout(new BorderLayout());
				JTextArea text = new JTextArea();
				text.setEditable(false);
				JButton showRoles = new JButton("Roles");
				JButton showBrown = new JButton("Brown cards");
				JButton showBlue = new JButton("Blue cards");
				JButton back = new JButton("Back");

				showRoles.addActionListener(e -> {
					text.setText(Resources.readFile(ROLES_FILENAME));
					showRoles.setEnabled(false);
					showBrown.setEnabled(true);
					showBlue.setEnabled(true);
				});
				showBrown.addActionListener(e -> {
					text.setText(Resources.readFile(BROWN_FILENAME));
					showRoles.setEnabled(true);
					showBrown.setEnabled(false);
					showBlue.setEnabled(true);
				});
				showBlue.addActionListener(e -> {
					text.setText(Resources.readFile(BLUE_FILENAME));
					showRoles.setEnabled(true);
					showBrown.setEnabled(true);
					showBlue.setEnabled(false);
				});
				back.addActionListener(e -> changeView("start"));

				showRoles.setEnabled(false);
				text.setText(Resources.readFile(ROLES_FILENAME));

				south.add(showRoles);
				south.add(showBrown);
				south.add(showBlue);
				south.add(back);
				panel.add(text, BorderLayout.CENTER);
				panel.add(south, BorderLayout.SOUTH);
			}
		};
	}

	@Override
	public View getGameView(final GameViewObservables observables) {
		return new AbstractSwingView(frame) {

			private static final int ERROR_VALUE = -1;
			private JPanel playersPanel;
			private JPanel currentPlayerPanel;
			private JPanel cardsPanel;
			private JPanel blueCardsPanel;
			private JButton endTurn;
			private JTextArea currentPlayerStats;

			@Override
			public void initView() {
				/*
				 * Set general view properties
				 */
				panel.setLayout(new BorderLayout());
				playersPanel = new JPanel();
				currentPlayerPanel = new JPanel();
				currentPlayerPanel.setLayout(new BoxLayout(currentPlayerPanel, BoxLayout.Y_AXIS));
				cardsPanel = new JPanel();
				blueCardsPanel = new JPanel();

				JLabel cardsInPlay = new JLabel("Your cards in play:");
				cardsInPlay.setAlignmentX(JPanel.CENTER_ALIGNMENT);
				JLabel cardsInHand = new JLabel("Your cards in hand:");
				cardsInHand.setAlignmentX(JPanel.CENTER_ALIGNMENT);

				JScrollPane cardsScrollPane = new JScrollPane(cardsPanel);
				cardsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				cardsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

				currentPlayerStats = new JTextArea();
				currentPlayerStats.setEditable(false);

				endTurn = new JButton("End turn");
				endTurn.setAlignmentX(JPanel.CENTER_ALIGNMENT);
				endTurn.addActionListener(e -> {
					CurrentPlayerInfo currentPlayer = observables.getCurrentPlayer().get();
					int cardsToDiscard = currentPlayer.getHand().get().size() - currentPlayer.getLifePoints();
					if (cardsToDiscard > 0) {
						JOptionPane.showMessageDialog(null,
								"You must discard " + cardsToDiscard + (cardsToDiscard == 1 ? " card." : " cards."),
								"Discard cards", JOptionPane.INFORMATION_MESSAGE);
					} else {
						observables.getAction().set("endTurn");
					}
				});

				/*
				 * Add observers
				 */
				// Current player observable
				observables.getCurrentPlayer().addObserver(() -> {
					CurrentPlayerInfo currentPlayer = observables.getCurrentPlayer().get();
					currentPlayerStats.setText("Name: " + currentPlayer.getName());
					currentPlayerStats.append("\nHP: " + currentPlayer.getLifePoints());
					currentPlayerStats.append("\nRole: " + currentPlayer.getRole());

					// Hand observable
					observables.getCurrentPlayer().get().getHand().addObserver(() -> {
						cardsPanel.removeAll();
						observables.getCurrentPlayer().get().getHand().get().forEach(c -> {
							JButton jb;
							jb = new JButton(new ImageIcon(Resources.getSwingImage("images/" + c + ".png")));
							jb.addActionListener(e -> {
								List<String> options = List.of("Play", "Discard", "Cancel");
								int choice = JOptionPane.showOptionDialog(frame,
										"Do you want to play or discard this card?", "Choose",
										JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
										options.toArray(), options.get(0));
								if (choice == 0) {
									observables.setChosenCard(c);
									observables.getAction().set("playCard");
								} else if (choice == 1) {
									observables.setChosenCard(c);
									observables.getAction().set("discardCard");
								}

							});
							cardsPanel.add(jb);
						});
					});

					// Add active cards
					blueCardsPanel.removeAll();
					observables.getCurrentPlayer().get().getBlueCards().forEach(c -> {
						JButton jb;
						jb = new JButton(new ImageIcon(Resources.getSwingImage("images/" + c + ".png")));
						blueCardsPanel.add(jb);
						blueCardsPanel.add(jb);
					});

					frame.getContentPane().validate();
					frame.getContentPane().repaint();
				});

				// Other players observable
				observables.getOtherPlayers().addObserver(() -> {
					playersPanel.removeAll();
					for (int i = 0; i < observables.getOtherPlayers().get().size(); i++) {
						var p = observables.getOtherPlayers().get().get(i);
						JPanel jp = new JPanel();
						jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

						JTextArea text = new JTextArea();
						text.setEditable(false);
						text.append("Name: " + p.getName());
						text.append("\nHP: " + p.getLifePoints());
						if (p.getRole().equals("sheriff")) {
							text.append("\nRole: " + p.getRole());
						}
						jp.add(text);

						p.getBlueCards().forEach(c -> {
							JButton jb = new JButton(c);
							jp.add(jb);
							jb.setAlignmentX(JPanel.CENTER_ALIGNMENT);
						});

						playersPanel.add(jp);
					}
					frame.getContentPane().validate();
					frame.getContentPane().repaint();
				});

				// Targets observable
				observables.getTargets().addObserver(() -> {
					List<String> options = observables.getTargets().get();
					int choice = JOptionPane.showOptionDialog(frame, "Choose target:", "Choose",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options.toArray(),
							options.get(0));
					if (choice != ERROR_VALUE) {
						observables.getChosenPlayer().set(options.get(choice));
					}
				});

				/*
				 * Compose view
				 */
				currentPlayerPanel.add(cardsInPlay);
				currentPlayerPanel.add(blueCardsPanel);
				currentPlayerPanel.add(cardsInHand);
				currentPlayerPanel.add(cardsScrollPane);
				currentPlayerPanel.add(currentPlayerStats);
				currentPlayerPanel.add(currentPlayerStats);
				currentPlayerPanel.add(endTurn);
				panel.add(playersPanel, BorderLayout.NORTH);
				panel.add(currentPlayerPanel, BorderLayout.SOUTH);
			}
		};
	}

	@Override
	public View getEndGameView(final List<String> winners) {
		return new AbstractSwingView(frame) {

			private static final int GAMEOVER_LABEL_FONT_SIZE = 50;
			private static final int FONT_SIZE = 30;

			@Override
			public void initView() {
				Font gameoverFont = new Font(null, Font.BOLD, GAMEOVER_LABEL_FONT_SIZE);
				Font font = new Font(null, Font.PLAIN, FONT_SIZE);
				panel.setLayout(new GridBagLayout());
				JPanel jp = new JPanel();
				jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
				JLabel gameOverLabel = new JLabel("GAME OVER!");
				gameOverLabel.setFont(gameoverFont);
				JButton quit = new JButton("Quit");
				quit.setFont(font);
				quit.setAlignmentX(JPanel.CENTER_ALIGNMENT);
				quit.addActionListener(e -> System.exit(0));

				StringBuilder builder = new StringBuilder("Player" + (winners.size() > 1 ? "s " : " "));
				winners.forEach(w -> {
					if (winners.indexOf(w) != 0) {
						builder.append(", ");
					}
					builder.append(w);
				});
				builder.append(" won the game!");
				JLabel winnersLabel = new JLabel(builder.toString());
				winnersLabel.setFont(font);

				gameOverLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
				winnersLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
				jp.add(gameOverLabel);
				jp.add(winnersLabel);
				jp.add(quit);
				panel.add(jp);
			}
		};
	}

	public ObservableElement<String> getChangeSceneObservable() {
		return this.changeSceneObservable;
	}

	public void changeView(final String s) {
		this.changeSceneObservable.set(s);
	}
}