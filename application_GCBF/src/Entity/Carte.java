package Entity;

import java.time.LocalDate;

public sealed abstract class Carte
        permits CarteDebit, CarteCredit, CartePrepayee {

    protected String id;
    protected String numero;
    protected LocalDate dateExpiration;
    protected StatutCarte statut;
    protected String idClient;

    public Carte(String id, String numero, LocalDate dateExpiration, StatutCarte statut, String idClient) {
        this.id = id;
        this.numero = numero;
        this.dateExpiration = dateExpiration;
        this.statut = statut;
        this.idClient = idClient;
    }

    public String id() { return id; }
    public String numero() { return numero; }
    public LocalDate dateExpiration() { return dateExpiration; }
    public StatutCarte statut() { return statut; }
    public String idClient() { return idClient; }

    public void setStatut(StatutCarte statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                "id='" + id + '\'' +
                ", numero='" + numero + '\'' +
                ", dateExpiration=" + dateExpiration +
                ", statut=" + statut +
                ", idClient='" + idClient + '\'' +
                " }";
    }
}
