package thatlevelagain.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import thatlevelagain.ScreenSize;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.various_state.LevelStateGeneral;


/**
 * type of save.
 * @author francesco
 *
 */
public final class Save {

    private static final String PERCORSODIR = System.getProperty("user.dir") + "/ThatLevelAgain_Saved";
    /**
     * LVL 1.
     */
    public static final int LVL1 = 1;
    /**
     * Save levels add.
     */
    public static final int INCREMENT_SAVE_STATE = 3;
    private static JFileChooser chooser;
    private static File choose;
    private static File newFile;

    private Save() { }

    /**
     * new save implementation.
     * @param gamePanel 
     *         panel
     * @param level
     *        level 
     */
    public static void newSave(final GamePanel gamePanel, final LevelStateGeneral level) {
        final JDialog dialog = new JDialog();
        final JLabel label = new JLabel();
        final JButton ok = new JButton("OK");
        final JButton cancel = new JButton("CANCEL");
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setSize((int) ScreenSize.getScala().getWidth() * 10, (int) ScreenSize.getScala().getHeight() * 10 / 3);
        final JTextField text = new JTextField((int) (dialog.getSize().getWidth() / 25));
        dialog.setLayout(new FlowLayout());
        label.setFont(new Font("Helvetica", Font.BOLD, (int) ScreenSize.getScala().getWidth() / 3));
        label.setText("How do you want to save your file? (don't write .txt)");
        dialog.add(label);
        dialog.add(text);
        dialog.add(ok);
        dialog.add(cancel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        ok.addActionListener(e -> {
            try {
                final String s = PERCORSODIR + "/" + text.getText() + ".txt";
                final File f = new File(s);
                if (f.exists()) {
                    final JOptionPane option2 = new JOptionPane("File already exist!", JOptionPane.WARNING_MESSAGE);
                    final JDialog dialog2 = option2.createDialog("ADVERSIMENT");
                    dialog2.pack();
                    dialog2.setVisible(true);
                } else {
                    if (f.createNewFile()) {
                        newFile = f;
                    }
                    backup(newFile, level.getMap().getLevel());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dialog.setVisible(false);
        });
        cancel.addActionListener(e -> {
            dialog.setVisible(false);
        });
    }
    /**
     * existent save implementation.
     * @param panel 
     *         panel
     * @param level
     *         level
     */
    public static void existentSave(final GamePanel panel, final LevelStateGeneral level) {
        int n;
        chooser = new JFileChooser(PERCORSODIR);
        chooser.setFileFilter(new TextFileFilter());
        n = chooser.showOpenDialog(panel);
        if (n == JFileChooser.APPROVE_OPTION) {
            choose = chooser.getSelectedFile();
            backup(choose, level.getMap().getLevel());
        }
    }

    /**
     *
     */
    private static void backup(final File file, final int livello) {
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
            if (livello == Save.LVL1) {
                buffWrite.write(String.valueOf(livello));
            } else {
                buffWrite.write((String.valueOf(livello + Save.INCREMENT_SAVE_STATE)));
            }
      } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
