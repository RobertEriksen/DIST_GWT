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
	void createOperatoer(UserDTO p, AsyncCallback<Void> callback);

	void updateOperatoer(UserDTO p, AsyncCallback<Void> callback);

	void getOperators(AsyncCallback<List<UserDTO>> callback);

	void deleteOperator(int id, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);
	
	// R�varer
	void createRaavare(CommoditiesDTO p, AsyncCallback<Void> callback);
	
	void updateRaavare(CommoditiesDTO p, AsyncCallback<Void> callback);
	
	void getRaavarer(AsyncCallback<List<CommoditiesDTO>> callback);
	
	// Receptkomponent
	void createReceptKomponent(ReceptKomponentDTO p, AsyncCallback<Void> callback);

	void getReceptKomponenter(AsyncCallback<List<ReceptKomponentDTO>> callback);
	
	//Recept
	void createRecept(RecipeDTO newRecept, AsyncCallback<Void> asyncCallback);
	
	void getRecepter(AsyncCallback<List<RecipeDTO>> callback);
	
	//Raavarebatch
	void createRaavareBatch(CommoditiesBatchDTO p, AsyncCallback<Void> callback) ;
	
	void getRaavareBatch(AsyncCallback<List<CommoditiesBatchDTO>> callback);

	//ProduktBatch
	void createProduktBatch(ProductBatchDTO p, AsyncCallback<Void> callback) ;

	void getProduktBatch(AsyncCallback<List<ProductBatchDTO>> callback);

}
