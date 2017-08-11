package addition;
import java.util.Scanner;

public class input {
	public static String input(Object object){
		if(object==null || ((String)object).equals("")){
			Scanner sc = new Scanner(System.in);
			return sc.nextLine();
		}else{
			return null;
		}
	}
}