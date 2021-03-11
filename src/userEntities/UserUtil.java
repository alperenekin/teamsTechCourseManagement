package userEntities;
import java.util.Random;
class UserUtil {
	public static int generateId() {
		//not unique
		boolean notUnique = false;
		int id = -1;
		while(notUnique){
			Random rand = new Random();		
			id = 1 + rand.nextInt(1000); //util method creates between 0 and 999 initially		
			notUnique = isUnique(id);
		}
		return id;
	}
	
	private static boolean isUnique(int id) {
		// TODO Auto-generated method stub
		/* 
		 * there should be file or variable that hold all the generated user id's so that new id 
		 * can be check if its unique or not
		 */
		return false;
	}
	
}
