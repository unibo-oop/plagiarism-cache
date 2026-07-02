package view.classes;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;

import model.interfaces.IArtGallery;
import controller.interfaces.IControllerExhibitForm;
import view.interfaces.IExhibitForm;

/**
 * The form where an user can manage an exhibit.
 * @author Elisa Casadio
 *
 */

public class ExhibitForm extends JDialog implements IExhibitForm {

	private static final long serialVersionUID = 4032197877069256541L;
	private static final double MAX_COST_TICKET = 1000.00;
	private static final int TOP_EDGE_10 = 10;
	private static final int TOP_EDGE_7 = 7;
	private static final int TOP_EDGE_5 = 5;
	private static final int DATE_SIZE = 9;
	private static final int FONT_SIZE = 14;
	private static final String FONT_NAME = "Century SchoolBook";
	private static final double ZERO = 0.00;
	
	private final JLabel codeLabel = new JLabel("Codice mostra: ");
	private final JLabel titleLabel = new JLabel("Titolo mostra: ");
	private final JLabel curatorLabel = new JLabel("Curatore/i: ");
	private final JLabel dateBegLabel = new JLabel("Data di inizio: ");
	private final JLabel dateEndLabel = new JLabel("Data di fine: ");
	private final JLabel costExLabel = new JLabel("Costo esposizione: ");
	private final JLabel costTicketLabel = new JLabel("Costo biglietto: ");
	private final JLabel codeField;
	private final JTextField titleField = new JTextField(50);
	private final JTextField curatorField = new JTextField(50);
	private final JDateChooser dateBegField = new JDateChooser();
	private final JDateChooser dateEndField = new JDateChooser();
	private final JSpinner costExField = new JSpinner(
			new SpinnerNumberModel(ZERO, ZERO, Integer.MAX_VALUE, 0.01));
	private final JSpinner costTckJS = new JSpinner(
			new SpinnerNumberModel(ZERO, ZERO, MAX_COST_TICKET, 0.01));
	private final JButton ok = new JButton("  OK", 
			new ImageIcon(this.getClass().getResource("/ok_23x23.png")));
	private final JButton cancel = new JButton("  Annulla", 
			new ImageIcon(this.getClass().getResource("/cancel_23x23.png")));
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	
	private IControllerExhibitForm controller;
	
