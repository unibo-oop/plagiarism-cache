package thatlevelagain.main;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.view.Save;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.panel.GamePanel;

/**
 * Main.
 *
 */

public final class Main {

    private static final int NUMBER_LEVELS = 12;

    private Main() {
        //not used
    }

    /**
     * 
     * @param args
     *     string
     */
    public static void main(final String[] args) {
        ImageManager.init();
        SoundManager.init();
        creaCartella();
        final JFrame frame = new JFrame("That Level Again");
        frame.setContentPane(new GamePanel(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void creaCartella() {
        final String dir = System.getProperty("user.dir") + "/ThatLevelAgain_Saved";
        if ((new File(dir)).mkdir()) {
            for (int i = 1; i <= NUMBER_LEVELS; i++) {
                final File f = new File(dir + "/level" + i + ".txt");
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try (FileWriter fileWriter = new FileWriter(f);
                        BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
                    if (i == Save.LVL1) {
                        buffWrite.write(String.valueOf(i));
                    } else {
                        buffWrite.write((String.valueOf(i + Save.INCREMENT_SAVE_STATE)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Saves directory just exist");
        }
    }
}
