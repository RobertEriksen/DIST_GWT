package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.DALException;
import dtu.shared.OperatoerDTO;
import dtu.shared.RaavareBatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;

public interface KartotekServiceAsync {

	void login(int id, String password, AsyncCallback<Integer> callback);

	// Operatører
	void createOperatoer(OperatoerDTO p, AsyncCallback<Void> callback);

	void updateOperatoer(OperatoerDTO p, AsyncCallback<Void> callback);

	void getOperators(AsyncCallback<List<OperatoerDTO>> callback);

	void deleteOperator(int id, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);
	
	// Råvarer
	void createRaavare(RaavareDTO p, AsyncCallback<Void> callback);
	
	void updateRaavare(RaavareDTO p, AsyncCallback<Void> callback);
	
	void getRaavarer(AsyncCallback<List<RaavareDTO>> callback);
	
	// Måske fejl i recept
	void createReceptKomponent(ReceptKomponentDTO p, AsyncCallback<Void> callback);

	void getRecept(AsyncCallback<List<ReceptKomponentDTO>> callback);
	
	void createRaavareBatch(RaavareBatchDTO p, AsyncCallback<Void> callback) ;
	
	void getRaavareBatch(AsyncCallback<List<RaavareBatchDTO>> callback);

	void createRecept(ReceptDTO newRecept, AsyncCallback<Void> asyncCallback);
}
