package addition;

import org.antlr.v4.runtime.*;

import addition.AddLexer;
import addition.AddParser;

public class demo {
	public static double qna(String args) throws Exception {
        ANTLRInputStream in = new ANTLRInputStream(args);
        AddLexer lexer = new AddLexer((CharStream)in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AddParser parser = new AddParser(tokens);
        double result = parser.r().value;
        System.out.println(""+result); // print the value		
        return result;
	}
	
	
    public static void main(String[] args) throws Exception {
    	demo.qna(args[0]);
    	//    	demo aDemo = new demo();
//    	aDemo.qna(args[0]);
//        ANTLRInputStream in = new ANTLRInputStream(args[0]);
//        AddLexer lexer = new AddLexer((CharStream)in);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        AddParser parser = new AddParser(tokens);
//        double result = parser.r().value;
//        System.out.println(parser.r().value); // print the value		
    }
}

