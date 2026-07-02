package magazzino.entratamerci.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrdineModel {
    private SimpleStringProperty codice;
    private SimpleStringProperty data;

    private SimpleStringProperty nome;
    private SimpleStringProperty fornitore;
    private SimpleIntegerProperty numeroPezzi;

    public OrdineModel(SimpleStringProperty codice, SimpleStringProperty data, SimpleStringProperty fornitore, SimpleIntegerProperty numeroPezzi) {
        this.codice = codice;
        this.data = data;
        this.fornitore = fornitore;
        this.numeroPezzi = numeroPezzi;
    }

    public OrdineModel(String codice, String data, String fornitore, String nome, int numeroPezzi) {
        this.codice = new SimpleStringProperty(codice);
        this.data = new SimpleStringProperty(data);
        this.fornitore = new SimpleStringProperty(fornitore);
        this.nome = new SimpleStringProperty(nome);
        this.numeroPezzi =  new SimpleIntegerProperty(numeroPezzi);
    }

    public String getCodice() {
        return codice.get();
    }

    public SimpleStringProperty codiceProperty() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice.set(codice);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public String getFornitore() {
        return fornitore.get();
    }

    public SimpleStringProperty fornitoreProperty() {
        return fornitore;
    }

    public void setFornitore(String fornitore) {
        this.fornitore.set(fornitore);
    }

    public int getNumeroPezzi() {
        return numeroPezzi.get();
    }

    public SimpleIntegerProperty numeroPezziProperty() {
        return numeroPezzi;
    }
    public void setNumeroPezzi(int numeroPezzi) {
        this.numeroPezzi.set(numeroPezzi);
    }
    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }


}
