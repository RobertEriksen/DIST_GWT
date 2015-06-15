package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dtu.server.dal.DAO;
import dtu.shared.UserDTO;

public class DAOTest {
	static String ListeB = "";
	static String ListeA = "";

	@Test
	public void test() throws Exception {
DAO dao = new DAO();
		
//		System.out.println("Vi definere en liste med alle superbrugere");
		List<UserDTO> ListBefore = dao.getUser();
		for (int i = 0; i < ListBefore.size(); i++) {
			ListeB = ListeB + ListBefore.get(i).getOprNavn();
			ListeB += ", ";
		}
		
		UserDTO op = new UserDTO("Jens Peter", "JP", "0987654444", "PassJP10", 1, 4);
		dao.createUser(op);
		
		List<UserDTO> ListAfter = dao.getUser();
		for (int i = 0; i < ListAfter.size(); i++) {
			ListeA = ListeA + ListAfter.get(i).getOprNavn();
			ListeA += ", ";
		}
		
		if(ListeA.equals(ListeB)){
			fail();
		}
		dao.deleteUser(op.getOprId());
		if(!dao.getUser().get(5).getActive().equals(op.getActive())){
			fail();
		};	
	}

}