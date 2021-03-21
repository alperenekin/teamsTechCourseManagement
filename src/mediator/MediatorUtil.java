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

}
