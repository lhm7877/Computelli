// Generated from addition/Know.g4 by ANTLR 4.5.1

package addition;
import java.sql.*;
import addition.Computelli;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KnowParser}.
 */
public interface KnowListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KnowParser#eval}.
	 * @param ctx the parse tree
	 */
	void enterEval(KnowParser.EvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnowParser#eval}.
	 * @param ctx the parse tree
	 */
	void exitEval(KnowParser.EvalContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnowParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(KnowParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnowParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(KnowParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnowParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(KnowParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnowParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(KnowParser.AtomExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnowParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(KnowParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnowParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(KnowParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnowParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(KnowParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnowParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(KnowParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KnowParser#unsigned_number}.
	 * @param ctx the parse tree
	 */
	void enterUnsigned_number(KnowParser.Unsigned_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link KnowParser#unsigned_number}.
	 * @param ctx the parse tree
	 */
	void exitUnsigned_number(KnowParser.Unsigned_numberContext ctx);
}