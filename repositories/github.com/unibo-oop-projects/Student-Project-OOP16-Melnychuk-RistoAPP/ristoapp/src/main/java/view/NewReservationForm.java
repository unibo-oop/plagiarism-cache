package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ReservationModelView;
import model.DataContainer;

/**
 * /** Class which implements the view layer for the add reservation use case.
 *
 */
public class NewReservationForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2769722365090473127L;
	JButton btnAdd, btnClear, btnCancel;
	ReservationModelView modelView;

	public NewReservationForm(ReservationModelView modelView, MasterView masterView) {
		super(masterView);
		this.modelView = modelView;
		btnAdd = new JButton("Aggiunga");
		btnClear = new JButton("Pulisci");
		btnCancel = new JButton("Anulla");
		btnAdd.setBounds(50, 430, 100, 30);
		btnClear.setBounds(170, 430, 100, 30);
		btnCancel.setBounds(290, 430, 100, 30);
		add(btnAdd);
		add(btnClear);
		add(btnCancel);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!val.validate()) {
					modelView.bind(nameField.getText(), emailField.getText(), phoneField.getText(),
							tableField.getText(), timeField.getText(), dateField.getText(),
							numberOfPersonsField.getText(), menuField.getText());
					modelView.addReservation();
				}
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				emailField.setText("");
				phoneField.setText("");
				tableField.setText("");
				dateField.setText("");
				numberOfPersonsField.setText("");
				menuField.setText("");
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
