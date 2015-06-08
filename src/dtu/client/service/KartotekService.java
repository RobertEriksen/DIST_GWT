package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.shared.OperatoerDTO;
import dtu.shared.RaavareDTO;


@RemoteServiceRelativePath("kartotekservice")
public interface KartotekService extends RemoteService {

	// note: announcing exception makes it possible to communicate 
	// user defined exceptions from the server side to the client side
	// otherwise only generic server exceptions will be send back
	// in the onFailure call back method
	
	// Operat�rer
	void createOperatoer(OperatoerDTO op) throws Exception;
	void deleteOperator(int id) throws Exception;
	void updateOperatoer(OperatoerDTO op) throws Exception;
	List<OperatoerDTO> getOperators() throws Exception;
	int getSize() throws Exception;
	
	int login(int id, String password) throws Exception;
	
	// R�varer
	void createRaavare(RaavareDTO op) throws Exception;
	void updateRaavare(RaavareDTO op) throws Exception;
	List<RaavareDTO> getRaavarer() throws Exception;
}
