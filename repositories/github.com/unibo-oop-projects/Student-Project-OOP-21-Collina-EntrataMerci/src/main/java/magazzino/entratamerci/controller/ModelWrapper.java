package magazzino.entratamerci.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import magazzino.entratamerci.dto.*;
import magazzino.entratamerci.models.*;
import magazzino.entratamerci.service.*;

public interface ModelWrapper {
	public static ArrayList<ArticoloModel> readArticoli() {
		ArrayList<articolo> articoli = new ArticoliService().getArticoli();
		return  articoli.stream().map(x-> new ArticoloModel(x.getCodice(),x.getDescrizione(),x.isObsoleto(),x.getNote())).collect(Collectors.toCollection(ArrayList::new));
	}

	public static ArrayList<FornitoriModel> readFornitori() {
		ArrayList<fornitore> fornitori = new FornitoriService().getFornitori();
		return fornitori.stream().map(x-> new FornitoriModel(x.getCodice(),x.getDescrizione(),x.getNome(),x.getCognome())).collect(Collectors.toCollection(ArrayList::new));
	}

	public static ArrayList<AreaModel> readAree() {
		ArrayList<area> aree = new AreeService().getAree();
		return aree.stream().map(x-> new AreaModel(x.getCodice(),x.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
	}

	public static ArrayList<LocazioneModel> readLocazioni() {
		ArrayList<LocazioneModel> arrModel = new ArrayList<LocazioneModel>();
		ArrayList<locazione> arr = new LocazioniService().getLocazioni();
		for (locazione locazione : arr) {
			arrModel.add(new LocazioneModel(locazione.getCodice(), locazione.getDescrizione()));
		}
		return arrModel;
	}

	public static ArrayList<OrdineModel> readOrdini(){
		ArrayList<ordine> ordini = new OrdiniService().getOrdini();

		return ordini.stream().map(x-> new OrdineModel(x.getAnnoNumero(),
						x.getData().toString(),
						x.getFornitore().getCodiceDescrizione(),
						x.getFornitore().getNomeCognome(),
						x.getNumeroPezzi()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public static ArrayList<GiacenzaModel> readGiacenza() {
		ArrayList<giacenza> giacenza = new GiacenzaService().getGiacenza();
		return  giacenza.stream().map(x-> new GiacenzaModel(x.getArticoloFormatted(),x.getPosizioneFormatted(),x.getQuantita())).collect(Collectors.toCollection(ArrayList::new));
	}
}
