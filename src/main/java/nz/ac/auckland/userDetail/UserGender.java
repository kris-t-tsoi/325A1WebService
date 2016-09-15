package nz.ac.auckland.userDetail;

public enum UserGender {
	
	MALE, FEMALE;

	/**
	 * Creates a Gender value from a text string.
	 * 
	 */
	public static UserGender fromString(String text) {
		if (text != null) {
			for (UserGender g : UserGender.values()) {
				if (text.equalsIgnoreCase(g.toString())) {
					return g;
				}
			}
		}
		return null;
	}
}
