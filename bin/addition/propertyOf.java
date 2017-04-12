import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

import com.github.javaparser.ast.body.Parameter;

import addition.ConnectionAlgoDB;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.stmt.BlockStmt;

public class propertyOf {
	ResultSet rs = null;
	ResultSet rs2 = null;
	String queryOperator;

	public void addField(String parentValue, String outputStackValue, String outputStackValue2) {
		System.out.println("propertyOf addfield 실행");
		System.out.println("parentValue 는 : " + parentValue);
		System.out.println("outputStackValue 는 : " + outputStackValue);
		System.out.println("outputStackValue2 는 : " + outputStackValue2);
		String propertyTypeAndName[] = outputStackValue.split(" ");
		
		rs = ConnectionAlgoDB.getRelevantAlgorithm(parentValue);
		rs2 = ConnectionAlgoDB.getRelevantAlgorithm(outputStackValue2);
		
		addEachClass(rs,propertyTypeAndName);
		String parentInArgType = null;
		try {
			parentInArgType = rs.getString("ClassName")+ "_"+ propertyTypeAndName[1];
		} catch (SQLException e) {
			e.printStackTrace();
		}
		addEachClass2(rs2,propertyTypeAndName,parentInArgType);
	}
	public void addEachClass(ResultSet rs,String[] propertyTypeAndName){
		String source = null;
		String inArgType = null;
		String outArgType = null;
		String className = null;
		int numInArg = 0;
		String operSymbol = null;
		String methodName = null;
		try {
			if (rs.next()) {
				className = rs.getString("ClassName");
				inArgType = rs.getString("InArgType");
				outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				numInArg = rs.getInt("NumInArg");
				operSymbol = rs.getString("OperSymbol");
				methodName = rs.getString("MethodName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("************************");
		System.out.println(className);

		
		
		CompilationUnit cu;
		cu = JavaParser.parse(source);
		ClassOrInterfaceDeclaration type = cu.getClassByName(className);
		EnumSet<Modifier> modifiers2 = EnumSet.of(Modifier.PUBLIC);
		FieldDeclaration field2 = new FieldDeclaration(modifiers2, new ClassOrInterfaceType(propertyTypeAndName[0]),
				new VariableDeclarator(new VariableDeclaratorId(propertyTypeAndName[1])));
		type.addMember(field2);
		type.setName(className + "_" + propertyTypeAndName[1]);
		
		ConnectionAlgoDB.updateDB("algorithm", className + "_" + propertyTypeAndName[1],className + "_" + propertyTypeAndName[1], inArgType, outArgType, cu.toString(),numInArg,operSymbol,methodName);
	}
	
	public void addEachClass2(ResultSet rs,String[] propertyTypeAndName, String parentInArgType){
		String source = null;
		String inArgType = null;
		String outArgType = null;
		String className = null;
		int numInArg = 0;
		String operSymbol = null;
		String methodName = null;
		try {
			if (rs.next()) {
				className = rs.getString("ClassName");
				inArgType = rs.getString("InArgType");
				outArgType = rs.getString("OutArgType");
				source = rs.getString("Source");
				numInArg = rs.getInt("NumInArg");
				operSymbol = rs.getString("OperSymbol");
				methodName = rs.getString("MethodName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("************************");
		System.out.println(className);
		
		CompilationUnit cu;
		cu = JavaParser.parse(source);
		ClassOrInterfaceDeclaration type = cu.getClassByName(className);
		EnumSet<Modifier> modifiers2 = EnumSet.of(Modifier.PUBLIC);
		FieldDeclaration field2 = new FieldDeclaration(modifiers2, new ClassOrInterfaceType(propertyTypeAndName[0]),
				new VariableDeclarator(new VariableDeclaratorId(propertyTypeAndName[1])));
	
				
        MethodDeclaration method = type.addMethod(className, Modifier.PUBLIC, Modifier.STATIC);
        method.addAndGetParameter(String.class, "refText");

    
        BlockStmt block = new BlockStmt();
block.addStatement(		
			"String " + className +"_"+ propertyTypeAndName[1] + "_Train_Data = Classifier.getTrainData(\""+ className + "_"+propertyTypeAndName[1]+"_"+"Train_Data\");\n"+
			"String resultModelPath = Classifier.learn(" + className + "_"+propertyTypeAndName[1]+"_Train_Data);\n"+
			"String classifiedPath = Classifier.classify(refText, resultModelPath, \"model_result\");\n"+
			"String crfModelPath = Classifier.getCRFModelPath(classifiedPath);\n"+
			"Ref_Style ref_Style = new Ref_Style();\n"+
			"ref_Style.setText(refText);\n"+
			"ref_Style.Style = crfModelPath;\n"+
			className+"."+className+"(ref_Style.getText(), ref_Style.Style);\n"
			);
        method.setBody(block);
		
		type.addMember(field2);
		type.setName(className + "_" + propertyTypeAndName[1]);
		
		ConnectionAlgoDB.updateDB("algorithm", className + "_" + propertyTypeAndName[1],className + "_" + propertyTypeAndName[1], parentInArgType, outArgType, cu.toString(),numInArg,operSymbol,methodName);
	}
}
