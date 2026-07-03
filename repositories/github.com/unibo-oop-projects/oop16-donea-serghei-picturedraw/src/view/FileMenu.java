package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import images.ImageLoader;

/**
 * Classe che contiene il JMenu File
 * @author redim
 *
 */

@SuppressWarnings("serial")
public class FileMenu extends JMenu {
    private static CanvasBoard imageView;

    public FileMenu() {
        super("File");
        imageView = CanvasBoard.getInstance();
        
        JMenuItem newImageItem = new JMenuItem("new workspace", new ImageIcon(ImageLoader.setImage("new.png")));
        ActionListener newImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                final int sw = (int) screen.getWidth();
                final int sh = (int) screen.getHeight();
                BufferedImage bi = new BufferedImage(
                        sw, sh, BufferedImage.TYPE_INT_ARGB);
                imageView.clear(bi, true);
                imageView.setImage(bi, true);
            }
        };
        newImageItem.addActionListener(newImage);
        this.add(newImageItem);

      
            this.addSeparator();
            
            ActionListener openListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        JFileChooser ch = new JFileChooser();
                        ch.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
                        int result = ch.showOpenDialog(imageView);
                        if (result==JFileChooser.APPROVE_OPTION ) {
                            try {
                                BufferedImage bi = ImageIO.read(
                                        ch.getSelectedFile());
                                imageView.setImage(bi, true);
                            } catch (IOException e) {
                                imageView.showError(e);
                            }
                        }                   
                }
            };
            
            
            JMenuItem openItem = new JMenuItem("open a picture", new ImageIcon(ImageLoader.setImage("open.png")));
            openItem.addActionListener(openListener);
            this.add(openItem);

            ActionListener saveListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser ch =  new JFileChooser();
                    int result = ch.showSaveDialog(imageView);
                    if (result==JFileChooser.APPROVE_OPTION ) {
                        try {
                            File f = ch.getSelectedFile();
                            ImageIO.write(imageView.getCanvasImage(), "png", f);
                        } catch (IOException ioe) {
                            imageView.showError(ioe);
                        }
                    }
                }
            };
            
            JMenuItem saveItem = new JMenuItem("save the picture", new ImageIcon(ImageLoader.setImage("save.png")));
            saveItem.addActionListener(saveListener);
            this.add(saveItem);
    

            ActionListener exit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.exit(0);
                }
            };
            
            JMenuItem exitItem = new JMenuItem("exit", new ImageIcon(ImageLoader.setImage("exit.png")));
            this.addSeparator();
            exitItem.addActionListener(exit);
            this.add(exitItem);
        
    }
       
     

}
