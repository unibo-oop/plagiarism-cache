package adminpackage.mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adminpackage.account.NewAccount;
import adminpackage.boardtype.CreaPensione;
import adminpackage.floor.CreaPiano;
import adminpackage.person.CreazionePersone;
import adminpackage.roomextra.CreaSupplementoCamera;
import adminpackage.rooms.CreaCamera;
import adminpackage.roomtype.CreazioneTipoCamera;
import adminpackage.season.CreaStagione;
import adminpackage.stayextra.CreaSupplementoSoggiorno;
import adminpackage.template.Operations;
import secretary.LogIn;

public class Crea extends Operations {

    public static void main(final String[] args) {
        new Crea();
    }

    @Override
    public ActionListener camera() {
        return (l) -> {
            new CreaCamera();
            this.shut();
        };
    }

    @Override
    public ActionListener piano() {
        return (l) -> {
            new CreaPiano();
            this.shut();
        };
    }

    @Override
    public ActionListener account() {
        return (l) -> {
            new NewAccount();
            this.shut();
        };
    }

    @Override
    public ActionListener supplementoSoggiorno() {
        return (l) -> {
            new CreaSupplementoSoggiorno();
            this.shut();
        };
    }

    @Override
    public ActionListener supplementoCamera() {
        return (l) -> {
            new CreaSupplementoCamera();
            this.shut();
        };
    }

    @Override
    public ActionListener tipoCamera() {
        return (l) -> {
            new CreazioneTipoCamera();
            this.shut();
        };
    }

    @Override
    public ActionListener tipoPensione() {
        return (l) -> {
            new CreaPensione();
            this.shut();
        };
    }

    @Override
    public ActionListener persone() {
        return (l) -> {
            new CreazionePersone();
            this.shut();
        };
    }

    @Override
    public ActionListener stagione() {
        return (l) -> {
            new CreaStagione();
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
