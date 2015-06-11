package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.DALException;
import dtu.shared.OperatoerDTO;
import dtu.shared.ProduktBatchDTO;
import dtu.shared.RaavareBatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;

public interface KartotekServiceAsync {

	void login(int id, String password, AsyncCallback<Integer> callback);

	// Operat�rer
	void createOperatoer(OperatoerDTO p, AsyncCallback<Void> callback);

	void updateOperatoer(OperatoerDTO p, AsyncCallback<Void> callback);

	void getOperators(AsyncCallback<List<OperatoerDTO>> callback);

	void deleteOperator(int id, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);
	
	// R�varer
	void createRaavare(RaavareDTO p, AsyncCallback<Void> callback);
	
	void updateRaavare(RaavareDTO p, AsyncCallback<Void> callback);
	
	void getRaavarer(AsyncCallback<List<RaavareDTO>> callback);
	
	// Receptkomponent
	void createReceptKomponent(ReceptKomponentDTO p, AsyncCallback<Void> callback);

	void getReceptKomponenter(AsyncCallback<List<ReceptKomponentDTO>> callback);
	
	//Recept
	void createRecept(ReceptDTO newRecept, AsyncCallback<Void> asyncCallback);
	
	void getRecepter(AsyncCallback<List<ReceptDTO>> callback);
	
	//Raavarebatch
	void createRaavareBatch(RaavareBatchDTO p, AsyncCallback<Void> callback) ;
	
	void getRaavareBatch(AsyncCallback<List<RaavareBatchDTO>> callback);

	//ProduktBatch
	void createProduktBatch(ProduktBatchDTO p, AsyncCallback<Void> callback) ;

	void getProduktBatch(AsyncCallback<List<ProduktBatchDTO>> callback);

}
