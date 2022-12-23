package stringToUUID;

import java.security.SecureRandom;
import java.util.UUID;

public class IdGSTU {
	private static final int iMin = 1000;
	  private static final int iMax = 9999;
	  private static final int aMin = 100000;
	  private static final int aMax = 999999;

	  public static UUID generateId(String fName, String lName) {
//	    UUID rand = UUID.randomUUID();
//	    String randS = rand.toString();
//	    String nRandS = randS.substring(0, 8);
		
	    SecureRandom sRand = new SecureRandom();
	    byte[] randBytes = new byte[1];
		sRand.nextBytes(randBytes);
		
	    UUID uId = UUID.nameUUIDFromBytes(randBytes);
//		String toReturn = "";
//	    toReturn += fName.charAt(0);
//	    int num1 = (int) Math.floor(Math.random() * (iMax - iMin + 1) + iMin);
//	    toReturn += num1;
//	    toReturn += lName.charAt(0);
//	    int num2 = (int) Math.floor(Math.random() * (iMax - iMin + 1) + iMin);
//	    toReturn += num2;
	    //UUID uId = UUID.fromString(nRandS).;
	    return uId;
	  }

	  public static String generateID(String t) {
	    String toReturn = "";
	    toReturn += t.substring(0, 2);
	    int num1 = (int) Math.floor(Math.random() * (aMax - aMin + 1) + aMin);
	    toReturn += num1;
	    return toReturn;
	  }
}
