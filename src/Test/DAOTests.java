package Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dtu.server.dal.DAO;
import dtu.shared.DALException;
import dtu.shared.OperatoerDTO;

public class DAOTests {
	static String ListeB = "";
	static String ListeA = "";

	@Test
	public void test() throws Exception {
DAO dao = new DAO();
		
//		System.out.println("Vi definere en liste med alle superbrugere");
		List<OperatoerDTO> ListBefore = dao.getOperators();
		for (int i = 0; i < ListBefore.size(); i++) {
			ListeB = ListeB + ListBefore.get(i).getOprNavn();
			ListeB += ", ";
		}
		
		OperatoerDTO op = new OperatoerDTO("Jens Peter", "JP", "0987654444", "PassJP10", 1, 4);
		dao.createOperatoer(op);
		
		List<OperatoerDTO> ListAfter = dao.getOperators();
		for (int i = 0; i < ListAfter.size(); i++) {
			ListeA = ListeA + ListAfter.get(i).getOprNavn();
			ListeA += ", ";
		}
		
		if(ListeA.equals(ListeB)){
			fail();
		}
		dao.deleteOperator(op.getOprId());
		if(!dao.getOperators().get(5).getActive().equals(op.getActive())){
			fail();
		};	
	}

}
