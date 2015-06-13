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
import dtu.shared.UserDTO;
import dtu.shared.ProductBatchDTO;
import dtu.shared.CommoditiesBatchDTO;
import dtu.shared.CommoditiesDTO;
import dtu.shared.RecipeDTO;
import dtu.shared.ReceptKomponentDTO;

public class DAO extends RemoteServiceServlet implements KartotekService {
	
	private static final String URL = "jdbc:mysql://62.79.16.16/grp16";
//	private static final String URL = "jdbc:mysql://localhost/cdio_3";
	private static final String USERNAME = "grp16";
	private static final String PASSWORD = "ZHnPq74Y";

	private Connection connection = null; // manages connection

	private PreparedStatement loginStmt = null;
	
	//user
	private PreparedStatement createUserStmt = null;
	private PreparedStatement updateUserStmt = null;
	private PreparedStatement getUserStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deleteUserStmt = null;
	
	//R�vareBatch
	private PreparedStatement getCommoditiesBatchStmt = null;
	private PreparedStatement createCommoditiesBatchStmt = null;
	
	//R�vare
	private PreparedStatement createCommoditiesStmt = null;
	private PreparedStatement updateCommoditiesStmt = null;
	private PreparedStatement getCommoditiesStmt = null;
	
	//Recept
	private PreparedStatement getRecipeStmt = null;
	private PreparedStatement createRecipeStmt = null;
	
	// Receptkomponent
	private PreparedStatement getRecipeComponentStmt = null;
	private PreparedStatement createRecipeComponentStmt = null;
	
	//ProduktBatch
	private PreparedStatement getProductBatchStmt = null;
	private PreparedStatement createProductBatchStmt = null;
	
	
	
