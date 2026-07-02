package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;

/***
 * interfaccia per la gestione delle riunione
 * @author Juan
 *
 */
public interface modelReunionsI {
	/***
	 * settto la data e ora della riunione
	 * @param data
	 * data e ora 
	 */
	void setDateAndHour(java.util.Date data);
	/***
	 * setto il nome per questa riunione
	 * @param nome
	 * string con il nome della riunione
	 */
	void setNameReunion(String nome);
	/***
	 * setto il risponsabile di questa riunione
	 * @param risposabileRiunione
	 * il risponsabile della riunione(l'utente corrente)
	 */
	void setResponsible(modelUsersI risposabileRiunione);
	/***
	 * dettagli della riunione
	 * @param dettagli
	 * string con i dettagli della riunione
	 */
	void setReunionDetails(String dettagli);
	
	/***
	 * ottiene l'id del record
	 * @return
	 * un integer con l'iD
	 */
	Integer getID();
	/***
	 * ottiene il nome assegnato alla riunione
	 * @return
	 * una stringa con il nome
	 */
	String getNameReunion();
	/***
	 * ottiene il responsabile della riunione
	 * @return
	 * il responsabile come modelUsersI altrimenti null
	 */
	modelUsersI getResponsible();
	/***
	 * ottiene le referenze per contattare il responsabile
	 * @return
	 * una string contenente i dati
	 */
	String getReferences();
	/***
	 * ottiene la data della riunione
	 * @return
	 * un object sql.date
	 */
	Date getDate();
	/***
	 * ottiene l'ora della riunione
	 * @return
	 * una string con l'ora della riunione
	 */
	String getTime();
	/***
	 * ottiene i detaggli, tra cui il motivo, luogo e altro della riunione
	 * @return
	 * una stringa contenente i dati
	 */
	String getReunionDetails();

	/***
	 * elimna la riunione corrente
	 * @return
	 * true o false a seconda dell'esito
	 */
	boolean deleteReunion();
	/***
	 * aggiornamento(salvattaggio o modifica) di una rinuione
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	boolean update();
	
	
	/***
	 * ottiene una lista con tutte le riunione svolte fino a quel giorno(escluso)
	 * @param dataCorrente
	 * dataCorrente(odierna))
	 * @return
	 * una lista contenente tutte le riunioni filtrate
	 */
	public static List<modelReunionsI>pastReunionsList(Date dataCorrente){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
			try {
				return query.find().stream()
						.map(e -> new modelReunions(e))
						.filter(e -> e.getDate().before(dataCorrente))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelReunionsI>();
			}
		}
		else
			return new ArrayList<modelReunionsI>();
	}
	
	/***
	 * ottiene una lista con tutte le riunioni per questo data
	 * @param dataCorrente
	 * Data del giorno corrente
	 * @return
	 * una lista con tutte le riunioni per oggi
	 */
	 public static List<modelReunionsI>reunionsTodayList(Date dataCorrente){
		 if(modelUsersI.isLogged()){
				DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
				try {
					return query.find().stream()
							.map(e -> new modelReunions(e))
							.filter(e -> e.getDate().equals(dataCorrente))
							.collect(Collectors.toList());
				} catch (SQLException e) {
					return new ArrayList<modelReunionsI>();
				}
		 }else
			 return new ArrayList<modelReunionsI>();
	}
	/***
	 * ottiene una lista con tutte le future riunioni
	 * @param dataCorrente
	 * data odierna
	 * @return
	 * una lista con tutte le riunioni filtrate
	 */
	 public static List<modelReunionsI>futureReunionsList(Date dataCorrente){
		 if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
			try {
				return query.find().stream()
						.map(e -> new modelReunions(e))
						.filter(e -> e.getDate().after(dataCorrente))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelReunionsI>();
			}
		 }
		  else
				return new ArrayList<modelReunionsI>();
	}
	
	/***
	 * ritorna l'elenco con tutte le riunioni nel DB
	 * @return
	 * una lista contenente tutte le riunioni
	 */
	public static List<modelReunionsI> reunionsList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
			try {
				return query.find().stream()
						.map(e -> new modelReunions(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelReunionsI>();
			}
		}
		else
			return new ArrayList<modelReunionsI>();
	}
	/***
	 * creazione di una nuova riunione
	 * @param dataEora
	 * data e ora della riunione
	 * @param nomeRiunione
	 * nome per questa riunione
	 * @param dettagli
	 * dettagli della riunione
	 * @param responsabile
	 * responsabile della riunione
	 * @return
	 * true se andato a buon fine
	 */
	public static boolean builder(java.util.Date dataEora, String nomeRiunione, String dettagli, modelUsersI responsabile){
		if(modelUsersI.isLogged()){
			modelReunionsI temp = new modelReunions();
			temp.setDateAndHour(dataEora);
			temp.setNameReunion(nomeRiunione);
			temp.setResponsible(responsabile);
			temp.setReunionDetails(dettagli);
			return temp.update();
		}
		else
			return false;
	}
}