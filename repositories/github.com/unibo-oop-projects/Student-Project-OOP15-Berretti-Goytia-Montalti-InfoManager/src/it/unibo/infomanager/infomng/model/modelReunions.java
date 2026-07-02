package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan Goytia
 *
 */
class modelReunions implements modelReunionsI{
	
	TableRow oggetto;
	
	//formato per estrarre l'ora in una data
	private DateFormat f= new SimpleDateFormat("HH:mm:ss");
	
	private Integer getIDResponsable(){
		return (Integer)this.oggetto.getObject("IDRisponsabile");
	}
	
	protected modelReunions(TableRow temp){
		this.oggetto = temp;
	}

	public modelReunions(){
		this.oggetto = TableRow.oggettoDaTabella("Riunioni");
	}
	@Override
	public void setNameReunion(String nome){
		this.oggetto.setObjectValue("Riunione", nome);
	}
	@Override
	public void setReunionDetails(String descrizione){
		this.oggetto.setObjectValue("Descrizione", descrizione);
	}
	@Override
	public void setDateAndHour(java.util.Date dataRiunione){
		this.oggetto.setObjectValue("Data", new java.sql.Date(dataRiunione.getTime()));
		this.setTime(f.format(dataRiunione));
	}

	private void setTime(String oraRiunione) {
		this.oggetto.setObjectValue("Ora", oraRiunione);
	}
	
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public String getNameReunion(){
		return (String)this.oggetto.getObject("Riunione");
	}
	@Override
	public String getReferences(){
		return new String(this.getResponsible().getName() +this.getResponsible().getLastName() + this.getResponsible().getMail());
	}
	@Override
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	@Override
	public String getTime(){
		return (String) this.oggetto.getObject("Ora");
	}
	@Override
	public String getReunionDetails(){
		return  (String)this.oggetto.getObject("Descrizione");
	}
	@Override
	public boolean update(){
		return this.oggetto.salva();
	}
	@Override
	public boolean deleteReunion(){
		return this.oggetto.elimina();
	}

	@Override
	public modelUsersI getResponsible() {
		return modelUsersI.usersList().stream()
				.filter(r -> r.getID().equals(this.getIDResponsable()))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void setResponsible(modelUsersI risponsabileRiunione) {
		this.oggetto.setObjectValue("IDResponsabile", risponsabileRiunione.getID());
	}
}
