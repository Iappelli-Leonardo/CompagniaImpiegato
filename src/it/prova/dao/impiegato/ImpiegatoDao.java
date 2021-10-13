package it.prova.dao.impiegato;

import java.util.Date;
import java.util.List;

import it.prova.dao.IBaseDAO;
import it.prova.model.Compagnia;
import it.prova.model.Impiegato;


public interface ImpiegatoDao extends IBaseDAO<Impiegato>{

	public List<Impiegato> findAllByCognome(Compagnia compagnia) throws Exception;

	public List<Impiegato> coundByDatafonzadioneCompagniaMaggioreDi(Date dataInput) throws Exception;
	
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception;
	
	public List<Impiegato> findAllErroriAssunzione(Date dataInput) throws Exception;
	

	
}
