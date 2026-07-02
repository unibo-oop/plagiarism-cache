package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Dottore;
import model.DottoreImpl;
import model.Prestazione;
import model.PrestazioneImpl;
import util.Enums;

public class AccountDottore {

	public static ArrayList<Prestazione> listaPrestazioniDottore(int tesserino) {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con
					.prepareStatement("SELECT * FROM prestazioni WHERE ID_Dottore=?");
			statement.setInt(1, tesserino);;

			ResultSet result = statement.executeQuery();

			ArrayList<Prestazione> array = new ArrayList<Prestazione>();
			while (result.next()) {

				Prestazione Pr = new PrestazioneImpl.Builder().paziente(result.getString("CF_Paziente")).dottore(result.getInt("ID_Dottore"))
						.tipoprestazione(Enums.TipoPrestazione.getFromString(result.getString("Tipo")))
						.data(LocalDate.parse(result.getString("Data"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
						.ora(LocalTime.parse(result.getString("Ora"), DateTimeFormatter.ofPattern("HH:mm")))
						.stato(Enums.Stato.getFromString(result.getString("Stato"))).macchinario(result.getString("Macchinario"))
						.ambulatorio(result.getString("Ambulatorio")).build();

				array.add(Pr);
			}
			con.close();
			statement.close();
			return array;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static Dottore getDottoreFromDB(int tesserino) {
		Dottore D = null;
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con
					.prepareStatement("SELECT * FROM dottori WHERE Tesserino=?");
			statement.setInt(1, tesserino);

			ResultSet result = statement.executeQuery();
			result.next();
			D = new DottoreImpl(result.getString("Nome"), result.getString("Cognome"),
					Enums.Sesso.getFromString(result.getString("Sesso")), result.getString("LuogoNascita"),
					LocalDate.parse(result.getString("DataNascita"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					result.getInt("Tesserino"), Enums.Ruolo.getFromString(result.getString("Ruolo")),
					LocalTime.parse(result.getString("OrarioInizio"), DateTimeFormatter.ofPattern("HH:mm")),
					LocalTime.parse(result.getString("OrarioFine"), DateTimeFormatter.ofPattern("HH:mm")));

			statement.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return D;
	}

	public static boolean checkEsistenzaTesserino (int tesserino) {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM dottori WHERE Tesserino=?");
			statement.setInt(1, tesserino);
			ResultSet result = statement.executeQuery();
			

			
			if (!result.isBeforeFirst()) {
				statement.close();
				con.close();
				return false;
			} else {
				statement.close();
				con.close();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}