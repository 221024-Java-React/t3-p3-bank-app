package stringToUUID;

import java.util.UUID;

public class StringToUUID {

	public static void main(String[] args) {
		
		
		UUID uId = IdGSTU.generateId("Neil", "Felix");
		System.out.println(uId);

	}

}
