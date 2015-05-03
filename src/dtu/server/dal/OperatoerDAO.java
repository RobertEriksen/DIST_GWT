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

public class OperatoerDAO extends RemoteServiceServlet implements KartotekService {
	
	private static final String URL = "jdbc:mysql://localhost/kartotek";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private Connection connection = null; // manages connection

	private PreparedStatement createOperatorStmt = null;
	private PreparedStatement updateOperatorStmt = null;
	private PreparedStatement getOperatorsStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deleteOperatorStmt = null;

	public OperatoerDAO() throws DALException {
		try 
		{
			connection = 
					DriverManager.getConnection( URL, USERNAME, PASSWORD );
			
			// create query that add an operator to kartotek
			createOperatorStmt = 
					connection.prepareStatement( "INSERT INTO operatoer " + 
							"( navn, ini, cpr, pass) " + 
							"VALUES ( ?, ?, ?, ?)" );

			// create query that updates an operator
			updateOperatorStmt = connection.prepareStatement( 
					"UPDATE operatoer SET navn = ?, ini = ?, cpr = ?, pass = ? WHERE id = ?" );

			// create query that get all operators in kartotek
			getOperatorsStmt = connection.prepareStatement( 
					"SELECT * FROM operatoer"); 

			// create query that gets size of kartotek
			getSizeStmt = connection.prepareStatement( 
					"SELECT COUNT(*) FROM operatoer");

			// create query that deletes a operator in kartotek
			deleteOperatorStmt = connection.prepareStatement( 
					"DELETE FROM operatoer WHERE id =  ?");


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
						resultSet.getString("pass")));
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
	public void createOperator(OperatoerDTO p) throws Exception {
		try {
			createOperatorStmt.setString(1, p.getOprNavn());
			createOperatorStmt.setString(2, p.getIni());
			createOperatorStmt.setString(3, p.getCpr());
			createOperatorStmt.setString(4, p.getPassword());

			createOperatorStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"createOperator\" fejlede");
		} 
	}

	@Override
	public void updateOperator(OperatoerDTO p) throws Exception {
		try {
			updateOperatorStmt.setString(1, p.getOprNavn());
			updateOperatorStmt.setString(2, p.getIni());
			updateOperatorStmt.setString(3, p.getCpr());
			updateOperatorStmt.setString(4, p.getPassword());
			updateOperatorStmt.setInt(5, p.getOprId());
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

}
