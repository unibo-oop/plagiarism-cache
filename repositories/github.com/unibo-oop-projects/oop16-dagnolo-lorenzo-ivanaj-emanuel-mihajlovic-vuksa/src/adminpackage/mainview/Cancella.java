package adminpackage.mainview;

import java.awt.event.ActionListener;

import adminpackage.account.CancellaAccount;
import adminpackage.boardtype.CancellaPensione;
import adminpackage.floor.RimuoviPiano;
import adminpackage.person.CancellaPersona;
import adminpackage.roomextra.CancellaSupplementoExtra;
import adminpackage.rooms.CancellaCamera;
import adminpackage.roomtype.CancellaTipoCamera;
import adminpackage.season.CancellaStagione;
import adminpackage.stayextra.CancellaSupplementoSoggiorno;
import adminpackage.template.Operations;

/**
 * 
 * emanu.
 *
 */
public class Cancella extends Operations {

    @Override
    public ActionListener camera() {

        return (l) -> {
            new CancellaCamera();
            this.shut();
        };
    }

    @Override
    public ActionListener piano() {
        return (l) -> {
            new RimuoviPiano();
            this.shut();
        };
    }

    @Override
    public ActionListener account() {
        return (l) -> {
            new CancellaAccount("Scegli account da cancellare", "Cancella account");
            this.shut();
        };
    }

    @Override
    public ActionListener supplementoSoggiorno() {

        return (l) -> {
            new CancellaSupplementoSoggiorno("Scegli supplemento da cancellare", "Cancellazione supplemento");
            this.shut();
        };
    }

    @Override
    public ActionListener supplementoCamera() {
        return (l) -> {
            new CancellaSupplementoExtra("Scegli extra di una camera da cancellare", "Cancella extra");
            this.shut();
        };
    }

    @Override
    public ActionListener tipoCamera() {
        return (l) -> {
            new CancellaTipoCamera("Scegli tipo camera da cancellare", "Cancella tipo camera");
            this.shut();
        };
    }

    @Override
    public ActionListener tipoPensione() {
        return (l) -> {
            new CancellaPensione("Seleziona tipo di pensione da cancellare", "Cancella pensione");
            this.shut();
        };
    }

    @Override
    public ActionListener stagione() {
        return (l) -> {
            new CancellaStagione("Scegli tipo di stagione da cancellare", "Cancella stagione");
            this.shut();
        };
    }

    @Override
    public ActionListener persone() {
        return (l) -> {
            new CancellaPersona("Scegli persona da cancellare", "Tipo persona da cancellare");
            this.shut();
        };
    }

    @Override
    public ActionListener esci() {
        return (l) -> {
            new Scelte();
            this.shut();
        };
    }
}
