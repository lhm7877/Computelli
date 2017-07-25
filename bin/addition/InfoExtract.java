package addition;
import java.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class InfoExtract{
public static void InfoExtract (Ref ref) // aRef is an instance of RefSet
{
         
                System.out.println("InfoExtract 함수 실행중");
System.out.println("ref : "+ref);
	// in case of mallet ...we need a seq. labeler
	
String command = "java -cp  \"C:/mallet/class;C:/mallet/lib/mallet-deps.jar;\"" // class path
				+ " cc.mallet.fst.SimpleTagger " // class to be executed
				+ " --train true --model-file C:/mallet/nouncrf  " // model to be trained
				+ "C:/mallet/sample"; // training data
		executeSystemCommand(command);

		String command2 = "java -cp  \"C:/mallet/class;C:/mallet/lib/mallet-deps.jar\"" // class path
				+ " cc.mallet.fst.SimpleTagger "
				+ " --model-file C:/mallet/nouncrf "
				+ " C:/mallet/stest";
	
	executeSystemCommand(command2);


}
public static void executeSystemCommand(String s)
{
//System.out.println(s);
//	String s= null;

    try {
        
    // run the Unix "ps -ef" command
        // using the Runtime exec method:
        Process p = Runtime.getRuntime().exec(s);
  	//	+ "/Users/sunghee/Documents/sunghee-data/2016programs/mallet-2.0.7/bin/mallet ");
        
        BufferedReader stdInput = new BufferedReader(new 
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new 
             InputStreamReader(p.getErrorStream()));

        // read the output from the command
//        System.out.println("Here is the standard output of the command:");
//        while ((s = stdInput.readLine()) != null) {
   //         System.out.println(s);
      //  }
        
        // read any errors from the attempted command
   //     System.out.println("Here is the standard error of the command (if any):");
    //    while ((s = stdError.readLine()) != null) {
//            System.out.println(s);
       // }
        
    }
    catch (IOException e) {
        System.out.println("exception happened - here is what I know: ");
        e.printStackTrace();
    }	
}
} 