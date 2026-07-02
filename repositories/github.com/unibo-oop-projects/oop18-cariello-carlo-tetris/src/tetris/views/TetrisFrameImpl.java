package tetris.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tetris.controllers.BoardControllerImpl;
import tetris.controllers.ReadRecord;
import tetris.models.Pair;
import tetris.models.ShapeImpl.Tetrominoes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * This class implements the GUI of game
 * 
 * @author Carlo
 *
 */
public class TetrisFrameImpl extends JFrame implements TetrisFrame {

	private static final long serialVersionUID = 1L;
	public static String name;
	public static int lastScore;
	private JLabel statusBar = new JLabel();
	public static JLabel lbLevel = new JLabel();
	private JLabel next = new JLabel("Next ");
	public static int outLevel = 0;
	private TetrisBoardImpl board;
	public static int outlevel;
	public JPanel contentPane = new JPanel();
	private JLabel pause = new JLabel("<html>Press 'p' for" + "<br> pause/resume the game! </html>");

	final static Color colorSidesPanel = new Color(0, 0, 128);
	final static Color colorBackgroundIntro = new Color(0, 0, 128);
	final static Color panelSouth = new Color(211, 211, 211);
	final static Color colorStatusBar = new Color(255, 255, 255);
	JPanel centerRight = new JPanel(new BorderLayout());
	JLabel Imm = new JLabel(" ");

	ImageIcon iconIntro = new ImageIcon("res/image/imageIntro.png");
	public ImageIcon nextPieceImage = new ImageIcon("res/image/ZShape.jpg");
	ImageIcon ZShapeImm = new ImageIcon("res/image/ZShape.jpg");
	ImageIcon LineShapeImm = new ImageIcon("res/image/LineShape.jpg");
	ImageIcon LShapeImm = new ImageIcon("res/image/LShape.jpg");
	ImageIcon SquareShapeImm = new ImageIcon("res/image/SquareShape.jpg");
	ImageIcon TShapeImm = new ImageIcon("res/image/TShape.jpg");
	ImageIcon MirroredLShapeImm = new ImageIcon("res/image/MirroredShape.jpg");
	ImageIcon SShapeImm = new ImageIcon("res/image/SShape.jpg");

	/**
	 * declaration of list for next tetromino
	 */
	private LinkedList<Pair<Tetrominoes, ImageIcon>> list = new LinkedList<>();

	public LinkedList<Pair<Tetrominoes, ImageIcon>> getListImage() {
		return list;
	}

	public void setNextPieceImage(ImageIcon imageIcon) {
		this.nextPieceImage.setDescription(imageIcon.getDescription());
	}

	public void updateNextImage(Tetrominoes tetr) {
		for (Pair<Tetrominoes, ImageIcon> pair : list) {
			if (tetr.equals(pair.getX())) {
				setNextPieceImage(pair.getY());
				System.out.println("L'immagine di next e': " + nextPieceImage.getDescription());
				centerRight.removeAll();
				centerRight.add(Imm);
				centerRight.repaint();
				centerRight.validate();
				break;
			}
		}
	}

	public void insertName() {
		do {
			name = JOptionPane.showInputDialog(null, "Please insert your nickname:", "Welcome to Tetris",
					JOptionPane.INFORMATION_MESSAGE);
			if (name == null) {
				System.exit(0);
			}
			if (name.isEmpty() || name.length() < 3) {
				JOptionPane.showMessageDialog(null, "ERROR: you must a valid name!");
			}
		} while (name.isEmpty() || name.length() < 3);

		@SuppressWarnings("unused")
		ReadRecord rv = new ReadRecord();

		JOptionPane.showMessageDialog(null,
				"Welcome " + name + "! Your PREVIOUS score is " + lastScore + " deleted rows", "",
				JOptionPane.INFORMATION_MESSAGE);

		statusBar.setText("<html>Welcome " + name + "!" + "<br> your score is 0 deleted rows! </html>");
		statusBar.setHorizontalAlignment(JLabel.CENTER);
		statusBar.setVerticalAlignment(JLabel.CENTER);
		statusBar.setForeground(colorStatusBar);
		board = new TetrisBoardImpl(this);
	}

