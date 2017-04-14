import java.*;

public class infoExtract_Style {

    public static void infoExtract(String text) {
        System.out.println("지금 mallet 알고리즘 실행 중");
        System.out.println("aRef.text");
        System.out.println(text);
    }

    public static void infoExtract_Style(String refText) {
        String infoExtract_Style_Train_Data = Classifier.getTrainData("infoExtract_Style_Train_Data");
String resultModelPath = Classifier.learn(infoExtract_Style_Train_Data);
String classifiedPath = Classifier.classify(refText, resultModelPath, "model_result");
String crfModelPath = Classifier.getCRFModelPath(classifiedPath);
Ref_Style ref_Style = new Ref_Style();
ref_Style.setText(refText);
ref_Style.Style = crfModelPath;
infoExtract.infoExtract(ref_Style.getText(), ref_Style.Style);
;
    }

    public String Style;
}
