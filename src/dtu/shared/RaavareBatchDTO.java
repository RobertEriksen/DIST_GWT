package dtu.shared;

import java.io.Serializable;

/**

 * Operatør Data Transfer Object

 */

public class RaavareBatchDTO implements Serializable {

	/** Operatør id i området 1­99999999. Vælges af brugerne */
	int rbId;

	/** Operatør navn min. 2 max. 20 karakterer */
	int raavareId;

	/** Operatør cpr­nr 10 karakterer */
	double maengde;

	/** Operatør password min. 7 max. 8 karakterer */
//	double nomNetto;
	
	/** Operatør aktiv (1) eller inaktiv (0) */
//	double tolerance;
	
	public RaavareBatchDTO(){
		
	}
	
	public RaavareBatchDTO(int rbId, int raavareId, double maengde) {
		this.rbId = rbId;
		this.raavareId = raavareId;
		this.maengde = maengde;
	}

	public int getRbId() {
		return rbId;
	}

	public void setRbId(int rbId) {
		this.rbId = rbId;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public void setRaavareId(int raavareId) {
		this.raavareId = raavareId;
	}

	public double getMaengde() {
		return maengde;
	}

	public void setMaengde(double maengde) {
		this.maengde = maengde;
	}

}