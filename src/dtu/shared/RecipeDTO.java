package dtu.shared;

import java.io.Serializable;

/**

 * Operatør Data Transfer Object

 */

public class RecipeDTO implements Serializable {

	/** Operatør id i området 1­99999999. Vælges af brugerne */
	int recept_ID;

	/** Operatør navn min. 2 max. 20 karakterer */
	String recept_Navn;
	
	public RecipeDTO() {
	}
	
	public RecipeDTO(int recept_ID, String raavare_Navn) {
		this.recept_ID= recept_ID;
		this.recept_Navn = raavare_Navn;
	}

	public void setRecept_ID(int recept_ID) {
		this.recept_ID = recept_ID;
	}

	public String getRecept_Navn() {
		return recept_Navn;
	}

	public void setRecept_Navn(String recept_Navn) {
		this.recept_Navn = recept_Navn;
	}

	public int getRcpId() {
		return recept_ID;
	}


	
}