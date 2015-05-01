package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.shared.OperatoerDTO;


@RemoteServiceRelativePath("kartotekservice")
public interface KartotekService extends RemoteService {

	// note: announcing exception makes it possible to communicate 
	// user defined exceptions from the server side to the client side
	// otherwise only generic server exceptions will be send back
	// in the onFailure call back method
	void createOperator(OperatoerDTO op) throws Exception;
	void deleteOperator(int id) throws Exception;
	void updateOperator(OperatoerDTO op) throws Exception;
	List<OperatoerDTO> getOperators() throws Exception;
	int getSize() throws Exception;
}
