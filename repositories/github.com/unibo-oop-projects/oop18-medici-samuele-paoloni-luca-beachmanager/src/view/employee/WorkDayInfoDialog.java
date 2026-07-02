package view.employee;

import java.awt.Frame;

import javax.swing.JLabel;

import view.InfoDialog;

/**
 * InfoDialog per i lavoratori che hanno il turno in un determinato giorno
 * @author Samuele Medici, samuele.medici2@studio.unibo.it (Mat. 0000718877 )
 *
 */
public class WorkDayInfoDialog extends InfoDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2061744525583325239L;

	public WorkDayInfoDialog(Frame parent, int selectedDay, int selectedMonth, int selectedYear) {
		super(parent);
		
		this.setSize(200,300);
		
		this.add(new JLabel(selectedDay + "/" + selectedMonth + "/" + selectedYear));
		
	}

	

}
