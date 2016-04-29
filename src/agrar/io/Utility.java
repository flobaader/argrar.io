package agrar.io;

public class Utility {

	public static double getDistance(Circle c1, Circle c2){
		int y_dis = c1.getLocation().y - c2.getLocation().y;
		int x_dis = c1.getLocation().x - c2.getLocation().x;
		//c� = a� + b�	
		return Math.sqrt(Math.pow(y_dis, 2) + Math.pow(x_dis, 2));
	}
	
}
