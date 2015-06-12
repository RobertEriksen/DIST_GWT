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
	void createUser(UserDTO op) throws Exception;
	void deleteUser(int id) throws Exception;
	void updateUser(UserDTO op) throws Exception;
	List<UserDTO> getUser() throws Exception;
	int getSize() throws Exception;
	
	int login(int id, String password) throws Exception;
	
	// R�varer
	void createCommodity(CommoditiesDTO op) throws Exception;
	void updateCommodity(CommoditiesDTO op) throws Exception;
	List<CommoditiesDTO> getCommodity() throws Exception;
	
	//Receptkomponent
	void createRecipeComponent(ReceptKomponentDTO p) throws Exception;
	List<ReceptKomponentDTO> getRecipeComponent() throws DALException;
	
	//Recept
	void createRecipe(RecipeDTO p) throws Exception;
	List<RecipeDTO> getRecipe() throws DALException; 
	
	//Raavarebatch
	void createCommodityBatch(CommoditiesBatchDTO p) throws Exception;
	List<CommoditiesBatchDTO> getCommodityBatch() throws DALException;
	
	//ProduktBatch
	void createProductBatch(ProductBatchDTO p) throws Exception;
	List<ProductBatchDTO> getProductBatch() throws DALException;
}
