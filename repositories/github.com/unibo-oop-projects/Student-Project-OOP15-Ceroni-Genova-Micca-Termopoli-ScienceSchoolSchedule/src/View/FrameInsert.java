package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.ControllerWorkers;
import Controller.ControllerWorkersInterface;
import Controller.Reservation;
import Controller.SaveController;
import Controller.SaveControllerInterface;
import Model.ErrorException;
import Model.WarningException;

/**
 * This class implements the Frame that provide to insert new item in the main
 * table. Contains the listener for the button apply, that implements the saving
 * on file.
 * 
 * 
 * @author Anna Termopoli
 * 
 *         Modify by Massimiliano Micca, Galya Genova
 *
 */
public class FrameInsert {

    private final JFrame frameInsert = new JFrame("Inserimento nuovo elemento nella tabella");
    private ComboBoxesViews combo = new ComboBoxesViews();
    private Labels label = new Labels();
    private ControllerWorkersInterface cntr = new ControllerWorkers();
    private SaveControllerInterface controller = new SaveController();
    private ControllerGuiInterface c = new ControllerGui();
  

    public FrameInsert(MainGUI mainGUI) {

        this.frameInsert.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frameInsert.setVisible(true);
        this.frameInsert.setSize(400, 200);
        this.frameInsert.setResizable(false);

        final JPanel panelInsert = new JPanel(new GridLayout(6, 2));

        panelInsert.add(this.label.getlProfessor());
        panelInsert.add(this.combo.getcProfessor());

        panelInsert.add(this.label.getlCorses());
        panelInsert.add(this.combo.getcCorses());

        panelInsert.add(this.label.getlDays());
        panelInsert.add(this.combo.getcDays());

        panelInsert.add(this.label.getlHours());
        panelInsert.add(this.combo.getcHours());

        panelInsert.add(this.label.getlRooms());
        panelInsert.add(this.combo.getcRooms());

        this.combo.LisenerCombo(this.combo.getcProfessor(), this.combo.getcCorses());

        final JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton apply = new JButton("Applica");
        apply.addActionListener(l -> {
            try {
                if(this.combo.getcProfessor().getSelectedItem().toString().equals(" ")){
                    throw new ErrorException("Selezionare tutti i campi ! ");
                }
                Reservation res = c.matchString(this.combo.getcProfessor().getSelectedItem().toString(),
                        this.combo.getcCorses().getSelectedItem().toString(),
                        this.combo.getcDays().getSelectedItem().toString(),
                        this.combo.getcHours().getSelectedItem().toString(),
                        this.combo.getcRooms().getSelectedItem().toString());

                cntr.addRes(res);
                controller.getObjToSave().setListReservation(cntr.getListReservation());
                controller.save(controller.getObjToSave());

                Integer row = c.getRow(res);
                Integer colum = c.getColum(res);
                mainGUI.update(res, row, colum);
                this.frameInsert.dispose();

            } catch (Exception e) {
                if (e instanceof WarningException) {
                    int i = JOptionPane.showConfirmDialog(null, e.getMessage(), "Warning", JOptionPane.YES_NO_OPTION);
                    if (i == JOptionPane.YES_OPTION) {
                        try {
                            Reservation res = c.matchString(this.combo.getcProfessor().getSelectedItem().toString(),
                                    this.combo.getcCorses().getSelectedItem().toString(),
                                    this.combo.getcDays().getSelectedItem().toString(),
                                    this.combo.getcHours().getSelectedItem().toString(),
                                    this.combo.getcRooms().getSelectedItem().toString());

                            cntr.getListReservation().add(res);
                            controller.getObjToSave().setListReservation(cntr.getListReservation());
                            controller.save(controller.getObjToSave());
                            Integer row = c.getRow(res);
                            Integer colum = c.getColum(res);
                            mainGUI.update(res, row, colum);
                            this.frameInsert.dispose();
                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }
                    }

                } else if (e instanceof ErrorException) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.OK_OPTION);
                } else {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error non previsto", JOptionPane.OK_OPTION);
                }

            }
        });
        panelButton.add(apply);

        this.frameInsert.add(panelInsert);
        this.frameInsert.add(panelButton, BorderLayout.SOUTH);
    }

}
