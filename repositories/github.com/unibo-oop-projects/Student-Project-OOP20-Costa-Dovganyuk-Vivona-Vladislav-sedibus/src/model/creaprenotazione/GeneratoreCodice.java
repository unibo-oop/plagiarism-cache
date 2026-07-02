package model.creaprenotazione;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class GeneratoreCodice {

	private final static String PERCORSO_CODICE = System.getProperty("user.home") + System.getProperty("file.separator") + "codice.txt";

	private final static int NUMERO_CIFRE = 4;
	private int n;
	private String codice = "";
	
	private enum Lettera {
		A, B, C, D, E, F, G, H, I, J, K, L, M,
		N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
		
		private final static Random RAND = new Random();
		private final static List<Lettera> LETTERE = Collections.unmodifiableList(Arrays.asList(values()));
		
		public static Lettera letteraCasuale() {
			return LETTERE.get(RAND.nextInt(LETTERE.size()));
		}
	}
	
	public GeneratoreCodice() {
		if(this.fileCreato()) {
			this.leggiUltimoCod();
		} else {
			this.n = 0;
		}
		this.codice = Lettera.letteraCasuale().toString() + 
					  String.format("%0" + NUMERO_CIFRE + "d", this.n);
		this.n++;
		this.scriviUltimoCod();
	}
	
	private boolean fileCreato() {
		return new File(PERCORSO_CODICE).exists();
	}

	/**
	 * @return a string consisting of a random letter and an increasing number
	 */
	public String ottieni() {
		return this.codice;
	}
	
	/**
	 * With try-with-resources writes the final number of the code in the file
	 */
	private void scriviUltimoCod() {
		try (final BufferedWriter bw = new BufferedWriter(new FileWriter(PERCORSO_CODICE))) {
			bw.write(String.valueOf(this.n));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * With try-with-resources reads and assign the final number of the code from the file
	 */
	private void leggiUltimoCod() {
		try (final BufferedReader br = new BufferedReader(new FileReader(PERCORSO_CODICE))) {
			this.n = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
