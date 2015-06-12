package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.DALException;
import dtu.shared.UserDTO;
import dtu.shared.ProductBatchDTO;
import dtu.shared.CommoditiesBatchDTO;
import dtu.shared.CommoditiesDTO;
import dtu.shared.RecipeDTO;
import dtu.shared.ReceptKomponentDTO;

public interface KartotekServiceAsync {

	void login(int id, String password, AsyncCallback<Integer> callback);

	// Operat�rer
	void createUser(UserDTO p, AsyncCallback<Void> callback);

	void updateUser(UserDTO p, AsyncCallback<Void> callback);

	void getUser(AsyncCallback<List<UserDTO>> callback);

	void deleteUser(int id, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);
	
	// R�varer
	void createCommodity(CommoditiesDTO p, AsyncCallback<Void> callback);
	
	void updateCommodity(CommoditiesDTO p, AsyncCallback<Void> callback);
	
	void getCommodity(AsyncCallback<List<CommoditiesDTO>> callback);
	
	// Receptkomponent
	void createRecipeComponent(ReceptKomponentDTO p, AsyncCallback<Void> callback);

	void getRecipeComponent(AsyncCallback<List<ReceptKomponentDTO>> callback);
	
	//Recept
	void createRecipe(RecipeDTO newRecept, AsyncCallback<Void> asyncCallback);
	
	void getRecipe(AsyncCallback<List<RecipeDTO>> callback);
	
	//Raavarebatch
	void createCommodityBatch(CommoditiesBatchDTO p, AsyncCallback<Void> callback) ;
	
	void getCommodityBatch(AsyncCallback<List<CommoditiesBatchDTO>> callback);

	//ProduktBatch
	void createProductBatch(ProductBatchDTO p, AsyncCallback<Void> callback) ;

	void getProductBatch(AsyncCallback<List<ProductBatchDTO>> callback);

}
