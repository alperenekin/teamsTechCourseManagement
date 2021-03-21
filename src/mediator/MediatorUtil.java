package mediator;
import java.util.ArrayList;
import java.util.Random;

import userEntities.User;
public class MediatorUtil {
	public static int generateId(ArrayList<User> list) {
		//not unique
		boolean notUnique = true;
		int id = -1;
		while(notUnique){
			Random rand = new Random();		
			id = 1 + rand.nextInt(1000); //util method creates between 0 and 999 initially		
			notUnique = !isUnique(id, list);
		}
		return id;
	}
	
	private static boolean isUnique(int id, ArrayList<User> list) {
		for (int i = 0 ; i < list.size(); i++) {
			if(list.get(i).getId()==id) {
				return false;
			}
		}
		return true;
	}

	public static String generatePassword() {
		final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	    final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	    final String NUMBER = "0123456789";

	    final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

	    StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
			Random rand = new Random();		
            int rndCharAt = rand.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        //System.out.println(sb); //delete later
        return sb.toString();	
  }
}
