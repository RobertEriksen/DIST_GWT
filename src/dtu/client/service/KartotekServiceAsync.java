package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.OperatoerDTO;
import dtu.shared.RaavareDTO;

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
	
}
