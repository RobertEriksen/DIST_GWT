package dtu.shared;

import java.io.Serializable;

/**

 * Operatør Data Transfer Object

 */

public class ReceptDTO implements Serializable {

	/** Operatør id i området 1­99999999. Vælges af brugerne */
	int rcpId;

	/** Operatør navn min. 2 max. 20 karakterer */
	String rcpNavn;

	/** Operatør cpr­nr 10 karakterer */
	int rvrId;

	/** Operatør password min. 7 max. 8 karakterer */
	double nomNetto;
	
	/** Operatør aktiv (1) eller inaktiv (0) */
	double tolerance;
	
	public ReceptDTO(){
		
	}
	
	public ReceptDTO(int rcpId, int rvrId, String rcpNavn, double nomNetto, double tolerance) {
		this.rcpNavn = rcpNavn;
		this.rcpId = rcpId;
		this.rvrId = rvrId;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public int getRcpId() {
		return rcpId;
	}

	public void setRcpId(int rcpId) {
		this.rcpId = rcpId;
	}

	public String getRcpNavn() {
		return rcpNavn;
	}

	public void setRcpNavn(String rcpNavn) {
		this.rcpNavn = rcpNavn;
	}

	public int getRvrId() {
		return rvrId;
	}

	public void setRvrId(int rvrId) {
		this.rvrId = rvrId;
	}

	public double getNomNetto() {
		return nomNetto;
	}

	public void setNomNetto(double nomNetto) {
		this.nomNetto = nomNetto;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

}