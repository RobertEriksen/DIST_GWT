package dtu.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.shared.DALException;
import dtu.shared.OperatoerDTO;
import dtu.shared.RaavareBatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;

public class DAO extends RemoteServiceServlet implements KartotekService {
	
	private static final String URL = "jdbc:mysql://localhost/cdio_3";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Newnewold1";

	private Connection connection = null; // manages connection

	private PreparedStatement loginStmt = null;

	private PreparedStatement createOperatorStmt = null;
	private PreparedStatement updateOperatorStmt = null;
	private PreparedStatement getOperatorsStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deleteOperatorStmt = null;
	
	private PreparedStatement createRaavareStmt = null;
	private PreparedStatement updateRaavareStmt = null;
	private PreparedStatement getRaavareStmt = null;
	
	// Recept
	private PreparedStatement getReceptStmt = null;
	private PreparedStatement createReceptStmt = null;
	
	private PreparedStatement getRaavareBatchStmt = null;
	private PreparedStatement createRaavareBatchStmt = null;
	
	public DAO() throws DALException {
		try 
		{
			connection = 
					DriverManager.getConnection( URL, USERNAME, PASSWORD );
			
			// create query that add an operator to kartotek
			createOperatorStmt = 
					connection.prepareStatement( "INSERT INTO operatoer " + 
							"(navn, ini, cpr, pass, active, level) " + 
							"VALUES ( ?, ?, ?, ?, ?, ?)" );

			// create query that updates an operator
			updateOperatorStmt = connection.prepareStatement( 
					"UPDATE operatoer SET navn = ?, ini = ?, cpr = ?, pass = ?, active = ?, level = ? WHERE id = ?" );

			// create query that get all operators in kartotek
			getOperatorsStmt = connection.prepareStatement( 
					"SELECT * FROM operatoer"); 

			// create query that gets size of kartotek
			getSizeStmt = connection.prepareStatement( 
					"SELECT COUNT(*) FROM operatoer");

			// create query that deletes a operator in kartotek
//			deleteOperatorStmt = connection.prepareStatement( 
//					"DELETE FROM operatoer WHERE id =  ?");
			deleteOperatorStmt = connection.prepareStatement( 
					"UPDATE operatoer SET active = 0 WHERE id = ?");
			
			loginStmt = connection.prepareStatement( 
					"SELECT * FROM operatoer WHERE id = ? AND pass = ?");
			
			
			// RÅVARER!!!
			// create query that add an raavare to kartotek
			createRaavareStmt = 
					connection.prepareStatement( "INSERT INTO raavare " + 
							"(raavare_ID, raavare_navn, leverandoer) " + 
							"VALUES ( ?, ?, ?)" );

			// create query that updates an raavare
			updateRaavareStmt = connection.prepareStatement( 
					"UPDATE raavare SET Raavare_navn = ?, leverandoer = ? WHERE raavare_id = ?" );

			// create query that get all raavarer in kartotek
			getRaavareStmt = connection.prepareStatement( 
					"SELECT * FROM raavare"); 
			
			//Create recept Query to make table
			createReceptStmt = connection.prepareStatement( "INSERT INTO recept " + 
					"(recept_Id, raavare_id,recept_name, nonNetto, tolerance) " + 
					"VALUES ( ?, ?, ?, ?, ?)" );
			//Get recept Query
			getReceptStmt = connection.prepareStatement( 
					"SELECT * FROM recept"); 
			
			//RÅVAREBATCHES
			//Create RåvareBatch query
			
			createRaavareBatchStmt = 
					connection.prepareStatement( "INSERT INTO Raavarebatch " + 
							"(Raavare_ID, RbId, maengde) " + 
							"VALUES ( ?, ?, ?)" );
			
			getRaavareBatchStmt = connection.prepareStatement( 
					"SELECT * FROM Raavarebatch"); 
			
			
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException("Kan ikke oprette forbindelse til database!");
		}
	}
	
	
	@Override
	public void deleteOperator(int id) throws DALException {
		try {
			deleteOperatorStmt.setInt(1, id);
			deleteOperatorStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"deleteOperator\" fejlede");
		} 
	}

	@Override
	public List<OperatoerDTO> getOperators() throws DALException {
		List<OperatoerDTO> results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getOperatorsStmt.executeQuery(); 
			results = new ArrayList< OperatoerDTO >();

			while (resultSet.next())
			{
				results.add( new OperatoerDTO(
						resultSet.getInt("id"),
						resultSet.getString("navn"),
						resultSet.getString("ini"),
						resultSet.getString("cpr"),
						resultSet.getString("pass"),
						resultSet.getInt("active"),
						resultSet.getInt("level")));
			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getOperators\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	}

	@Override
	public int getSize() throws DALException {
		try {
			ResultSet rs = null;
			rs = getSizeStmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DALException(" \"getSize\" fejlede");
		} 
	}
	
	@Override
	public void createOperatoer(OperatoerDTO p) throws Exception {
		try {
			createOperatorStmt.setString(1, p.getOprNavn());
			createOperatorStmt.setString(2, p.getIni());
			createOperatorStmt.setString(3, p.getCpr());
			createOperatorStmt.setString(4, p.getPassword());
			createOperatorStmt.setString(5, p.getActive());
			createOperatorStmt.setString(6, p.getLevel());
			createOperatorStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createOperator\" fejlede");
		} 
	}

	@Override
	public void updateOperatoer(OperatoerDTO p) throws Exception {
		try {
			updateOperatorStmt.setString(1, p.getOprNavn());
			updateOperatorStmt.setString(2, p.getIni());
			updateOperatorStmt.setString(3, p.getCpr());
			updateOperatorStmt.setString(4, p.getPassword());
			updateOperatorStmt.setString(5, p.getActive());
			updateOperatorStmt.setString(6, p.getLevel());
			updateOperatorStmt.setInt(7, p.getOprId());
			updateOperatorStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"updateOperator\" fejlede: " + e.getMessage());
		} 
	}
	
	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
	}


	@Override
	public int login(int id, String password) throws Exception {
		ResultSet resultSet = null;
		loginStmt.setInt(1, id);
		loginStmt.setString(2, password);
		try 
		{
			resultSet = loginStmt.executeQuery(); 

			if (resultSet.first()) return resultSet.getInt(7);
			
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"Login\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return 0;
	}


	@Override
	public void createRaavare(RaavareDTO p) throws Exception {
		try {
			createRaavareStmt.setInt(1, p.getRvrId());
			createRaavareStmt.setString(2, p.getRvrNavn());
			createRaavareStmt.setString(3, p.getlvr());
			
			createRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRaavare\" fejlede");
		} 
	}


	@Override
	public void updateRaavare(RaavareDTO p) throws Exception {
		try {
			updateRaavareStmt.setString(1, p.getRvrNavn());
			updateRaavareStmt.setString(2, p.getlvr());
			updateRaavareStmt.setInt(3, p.getRvrId());
			updateRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"updateRaavare\" fejlede: " + e.getMessage());
		} 
	}


	@Override
	public List<RaavareDTO> getRaavarer() throws Exception {
		List<RaavareDTO> results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getRaavareStmt.executeQuery(); 
			results = new ArrayList< RaavareDTO >();

			while (resultSet.next())
			{
				results.add( new RaavareDTO(
						resultSet.getInt("raavare_ID"),
						resultSet.getString("raavare_navn"),
						resultSet.getString("leverandoer")));
			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getRaavarer\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	} 
	
	//CREATE RECEPT
	
	@Override
	public void createRecept(ReceptDTO p) throws Exception {
		try {
			createReceptStmt.setInt(1, p.getRcpId());
			createReceptStmt.setInt(2, p.getRvrId());
			createReceptStmt.setString(3, p.getRcpNavn());
			createReceptStmt.setDouble(4, p.getNomNetto());
			createReceptStmt.setDouble(5, p.getTolerance());
			createReceptStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRecept\" fejlede");
		} 
	}
	
	// Vis RECEPT
	
	@Override
	public List<ReceptDTO> getRecept() throws DALException {
		List<ReceptDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getReceptStmt.executeQuery(); 
			results = new ArrayList< ReceptDTO >();

			while (resultSet.next())
			{
				results.add( new ReceptDTO(
						resultSet.getInt("Recept_id"),
						resultSet.getInt("Raavare_id"),
						resultSet.getString("Recept_name"),
						resultSet.getDouble("NonNetto"),
						resultSet.getDouble("Tolerance")
						));
			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getRecepts\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	}
	
	//RÅVAREBATCH
	//Create tuple
	@Override
	public void createRaavareBatch(RaavareBatchDTO p) throws Exception {
		try {
			createReceptStmt.setInt(1, p.getRbId());
			createReceptStmt.setInt(2, p.getRaavareId());
			createReceptStmt.setDouble(3, p.getMaengde());
			createReceptStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRaavarebatch\" fejlede");
		} 
	}
	
	@Override
	public List<RaavareBatchDTO> getRaavareBatch() throws DALException {
		List<RaavareBatchDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getRaavareBatchStmt.executeQuery(); 
			results = new ArrayList< RaavareBatchDTO >();

			while (resultSet.next())
			{
				results.add(new RaavareBatchDTO(
						resultSet.getInt("rbid"),
						resultSet.getInt("Raavare_id"),
						resultSet.getDouble("maengde")
						));
			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getRaavareBatch\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	}
	
}
