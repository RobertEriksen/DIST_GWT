package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.OperatoerDTO;

public interface KartotekServiceAsync {

	void createOperator(OperatoerDTO p, AsyncCallback<Void> callback);

	void updateOperator(OperatoerDTO p, AsyncCallback<Void> callback);

	void getOperators(AsyncCallback<List<OperatoerDTO>> callback);

	void deleteOperator(int id, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);

}
