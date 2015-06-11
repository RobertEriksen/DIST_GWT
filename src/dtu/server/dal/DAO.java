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
import dtu.shared.ReceptKomponentDTO;

public class DAO extends RemoteServiceServlet implements KartotekService {
	
	private static final String URL = "jdbc:mysql://62.79.16.16/grp16";
//	private static final String URL = "jdbc:mysql://localhost/cdio_3";
	private static final String USERNAME = "grp16";
	private static final String PASSWORD = "ZHnPq74Y";

	private Connection connection = null; // manages connection

	private PreparedStatement loginStmt = null;
	
	//Operatør
	private PreparedStatement createOperatorStmt = null;
	private PreparedStatement updateOperatorStmt = null;
	private PreparedStatement getOperatorsStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deleteOperatorStmt = null;
	
	//RåvareBatch
	private PreparedStatement getRaavareBatchStmt = null;
	private PreparedStatement createRaavareBatchStmt = null;
	
	//Råvare
	private PreparedStatement createRaavareStmt = null;
	private PreparedStatement updateRaavareStmt = null;
	private PreparedStatement getRaavareStmt = null;
	
	//Recept
	private PreparedStatement getReceptStmt = null;
	private PreparedStatement createReceptStmt = null;
	
	// Receptkomponent
	private PreparedStatement getReceptKomponentStmt = null;
	private PreparedStatement createReceptKomponentStmt = null;
	
	//ProduktBatch
	private PreparedStatement getProduktBatchStmt = null;
	private PreparedStatement createProduktBatchStmt = null;
	
	
	
