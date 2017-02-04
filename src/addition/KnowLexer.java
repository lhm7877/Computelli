// Generated from addition/Know.g4 by ANTLR 4.5.1

package addition;
import java.sql.*;
import addition.Computelli;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KnowLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, UNSIGNED_INT=6, UNSIGNED_FLOAT=7, 
		Hangul=8, WS=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "UNSIGNED_INT", "UNSIGNED_FLOAT", 
		"Exponent", "Hangul", "WS"
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


	int x = 0;
	int y = 0;
	int z = 0;


	public KnowLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Know.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13`\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\7\7%\n\7\f\7\16"+
		"\7(\13\7\5\7*\n\7\3\b\6\b-\n\b\r\b\16\b.\3\b\3\b\7\b\63\n\b\f\b\16\b\66"+
		"\13\b\3\b\5\b9\n\b\3\b\3\b\6\b=\n\b\r\b\16\b>\3\b\5\bB\n\b\3\b\6\bE\n"+
		"\b\r\b\16\bF\3\b\5\bJ\n\b\3\t\3\t\5\tN\n\t\3\t\6\tQ\n\t\r\t\16\tR\3\n"+
		"\6\nV\n\n\r\n\16\nW\3\13\6\13[\n\13\r\13\16\13\\\3\13\3\13\2\2\f\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\2\23\n\25\13\3\2\6\4\2GGgg\4\2--//\4\2\u3133"+
		"\u3165\uac02\ud7a5\5\2\13\f\17\17\"\"l\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\3\27\3\2\2\2\5\31\3\2\2\2\7\33\3\2\2\2\t\35\3\2\2\2\13\37"+
		"\3\2\2\2\r)\3\2\2\2\17I\3\2\2\2\21K\3\2\2\2\23U\3\2\2\2\25Z\3\2\2\2\27"+
		"\30\7\ub296\2\2\30\4\3\2\2\2\31\32\7*\2\2\32\6\3\2\2\2\33\34\7+\2\2\34"+
		"\b\3\2\2\2\35\36\7-\2\2\36\n\3\2\2\2\37 \7/\2\2 \f\3\2\2\2!*\7\62\2\2"+
		"\"&\4\63;\2#%\4\62;\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'*\3\2"+
		"\2\2(&\3\2\2\2)!\3\2\2\2)\"\3\2\2\2*\16\3\2\2\2+-\4\62;\2,+\3\2\2\2-."+
		"\3\2\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\64\7\60\2\2\61\63\4\62;\2"+
		"\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\658\3\2\2\2\66"+
		"\64\3\2\2\2\679\5\21\t\28\67\3\2\2\289\3\2\2\29J\3\2\2\2:<\7\60\2\2;="+
		"\4\62;\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@B\5\21\t\2"+
		"A@\3\2\2\2AB\3\2\2\2BJ\3\2\2\2CE\4\62;\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2"+
		"FG\3\2\2\2GH\3\2\2\2HJ\5\21\t\2I,\3\2\2\2I:\3\2\2\2ID\3\2\2\2J\20\3\2"+
		"\2\2KM\t\2\2\2LN\t\3\2\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OQ\4\62;\2PO\3\2"+
		"\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\22\3\2\2\2TV\t\4\2\2UT\3\2\2\2VW\3"+
		"\2\2\2WU\3\2\2\2WX\3\2\2\2X\24\3\2\2\2Y[\t\5\2\2ZY\3\2\2\2[\\\3\2\2\2"+
		"\\Z\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\b\13\2\2_\26\3\2\2\2\20\2&).\648>A"+
		"FIMRW\\\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}