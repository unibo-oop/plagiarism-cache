package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Libro;
import utilities.ControllerUtilities.TipoController;
import cartasoci.FidCard;
import cartasoci.User;

import com.thoughtworks.xstream.XStream;

import controller.BookController;
import controller.FidelityController;
import controller.IBookController;
import controller.IFidelityController;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class FileTabMenuGUI  extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1894309391410079587L;

	private final String[] names = {"Carica", "Salva", "Salva e chiudi"};
	/**
	 * 
	 * @param frame is the Jframe 
	 * 
	 * 
	 */
	public FileTabMenuGUI(final JFrame frame) {
		super("File");
		
		final IBookController controller = BookController.getIstance();
		final IFidelityController fidcontroller = FidelityController.getIstance();
		

		
		final JMenuItem[] buttons = new JMenuItem[names.length];
			
		for (int i = 0; i < names.length; i++) {
			buttons[i] = new JMenuItem(names[i]);
			add(buttons[i]);
			if (i > 0) {
				addSeparator();
			}
		}
		
		
		
		
		buttons[0].setMnemonic('c');
		buttons[0].addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				final JFileChooser fileChooser = new JFileChooser();
				fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Documento XML (*.xml)", "xml"));
				fileChooser.setDialogTitle("Specify a file to Load");   
				 
				final int userSelection = fileChooser.showSaveDialog(frame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    final File fileToLoad = fileChooser.getSelectedFile();
				    System.out.println("Load as file: " + fileToLoad.getAbsolutePath());
				    final XStream xstream = new XStream();
				    xstream.alias("User", User.class);
				    xstream.alias("Carta", FidCard.class);
				    xstream.alias("Libro", Libro.class);
				    xstream.alias("Lista", List.class);
				
					
				    
				    List<Libro> list;
					try {
						final ObjectInputStream ois = xstream.createObjectInputStream(new ObjectInputStream(new FileInputStream(fileToLoad)));
						
						controller.setType(TipoController.MAGAZZINO);
						list = (List<Libro>) ois.readObject();
					    controller.loadMemory(list);
					    list = (List<Libro>) ois.readObject();
						controller.setType(TipoController.ORDINI);
						controller.loadMemory(list);
						final Map<Integer, User> map = (Map<Integer, User>) ois.readObject();
						fidcontroller.loadMemory(map);
						

					    
					    ois.close();
					    
					} catch (IOException | ClassNotFoundException e) {
						JOptionPane.showMessageDialog(frame, "Errore nel caricamento");
						} 
					JOptionPane.showMessageDialog(frame, "Caricamento eseguito correttamente");
					
					
					
				}
			}
		});
		
		
		buttons[1].setMnemonic('S');
		buttons[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				
				saveMe(controller, fidcontroller, frame);
				
				
				JOptionPane.showMessageDialog(frame, "Salvataggio eseguito correttamente");
			}
		});
		
		buttons[2].setMnemonic('V');
		buttons[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				saveMe(controller, fidcontroller, frame);
				
				JOptionPane.showMessageDialog(frame, "Salvataggio eseguito correttamente, il programma si chiuderà");
				
				System.exit(0);
				
			}
		});
		
	}
	/**
	 * 
	 * @param controller is the BookController
	 * @param fidcontroller is the FidelityController
	 * @param frame is the JFrame
	 */
	private void saveMe(final IBookController controller, final IFidelityController fidcontroller, final JFrame frame) {
			
			final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("line.separator") + "Desktop");
			fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Documento XML (*.xml)", "xml"));
			fileChooser.setDialogTitle("Specify a file to Save");   
			 
			final int userSelection = fileChooser.showSaveDialog(frame);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    final File fileToLoad = fileChooser.getSelectedFile();
			    System.out.println("Save as file: " + fileToLoad.getAbsolutePath());
			    
			    File file;
			    if (fileToLoad.getAbsolutePath().endsWith(".xml")) {
			     	file = new File(fileToLoad.getAbsolutePath());
					   
			    } else {
			     	file = new File(fileToLoad + ".xml");
			    }
			    
			    final XStream xstream = new XStream();
			    xstream.alias("User", User.class);
			    xstream.alias("Carta", FidCard.class);
			    xstream.alias("Libro", Libro.class);
			    xstream.alias("Lista", List.class);
			    
			    try {
					controller.setType(TipoController.MAGAZZINO);
					final ObjectOutputStream out = xstream.createObjectOutputStream(new ObjectOutputStream(new FileOutputStream(file)));
					out.writeObject(controller.bookList());

					controller.setType(TipoController.ORDINI);
					out.writeObject(controller.bookList());
					
					out.writeObject(fidcontroller.getMap());
					
					out.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Errore nel salvataggio");
					}				
			}
			
		}
	}


