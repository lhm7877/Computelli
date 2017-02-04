package addition;

import org.antlr.v4.runtime.*;

import addition.InverseLexer;
import addition.InverseParser;

public class demoInverse {

	public demoInverse() {
		// TODO Auto-generated constructor stub
	}

    public static void main(String[] args) throws Exception {
        ANTLRInputStream in = new ANTLRInputStream(args[0]);
        InverseLexer lexer = new InverseLexer((CharStream)in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        InverseParser parser = new InverseParser(tokens);
        System.out.println("This is demoInverse()!!");
        System.out.println(parser.eval().value); // print the value
    }
}