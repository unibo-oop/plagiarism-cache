package magazzino.entratamerci.utils;

public interface ComboBoxUtility {
    static  String DisplayCodiceDescrizione(String codice, String descrizione){
        return  String.format("%s - %s",codice,descrizione);
    }
    static String GetKey(String cmbValue){
        return cmbValue.split("-")[0].toString().trim();
    }
}
