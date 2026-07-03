package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class FileWrite {
	private static PrintWriter pw;
	private static String deskPath = System.getProperty("user.home") + "/Desktop";

	private FileWrite() {
	}

	/*
	 * Metodo Statico per la creazione di un file su desktop, prende in ingresso
	 * due stringhe di testo, una che identificherà il file, l'altra invece sarà
	 * ciò che poi verrà scritto all'interno del file.
	 * 
	 * @param filename il nome del file
	 * 
	 * @param toWrite la stringa di testo da scrivere
	 */

	public static void createFileOnDesktop(final String filename, final String toWrite) {
		try {
			File newDesktopFile = new File(deskPath, "[" + LocalDate.now().toString() + "]" + filename);
			if (!newDesktopFile.exists()) {
				newDesktopFile.createNewFile();
			}
			pw = new PrintWriter(new BufferedWriter(new FileWriter(newDesktopFile)));
			pw.write(toWrite);
			pw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERRORE", e.toString(), JOptionPane.ERROR_MESSAGE);
		}

	}
}
