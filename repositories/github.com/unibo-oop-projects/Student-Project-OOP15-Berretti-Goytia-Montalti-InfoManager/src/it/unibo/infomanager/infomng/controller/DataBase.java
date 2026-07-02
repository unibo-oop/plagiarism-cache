package it.unibo.infomanager.infomng.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.model.modelProvidersI;

class DataBase {
	private boolean connesso;
	private Connection c;
	private String percorso;
	
	public DataBase(String percorso) {
		this.percorso = percorso;
	}
	
	public DataBase(){
		this(System.getProperty("user.home") + File.separator + "info-mng.db");
	}
	
	public boolean connetti() throws SQLException{
		if(this.connesso){
			return true;
		}
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ this.percorso);
			this.connesso = true;
			return true;
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void disconnetti() throws SQLException{
		if(this.connesso){
			this.c.close();
		}
	}

	private Optional<Set<String>> colonneDaTabella(String nomeTabella) throws SQLException{
		String sql = String.format("pragma table_info(%s)", nomeTabella);
		List<String> colonne = this.eseguiLettura(sql).stream()
				.map(d ->{
					return (String)d.get("name");
				}).collect(Collectors.toList());
		return Optional.ofNullable(new HashSet<>(colonne));
	}
	
	private List<String> nomiColonneDalRisultato(ResultSet set) throws SQLException{
		ResultSetMetaData meta = set.getMetaData();
		Integer numeroColonne = meta.getColumnCount();
		List<String> ritorno = new ArrayList<>();
		for(int i = 1; i <= numeroColonne; i++){
			ritorno.add(meta.getColumnName(i));
		}
		return ritorno;
	}
	
	public List<Map<String, Object>> eseguiLettura(String query) throws SQLException {
		Statement stmt = this.c.createStatement();
		ResultSet result = stmt.executeQuery(query);
		List<String> nomiColonne = this.nomiColonneDalRisultato(result);
		
		List<Map<String, Object>> ritorno = new ArrayList<>();
		
		while(result.next()){
			Map<String, Object> item = new HashMap<>();
			for(String colonna : nomiColonne){
				item.put(colonna, result.getObject(colonna));
			}
			ritorno.add(item);
		}
		
		result.close();
		stmt.close();
		return ritorno;
	}

