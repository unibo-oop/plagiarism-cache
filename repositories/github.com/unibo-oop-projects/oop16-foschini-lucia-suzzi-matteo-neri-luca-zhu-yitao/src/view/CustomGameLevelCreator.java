package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.PlumberControllerImpl;
import model.pkglevels.ImageLoaderImpl;
import model.pkglevels.Pair;
import model.pkglevels.Pipe;
import model.pkglevels.PlumberModelImpl;

/**
 * Class that realizes the level creation.
 * 
 * 
 *
 */
public class CustomGameLevelCreator extends GameLevelImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 2486333993160616491L;

    private final File customSolutionsFile = ImageLoaderImpl.getLoaderInstance().getCustomFIle();

    private Map<Pair<JButton, Pipe>, Pair<Integer, Integer>> customMap = new HashMap<>();
    private int contatore, righe, colonne;
    private final JTextField txtRows, txtColumns;
    private final JButton testRun, save;
    private final JPanel mainP, gridSelection;

    private JPanel gamePanel;

    private final int counter;
    private JButton btnFinish;
    private final PlumberControllerImpl controller = PlumberControllerImpl.getControllerInstance();

    

    /**
     * Returns the customMap used for the save phase.
     * 
     * @return custom map
     */
    public Map<Pair<JButton, Pipe>, Pair<Integer, Integer>> getMap() {
        return this.customMap;
    }

    /**
     * Initialize the map to an empty map.
     * 
     */
    public void setNewMap() {
        this.customMap = new HashMap<>();
    }

    /**
     * Return the button that has to be enabled.
     * 
     * @return the button
     */
    public JButton getSaveButt() {
        return this.save;
    }

    @Override
    public JButton getFinish() {
        return this.btnFinish;
    }

    /**
     * Constructor.
     * 
     * @param gm
     *            gameWindow
     * @param progressiveIntegerCounter
     *            counter, specify how many custom levels have been created so
     *            far
     */
    public CustomGameLevelCreator(final GameWindow gm, final int progressiveIntegerCounter) {
        super(gm);
        counter = progressiveIntegerCounter;

        mainP = new JPanel(new BorderLayout()); // pannello principale
        gridSelection = new JPanel(new FlowLayout());

        final JPanel controlsPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints controlConstraints = new GridBagConstraints();
        txtRows = new JTextField("Insert rows");
        controlConstraints.insets = new Insets(5, 0, 5, 0);
        txtColumns = new JTextField("Insert columns");
        final JButton submitChoise = new JButton("Create layout");
        testRun = new JButton("Test");
        gamePanel = new JPanel();
        controlConstraints.gridx = 0;
        controlConstraints.gridy = 0;
        testRun.setPreferredSize(new Dimension(100, 75));
        testRun.setEnabled(false);
        controlsPanel.add(testRun, controlConstraints);

        save = new JButton("Save");

        controlConstraints.gridx = 0;
        controlConstraints.gridy = 1;
        save.setPreferredSize(new Dimension(100, 75));
        save.setEnabled(false);
        gridSelection.add(txtRows);
        gridSelection.add(txtColumns);
        gridSelection.add(submitChoise);

        controlsPanel.add(save, controlConstraints);

        mainP.add(controlsPanel, BorderLayout.EAST);

        mainP.add(gridSelection, BorderLayout.NORTH);

        submitChoise.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                save.setEnabled(false);
                righe = Integer.parseInt(txtRows.getText());
                colonne = Integer.parseInt(txtColumns.getText());
                instantiateModel();
               /* submitChoise.setEnabled(false);
                txtRows.setFocusable(false);
                txtColumns.setFocusable(false);*/
                txtRows.setText("Insert rows");
                txtColumns.setText("Insert columns");
                createLayout(righe, colonne);
                
                testRun.setEnabled(true);

            }
        });
        final ActionListener a = e -> {
            saveCustomLevelLayout();
            closeWindow();
        };

        save.addActionListener(a);

        

        this.add(mainP);
        this.getContentPane().add(mainP);
        

        this.getRootPane().setDefaultButton(submitChoise);

        this.setSize(700, 700);
        this.setVisible(true);
        PlumberControllerImpl.centreWindow(this);
    }

    private void instantiateModel() {

        controller.addModel(new PlumberModelImpl(this, counter, righe, colonne));
        
        testRun.addActionListener(controller.getModel().solutionListener());
    }

    @Override
    public void loadImages() {
        super.loadImages();
        final Pipe t5 = super.getTube("SV");
        final Pipe t6 = super.getTube("SO");
        final Pipe t1 = super.getTube("CL");
        super.getTube("CD").addPipes(t5);

        t5.addPipes(t6);
        t6.addPipes(t1);
        super.getAllTubes().addAll(Arrays.asList(t5, t6));
    }

    private void createLayout(final int lines, final int columns) {

        JButton jb;
        final JButton jb1 = new JButton();
        mainP.remove(gamePanel);
        btnFinish = new JButton();

        gamePanel = new JPanel(new GridBagLayout());

        final GridBagConstraints c = new GridBagConstraints();

        if (contatore > 0) {
            gamePanel.setVisible(false);

        }
        int newX = 67;
        int newY = 64;
        //grid to large
        if (righe > 7 || colonne > 7) {

            newX = (int) (this.getWidth() / (righe + 2));
            newY = (int) (this.getHeight() / colonne + 2) - 20;

            ImageLoaderImpl.getLoaderInstance().loadImages(newX, newY);
            this.loadImages();
            System.out.println(newX + " " + newY);
        }

        contatore++;
        super.setStartAndFinish(jb1, ImageLoaderImpl.getLoaderInstance().getStartImage(), 0, 0, c, gamePanel, newX, newY);
        c.fill = GridBagConstraints.REMAINDER;
        super.setStartAndFinish(btnFinish, ImageLoaderImpl.getLoaderInstance().getEmptyImage(), columns - 1, lines + 1, c, gamePanel,
                newX, newY);

        gamePanel.setVisible(true);

        mainP.add(gamePanel, BorderLayout.CENTER);

        for (int x = 0; x < lines; x++) {

            for (int y = 0; y < columns; y++) {
                final Random r = new Random();
                jb = new JButton();

                final int j = r.nextInt(6);

                jb.setOpaque(true);

                jb.setBorderPainted(false);
                jb.setContentAreaFilled(false);
                jb.setBorder(null);

                super.getAllTubes().get(j).getImg();

                controller.getModel().setLevelStartConfig(j, jb, c, gamePanel, x, y, newX, newY);

                SwingUtilities.updateComponentTreeUI(this);

            }
        }

    }
    /**Saves the custom level
     * 
     */
    private void saveCustomLevelLayout() {
        try (FileWriter fr = new FileWriter(customSolutionsFile, true); BufferedWriter bfw = new BufferedWriter(fr)) {

            bfw.write(counter + " " + righe + " " + colonne);
            bfw.newLine();
            for (final Map.Entry<Pair<JButton, Pipe>, Pair<Integer, Integer>> temp : customMap.entrySet()) {
                bfw.write(temp.getKey().getY().getDir() + " " + temp.getKey().getY().getType() + " "
                        + temp.getValue().getX() + " " + temp.getValue().getY() + " ");
                bfw.newLine();
            }
            customMap = new HashMap<>();

        } catch (final IOException e) {

            e.printStackTrace();
        }

    }

}
