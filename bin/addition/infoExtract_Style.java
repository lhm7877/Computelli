package addition;

import java.*;
import ke.*;

public class InfoExtract_Style {

    public static void InfoExtract(String text) {
        System.out.println("aRef.text");
        System.out.println(text);
    }

    public static void InfoExtract(String refText, String b) {
        System.out.println("refText" + refText);
    }

    public static void InfoExtract_Style(String refText) {
        String InfoExtract_Style_Train_Data = Classifier.getTrainData("InfoExtract_Style_Train_Data");
String resultModelPath = Classifier.learn(InfoExtract_Style_Train_Data);
String classifiedPath = Classifier.classify(refText, resultModelPath, "model_result");
String crfModelPath = Classifier.getCRFModelPath(classifiedPath);
InfoExtract.InfoExtract(refText, crfModelPath);
;
    }

    public String Style;
}
