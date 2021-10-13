package it.prova.dao.compagnia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Compagnia;

public class CompagniaDaoImpl extends AbstractMySQLDAO implements CompagniaDao {

	public CompagniaDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Compagnia> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia userTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from compagnia")) {

			while (rs.next()) {
				userTemp = new Compagnia();
				userTemp.setRagioneSociale(rs.getString("RAGIONESOCIALE"));
				userTemp.setFatturatoAnnuo(rs.getInt("FATTURATOANNUO"));
				userTemp.setDataFondazione(rs.getDate("DATAFONDAZIONE"));
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
	public Compagnia get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Compagnia();
					result.setRagioneSociale(rs.getString("RAGIONESOCIALE"));
					result.setFatturatoAnnuo(rs.getInt("FATTURATOANNUO"));
					result.setDataFondazione(rs.getDate("DATAFONDAZIONE"));
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
	public int update(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET ragioneSociale=?, fatturatoAnnuo=?, dataFondazione=? where id=?;")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setDouble(2, input.getFatturatoAnnuo());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			ps.setLong(4, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO compagnia (ragioneSociale, fatturatoAnnuo, dataFondazione) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setDouble(2, input.getFatturatoAnnuo());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dateCreatedInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (dateCreatedInput == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia userTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia c inner join impiegato i on c.id = i.id where i.dataAssunzione < ?")) {
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(1, new java.sql.Date(dateCreatedInput.getTime()));

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					userTemp = new Compagnia();
					userTemp.setRagioneSociale(rs.getString("RAGIONESOCIALE"));
					userTemp.setFatturatoAnnuo(rs.getInt("FATTURATOANNUO"));
					userTemp.setDataFondazione(rs.getDate("DATAFONDAZIONE"));
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
	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSocialeInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
				if (isNotActive())
					throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

				if (ragioneSocialeInput == null)
					throw new Exception("Valore di input non ammesso.");

				ArrayList<Compagnia> result = new ArrayList<Compagnia>();
				Compagnia userTemp = null;

				try (PreparedStatement ps = connection.prepareStatement("select * from compagnia c where c.ragioneSociale like ?;")) {
					// quando si fa il setDate serve un tipo java.sql.Date
					ps.setString(1, ragioneSocialeInput + "%");

					try (ResultSet rs = ps.executeQuery();) {
						while (rs.next()) {
							userTemp = new Compagnia();
							userTemp.setRagioneSociale(rs.getString("RAGIONESOCIALE"));
							userTemp.setFatturatoAnnuo(rs.getInt("FATTURATOANNUO"));
							userTemp.setDataFondazione(rs.getDate("DATAFONDAZIONE"));
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
