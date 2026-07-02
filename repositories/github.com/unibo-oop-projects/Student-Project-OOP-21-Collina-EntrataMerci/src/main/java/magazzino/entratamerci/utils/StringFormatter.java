package magazzino.entratamerci.utils;

public interface StringFormatter {


    static  String DisplayCodiceDescrizione(String codice, String descrizione){
        return  String.format("[%s] - %s",codice,descrizione);
    }
    static  String DisplayPosizione(String codiceArea, String descrizioneArea, String codiceLocazione, String descrizioneLocazione){
        return  String.format("%s %s",DisplayCodiceDescrizione(codiceArea,descrizioneArea),DisplayCodiceDescrizione(codiceLocazione,descrizioneLocazione));
    }
}
