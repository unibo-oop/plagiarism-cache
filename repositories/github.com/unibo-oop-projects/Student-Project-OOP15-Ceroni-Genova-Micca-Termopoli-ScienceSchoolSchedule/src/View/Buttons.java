package View;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controller.ObjToSave;
import Controller.SaveController;
import Controller.SaveControllerInterface;
import ViewBy.MyListenerView;

/**
 * This class contains all of the principal buttons
 * that are insert in PanelButton Class.
 * Contains all listeners.
 * 
 * @author Anna Termopoli
 * 
 *         Modify by Galya Genova
 *
 */
public class Buttons {

	private final JButton insert;
	private final JButton generalView;
	private final JButton insertNew;
	private final JButton saveInExel;
	private final JButton resetTable;
	private final JButton cancel;
	private final JButton exit;

	private MyListenerView listenerViewGen = new MyListenerView();
	private SaveControllerInterface saveCntr = new SaveController();

	public Buttons(MainGUI mainGUI) {

		this.insert = new JButton("INSERISCI");
		this.generalView = new JButton("VISTA GENERALE");
		this.insertNew = new JButton("MODIFICA NOME PROF / AGGIUNGI AULA");
		this.saveInExel = new JButton("ESPORTA IN EXCEL");
		this.resetTable = new JButton("RESETTA LA TABELLA");
		this.cancel = new JButton("CANCELLA");
		this.exit = new JButton("<<<<<<<<  ESCI  >>>>>>>>");

		this.insert.addActionListener(l -> {
			new FrameInsert(mainGUI);
		});

		this.generalView.addActionListener(this.listenerViewGen);

		this.insertNew.addActionListener(l -> {
			new FrameModify();
		});

		this.resetTable.addActionListener(l -> {
			int reset = JOptionPane.showConfirmDialog(null, "Stai per resettare tutta la tabella! "
					+ "\nPrima di prosegure assicurati di aver salvato il file in Excel!"
					+ "\nVuoi continuare comunque?", "Attenzione!", JOptionPane.YES_NO_OPTION);
			if (reset == JOptionPane.YES_OPTION) {
				ObjToSave obj = this.saveCntr.getObjToSave();
				obj.setListReservation(new ArrayList<>());
				this.saveCntr.save(obj);
				int mess = JOptionPane.showConfirmDialog(null, "Tutti i dati cancellati!"
						+ "\nRiavvia il programma per aggiornare la tabella." + "\nVuoi proseguire?", "Attenzione!",
						JOptionPane.YES_NO_OPTION);
				if (mess == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		this.saveInExel.addActionListener(new MyListenerExcel());
		this.cancel.addActionListener(new MyListenerCancel(mainGUI));

		this.exit.addActionListener(l -> {
			int mess = JOptionPane.showConfirmDialog(null, "Vuoi chiudere il programma?", "USCITA",
					JOptionPane.YES_NO_OPTION);
			if (mess == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});

	}

	public JButton getExit() {
		return exit;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getInsert() {
		return insert;
	}

	public JButton getSaveInExel() {
		return saveInExel;
	}

	public JButton getResetTable() {
		return resetTable;
	}

	public JButton getGeneralView() {
		return generalView;
	}

	public JButton getInsertNew() {
		return insertNew;
	}

}
