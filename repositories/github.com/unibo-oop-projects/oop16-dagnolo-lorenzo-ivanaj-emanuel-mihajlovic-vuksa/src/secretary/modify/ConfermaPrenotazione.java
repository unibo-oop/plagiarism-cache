package secretary.modify;

import secretary.DocumentTemplate;
import secretary.VisualizzazioneCamerePrenotate;

public class ConfermaPrenotazione extends DocumentTemplate {

    public ConfermaPrenotazione(String testo) {
        super(testo);
    }

    @Override
    public void nextOperations() {
        new VisualizzazioneCamerePrenotate();
    }
}
