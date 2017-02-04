// Generated from addition/Inverse.g4 by ANTLR 4.5.1

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
public class InverseParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NUM=3, UNKNOWN=4, Equal=5, Hangul=6, WS=7;
	public static final int
		RULE_eval = 0, RULE_equation2 = 1, RULE_equation1 = 2, RULE_r = 3, RULE_atomExp = 4;
	public static final String[] ruleNames = {
		"eval", "equation2", "equation1", "r", "atomExp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", null, "'X'", "'는'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "NUM", "UNKNOWN", "Equal", "Hangul", "WS"
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
	public String getGrammarFileName() { return "Inverse.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	int x = 0;
	int y = 0;
	int z = 0;
	int n2=0;
	int n1=0;
	String n3="0.0";

	public InverseParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class EvalContext extends ParserRuleContext {
		public double value;
		public Equation1Context b;
		public Equation2Context b2;
		public Equation1Context equation1() {
			return getRuleContext(Equation1Context.class,0);
		}
		public Equation2Context equation2() {
			return getRuleContext(Equation2Context.class,0);
		}
		public EvalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).enterEval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).exitEval(this);
		}
	}

	public final EvalContext eval() throws RecognitionException {
		EvalContext _localctx = new EvalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_eval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			switch (_input.LA(1)) {
			case T__0:
			case NUM:
				{
				setState(10);
				((EvalContext)_localctx).b = equation1();
				 ((EvalContext)_localctx).value = ((EvalContext)_localctx).b.value;
				}
				break;
			case UNKNOWN:
				{
				setState(13);
				((EvalContext)_localctx).b2 = equation2();
				 ((EvalContext)_localctx).value =  ((EvalContext)_localctx).b2.value;
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Equation2Context extends ParserRuleContext {
		public double value;
		public Token unon;
		public Token n3;
		public AtomExpContext n2;
		public AtomExpContext n4;
		public TerminalNode UNKNOWN() { return getToken(InverseParser.UNKNOWN, 0); }
		public List<AtomExpContext> atomExp() {
			return getRuleContexts(AtomExpContext.class);
		}
		public AtomExpContext atomExp(int i) {
			return getRuleContext(AtomExpContext.class,i);
		}
		public List<TerminalNode> Hangul() { return getTokens(InverseParser.Hangul); }
		public TerminalNode Hangul(int i) {
			return getToken(InverseParser.Hangul, i);
		}
		public Equation2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equation2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).enterEquation2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).exitEquation2(this);
		}
	}

	public final Equation2Context equation2() throws RecognitionException {
		Equation2Context _localctx = new Equation2Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_equation2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			((Equation2Context)_localctx).unon = match(UNKNOWN);
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Hangul) {
				{
				{
				setState(19);
				((Equation2Context)_localctx).n3 = match(Hangul);
				setState(20);
				((Equation2Context)_localctx).n2 = atomExp();
				setState(21);
				match(Equal);
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
			((Equation2Context)_localctx).n4 = atomExp();
			((Equation2Context)_localctx).value = Computelli.inverse((((Equation2Context)_localctx).unon!=null?((Equation2Context)_localctx).unon.getText():null),(((Equation2Context)_localctx).n3!=null?((Equation2Context)_localctx).n3.getText():null),((Equation2Context)_localctx).n2.value,((Equation2Context)_localctx).n4.value);

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

	public static class Equation1Context extends ParserRuleContext {
		public double value;
		public AtomExpContext n1;
		public Token n3;
		public Token unon;
		public AtomExpContext n4;
		public List<AtomExpContext> atomExp() {
			return getRuleContexts(AtomExpContext.class);
		}
		public AtomExpContext atomExp(int i) {
			return getRuleContext(AtomExpContext.class,i);
		}
		public List<TerminalNode> Hangul() { return getTokens(InverseParser.Hangul); }
		public TerminalNode Hangul(int i) {
			return getToken(InverseParser.Hangul, i);
		}
		public List<TerminalNode> UNKNOWN() { return getTokens(InverseParser.UNKNOWN); }
		public TerminalNode UNKNOWN(int i) {
			return getToken(InverseParser.UNKNOWN, i);
		}
		public Equation1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equation1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).enterEquation1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).exitEquation1(this);
		}
	}

	public final Equation1Context equation1() throws RecognitionException {
		Equation1Context _localctx = new Equation1Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_equation1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			((Equation1Context)_localctx).n1 = atomExp();
			((Equation1Context)_localctx).value = ((Equation1Context)_localctx).n1.value;
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Hangul) {
				{
				{
				setState(33);
				((Equation1Context)_localctx).n3 = match(Hangul);
				setState(34);
				((Equation1Context)_localctx).unon = match(UNKNOWN);
				setState(35);
				match(Equal);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			((Equation1Context)_localctx).n4 = atomExp();
			((Equation1Context)_localctx).value = Computelli.inverse((((Equation1Context)_localctx).unon!=null?((Equation1Context)_localctx).unon.getText():null), (((Equation1Context)_localctx).n3!=null?((Equation1Context)_localctx).n3.getText():null),((Equation1Context)_localctx).n1.value,((Equation1Context)_localctx).n4.value);

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
		public List<AtomExpContext> atomExp() {
			return getRuleContexts(AtomExpContext.class);
		}
		public AtomExpContext atomExp(int i) {
			return getRuleContext(AtomExpContext.class,i);
		}
		public List<TerminalNode> Hangul() { return getTokens(InverseParser.Hangul); }
		public TerminalNode Hangul(int i) {
			return getToken(InverseParser.Hangul, i);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_r);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			((RContext)_localctx).n1 = atomExp();
			((RContext)_localctx).value = ((RContext)_localctx).n1.value;
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Hangul) {
				{
				{
				setState(46);
				((RContext)_localctx).n3 = match(Hangul);
				setState(47);
				((RContext)_localctx).n2 = atomExp();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class AtomExpContext extends ParserRuleContext {
		public double value;
		public Token n;
		public RContext exp;
		public TerminalNode NUM() { return getToken(InverseParser.NUM, 0); }
		public RContext r() {
			return getRuleContext(RContext.class,0);
		}
		public AtomExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).enterAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InverseListener ) ((InverseListener)listener).exitAtomExp(this);
		}
	}

	public final AtomExpContext atomExp() throws RecognitionException {
		AtomExpContext _localctx = new AtomExpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atomExp);
		try {
			setState(62);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				((AtomExpContext)_localctx).n = match(NUM);
				((AtomExpContext)_localctx).value = Double.parseDouble((((AtomExpContext)_localctx).n!=null?((AtomExpContext)_localctx).n.getText():null)); 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__0);
				setState(58);
				((AtomExpContext)_localctx).exp = r();
				setState(59);
				match(T__1);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\tC\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\5\2\23\n\2\3\3\3\3"+
		"\3\3\3\3\3\3\7\3\32\n\3\f\3\16\3\35\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\'\n\4\f\4\16\4*\13\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5\63\n\5\f\5"+
		"\16\5\66\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6A\n\6\3\6\2\2\7\2"+
		"\4\6\b\n\2\2B\2\22\3\2\2\2\4\24\3\2\2\2\6!\3\2\2\2\b.\3\2\2\2\n@\3\2\2"+
		"\2\f\r\5\6\4\2\r\16\b\2\1\2\16\23\3\2\2\2\17\20\5\4\3\2\20\21\b\2\1\2"+
		"\21\23\3\2\2\2\22\f\3\2\2\2\22\17\3\2\2\2\23\3\3\2\2\2\24\33\7\6\2\2\25"+
		"\26\7\b\2\2\26\27\5\n\6\2\27\30\7\7\2\2\30\32\3\2\2\2\31\25\3\2\2\2\32"+
		"\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\33\3\2\2\2\36"+
		"\37\5\n\6\2\37 \b\3\1\2 \5\3\2\2\2!\"\5\n\6\2\"(\b\4\1\2#$\7\b\2\2$%\7"+
		"\6\2\2%\'\7\7\2\2&#\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*"+
		"(\3\2\2\2+,\5\n\6\2,-\b\4\1\2-\7\3\2\2\2./\5\n\6\2/\64\b\5\1\2\60\61\7"+
		"\b\2\2\61\63\5\n\6\2\62\60\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3"+
		"\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\b\5\1\28\t\3\2\2\29:\7\5\2\2:A"+
		"\b\6\1\2;<\7\3\2\2<=\5\b\5\2=>\7\4\2\2>?\b\6\1\2?A\3\2\2\2@9\3\2\2\2@"+
		";\3\2\2\2A\13\3\2\2\2\7\22\33(\64@";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}