package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ObjToSave;
import Controller.SaveController;
import Controller.SaveControllerInterface;
import Model.CoursesImpl;
import Model.Professor;
import Model.RoomImpl;

/**
 * This frame allow to modify the name of specific professor and add new
 * classroom
 * 
 * @author Anna Termopoli
 *
 */
public class FrameModify {

	private final static String EMPTYSTR = " ";

	private final JFrame frameModify = new JFrame("Acquisizione nuovo elemento");
	private SaveControllerInterface controller = new SaveController();
	private ObjToSave objToSave = controller.getObjToSave();
	private final JButton prof = new JButton("Professore");
	private final JButton room = new JButton("Aula");
	private final JComboBox<String> comboProf = new JComboBox<String>();

	public FrameModify() {
		this.frameModify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameModify.setVisible(true);
		this.frameModify.setSize(400, 180);
		this.frameModify.setResizable(false);

		final JPanel panelModify = new JPanel(new GridBagLayout());
		final GridBagConstraints cnst = new GridBagConstraints();
		cnst.gridy = 0;
		cnst.fill = GridBagConstraints.HORIZONTAL;
		panelModify.add(prof, cnst);
		cnst.gridy++;
		panelModify.add(room, cnst);

		prof.addActionListener(l -> {

			this.frameModify.setVisible(false);
			final JFrame frameProf = new JFrame();
			frameProf.setVisible(true);
			frameProf.setTitle("Inserimento nuovo professore");
			frameProf.setSize(800, 180);
			frameProf.setResizable(false);
			final JPanel profPanel = new JPanel(new GridBagLayout());
			final GridBagConstraints cost = new GridBagConstraints();
			final JLabel profLabel = new JLabel("Modifica professore: ");
			final JLabel prof1Label = new JLabel("Nome nuovo professore: ");
			final JLabel prof2Label = new JLabel("Cognome nuovo professore: ");

			final JTextField nameProf = new JTextField(15);
			final JTextField surnameProf = new JTextField(15);

			this.comboProf.addItem(EMPTYSTR);
			for (Professor p : objToSave.getListProfessor()) {
				comboProf.addItem(p.getPerson().toString());
			}

			cost.gridx = 0;
			cost.gridy = 1;
			cost.fill = GridBagConstraints.BOTH;
			profPanel.add(profLabel, cost);
			cost.gridx = 1;
			profPanel.add(this.comboProf, cost);
			cost.gridy++;
			cost.gridy++;
			cost.gridx = 0;
			profPanel.add(prof1Label, cost);
			cost.gridx = 1;
			profPanel.add(nameProf, cost);
			cost.gridy++;
			cost.gridx = 0;
			profPanel.add(prof2Label, cost);
			cost.gridx = 1;
			profPanel.add(surnameProf, cost);

			final JButton save = new JButton("Salva");
			final JPanel panelSave = new JPanel();
			panelSave.add(save);
			frameProf.add(profPanel);
			frameProf.add(panelSave, BorderLayout.SOUTH);
			

			save.addActionListener(e -> {
				boolean control1=true;
				if(nameProf.getText().equals("") || surnameProf.getText().equals(" ")){
					JOptionPane.showMessageDialog(null, "Inserisci un nuovo nome e cognome",
							null, JOptionPane.ERROR_MESSAGE);
					control1 = false;
					
				}
				
				if(control1==true){
				List<String> temp = new ArrayList<>();
				for (Professor prof : objToSave.getListProfessor()) {
					if (prof.getPerson().toString().equals(comboProf.getSelectedItem())) {
						for (CoursesImpl co : prof.getCourses()) {
							temp.add(co.getName());
						}
					}
				}

				int f = JOptionPane.showConfirmDialog(null,
						"STAI SOSTITUENDO IL PROFESSORE:  " + comboProf.getSelectedItem() + (" \n")
								+ "CON IL PROFESSORE: " + nameProf.getText() + ("  ") + surnameProf.getText()
								+ "\nPER I CORSI:\n" + temp.toString() + "\n\nVuoi continuare?", "Attenzione",
						JOptionPane.YES_NO_CANCEL_OPTION);

				if (f == JOptionPane.YES_OPTION) {

					for (Professor p : objToSave.getListProfessor()) {
						String prof = comboProf.getSelectedItem().toString();

						if (prof.equals(p.getPerson().toString())) {
							p.getPerson().setName(nameProf.getText());
							p.getPerson().setSurname(surnameProf.getText());
						}

						controller.save(objToSave);
					}
					int g = JOptionPane.showConfirmDialog(null,
							"Riavvia per visualizzare il professore sull'interfaccia!"
									+ "\n Vuoi chiudere adesso il programma?", null, JOptionPane.YES_NO_OPTION);
					if (g == JOptionPane.YES_OPTION) {
						System.exit(0);
					}

				}
				}
			});
		
		});
		room.addActionListener(l -> {

			this.frameModify.dispose();
			final JFrame frameRoom = new JFrame();
			frameRoom.setVisible(true);
			frameRoom.setTitle("Inserimento nuovo professore");
			frameRoom.setSize(400, 180);
			frameRoom.setResizable(false);
			final JPanel roomPanel = new JPanel(new GridBagLayout());
			final JLabel roomLabel = new JLabel("Aula:");
			final JTextField insRoom = new JTextField(15);
			final JButton save = new JButton("Salva");
			final JPanel panelSave = new JPanel();
			panelSave.add(save);
			frameRoom.add(roomPanel);
			frameRoom.add(panelSave, BorderLayout.SOUTH);
			save.addActionListener(e -> {

				boolean contol = true;
				String s = insRoom.getText().toString();
				String sMai = s.toUpperCase();

				for (RoomImpl r : objToSave.getListRoom()) {
					String ss = r.getNameRoom().toString();
					String ssMai = ss.toUpperCase();
					if (sMai.equals(ssMai)) {
						JOptionPane.showMessageDialog(null, "Non e' possibile aggungere l'aula perchè già esiste!",
								null, JOptionPane.ERROR_MESSAGE);
						contol = false;

					}
				}
				if (contol == true) {
					int n = JOptionPane.showConfirmDialog(null, "STAI SALVANDO L'AULA:   " + insRoom.getText()
							+ "\nVuoi continuare?", "Attenzione", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						List<RoomImpl> temp = objToSave.getListRoom();
						temp.add(new RoomImpl(insRoom.getText()));
						objToSave.setListRoom(temp);
						controller.save(objToSave);
						frameRoom.dispose();
						int f = JOptionPane.showConfirmDialog(null, "Riavvia per visualizzare l'aula sull'interfaccia!"
								+ "\n Vuoi chiudere adesso il programma?", null, JOptionPane.YES_NO_OPTION);
						if (f == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}
				}
			});
			roomPanel.add(roomLabel);
			roomPanel.add(insRoom);
			frameRoom.add(roomPanel);

		});

		frameModify.add(panelModify, BorderLayout.CENTER);
		this.frameModify.setVisible(true);

	}
}
