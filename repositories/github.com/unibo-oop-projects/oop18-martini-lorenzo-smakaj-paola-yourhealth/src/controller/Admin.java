package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Ambulatorio;
import model.AmbulatorioImpl;
import model.Dottore;
import model.DottoreImpl;
import model.Macchinario;
import model.MacchinarioImpl;
import model.Paziente;
import model.PazienteImpl;
import model.Prestazione;
import model.PrestazioneImpl;
import util.Enums;

public class Admin {

	public static int addPaziente(Paziente P) throws Exception {
		int up = -1;
		try {
			Connection con = Database.getConnection();

			PreparedStatement posted = con.prepareStatement(
					"INSERT INTO pazienti (Nome,Cognome,Sesso,LuogoNascita,DataNascita,Codicefiscale,Residenza) VALUES (?,?,?,?,?,?,?)");
			
			posted.setString(1, P.getNome());
			posted.setString(2, P.getCognome());
			posted.setString(3, P.getSesso());
			posted.setString(4, P.getLuogoNascita());
			posted.setString(5, P.getDataNascita().toString());
			posted.setString(6, P.getCodicefiscale());
			posted.setString(7, P.getResidenza());

			up = posted.executeUpdate();
			
			posted.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return up;
	}

	public static void removePaziente(String codicefiscale) {
		try {
			Connection con = Database.getConnection();
			
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM pazienti WHERE Codicefiscale = ?");
			posted.setString(1, codicefiscale);

			posted.executeUpdate();
			posted.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<Paziente> getListaPazienti() {
		try {
			Connection con = Database.getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT * FROM pazienti ORDER BY Cognome");

			ResultSet result = statement.executeQuery();

			ArrayList<Paziente> array = new ArrayList<Paziente>();
			while (result.next()) {
				Paziente P = new PazienteImpl(result.getString("Nome"), result.getString("Cognome"),
						Enums.Sesso.getFromString(result.getString("Sesso")), result.getString("LuogoNascita"),
						LocalDate.parse(result.getString("DataNascita"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						result.getString("Codicefiscale"), result.getString("Residenza"));

				array.add(P);
			}
			statement.close();
			con.close();
			return array;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static int addDottore(Dottore D) throws Exception {
		int up = -1;
		try {
			Connection con = Database.getConnection();
			

			PreparedStatement posted = con.prepareStatement(
					"INSERT INTO dottori (Nome,Cognome,Sesso,LuogoNascita,DataNascita,Tesserino,Ruolo,OrarioInizio,OrarioFine)"
					+ " VALUES (?,?,?,?,?,?,?,?,?)");
			
			posted.setString(1, D.getNome());
			posted.setString(2, D.getCognome());
			posted.setString(3, D.getSesso());
			posted.setString(4, D.getLuogoNascita());
			posted.setString(5, D.getDataNascita().toString());
			posted.setInt(6, D.getTesserino());
			posted.setString(7, D.getRuolo());
			posted.setString(8, D.getOrarioInizio().toString());
			posted.setString(9, D.getOrarioFine().toString());
			
			up = posted.executeUpdate();
			con.close();
			posted.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return up;
	}

	public static int removeDottore(int tesserino) {
		int up = -1;
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con.prepareStatement("DELETE FROM dottori WHERE Tesserino = ?");
			
			posted.setInt(1, tesserino);

			up = posted.executeUpdate();
			posted.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return up;
	}

	public static ArrayList<Dottore> getListaDottori() {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM dottori ORDER BY Cognome");

			ResultSet result = statement.executeQuery();

			ArrayList<Dottore> array = new ArrayList<Dottore>();
			while (result.next()) {
				Dottore D = new DottoreImpl(result.getString("Nome"), result.getString("Cognome"),
						Enums.Sesso.getFromString(result.getString("Sesso")), result.getString("LuogoNascita"),
						LocalDate.parse(result.getString("DataNascita"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						result.getInt("Tesserino"), Enums.Ruolo.getFromString(result.getString("Ruolo")),
						LocalTime.parse(result.getString("OrarioInizio"), DateTimeFormatter.ofPattern("HH:mm")),
						LocalTime.parse(result.getString("OrarioFine"), DateTimeFormatter.ofPattern("HH:mm")));
				array.add(D);
			}
			System.out.println("Lista Dottori Selezionata");
			statement.close();
			con.close();
			return array;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static int addMacchinario(Macchinario M) {
		int up = -1;
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO macchinari (Codice,Tipo) VALUES (?,?)");
			
			posted.setString(1, M.getCodice());
			posted.setString(2, M.getTipo());
			up = posted.executeUpdate();
			con.close();
			posted.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return up;
	}

	public static void removeMacchinario(String codice){
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con.prepareStatement("DELETE FROM macchinari WHERE Codice = ?");
			posted.setString(1, codice);
			posted.executeUpdate();
			con.close();
			posted.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<Macchinario> getListaMacchinari() {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM macchinari ORDER BY Tipo");

			ResultSet result = statement.executeQuery();

			ArrayList<Macchinario> array = new ArrayList<Macchinario>();
			while (result.next()) {
				Macchinario M = new MacchinarioImpl.Builder().codice(result.getString("Codice"))
						.tipo(Enums.TipoMacchinario.getFromString(result.getString("Tipo")))
						.build();
				array.add(M);
			}
			System.out.println("Lista Macchinari Selezionata");
			con.close();
			statement.close();
			return array;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static int addAmbulatorio(Ambulatorio A) {
		int up = -1;
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO ambulatori (Codice,Tipo) VALUES (?,?)");
			
			posted.setString(1, A.getCodice());
			posted.setString(2, A.getTipo());

			up = posted.executeUpdate();
			con.close();
			posted.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return up;
	}

	public static void removeAmbulatorio(String codice) {
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con.prepareStatement("DELETE FROM ambulatori WHERE Codice = ?");
			
			posted.setString(1, codice);

			posted.executeUpdate();
			posted.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<Ambulatorio> getListaAmbulatori(){
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM ambulatori ORDER BY Tipo");

			ResultSet result = statement.executeQuery();

			ArrayList<Ambulatorio> array = new ArrayList<Ambulatorio>();
			while (result.next()) {
				Ambulatorio A = new AmbulatorioImpl.Builder().codice(result.getString("Codice"))
						.tipo(Enums.TipoAmbulatorio.getFromString(result.getString("Tipo"))).build();
				array.add(A);
			}
			System.out.println("Lista Ambulatori Selezionata");
			statement.close();
			con.close();
			return array;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void addPrestazione(Prestazione Pr) throws Exception {
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con.prepareStatement(
					"INSERT INTO prestazioni (CF_Paziente,ID_Dottore,Tipo,Data,Ora,Stato,Macchinario,Ambulatorio)"
					+" VALUES (?,?,?,?,?,?,?,?)");
			
			posted.setString(1, Pr.getPaziente());
			posted.setInt(2, Pr.getDottore());
			posted.setString(3, Pr.getTipo());
			posted.setString(4, Pr.getData().toString());
			posted.setString(5, Pr.getOra().toString());
			posted.setString(6, Pr.getStato());
			posted.setString(7, Pr.getMacchinario());
			posted.setString(8, Pr.getAmbulatorio());
			posted.executeUpdate();
			
			posted.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static int removePrestazione(String codicefiscale, int tesserino, LocalDate data, LocalTime ora) throws Exception {
		int up = -1;
		try {
			Connection con = Database.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM prestazioni WHERE CF_Paziente = ? and ID_Dottore = ?"
							+" and Data = ? and Ora = ?");
			posted.setString(1, codicefiscale);
			posted.setInt(2, tesserino);
			posted.setString(3, data.toString());
			posted.setString(4, ora.toString());
			up = posted.executeUpdate();
			
			con.close();
			posted.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return up;
	}

	public static ArrayList<Prestazione> getListaPrestazioni() throws Exception {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM prestazioni ORDER BY Data");

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
			System.out.println("Lista Prestazioni Selezionata");
			statement.close();
			con.close();
			return array;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static Macchinario getMacchinarioFromDB(String codice) {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM macchinari WHERE Codice = ?");
			
			statement.setString(1, codice);

			ResultSet result = statement.executeQuery();


			Macchinario M = new MacchinarioImpl.Builder()
					.codice(result.getString("Codice"))
					.tipo(Enums.TipoMacchinario.getFromString(result.getString("Tipo")))
					.build();
			
			statement.close();
			con.close();
			return M;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static Ambulatorio getAmbulatorioFromDB(String codice) {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM ambulatori WHERE Codice = ?");

			ResultSet result = statement.executeQuery();

			Ambulatorio A = new AmbulatorioImpl.Builder().codice(result.getString("Codice"))
					.tipo(Enums.TipoAmbulatorio.getFromString(result.getString("Tipo")))
					.build();
			
			statement.close();
			con.close();
			return A;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static boolean checkDisponibilitaPrestazione(int tesserino, LocalDate data, LocalTime ora, String codiceMacch,
			String codiceAmb) {
		try {
			Connection con = Database.getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM prestazioni WHERE Data = ? AND Ora = ?");
			
			statement.setString(1, data.toString());
			statement.setString(2, ora.toString());

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
			
			statement.close();
			con.close();
			
			for (Prestazione object : array) {
				if (object.getDottore() == tesserino || object.getMacchinario() == codiceMacch
						|| object.getAmbulatorio() == codiceAmb) {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}

}