	/**
	 * class constructor
	 */
	public TetrisFrameImpl() {

		JFrame nickName = new JFrame("Welcome Tetris");
		nickName.setVisible(true);
		nickName.setSize(750, 600);
		nickName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		list.add(new Pair<>(Tetrominoes.ZShape, ZShapeImm));
		list.add(new Pair<>(Tetrominoes.SShape, SShapeImm));
		list.add(new Pair<>(Tetrominoes.LineShape, LineShapeImm));
		list.add(new Pair<>(Tetrominoes.LShape, LShapeImm));
		list.add(new Pair<>(Tetrominoes.MirroredLShape, MirroredLShapeImm));
		list.add(new Pair<>(Tetrominoes.TShape, TShapeImm));
		list.add(new Pair<>(Tetrominoes.SquareShape, SquareShapeImm));

		setBounds(200, 200, 450, 300);

		contentPane.setBackground(colorBackgroundIntro);
		contentPane.setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		nickName.setLocation(dim.width / 3 - this.getSize().width / 4, dim.height / 3 - this.getSize().height / 2);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIntro = new JLabel(" ");
		lblIntro.setIcon(iconIntro);
		lblIntro.setBounds(250, 17, 32, 54);
		contentPane.add(lblIntro);

		JLabel lblIcon = new JLabel(" ");
		lblIcon.setIcon(iconIntro);
		lblIcon.setBounds(500, 23, 32, 42);
		contentPane.add(lblIcon);

		JLabel lblRules = new JLabel("RULES:");
		lblRules.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblRules.setForeground(colorStatusBar);
		lblRules.setBounds(110, 85, 127, 16);
		contentPane.add(lblRules);

		JLabel lblRightLeft = new JLabel();
		JLabel lblBelowKey = new JLabel();
		JLabel lblUpKey = new JLabel();
		JLabel lblSpaceBar = new JLabel();
		JLabel lblDeletedRows = new JLabel();

		lblRightLeft.setText("1- Press the right and left keys to move around the grid.");
		lblRightLeft.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblBelowKey.setText("2- Press the below key to speed up the descent.");
		lblBelowKey.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUpKey.setText("3- Press the up key to rotate the tetramino.");
		lblUpKey.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblSpaceBar.setText("4- Press the space bar to hold the game.");
		lblSpaceBar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblDeletedRows.setText("5- Every 5 rows eliminated, go to the next level.");
		lblDeletedRows.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

		lblRightLeft.setBounds(110, 104, 547, 100);
		lblBelowKey.setBounds(110, 152, 547, 100);
		lblUpKey.setBounds(110, 203, 547, 100);
		lblSpaceBar.setBounds(110, 254, 547, 100);
		lblDeletedRows.setBounds(110, 326, 547, 60);

		lblRightLeft.setForeground(colorStatusBar);
		lblBelowKey.setForeground(colorStatusBar);
		lblUpKey.setForeground(colorStatusBar);
		lblSpaceBar.setForeground(colorStatusBar);
		lblDeletedRows.setForeground(colorStatusBar);

		contentPane.add(lblRightLeft);
		contentPane.add(lblBelowKey);
		contentPane.add(lblUpKey);
		contentPane.add(lblSpaceBar);
		contentPane.add(lblDeletedRows);

		JLabel lblTetris = new JLabel("TETRIS");
		lblTetris.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		lblTetris.setForeground(colorStatusBar);
		lblTetris.setBounds(350, 33, 72, 16);
		contentPane.add(lblTetris);

		contentPane.setVisible(true);
		JButton button = new JButton("START");

		button.setBounds(326, 494, 117, 29);
		contentPane.add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertName();
				nickName.dispose();
				init();
			}
		});

		nickName.add(contentPane);
	}

	public TetrisFrameImpl getTetrisFrame() {
		return this;
	}

	public void init() {
		JFrame tetris = new JFrame("Tetris");
		tetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tetris.setVisible(true);
		tetris.pack();
		tetris.setResizable(false);
		tetris.setSize(815, 630);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(dimension.width / 3 - this.getSize().width / 2, dimension.height / 4 - this.getSize().height / 2);
		tetris.setLayout(new GridLayout(1, 3));
		add(statusBar, BorderLayout.SOUTH);
		tetris.add(statusBar, BorderLayout.NORTH);
		tetris.add(board, BorderLayout.CENTER);
		board.start();

		tetris.setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		tetris.setLocation(dim.width / 3 - this.getSize().width / 3, dim.height / 3 - this.getSize().height / 2);

		Panel leftPanel = new Panel(new GridLayout(2, 1));
		statusBar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		leftPanel.add(statusBar);
		leftPanel.setBackground(colorSidesPanel);

		outLevel = BoardControllerImpl.removeFullLines();

		lbLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lbLevel.setHorizontalAlignment(JLabel.CENTER);
		lbLevel.setVerticalAlignment(JLabel.CENTER);
		lbLevel.setBounds(371, 33, 72, 16);
		lbLevel.setText("Level: " + outLevel);

		lbLevel.setForeground(colorStatusBar);
		leftPanel.add(lbLevel);
		lbLevel.setBounds(400, 100, 100, 100);

		Panel centerPanel = new Panel(new GridLayout(1, 1));
		centerPanel.add(board);

		Panel rightPanel = new Panel(new GridLayout(3, 1));
		rightPanel.setBackground(colorSidesPanel);
		pause.setForeground(colorStatusBar);
		pause.setHorizontalAlignment(JLabel.CENTER);
		rightPanel.add(pause);

		Panel north = new Panel(new BorderLayout());
		north.add(next);
		next.setForeground(Color.WHITE);
		next.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		next.setHorizontalAlignment(JLabel.CENTER);

		lbLevel.setBounds(371, 33, 72, 16);
		north.setBackground(colorSidesPanel);

		Imm.setIcon(nextPieceImage);
		centerRight.add(Imm);

		Panel south = new Panel(new BorderLayout());
		south.setBackground(colorSidesPanel);
		pause.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		south.add(pause);

		rightPanel.add(north);
		rightPanel.add(centerRight);
		rightPanel.add(south);

		tetris.add(leftPanel);
		tetris.add(centerPanel);
		tetris.add(rightPanel);
	}

	public JLabel getStatusBar() {
		return statusBar;
	}
}