	public DAO() throws DALException {
		try 
		{
			connection = 
					DriverManager.getConnection( URL, USERNAME, PASSWORD );
			
			// create query that add an operator to kartotek
			createUserStmt = 
					connection.prepareStatement( "INSERT INTO brugere " + 
							"(opr_navn, ini, cpr, password, active, level) " + 
							"VALUES ( ?, ?, ?, ?, ?, ?)" );

			// create query that updates an operator
			updateUserStmt = connection.prepareStatement( 
					"UPDATE brugere SET opr_navn = ?, ini = ?, cpr = ?, password = ?, active = ?, level = ? WHERE opr_id = ?" );

			// create query that get all operators in kartotek
			getUserStmt = connection.prepareStatement( 
					"SELECT * FROM brugere"); 

			// create query that gets size of kartotek
			getSizeStmt = connection.prepareStatement( 
					"SELECT COUNT(*) FROM brugere");

			// create query that deletes a operator in kartotek
//			deleteOperatorStmt = connection.prepareStatement( 
//					"DELETE FROM brugere WHERE id =  ?");
			deleteUserStmt = connection.prepareStatement( 
					"UPDATE brugere SET active = 0 WHERE opr_id = ?");
			
			loginStmt = connection.prepareStatement( 
					"SELECT * FROM brugere WHERE opr_id = ? AND password = ?");
			
			
			// R�VARER!!!
			// create query that add an raavare to kartotek
			createCommoditiesStmt = 
					connection.prepareStatement( "INSERT INTO raavare " + 
							"(raavare_id, raavare_navn, leverandoer) " + 
							"VALUES ( ?, ?, ?)" );

			// create query that updates an raavare
			updateCommoditiesStmt = connection.prepareStatement( 
					"UPDATE raavare SET Raavare_navn = ?, leverandoer = ? WHERE raavare_id = ?" );

			// create query that get all raavarer in kartotek
			getCommoditiesStmt = connection.prepareStatement( 
					"SELECT * FROM raavare"); 
			
			createRecipeStmt = connection.prepareStatement( "INSERT INTO recept " + 
					"(recept_Id, recept_navn) " + 
					"VALUES (?, ?)" );
			
			getRecipeStmt = connection.prepareStatement( 
					"SELECT * FROM recept"); 
			
			// RECEPTKOMPONENT 
			
			//Create receptkomponent Query to make table
			createRecipeComponentStmt = connection.prepareStatement( "INSERT INTO receptkomponent " + 
					"(recept_Id, raavare_id, nom_Netto, tolerance) " + 
					"VALUES (?, ?, ?, ?)" );
			//Get receptkomponent Query
			getRecipeComponentStmt = connection.prepareStatement( 
					"SELECT * FROM receptkomponent"); 
			
			//R�VAREBATCHES
			
			//Create R�vareBatch query
			createCommoditiesBatchStmt = 
					connection.prepareStatement( "INSERT INTO Raavarebatch " + 
							"(Raavare_ID, Rb_Id, maengdeValid) " + 
							"VALUES ( ?, ?, ?)" );
			
			getCommoditiesBatchStmt = connection.prepareStatement( 
					"SELECT * FROM Raavarebatch"); 
			
			getProductBatchStmt = connection.prepareStatement( 
					"SELECT * FROM ProduktBatch"); 
			
			createProductBatchStmt = 
					connection.prepareStatement( "INSERT INTO ProduktBatch " + 
							"(pb_id, statusValid, recept_id) " + 
							"VALUES ( ?, ?, ?)" );
			
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException("Kan ikke oprette forbindelse til database! " + sqlException.getMessage());
		}
	}
	
	
	@Override
	public void deleteUser(int id) throws DALException {
		try {
			deleteUserStmt.setInt(1, id);
			deleteUserStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"deleteOperator\" fejlede" + e.getMessage());
		} 
	}

	@Override
	public List<UserDTO> getUser() throws DALException {
		List<UserDTO> results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getUserStmt.executeQuery(); 
			results = new ArrayList< UserDTO >();

			while (resultSet.next())
			{
				results.add( new UserDTO(
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
	public void createUser(UserDTO p) throws Exception {
		try {
			createUserStmt.setString(1, p.getOprNavn());
			createUserStmt.setString(2, p.getIni());
			createUserStmt.setString(3, p.getCpr());
			createUserStmt.setString(4, p.getPassword());
			createUserStmt.setString(5, p.getActive());
			createUserStmt.setString(6, p.getLevel());
			createUserStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createOperator\" fejlede " + e.getMessage());
		} 
	}

	@Override
	public void updateUser(UserDTO p) throws Exception {
		try {
			updateUserStmt.setString(1, p.getOprNavn());
			updateUserStmt.setString(2, p.getIni());
			updateUserStmt.setString(3, p.getCpr());
			updateUserStmt.setString(4, p.getPassword());
			updateUserStmt.setString(5, p.getActive());
			updateUserStmt.setString(6, p.getLevel());
			updateUserStmt.setInt(7, p.getOprId());
			updateUserStmt.executeUpdate();
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
//			loginStmt.setInt(1, 1);
//			loginStmt.setString(2, "HelloKitty");
			loginStmt.setInt(1, id);
			loginStmt.setString(2, password);
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
	public void createCommodity(CommoditiesDTO p) throws Exception {
		try {
			createCommoditiesStmt.setInt(1, p.getRvrId());
			createCommoditiesStmt.setString(2, p.getRvrNavn());
			createCommoditiesStmt.setString(3, p.getlvr());
			
			createCommoditiesStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRaavare\" fejlede " + e.getMessage());
		} 
	}


	@Override
	public void updateCommodity(CommoditiesDTO p) throws Exception {
		try {
			updateCommoditiesStmt.setString(1, p.getRvrNavn());
			updateCommoditiesStmt.setString(2, p.getlvr());
			updateCommoditiesStmt.setInt(3, p.getRvrId());
			updateCommoditiesStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"updateRaavare\" fejlede: " + e.getMessage());
		} 
	}


	@Override
	public List<CommoditiesDTO> getCommodity() throws Exception {
		List<CommoditiesDTO> results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getCommoditiesStmt.executeQuery(); 
			results = new ArrayList< CommoditiesDTO >();

			while (resultSet.next())
			{
				results.add( new CommoditiesDTO(
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
	public void createRecipeComponent(ReceptKomponentDTO p) throws Exception {
		try {
			createRecipeComponentStmt.setInt(1, p.getRcpId());
			createRecipeComponentStmt.setInt(2, p.getRvrId());
			createRecipeComponentStmt.setDouble(3, p.getNomNetto());
			createRecipeComponentStmt.setDouble(4, p.getTolerance());
			createRecipeComponentStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createReceptKomponent\" fejlede "+ e.getMessage());
		} 
	}
	
	// Vis RECEPT
	
	@Override
	public List<ReceptKomponentDTO> getRecipeComponent() throws DALException {
		List<ReceptKomponentDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getRecipeComponentStmt.executeQuery(); 
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
	
	//R�VAREBATCH
	//Create tuple
	@Override
	public void createCommodityBatch(CommoditiesBatchDTO p) throws Exception {
		try {
			createCommoditiesBatchStmt.setInt(1, p.getRaavareId());
			createCommoditiesBatchStmt.setInt(2, p.getRbId());
			createCommoditiesBatchStmt.setDouble(3, p.getMaengde());
			createCommoditiesBatchStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRaavarebatch\" fejlede " + e.getMessage());
		} 
	}
	
	@Override
	public List<CommoditiesBatchDTO> getCommodityBatch() throws DALException {
		List<CommoditiesBatchDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getCommoditiesBatchStmt.executeQuery(); 
			results = new ArrayList< CommoditiesBatchDTO >();

			while (resultSet.next())
			{
				results.add(new CommoditiesBatchDTO(
						resultSet.getInt("rb_id"),
						resultSet.getInt("Raavare_id"),
						resultSet.getDouble("maengdeValid")
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
	public void createRecipe(RecipeDTO p) throws Exception {
		try {
			createRecipeStmt.setInt(1, p.getRcpId());
			createRecipeStmt.setString(2, p.getRecept_Navn());
			createRecipeStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createRecept\" fejlede "+ e.getMessage());
		} 
	}


	@Override
	public List<RecipeDTO> getRecipe() throws DALException {
		List<RecipeDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getRecipeStmt.executeQuery(); 
			results = new ArrayList< RecipeDTO >();

			while (resultSet.next())
			{
				results.add( new RecipeDTO(
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


	@Override
	public void createProductBatch(ProductBatchDTO p) throws Exception {
		try {
			createProductBatchStmt.setInt(1, p.getPb_ID());
			createProductBatchStmt.setInt(2, p.getStatus());
			createProductBatchStmt.setInt(3, p.getRecept_id());
			createProductBatchStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createProduktBatcht\" fejlede "+ e.getMessage());
		} 	
	}

	@Override
	public List<ProductBatchDTO> getProductBatch() throws DALException {
		List<ProductBatchDTO> results = null;
		ResultSet resultSet = null;
		try 
		{
			resultSet = getProductBatchStmt.executeQuery(); 
			results = new ArrayList< ProductBatchDTO >();

			while (resultSet.next())
			{
				results.add( new ProductBatchDTO(
						resultSet.getInt("Pb_Id"),
						resultSet.getInt("Status"),
						resultSet.getInt("Recept_Id")
						));
			} 
		} 
		catch ( SQLException e )
		{
			throw new DALException(" \"getProduktBatch\" fejlede " + e.getMessage());
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
