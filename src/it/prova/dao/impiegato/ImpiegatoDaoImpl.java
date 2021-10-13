package it.prova.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Compagnia;
import it.prova.model.Impiegato;

public class ImpiegatoDaoImpl extends AbstractMySQLDAO implements ImpiegatoDao {

	public ImpiegatoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Impiegato> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato userTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from impiegato")) {

			while (rs.next()) {
				userTemp = new Impiegato();
				userTemp.setNome(rs.getString("NOME"));
				userTemp.setCognome(rs.getString("COGNOME"));
				userTemp.setCodiceFiscale(rs.getString("CODICEFISCALE"));
				userTemp.setDataNascita(rs.getDate("DATADINASCITA"));
				userTemp.setDataAssunzione(rs.getDate("DATAASSUNZIONE"));
				userTemp.setId(rs.getLong("ID"));
				result.add(userTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Impiegato get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Impiegato result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Impiegato();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setCodiceFiscale(rs.getString("CODICEFISCALE"));
					result.setDataNascita(rs.getDate("DATADINASCITA"));
					result.setDataAssunzione(rs.getDate("DATAASSUNZIONE"));
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE impiegato SET nome=?, cognome=?, codiceFiscale=? , dataNascita=?, dataAssunzione where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(4, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			ps.setLong(6, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO impiegato (nome, cogome, codiceFiscale, dataNascita, dataAssunzione) VALUES (?, ?, ?, ? , ?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(4, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			ps.setLong(6, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM impiegato WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Impiegato> findAllByCognome(Compagnia compagnia) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (compagnia == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato userTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato i where i.c like ?")) {
			// quando si fa il setDate serve un tipo java.sql.Date

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					userTemp = new Impiegato();
					userTemp.setNome(rs.getString("NOME"));
					userTemp.setCognome(rs.getString("COGNOME"));
					userTemp.setCodiceFiscale(rs.getString("LOGIN"));
					userTemp.setDataNascita(rs.getDate("PASSWORD"));
					userTemp.setDataAssunzione(rs.getDate("DATECREATED"));
					userTemp.setId(rs.getLong("ID"));
					result.add(userTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Impiegato> coundByDatafonzadioneCompagniaMaggioreDi(Date dataInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(int fatturatoInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllErroriAssunzione(Date dataInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (dataInput == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato userTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato i where i.dataAssunzione <> ?")) {

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					userTemp = new Impiegato();
					userTemp.setNome(rs.getString("NOME"));
					userTemp.setCognome(rs.getString("COGNOME"));
					userTemp.setCodiceFiscale(rs.getString("LOGIN"));
					userTemp.setDataNascita(rs.getDate("PASSWORD"));
					userTemp.setDataAssunzione(rs.getDate("DATECREATED"));
					userTemp.setId(rs.getLong("ID"));
					result.add(userTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