	/**
	 * Constructor that creates a new form for the exhibits.
	 * 
	 * @param frame
	 * 			the frame that this JDialog refers to.
	 * @param code
	 * 			the code of this exhibit.
	 */
	public ExhibitForm(final Frame frame, final Long code) {
		super(frame);
		this.setTitle("Esposizione");
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
		final JPanel exhibitPanel = new JPanel();
		exhibitPanel.setLayout(layout);
		
		this.adder.addComponent(exhibitPanel, this.codeLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.adder.addComponent(exhibitPanel, this.codeField, 1, row++, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		this.adder.addComponent(exhibitPanel, this.titleLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.adder.addComponent(exhibitPanel, this.titleField, 1, row++, 3, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		this.adder.addComponent(exhibitPanel, this.curatorLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.adder.addComponent(exhibitPanel, this.curatorField, 1, row++, 3, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_10, 0, layout);
		
		this.adder.addComponent(exhibitPanel, this.dateBegLabel, 0, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.setDateComponent(this.dateBegField, exhibitPanel, 1, layout);
		this.adder.addComponent(exhibitPanel, dateEndLabel, 2, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		this.setDateComponent(this.dateEndField, exhibitPanel, 3, layout);
		
		this.adder.addComponent(exhibitPanel, this.costExLabel, 0, ++row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		final JPanel costPanelEx = new JPanel(new FlowLayout());
		this.setCostComponent(costPanelEx, this.costExField);
		this.adder.addComponent(exhibitPanel, costPanelEx, 1, row, 1, 1, 
				GridBagConstraints.NORTHWEST, TOP_EDGE_5, 0, layout);
		
		this.adder.addComponent(exhibitPanel, this.costTicketLabel, 2, row, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE_10, 0, layout);
		final JPanel cosTicketPanel = new JPanel(new FlowLayout());
		this.setCostComponent(cosTicketPanel, this.costTckJS);
		this.adder.addComponent(exhibitPanel, cosTicketPanel, 3, row++, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_5, 0, layout);
		
		final JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(this.ok);
		buttonPanel.add(this.cancel);
		this.adder.addComponent(exhibitPanel, buttonPanel, 3, row, 1, 1, 
				GridBagConstraints.NORTHEAST, TOP_EDGE_10, 0, layout);
		
		this.setFont();
		this.setHandlers();
		
		this.getContentPane().add(exhibitPanel);
	}
	
	/**
	 * Sets a JDateChooser and adds it to a panel.
	 * 
	 * @param date
	 * 			the component that must be added.
	 * @param panel
	 * 			the panel where the component is added.
	 * @param column
	 * 			the GridBagLayout's column where the component is added.
	 * @param layout
	 * 			the panel's layout.
	 */
	private void setDateComponent(final JDateChooser date, final JPanel panel,
			final int column, final GridBagLayout layout) {
		date.setPreferredSize(new JTextField(DATE_SIZE).getPreferredSize());
		date.setDateFormatString("dd/MM/yyyy");
		this.adder.addComponent(panel, date, column, 3, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE_7, 0, layout);
	}
	
	/**
	 * Sets a panel of cost and adds the component to a panel.
	 * 
	 * @param panel
	 * 			the panel where the component is added.
	 * @param spinnerCost
	 * 			the component that must be added.
	 */
	private void setCostComponent(final JPanel panel, final JSpinner spinnerCost) {
		panel.add(new JLabel("€"));
		panel.add(spinnerCost);
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
		this.curatorLabel.setFont(font);
		this.curatorField.setFont(fontField);
		this.dateBegLabel.setFont(font);
		this.dateBegField.setFont(fontField);
		this.dateEndLabel.setFont(font);
		this.dateEndField.setFont(fontField);
		this.costExLabel.setFont(font);
		this.costExField.setFont(fontField);
		this.costTicketLabel.setFont(font);
		this.costTckJS.setFont(fontField);
		this.ok.setFont(font);
		this.cancel.setFont(font);
	}
	
	/**
	 * Adds the action to each component.
	 */
	private void setHandlers() {
		this.ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.commandConfirm(getCode(), getTitleEx(), 
						getCurator(), getDateBeginning(), getDateEnd(),
						getCostEx(), getCostTicket(), ExhibitForm.this);
			}
		});
		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				ExhibitForm.this.setVisible(false);
			}
		});
	}
	
	
	@Override
	public void attachController(final IControllerExhibitForm ctrl) {
		this.controller = ctrl;
	}
	
	@Override
	public Long getCode() {
		return Long.parseLong(this.codeField.getText());
	}
		
	@Override
	public String getTitleEx() {
		return this.titleField.getText();
	}
	
	@Override
	public String getCurator() {
		return this.curatorField.getText();
	}
	
	@Override
	public Calendar getDateBeginning() {
		return this.dateBegField.getCalendar();
	}
	
	@Override
	public Calendar getDateEnd() {
		return this.dateEndField.getCalendar();
	}
	
	@Override
	public Double getCostEx() {
		return Double.parseDouble(this.costExField.getValue().toString());
	}
	
	@Override
	public Double getCostTicket() {
		return Double.parseDouble(this.costTckJS.getValue().toString());
	}
	
	@Override
	public void reinit() {
		this.titleField.setText("");
		this.curatorField.setText("");
		this.dateBegField.setCalendar(null);
		this.dateEndField.setCalendar(null);
		this.costExField.setValue(ZERO);
		this.costTckJS.setValue(ZERO);
	}
	
	@Override
	public void setForm(final int index, final IArtGallery model) {
		this.titleField.setText(model.getExhibit().get(index).getTitleExhibit());
		this.curatorField.setText(model.getExhibit().get(index).getCurator());
		this.dateBegField.setCalendar(model.getExhibit().get(index).getBeginning());
		this.dateEndField.setCalendar(model.getExhibit().get(index).getEnd());
		this.costExField.setValue(model.getExhibit().get(index).getCostExhibit());
		this.costTckJS.setValue(model.getExhibit().get(index).getCostTicket());
	}

}