	public void eseguiAggiornamento(String query) throws SQLException {
		Statement stmt = this.c.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	protected Set<String> creaTabella(Oggetto oggetto) throws SQLException{
		//creo la tabella se non esiste
		String sql = String.format("CREATE TABLE IF NOT EXISTS %s (objectId INTEGER PRIMARY KEY AUTOINCREMENT, Creazione DATE, Modifica DATE)", oggetto.nomeTabella);
		this.eseguiAggiornamento(sql);
		
		//Leggo la tabella per ottenere i nomi delle colonne
		sql = String.format("SELECT * FROM %s", oggetto.nomeTabella);
		Optional<Set<String>> ritorno = this.colonneDaTabella(oggetto.nomeTabella);
		if(ritorno.isPresent()){
			return ritorno.get();
		}
		else{
			Set<String> rit = new HashSet<>();
			rit.add("objectId");
			rit.add("Creazione");
			rit.add("Modifica");
			return rit;
		}
	}

	protected Integer inserisciOggetto(Oggetto oggetto) throws IllegalArgumentException, SQLException{
		this.aggiornaTabella(oggetto);
		
		String sql = String.format("INSERT INTO %s (", oggetto.nomeTabella);
		String[] campi = oggetto.dati.keySet().toArray(new String[oggetto.dati.size()]);
		for(int i = 0; i < campi.length - 1; i++){
			sql = String.format("%s %s,", sql, campi[i]);
		}
		if(campi.length > 0){
			sql = String.format("%s %s) VALUES (", sql, campi[campi.length - 1]);
		}
		for(int i = 0; i < campi.length - 1; i++){
			String valore = oggetto.dati.get(campi[i]) == null ? "NULL" : oggetto.dati.get(campi[i]).toString();
			sql = String.format("%s '%s',", sql, valore);
		}
		if(campi.length > 0){
			String valore = oggetto.dati.get(campi[campi.length - 1]) == null ? "NULL" : oggetto.dati.get(campi[campi.length - 1]).toString();
			sql = String.format("%s '%s')", sql, valore);
		}
		
		this.eseguiAggiornamento(sql);
		OptionalInt ris = this.eseguiLettura(String.format("SELECT objectId FROM %s", oggetto.nomeTabella)).stream()
				.mapToInt(d -> (Integer)d.get("objectId"))
				.max();
		if(ris.isPresent()){
			return ris.getAsInt();
		}
		else{
			return -1;
		}
	}
	
	protected void aggiornaRecord(Oggetto oggetto) throws IllegalArgumentException, SQLException{
		this.aggiornaTabella(oggetto);
		
		String sql = String.format("UPDATE %s SET ", oggetto.nomeTabella);
		String[] campi = oggetto.dati.keySet().stream()
				.filter(s -> {
					return !s.equals("objectId");
				})
				.collect(Collectors.toList()).toArray(new String[oggetto.dati.size() - 1]);
		
		for(int i = 0; i < campi.length - 1; i++){
			Object valore = oggetto.dati.get(campi[i]);
			if(valore == null){
				valore = "NULL";
			}
			if(valore.getClass() == modelProvidersI.class){
				sql = String.format("%s %s = '%s',", sql, campi[i], ((modelProvidersI)valore).getIDFornitore());
			}
			else{
				sql = String.format("%s %s = '%s',", sql, campi[i], valore.toString());
			}
		}
		if(campi.length > 0){
			Object valore = oggetto.dati.get(campi[campi.length - 1]);
			if(valore == null){
				valore = "NULL";
			}
			if(valore.getClass() == modelProvidersI.class){
				sql = String.format("%s %s = '%s'", sql, campi[campi.length - 1], ((modelProvidersI)valore).getIDFornitore());
			}
			else{
				sql = String.format("%s %s = '%s'", sql, campi[campi.length - 1], valore.toString());
			}
		}
		sql = String.format("%s WHERE objectId = %s", sql, oggetto.objectId().toString());
		
		this.eseguiAggiornamento(sql);
	}

	protected void eliminaRecord(Oggetto oggetto) throws SQLException{
		String sql = String.format("DELETE FROM %s WHERE objectId = %s", oggetto.nomeTabella, oggetto.objectId().toString());
		this.eseguiAggiornamento(sql);
	}
	
	private void aggiornaTabella(Oggetto oggetto) throws IllegalArgumentException, SQLException{
		Set<String> campiEsistenti = this.colonneDaTabella(oggetto.nomeTabella).get();
		Set<String> campiAggiungere = oggetto.dati.keySet().stream()
				.filter(c -> {
					return !campiEsistenti.contains(c);
				})
				.collect(Collectors.toSet());
		System.out.println("Campi esistenti");
		campiEsistenti.stream().forEach(System.out::println);
		System.out.println("\nCampi da aggiungere");
		
		campiAggiungere.stream().forEach(System.out::println);
		System.out.println("\n");
		for(String c : campiAggiungere){
			Object valore = oggetto.dati.get(c);
			this.aggiungiColonna(oggetto.nomeTabella, c, valore);
			oggetto.campi.add(c);
		}
		
	}
	
	private void aggiungiColonna(String tabella, String colonna, Object class1) throws IllegalArgumentException, SQLException{
		String tipo;
		if(class1 == null){
			tipo = "";
		}
		else if(class1.getClass() == Integer.class){
			tipo = "INTEGER";
		}
		else if(class1.getClass() == String.class){
			tipo = "TEXT";
		}
		else if(class1.getClass() == Double.class || class1.getClass() == Float.class){
			tipo = "DECIMAL";
		}
		else if(class1.getClass() == Date.class){
			tipo = "DATE";
		}
		else if(class1.getClass() == Boolean.class){
			tipo = "BOOLEAN";
		}
		else if(class1.getClass() == modelProvidersI.class){
			tipo = "INTEGER";
		}
		else{
			tipo = "NULL";
			throw new IllegalArgumentException();
		}
		
		String sql = String.format("ALTER TABLE %s ADD %s %s", tabella, colonna, tipo);
		this.eseguiAggiornamento(sql);
	}
}
