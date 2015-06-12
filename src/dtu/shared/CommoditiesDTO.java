package dtu.shared;

import java.io.Serializable;

/**

 * Operatør Data Transfer Object

 */

public class CommoditiesDTO implements Serializable {

	/** Operatør id i området 1­99999999. Vælges af brugerne */
	int raavare_ID;

	/** Operatør navn min. 2 max. 20 karakterer */
	String raavare_Navn;

	/** Operatør initialer min. 2 max. 3 karakterer */
	String leverandoer;
	
	public CommoditiesDTO() {
	}
	
	public CommoditiesDTO(int raavare_ID, String raavare_Navn, String leverandoer) {
		this.raavare_ID= raavare_ID;
		this.raavare_Navn = raavare_Navn;
		this.leverandoer = leverandoer;
	}


	public int getRvrId() {
		return raavare_ID;
	}

	public void setRvrId(int raavare_ID) {
		this.raavare_ID = raavare_ID;
	}

	public String getRvrNavn() {
		return raavare_Navn;
	}

	public void setRvrNavn(String raavare_Navn) {
		this.raavare_Navn = raavare_Navn;
	}

	public String getlvr() {
		return leverandoer;
	}

	public void setlvr (String leverandoer) {
		this.leverandoer = leverandoer;
	}

	
}