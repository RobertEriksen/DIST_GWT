package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.shared.DALException;
import dtu.shared.OperatoerDTO;
import dtu.shared.ProduktBatchDTO;
import dtu.shared.RaavareBatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptKomponentDTO;


@RemoteServiceRelativePath("kartotekservice")
public interface KartotekService extends RemoteService {

	// note: announcing exception makes it possible to communicate 
	// user defined exceptions from the server side to the client side
	// otherwise only generic server exceptions will be send back
	// in the onFailure call back method
	
	// Operatører
	void createOperatoer(OperatoerDTO op) throws Exception;
	void deleteOperator(int id) throws Exception;
	void updateOperatoer(OperatoerDTO op) throws Exception;
	List<OperatoerDTO> getOperators() throws Exception;
	int getSize() throws Exception;
	
	int login(int id, String password) throws Exception;
	
	// Råvarer
	void createRaavare(RaavareDTO op) throws Exception;
	void updateRaavare(RaavareDTO op) throws Exception;
	List<RaavareDTO> getRaavarer() throws Exception;
	
	//Receptkomponent
	void createReceptKomponent(ReceptKomponentDTO p) throws Exception;
	List<ReceptKomponentDTO> getReceptKomponenter() throws DALException;
	
	//Recept
	void createRecept(ReceptDTO p) throws Exception;
	List<ReceptDTO> getRecepter() throws DALException; 
	
	//Raavarebatch
	void createRaavareBatch(RaavareBatchDTO p) throws Exception;
	List<RaavareBatchDTO> getRaavareBatch() throws DALException;
	
	//ProduktBatch
	void createProduktBatch(ProduktBatchDTO p) throws Exception;
	List<ProduktBatchDTO> getProduktBatch() throws DALException;
}
