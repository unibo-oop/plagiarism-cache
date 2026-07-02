package view.classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import model.interfaces.IArtGallery;
import controller.interfaces.IControllerArtworkForm;
import view.interfaces.IArtworkForm;

/**
 * The form where an user can manage an artwork.
 * @author Elisa Casadio
 *
 */
public class ArtworkForm extends JDialog implements IArtworkForm {
	
	private static final long serialVersionUID = 4953331865251845305L;
	private static final String[] PAINT = new String[]{"Tempera",
		"Olio su tela", "Olio su tavola", "Acrilico", "Pastello",
		"Acquerello", "Guazzo", "Acquaforte"};
	private static final String[] SCULPTURE = new String[]{"Marmo",
		"Pietra", "Gesso", "Porcellana", "Ferro", "Bronzo", "Legno",
		"Argilla"};
	private static final int FONT_SIZE = 14;
	private static final int TOP_EDGE_10 = 10;
	private static final int TOP_EDGE_7 = 7;
	private static final int TOP_EDGE_5 = 5;
	private static final int MAX_YEAR = 500000;
	private static final String FONT_NAME = "Century SchoolBook";
	private static final String PAINT_S = "Pittura";
	private static final String SCULP_S = "Scultura";
	private static final String CM = "cm";
	private static final double MAX_DIM = 1000.00;
	private static final double ZERO = 0.00;
	
	private final JLabel codeLabel = new JLabel("Codice opera: ");
	private final JLabel titleLabel = new JLabel("Titolo opera: ");
	private final JLabel authorLabel = new JLabel("Autore: ");
	private final JLabel yearLabel = new JLabel("Anno: ");
	private final JLabel artisticLabel = new JLabel("Disciplina artistica: ");
	private final JLabel techniqueLabel = new JLabel("Tecnica/Materiale: ");
	private final JLabel heightLabel = new JLabel("Altezza: ");
	private final JLabel widthLabel = new JLabel("Larghezza: ");
	private final JLabel depthLabel = new JLabel("Profondità: ");
	private final JLabel descriptionLabel = new JLabel("Descrizione: ");
	private final JLabel codeField;
	private final JLabel cmHeight = new JLabel(CM);
	private final JLabel cmWidth = new JLabel(CM);
	private final JLabel cmDepth = new JLabel(CM);
	private final JTextField titleField = new JTextField(30);
	private final JTextField authorField = new JTextField(30);
	private final JTextArea descriptionField = new JTextArea(7, 30);
	private final JSpinner yearField = new JSpinner(
			new SpinnerNumberModel(LocalDate.now().getYear(), 0, MAX_YEAR, 1));
	private final JSpinner heightField = new JSpinner(
			new SpinnerNumberModel(ZERO, ZERO, MAX_DIM, 0.01));
	private final JSpinner widthField = new JSpinner(
			new SpinnerNumberModel(ZERO, ZERO, MAX_DIM, 0.01));
	private final JSpinner depthField = new JSpinner(
			new SpinnerNumberModel(ZERO, ZERO, MAX_DIM, 0.01));
	private final ButtonGroup yearGroup = new ButtonGroup();
	private final ButtonGroup artDiscGroup = new ButtonGroup();
	private final JRadioButton acField = new JRadioButton("Avanti Cristo");
	private final JRadioButton dcField = new JRadioButton("Dopo Cristo");
	private final JRadioButton paintingField = new JRadioButton(PAINT_S);
	private final JRadioButton sculptureField = new JRadioButton(SCULP_S);
	private final JComboBox<String> techniquesPainting = new JComboBox<>(PAINT);
	private final JComboBox<String> materialsSculpture = new JComboBox<>(SCULPTURE); // per la scultura
	private final TitledBorder titleDimensionPanel = new TitledBorder("Dimensioni");
	private final JButton confirmButton = new JButton("  OK", 
			new ImageIcon(this.getClass().getResource("/ok_23x23.png")));
	private final JButton cancelButton = new JButton("  Annulla", 
			new ImageIcon(this.getClass().getResource("/cancel_23x23.png")));
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	
	private IControllerArtworkForm controller;

