package dtu.shared;

import java.io.Serializable;

/**

 * Operatør Data Transfer Object

 */

public class UserDTO implements Serializable {

	/** Operatør id i området 1­99999999. Vælges af brugerne */
	int oprId;

	/** Operatør navn min. 2 max. 20 karakterer */
	String oprNavn;

	/** Operatør initialer min. 2 max. 3 karakterer */
	String ini;

	/** Operatør cpr­nr 10 karakterer */
	String cpr;

	/** Operatør password min. 7 max. 8 karakterer */
	String password;
	
	/** Operatør aktiv (1) eller inaktiv (0) */
	int active;
	
	/** Operatør niveau - 1 = operatør, 2 = superbruger */
	int level;
	
	public UserDTO() {
	}
	
	public UserDTO(String oprNavn, String ini, String cpr, String password, int active, int level) {
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
		this.active = active;
		this.level = level;
	}

	public UserDTO(int oprId, String oprNavn, String ini, String cpr, String password, int active, int level) {
		this.oprId = oprId;
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
		this.active = active;
		this.level = level;
	}

	public int getOprId() {
		return oprId;
	}

	public void setOprId(int oprId) {
		this.oprId = oprId;
	}

	public String getOprNavn() {
		return oprNavn;
	}

	public void setOprNavn(String oprNavn) {
		this.oprNavn = oprNavn;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getActive() {
		return String.valueOf(active);
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	public String getLevel() {
		return String.valueOf(level);
	}

	public void setLevel(int level) {
		this.level = level;
	}

}