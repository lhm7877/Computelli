package addition;

import java.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ke.*;

public class InfoExtract_Style {

    public static // aRef is an instance of RefSet
    void InfoExtract(// aRef is an instance of RefSet
    Ref ref) {
        System.out.println("InfoExtract 함수 실행중");
        System.out.println("ref : " + ref);
        // in case of mallet ...we need a seq. labeler
        String command = // class path
        "java -cp  \"C:/mallet/class;C:/mallet/lib/mallet-deps.jar;\"" + // class to be executed
        " cc.mallet.fst.SimpleTagger " + // model to be trained
        " --train true --model-file C:/mallet/nouncrf  " + // training data
        "C:/mallet/sample";
        executeSystemCommand(command);
        String command2 = // class path
        "java -cp  \"C:/mallet/class;C:/mallet/lib/mallet-deps.jar\"" + " cc.mallet.fst.SimpleTagger " + " --model-file C:/mallet/nouncrf " + " C:/mallet/stest";
        executeSystemCommand(command2);
    }

    public static void executeSystemCommand(String s) {
        try {
            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(s);
            //	+ "/Users/sunghee/Documents/sunghee-data/2016programs/mallet-2.0.7/bin/mallet ");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
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
        } catch (IOException e) {
            System.out.println("exception happened - here is what I know: ");
            e.printStackTrace();
        }
    }

    public static void InfoExtract_Style(addition.Ref_Style refText) {
        String InfoExtract_Style_Train_Data = Classifier.getTrainData("InfoExtract_Style_Train_Data");
String classifiedValue = Classifier.classifyWithWeka(InfoExtract_Style_Train_Data);
String crfModelPath = Classifier.getCRFModelPath(classifiedValue);
InfoExtract.InfoExtract(refText);
;
    }

    public String Style;
}
