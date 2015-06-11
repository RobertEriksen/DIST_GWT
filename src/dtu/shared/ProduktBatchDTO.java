package dtu.shared;

import java.io.Serializable;

/**

 * Operatør Data Transfer Object

 */

public class ProduktBatchDTO implements Serializable {

	/** Operatør id i området 1­99999999. Vælges af brugerne */
	int pb_ID;

	/** Operatør navn min. 2 max. 20 karakterer */
	int status;
	
	int recept_id;
	
	public ProduktBatchDTO() {
	}
	
	public ProduktBatchDTO(int pb_ID, int status, int recept_id) {
		this.pb_ID= pb_ID;
		this.recept_id = recept_id;
		this.status = status;
	}

	public int getPb_ID() {
		return pb_ID;
	}

	public void setPb_ID(int pb_ID) {
		this.pb_ID = pb_ID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRecept_id() {
		return recept_id;
	}

	public void setRecept_id(int recept_id) {
		this.recept_id = recept_id;
	}
	
}