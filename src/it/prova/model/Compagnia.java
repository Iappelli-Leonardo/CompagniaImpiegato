package it.prova.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compagnia {

	private Long id;
	private String ragioneSociale;
	private int fatturatoAnnuo;
	private Date dataFondazione;
	private List<Impiegato> impiegato = new ArrayList<>();

	public Compagnia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compagnia(String ragioneSociale, int fatturatoAnnuo, Date dataFondazione, List<Impiegato> impiegato) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazione = dataFondazione;
		this.impiegato = impiegato;
	}

	public Compagnia(String ragioneSociale, int fatturatoAnnuo) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public double getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(int fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public List<Impiegato> getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(List<Impiegato> impiegato) {
		this.impiegato = impiegato;
	}

	@Override
	public String toString() {
		return "Compagnia [id=" + id + ", ragioneSociale=" + ragioneSociale + ", fatturatoAnnuo=" + fatturatoAnnuo
				+ ", dataFondazione=" + dataFondazione + ", impiegato=" + impiegato + "]";
	}

}
