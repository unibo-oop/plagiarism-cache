package it.rentalmanage.model.filemanager;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IPerson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Created by utente on 26/02/2016.
 */
public interface IFileManager {

    /**
     *
     * @param filename nome del file da andare a leggere
     * @return una stringa con tutto il contenuto del file
     */
    String readFile(File filename);

    /**
     *
     * @param fileName nome del file da andare a cercare
     * @return file corrispondete a quello cercato
     */
    File searchFileFromName(String fileName);



    /**
     *
     * @param list lista da scrivere
     * @param type tipologia della lista da scrivere (ad esempio persone o auto)
     */
    void writeList(JSONArray list, String type);

    /**
     * converte una lista di persone in un json array
     * @param list lista di tutte le persone
     * @return un json array con al suo interno la lista di tutte le persone
     */
    JSONArray writePersonToJArray(Collection<IPerson> list);

    /**
     * convert una lista di auto in un json array
     * @param list lista di tutte le auto
     * @return un json array con al suo interno la lista di tutte le auto
     */
    JSONArray writeCarToJArray(Collection<ICar> list);

    /**
     * converte un json array in una lista di persone
     * @param list un json array con al suo interno i dati di tutte le persone
     * @return una lista di oggetti IPerson
     */
    List<IPerson> fromJArrayToIPersonList(JSONArray list);

    /**
     * converte un json array in una lista di macchine
     * @param list  un json array con al suo interno i dati di tutte le auto
     * @return una lista di oggetti ICar
     */
    List<ICar> fromJArrayToICarList(JSONArray list);

    /**
     * converte un json object in un oggetto di tipo ICar
     * @param car json object con al suo interno i dati di un auto
     * @return un oggetto di tipo ICar
     */
    ICar getCar(JSONObject car);

    /**
     * converte un json object in un oggetto di tipo IPerson
     * @param person json object con al suo interno i dati di una persona
     * @return un oggetto di tipo IPerson
     */
    IPerson getPerson(JSONObject person);


    /**
     * converte un oggetto di tipo IPerson in un json object
     * @param person  oggetto IPerson da convertire
     * @return un json object con al suo interno i dati di una persona
     */
    JSONObject writeIPersonToJSON(IPerson person);

    /**
     * converte un oggetto di tipo ICar in un json object
     * @param car oggetto ICar da convertire
     * @return un json object con al suo interno i dati di una macchina
     */
    JSONObject writeICarToJSON(ICar car);


    /**
     * crea un file vuoto nel persorso in cui vengono memorizzati tutti i dati del programma
     * @param fileName nome del file
     *
     */
    void createEmptyFile(String fileName);

    /**
     * converte una stringa che ha al suo interno i dati letti da un file in un json array
     * @param list stringa con i dati letti da file
     * @return un json array con tutti i dati letti da file
     */
    JSONArray writeToJArray(String list);

    /**
     * crea un file con al suo interno il contenuto di un json array
     * @param pathname persorso in cui andare a creare il file
     * @param thingToWrite json array da scrivere nel file
     */
    void createJArrFile(String pathname,JSONArray thingToWrite);


}
