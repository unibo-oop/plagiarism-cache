package dev.spaccabolle.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import dev.spaccabolle.states.StateMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class Display.
 */
public class Display {
	

        /** The frame. */
        private static JFrame frame;
        
        /** The canvas. */
        private Canvas canvas;
        
        /** The title. */
        private String title;
        
        /** The height. */
        private int width, height;
        
        /**
         * Instantiates a new display.
         *
         * @param title the title
         * @param width the width
         * @param height the height
         */
        public Display(String title, int width, int height){
                this.title = title;
                this.width = width;
                this.height = height;
                createDisplay();
        }
        
        /**
         * Creates the display.
         */
        private void createDisplay(){
                frame = new JFrame(title);
                frame.setSize(width, height);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setIconImage(new ImageIcon(getClass().getResource("/res/textures/draghetto4.png")).getImage());
                canvas = new Canvas();
                canvas.setPreferredSize(new Dimension(width, height));
                canvas.setMaximumSize(new Dimension(width, height));
                canvas.setMinimumSize(new Dimension(width, height));
                canvas.setFocusable(false);
                frame.add(canvas);
                frame.pack();
        }
        
        /**
         * Close display.
         */
        public static void closeDisplay() {
            frame.setVisible(false);
            frame.dispose();
        }
        
        /**
         * Gets the file.
         *
         * @param menuFile the menu file
         * @return the file
         */
		/*
		 * public static File getFile() {
		 * 
		 * String filePath = new File("").getAbsolutePath(); JFileChooser fileChooser =
		 * new JFileChooser(new File(filePath+"/res/map/")); int response =
		 * fileChooser.showOpenDialog(frame); if(response ==
		 * JFileChooser.APPROVE_OPTION) { File file= new
		 * File(fileChooser.getSelectedFile().getAbsolutePath()); return file; } return
		 * null; }
		 */
        
/**
 * Save file.
 *
 * @param menuFile the menu file
 */
public static void saveFile(StateMenu menuFile) {
				ProtectionDomain pd = Display.class.getProtectionDomain();
				CodeSource cs = pd.getCodeSource();
				URL location = cs.getLocation();
                JFileChooser fileChooser = new JFileChooser(new File(location+"save.txt"));
                fileChooser.setDialogTitle("Save your game");   
                int retrival = fileChooser.showSaveDialog(frame);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    File fw = new File(fileChooser.getSelectedFile()+".txt");
                    @SuppressWarnings("static-access")
                FileInputStream in = new FileInputStream(menuFile.saveGame);
                    FileOutputStream out = new FileOutputStream(fw);          
                    try {             
                                int n;        
                                while ((n = in.read()) != -1) {                    
                            out.write(n);
                        }
                    }
                    finally {
                        if (in != null) {             
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        /**
         * Gets the canvas.
         *
         * @return the canvas
         */
        public Canvas getCanvas(){
                return this.canvas;
        }
        
        /**
         * Gets the frame.
         *
         * @return the frame
         */
        public JFrame getFrame(){
                return frame;
        }
        
}
