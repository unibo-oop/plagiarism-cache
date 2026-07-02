package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.CancellationModelView;
import model.DataContainer;
import util.Utilities;

/**
 * Class which implements the view layer for the cancellation use case.
 *
 */
public class CancellationForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 289227851920857868L;

	// Table, Name, DAteTime, number Of persons, Telephone, Menu (as notes)
	// The reference to the top level window
	MasterView masterView;

	JButton btnDel, btnCancel;

	CancellationModelView modelView;

	public CancellationForm(CancellationModelView modelView, MasterView masterView) {
		super(masterView);
		this.modelView = modelView;
		this.modelView.initializeWithSelectedReservation();
		this.reservationId = this.modelView.getReservation().getId();
		this.nameField.setText(this.modelView.getReservation().getName());
		this.emailField.setText(this.modelView.getReservation().getEmail());
		this.phoneField.setText(this.modelView.getReservation().getPhone());
		this.timeField.setText(Utilities.getFormattedTime(this.modelView.getReservation().getDate()));
		this.dateField.setText(Utilities.getFormattedDate(this.modelView.getReservation().getDate()));
		this.tableField.setText(this.modelView.getReservation().getTable().toString());
		this.menuField.setText(this.modelView.getReservation().getMenu());
		this.numberOfPersonsField.setText(this.modelView.getReservation().getNumberOfPersons().toString());
		modelView.bind(this.reservationId.intValue());
		btnDel = new JButton("Cancella");
		btnCancel = new JButton("Anulla");
		btnDel.setBounds(50, 390, 100, 30);
		btnCancel.setBounds(170, 390, 100, 30);
		add(btnDel);
		add(btnCancel);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelView.cancelReservation();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataContainer.remove(DataContainer.SELECTED_RESERVATION_ID);
				modelView.toBase();
			}
		});
	}

}
