package addition;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.List;

public class ParseTreeTest {

	public ParseTreeTest() {
		// TODO Auto-generated constructor stub
	}

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
//        FileInputStream in = new FileInputStream("/Users/sunghee/Documents/sunghee-data/2015programs/workspace-java/rdf-prog/src/InfoExtractWithKE.java");
        FileInputStream in = new FileInputStream("/Users/sunghee/Documents/sunghee-data/2015programs/workspace-java/rdf-prog/src/InfoExtractWithKE.java");

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        // visit and print the methods names
        new MethodVisitor().visit(cu, null);

        // prints the resulting compilation unit to default system output
      System.out.println(cu.toString());
   
      
      // visit and change the methods names and parameters
      new MethodChangerVisitor().visit(cu, null);

      // prints the resulting compilation unit to default system output
      System.out.println(cu.toString());
      
      // change the methods names and parameters
      changeMethods(cu);

      // prints the changed compilation unit
      System.out.println(cu.toString());
      
      
      // creates the compilation unit
      CompilationUnit cu2 = createCU();

      // prints the created compilation unit
      System.out.println(cu2.toString());
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    public static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
            System.out.println(n.getName());
	    //return n.getName();
        }
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodChangerVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // change the name of the method to upper case
            n.setName(n.getName().toUpperCase());

            // create the new parameter
            Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "value");

            // add the parameter to the method
            ASTHelper.addParameter(n, newArg);
        }

    }
    
    private static void changeMethods(CompilationUnit cu) {
        List<TypeDeclaration> types = cu.getTypes();
        System.out.print("\nts****\n"+types.toString()+"\nts-----\n");
        for (TypeDeclaration type : types) {
            System.out.print("\nte****\n"+type.toString()+"\nte-----\n");
            List<BodyDeclaration> members = type.getMembers();
            for (BodyDeclaration member : members) {
                System.out.print("\nmem****\n"+member.toString()+"\nmem-----\n");
                if (member instanceof MethodDeclaration) {
                    MethodDeclaration method = (MethodDeclaration) member;
                    System.out.print("\nmtd****\n"+method.toString()+"\nmtd-----\n");                    
                    changeMethod(method);
                }
            }
        }
    }

    private static void changeMethod(MethodDeclaration n) {
        // change the name of the method to upper case
        n.setName(n.getName().toLowerCase());

        // create the new parameter
        Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "valueWoVisitor");

        // add the parameter to the method
        ASTHelper.addParameter(n, newArg);
    }
    /**
     * creates a method
     * @return
     */
    private static void addMethodtoCU (MethodDeclaration mc) {

        // create a method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "main");
        method.setModifiers(ModifierSet.addModifier(method.getModifiers(), ModifierSet.STATIC));
//        ASTHelper.addMember(type, method);
    	
    }
    
    /**
     * creates the compilation unit
     */
    private static CompilationUnit createCU() {
        CompilationUnit cu = new CompilationUnit();
        // set the package
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("java.parser.test")));

        // create the type declaration 
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, "GeneratedClass");
        ASTHelper.addTypeDeclaration(cu, type);

        // create a method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "main");
        method.setModifiers(ModifierSet.addModifier(method.getModifiers(), ModifierSet.STATIC));
        ASTHelper.addMember(type, method);

        // add a parameter to the method
        Parameter param = ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "args");
        param.setVarArgs(true);
        ASTHelper.addParameter(method, param);

        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        NameExpr clazz = new NameExpr("System");
        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
        MethodCallExpr call = new MethodCallExpr(field, "println");
        ASTHelper.addArgument(call, new StringLiteralExpr("Hello World!"));
        ASTHelper.addStmt(block, call);

        return cu;
    }
}
