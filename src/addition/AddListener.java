// Generated from addition/Add.g4 by ANTLR 4.5.1

package addition;
import java.sql.*;
import addition.Computelli;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AddParser}.
 */
public interface AddListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AddParser#eval}.
	 * @param ctx the parse tree
	 */
	void enterEval(AddParser.EvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AddParser#eval}.
	 * @param ctx the parse tree
	 */
	void exitEval(AddParser.EvalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AddParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(AddParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link AddParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(AddParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link AddParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(AddParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link AddParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(AddParser.AtomExpContext ctx);
}