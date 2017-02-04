// Generated from addition/Add.g4 by ANTLR 4.5.1

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
public class AddParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NUM=3, Hangul=4, WS=5;
	public static final int
		RULE_eval = 0, RULE_r = 1, RULE_atomExp = 2;
	public static final String[] ruleNames = {
		"eval", "r", "atomExp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "NUM", "Hangul", "WS"
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
	public String getGrammarFileName() { return "Add.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	int x = 0;
	int y = 0;
	int z = 0;

	public AddParser(TokenStream input) {
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
			if ( listener instanceof AddListener ) ((AddListener)listener).enterEval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AddListener ) ((AddListener)listener).exitEval(this);
		}
	}

	public final EvalContext eval() throws RecognitionException {
		EvalContext _localctx = new EvalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_eval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
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
		public List<AtomExpContext> atomExp() {
			return getRuleContexts(AtomExpContext.class);
		}
		public AtomExpContext atomExp(int i) {
			return getRuleContext(AtomExpContext.class,i);
		}
		public List<TerminalNode> Hangul() { return getTokens(AddParser.Hangul); }
		public TerminalNode Hangul(int i) {
			return getToken(AddParser.Hangul, i);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AddListener ) ((AddListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AddListener ) ((AddListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_r);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9);
			((RContext)_localctx).n1 = atomExp();
			((RContext)_localctx).value =  ((RContext)_localctx).n1.value;
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Hangul) {
				{
				{
				setState(11);
				((RContext)_localctx).n3 = match(Hangul);
				setState(12);
				((RContext)_localctx).n2 = atomExp();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			((RContext)_localctx).value = Computelli.compute((((RContext)_localctx).n3!=null?((RContext)_localctx).n3.getText():null),((RContext)_localctx).n1.value,((RContext)_localctx).n2.value);

			System.out.print (_localctx.value+ " = "+ ((RContext)_localctx).n1.value +" "+(((RContext)_localctx).n3!=null?((RContext)_localctx).n3.getText():null) +" " + ((RContext)_localctx).n2.value );
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
		public Token n;
		public RContext exp;
		public TerminalNode NUM() { return getToken(AddParser.NUM, 0); }
		public RContext r() {
			return getRuleContext(RContext.class,0);
		}
		public AtomExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AddListener ) ((AddListener)listener).enterAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AddListener ) ((AddListener)listener).exitAtomExp(this);
		}
	}

	public final AtomExpContext atomExp() throws RecognitionException {
		AtomExpContext _localctx = new AtomExpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atomExp);
		try {
			setState(27);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				((AtomExpContext)_localctx).n = match(NUM);
				((AtomExpContext)_localctx).value = Double.parseDouble((((AtomExpContext)_localctx).n!=null?((AtomExpContext)_localctx).n.getText():null)); 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				match(T__0);
				setState(23);
				((AtomExpContext)_localctx).exp = r();
				setState(24);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\7 \4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\20\n\3\f\3\16\3\23\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\36\n\4\3\4\2\2\5\2\4\6\2\2\36\2\b"+
		"\3\2\2\2\4\13\3\2\2\2\6\35\3\2\2\2\b\t\5\4\3\2\t\n\b\2\1\2\n\3\3\2\2\2"+
		"\13\f\5\6\4\2\f\21\b\3\1\2\r\16\7\6\2\2\16\20\5\6\4\2\17\r\3\2\2\2\20"+
		"\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\24\3\2\2\2\23\21\3\2\2\2\24"+
		"\25\b\3\1\2\25\5\3\2\2\2\26\27\7\5\2\2\27\36\b\4\1\2\30\31\7\3\2\2\31"+
		"\32\5\4\3\2\32\33\7\4\2\2\33\34\b\4\1\2\34\36\3\2\2\2\35\26\3\2\2\2\35"+
		"\30\3\2\2\2\36\7\3\2\2\2\4\21\35";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}