	public DAO() throws DALException {
		try 
		{
			connection = 
					DriverManager.getConnection( URL, USERNAME, PASSWORD );
			
			// create query that add an operator to kartotek
			createOperatorStmt = 
					connection.prepareStatement( "INSERT INTO operatoer " + 
							"(opr_navn, ini, cpr, password, active, level) " + 
							"VALUES ( ?, ?, ?, ?, ?, ?)" );

			// create query that updates an operator
			updateOperatorStmt = connection.prepareStatement( 
					"UPDATE operatoer SET opr_navn = ?, ini = ?, cpr = ?, password = ?, active = ?, level = ? WHERE opr_id = ?" );

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
					"UPDATE operatoer SET active = 0 WHERE opr_id = ?");
			
			loginStmt = connection.prepareStatement( 
					"SELECT * FROM operatoer WHERE opr_id = ? AND password = ?");
			
			
			// RÅVARER!!!
			// create query that add an raavare to kartotek
			createRaavareStmt = 
					connection.prepareStatement( "INSERT INTO raavare " + 
							"(raavare_id, raavare_navn, leverandoer) " + 
							"VALUES ( ?, ?, ?)" );

			// create query that updates an raavare
			updateRaavareStmt = connection.prepareStatement( 
					"UPDATE raavare SET Raavare_navn = ?, leverandoer = ? WHERE raavare_id = ?" );

			// create query that get all raavarer in kartotek
			getRaavareStmt = connection.prepareStatement( 
					"SELECT * FROM raavare"); 
			
			createReceptStmt = connection.prepareStatement( "INSERT INTO recept " + 
					"(recept_Id, recept_navn) " + 
					"VALUES (?, ?)" );
			
			getReceptStmt = connection.prepareStatement( 
					"SELECT * FROM recept"); 
			
			// RECEPTKOMPONENT 
			
			//Create receptkomponent Query to make table
			createReceptKomponentStmt = connection.prepareStatement( "INSERT INTO receptkomponent " + 
					"(recept_Id, raavare_id, nom_Netto, tolerance) " + 
					"VALUES (?, ?, ?, ?)" );
			//Get receptkomponent Query
			getReceptKomponentStmt = connection.prepareStatement( 
					"SELECT * FROM receptkomponent"); 
			
			//RÅVAREBATCHES
			
			//Create RåvareBatch query
			createRaavareBatchStmt = 
					connection.prepareStatement( "INSERT INTO Raavarebatch " + 
							"(Raavare_ID, Rb_Id, maengde) " + 
							"VALUES ( ?, ?, ?)" );
			
			getRaavareBatchStmt = connection.prepareStatement( 
					"SELECT * FROM Raavarebatch"); 
			
			getProduktBatchStmt = connection.prepareStatement( 
					"SELECT * FROM ProduktBatch"); 
			
			createProduktBatchStmt = 
					connection.prepareStatement( "INSERT INTO ProduktBatch " + 
							"(pb_id, status, recept_id) " + 
							"VALUES ( ?, ?, ?)" );
			
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
			throw new DALException(" \"deleteOperator\" fejlede" + e.getMessage());
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
						resultSet.getInt("opr_id"),
						resultSet.getString("opr_navn"),
						resultSet.getString("ini"),
						resultSet.getString("cpr"),
						resultSet.getString("password"),
						resultSet.getInt("active"),
						resultSet.getInt("level")));
			} 
		} 
		catch ( SQLException e )
		{
			throw new DALException(" \"getOperators\" fejlede " + e.getMessage());
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
			throw new DALException(" \"getSize\" fejlede " + e.getMessage());
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
			throw new DALException(" \"createOperator\" fejlede " + e.getMessage());
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
	public int login(int id, String password) throws DALException{
		ResultSet resultSet = null;
		try 
		{
			loginStmt.setInt(1, 1);
			loginStmt.setString(2, "HelloKitty");
//			loginStmt.setInt(1, id);
//			loginStmt.setString(2, password);
			try {
				resultSet = loginStmt.executeQuery();
			} catch (Exception e) {
				throw new DALException(" \"Login\" fejlede " + e.getMessage());
			}
			 
			
			if (resultSet.first()) return resultSet.getInt(7);
			
		} 
		catch ( SQLException e )
		{
			throw new DALException(" \"Login\" fejlede " + e.getMessage());
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
			throw new DALException(" \"createRaavare\" fejlede " + e.getMessage());
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
		catch ( SQLException e )
		{
			throw new DALException(" \"getRaavarer\" fejlede " + e.getMessage());
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
	public void createReceptKomponent(ReceptKomponentDTO p) throws Exception {
		try {
			createReceptKomponentStmt.setInt(1, p.getRcpId());
			createReceptKomponentStmt.setInt(2, p.getRvrId());
			createReceptKomponentStmt.setDouble(3, p.getNomNetto());
			createReceptKomponentStmt.setDouble(4, p.getTolerance());
			createReceptKomponentStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createReceptKomponent\" fejlede "+ e.getMessage());
		} 
	}
	
	// Vis RECEPT
	
	@Override
	public List<ReceptKomponentDTO> getReceptKomponenter() throws DALException {
		List<ReceptKomponentDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getReceptKomponentStmt.executeQuery(); 
			results = new ArrayList< ReceptKomponentDTO >();

			while (resultSet.next())
			{
				results.add( new ReceptKomponentDTO(
						resultSet.getInt("Recept_id"),
						resultSet.getInt("Raavare_id"),
						resultSet.getDouble("Nom_Netto"),
						resultSet.getDouble("Tolerance")
						));
			} 
		} 
		catch ( SQLException e )
		{
			throw new DALException(" \"getRecepts\" fejlede " + e.getMessage());
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
			createReceptKomponentStmt.setInt(1, p.getRaavareId());
			createReceptKomponentStmt.setInt(2, p.getRbId());
			createReceptKomponentStmt.setDouble(3, p.getMaengde());
			createReceptKomponentStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRaavarebatch\" fejlede " + e.getMessage());
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
						resultSet.getInt("rb_id"),
						resultSet.getInt("Raavare_id"),
						resultSet.getDouble("maengde")
						));
			} 
		} 
		catch ( SQLException e )
		{
			throw new DALException(" \"getRaavareBatch\" fejlede " + e.getMessage());
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
	public void createRecept(ReceptDTO p) throws Exception {
		try {
			createReceptStmt.setInt(1, p.getRcpId());
			createReceptStmt.setString(2, p.getRecept_Navn());
			createReceptStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRecept\" fejlede "+ e.getMessage());
		} 
	}


	@Override
	public List<ReceptDTO> getRecepter() throws DALException {
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
						resultSet.getString("Recept_navn")
						));
			} 
		} 
		catch ( SQLException e )
		{
			throw new DALException(" \"getRecepts\" fejlede " + e.getMessage());
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