	/**
	 * Constructor.
	 * Constructs a new form for the artworks with a specific code.
	 * 
	 * @param frame
	 * 			the frame that this JDialog refers to.
	 * @param code
	 * 			the code of this artwork.
	 */
	public ArtworkForm(final JFrame frame, final Long code) {
		super(frame);
		this.setTitle("Opera d'arte");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.codeField = new JLabel(code.toString());
		
		this.buildLayout();		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	/**
	 * Builds the layout of the JDialog.
	 */
	private void buildLayout() {
		int row = 0;
		final GridBagLayout layout = new GridBagLayout();
		final JPanel gbdPanel = new JPanel();
		gbdPanel.setLayout(layout);
		this.setFont();
		
		this.adder.addComponent(gbdPanel, this.codeLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.adder.addComponent(gbdPanel, this.codeField, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		this.adder.addComponent(gbdPanel, this.titleLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.adder.addComponent(gbdPanel, this.titleField, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		this.adder.addComponent(gbdPanel, this.authorLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.adder.addComponent(gbdPanel, this.authorField, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		this.adder.addComponent(gbdPanel, this.yearLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		
		final JPanel yearPanel = new JPanel(new FlowLayout());
		this.yearField.setEditor(new JSpinner.NumberEditor(this.yearField, "#"));
		this.yearGroup.add(this.acField);
		this.yearGroup.add(this.dcField);
		yearPanel.add(this.yearField);
		yearPanel.add(this.acField);
		yearPanel.add(this.dcField);
		this.adder.addComponent(gbdPanel, yearPanel, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, 1, 0, layout);
		
		this.adder.addComponent(gbdPanel, this.artisticLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);	
		this.artDiscGroup.add(this.paintingField);
		this.artDiscGroup.add(this.sculptureField);
		final JPanel artisticDPanel = new JPanel(new FlowLayout());
		artisticDPanel.add(this.paintingField);
		artisticDPanel.add(this.sculptureField);
		this.adder.addComponent(gbdPanel, artisticDPanel, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, 1, 0, layout);
		
		this.adder.addComponent(gbdPanel, this.techniqueLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.setTechniqueForm(this.techniquesPainting);
		this.setTechniqueForm(this.materialsSculpture);
		this.adder.addComponent(gbdPanel, this.techniquesPainting, 1, row,
				1, 1, GridBagConstraints.NORTHWEST, TOP_EDGE_7, 0, layout);
		this.adder.addComponent(gbdPanel, this.materialsSculpture, 1, row++,
				1, 1, GridBagConstraints.NORTHWEST, TOP_EDGE_7, 0, layout);
		
		final JPanel dimensionPanel = new JPanel();
		final GridBagLayout gblDim = new GridBagLayout();
		dimensionPanel.setLayout(gblDim);
		dimensionPanel.setBorder(this.titleDimensionPanel);
				
		this.adder.addComponent(dimensionPanel, this.heightLabel, 0, 0, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, gblDim);
		final JPanel heightPanel = new JPanel(new FlowLayout());
		heightPanel.add(this.heightField);
		heightPanel.add(this.cmHeight);
		this.adder.addComponent(dimensionPanel, heightPanel, 1, 0, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_5, 0, gblDim);
				
		this.adder.addComponent(dimensionPanel, this.widthLabel, 0, 1, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, gblDim);
		final JPanel widthP = new JPanel(new FlowLayout());
		widthP.add(this.widthField);
		widthP.add(this.cmWidth);
		this.adder.addComponent(dimensionPanel, widthP, 1, 1, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_5, 0, gblDim);
				
		this.adder.addComponent(dimensionPanel, this.depthLabel, 0, 2, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, gblDim);
		final JPanel depthP = new JPanel(new FlowLayout());
		depthP.add(this.depthField);
		depthP.add(this.cmDepth);
		this.adder.addComponent(dimensionPanel, depthP, 1, 2, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_5, 0, gblDim);
		this.depthField.setEnabled(false);
		
		this.adder.addComponent(gbdPanel, dimensionPanel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		
		final JPanel descrP = new JPanel(new BorderLayout());
		descrP.add(this.descriptionLabel, BorderLayout.NORTH);
		this.descriptionField.setLineWrap(true);
		this.descriptionField.setWrapStyleWord(true);
		final JScrollPane jsp = new JScrollPane(this.descriptionField, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		descrP.add(jsp, BorderLayout.SOUTH);
		this.adder.addComponent(gbdPanel, descrP, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		final JPanel buttonP = new JPanel(new FlowLayout());
		buttonP.add(this.confirmButton);
		buttonP.add(this.cancelButton);
		this.adder.addComponent(gbdPanel, buttonP, 1, row, 1, 1,
				GridBagConstraints.NORTHEAST, TOP_EDGE_10, 0, layout);
		
		this.setHandlers();
		this.getContentPane().add(gbdPanel);	
	}
	
	/**
	 * Sets the font of each component of the GUI.
	 */
	private void setFont() {
		final Font font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		final Font fontField = new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);
		this.codeLabel.setFont(font);
		this.codeField.setFont(fontField);
		this.titleLabel.setFont(font);
		this.titleField.setFont(fontField);
		this.authorLabel.setFont(font);
		this.authorField.setFont(fontField);
		this.yearLabel.setFont(font);
		this.yearField.setFont(fontField);
		this.acField.setFont(fontField);
		this.dcField.setFont(fontField);
		this.artisticLabel.setFont(font);
		this.paintingField.setFont(fontField);
		this.sculptureField.setFont(fontField);
		this.techniqueLabel.setFont(font);
		this.techniquesPainting.setFont(fontField);
		this.materialsSculpture.setFont(fontField);
		this.titleDimensionPanel.setTitleFont(font);
		this.heightLabel.setFont(font);
		this.heightField.setFont(fontField);
		this.cmHeight.setFont(fontField);
		this.widthLabel.setFont(font);
		this.widthField.setFont(fontField);
		this.cmWidth.setFont(fontField);
		this.depthLabel.setFont(font);
		this.depthField.setFont(fontField);
		this.cmDepth.setFont(fontField);
		this.descriptionLabel.setFont(font);
		this.descriptionField.setFont(fontField);
		this.confirmButton.setFont(font);
		this.cancelButton.setFont(font);
	}
	
	/**
	 * Sets the characteristics of the JComboBox.
	 * 
	 * @param list
	 * 			the JComboBox to set.
	 */
	private void setTechniqueForm(final JComboBox<String> list) {
		list.setEditable(true);
		list.setVisible(true);
	}
	
	/**
	 * Adds the action to each component of this view.
	 */
	private void setHandlers() {
		this.paintingField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (isPainting()) {
					ArtworkForm.this.depthField.setEnabled(false);
					ArtworkForm.this.materialsSculpture.setVisible(false);
					ArtworkForm.this.techniquesPainting.setVisible(true);
				}
			}
		});
		this.sculptureField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (!isPainting()) {
					ArtworkForm.this.depthField.setEnabled(true);
					ArtworkForm.this.techniquesPainting.setVisible(false);
					ArtworkForm.this.materialsSculpture.setVisible(true);
				}
			}
		});
		this.confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.commandConfirm(getCode(), getTitleArt(), getAuthor(), isAC(),
						isDC(), getYear(), isPainting(), getArtisticDisc(),
						getTechnique(), getHeightArt(), getWidthArt(),
						getDepthArt(), getDescription(), ArtworkForm.this);
			}
		});	
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				ArtworkForm.this.setVisible(false);
			}
		});
	}

	
	@Override
	public void attachController(final IControllerArtworkForm ctrl) {
		this.controller = ctrl;
	}
	
	@Override
	public Long getCode() {
		return Long.parseLong(this.codeField.getText());
	}
	
	@Override
	public String getTitleArt() {
		return this.titleField.getText();
	}
	
	@Override
	public String getAuthor() {
		return this.authorField.getText();
	}
	
	@Override
	public boolean isAC() {
		return this.acField.isSelected();
	}
	
	@Override
	public boolean isDC() {
		return this.dcField.isSelected();
	}
	
	@Override
	public int getYear() {
		return Integer.parseInt(this.yearField.getValue().toString());
	}
	
	@Override
	public boolean isPainting() {
		return this.paintingField.isSelected();
	}
	
	@Override
	public String getArtisticDisc() {
		if (this.paintingField.isSelected()) {
			return PAINT_S;
		}
		if (this.sculptureField.isSelected()) {
			return SCULP_S;
		}
		return null;
	}
	
	@Override
	public String getTechnique() {
		if (this.isPainting()) {
			return this.techniquesPainting.getSelectedItem().toString();
		}
		return this.materialsSculpture.getSelectedItem().toString();
	}
	
	@Override
	public double getHeightArt() {
		return Double.parseDouble(this.heightField.getValue().toString());
	}
	
	@Override
	public double getWidthArt() {
		return Double.parseDouble(this.widthField.getValue().toString());
	}
	
	@Override
	public double getDepthArt() {
		return Double.parseDouble(this.depthField.getValue().toString());
	}
	
	@Override
	public String getDescription() {
		return this.descriptionField.getText();
	}
	
	@Override
	public void reinit() {
		this.titleField.setText("");
		this.authorField.setText("");
		this.yearField.setValue(LocalDate.now().getYear());
		this.acField.setSelected(false);
		this.dcField.setSelected(false);
		this.paintingField.setSelected(false);
		this.sculptureField.setSelected(false);
		this.techniquesPainting.setSelectedIndex(0);
		this.techniquesPainting.setVisible(false);
		this.materialsSculpture.setSelectedIndex(0);
		this.materialsSculpture.setVisible(false);
		this.heightField.setValue(ZERO);
		this.widthField.setValue(ZERO);
		this.depthField.setValue(ZERO);
		this.descriptionField.setText("");
	}
	
	@Override
	public void setForm(final int index, final IArtGallery model) {
		this.titleField.setText(model.getArtwork().get(index).getTitle());
		this.authorField.setText(model.getArtwork().get(index).getAuthor());
		if (model.getArtwork().get(index).getYear() < 0) {
			this.setYear(model, index, -1, true, false);
		} else {
			this.setYear(model, index, 1, false, true);
		}
		if (model.getArtwork().get(index).getArtisticDiscipline().equals(PAINT_S)) {
			this.setArtisticDiscipline(model, index, true, false);
		} else {
			this.setArtisticDiscipline(model, index, false, true);
		}
		this.heightField.setValue(model.getArtwork().get(index).getHeight());
		this.widthField.setValue(model.getArtwork().get(index).getWidth());
		this.depthField.setValue(model.getArtwork().get(index).getDepth());
		this.descriptionField.setText(model.getArtwork().get(index).getDescription());
	}
	
	/**
	 * Sets the fields about the year of the artwork taking data from the model.
	 * 
	 * @param model
	 * 			the model.
	 * @param index
	 * 			the index of the artwork.
	 * @param one
	 * 			-1 if the dating year is A.C. otherwise 1.
	 * @param ac
	 * 			true if the dating year is A.C. otherwise false.
	 * @param dc
	 * 			true if the dating year is D.C. otherwise false.
	 */
	private void setYear(final IArtGallery model, final int index,
			final int one, final boolean ac, final boolean dc) {
		this.yearField.setValue(model.getArtwork().get(index).getYear() * one);
		this.acField.setSelected(ac);
		this.dcField.setSelected(dc);
	}
	
	/**
	 * Sets the fields about artistic discipline of the artwork taking data 
	 * from the model.
	 * 
	 * @param model
	 * 			the model.
	 * @param index
	 * 			the index of the artwork.
	 * @param isPaint
	 * 			true if the artistic discipline is "Pittura" otherwise false.
	 * @param isSculp
	 * 			true if the artistic discipline is "Scultura" otherwise false.
	 */
	private void setArtisticDiscipline(final IArtGallery model, final int index,
			final boolean isPaint, final boolean isSculp) {
		this.paintingField.setSelected(isPaint);
		this.sculptureField.setSelected(isSculp);
		if (isPaint) {
			this.techniquesPainting.setSelectedItem(model.getArtwork().get(index).getTechnique());
			this.materialsSculpture.setSelectedIndex(0);
		} else {
			this.materialsSculpture.setSelectedItem(model.getArtwork().get(index).getTechnique());
			this.techniquesPainting.setSelectedIndex(0);
		}
		this.techniquesPainting.setVisible(isPaint);
		this.materialsSculpture.setVisible(isSculp);
		this.depthField.setEnabled(isSculp);
	}
	
}
