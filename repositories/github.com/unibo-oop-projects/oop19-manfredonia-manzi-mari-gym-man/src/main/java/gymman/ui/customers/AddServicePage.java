package gymman.ui.customers;

import gymman.ui.AbstractPage;

// TODO: Auto-generated Javadoc
/**
 * The Class AddServicePage.
 */
public class AddServicePage extends AbstractPage {

    /**
     * Instantiates a new adds the service page.
     */
    public AddServicePage() {
        super(AddServicePage.class.getResource("creazioneServizioAggiuntivo.fxml"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return "page_additionalService_creation";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return "Crea un servizio aggiuntivo";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasMenuEntry() {
        return false;
    }
}
