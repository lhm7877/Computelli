// Generated from addition/Know.g4 by ANTLR 4.5.1

package addition;
import java.sql.*;
import addition.Computelli;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KnowParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, UNSIGNED_INT=6, UNSIGNED_FLOAT=7, 
		Hangul=8, WS=9;
	public static final int
		RULE_eval = 0, RULE_r = 1, RULE_atomExp = 2, RULE_number = 3, RULE_unary_operator = 4, 
		RULE_unsigned_number = 5;
	public static final String[] ruleNames = {
		"eval", "r", "atomExp", "number", "unary_operator", "unsigned_number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'는'", "'('", "')'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "UNSIGNED_INT", "UNSIGNED_FLOAT", 
		"Hangul", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Know.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	int x = 0;
	int y = 0;
	int z = 0;

	public KnowParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class EvalContext extends ParserRuleContext {
		public double value;
		public RContext b;
		public RContext r() {
			return getRuleContext(RContext.class,0);
		}
		public EvalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).enterEval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).exitEval(this);
		}
	}

	public final EvalContext eval() throws RecognitionException {
		EvalContext _localctx = new EvalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_eval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			((EvalContext)_localctx).b = r();
			((EvalContext)_localctx).value =  ((EvalContext)_localctx).b.value;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RContext extends ParserRuleContext {
		public double value;
		public AtomExpContext n1;
		public Token n3;
		public AtomExpContext n2;
		public AtomExpContext n4;
		public List<AtomExpContext> atomExp() {
			return getRuleContexts(AtomExpContext.class);
		}
		public AtomExpContext atomExp(int i) {
			return getRuleContext(AtomExpContext.class,i);
		}
		public TerminalNode Hangul() { return getToken(KnowParser.Hangul, 0); }
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			((RContext)_localctx).n1 = atomExp();
			((RContext)_localctx).value =  ((RContext)_localctx).n1.value;
			{
			setState(17);
			((RContext)_localctx).n3 = match(Hangul);
			setState(18);
			((RContext)_localctx).n2 = atomExp();
			setState(19);
			match(T__0);
			setState(20);
			((RContext)_localctx).n4 = atomExp();
			}

			Computelli.learn((((RContext)_localctx).n3!=null?((RContext)_localctx).n3.getText():null),((RContext)_localctx).n1.value,((RContext)_localctx).n2.value,((RContext)_localctx).n4.value);

			System.out.print (((RContext)_localctx).n4.value+ " = "+ ((RContext)_localctx).n1.value+" " +(((RContext)_localctx).n3!=null?((RContext)_localctx).n3.getText():null) +" " + ((RContext)_localctx).n2.value );
			/*   return (y + z);*/

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomExpContext extends ParserRuleContext {
		public double value;
		public NumberContext n;
		public RContext exp;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public RContext r() {
			return getRuleContext(RContext.class,0);
		}
		public AtomExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).enterAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).exitAtomExp(this);
		}
	}

	public final AtomExpContext atomExp() throws RecognitionException {
		AtomExpContext _localctx = new AtomExpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atomExp);
		try {
			setState(32);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
			case UNSIGNED_INT:
			case UNSIGNED_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				((AtomExpContext)_localctx).n = number();
				((AtomExpContext)_localctx).value = Double.parseDouble((((AtomExpContext)_localctx).n!=null?_input.getText(((AtomExpContext)_localctx).n.start,((AtomExpContext)_localctx).n.stop):null)); 
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(T__1);
				setState(28);
				((AtomExpContext)_localctx).exp = r();
				setState(29);
				match(T__2);
				((AtomExpContext)_localctx).value =  ((AtomExpContext)_localctx).exp.value;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public Unsigned_numberContext unsigned_number() {
			return getRuleContext(Unsigned_numberContext.class,0);
		}
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_la = _input.LA(1);
			if (_la==T__3 || _la==T__4) {
				{
				setState(34);
				unary_operator();
				}
			}

			setState(37);
			unsigned_number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_operatorContext extends ParserRuleContext {
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).exitUnary_operator(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unsigned_numberContext extends ParserRuleContext {
		public TerminalNode UNSIGNED_INT() { return getToken(KnowParser.UNSIGNED_INT, 0); }
		public TerminalNode UNSIGNED_FLOAT() { return getToken(KnowParser.UNSIGNED_FLOAT, 0); }
		public Unsigned_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsigned_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).enterUnsigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KnowListener ) ((KnowListener)listener).exitUnsigned_number(this);
		}
	}

	public final Unsigned_numberContext unsigned_number() throws RecognitionException {
		Unsigned_numberContext _localctx = new Unsigned_numberContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unsigned_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_la = _input.LA(1);
			if ( !(_la==UNSIGNED_INT || _la==UNSIGNED_FLOAT) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13.\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4#\n\4\3\5\5\5&\n\5\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\4\3\2\6\7\3\2\b\t)\2\16\3"+
		"\2\2\2\4\21\3\2\2\2\6\"\3\2\2\2\b%\3\2\2\2\n)\3\2\2\2\f+\3\2\2\2\16\17"+
		"\5\4\3\2\17\20\b\2\1\2\20\3\3\2\2\2\21\22\5\6\4\2\22\23\b\3\1\2\23\24"+
		"\7\n\2\2\24\25\5\6\4\2\25\26\7\3\2\2\26\27\5\6\4\2\27\30\3\2\2\2\30\31"+
		"\b\3\1\2\31\5\3\2\2\2\32\33\5\b\5\2\33\34\b\4\1\2\34#\3\2\2\2\35\36\7"+
		"\4\2\2\36\37\5\4\3\2\37 \7\5\2\2 !\b\4\1\2!#\3\2\2\2\"\32\3\2\2\2\"\35"+
		"\3\2\2\2#\7\3\2\2\2$&\5\n\6\2%$\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\5\f\7"+
		"\2(\t\3\2\2\2)*\t\2\2\2*\13\3\2\2\2+,\t\3\2\2,\r\3\2\2\2\4\"%";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}