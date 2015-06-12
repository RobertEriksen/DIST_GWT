package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.shared.DALException;
import dtu.shared.UserDTO;
import dtu.shared.ProductBatchDTO;
import dtu.shared.CommoditiesBatchDTO;
import dtu.shared.CommoditiesDTO;
import dtu.shared.RecipeDTO;
import dtu.shared.ReceptKomponentDTO;


@RemoteServiceRelativePath("kartotekservice")
public interface KartotekService extends RemoteService {

	// note: announcing exception makes it possible to communicate 
	// user defined exceptions from the server side to the client side
	// otherwise only generic server exceptions will be send back
	// in the onFailure call back method
	
	// Operat�rer
	void createOperatoer(UserDTO op) throws Exception;
	void deleteOperator(int id) throws Exception;
	void updateOperatoer(UserDTO op) throws Exception;
	List<UserDTO> getOperators() throws Exception;
	int getSize() throws Exception;
	
	int login(int id, String password) throws Exception;
	
	// R�varer
	void createRaavare(CommoditiesDTO op) throws Exception;
	void updateRaavare(CommoditiesDTO op) throws Exception;
	List<CommoditiesDTO> getRaavarer() throws Exception;
	
	//Receptkomponent
	void createReceptKomponent(ReceptKomponentDTO p) throws Exception;
	List<ReceptKomponentDTO> getReceptKomponenter() throws DALException;
	
	//Recept
	void createRecept(RecipeDTO p) throws Exception;
	List<RecipeDTO> getRecepter() throws DALException; 
	
	//Raavarebatch
	void createRaavareBatch(CommoditiesBatchDTO p) throws Exception;
	List<CommoditiesBatchDTO> getRaavareBatch() throws DALException;
	
	//ProduktBatch
	void createProduktBatch(ProductBatchDTO p) throws Exception;
	List<ProductBatchDTO> getProduktBatch() throws DALException;
}
