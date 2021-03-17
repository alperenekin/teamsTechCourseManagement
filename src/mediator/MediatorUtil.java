package mediator;
import java.util.Random;
public class MediatorUtil {
	public static int generateId(IIdentifiable[] list) {
		//not unique
		boolean notUnique = false;
		int id = -1;
		while(notUnique){
			Random rand = new Random();		
			id = 1 + rand.nextInt(1000); //util method creates between 0 and 999 initially		
			notUnique = isUnique(id, list);
		}
		return id;
	}
	
	private static boolean isUnique(int id,IIdentifiable[] list) {
		for (int i = 0 ; i < list.length; i++) {
			if(list[i].getId()==id) {
				return false;
			}
		}
		return true;
	}
		
}

