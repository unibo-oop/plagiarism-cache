package sharelist.controller;

import sharelist.view.application.ApplicationView;
import sharelist.view.application.ApplicationViewInterface;
import sharelist.view.login.LoginViewInterface;
import sharelist.model.*;

/**
 * Intercetta gli eventi della View, commanda le modifiche al Modello, cambia la View
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class Controller implements ViewObserver{
	
	private LoginViewInterface view;
	private ApplicationViewInterface applicationView;
	private ModelInterface model;
	private String username;
	private String password;
	
	/**
	 * Costruttore del Controllore
	 */
	public Controller(){
	}

	/**
	 * Setta la View al Controllore
	 * 
	 * @param view
	 * 				View
	 */
	public void setView(LoginViewInterface view) {
		this.view = view;
		this.view.attachViewObserver(this);
	}

	/**
	 * Setta il Modello al Controllore
	 * 
	 * @param model
	 * 				Modello
	 */
	public void setModel(ModelInterface model) {
		this.model = model;
	}
	
	/**
	 * Accede all'account utente logato correttamente
	 */
	@Override
	public void accessApplication(){
		this.applicationView = new ApplicationView("Utente: "+this.username);
		this.applicationView.attachViewObserver(this);
		this.commandLoadList(this.username, this.password);
		this.commandLoadNotification();
	}
	
	/**
	 * Effettua il login utente
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 */
	@Override
	public void commandLogin(String username, String password){
		this.username = username;
		this.password = password;
		try{
			/* Chiedo al Modello di effetuare il login */
			Object[] access = this.model.login(username, password);
			Integer success = (Integer)access[0];
			if(success==1){
				/* Login effettuato con successo */
				this.view.loginResponse();
				accessApplication();
			} else {
				/* Accesso fallito */
				this.view.commandFailed(new String((String)access[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua la registrazione utente
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: name, surname, username, password
	 */
	@Override
	public void commandRegister(Object[] o){
		try{
			/* Chiedo al Modello di effetuare una nuova registrazione utente */
			Object[] register =  this.model.register((String)o[0], (String)o[1], (String)o[2], (String)o[3]);
			/* Chiedo alla View di visualizzare l'esito della registrazione */
			this.view.commandRegisterOk(new String((String)register[1]));
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua il caricamento delle liste utente logato
	 */
	@Override
	public void commandLoadList(){
		this.commandLoadList(this.username, this.password);
	}
	
	/**
	 * Effettua il caricamento delle liste utente
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 */
	@Override
	public void commandLoadList(String username, String password){
		try{
			/* Chiedo al Modello di caricare le liste */
			Object[][] list = this.model.loadList(username, password);
			Integer success = (Integer)list[0][0];
			if(success > 0){
				/* Caricamento liste effettuato con successo */
				this.commandLoadNotification();
				this.applicationView.loadList(list);
			} else {
				/* Caricamento liste fallito */
				this.applicationView.commandFailed(new String((String)list[0][1]));
			}
		} catch (Exception e){
			this.applicationView.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua l'aggiunta di una nuova lista
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: nome lista
	 */
	@Override
	public void commandAddList(Object[] o){
		try{
			/* Chiedo al Modello di aggiungere una nuova liste */
			Object[] addList =  this.model.addList(this.username, this.password, (String)o[0]);
			Integer success = (Integer)addList[0];
			if(success > 0){
				/* Aggiunta lista effettuato con successo, quindi ricarico elenco liste */
				this.commandLoadList(this.username, this.password);
			} else {
				/* Aggiunta lista fallita */
				this.view.commandFailed(new String((String)addList[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua la modifica di una nuova lista
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: id lista, nome lista
	 */
	@Override
	public void commandEditList(Object[] o){
		try{
			/* Chiedo al Modello di modificare una lista */
			Object[] addList =  this.model.editList(this.username, this.password, (String)o[0], (String)o[1]);
			Integer success = (Integer)addList[0];
			if(success > 0){
				/* Modifica lista effettuato con successo, quindi ricarico elenco liste */
				this.commandLoadList(this.username, this.password);
			} else {
				/* Modifica lista fallita */
				this.view.commandFailed(new String((String)addList[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua la cancellazione di una lista
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: id lista
	 */
	@Override
	public void commandDeleteList(Object[] o){
		try{
			/* Chiedo al Modello di cancellare una lista */
			Object[] deleteList =  this.model.deleteList(this.username, this.password, (String)o[0]);
			Integer success = (Integer)deleteList[0];
			if(success > 0){
				/* Cancellazione lista effettuato con successo, quindi ricarico elenco liste */
				this.commandLoadList(this.username, this.password);
			} else {
				/* Cancellazione lista fallita */
				this.view.commandFailed(new String((String)deleteList[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua il caricamento delle attivita' utente logato
	 * 
	 * @param idList
	 * 				id lista
	 */
	@Override
	public void commandLoadAttivitaFromView(String idList){
		this.commandLoadAttivita(this.username, this.password, idList);
	}
	
	/**
	 * Effettua il caricamento delle attivita'
	 * 
	 * @param username
	 * 				username utente
	 * @param password
	 * 				password utente
	 * @param idList
	 * 				id lista
	 */
	@Override
	public void commandLoadAttivita(String username, String password, String idList){
		try{
			/* Chiedo al Modello di caricare le attivita' */
			Object[][] attivita = this.model.loadAttivita(username, password, idList);
			Integer success = (Integer)attivita[0][0];
			if(success > 0){
				/* Caricamento attivita' effettuato con successo */
				this.applicationView.loadAttivita(attivita);
			} else {
				/* Caricamento attivita' fallito */
				this.applicationView.commandFailed(new String((String)attivita[0][1]));
			}
		} catch (Exception e){
			this.applicationView.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua l'aggiunta di un'attivita'
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: id lista, nome attivita'
	 */
	@Override
	public void commandAddAttivita(Object[] o){
		try{
			/* Chiedo al Modello di aggiungere un'attivita' */
			Object[] addAttivita =  this.model.addAttivita(this.username, this.password, (String)o[0], (String)o[1]);
			Integer success = (Integer)addAttivita[0];
			if(success > 0){
				/* Aggiunta attivita' effettuato con successo, quindi ricarico elenco attivita' */
				this.commandLoadAttivita(this.username, this.password, (String)o[0]);
			} else {
				/* Aggiunta attivita' fallita */
				this.view.commandFailed(new String((String)addAttivita[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua la modifica di un'attivita'
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: id lista, id attivita', nome attivita', completo, note
	 */
	@Override
	public void commandEditAttivita(Object[] o){
		try{
			/* Chiedo al Modello di modificare un'attivita' */
			Object[] addList =  this.model.editAttivita(this.username, this.password, (String)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4]);
			Integer success = (Integer)addList[0];
			if(success > 0){
				/* Modifica attivita' effettuato con successo, quindi ricarico elenco attivita' */
				this.commandLoadAttivita(this.username, this.password, (String)o[0]);
			} else {
				/* Modifica attivita' fallita */
				this.view.commandFailed(new String((String)addList[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua l'eleminazione di un'attivita'
	 * 
	 * @param o
	 * 				Array di tipo Object che contiene: id lista, id attivita'
	 */
	@Override
	public void commandDeleteAttivita(Object[] o){
		try{
			/* Chiedo al Modello di eliminare un'attivita' */
			Object[] deleteAttivita =  this.model.deleteAttivita(this.username, this.password, (String)o[0], (String)o[1]);
			Integer success = (Integer)deleteAttivita[0];
			if(success > 0){
				/* Eliminazione attivita' effettuato con successo, quindi ricarico elenco attivita' */
				this.commandLoadAttivita(this.username, this.password, (String)o[0]);
			} else {
				/* Eliminazione attivita' fallita */
				this.view.commandFailed(new String((String)deleteAttivita[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Carica i membri di una lista
	 * 
	 * @param idList
	 * 				id lista
	 */
	@Override
	public void commandLoadMember(String idList){
		try{
			/* Chiedo al Modello di caricare i membri di una lista */
			Object[][] member = this.model.loadMember(this.username, this.password, idList);
			Integer success = (Integer)member[0][0];
			if(success > 0){
				/* Caricamento membri effettuato con successo */
				this.applicationView.loadMember(member);
			} else {
				/* Caricamento membri fallito */
				this.applicationView.commandFailed(new String((String)member[0][1]));
			}	
		} catch (Exception e){
			this.applicationView.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Aggiunge un membro ad una lista
	 * 
	 * @param username_new_person
	 * 				username nuovo membro
	 * @param id_list
	 * 				id lista
	 * @param list
	 * 				nome lista
	 */
	@Override
	public void commandAggiungiMember(String username_new_person, String id_list, String list){
		try{
			/* Chiedo al Modello di aggiungere un membro ad una lista */
			Object[] addMember =  this.model.addMember(this.username, this.password, username_new_person, id_list, list);
			Integer success = (Integer)addMember[0];
			if(success > 0){
				/* Aggiunta membro effettuato con successo, quindi ricarico elenco membri */
				this.commandLoadMember(id_list);
			} else {
				/* Aggiunta membro fallito */
				this.view.commandFailed(new String((String)addMember[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Aggiunge un membro ad una lista
	 * 
	 * @param id
	 * 				id membro
	 * @param id_list
	 * 				id lista
	 */
	@Override
	public void commandDeleteMember(String id, String id_list){
		try{
			/* Chiedo al Modello di eliminare un membro da una lista */
			Object[] deleteMember =  this.model.deleteMember(this.username, this.password, id, id_list);
			Integer success = (Integer)deleteMember[0];
			if(success > 0){
				/* Eliminazione membro effettuato con successo, quindi ricarico elenco membri */
				this.commandLoadMember(id_list);
			} else {
				/* Eliminazione membro fallito */
				this.view.commandFailed(new String((String)deleteMember[1]));
			}
		} catch (Exception e){
			this.view.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Effettua il caricamento delle notifiche.
	 */
	@Override
	public void commandLoadNotification(){
		try{
			/* Chiedo al Modello di caricare le notifiche */
			Object[][] notification = this.model.loadNotification(this.username, this.password);
			Integer success = (Integer)notification[0][0];
			if(success > 0){
				/* Caricamento notifiche effettuato con successo */
				this.applicationView.loadNotification(notification);
			} else {
				/* Caricamento notifiche fallito */
				this.applicationView.commandFailed(new String((String)notification[0][1]));
			}
		} catch (Exception e){
			//this.applicationView.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Invia notifica al server che l'utente ha accettato a partecipare ad una lista.
	 * 
	 * @param id
	 * 				id notifica
	 * @param id_list
	 * 				id lista
	 */
	@Override
	public void commandAccettaListaNotification(String id, String id_list){
		try{
			/* Chiedo al Modello di inviare notifica al server che l'utente ha accettato a partecipare ad una lista */
			Object[] notification = this.model.acceptListNotification(this.username, this.password, id, id_list);
			Integer success = (Integer)notification[0];
			if(success > 0){
				/* Invio notifica al server che l'utente ha accettato a partecipare ad una lista effettuato con successo,
				 * quindi ricarico notifiche e ricarico liste */
				this.commandLoadNotification();
				this.commandLoadList();
			} else {
				/* Invio notifica al server che l'utente ha accettato a partecipare ad una lista fallito */
				this.applicationView.commandFailed(new String((String)notification[1]));
			}
		} catch (Exception e){
			this.applicationView.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Invia notifica al server che l'utente ha letto le notifiche.
	 */
	@Override
	public void commandReadNotification(){
		try{
			/* Chiedo al Modello di inviare notifica al server che l'utente ha letto le notifiche */
			Object[] notification = this.model.readNotification(this.username, this.password);
			Integer success = (Integer)notification[0];
			if(success > 0){
				/* Invio notifica al server che l'utente ha letto le notifiche effettuato con successo,
				 * quindi ricarico notifiche */
				this.commandLoadNotification();
			} else {
				/* Invio notifica al server che l'utente ha letto le notifiche fallito */
				this.applicationView.commandFailed(new String((String)notification[1]));
			}
		} catch (Exception e){
			this.applicationView.commandFailed(e.getMessage());
		}
	}
	
	/**
	 * Uscita dall'applicazione
	 */
	@Override
	public void commandQuit(){
		System.exit(0);
	}
	
}
