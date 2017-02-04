// Generated from addition/Inverse.g4 by ANTLR 4.5.1

package addition;
import java.sql.*;
import addition.Computelli;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InverseParser}.
 */
public interface InverseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InverseParser#eval}.
	 * @param ctx the parse tree
	 */
	void enterEval(InverseParser.EvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link InverseParser#eval}.
	 * @param ctx the parse tree
	 */
	void exitEval(InverseParser.EvalContext ctx);
	/**
	 * Enter a parse tree produced by {@link InverseParser#equation2}.
	 * @param ctx the parse tree
	 */
	void enterEquation2(InverseParser.Equation2Context ctx);
	/**
	 * Exit a parse tree produced by {@link InverseParser#equation2}.
	 * @param ctx the parse tree
	 */
	void exitEquation2(InverseParser.Equation2Context ctx);
	/**
	 * Enter a parse tree produced by {@link InverseParser#equation1}.
	 * @param ctx the parse tree
	 */
	void enterEquation1(InverseParser.Equation1Context ctx);
	/**
	 * Exit a parse tree produced by {@link InverseParser#equation1}.
	 * @param ctx the parse tree
	 */
	void exitEquation1(InverseParser.Equation1Context ctx);
	/**
	 * Enter a parse tree produced by {@link InverseParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(InverseParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link InverseParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(InverseParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link InverseParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(InverseParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link InverseParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(InverseParser.AtomExpContext ctx);
}