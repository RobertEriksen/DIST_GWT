package dtu.shared;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client-side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {

	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	
	public static boolean isValidID(String id) {
		if (id == null) return false;
		if (id.length() == 0) return false;
		try {
			if (Integer.valueOf(id) >= 1 && Integer.valueOf(id) <= 99999999) return true;
		} catch (NumberFormatException e) {
			return false;
		}
		return false;
	}
	
	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		if (name.length() == 0)
			return false;
		// min. 2 og max. 20 karakterer
		return (name.length() >= 2 && name.length() <= 20);
	}
	
	public static boolean isValidInitials(String ini) {
		if (ini == null) return false;
		if (ini.length() == 0) return false;
		// min. 2 og max. 3 characters
		return (ini.length() == 2 || ini.length() == 3);
	}
	
	public static boolean isValidCpr(String cpr) {
		if (cpr == null) return false;
		if (cpr.length() == 0) return false;
		// must be 11 chars (CDIO oplæg siger 10? Ingen bindestreg?)
		return (cpr.length() == 10);
	}
	
//	public static boolean isValidPass(String pass) {
//		if (pass == null) return false;
//		if (pass.length() == 0) return false;
//		// min. 7 & max. 8 characters
//		return (pass.length() == 7 || pass.length() == 8);
//	}
	
	// Password validation according to DTU's password rules (Taken from our CDIO1 (Copyright CDIO group 16, CDIO1, 2015))
	public static boolean isValidPass(String input) {
		if (input == null) return false;
		if (input.length() == 0) return false;
		
		char[] pw = input.toCharArray();
		int upper = 0, lower = 0, numbers = 0, symbols = 0, categories = 0;
		boolean valid = true;
		
		if (input.length() == 7 || input.length() == 8) {
			for (char chr : pw) {
				if (chr >= 65 && chr <= 90) upper++;// A-Z
				else if (chr >= 97 && chr <= 122) lower++;// a-z
				else if (chr >= 48 && chr <= 57) numbers++;// 0-9
				else if (chr == 95 || chr == 46 || chr == 45 || chr == 44 || chr == 33 || chr == 63 || chr == 61 || chr == 43) symbols++; // .-,_+!?=
				else valid = false;
			}
			
			if (upper > 0) categories++;
			if (lower > 0) categories++;
			if (numbers > 0) categories++;
			if (symbols > 0) categories++;
		}	
		if (categories >= 3 && valid) { // MINDST 3 AF KATEGORIERNE SKAL VÆRE REPRÆSENTERET
			return true; // PASSWORD ER GYLDIGT
		} else { return false; } // PASSWORD ER UGYLDIGT
	}
	
	public static boolean isValidActive(String active) {
		if (active == null) return false;
		if (active.length() != 1) return false;
		// 1 = active, 0 = inactive
		return (Integer.valueOf(active) == 1 || Integer.valueOf(active) == 0);
	}
	
	public static boolean isValidLevel(String level) {
		if (level == null) return false;
		if (level.length() != 1) return false;
		// 1 = operatør, 2 = superbruger
		return (Integer.valueOf(level) >= 1 && Integer.valueOf(level)<= 4);
	}
	
	public static boolean isValidTolerance(String Decimal){
		if(Double.valueOf(Decimal)>10) return false;
		if(Double.valueOf(Decimal)<0.1) return false;
		return true;
	}
	
	public static boolean isValidNomNetto(String Netto){
		if(Double.valueOf(Netto)>20) return false;
		if(Double.valueOf(Netto)<0.05) return false;
		return true;
	}
	
	public static boolean IsValidMaengde(String netto){
		if(!Double.valueOf(netto).isNaN()) return true;
		return false;
	}
	
}
