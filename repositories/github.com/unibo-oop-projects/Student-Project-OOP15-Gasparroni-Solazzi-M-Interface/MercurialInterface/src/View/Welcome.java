package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Control.Control;

import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

/**
 * Classe utilizzata come View di Benvenuto
 *
 * @author Filippo Solazzi
 *
 */
public class Welcome extends JFrame{
	
	private static final long serialVersionUID = -7610101991252736339L;
	
	/**
	* Costruttore che inizializza la propria View
	* con i rispettivi eventi
	*
	* @param ctr
	*          Control utilizzato per l'intercetto degli eventi
	*/
	public Welcome(Control ctr){
		
		class ImageComponent extends Component {
			private static final long serialVersionUID = 6154104590854935843L;
			BufferedImage img;

		      public void paint(Graphics g) {
		         g.drawImage(img, 0, 0, null);
		      }

		    /*  public ImageComponent(String path) {
		         try {
		            img = ImageIO.read(new File(path));
		         } catch (IOException e) {
		            e.printStackTrace();
		         }
		      }*/
		      
		      public ImageComponent(URL imgURL) {
			         try {
			            img = ImageIO.read(imgURL);
			         } catch (IOException e) {
			            e.printStackTrace();
			         }
			      }
		      public Dimension getPreferredSize() {
		         if (img == null) {
		            return new Dimension(100,100);
		         } else {
		            return new Dimension(img.getWidth(), img.getHeight());
		         }
		      }
	    }
		
		this.setTitle("Welcome");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(350,250);
		this.setLocationRelativeTo(null);
		
		JLabel ben = new JLabel("Benvenuti in Mercurial Interface");
		ben.setForeground(Color.RED);
		ben.setBounds(0, 0, 500, 20);
		
		JLabel preme = new JLabel("Premere START per continuare");
		preme.setForeground(Color.BLACK);
		preme.setBounds(0, 170, 500, 20);
		
		//ImageComponent img = new ImageComponent("src/Image/Start.png");

		URL imgURL= Image.class.getResource("/Image/Start.png");
		ImageComponent img = new ImageComponent(imgURL);
		
		img.setBounds(250, 130, 90, 90);
		img.setCursor(new Cursor(Cursor.HAND_CURSOR));
		img.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	ctr.exe();
				dispose();
	        }
        });
		
		
		java.net.URL imgURL2= Image.class.getResource("/Image/logo.png");
		//setContentPane(new JLabel(new ImageIcon("src/Image/logo.png")));

		setContentPane(new JLabel(new ImageIcon(imgURL2)));
		this.add(ben);
		this.add(preme);
		this.add(img);
	}
}
