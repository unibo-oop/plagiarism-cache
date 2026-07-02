package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import model.Model;
import model.Pair;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.user.UserImpl;
import view.View;
import view.ViewImpl;

/**
 * Class which does the operations of I/O.
 *
 * @author
 *
 */
public class FileManager {

	// constants for I/O
	/**
	 * Name of the file containing the archive of users.
	 */
	public static final String FILENAMEUSER = "archivio.utenti";
	/**
	 * Name of the file containing the archive of items.
	 */
	public static final String FILENAMEITEM = "archivio.oggetti";
	/**
	 * Name of the file containing the status of study room.
	 */
	public static final String FILENAMESTUDYROOM = "archivio.aulastudio";
	/**
	 * Path to the folder where files are saved/read.
	 */
	public static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");

	private View v = new ViewImpl();

	/**
	 * Method that writes an object into a file.
	 *
	 * @param fileName
	 *            name of the file to write.
	 * @param model
	 *            implementation of the model.
	 * @throws IOException
	 *             when the file at the target path is missing
	 */
	public void writeObjectIntoFile(final String fileName, final Model model) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileManager.PATH + fileName))) {
			if (fileName.equals(FileManager.FILENAMEUSER)) {
				oos.writeObject(model.getUserArchive());
			} else if (fileName.equals(FileManager.FILENAMEITEM)) {
				oos.writeObject(model.getItemArchive());
			} else if (fileName.equals(FileManager.FILENAMESTUDYROOM)) {
				oos.writeObject(model.getStudyRoom());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.v.showError("File " + fileName + " non trovato per la scrittura");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.v.showError("Errore I/O");
		}
	}

	/**
	 * Method that reads the content of the ArchiveItem's file.
	 *
	 * @param fileNameItem
	 *            name of the file to read.
	 * @return returns the content of the file casted into the right type
	 */
	public Map<Integer, Pair<ItemImpl, ItemInfo>> readArchiveItemFromFile(final String fileNameItem) {
		Map<Integer, Pair<ItemImpl, ItemInfo>> objectItem = null;
		try (ObjectInputStream oisItem = new ObjectInputStream(new FileInputStream(FileManager.PATH + fileNameItem))) {
			objectItem = (Map<Integer, Pair<ItemImpl, ItemInfo>>) oisItem.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.v.showError("File " + fileNameItem + " non trovato per la lettura");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.v.showError("Errore I/O");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError("File .oggetti non trovato:\n" + e2.getMessage());
		}
		return objectItem;
	}

	/**
	 * Method that reads the content of the ArchiveUser's file.
	 *
	 * @param fileNameUser
	 *            name of the file to read.
	 * @return returns the content of the file casted into the right type
	 */
	public Map<Integer, UserImpl> readArchiveUserFromFile(final String fileNameUser) {
		Map<Integer, UserImpl> objectUser = null;
		try (ObjectInputStream oisUser = new ObjectInputStream(new FileInputStream(FileManager.PATH + fileNameUser))) {
			objectUser = (Map<Integer, UserImpl>) oisUser.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.v.showError("File " + fileNameUser + " non trovato per la lettura");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.v.showError("Errore I/O");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError("File .utenti non trovato:\n" + e2.getMessage());
		}
		return objectUser;
	}

	/**
	 * Method that reads the content of the StudyRoom's file.
	 *
	 * @param fileNameStudyRoom
	 *            name of the file to read.
	 * @return returns the content of the file casted into the right type
	 */
	public Map<GregorianCalendar, ArrayList<Integer>> readStudyRoomFromFile(final String fileNameStudyRoom) {
		Map<GregorianCalendar, ArrayList<Integer>> objectStudyRoom = null;
		try (ObjectInputStream oisStudyRoom = new ObjectInputStream(
				new FileInputStream(FileManager.PATH + fileNameStudyRoom))) {
			objectStudyRoom = (Map<GregorianCalendar, ArrayList<Integer>>) oisStudyRoom.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.v.showError("File " + fileNameStudyRoom + " non trovato per la lettura");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.v.showError("Errore I/O");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			this.v.showError("File .oggetti non trovato:\n" + e2.getMessage());
		}
		return objectStudyRoom;
	}
}