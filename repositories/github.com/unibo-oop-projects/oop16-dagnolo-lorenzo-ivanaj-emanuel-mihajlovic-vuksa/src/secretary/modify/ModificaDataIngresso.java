package secretary.modify;

import secretary.DocumentTemplate;

/**
 * 
 * emanu.
 *
 */
public class ModificaDataIngresso extends DocumentTemplate {
    /**
     * 
     * @param testo
     */
    public ModificaDataIngresso(final String testo) {
        super(testo);
    }

    @Override
    public void nextOperations() {
        new DataIngresso();

    }
}
