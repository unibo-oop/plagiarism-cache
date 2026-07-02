package view.analysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import controller.AnalysisControllerImpl;
import controller.EnvironmentControllerImpl;
import controller.FileControllerImpl;
import model.analysis.save.ChartImg;
import model.analysis.save.ChartImgBuilder;

public class SaveAnalysisMenu extends JMenu {

    private static final long serialVersionUID = -531274145809287580L;
    private final FileControllerImpl fileController;
    private final AnalysisControllerImpl analysisController;
    private final JPanel panelCenter = new JPanel();
    private final AnalysisDialog gui;

    public SaveAnalysisMenu(final EnvironmentControllerImpl envController, 
                        final AnalysisControllerImpl analysisController, final AnalysisDialog gui) {
        this.analysisController = analysisController;
        this.fileController = new FileControllerImpl(envController);
        this.gui = gui;
        this.setVisible(true);
    }

    /**
     * @return the JMenu which shows saving options: save on default file,
     * choose a specific file or save the charts as jpeg file
     */
    protected JMenu createSaveMenu() {
            final JMenu menuFile = new JMenu("Save Analysis");
                final JMenuItem cancel = new JMenuItem("Exit");
                final JMenu radioButtonMenu = new JMenu("Choose where you want to save:");
                    final JRadioButtonMenuItem buttonDefault = new JRadioButtonMenuItem("Default file (~/output.txt)");
                    final JRadioButtonMenuItem buttonChoose = new JRadioButtonMenuItem("Choose file");
                    final JRadioButtonMenuItem buttonCharts = new JRadioButtonMenuItem("Save charts as JPEG files ");
                    final ButtonGroup group = new ButtonGroup();
                    group.add(buttonDefault);
                    group.add(buttonChoose);
                    group.add(buttonCharts);
                radioButtonMenu.add(buttonDefault);
                radioButtonMenu.add(buttonChoose);
                radioButtonMenu.add(buttonCharts);
            menuFile.add(radioButtonMenu);
            menuFile.addSeparator();
            menuFile.add(cancel);
        buttonDefault.addActionListener(new DefaultActionListener());
        buttonChoose.addActionListener(new ChooseActionListener());
        buttonCharts.addActionListener(new ChartsActionListener());
        cancel.addActionListener(new ExitActionListener());
        return menuFile;
    }

    private class DefaultActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            try {
                SaveAnalysisMenu.this.fileController.save();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private class ChooseActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            final JFileChooser chooser = new JFileChooser();
                final int result = chooser.showSaveDialog(panelCenter);
                if (result == JFileChooser.APPROVE_OPTION) {
                    SaveAnalysisMenu.this.fileController.setDestination(chooser.getSelectedFile());
                    try {
                        SaveAnalysisMenu.this.fileController.save();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            }
        }
    }

    private class ChartsActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            final ChartImg chartSaver = new ChartImgBuilder(analysisController);
            try {
                chartSaver.fairChartImg();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                chartSaver.profitChartImg();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class ExitActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            SaveAnalysisMenu.this.gui.dispose();
        }
    }

}
