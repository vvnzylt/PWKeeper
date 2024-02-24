package backend;

import java.security.SecureRandom;

public class PasswordGenerator {
	private final static String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	private final static String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String digits = "0123456789";
	private final static String specialChar = "!@#$%^&*()-_+=~`[]{}|;:,.<>?";
	private final static String combinedStrings = lowerCase + upperCase + digits + specialChar;
	private static SecureRandom random = new SecureRandom();
	
	public static String generatePassword(int length) {
		StringBuilder password = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			password.append(getRandomCharacter(combinedStrings));
		}
		
		return password.toString();
	}
	
	private static char getRandomCharacter(String characters) {
		int randomIndex = random.nextInt(characters.length());
		return characters.charAt(randomIndex);
	}
}