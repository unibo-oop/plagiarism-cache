package magazzino.entratamerci.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import magazzino.entratamerci.controller.StorageController;
import magazzino.entratamerci.dto.ItemsPosition;
import magazzino.entratamerci.models.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.stream.Collectors;

public class GiacenzaService {



    /*
    * Api centrale di giacenza, in cui vengono presi ordini, articoli e locazioni e viene fatto il merge per il retrieve della giacenza puntuale.
    * La giacenza viene mostrata raggruppata per articolo/ area-locazione. Quindi a fronte di più carrelli contenenti lo stesso articolo nella locazione, la quantità viene sommata
    * */
    public ArrayList<giacenza> getGiacenza() {
        ArrayList<ordine> ordini = new OrdiniService().getOrdini();

        ArrayList<ItemsPosition> carrelli =  ordini.stream().flatMap(x-> x.getCarrello().getItems().stream()
                        .map(i-> new ItemsPosition(x.getCarrello().getLocazione(), i.getArticolo(), i.getQuantita())))
                .collect(Collectors.toCollection(ArrayList:: new));

        ArrayList<giacenza> giacenza = new ArrayList<>();
        for (ItemsPosition c: carrelli) {
            String codiceArticolo = c.getArticolo().getCodice();
            String codiceArea = c.getLocazione().getArea().getCodice();
            String codiceLocazione = c.getLocazione().getCodice();

            if(giacenza.stream().filter(x-> x.getArticoloCodice().equals(codiceArticolo) && x.getAreaCodice().equals(codiceArea) && x.getLocazioneCodice().equals(codiceLocazione)).count() == 0){
                giacenza.add(new giacenza(c.getArticolo(),c.getLocazione(), c.getQuantita()));
            }
            else {
                giacenza.stream().filter(x-> x.getArticoloCodice().equals(codiceArticolo) && x.getAreaCodice().equals(codiceArea) && x.getLocazioneCodice().equals(codiceLocazione)).findFirst().get().addQuantita(c.getQuantita());
            }
        }
        return  giacenza;
    }


}
